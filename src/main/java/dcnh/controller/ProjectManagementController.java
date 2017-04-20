package dcnh.controller;

import java.io.IOException;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import dcnh.globalInfo.ProjectManagementInfo;

import dcnh.globalInfo.SessionKey;
import dcnh.globalInfo.UserPermission;
import dcnh.handler.AttachementHandler;
import dcnh.handler.ProjectManageHandler;
import dcnh.mode.BaseUser;
import dcnh.mode.InnovationProject;
import dcnh.mode.ResponseMessage;

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
	
	

	@RequestMapping("/dcnh/getcategory")
	@ResponseBody
	public Map<String, List<String>> getCategory() {
		Map<String, List<String>> result = projectManageHandler.getAllCategory();
		
		return result;
	}

	

	/*
	 * @RequestMapping("/getallcategoryproject")
	 * 
	 * @ResponseBody public List<InnovationProject>
	 * getAllCategoryProject(@RequestParam String category){
	 * 
	 * }
	 */
/*
	@RequestMapping("/dcnh/getallproject")
	@ResponseBody
	public List<InnovationProject> getAllProject() {
		return projectManageHandler.getAllProject();
	}*/

	@RequestMapping("/dcnh/uploadattachement")
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
	 @RequestMapping(value="/dcnh/getattachement/{attachementId}")
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
	 @RequestMapping("/dcnh/getprojectlist")
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
	 
	
}
