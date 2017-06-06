package dcnh.handler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import dcnh.ApplicationContext;
import dcnh.model.ResponseMessage;

/**
 * @author WuJie
 * @date 2017年4月23日下午10:12:39
 * @version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration()
@SpringBootTest(classes = ApplicationContext.class)
public class ProjectManageHandlerTest {
	@Autowired
	private ProjectManageHandler handler;;

	@Test
	public void testDeleteProjectByIdAndSchoolName() {
		ResponseMessage response = new ResponseMessage();
		handler.deleteProjectByIdAndSchoolName(13, "大连海事大学", response, "dmu", "创意作品");
		assert (response.getCode() == 1);
	}
	
	@Test
	public void testGetAllCategory() {
		System.err.println(handler.getAllCategory());
	}

}
