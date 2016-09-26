package dcnh.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserProjectDBMapper {

	@Insert("")
	public int insertUserProject(String userName,int categoryId,int project_count);
	
	
	@Select("")
	public int getUserUploadProject(String userName,int categotyId);
}
