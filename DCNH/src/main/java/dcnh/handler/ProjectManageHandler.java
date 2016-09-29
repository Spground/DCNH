package dcnh.handler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dcnh.dbservice.GroupingDBService;
import dcnh.dbservice.ProjectDBService;
import dcnh.dbservice.UserDBService;
import dcnh.globalInfo.ProjectManagementInfo;
import dcnh.globalInfo.ResponseCode;
import dcnh.globalInfo.UserPermission;
import dcnh.mode.InnovationProject;
import dcnh.mode.ResponseMessage;
import dcnh.mode.UserInfo;

@Component
public class ProjectManageHandler {
	
	@Autowired
	private ProjectManagementInfo projectManagementInfo ;
	
	@Autowired
	private UserDBService userDBService;
	
	@Autowired
	private ProjectDBService projectService;
	
	@Autowired
	private GroupingDBService groupingDBService;
	
	public ResponseMessage groupingProject(){
			ResponseMessage response = new ResponseMessage();
			
			//int projectGroupCount = projectManagementInfo.getProjectGroupCount();
			
			//专家分组
			int judgeGroupCount = projectManagementInfo.getJudgeGroupCount();
		
			List<UserInfo> judgeList = userDBService.getAllUserInfo(UserPermission.JUDGE);
			System.out.println("judgeList "+judgeList.size());
			int judgeCount = judgeList.size();
			if(judgeGroupCount<=0 || judgeCount<judgeGroupCount){
				response.setCode(ResponseCode.FAILED.ordinal());
				response.setMessage("专家分组信息错误，请设置每个项目需要几个专家评审");
			}
			
			int groupNum = judgeCount/judgeGroupCount;//分成多少组
			System.out.println("groupNum "+groupNum+" ## judgeGroupCount "+judgeGroupCount);
			ArrayList<List<UserInfo>> judgeGroup = new ArrayList<List<UserInfo>>();
			Iterator<UserInfo> userIterator = judgeList.iterator();
			for(int i=0;i<groupNum;i++){
				List<UserInfo> list = new LinkedList<UserInfo>();
				for(int j=0;j<judgeGroupCount;j++)
					list.add(userIterator.next());
				if(i==(groupNum-1)){
					while(userIterator.hasNext())
						list.add(userIterator.next());
				}
				judgeGroup.add(list);
			}
			
			//项目分组
			ArrayList<List<InnovationProject>> projectGroup = new ArrayList<List<InnovationProject>>();
			List<InnovationProject> allProjectList = projectService.getAllInnovationProject();
			int projectCount = allProjectList.size();
			int projectNumofGroup = projectCount/groupNum;//大创项目每组多少个
			Iterator<InnovationProject> projectIterator = allProjectList.iterator();
			
			for(int i=0;i < groupNum ;i++){
				
				List<InnovationProject> projectList = new LinkedList<InnovationProject>();
				
				for(int j=0;j<projectNumofGroup;j++){
					projectList.add(projectIterator.next());
				}
				if(i==(groupNum-1)){
					while(userIterator.hasNext())
						projectList.add(projectIterator.next());
				}
				projectGroup.add(projectList);
			}
			
			
			//为专家组分配项目
			groupingDBService.clear();
			for(int i=0;i<groupNum;i++){
				groupingDBService.saveGroupInfo(judgeGroup.get(i),i,projectGroup.get(i),i);
			}
			response.setCode(ResponseCode.SUCCESS.ordinal());
			response.setMessage("分配完成");
 			return response;
	}
	
	public List<InnovationProject> getAllProject(){
		return projectService.getAllInnovationProject();
	}
	
	/*public List<InnovationProject>  getAllCategoryProject(String category){
		
	}*/
	
	public void sendAttachement(String attachementId,HttpServletRequest request,HttpServletResponse response){
		
	}
	
	//public ResponseMessage saveAttachement()
}
