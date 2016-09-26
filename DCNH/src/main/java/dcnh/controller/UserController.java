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
import dcnh.myutil.SHA256;
/*
 * 账户操作相关
 */
@Controller
public class UserController {
	
	@Autowired
	private LoginHandler loginHandler;
	
	@Autowired
	private AccountManageHandler accountManageHandler;
	
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
	public ResponseMessage addNewAccount(@RequestParam String userName,@RequestParam String password,
			@RequestParam  String school,@RequestParam String  permission){
		ResponseMessage response  = accountManageHandler.addNewuser(userName,password,school,permission);
		return response;
	}
	
	@RequestMapping("/deleteuser")
	@ResponseBody
	public ResponseMessage deleteAccount(HttpSession session,@RequestParam String userName){
		return accountManageHandler.deleteAccount(session, userName);
	}
	
	@RequestMapping("/getalluserinfo")
	@ResponseBody
	public List<UserInfo> getAllUserInfo(HttpSession session,@RequestParam String permission){
		BaseUser user = (BaseUser) session.getAttribute(SessionKey.USERNAME.name());
		return accountManageHandler.getAllUserInfo(user,permission);
	}
	
	@RequestMapping("/addschoolUser")
	@ResponseBody
	public ResponseMessage addNewAccount(@RequestParam String userName,@RequestParam String password,
			@RequestParam  String school,@RequestParam String  permission,
			@RequestParam int paper,@RequestParam int project,@RequestParam int startup,
			@RequestParam int creative){
		
	}
	
	
	
}
