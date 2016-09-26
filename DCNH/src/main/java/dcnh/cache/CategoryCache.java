package dcnh.cache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dcnh.mapper.CategoryDBMapper;
import dcnh.mode.BaseCategory;
import dcnh.mode.MainCategory;
import dcnh.mode.SubCategory;

@Component
public class CategoryCache implements InitializingBean{
	@Autowired
	private CategoryDBMapper categoryDBMapper;
	
	private HashMap<String,MainCategory> cacheMap = new HashMap<String,MainCategory>();
	
	private List<String> mainCategoryNamesList = new LinkedList<String>(); 
	public synchronized void refersh(){
		cacheMap.clear();
		List<BaseCategory> allMainCategory = categoryDBMapper.getAllMainCategory();
		for(BaseCategory baseCategory:allMainCategory){
			MainCategory mainCategory = new MainCategory();
			mainCategory.setId(baseCategory.getId());
			mainCategory.setName(baseCategory.getName());
			
			List<SubCategory> subCategorys = categoryDBMapper.getAllSubCategory(mainCategory.getId());
			mainCategory.setAllsubCategorys(subCategorys);
			cacheMap.put(mainCategory.getName(), mainCategory);
			
			mainCategoryNamesList.add(mainCategory.getName());
		}
	}

	public List<String> getAllMainCategoryNames(){
		return mainCategoryNamesList;
	}
	
	public boolean check(String mainCategoryName,String subCategoryName){
		if(!cacheMap.containsKey(mainCategoryName)){
			return false;
		}
		MainCategory mainCategory = cacheMap.get(mainCategoryName);
		List<SubCategory> subCategorys =  mainCategory.getAllsubCategorys();
		if(subCategorys==null || subCategorys.isEmpty()){
			return false;
		}
		
		for(SubCategory subCategory : subCategorys){
			if(subCategory.getName().equals(subCategoryName)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		refersh();
	}
	
	
	
}
