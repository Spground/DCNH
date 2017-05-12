package dcnh.templatecontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JudgeTemplateController {

	@RequestMapping("/dcnh/judge")
	public String getJudgepage() {
		return "dcnh/judge/judge";
	}

	@RequestMapping("/dcnh/gradeprojectpage")
	public String getgradeprojectpage() {
		return "dcnh/judge/gradedprojectpage";
	}

	@RequestMapping("/dcnh/notgradedproject")
	public String getnotgradeproject() {
		return "dcnh/judge/notgradedproject";
	}

	@RequestMapping("/dcnh/judgeshowproject")
	public String getjudgeshowproject() {
		return "dcnh/judge/judgeshowproject";
	}
}
