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
	
	private HashMap<String,MainCategory> cacheMapName = new HashMap<String,MainCategory>();
	
	private HashMap<Integer,MainCategory> cacheMapId = new HashMap<Integer,MainCategory>();
	
	private List<String> mainCategoryNamesList = new LinkedList<String>(); 
	
	public synchronized void refersh(){
		cacheMapName.clear();
		List<BaseCategory> allMainCategory = categoryDBMapper.getAllMainCategory();
		for(BaseCategory baseCategory:allMainCategory){
			MainCategory mainCategory = new MainCategory();
			mainCategory.setId(baseCategory.getId());
			mainCategory.setName(baseCategory.getName());
			List<SubCategory> subCategorys = categoryDBMapper.getAllSubCategory(mainCategory.getId());
			for(SubCategory subCategory:subCategorys){
				System.out.println("###### "+subCategory.getName());
			}
			mainCategory.setAllsubCategorys(subCategorys);
			cacheMapName.put(mainCategory.getName(), mainCategory);
			cacheMapId.put(mainCategory.getId(),mainCategory);
			mainCategoryNamesList.add(mainCategory.getName());
		}
	}

	public List<String> getAllMainCategoryNames(){
		return mainCategoryNamesList;
	}
	
	public boolean check(String mainCategoryName,String subCategoryName){
		if(!cacheMapName.containsKey(mainCategoryName)){
			return false;
		}
		MainCategory mainCategory = cacheMapName.get(mainCategoryName);
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
	
	public int getIdByName(String name){
		
		if(cacheMapName.containsKey(name)){
			return cacheMapName.get(name).getId();
		}
		else{
			System.out.println("null "+name);
			return -1;
		}
	}
	
	public MainCategory getMainCategory(String name){
		return cacheMapName.get(name);
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		refersh();
	}
	
	public void updateMainCategory(String categoryName,int proportion){
		MainCategory category = getMainCategory(categoryName);
		category.setProportion(proportion);
		categoryDBMapper.updateCategoryproportion(categoryName, proportion);
	}
	
}
