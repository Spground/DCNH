package dcnh.dbservice;

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
	private UserProjectDBMapper userProjectMapper;
	
	@Autowired
	private ProjectDBMapper projectMapper;
	
	/*
	 * 保存用户分配了哪些项目
	 */
	public boolean saveUserProject(String userName,int categoryId,int projectCount){
		
		int result = userProjectMapper.insertUserProject(userName, categoryId, projectCount);
		return result>0?true:false;
	}
	
	public List<InnovationProject> getAllInnovationProject(){
		List<InnovationProject> projectList = projectMapper.getAllInnovationProject();
		return projectList;
	}
}
