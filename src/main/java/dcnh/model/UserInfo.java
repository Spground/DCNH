package dcnh.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author WuJie
 * @date 2017年5月3日下午9:37:32
 * @version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class UserInfo extends BaseUserInfo {
	private int permission;
	private String permissionName;
	private String email;
	private String schoolName;
	private String phoneNumber;

	public UserInfo() {
		super();
	}

	public UserInfo(User user) {
		super(user);
		this.permission = user.getPermission().getCode();
		this.permissionName = user.getPermission().getName();
		this.email = user.getEmail();
		this.schoolName = user.getSchoolName();
		this.phoneNumber = user.getPhoneNumber();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append(
				String.format("UserInfo(userId=%s, userName=%s, email=%s, schoolName=%s, phoneNumer=%s, permission=%s)",
						getUserId(), getUserName(), getEmail(), getSchoolName(), getPhoneNumber(), getPermission()));
		return sb.toString();
	}
}
