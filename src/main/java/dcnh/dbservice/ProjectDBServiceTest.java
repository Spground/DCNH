package dcnh.dbservice;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import dcnh.ApplicationContext;

/**
 * @author WuJie
 * @date 2017年4月23日下午9:05:33
 * @version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration()
@SpringBootTest(classes = ApplicationContext.class)
public class ProjectDBServiceTest {
	@Resource
	ProjectDBService service;

	@Test
	public void testDeleteProjectByIdAndSchoolName() {
		int count = service.deleteProjectByIdAndSchoolName(11, "大连理工大");
		assert (count != 1);
	}

}
