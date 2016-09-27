package dcnh.dbservice;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dcnh.mapper.UserProjectDBMapper;


@Data
@Service
public class ProjectDBService {
	@Autowired
	private UserProjectDBMapper userProjectMapper;
	
	/*
	 * 保存用户分配了哪些项目
	 */
	public boolean saveUserProject(String userName,int categoryId,int projectCount){
		
		int result = userProjectMapper.insertUserProject(userName, categoryId, projectCount);
		return result>0?true:false;
	}
}
