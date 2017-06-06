package dcnh.handler;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dcnh.cache.CategoryCache;
import dcnh.cache.SchoolCache;
import dcnh.dbservice.SchoolProjectCountDBService;
import dcnh.dbservice.UserDBService;
import dcnh.global.ResponseCode;
import dcnh.global.SessionKey;
import dcnh.global.UserPermission;
import dcnh.model.ResponseMessage;
import dcnh.model.User;
import dcnh.model.UserInfo;
import dcnh.utils.SHA256;

/*
 * 各种类型账户的增删改查
 */
@Component
public class AccountManageHandler {

	@Autowired
	private UserDBService userDBService;

	@Autowired
	private SchoolCache schoolCache;

	@Autowired
	private CategoryCache categoryCache;
	
	@Autowired
	private SchoolProjectCountDBService schoolProjectCountDBService;

	public ResponseMessage addNewuser(String userName, String passowrd, String schoolName, String permission) {
		ResponseMessage response = new ResponseMessage();
		User user = new User();
		user.setPassword(SHA256.encode(passowrd));
		user.setSchoolName(schoolName);
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
	 * TODO 等有时间必须重写,应该作为一个事务，呵呵
	 */
	public ResponseMessage addNewSchooluser(Map<String, String> userInfoMap) {
		String userName = userInfoMap.get("userName");
		String password = userInfoMap.get("password");
		String schoolName = userInfoMap.get("school");
		String permission = userInfoMap.get("permission");
		int schoolId = schoolCache.querySchoolIdBySchoolName(schoolName);

		int paperCount = Integer.parseInt(userInfoMap.get("学术论文").trim());
		int showProjectCount = Integer.parseInt(userInfoMap.get("展示项目").trim());
		int chuangyeCount = Integer.parseInt(userInfoMap.get("创业直通车项目").trim());
		int chuangyiCount = Integer.parseInt(userInfoMap.get("创意作品").trim());
		System.out.println(userInfoMap);
		// 先创建用户
		ResponseMessage response = addNewuser(userName, password, schoolName, permission);
		// 更新用户-项目表
		if (response.getCode() == ResponseCode.SUCCESS.ordinal()) {
			if (paperCount > 0) {
				int maincategoryId = categoryCache.getMainCategoryIdByMainCategoryName("学术论文");
				schoolProjectCountDBService.saveSchoolProjectCountInfo(schoolId, maincategoryId, paperCount);
			}

			if (showProjectCount > 0) {
				int maincategoryId = categoryCache.getMainCategoryIdByMainCategoryName("展示项目");
				schoolProjectCountDBService.saveSchoolProjectCountInfo(schoolId, maincategoryId, showProjectCount);
			}

			if (chuangyeCount > 0) {
				int maincategoryId = categoryCache.getMainCategoryIdByMainCategoryName("创业直通车项目");
				schoolProjectCountDBService.saveSchoolProjectCountInfo(schoolId, maincategoryId, chuangyeCount);
			}

			if (chuangyiCount > 0) {
				int maincategoryId = categoryCache.getMainCategoryIdByMainCategoryName("创意作品");
				schoolProjectCountDBService.saveSchoolProjectCountInfo(schoolId, maincategoryId, chuangyiCount);
			}
		}
		return response;
	}

	public ResponseMessage deleteAccount(HttpSession session, String userName) {
		ResponseMessage responseMessage = new ResponseMessage();
		User user = (User) session.getAttribute(SessionKey.USERNAME.name());
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

	public List<UserInfo> getAllUserInfo(User user, String permission) {
		List<UserInfo> userList = new LinkedList<UserInfo>();
		if (user == null || !user.getPermission().equals(UserPermission.SUPERADMIN)) {
			return userList;
		}
		UserPermission userPermission = UserPermission.stringToEnum(permission);
		if (userPermission == null)
			return userList;
		userList = userDBService.getAllUserInfosByPermission(userPermission);
		return userList;
	}

	public List<UserInfo> getAllUserInfoBySchool(String schoolName) {
		return userDBService.getUserInfosBySchool(schoolName);
	}

	private boolean checkUserInfo(User user, String permission, ResponseMessage response) {

		// 检查用户权限设置
		UserPermission userPermission = UserPermission.stringToEnum(permission);
		if (userPermission == null) {
			response.setCode(ResponseCode.FAILED.ordinal());
			response.setMessage("用户权限设置错误");
			return false;
		}

		if (userPermission.equals(UserPermission.SCHOOLADMIN)) {
			if (user.getSchoolName() == null || user.getSchoolName().isEmpty()
					|| !schoolCache.containsSchool(user.getSchoolName())) {
				response.setCode(ResponseCode.FAILED.ordinal());
				response.setMessage("学校名称设置错误");
				return false;
			}
		}
		return true;
	}
}
