package dcnh.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dcnh.globalInfo.SessionKey;
import dcnh.handler.ProjectManageHandler;
import dcnh.mode.BaseUser;
import dcnh.mode.ResponseMessage;

@Controller
@Slf4j
public class SchoolAdminController {
	@Autowired
	private ProjectManageHandler projectManageHandler;
	
	 @RequestMapping(value = "/dcnh/addnewproject", method = RequestMethod.POST)
		@ResponseBody
		public ResponseMessage addNewProject(HttpSession session, @RequestParam("category") String mainCategoryName,
				@RequestParam("subcategory") String subcategory, @RequestParam("school") String school,
				@RequestParam("teacher") String teacher,@RequestParam("prjct_title") String projectTitle, 
				@RequestParam("participators[]") List<String> participators,@RequestParam("attachement_id")String attachementId ) {

			// 先新建一个项目

			// 保存文件

			// 将附件路径写库
			BaseUser user = (BaseUser) session.getAttribute(SessionKey.USERNAME.name());
			ResponseMessage response = projectManageHandler.addNewProject(user, mainCategoryName, subcategory, teacher, projectTitle, participators, attachementId);

			return response;
		}
	
}
