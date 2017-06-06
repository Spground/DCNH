package dcnh.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dcnh.mapper.CategoryDBMapper;
import dcnh.model.MainCategory;
import dcnh.model.SubCategory;

@Component
public class CategoryCache implements InitializingBean {

	@Autowired
	private CategoryDBMapper categoryDBMapper;

	private Map<String, MainCategory> cacheName2Model = new HashMap<String, MainCategory>();//主分类名<--->主分类对象

	private Map<Integer, MainCategory> cacheId2Model = new HashMap<Integer, MainCategory>();//主分类ID<--->主分类对象
	
	private List<String> mainCategoryNamesList = new LinkedList<String>();

	public synchronized void refersh() {
		cacheName2Model.clear();
		cacheId2Model.clear();
		mainCategoryNamesList.clear();
		List<MainCategory> allMainCategory = categoryDBMapper.getAllMainCategory();
		for (MainCategory mainCategory : allMainCategory) {			
			List<SubCategory> subCategories = mainCategory.getSubCategories();
			for(SubCategory subCategory : subCategories)
				System.out.println("###### " + subCategory.getSubCategoryName());
			cacheName2Model.put(mainCategory.getMainCategoryName(), mainCategory);
			cacheId2Model.put(mainCategory.getMainCategoryId(), mainCategory);
			mainCategoryNamesList.add(mainCategory.getMainCategoryName());
		}
	}

	public List<String> getAllMainCategoryNames() {
		return mainCategoryNamesList;
	}
	
	public Map<String, MainCategory> getAllMainCategoriesMappedByName() {
		return cacheName2Model;
	}
	
	public List<MainCategory> getAllMainCategories() {
		return new ArrayList<>(cacheName2Model.values());
	}
	
	/**
	 * 检查是否有从属关系
	 * @param mainCategoryName
	 * @param subCategoryName
	 * @return
	 */
	public boolean check(String mainCategoryName, String subCategoryName) {
		if (!cacheName2Model.containsKey(mainCategoryName)) {
			return false;
		}
		MainCategory mainCategory = cacheName2Model.get(mainCategoryName);
		List<SubCategory> subCategorys = mainCategory.getSubCategories();
		if (subCategorys == null || subCategorys.isEmpty()) {
			return false;
		}
		for (SubCategory subCategory : subCategorys) {
			if (subCategory.getSubCategoryName().equals(subCategoryName)) {
				return true;
			}
		}
		return false;
	}
	
	public int getMainCategoryIdByMainCategoryName(String mainCategoryName) {

		if (cacheName2Model.containsKey(mainCategoryName)) {
			return cacheName2Model.get(mainCategoryName).getMainCategoryId();
		} else {
			System.out.println("null " + mainCategoryName);
			return -1;
		}
	}

	public MainCategory getMainCategoryByMainCategoryName(String mainCategoryName) {
		return cacheName2Model.get(mainCategoryName);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		refersh();
	}

	public void updateMainCategoryProportion(String mainCategoryName, int proportion) {
		MainCategory category = getMainCategoryByMainCategoryName(mainCategoryName);
		category.setProportion(proportion);
		categoryDBMapper.updateMainCategoryProportion(mainCategoryName, proportion);
	}

}
