package dcnh.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dcnh.global.SessionKey;
import dcnh.handler.AssignmentHandler;
import dcnh.model.ResponseMessage;
import dcnh.model.User;

@Controller
public class JudgementController {

	@Autowired
	private AssignmentHandler judgementhandler;

	@RequestMapping("/dcnh/addjudgement")
	@ResponseBody
	public ResponseMessage addJudgement(HttpSession session, @RequestParam int score, @RequestParam int projectId) {
		User user = (User) session.getAttribute(SessionKey.USERNAME.name());

		return judgementhandler.markProject(user, score, projectId);
	}
}
