package dcnh.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dcnh.globalInfo.ProjectManagementInfo;
import dcnh.globalInfo.ResponseCode;
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
	
	@RequestMapping("/setprojectmanageinfo")
	@ResponseBody
	public ResponseMessage setProjectManagementInfo(@RequestParam int projectGroupCount,@RequestParam int judgeGroupCount){
		ResponseMessage response  = new ResponseMessage();
		response.setCode(ResponseCode.SUCCESS.ordinal());
		response.setMessage("设置成功");
		projectManagementInfo.writeInfo(projectGroupCount, judgeGroupCount);
		return response;
	}
	
	
}
