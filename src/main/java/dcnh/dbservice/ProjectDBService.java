package dcnh.dbservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dcnh.mapper.ProjectDBMapper;
import dcnh.mapper.ProjectGroupDBMapper;
import dcnh.mapper.ProjectJudgeDBMapper;
import dcnh.mode.InnovationProject;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import lombok.Data;
import dcnh.mapper.UserProjectDBMapper;

@Data
@Service
public class ProjectDBService {

	@Autowired
	private ProjectDBMapper projectDBMapper;

	@Autowired
	private UserProjectDBMapper userProjectDBMapper;

	@Autowired
	private ProjectJudgeDBMapper projectJudgeDBMapper;

	@Autowired
	private ProjectGroupDBMapper projectGroupDBMapper;

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

	/*
	 * 获取所有的大创项目
	 */
	public List<InnovationProject> getAllInnovationProject() {
		List<InnovationProject> projectList = projectMapper.getAllInnovationProject();
		return projectList;
	}

	/*
	 * 获取用户已经上传的项目的个数
	 */
	public int getUploadProjectCount(String userName, int categoryId) {
		try {
			return userProjectDBMapper.getUserUploadProject(userName, categoryId);
		} catch (Exception e) {
			return 0;
		}

	}

	/*
	 * 获取该类别给用户分配的项目的个数
	 */
	public int getProjectCount(String userName, int categoryId) {
		try {
			return userProjectDBMapper.getUserProject(userName, categoryId);
		} catch (Exception e) {
			return 0;
		}
	}

	/*
	 * 更新用户上传项目数
	 */
	public int updateUploadCount(String userName, int categoryId, int newCount) {
		try {
			return userProjectDBMapper.updateUploadCount(newCount, userName, categoryId);
		} catch (Exception e) {
			return 0;
		}
	}

	/*
	 * 获取指定学校的所有创新项目
	 */
	public List<InnovationProject> getProjectList(String school) {
		return projectDBMapper.getAllInnovationProjectByschool(school);
	}

	/*
	 * 获取给某位专家分配的项目列表
	 * 
	 * kind == 0 表示所有项目 kind == 1 表示已评分项目 kind == 2 表示未评分项目
	 */
	public List<InnovationProject> getProjectListOfJudge(String userName, int kind) {
		List<InnovationProject> list = new LinkedList<InnovationProject>();
		Integer judgeGroupId = userProjectDBMapper.getGroupIdofJudge(userName);
		if (judgeGroupId == null) {
			System.out.println("judgeGroupId is null");
		}
		if (judgeGroupId == null)
			return list;
		Integer projectGroupId = projectJudgeDBMapper.getProjectGroupId(judgeGroupId);
		if (kind == 0) {
			System.out.println("projectGroupId " + projectGroupId);
			list = projectGroupDBMapper.getProjectGroupListById(projectGroupId);
		} else if (kind == 1) {
			list = projectGroupDBMapper.getGradedProject(userName);
		} else if (kind == 2) {
			List<Integer> allProjectIdList = projectGroupDBMapper.getAllProjectId(projectGroupId);
			List<Integer> allGradedProjectIdList = projectGroupDBMapper.getAllGradedProjectId(userName);
			HashSet<Integer> allGradedProjectIdSet = new HashSet<Integer>();
			allGradedProjectIdSet.addAll(allGradedProjectIdList);
			for (Integer id : allProjectIdList) {
				if (!allGradedProjectIdSet.contains(id)) {
					list.add(projectDBMapper.getInnovationProject(id));
				}
			}
			return list;
		}
		return list;
	}

	/*
	 * 获取某一类项目评分靠前的多少项
	 */
	public List<InnovationProject> getProjectList(String category, int projectCount) {
		return projectDBMapper.getProjectListByCategory(category, projectCount);
	}

	/*
	 * 获取某一类项目的总数
	 */
	public int getProjectCountCategory(String category) {
		return projectDBMapper.getProjectCount(category);
	}
	
	/**
	 * 根据项目ID和学校名称删除项目
	 * @param projectId
	 * @return
	 */
	public int deleteProjectByIdAndSchoolName(int projectId, String schoolName) {
		return projectDBMapper.deleteInnovationProjectByProjectIDAndSchoolName(projectId, schoolName);
	}
	
	/**
	 * 根据项目ID删除项目
	 * @param projectId
	 * @return
	 */
	public int deleteProjectById(int projectId) {
		return projectDBMapper.deleteInnovationProjectByProjectID(projectId);
	}
}
