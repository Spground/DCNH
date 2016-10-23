package dcnh.pagecontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JudgepageController {

	@RequestMapping("/judge")
	public String getJudgepage(){
		return "/judge/judge";
	}
	
	@RequestMapping("/gradeprojectpage")
	public String getgradeprojectpage(){
		return "/judge/gradedprojectpage";
	}
	
	@RequestMapping("/notgradedproject")
	public String getnotgradeproject(){
		return "/judge/notgradedproject";
	}
	
	@RequestMapping("/judgeshowproject")
	public String getjudgeshowproject(){
		return "/judge/judgeshowproject";
	}
}
