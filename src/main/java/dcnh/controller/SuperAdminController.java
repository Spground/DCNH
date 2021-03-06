package dcnh.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import dcnh.global.ProjectManagementInfo;
import dcnh.global.ResponseCode;
import dcnh.global.SessionKey;
import dcnh.global.UserPermission;
import dcnh.handler.AccountManageHandler;
import dcnh.handler.ProjectManageHandler;
import dcnh.model.Project;
import dcnh.model.ResponseMessage;
import dcnh.model.User;
import dcnh.model.UserInfo;
import dcnh.utils.HTMLToWord;

@Controller
@Slf4j
public class SuperAdminController {

	@Autowired
	private AccountManageHandler accountManageHandler;

	@Autowired
	private ProjectManageHandler projectManageHandler;

	@Autowired
	private Environment env;

	@Autowired
	private ProjectManagementInfo pmConfig;

	@RequestMapping("/dcnh/getuserbyschool")
	@ResponseBody
	public List<UserInfo> getUserInfoBySchool(@RequestParam String school) {
		return accountManageHandler.getAllUserInfoBySchool(school);
	}

	/*
	 * 设置系统信息 比如每组有多少个大创项目 每个专家组有多少个专家
	 */
	@RequestMapping("/dcnh/setprojectmanageinfo")
	@ResponseBody
	public ResponseMessage setProjectManagementInfo(@RequestParam int judgeGroupCount) {
		ResponseMessage response = new ResponseMessage();
		if (pmConfig.writeInfo(0, judgeGroupCount)) {
			response.setCode(ResponseCode.SUCCESS.ordinal());
			response.setMessage("设置成功");
		} else {
			response.setCode(ResponseCode.SUCCESS.ordinal());
			response.setMessage("警告, 设置失败，请联系网站管理员。");
		}
		return response;
	}

	/*
	 * 分组
	 */
	@RequestMapping("/dcnh/grouping")
	@ResponseBody
	public ResponseMessage groupingProject() {
		ResponseMessage response = projectManageHandler.assignProjectsToExperts();
		return response;
	}

	/*
	 * 查看所有项目
	 */
	@RequestMapping("/dcnh/getallproject")
	@ResponseBody
	@JsonIgnoreProperties(value={"attachment"})
	public List<Project> getAllProject() {
		return projectManageHandler.getAllProjects();
	}

	/*
	 * 获取导出项目列表
	 */
	@RequestMapping("/dcnh/resultprojectlist")
	@ResponseBody
	public Map<String, List<Project>> getResultProjectList(HttpSession session) {
		User user = (User) session.getAttribute(SessionKey.USERNAME.name());
		if (user != null && user.getPermission().equals(UserPermission.SUPERADMIN)) {
			return projectManageHandler.getResultProjectList(user);
		} else {
			return new HashMap<String, List<Project>>();
		}
	}

	/*
	 * 获取导出项目列表
	 */
	@RequestMapping("/dcnh/sendhtmlcontent")
	@ResponseBody
	public ResponseMessage createDoc(@RequestParam String htmlContent) {
		ResponseMessage response = new ResponseMessage();
		try {
			String rootPath = env.getProperty("rootPath");
			String fileName = System.currentTimeMillis() + ".doc";
			HTMLToWord.createWord(rootPath, fileName, htmlContent);
			response.setCode(ResponseCode.SUCCESS.ordinal());
			response.setMessage(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			response.setCode(ResponseCode.FAILED.ordinal());
			response.setMessage("生成word文档失败");
		}
		return response;
	}

	/*
	 * 下载导出项目文件
	 */
	@RequestMapping(value = "/dcnh/getresultdocument/{filename}")
	public void getDocument(@PathVariable String filename, HttpServletRequest request, HttpServletResponse response) {
		String path = env.getProperty("rootPath");
		log.info("path = " + path);
		try {
			StreamUtils.copy(new FileInputStream(path + filename + ".doc"), response.getOutputStream());
		} catch (IOException e) {
			log.warn("StreamUtils copy warn", e);
		}

		try {
			response.getOutputStream().flush();
		} catch (IOException e) {
			log.warn("getOutputStream flush warn", e);
		}
	}

	@RequestMapping("/dcnh/addnewuser")
	@ResponseBody
	public ResponseMessage addNewAccount(@RequestParam String userName, @RequestParam String password,
			@RequestParam String school, @RequestParam String permission) {
		ResponseMessage response = accountManageHandler.addNewuser(userName, password, school, permission);
		return response;
	}

	@RequestMapping("/dcnh/deleteuser")
	@ResponseBody
	public ResponseMessage deleteAccount(HttpSession session, @RequestParam String userName) {
		return accountManageHandler.deleteAccount(session, userName);
	}

	@RequestMapping("/dcnh/getalluserinfo")
	@ResponseBody
	public List<UserInfo> getAllUserInfo(HttpSession session, @RequestParam String permission) {
		User user = (User) session.getAttribute(SessionKey.USERNAME.name());
		return accountManageHandler.getAllUserInfo(user, permission);
	}

	// 增加校级管理员用户
	@RequestMapping("/dcnh/addschooluser")
	@ResponseBody
	public ResponseMessage addNewSchoolAccount(@RequestParam Map<String, String> userInfoMap) {
		return accountManageHandler.addNewSchooluser(userInfoMap);
	}

	/**
	 * 超级管理员删除项目
	 * 
	 * @param session
	 * @param projectId
	 * @param schoolName
	 * @param mainCategory
	 * @return
	 */
	@RequestMapping("/dcnh/deleteproject")
	@ResponseBody
	public ResponseMessage deleteProjectById(HttpSession session, @RequestParam("projectid") int projectId,
			@RequestParam("school") String schoolName, @RequestParam("maincategory") String mainCategory) {
		ResponseMessage responseMessage = new ResponseMessage();
		User user = null;
		if ((user = (User) session.getAttribute(SessionKey.USERNAME.name())) == null) {
			responseMessage.setCode(ResponseCode.FAILED.ordinal());
			responseMessage.setMessage("未登录");
			return responseMessage;
		} else if (!user.getPermission().equals(UserPermission.SUPERADMIN)) {
			responseMessage.setCode(ResponseCode.FAILED.ordinal());
			responseMessage.setMessage("没有权限");
			return responseMessage;
		} else
			projectManageHandler.deleteProjectById(projectId, schoolName, mainCategory, responseMessage);
		return responseMessage;
	}
}
