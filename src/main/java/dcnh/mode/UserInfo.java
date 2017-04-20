package dcnh.mode;

import lombok.Data;

@Data
public class UserInfo {
	private String userName="";
	private String email="";
	private String phoneNumber="";
	private String school="";
	
	public UserInfo(){
		
	}
	
	public UserInfo(BaseUser user){
		if(user!=null){
			this.email = user.getEmail();
			this.school = user.getSchool();
			this.userName = user.getUserName();
		}
	}
}
