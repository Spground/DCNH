package dcnh.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dcnh.globalInfo.SessionKey;
import dcnh.handler.LoginHandler;
import dcnh.mode.BaseUser;
import dcnh.mode.ResponseMessage;
import dcnh.mode.UserInfo;

@Controller
public class UserController {
	
	@Autowired
	private LoginHandler loginHandler;
	
	@RequestMapping("/login")
	@ResponseBody
	public ResponseMessage login(HttpSession session,@RequestParam String userName,@RequestParam String password){
		return loginHandler.tryLogin(session,userName, password);
	}
	
	@RequestMapping("/getuserinfo")
	@ResponseBody
	public UserInfo getUserInfo(HttpSession session){
		BaseUser user = (BaseUser) session.getAttribute(SessionKey.USERNAME.name());
		UserInfo userinfo = new UserInfo(user);
		return userinfo;
	}
	
	@RequestMapping("/addnewuser")
	@ResponseBody
	public ResponseMessage addNewAccount(@RequestParam BaseUser user){
		ResponseMessage response  = new ResponseMessage();
		return response;
	}
	
	
}
