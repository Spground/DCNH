package dcnh.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dcnh.ApplicationContext;
import dcnh.model.MainCategory;
import dcnh.model.SubCategory;

/**
 * @author WuJie
 * @date 2017年5月3日下午7:33:37
 * @version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationContext.class)
public class CategoryDBMapperTest {
	@Autowired
	CategoryDBMapper categoryDBMapper;

	@Test
	public void testGetAllMainCategory() {
		List<MainCategory> mainCategries = categoryDBMapper.getAllMainCategory();
		assert (mainCategries != null);
		System.out.println(mainCategries);
	}

	@Test
	public void testGetAllSubCategory() {
		List<MainCategory> mainCategries = categoryDBMapper.getAllMainCategory();
		for (MainCategory main : mainCategries) {
			List<SubCategory> sub = categoryDBMapper.getAllSubCategory(main.getMainCategoryId());
			assert (sub != null);
			System.out.println(sub);
		}
	}

	@Test
	public void testUpdateMainCategoryProportion() {
		List<MainCategory> mainCategries = categoryDBMapper.getAllMainCategory();
		System.out.println(categoryDBMapper.getAllMainCategory());
		for (MainCategory main : mainCategries) {
			int r = categoryDBMapper.updateMainCategoryProportion(main.getMainCategoryName(), main.getProportion() + 1);
			assert (r != 0);
		}
		System.out.println(categoryDBMapper.getAllMainCategory());
	}

}
