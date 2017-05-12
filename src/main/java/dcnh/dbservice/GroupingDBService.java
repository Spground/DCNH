package dcnh.dbservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dcnh.mapper.ProjectExpertAssignmentDBMapper;
import dcnh.model.Project;
import dcnh.model.UserInfo;

@Service
public class GroupingDBService {

	@Autowired
	private ProjectExpertAssignmentDBMapper judgementDBMapper;

	@Transactional
	public void saveGroupInfo(List<UserInfo> userList, int judgeGroupId, List<Project> projectList,
			int projectGroupId) {
		saveJudgeGroupInfo(userList, judgeGroupId);
		saveProjectGroupInfo(projectList, projectGroupId);
		// projectJudgeDBMapper.insertProjectJudge(projectGroupId,
		// judgeGroupId);
	}

	public void saveJudgeGroupInfo(List<UserInfo> userList, int groupId) {
		// for (UserInfoBO user : userList)
		// judgeGroupDBMapper.insertJudgeGroup(user.getUserName(), groupId);
	}

	public void saveProjectGroupInfo(List<Project> projectList, int groupId) {
		// for (ProjectDO project : projectList)
		// projectGroupDBMapper.insertProjectGroup(project.getProjectId(),
		// groupId);
	}

	public void clear() {
		// projectGroupDBMapper.truncate();
		// judgeGroupDBMapper.truncateJudgeGroupTable();
		// projectJudgeDBMapper.truncateProjectJudgeTable();
		judgementDBMapper.truncateProjectExpertAssignmentTable();
	}
}
