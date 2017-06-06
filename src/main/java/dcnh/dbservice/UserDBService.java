package dcnh.dbservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dcnh.global.UserPermission;
import dcnh.mapper.UserDBMapper;
import dcnh.model.User;
import dcnh.model.UserInfo;

@Service
public class UserDBService {

	@Autowired
	private UserDBMapper userDBMapper;

	public User getUserByUserName(String userName) {
		return userDBMapper.getUserByUserName(userName);
	}

	public List<UserInfo> getUserInfosBySchool(String schoolName) {
		List<UserInfo> userInfos = new ArrayList<>();
		List<User> users = userDBMapper.getAllUserInfoBySchoolName(schoolName);
		for(User user : users) {
			UserInfo u = new UserInfo(user);
			userInfos.add(u);
		}
		return userInfos;
	}

	public int addNewUser(User user) {
		try {
			return userDBMapper.addNewUser(user);
		} catch (Exception e) {
			return -1;
		}
	}

	public int deleteUser(String userName) {
		return userDBMapper.deleteUser(userName);
	}

	public List<UserInfo> getAllUserInfosByPermission(UserPermission permission) {
		List<UserInfo> userInfos = new ArrayList<>();
		List<User> users = userDBMapper.getAllUserInfoByPermission(permission.getCode());
		for(User user : users) {
			UserInfo u = new UserInfo(user);
			userInfos.add(u);
		}
		return userInfos;
	}
}
