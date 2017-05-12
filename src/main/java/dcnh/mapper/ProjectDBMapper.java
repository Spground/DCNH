package dcnh.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import dcnh.model.Attachment;
import dcnh.model.Project;
import dcnh.sqlprovider.ProjectSqlProvider;
import dcnh.utils.JsonStr2ListHandler;

public interface ProjectDBMapper {

	@Results({
			@Result(property = "projectId", column = "project_id", javaType = int.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "title", column = "title", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "mainCategory", column = "maincategory", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "subCategory", column = "subcategory", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "participators", column = "participators", javaType = List.class, typeHandler = JsonStr2ListHandler.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "schoolName", column = "school_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "teacher", column = "teacher", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "attachment", column = "attachment_id", javaType = Attachment.class, jdbcType = JdbcType.VARCHAR, one = @One(select = "dcnh.mapper.AttachmentDBMapper.getAttachmentById")),
			@Result(property = "score", column = "score", javaType = int.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "uploader", column = "uploader", javaType = int.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "uploadTime", column = "upload_time", javaType = long.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "projectStatus", column = "project_status", javaType = int.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "year", column = "year", javaType = int.class, jdbcType = JdbcType.INTEGER) })
	@Select("SELECT * FROM project_table WHERE project_id=#{projectId};")
	public Project getInnovationProjectByProjectId(@Param("projectId") int projectId);

	@Results({
			@Result(property = "projectId", column = "project_id", javaType = int.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "title", column = "title", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "mainCategory", column = "maincategory", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "subCategory", column = "subcategory", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "participators", column = "participators", javaType = List.class, typeHandler = JsonStr2ListHandler.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "schoolName", column = "school_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "teacher", column = "teacher", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "attachment", column = "attachment_id", javaType = Attachment.class, jdbcType = JdbcType.VARCHAR, one = @One(select = "dcnh.mapper.AttachmentDBMapper.getAttachmentById")),
			@Result(property = "score", column = "score", javaType = int.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "uploader", column = "uploader", javaType = int.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "uploadTime", column = "uploaded_time", javaType = long.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "projectStatus", column = "project_status", javaType = int.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "year", column = "year", javaType = int.class, jdbcType = JdbcType.INTEGER) })
	@SelectProvider(type = ProjectSqlProvider.class, method = "getAllInnovationProjects")
	public List<Project> getAllInnovationProjects(Map<String, Object> where);

	@Results({
			@Result(property = "projectId", column = "project_id", javaType = int.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "title", column = "title", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "mainCategory", column = "maincategory", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "subCategory", column = "subcategory", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "participators", column = "participators", javaType = List.class, typeHandler = JsonStr2ListHandler.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "schoolName", column = "school_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "teacher", column = "teacher", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "attachment", column = "attachment_id", javaType = Attachment.class, jdbcType = JdbcType.VARCHAR, one = @One(select = "dcnh.mapper.AttachmentDBMapper.getAttachmentById")),
			@Result(property = "score", column = "score", javaType = int.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "uploader", column = "uploader", javaType = int.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "uploadTime", column = "upload_time", javaType = long.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "projectStatus", column = "project_status", javaType = int.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "year", column = "year", javaType = int.class, jdbcType = JdbcType.INTEGER) })
	@Select("SELECT * FROM project_table ORDER BY school_name")
	public List<Project> getAllInnovationProjectsOrderBySchoolName();

	@Results({
			@Result(property = "projectId", column = "project_id", javaType = int.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "title", column = "title", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "mainCategory", column = "maincategory", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "subCategory", column = "subcategory", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "participators", column = "participators", javaType = List.class, typeHandler = JsonStr2ListHandler.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "schoolName", column = "school_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "teacher", column = "teacher", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "attachment", column = "attachment_id", javaType = Attachment.class, jdbcType = JdbcType.VARCHAR, one = @One(select = "dcnh.mapper.AttachmentDBMapper.getAttachmentById")),
			@Result(property = "score", column = "score", javaType = int.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "uploader", column = "uploader", javaType = int.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "uploadTime", column = "upload_time", javaType = long.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "projectStatus", column = "project_status", javaType = int.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "year", column = "year", javaType = int.class, jdbcType = JdbcType.INTEGER) })
	@Select("SELECT * FROM project_table WHERE maincategory=#{mainCategory} ORDEY BY score DESC LIMIT #{topCount}")
	public List<Project> getTopInnovationProjectsByMainCategory(@Param("mainCategory") String mainCategory,
			@Param("topCount") int topCount);

	@Select("SELECT count(project_id) FROM dcnh.project_table WHERE maincategory= #{mainCategory};")
	public int getInnovationProjectsCountByMainCategory(@Param("mainCategory") String mainCategory);

	@Insert("INSERT INTO `dcnh`.`project_table` "
			+ "(`school_name`, `teacher`, `score`, `maincategory`, `subcategory`, `title`, `participators`, `attachment_id`, `uploader`, `upload_time`, `project_status`, `year`)"
			+ "VALUES (#{project.schoolName},#{project.teacher},#{project.score},#{project.mainCategory},#{project.subCategory}, #{project.title},#{project.participators, typeHandler = dcnh.utils.JsonStr2ListHandler}, "
			+ "#{project.attachment.attachementId}, #{project.uploader}, #{project.uploadTime},#{project.projectStatus}, #{project.year});")
	@Options(keyProperty = "project.projectId", useGeneratedKeys = true)
	public int addProject(@Param("project") Project project);

	@Update("UPDATE dcnh.project_table SET score=#{newScore} WHERE project_id = #{projectId};")
	public boolean updateProjectScore(@Param("projectId") int projectId, @Param("newScore") int newScore);

	@Delete("DELETE FROM dcnh.project_table WHERE project_id=#{projectid};")
	public int deleteInnovationProjectByProjectId(@Param("projectid") int id);

}
