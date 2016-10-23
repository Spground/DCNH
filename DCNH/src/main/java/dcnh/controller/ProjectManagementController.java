package dcnh.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import dcnh.globalInfo.ProjectManagementInfo;
import dcnh.globalInfo.ResponseCode;
import dcnh.globalInfo.SessionKey;
import dcnh.globalInfo.UserPermission;
import dcnh.handler.AttachementHandler;
import dcnh.handler.ProjectManageHandler;
import dcnh.mode.BaseUser;
import dcnh.mode.InnovationProject;
import dcnh.mode.ResponseMessage;
import dcnh.myutil.HTMLToWord;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ProjectManagementController {

	@Autowired
	private AttachementHandler attachmentHandler;

	@Autowired
	private ProjectManagementInfo projectManagementInfo;

	@Autowired
	private ProjectManageHandler projectManageHandler;
	
	@Autowired
	private AttachementHandler attachementHandler;

	@Autowired
	private Environment env;
	
	@RequestMapping(value = "/addnewproject", method = RequestMethod.POST)
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

	@RequestMapping("/getcategory")
	@ResponseBody
	public Map<String, List<String>> getCategory() {
		Map<String, List<String>> result = projectManageHandler.getAllCategory();
		
		return result;
	}

	/*
	 * 设置系统信息 比如每组有多少个大创项目 每个专家组有多少个专家
	 */
	@RequestMapping("/setprojectmanageinfo")
	@ResponseBody
	public ResponseMessage setProjectManagementInfo(@RequestParam int judgeGroupCount) {
		ResponseMessage response = new ResponseMessage();
		response.setCode(ResponseCode.SUCCESS.ordinal());
		response.setMessage("设置成功");
		projectManagementInfo.writeInfo(0, judgeGroupCount);
		return response;
	}

	@RequestMapping("/grouping")
	@ResponseBody
	public ResponseMessage groupingProject() {
		ResponseMessage response = projectManageHandler.groupingProject();
		return response;
	}

	/*
	 * @RequestMapping("/getallcategoryproject")
	 * 
	 * @ResponseBody public List<InnovationProject>
	 * getAllCategoryProject(@RequestParam String category){
	 * 
	 * }
	 */

	@RequestMapping("/getallproject")
	@ResponseBody
	public List<InnovationProject> getAllProject() {
		return projectManageHandler.getAllProject();
	}

	@RequestMapping("/uploadattachement")
	@ResponseBody
	public ResponseMessage getAttachement(@RequestParam("attachement_file") MultipartFile file) throws IOException {
		//System.out.println("##### fileName ### " + file.getName() + " original filename is " + 	file.getOriginalFilename() + file.getSize());
		ResponseMessage response = new ResponseMessage();
		String fileName = file.getOriginalFilename();
		String id = attachementHandler.uploadFile(file, fileName);
		response.setMessage(id);
		return response;
	}
	
	/*
	 * 下载附件
	 */
	 @RequestMapping(value="/getattachement/{attachementId}")
	 public void getAttachement(@PathVariable String attachementId,HttpServletRequest request,HttpServletResponse response){
	/*	String path = env.getProperty("rootPath");
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
		}*/
		 attachmentHandler.getFile(request,response,attachementId);
	 }
	 
	 /*
	  * 获取该用户可以访问的大创项目
	  */
	 @RequestMapping("/getprojectlist")
	 @ResponseBody
	 public List<InnovationProject> getProjectList(@RequestParam int kind,HttpSession session){
		 BaseUser user = (BaseUser) session.getAttribute(SessionKey.USERNAME.name());
		 if(user == null){
			 System.out.println(" user is null");
			 return new LinkedList<InnovationProject>();
		 }
		 if(user.getPermission().equals(UserPermission.SCHOOLADMIN))
			 return projectManageHandler.getAllProject(user.getSchool());
		 if(user.getPermission().equals(UserPermission.JUDGE)){
			 System.out.println(" user is judge ");
			 return projectManageHandler.getAllProjectOfJudge(user.getUserName(), kind);
		 }
		 if(user.getPermission().equals(UserPermission.SUPERADMIN)){
			 return projectManageHandler.getAllProject();
		 }
		 return new LinkedList<InnovationProject>();
	 }
	 
	 /*
	  * 获取导出项目列表
	  */
	 @RequestMapping("/resultprojectlist")
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
	 @RequestMapping("/sendhtmlcontent")
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
	 @RequestMapping(value="/getresultdocument/{filename}")
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
}
