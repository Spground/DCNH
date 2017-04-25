package dcnh.pagecontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * 获取html页面
 */
@Controller
public class SuperAdminPageController {

	@RequestMapping("/dcnh/superadmin")
	public String getSuperAdminPage() {
		return "dcnh/superadmin/superadmin";
	}

	@RequestMapping("/dcnh/getaccountmanagepage")
	public String getAccountManagePage() {
		return "dcnh/superadmin/accountManage";
	}

	@RequestMapping("/dcnh/getcreateaccountpage")
	public String getcreateAccount() {
		return "dcnh/superadmin/createaccount";
	}

	@RequestMapping("/dcnh/getprojectmanagepage")
	public String getProjectManagePage() {
		return "dcnh/superadmin/projectManage";
	}

	@RequestMapping("/dcnh/resultsetting")
	public String getResultsetting() {
		return "dcnh/superadmin/resultsetting";
	}

	@RequestMapping("/dcnh/resultpage")
	public String getResult() {
		return "dcnh/superadmin/result";
	}

}
