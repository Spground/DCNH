package dcnh.handler;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dcnh.cache.CategoryCache;
import dcnh.cache.SchoolCache;
import dcnh.dbservice.ProjectDBService;
import dcnh.dbservice.UserDBService;
import dcnh.global.ResponseCode;
import dcnh.global.SessionKey;
import dcnh.global.UserPermission;
import dcnh.mode.BaseUser;
import dcnh.mode.ResponseMessage;
import dcnh.mode.UserInfo;
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

	@SuppressWarnings("unused")
	@Autowired
	private CategoryCache categoryCache;

	@SuppressWarnings("unused")
	@Autowired
	private ProjectDBService projectDBService;

	public ResponseMessage addNewuser(String userName, String passowrd, String school, String permission) {
		ResponseMessage response = new ResponseMessage();
		BaseUser user = new BaseUser();
		user.setPassword(SHA256.encode(passowrd));
		user.setSchool(school);
		user.setUserName(userName);

		if (!checkUserInfo(user, permission, response)) {
			return response;
		}
		UserPermission userPermission = UserPermission.stringToEnum(permission);
		user.setPermission(userPermission);
		int result = userDBService.addNewUser(user);

		if (result > 0) {
			response.setCode(ResponseCode.SUCCESS.ordinal());
			response.setMessage("添加成功");
		} else {
			response.setCode(ResponseCode.FAILED.ordinal());
			response.setMessage("添加失败,用户名已存在");
		}
		return response;
	}

	/*
	 * 等有时间必须重写,应该作为一个事务，呵呵
	 */
	public ResponseMessage addNewSchooluser(Map<String, String> userInfoMap) {
		String userName = userInfoMap.get("userName");
		String password = userInfoMap.get("password");
		String school = userInfoMap.get("school");
		String permission = userInfoMap.get("permission");
		ResponseMessage response = addNewuser(userName, password, school, permission);
		/*
		 * if(response.getCode() == ResponseCode.SUCCESS.ordinal()){
		 * if(paper>0){ int categoryId = categoryCache.getIdByName("学术论文");
		 * projectDBService.saveUserProject(userName, categoryId, paper); }
		 * 
		 * if(project > 0){ int categoryId = categoryCache.getIdByName("展示项目");
		 * projectDBService.saveUserProject(userName, categoryId, paper); }
		 * 
		 * if(startup>0){ int categoryId = categoryCache.getIdByName("创业直通车项目");
		 * projectDBService.saveUserProject(userName, categoryId, paper); }
		 * if(creative>0){ int categoryId = categoryCache.getIdByName("创意作品");
		 * projectDBService.saveUserProject(userName, categoryId, paper); } }
		 */
		return response;

	}

	public ResponseMessage deleteAccount(HttpSession session, String userName) {
		ResponseMessage responseMessage = new ResponseMessage();
		BaseUser user = (BaseUser) session.getAttribute(SessionKey.USERNAME.name());
		if (!user.getPermission().equals(UserPermission.SUPERADMIN)) {
			responseMessage.setCode(ResponseCode.FAILED.ordinal());
			responseMessage.setMessage("无权删除用户");
			return responseMessage;
		}
		if (user != null && user.getUserName().equals(userName)) {
			responseMessage.setCode(ResponseCode.FAILED.ordinal());
			responseMessage.setMessage("不能删除当前登录用户");
			return responseMessage;
		}

		int result = userDBService.deleteUser(userName);
		if (result > 0) {
			responseMessage.setCode(ResponseCode.SUCCESS.ordinal());
			responseMessage.setMessage("删除用户成功");
			return responseMessage;
		} else {
			responseMessage.setCode(ResponseCode.FAILED.ordinal());
			responseMessage.setMessage("删除用户失败");
			return responseMessage;
		}
	}

	public List<UserInfo> getAllUserInfo(BaseUser user, String permission) {
		List<UserInfo> userList = new LinkedList<UserInfo>();
		if (user == null || !user.getPermission().equals(UserPermission.SUPERADMIN)) {
			return userList;
		}
		UserPermission userPermission = UserPermission.stringToEnum(permission);
		if (userPermission == null)
			return userList;
		userList = userDBService.getAllUserInfo(userPermission);
		return userList;
	}

	public List<UserInfo> getAllUserInfoBySchool(String schoolName) {
		return userDBService.getUserBySchool(schoolName);
	}

	private boolean checkUserInfo(BaseUser user, String permission, ResponseMessage response) {

		// 检查用户权限设置
		UserPermission userPermission = UserPermission.stringToEnum(permission);
		if (userPermission == null) {
			response.setCode(ResponseCode.FAILED.ordinal());
			response.setMessage("用户权限设置错误");
			return false;
		}

		if (userPermission.equals(UserPermission.SCHOOLADMIN)) {
			if (user.getSchool() == null || user.getSchool().isEmpty()
					|| !schoolCache.containsSchool(user.getSchool())) {
				response.setCode(ResponseCode.FAILED.ordinal());
				response.setMessage("学校名称设置错误");
				return false;
			}
		}
		return true;
	}
}
