package dcnh.model;

import lombok.Data;

/**
 * @author WuJie
 * @date 2017年5月10日下午5:00:26
 * @version 1.0
 **/
@Data
public class BaseUserInfo {
	private int userId;
	private String userName;

	public BaseUserInfo() {

	}

	public BaseUserInfo(User user) {
		this.userId = user.getUserId();
		this.userName = user.getUserName();
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.userId;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
}
