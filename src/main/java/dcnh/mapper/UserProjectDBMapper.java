package dcnh.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/*
 * 用户分配了哪些项目
 */
public interface UserProjectDBMapper {

	@Insert("INSERT INTO `user_project_table` (`user_name`, `category_id`, `project_count`) "
			+ "VALUES (#{userName}, #{categoryId}, #{projectCount});")
	public int insertUserProject(@Param("userName") String userName, @Param("categoryId") int categoryId,
			@Param("projectCount") int projectCount);

	/*
	 * 用户上传的项目数,对于某一类型
	 */
	@Select("SELECT upload_count  FROM dcnh.user_project_table where user_name=#{userName} and category_id=#{categoryId};")
	public Integer getUserUploadProject(@Param("userName") String userName, @Param("categoryId") int categoryId);

	/*
	 * 分配给用户的项目数
	 */
	@Select("SELECT project_count FROM dcnh.user_project_table WHERE `user_name`=#{userName} and `category_id`=#{categoryId};")
	public Integer getUserProject(@Param("userName") String userName, @Param("categoryId") int categotyId);

	/*
	 * 更新上传的项目数目
	 */
	@Insert("UPDATE `dcnh`.`user_project_table` SET `upload_count`=#{newuploadCount} WHERE `user_name`=#{userName} and `category_id`=#{categoryId};")
	public int updateUploadCount(@Param("newuploadCount") int newuploadCount, @Param("userName") String userName,
			@Param("categoryId") int categoryId);

	/*
	 * 获取某位专家所在的专家组的ID
	 */
	@Select("SELECT judge_group_id FROM dcnh.judge_group_table where judge_name=#{userName};")
	public Integer getGroupIdofJudge(@Param("userName") String userName);
}
