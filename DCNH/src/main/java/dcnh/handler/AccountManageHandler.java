package dcnh.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dcnh.cache.SchoolCache;
import dcnh.dbservice.UserDBService;
import dcnh.globalInfo.ResponseCode;
import dcnh.globalInfo.UserPermission;
import dcnh.mode.BaseUser;
import dcnh.mode.ResponseMessage;
import dcnh.myutil.SHA256;

/*
 * 各种类型账户的增删改查
 */
@Component
public class AccountManageHandler {
	
	@Autowired
	private UserDBService userDBService;
	
	@Autowired
	private SchoolCache schoolCache;
	
	public ResponseMessage addNewuser(String userName,String passowrd,String school,String permission){
		ResponseMessage response = new ResponseMessage();
		BaseUser user = new BaseUser();
		user.setPassword(SHA256.encode(passowrd));
		user.setSchool(school);
		user.setUserName(userName);
		
		
		if(!checkUserInfo(user,permission,response)){
			return response;
		}
		UserPermission userPermission = UserPermission.stringToEnum(permission);
		user.setPermission(userPermission);
		int result = userDBService.addNewUser(user);
		
		if(result>0)
		{
			response.setCode(ResponseCode.SUCCESS.ordinal());
			response.setMessage("添加成功");
		}
		else{
			response.setCode(ResponseCode.SUCCESS.ordinal());
			response.setMessage("添加失败");
		}
		return response;
	}
	
	private boolean checkUserInfo(BaseUser user,String permission,ResponseMessage response){
		
		//检查用户权限设置
		UserPermission userPermission =	UserPermission.stringToEnum(permission);
		if(userPermission == null){
			response.setCode(ResponseCode.FAILED.ordinal());
			response.setMessage("用户权限设置错误");
			return false;
		}
		
		if(user.getSchool()==null
				||user.getSchool().isEmpty()
				||!schoolCache.containsSchool(user.getSchool())){
			response.setCode(ResponseCode.FAILED.ordinal());
			response.setMessage("学校名称设置错误");
			return false;
		}
		return true;
	}
}
