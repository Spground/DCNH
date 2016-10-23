package dcnh.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dcnh.globalInfo.SessionKey;
import dcnh.handler.JudgementHandler;
import dcnh.mode.BaseUser;
import dcnh.mode.ResponseMessage;

@Controller
public class JudgementController {

	@Autowired
	private JudgementHandler judgementhandler;
	
	@RequestMapping("/addjudgement")
	@ResponseBody
	public ResponseMessage addJudgement(HttpSession session,@RequestParam int score,@RequestParam int projectId){
		BaseUser user = (BaseUser) session.getAttribute(SessionKey.USERNAME.name());
		
		return judgementhandler.addNewJudgement(user, score, projectId);
	}
}
