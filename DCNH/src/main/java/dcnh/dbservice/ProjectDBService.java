package dcnh.dbservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dcnh.mapper.ProjectDBMapper;
import dcnh.mapper.UserDBMapper;
import dcnh.mode.InnovationProject;
import lombok.Data;

@Data
@Service
public class ProjectDBService {

	@Autowired
	private ProjectDBMapper projectDBMapper;
	
	public int createNewProject(InnovationProject project) {
		return projectDBMapper.addNewProject(project);
	}
	
	public InnovationProject queryProjectByID(int projectId) {
		return projectDBMapper.getInnovationProjectByProjectID(projectId);
	}
}
