package dcnh.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dcnh.ApplicationContext;

/**
 * @author WuJie
 * @date 2017年5月3日下午4:33:07
 * @version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationContext.class)
public class SchoolDBMapperTest {
	@Autowired
	SchoolDBMapper schoolDBMapper;

	@Test
	public void testGetAllSchool() {
		List<String> schools = schoolDBMapper.getAllSchool();
		assert (schools != null);
		System.out.println(schools);
	}

}
