package dcnh.dbservice;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dcnh.mapper.UserDBMapper;
import dcnh.mode.BaseUser;

@Data
@Service
public class UserDBService {
	@Autowired
	private UserDBMapper userDBMapper;
	
	public BaseUser getUserByUserName(String userName){
		return userDBMapper.getUserByUserName(userName);
	}
	
	public int addNewUser(BaseUser user){
		return userDBMapper.addNewUser(user);
	}
	
	
}
