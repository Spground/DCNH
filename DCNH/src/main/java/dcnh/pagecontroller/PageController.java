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
	
	
	@RequestMapping("/getshowuserinfopage")
	public String getUserInfoPage(){
		return "/superadmin/showaccount";
	}
	
	
}
