package dcnh.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dcnh.dbservice.UserDBService;
import dcnh.globalInfo.ResponseCode;
import dcnh.mode.BaseUser;
import dcnh.mode.ResponseMessage;

@Component
public class RegisterHandler {
	@Autowired
	private UserDBService userDBService;
	
	
	public ResponseMessage addNewuser(BaseUser user){
		int result = userDBService.addNewUser(user);
		ResponseMessage response = new ResponseMessage();
		
		if(result>0)
		{
			response.setCode(ResponseCode.SUCCESS.ordinal());
			response.setMessage("添加成功");
		}
		else{
			response.setCode(ResponseCode.SUCCESS.ordinal());
			response.setMessage("添加失败");
		}
		return response;
	}
}
