package dcnh.dbservice;

import java.util.List;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dcnh.globalInfo.UserPermission;
import dcnh.mapper.UserDBMapper;
import dcnh.mode.BaseUser;
import dcnh.mode.UserInfo;

@Data
@Service
public class UserDBService {
	@Autowired
	private UserDBMapper userDBMapper;
	
	public BaseUser getUserByUserName(String userName){
		return userDBMapper.getUserByUserName(userName);
	}
	
	public int addNewUser(BaseUser user){
		return userDBMapper.addNewUser(user,user.getPermission().getCode());
	}
	
	public int deleteUser(String userName){
		return userDBMapper.deleteUser(userName);
	}
	
	public List<UserInfo> getAllUserInfo(UserPermission permission){
		List<UserInfo> userList = userDBMapper.getAllUserInfo(permission.getCode());
		return userList;
	}
}
