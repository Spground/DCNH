package dcnh.mode;

import dcnh.globalInfo.UserPermission;
import lombok.Data;

@Data
public class BaseUser {
	private String userName;
	private String password;
	private String email;
	private UserPermission permission;
	private String school;
	private String phoneNumber;
}
