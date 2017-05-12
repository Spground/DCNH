package dcnh.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/*
 * 用户分配了哪些项目
 */
public interface SchoolProjectCountDBMapper {

	@Insert("INSERT INTO `school_project_count_table` (`school_id`, `maincategory_id`, `limit_count`) "
			+ "VALUES (#{school_id}, #{maincategory_id}, #{limit_count});")
	public int insertSchoolProjectCountItem(@Param("school_id") int schoolId,
			@Param("maincategory_id") int maincategoryId, @Param("limit_count") int limitCount);

	/*
	 * 获取某个学校的每个主分类下已经上传的项目数
	 */
	@Select("SELECT uploaded_count  FROM `dcnh`.`school_project_count_table` WHERE school_id=#{school_id} AND maincategory_id=#{maincategory_id};")
	public Integer getUploadedProjectCount(@Param("school_id") int schoolId,
			@Param("maincategory_id") int maincategoryId);

	/*
	 * 分配给用户的项目数
	 */
	@Select("SELECT limit_count FROM `dcnh`.`school_project_count_table` WHERE `school_id`=#{school_id} AND `maincategory_id`=#{maincategory_id};")
	public Integer getLimitProjectCount(@Param("school_id") int schoolId, @Param("maincategory_id") int maincategoryId);

	/*
	 * 更新已经上传的项目数目
	 */
	@Insert("UPDATE `dcnh`.`school_project_count_table` SET `uploaded_count`=#{newUploadedCount} WHERE `school_id`=#{school_id} AND `maincategory_id`=#{maincategory_id};")
	public int updateUploadCount(@Param("school_id") int schoolId, @Param("maincategory_id") int maincategoryId,
			@Param("newUploadedCount") int newUploadedCount);

}
