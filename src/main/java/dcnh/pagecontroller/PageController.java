package dcnh.pagecontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * 获取html页面
 */
@Controller
public class PageController {

	@RequestMapping("/dcnh")
	public String getLoginPage() {
		return "dcnh/login";
	}

	@RequestMapping("/dcnh/schooladmin")
	public String getSchoolAdminPage() {
		return "dcnh/schooladmin/schooladmin";
	}

	@RequestMapping("/dcnh/getuserinfopage")
	public String getUserInfopage() {
		return "dcnh/userinfo";
	}

	@RequestMapping("/dcnh/getshowuserinfopage")
	public String getUserInfoPage() {
		return "dcnh/superadmin/showaccount";
	}

	@RequestMapping("/dcnh/getshowprojectpage")
	public String getProjectShowpage() {
		return "dcnh/showallproject";
	}

	@RequestMapping("/dcnh/getaddprjctpage")
	public String getAddPrjctPage() {
		return "dcnh/projectmanagment/add_project";
	}

	@RequestMapping("/dcnh/getaddprojectbutton")
	public String getAddProjectPage() {
		return "dcnh/schooladmin/addproject";
	}
}
