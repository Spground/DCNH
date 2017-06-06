package dcnh.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author WuJie
 * @date 2017年5月10日下午4:22:54
 * @version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class Expert extends BaseUserInfo {
	private String email;
	private String schoolName;
	private String phoneNumber;

	public Expert() {

	}

	public Expert(User user) {
		super(user);
		this.email = user.getEmail();
		this.schoolName = user.getSchoolName();
		this.phoneNumber = user.getPhoneNumber();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("Expert(userId=%s, userName=%s, email=%s, schoolName=%s, phoneNumer=%s", getUserId(),
				getUserName(), getEmail(), getSchoolName(), getPhoneNumber()));
		return sb.toString();
	}
}
