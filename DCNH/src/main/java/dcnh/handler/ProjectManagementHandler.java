package dcnh.handler;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dcnh.dbservice.UserDBService;
import dcnh.mode.ResponseMessage;

@Component
public class ProjectManagementHandler {
	@Autowired
	private UserDBService userDBService;
	
	public ResponseMessage addProject(HttpSession session,String userName,String password) {
		
		return null;
	}
}
