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
	
	
	
	@RequestMapping("/schooladmin")
	public String getSchoolAdminPage(){
		return "/schooladmin/schooladmin";
	}
	
	@RequestMapping("/getuserinfopage")
	public String getUserInfopage(){
		return "userinfo";
	}
	
	
	@RequestMapping("/getshowuserinfopage")
	public String getUserInfoPage(){
		return "/superadmin/showaccount";
	}

	@RequestMapping("/getshowprojectpage")
	public String getProjectShowpage(){
		return "/showallproject";
	}
	

	@RequestMapping("/getaddprjctpage")
	public String getAddPrjctPage() {
		return "/projectmanagment/add_project";
	}
	
	@RequestMapping("/getaddprojectbutton")
	public String getAddProjectPage() {
		return "/schooladmin/addproject";
	}
}
