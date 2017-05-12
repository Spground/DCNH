package dcnh.dbservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dcnh.mapper.ProjectDBMapper;
import dcnh.model.Project;

@Service
public class ProjectDBService {

	@Autowired
	private ProjectDBMapper projectDBMapper;

	public int createNewProject(Project project) {
		return projectDBMapper.addProject(project);
	}

	public Project queryProjectByID(int projectId) {
		return projectDBMapper.getInnovationProjectByProjectId(projectId);
	}

	/*
	 * 获取所有的大创项目
	 */
	public List<Project> getAllInnovationProjects() {
		List<Project> projectList = projectDBMapper.getAllInnovationProjectsOrderBySchoolName();
		return projectList;
	}

	/*
	 * 获取指定学校的所有创新项目
	 */
	public List<Project> getProjectsBySchoolName(String schoolName) {
		Map<String, Object> where = new HashMap<>();
		where.put("schoolName", schoolName);
		return projectDBMapper.getAllInnovationProjects(where);
	}
	
	/*
	 * 获取某一类项目评分靠前的多少项
	 */
	public List<Project> getProjectList(String category, int projectCount) {
		return projectDBMapper.getTopInnovationProjectsByMainCategory(category, projectCount);
	}

	/*
	 * 获取某一类项目的总数
	 */
	public int getProjectCountCategory(String category) {
		return projectDBMapper.getInnovationProjectsCountByMainCategory(category);
	}

	/**
	 * 根据项目ID删除项目
	 * 
	 * @param projectId
	 * @return
	 */
	public int deleteProjectById(int projectId) {
		return projectDBMapper.deleteInnovationProjectByProjectId(projectId);
	}

	public Project getProjectById(int projectId) {
		return projectDBMapper.getInnovationProjectByProjectId(projectId);
	}
}
