package dcnh.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dcnh.dbservice.AssignmentDBService;
import dcnh.dbservice.ProjectDBService;
import dcnh.global.ResponseCode;
import dcnh.global.UserPermission;
import dcnh.model.Assignment;
import dcnh.model.Expert;
import dcnh.model.Project;
import dcnh.model.ResponseMessage;
import dcnh.model.User;

@Component
public class AssignmentHandler {

	@Autowired
	private AssignmentDBService assignmentDBService;
	@Autowired
	private ProjectDBService projectDBService;
	
	/**
	 * 给项目打分
	 * @param user
	 * @param score
	 * @param projectId
	 * @return
	 */
	public ResponseMessage markProject(User user, int score, int projectId) {
		ResponseMessage response = new ResponseMessage();
		if (user == null) {
			response.setCode(ResponseCode.FAILED.ordinal());
			response.setMessage("用户未登录");
			return response;
		}
		if (!user.getPermission().equals(UserPermission.EXPERT)) {
			response.setCode(ResponseCode.FAILED.ordinal());
			response.setMessage("非专家用户不能评分");
			return response;
		}
		if (score < 0 || score > 100) {
			response.setCode(ResponseCode.FAILED.ordinal());
			response.setMessage("评分范围错误,评分范围为0至100");
			return response;
		}

		Expert expert = new Expert(user);
		Project project = projectDBService.getProjectById(projectId);
		Assignment assignment = new Assignment();
		assignment.setExpert(expert);
		assignment.setProject(project);
		assignment.setScore(score);
		boolean ok = assignmentDBService.updateAssignment(assignment);
		if(ok) {
			response.setCode(ResponseCode.SUCCESS.ordinal());
			response.setMessage("评价成功");
		} else {
			response.setCode(ResponseCode.FAILED.ordinal());
			response.setMessage("评价失败");
		}
		return response;
	}

}
