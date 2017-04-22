package dcnh.handler;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import dcnh.mode.BaseUser;
import dcnh.mode.ResponseMessage;

/**
*@author WuJie
*@date 2017年4月21日下午9:28:15
*@version 1.0
**/
public class ProjectManageHandlerTest {
	
	ProjectManageHandler hander = new ProjectManageHandler();
	
	@Test
	public void test() {
		BaseUser user = new BaseUser();
		user.setUserName("admin");
		ResponseMessage response = new ResponseMessage();
		hander.checkInfo(user, "创业直通车", "", "", response);
	}

}
