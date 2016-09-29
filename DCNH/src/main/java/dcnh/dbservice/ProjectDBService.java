package dcnh.dbservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dcnh.mapper.ProjectDBMapper;
import dcnh.mapper.UserDBMapper;
import dcnh.mode.InnovationProject;
import java.util.List;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dcnh.mapper.ProjectDBMapper;
import dcnh.mapper.UserProjectDBMapper;
import dcnh.mode.InnovationProject;

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

	@Autowired
	private UserProjectDBMapper userProjectMapper;

	@Autowired
	private ProjectDBMapper projectMapper;
	/*
	 * 保存用户分配了哪些项目
	 */
	public boolean saveUserProject(String userName, int categoryId, int projectCount) {

		int result = userProjectMapper.insertUserProject(userName, categoryId, projectCount);
		return result > 0 ? true : false;
	}
	
	public List<InnovationProject> getAllInnovationProject(){
		List<InnovationProject> projectList = projectMapper.getAllInnovationProject();
		return projectList;
	}
}
