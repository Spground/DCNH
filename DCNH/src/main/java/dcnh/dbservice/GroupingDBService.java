package dcnh.dbservice;

import java.util.List;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dcnh.mapper.JudgeGroupDBMapper;
import dcnh.mapper.ProjectDBMapper;
import dcnh.mapper.ProjectGroupDBMapper;
import dcnh.mapper.ProjectJudgeDBMapper;
import dcnh.mapper.UserProjectDBMapper;
import dcnh.mode.InnovationProject;
import dcnh.mode.UserInfo;


@Data
@Service
public class GroupingDBService {
	
	@Autowired
	private ProjectGroupDBMapper projectGroupDBMapper;
	
	@Autowired
	private JudgeGroupDBMapper judgeGroupDBMapper;
	
	@Autowired
	private ProjectJudgeDBMapper projectJudgeDBMapper;
	
	@Transactional
	public void saveGroupInfo(List<UserInfo> userList,int judgeGroupId,List<InnovationProject> projectList,int projectGroupId){
		saveJudgeGroupInfo(userList,judgeGroupId);
		saveProjectGroupInfo(projectList,projectGroupId);
		projectJudgeDBMapper.insertProjectJudge(projectGroupId, judgeGroupId);
	}
	
	public void saveJudgeGroupInfo(List<UserInfo> userList,int groupId){
		for(UserInfo user:userList)
			judgeGroupDBMapper.insertJudgeGroup(user.getUserName(), groupId);
	}
	
	public void saveProjectGroupInfo(List<InnovationProject> projectList,int groupId){
		for(InnovationProject project:projectList)
			projectGroupDBMapper.insertProjectGroup(project.getProjectId(), groupId);
	}
	
	public void clear(){
		projectGroupDBMapper.truncate();
		judgeGroupDBMapper.truncateJudgeGroupTable();
		projectJudgeDBMapper.truncateProjectJudgeTable();
		
	}
}
