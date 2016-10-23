package dcnh.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dcnh.dbservice.JudgeDBServer;
import dcnh.globalInfo.ResponseCode;
import dcnh.globalInfo.UserPermission;
import dcnh.mode.BaseUser;
import dcnh.mode.ResponseMessage;

@Component
public class JudgementHandler {
	
	@Autowired
	private JudgeDBServer judgeDBServer;
	
	public ResponseMessage addNewJudgement(BaseUser user,int score,int projectId){
		ResponseMessage response = new ResponseMessage();
		if(user==null){
			response.setCode(ResponseCode.FAILED.ordinal());
			response.setMessage("用户未登录");
			return response;
		}
		if(!user.getPermission().equals(UserPermission.JUDGE)){
			response.setCode(ResponseCode.FAILED.ordinal());
			response.setMessage("非专家用户不能评分");
			return response;
		}
		if(score<0 || score>100){
			response.setCode(ResponseCode.FAILED.ordinal());
			response.setMessage("评分范围错误,评分范围为0至100");
			return response;
		}
		judgeDBServer.addJudgement(user.getUserName(), projectId, score);
		response.setCode(ResponseCode.SUCCESS.ordinal());
		response.setMessage("评价成功");
		return response;
	}
	
}
