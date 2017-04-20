package dcnh.handler;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import dcnh.dbservice.UserDBService;
import dcnh.global.Href;
import dcnh.global.ResponseCode;
import dcnh.global.SessionKey;
import dcnh.global.UserPermission;
import dcnh.mode.BaseUser;
import dcnh.mode.ResponseMessage;
import dcnh.myutil.SHA256;

@Component
public class LoginHandler {
	
	@Autowired
	private UserDBService userDBService;
	
	public ResponseMessage tryLogin(HttpSession session,String userName,String password){
		ResponseMessage responseMessage = new ResponseMessage();
		BaseUser user = userDBService.getUserByUserName(userName);
		
		if(user ==null){
			responseMessage.setCode(ResponseCode.FAILED.ordinal());
			responseMessage.setMessage("该用户不存在");
			return responseMessage;
		}
		String userPassword = user.getPassword();
		if(userPassword.equals(SHA256.encode(password))){
			responseMessage.setCode(ResponseCode.SUCCESS.ordinal());
			String href = getHref(user.getPermission());
			responseMessage.setHref(href);
			responseMessage.setMessage("登录成功");
			session.setAttribute(SessionKey.USERNAME.name(), user);
		}
		else{
			responseMessage.setCode(ResponseCode.FAILED.ordinal());
			responseMessage.setMessage("登录失败，密码错误");
		}
		return responseMessage;
	}
	
	public static String getHref(UserPermission permission){
		if(permission == null) return "/";
		switch(permission){
			case SUPERADMIN:{
				return Href.SUPER_ADMIN;
			}
			case SCHOOLADMIN:{
				return Href.SCHOOL_ADMIN;
			}
			case JUDGE:{
				return Href.JUDGE;
			}
			default:
				return "/";
		}
	}
}
