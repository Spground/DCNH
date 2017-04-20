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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dcnh.globalInfo.ProjectManagementInfo;
import dcnh.globalInfo.ResponseCode;
import dcnh.globalInfo.SessionKey;
import dcnh.globalInfo.UserPermission;
import dcnh.handler.AccountManageHandler;
import dcnh.handler.ProjectManageHandler;
import dcnh.mode.BaseUser;
import dcnh.mode.InnovationProject;
import dcnh.mode.ResponseMessage;
import dcnh.mode.UserInfo;
import dcnh.myutil.HTMLToWord;

@Controller
@Slf4j
public class SuperAdminController {
	
	 @Autowired
	 private AccountManageHandler accountManageHandler;
	

	@Autowired
	private ProjectManagementInfo projectManagementInfo;

	@Autowired
	private ProjectManageHandler projectManageHandler;
	

	@Autowired
	private Environment env;
	 
	@RequestMapping("/dcnh/getuserbyschool")
	@ResponseBody
	public List<UserInfo> getUserInfoBySchool(@RequestParam String school){
		return  accountManageHandler.getAllUserInfoBySchool(school);
	}
	
	/*
	 * 设置系统信息 比如每组有多少个大创项目 每个专家组有多少个专家
	 */
	@RequestMapping("/dcnh/setprojectmanageinfo")
	@ResponseBody
	public ResponseMessage setProjectManagementInfo(@RequestParam int judgeGroupCount) {
		ResponseMessage response = new ResponseMessage();
		response.setCode(ResponseCode.SUCCESS.ordinal());
		response.setMessage("设置成功");
		projectManagementInfo.writeInfo(0, judgeGroupCount);
		return response;
	}

	/*
	 * 分组
	 */
	@RequestMapping("/dcnh/grouping")
	@ResponseBody
	public ResponseMessage groupingProject() {
		ResponseMessage response = projectManageHandler.groupingProject();
		return response;
	}
	
	/*
	 * 查看所有项目
	 */
	@RequestMapping("/dcnh/getallproject")
	@ResponseBody
	public List<InnovationProject> getAllProject() {
		return projectManageHandler.getAllProject();
	}
	
	 /*
	  * 获取导出项目列表
	  */
	 @RequestMapping("/dcnh/resultprojectlist")
	 @ResponseBody
	 public Map<String,List<InnovationProject>> getResultProjectList(HttpSession session){
		 BaseUser user = (BaseUser) session.getAttribute(SessionKey.USERNAME.name());
		 if(user!=null && user.getPermission().equals(UserPermission.SUPERADMIN)){
			 return projectManageHandler.getResultProjectList(user);
		 }
		 else{
			return new HashMap<String,List<InnovationProject>>();
		 }
	 }
	 
	 /*
	  * 获取导出项目列表
	  */
	 @RequestMapping("/dcnh/sendhtmlcontent")
	 @ResponseBody
	 public ResponseMessage  createDoc(@RequestParam String htmlContent){
		 ResponseMessage response = new ResponseMessage();
		try {
			String rootPath = env.getProperty("rootPath");
			String fileName = System.currentTimeMillis()+".doc";
			HTMLToWord.createWord(rootPath,fileName,htmlContent);
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
	 @RequestMapping(value="/dcnh/getresultdocument/{filename}")
	 public void getDocument(@PathVariable String filename,HttpServletRequest request,HttpServletResponse response){
		 	String path = env.getProperty("rootPath");
	    	log.info("path = " + path);
	    	try {
				StreamUtils.copy( new FileInputStream(path + filename+".doc") , response.getOutputStream() );
			} catch (IOException e) {
				log.warn("StreamUtils copy warn",e );
			}
	    	
	    	try {
				response.getOutputStream().flush();
			} catch (IOException e) {
				log.warn("getOutputStream flush warn",e );
			}
	 }
	 
	 @RequestMapping("/dcnh/addnewuser")
		@ResponseBody
		public ResponseMessage addNewAccount(@RequestParam String userName,@RequestParam String password,
				@RequestParam  String school,@RequestParam String  permission){
			ResponseMessage response  = accountManageHandler.addNewuser(userName,password,school,permission);
			return response;
		}
		
		@RequestMapping("/dcnh/deleteuser")
		@ResponseBody
		public ResponseMessage deleteAccount(HttpSession session,@RequestParam String userName){
			return accountManageHandler.deleteAccount(session, userName);
		}
		
		@RequestMapping("/dcnh/getalluserinfo")
		@ResponseBody
		public List<UserInfo> getAllUserInfo(HttpSession session,@RequestParam String permission){
			BaseUser user = (BaseUser) session.getAttribute(SessionKey.USERNAME.name());
			return accountManageHandler.getAllUserInfo(user,permission);
		}
		
		@RequestMapping("/dcnh/addschooluser")
		@ResponseBody
		public ResponseMessage addNewSchoolAccount(@RequestParam Map<String,String> userInfoMap){
			return accountManageHandler.addNewSchooluser(userInfoMap);
		}
	 
}
