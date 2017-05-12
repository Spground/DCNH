package dcnh.mapper;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dcnh.ApplicationContext;

/**
 * @author WuJie
 * @date 2017年5月11日下午4:12:05
 * @version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationContext.class)
public class SchoolProjectCountDBMapperTest {

	@Autowired
	SchoolProjectCountDBMapper mapper;

	@Test
	public void testInsertSchoolProjectCountItem() {
		int r = mapper.insertSchoolProjectCountItem(16, 2, 20);
		assertEquals(r, 1);
	}

	@Test
	public void testGetUploadedProjectCount() {
		int r = mapper.getUploadedProjectCount(16, 2);
		System.out.println(r);
		assertEquals(r, 0);
	}

	@Test
	public void testGetLimitProjectCount() {
		int r = mapper.getLimitProjectCount(16, 2);
		System.out.println(r);
		assertEquals(r, 20);
	}

	@Test
	public void testUpdateUploadCount() {
		int r = mapper.updateUploadCount(16, 2, 14);
		System.out.println(r);
		assertEquals(r, 1);
	}

}
