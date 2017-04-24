package dcnh.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dcnh.global.ResponseCode;
import dcnh.global.SessionKey;
import dcnh.handler.ProjectManageHandler;
import dcnh.mode.BaseUser;
import dcnh.mode.ResponseMessage;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SchoolAdminController {
	@Autowired
	private ProjectManageHandler projectManageHandler;

	@RequestMapping(value = "/dcnh/addnewproject", method = RequestMethod.POST)
	@ResponseBody
	public ResponseMessage addNewProject(HttpSession session, @RequestParam("category") String mainCategoryName,
			@RequestParam("subcategory") String subcategory, @RequestParam("school") String school,
			@RequestParam("teacher") String teacher, @RequestParam("prjct_title") String projectTitle,
			@RequestParam("participators[]") List<String> participators,
			@RequestParam("attachement_id") String attachementId) {

		// 先新建一个项目
		log.info("###" + mainCategoryName + "###" +subcategory);
		// 保存文件
		
		// 将附件路径写库
		BaseUser user = (BaseUser) session.getAttribute(SessionKey.USERNAME.name());
		ResponseMessage response = projectManageHandler.addNewProject(user, mainCategoryName, subcategory, teacher,
				projectTitle, participators, attachementId);
		return response;
	}
	
	@RequestMapping(value = "/dcnh/schooladmin/deleteproject", method = RequestMethod.POST)
	@ResponseBody
	public ResponseMessage deleteProjectById(HttpSession session, @RequestParam("projectid") int proejctId, @RequestParam("maincategory") String mainCategory, @RequestParam("school") String school) {
		log.info("###删除项目ID" + proejctId + "###" + "项目分类" + mainCategory + "所属学校" + school);
		ResponseMessage response = new ResponseMessage();
		BaseUser user = null;
		if((user = (BaseUser)session.getAttribute(SessionKey.USERNAME.name())) == null) {
			response.setCode(ResponseCode.FAILED.ordinal());
			response.setMessage("未登录，不能执行此操作！");
		} else {
			projectManageHandler.deleteProjectByIdAndSchoolName(proejctId, user.getSchool(), response, user.getUserName(), mainCategory);
		}
			
		return response;
	}
}
