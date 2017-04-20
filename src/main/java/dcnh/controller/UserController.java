package dcnh.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dcnh.globalInfo.SessionKey;
import dcnh.handler.AccountManageHandler;
import dcnh.handler.LoginHandler;
import dcnh.mode.BaseUser;
import dcnh.mode.ResponseMessage;
import dcnh.mode.UserInfo;
/*
 * 账户操作相关
 */
@Controller
public class UserController {
	
	@Autowired
	private LoginHandler loginHandler;
	
	@Autowired
	private AccountManageHandler accountManageHandler;
	
	@RequestMapping("/dcnh/login")
	@ResponseBody
	public ResponseMessage login(HttpSession session,@RequestParam String userName,@RequestParam String password){
		return loginHandler.tryLogin(session,userName, password);
	}
	
	@RequestMapping("/dcnh/getuserinfo")
	@ResponseBody
	public UserInfo getUserInfo(HttpSession session){
		BaseUser user = (BaseUser) session.getAttribute(SessionKey.USERNAME.name());
		UserInfo userinfo = new UserInfo(user);
		return userinfo;
	}
	
	

}