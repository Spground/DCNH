package dcnh.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import dcnh.globalInfo.ProjectManagementInfo;
import dcnh.globalInfo.ResponseCode;
import dcnh.handler.ProjectManageHandler;
import dcnh.mode.InnovationProject;
import dcnh.mode.ResponseMessage;


/*
 * 设置系统信息
 * 比如每组有多少个大创项目
 * 每个专家组有多少个专家
 */
@Controller
public class ProjectManagementController {

	@Autowired
	private ProjectManagementInfo projectManagementInfo;
	
	@Autowired
	private ProjectManageHandler projectManageHandler;
	
	@RequestMapping("/setprojectmanageinfo")
	@ResponseBody
	public ResponseMessage setProjectManagementInfo(@RequestParam int judgeGroupCount){
		ResponseMessage response  = new ResponseMessage();
		response.setCode(ResponseCode.SUCCESS.ordinal());
		response.setMessage("设置成功");
		projectManagementInfo.writeInfo(0, judgeGroupCount);
		return response;
	}
	
	@RequestMapping("/grouping")
	@ResponseBody
	public ResponseMessage groupingProject(){
		ResponseMessage response = projectManageHandler.groupingProject();
		return response;
	}
	
	/*
	@RequestMapping("/getallcategoryproject")
	@ResponseBody
	public List<InnovationProject> getAllCategoryProject(@RequestParam String category){
		
	}
	*/

	@RequestMapping("/getallproject")
	@ResponseBody
	public List<InnovationProject> getAllProject(){
		return projectManageHandler.getAllProject();
	}
	

	@RequestMapping("/uploadattachement")
	@ResponseBody
	public ResponseMessage getAttachement(@RequestParam("attachement_file") MultipartFile file){
		System.out.println("##### fileName ### "+file.getName());
		ResponseMessage response = new ResponseMessage();
		response.setCode(ResponseCode.SUCCESS.ordinal());
		response.setMessage("呵呵呵呵");
		return response;
		
	}
	
	 @RequestMapping(value="/getattachement/{attachementId}")
	 public void getAttachement(@PathVariable String attachementId,HttpServletRequest request,HttpServletResponse response){
		 
	 }
	
	
}
