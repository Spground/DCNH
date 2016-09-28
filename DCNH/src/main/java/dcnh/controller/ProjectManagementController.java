package dcnh.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import dcnh.handler.AttachementHandler;
import dcnh.handler.LoginHandler;
import dcnh.mode.ResponseMessage;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ProjectManagementController {
	@Autowired
	private ProjectManagementController projectManagementController;
	@Autowired
	private AttachementHandler attachmentHandler;
	@RequestMapping(value="/addnewproject", method=RequestMethod.POST)
	@ResponseBody
	public ResponseMessage addNewProject(HttpSession session,
			@RequestParam String category,
			@RequestParam String subcategory,
			@RequestParam String school,
			@RequestParam String teacher,
			@RequestParam String prjct_title,
			@RequestParam String[] participators,
			@RequestParam MultipartFile attachement_file 
			) {
		
		System.err.println("category " + category 
				+  " participators size is : " 
				+ participators.length + "subcategory " + subcategory + "");
		//先新建一个项目
		
		
		//保存文件
		
		//将附件路径写库
		
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
	
}
