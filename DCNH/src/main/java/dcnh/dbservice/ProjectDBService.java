package dcnh.dbservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dcnh.mapper.ProjectDBMapper;
import dcnh.mapper.UserDBMapper;
import dcnh.mode.InnovationProject;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dcnh.mapper.UserProjectDBMapper;

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

	/*
	 * 保存用户分配了哪些项目
	 */
	public boolean saveUserProject(String userName, int categoryId, int projectCount) {

		int result = userProjectMapper.insertUserProject(userName, categoryId, projectCount);
		return result > 0 ? true : false;
	}
}
