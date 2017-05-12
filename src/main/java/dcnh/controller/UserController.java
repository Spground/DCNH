package dcnh.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dcnh.global.SessionKey;
import dcnh.handler.LoginHandler;
import dcnh.model.ResponseMessage;
import dcnh.model.User;
import dcnh.model.UserInfo;

/*
 * 账户相关
 */
@Controller
public class UserController {

	@Autowired
	private LoginHandler loginHandler;

	@RequestMapping("/dcnh/login")
	@ResponseBody
	public ResponseMessage login(HttpSession session, @RequestParam String userName, @RequestParam String password) {
		return loginHandler.tryLogin(session, userName, password);
	}

	@RequestMapping("/dcnh/getuserinfo")
	@ResponseBody
	public UserInfo getUserInfo(HttpSession session) {
		User user = (User) session.getAttribute(SessionKey.USERNAME.name());
		UserInfo userinfo = new UserInfo(user);
		return userinfo;
	}

	/**
	 * 注销
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/dcnh/logout")
	public String logout(HttpSession session) {
		if (session.getAttribute(SessionKey.USERNAME.name()) != null)
			session.removeAttribute(SessionKey.USERNAME.name());
		return "dcnh/login";
	}
}
