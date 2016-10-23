package dcnh.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dcnh.globalInfo.ResponseCode;
import dcnh.handler.ProjectManageHandler;
import dcnh.mode.ResponseMessage;

/*
 * 设置大创项目种类，大类和小类
 */
@Controller
public class SystemManageController {
	
	@Autowired
	private ProjectManageHandler projectManageHandler;
	/*
	  * 设置导出文件的导出比例
	  * 即 导出前百分之多少的项目
	  */
	 @RequestMapping("/resultsettinginfo")
	 @ResponseBody
	 public ResponseMessage setResultInfo(@RequestParam Map<String,Integer> setInfoMap){
		// System.out.println("########********");
		// HashMap<String,Integer> map = new HashMap<String,Integer>();
		//	map.putAll(setInfoMap);
		
		 return projectManageHandler.setResultInfo(setInfoMap);
	 }
}
