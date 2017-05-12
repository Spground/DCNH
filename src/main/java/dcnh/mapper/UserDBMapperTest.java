package dcnh.mapper;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import dcnh.ApplicationContext;
import dcnh.global.UserPermission;
import dcnh.model.Expert;
import dcnh.model.User;
import dcnh.model.UserInfo;

/**
 * @author WuJie
 * @date 2017年5月3日下午3:24:51
 * @version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationContext.class)
public class UserDBMapperTest {

	@Autowired
	UserDBMapper userDBMapper;

	@Test
	public void testGetUserByUserName() {
		User user = userDBMapper.getUserByUserName("admin");
		assert (user != null);
		System.out.println(user);
	}

	@Test
	public void testAddNewUser() {
		User user = new User();
		user.setEmail("test@qq.com");
		user.setUserName("test");
		user.setPassword("test");
		user.setSchoolName("大连理工大学");
		user.setPermission(UserPermission.EXPERT);
		user.setPhoneNumber("111");
		int r = userDBMapper.addNewUser(user);
		assert (r == 1);
	}

	@Test
	public void testDeleteUser() {
		int r = userDBMapper.deleteUser("test");
		assert (r == 1);
	}

	@Test
	public void testGetAllUserInfoByPermission() {
		List<UserInfo> userInfos = new ArrayList<>();
		List<User> users = userDBMapper.getAllUserInfoByPermission(2);
		for(User user : users) {
			UserInfo u = new UserInfo(user);
			userInfos.add(u);
		}
		assertNotEquals(userInfos, null);
		System.out.println(userInfos);
	}

	@Test
	public void testGetAllUserInfoBySchoolName() {
		List<UserInfo> userInfos = new ArrayList<>();
		List<User> users = userDBMapper.getAllUserInfoBySchoolName("大连理工大学");
		for(User user : users) {
			UserInfo u = new UserInfo(user);
			userInfos.add(u);
		}
		assert (userInfos != null);
		System.out.println(userInfos);
	}

	@Test
	public void testGetExpertByExpertId() {
		Expert expert = userDBMapper.getExpertByExpertId(1);
		assert (expert != null);
		System.err.println(expert);
	}
}
