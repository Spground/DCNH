package dcnh.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import dcnh.cache.CategoryCache;
import dcnh.handler.ProjectManageHandler;
import dcnh.mode.ResponseMessage;

/*
 * 设置大创项目种类，大类和小类
 */
@Controller
public class SystemManageController {

	@Autowired
	private ProjectManageHandler projectManageHandler;

	@Autowired
	private CategoryCache categoryCache;

	/*
	 * 设置导出文件的导出比例 即 导出前百分之多少的项目
	 */
	@RequestMapping("/dcnh/resultsettinginfo")
	@ResponseBody
	public ResponseMessage setResultInfo(@RequestParam Map<String, Integer> setInfoMap) {
		return projectManageHandler.setResultInfo(setInfoMap);
	}

	/*
	 * 获取大创项目大类类别
	 */
	@RequestMapping("/dcnh/getallmaincategory")
	@ResponseBody
	public List<String> getAllMainCategory() {
		return categoryCache.getAllMainCategoryNames();
	}
}
