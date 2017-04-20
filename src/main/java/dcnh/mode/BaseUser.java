package dcnh.mode;

import dcnh.global.UserPermission;
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
