package dcnh.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import dcnh.cache.CategoryCache;
import dcnh.cache.SchoolCache;
import dcnh.dbservice.AssignmentDBService;
import dcnh.dbservice.GroupingDBService;
import dcnh.dbservice.ProjectDBService;
import dcnh.dbservice.SchoolProjectCountDBService;
import dcnh.dbservice.UserDBService;
import dcnh.global.ProjectManagementInfo;
import dcnh.global.ResponseCode;
import dcnh.global.UserPermission;
import dcnh.mapper.AttachmentDBMapper;
import dcnh.model.Attachment;
import dcnh.model.Expert;
import dcnh.model.MainCategory;
import dcnh.model.Project;
import dcnh.model.ResponseMessage;
import dcnh.model.SubCategory;
import dcnh.model.User;
import dcnh.model.UserInfo;

@Component
public class ProjectManageHandler {

	@Autowired
	private ProjectManagementInfo pmConfig;

	@Autowired
	private UserDBService userDBService;

	@Autowired
	private ProjectDBService projectDBService;
	
	@Autowired
	private AssignmentDBService assignmentDBService;

	@Autowired
	private SchoolProjectCountDBService schoolProjectCountDBService;

	@Autowired
	private GroupingDBService groupingDBService;

	@Autowired
	private CategoryCache categoryCache;

	@Autowired
	private SchoolCache schoolCache;

	@Autowired
	private AttachmentDBMapper attachementDBMapper;

	public Map<String, List<String>> getAllCategory() {
		Map<String, List<String>> result = new HashMap<>();
		List<MainCategory> mainCategories = categoryCache.getAllMainCategories();
		for (MainCategory mainCategory : mainCategories) {
			String mainCategoryName = mainCategory.getMainCategoryName();
			List<String> subCategoryNames = new ArrayList<>();
			for(SubCategory subCategory : mainCategory.getSubCategories())
				subCategoryNames.add(subCategory.getSubCategoryName());
			result.put(mainCategoryName, subCategoryNames);
		}
		return result;
	}

	/*
	 * 查看某一学校的所有项目
	 */
	public List<Project> getAllProjectsBySchoolName(String schoolName) {
		return projectDBService.getProjectsBySchoolName(schoolName);
	}

	//---------------------- 项目-专家分配策略------------------------------------
	/**
	 * 指派项目给专家，并且满足给定的条件
	 * TODO
	 * @return
	 */
	public ResponseMessage assignProjectsToExperts() {
		ResponseMessage response = new ResponseMessage();

		// 首先清除原有的分组数据
		groupingDBService.clear();
		// int projectGroupCount = projectManagementInfo.getProjectGroupCount();

		// 专家分组
		int judgeGroupCount = pmConfig.getJudgeGroupCount();

		List<UserInfo> judgeList = userDBService.getAllUserInfosByPermission(UserPermission.EXPERT);
		System.out.println("judgeList " + judgeList.size());
		int judgeCount = judgeList.size();
		if (judgeGroupCount <= 0 || judgeCount < judgeGroupCount) {
			response.setCode(ResponseCode.FAILED.ordinal());
			response.setMessage("专家分组信息错误，请设置每个项目需要几个专家评审");
		}

		int groupNum = judgeCount / judgeGroupCount;// 分成多少组
		System.out.println("groupNum " + groupNum + " ## judgeGroupCount " + judgeGroupCount);
		ArrayList<List<UserInfo>> judgeGroup = new ArrayList<List<UserInfo>>();
		Iterator<UserInfo> userIterator = judgeList.iterator();
		for (int i = 0; i < groupNum; i++) {
			List<UserInfo> list = new LinkedList<UserInfo>();
			for (int j = 0; j < judgeGroupCount; j++)
				list.add(userIterator.next());
			if (i == (groupNum - 1)) {
				while (userIterator.hasNext())
					list.add(userIterator.next());
			}
			judgeGroup.add(list);
		}

		// 项目分组
		ArrayList<List<Project>> projectGroup = new ArrayList<List<Project>>();
		List<Project> allProjectList = projectDBService.getAllInnovationProjects();
		int projectCount = allProjectList.size();
		int projectNumofGroup = projectCount / groupNum;// 大创项目每组多少个
		Iterator<Project> projectIterator = allProjectList.iterator();

		for (int i = 0; i < groupNum; i++) {

			List<Project> projectList = new LinkedList<Project>();

			for (int j = 0; j < projectNumofGroup; j++) {
				projectList.add(projectIterator.next());
			}
			if (i == (groupNum - 1)) {
				while (userIterator.hasNext())
					projectList.add(projectIterator.next());
			}
			projectGroup.add(projectList);
		}

		// 为专家组分配项目
		groupingDBService.clear();
		for (int i = 0; i < groupNum; i++) {
			groupingDBService.saveGroupInfo(judgeGroup.get(i), i, projectGroup.get(i), i);
		}
		response.setCode(ResponseCode.SUCCESS.ordinal());
		response.setMessage("分配完成");
		return response;
	}

	public List<Project> getAllProjects() {
		return projectDBService.getAllInnovationProjects();
	}

	public List<Project> getAllAssignedProjectsOfExpert(Expert expert, int finished) {
		if (finished == 0)
			return assignmentDBService.getAllUnFinishedAssignedProjectsOfExpert(expert);
		if (finished == 1)
			return assignmentDBService.getAllFinishedAssignedProjectsOfExpert(expert);
		return assignmentDBService.getAllFinishedAssignedProjectsOfExpert(expert);
	}

	public void sendAttachement(String attachementId, HttpServletRequest request, HttpServletResponse response) {

	}

	/**
	 * 增加一个新的项目
	 * 
	 * @param user
	 * @param mainCategoryName
	 * @param subCategoryName
	 * @param teacher
	 * @param projectTitle
	 * @param participators
	 * @param attachementId
	 * @return
	 */
	public ResponseMessage addNewProject(User user, String mainCategoryName, String subCategoryName, String teacher,
			String projectTitle, List<String> participators, String attachementId) {

		ResponseMessage response = new ResponseMessage();
		Project project = new Project();
		System.out.println("##################  ResponseMessage addNewProject");

		// 检查信息是否正确
		if (!checkInfo(user, mainCategoryName, subCategoryName, teacher, response))
			return response;

		Attachment attachement = attachementDBMapper.getAttachmentById(attachementId);
		project.setAttachment(attachement);
		project.setMainCategory(mainCategoryName);
		project.setSchoolName(user.getSchoolName());
		project.setSubCategory(subCategoryName);
		project.setTitle(projectTitle);
		project.setTeacher(teacher);
		project.setUploader(user.getUserId());
		project.setParticipators(participators);

		int mainCategoryId = categoryCache.getMainCategoryIdByMainCategoryName(mainCategoryName);
		int schoolId = schoolCache.querySchoolIdBySchoolName(user.getSchoolName());
		// 将该用户已经上传的项目更新
		int uploadedCount = schoolProjectCountDBService.getUploadedProjectCount(schoolId, mainCategoryId);
		int limitUploadCount = schoolProjectCountDBService.getLimitProjectCount(schoolId, mainCategoryId);

		// 如果项目个数已经达到上限，就不允许再创建了
		if (uploadedCount + 1 > limitUploadCount) {
			response.setCode(ResponseCode.FAILED.ordinal());
			response.setMessage("新建项目失败,已上传的该类别项目个数已达上限。");
			return response;
		}
		System.out.println("####newUploadcount" + uploadedCount + "####");

		// 创建项目
		if (projectDBService.createNewProject(project) != 0) {
			// 将上传数量更新
			schoolProjectCountDBService.updateUploadedCount(schoolId, mainCategoryId, uploadedCount + 1);
			response.setCode(ResponseCode.SUCCESS.ordinal());
			response.setMessage(String.format("新建项目成功，您已经上传项目%d个，剩余%d个。", uploadedCount + 1,
					limitUploadCount - (uploadedCount + 1)));
		} else {
			response.setCode(ResponseCode.FAILED.ordinal());
			response.setMessage("新建项目失败，请重新尝试。");
		}

		return response;
	}

	/**
	 * 验证新项目的信息是否完备
	 * 
	 * @param user
	 * @param mainCategoryName
	 * @param subCategoryName
	 * @param attachementId
	 * @param response
	 * @return
	 */
	protected boolean checkInfo(User user, String mainCategoryName, String subCategoryName, String attachementId,
			ResponseMessage response) {

		System.out.println("##################  checkInfo(BaseUser user,");

		if (!categoryCache.check(mainCategoryName, subCategoryName)) {
			response.setCode(ResponseCode.FAILED.ordinal());
			response.setMessage("类别信息设置错误");
			return false;
		}

		System.out.println("##################  checkInfo(BaseUser user,*****");

		int mainCategoryId = categoryCache.getMainCategoryIdByMainCategoryName(mainCategoryName);
		System.out.println("categoryId" + mainCategoryId);
		int schoolId = schoolCache.querySchoolIdBySchoolName(user.getSchoolName());

		int limitProjectCount = schoolProjectCountDBService.getLimitProjectCount(schoolId, mainCategoryId);
		if (limitProjectCount <= 0) {
			response.setCode(ResponseCode.FAILED.ordinal());
			response.setMessage("未被分配该类型项目的名额");
			return false;
		}

		int projectUploadCount = schoolProjectCountDBService.getUploadedProjectCount(schoolId, mainCategoryId);
		if (limitProjectCount <= projectUploadCount) {
			response.setCode(ResponseCode.FAILED.ordinal());
			response.setMessage("上传失败 已达到最大数量限制");
			return false;
		}
		return true;

	}

	public ResponseMessage setResultInfo(Map<String, Integer> setInfoMap) {
		ResponseMessage response = new ResponseMessage();
		Set<String> keySet = setInfoMap.keySet();
		for (String category : keySet) {
			String value = "" + setInfoMap.get(category);
			int proportion = Integer.valueOf(value);
			// System.out.println(count);
			if (proportion < 0 || proportion > 100) {
				response.setCode(ResponseCode.FAILED.ordinal());
				response.setMessage("设定比例有误，设置的百分比只能是0至100");
				return response;
			}
		}

		for (String category : keySet) {
			String value = "" + setInfoMap.get(category);
			int proportion = Integer.valueOf(value);
			categoryCache.updateMainCategoryProportion(category, proportion);
		}
		response.setCode(ResponseCode.SUCCESS.ordinal());
		response.setMessage("设置成功");
		return response;
	}

	public Map<String, List<Project>> getResultProjectList(User user) {

		List<String> mainCategoryList = categoryCache.getAllMainCategoryNames();
		HashMap<String, List<Project>> resultMap = new HashMap<String, List<Project>>();
		for (String category : mainCategoryList) {
			int sum = projectDBService.getProjectCountCategory(category);
			MainCategory mainCategory = categoryCache.getMainCategoryByMainCategoryName(category);
			int projectCount = sum * mainCategory.getProportion() / 100;
			List<Project> projectList = projectDBService.getProjectList(category, projectCount);
			resultMap.put(category, projectList);
		}
		return resultMap;
	}

	/**
	 * 通过项目ID和学校名称删除某个学校名下的项目
	 * 
	 * @param projectId
	 * @return
	 */
	public void deleteProjectByIdAndSchoolName(int projectId, String schoolName, ResponseMessage responseMessage,
			String userName, String mainCategory) {

		int count = projectDBService.deleteProjectById(projectId);
		int schoolId = schoolCache.querySchoolIdBySchoolName(schoolName);
		if (count == 0) {
			responseMessage.setCode(ResponseCode.FAILED.ordinal());
			responseMessage.setMessage("删除失败，请检查是否有权限！");
		} else {
			responseMessage.setCode(ResponseCode.SUCCESS.ordinal());
			responseMessage.setMessage("删除项目成功， 项目ID为！" + projectId);
			// 更新user-project表,将已经上传的数量减一
			schoolProjectCountDBService.updateUploadedCount(schoolId,
					categoryCache.getMainCategoryIdByMainCategoryName(mainCategory),
					schoolProjectCountDBService.getUploadedProjectCount(schoolId,
							categoryCache.getMainCategoryIdByMainCategoryName(mainCategory)) - 1);
		}
	}

	/**
	 * 通过项目ID删除某个项目
	 * 
	 * @param projectId
	 * @return
	 */
	public void deleteProjectById(int projectId, String schoolName, String mainCategory,
			ResponseMessage responseMessage) {
		System.out.println("=====deleteProjectById() " + schoolName + mainCategory + "=====");
		int schoolId = schoolCache.querySchoolIdBySchoolName(schoolName);
		int count = projectDBService.deleteProjectById(projectId);
		if (count == 0) {
			responseMessage.setCode(ResponseCode.FAILED.ordinal());
			responseMessage.setMessage("删除失败，请检查是否有权限！");
		} else {
			responseMessage.setCode(ResponseCode.SUCCESS.ordinal());
			responseMessage.setMessage("删除项目成功， 项目ID为！" + projectId);
			// 更新school_project_count表,将已经上传的数量减一
			// TODO
			schoolProjectCountDBService.updateUploadedCount(schoolId,
					categoryCache.getMainCategoryIdByMainCategoryName(mainCategory),
					schoolProjectCountDBService.getUploadedProjectCount(schoolId,
							categoryCache.getMainCategoryIdByMainCategoryName(mainCategory)) - 1);
		}
	}
}
