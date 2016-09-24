package dcnh.pagecontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/*
 * 获取html页面
 */
@Controller
public class PageController {
	
	@RequestMapping("/")
	public String getLoginPage(){
		return "login";
	}
	
	@RequestMapping("/superadmin")
	public String getSuperAdminPage(){
		return "/superadmin/superadmin";
	}
	
	@RequestMapping("/getaccountmanagepage")
	public String getAccountManagePage(){
		return "/superadmin/accountManage";
	}
	
	@RequestMapping("/getschooladminpage")
	public String getSchoolAdminPage(){
		return "schooladmin";
	}
	
	@RequestMapping("/getjudgepage")
	public String getJudgepage(){
		return "judge";
	}
	
	@RequestMapping("/getuserinfopage")
	public String getUserInfopage(){
		return "userinfo";
	}
	
	@RequestMapping("/getcreateaccountpage")
	public String getcreateAccount(){
		return "/superadmin/createaccount";
	}
	
}
