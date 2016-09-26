package dcnh.pagecontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * 获取html页面
 */
@Controller
public class SuperAdminPageController {
	
	@RequestMapping("/superadmin")
	public String getSuperAdminPage(){
		return "/superadmin/superadmin";
	}
	
	@RequestMapping("/getaccountmanagepage")
	public String getAccountManagePage(){
		return "/superadmin/accountManage";
	}
	
	@RequestMapping("/getcreateaccountpage")
	public String getcreateAccount(){
		return "/superadmin/createaccount";
	}
	
	@RequestMapping("/getprojectmanagepage")
	public String getProjectManagePage(){
		return "/superadmin/projectManage";
	}
	
}
