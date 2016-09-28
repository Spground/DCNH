package dcnh.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/*
 * 用户分配了哪些项目
 */
public interface UserProjectDBMapper {

	@Insert("INSERT INTO `user_project_table` (`user_name`, `category_id`, `project_count`) "
			+ "VALUES ('admin', #{categoryId}, #{projectCount});")
	public int insertUserProject(@Param("userName") String userName,@Param("categoryId") int categoryId,@Param("projectCount") int projectCount);
	
	/*
	 * 用户上传的项目数,对于某一类型
	 */
	@Select("SELECT upload_count  FROM dcnh.user_project_table where user_name=#{userName} and category_id=#{categoryId};")
	public int getUserUploadProject(@Param("userName") String userName,@Param("categoryId") int categoryId);
	
	/*
	 * 分配给用户的项目数
	 */
	@Select("SELECT project_count FROM dcnh.user_project_table;")
	public int getUserProject(@Param("userName") String userName,@Param("categoryId") int categotyId);
}
