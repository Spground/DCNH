package dcnh.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import dcnh.global.UserPermission;
import lombok.Data;
@JsonIgnoreProperties({"password"})
@Data
public class User {
	private int userId;
	private String userName;
	private String password;
	private String email;
	private UserPermission permission;
	private String schoolName;
	private String phoneNumber;
}
