package dcnh.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import dcnh.globalInfo.ProjectManagementInfo;
import dcnh.globalInfo.ResponseCode;
import dcnh.handler.AttachementHandler;
import dcnh.handler.ProjectManageHandler;
import dcnh.mode.InnovationProject;
import dcnh.mode.ResponseMessage;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ProjectManagementController {
	@Autowired
	private ProjectManagementController projectManagementController;
	@Autowired
	private AttachementHandler attachmentHandler;

	@Autowired
	private ProjectManagementInfo projectManagementInfo;

	@Autowired
	private ProjectManageHandler projectManageHandler;

	@RequestMapping(value = "/addnewproject", method = RequestMethod.POST)
	@ResponseBody
	public ResponseMessage addNewProject(HttpSession session, @RequestParam("category") String category,
			@RequestParam("subcategory") String subcategory, @RequestParam("school") String school,
			@RequestParam("teacher") String teacher,@RequestParam("prjct_title") String prjct_title, 
			@RequestParam("participators[]") List<String> participators,@RequestParam("attachement_id")String attachementId ) {

		System.err.println("category " + category + " participators size is : " + participators.size() + "subcategory "
				+ subcategory + "");
		System.err.println("category " + category + " \nparticipators is : " + participators + "\nsubcategory "
				+ subcategory + "\nschool is " + school + "\n" + "teacher" + teacher + "\nprjct_title" + prjct_title
				+ "\nattachementId " + attachementId) ;
		// 先新建一个项目

		// 保存文件

		// 将附件路径写库

		return null;
	}

	@RequestMapping("/getcategory")
	@ResponseBody
	public Map<String, List<String>> getCategory() {
		Map<String, List<String>> result = new HashMap<>();
		List<String> list1 = new ArrayList<>();
		list1.add("理工");
		list1.add("文学");
		list1.add("管理");

		List<String> list2 = new ArrayList<>();
		list2.add("理工A");
		list2.add("文学B");
		list2.add("管理C");

		result.put("创新", list1);
		result.put("论文", list2);
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
	public ResponseMessage getAttachement(@RequestParam("attachement_file") MultipartFile file) {
		System.out.println("##### fileName ### " + file.getName() + " original filename is " + 	file.getOriginalFilename() + file.getSize());
		ResponseMessage response = new ResponseMessage();
		response.setCode(ResponseCode.SUCCESS.ordinal());
		response.setMessage("呵呵呵呵");
		return response;
	}
	
	 @RequestMapping(value="/getattachement/{attachementId}")
	 public void getAttachement(@PathVariable String attachementId,HttpServletRequest request,HttpServletResponse response){
		 
	 }
	

}
