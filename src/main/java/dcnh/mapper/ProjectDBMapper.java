package dcnh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;
import dcnh.mode.InnovationProject;
import dcnh.myutil.JsonStr2ListHandler;

public interface ProjectDBMapper {

	@Results({
			@Result(property = "projectId", column = "project_id", javaType = int.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "title", column = "title", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "mainCategory", column = "category", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "subCategory", column = "subcategory", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "participators", column = "participators", javaType = List.class, typeHandler = JsonStr2ListHandler.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "school", column = "school", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "teacher", column = "teacher", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "score", column = "score", javaType = String.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "attachmentId", column = "attachment_id", javaType = String.class, jdbcType = JdbcType.VARCHAR) })
	@Select("SELECT * FROM project_table where project_id=#{projectId};")
	public InnovationProject getInnovationProjectByProjectID(@Param("projectId") int projectId);

	@Results({
			@Result(property = "projectId", column = "project_id", javaType = int.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "title", column = "title", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "mainCategory", column = "category", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "subCategory", column = "subcategory", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "participators", column = "participators", javaType = List.class, typeHandler = JsonStr2ListHandler.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "school", column = "school", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "teacher", column = "teacher", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "score", column = "score", javaType = String.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "attachmentId", column = "attachment_id", javaType = String.class, jdbcType = JdbcType.VARCHAR) })
	@Insert("INSERT INTO `dcnh`.`project_table` "
			+ "(`school`, `teacher`, `score`, `category`, `subcategory`, `title`, `participators`, `attachment_id`) "
			+ "VALUES (#{project.school},#{project.teacher},#{project.score},#{project.mainCategory},#{project.subCategory}, #{project.title},#{project.participators}, #{project.attachmentId});")
	public int addNewProject(@Param("project") InnovationProject project);

	@Results({
			@Result(property = "projectId", column = "project_id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "school", column = "school", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "teacher", column = "teacher", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "score", column = "score", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "mainCategory", column = "category", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "subCategory", column = "subcategory", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "title", column = "title", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "participators", column = "participators", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "attachmentId", column = "attachment_id", javaType = String.class, jdbcType = JdbcType.VARCHAR), })
	@Insert("INSERT INTO `dcnh`.`project_table` "
			+ "(`school`, `teacher`, `score`, `category`, `subcategory`, `title`, `participators`, `attachment_id`) "
			+ "VALUES ('asd', 'asd', '1', 'as', 'as', 'asd', 'asd', '1');")
	public int insertNewProject(@Param("project") InnovationProject project);

	@Results({
			@Result(property = "projectId", column = "project_id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "school", column = "school", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "teacher", column = "teacher", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "score", column = "score", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "mainCategory", column = "category", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "subCategory", column = "subcategory", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "title", column = "title", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "participators", column = "participators", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "attachmentId", column = "attachment_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),

	})
	@Select("SELECT * FROM project_table where project_id=#{id};")
	public InnovationProject getInnovationProject(@Param("id") int id);

	@Results({
			@Result(property = "projectId", column = "project_id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "school", column = "school", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "teacher", column = "teacher", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "score", column = "score", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "mainCategory", column = "category", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "subCategory", column = "subcategory", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "title", column = "title", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "participators", column = "participators", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "attachmentId", column = "attachment_id", javaType = String.class, jdbcType = JdbcType.VARCHAR) })
	@Select("SELECT * FROM project_table order by school")
	public List<InnovationProject> getAllInnovationProject();

	@Results({
			@Result(property = "projectId", column = "project_id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "school", column = "school", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "teacher", column = "teacher", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "score", column = "score", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "mainCategory", column = "category", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "subCategory", column = "subcategory", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "title", column = "title", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "participators", column = "participators", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "attachmentId", column = "attachment_id", javaType = String.class, jdbcType = JdbcType.VARCHAR) })
	@Select("SELECT * FROM project_table where school=#{school};")
	public List<InnovationProject> getAllInnovationProjectByschool(@Param("school") String school);

	@Update("update dcnh.project_table set score=#{newScore} where project_id = #{projectId};")
	public boolean updateScore(@Param("projectId") int projectId, @Param("newScore") int newScore);

	@Results({
			@Result(property = "projectId", column = "project_id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "school", column = "school", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "teacher", column = "teacher", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "score", column = "score", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "mainCategory", column = "category", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "subCategory", column = "subcategory", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "title", column = "title", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "participators", column = "participators", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "attachmentId", column = "attachment_id", javaType = String.class, jdbcType = JdbcType.VARCHAR) })
	@Select("SELECT * FROM project_table where category=#{category} order by score limit #{projectCount}")
	public List<InnovationProject> getProjectListByCategory(@Param("category") String category,
			@Param("projectCount") int projectCount);

	@Select("SELECT count(project_id) FROM dcnh.project_table where category= #{category};")
	public int getProjectCount(@Param("category") String category);

	@Delete("DELETE FROM dcnh.project_table WHERE project_id=#{projectid} AND school=#{schoolName};")
	public int deleteInnovationProjectByProjectIDAndSchoolName(@Param("projectid") int id,
			@Param("schoolName") String schoolName);

	@Delete("DELETE FROM dcnh.project_table WHERE project_id=#{projectid};")
	public int deleteInnovationProjectByProjectID(int projectId);

}
