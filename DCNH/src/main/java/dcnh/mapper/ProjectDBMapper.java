package dcnh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import dcnh.mode.InnovationProject;
import dcnh.myutil.JsonStr2ListHandler;
import dcnh.myutil.ListStringTypeHandler;

public interface ProjectDBMapper {
	
	@Results({

		@Result(property="projectId", column="project_id", javaType=int.class, jdbcType=JdbcType.INTEGER),
		@Result(property="title", column="title", javaType=String.class, jdbcType=JdbcType.VARCHAR),
		@Result(property="mainCategory", column="category", javaType=String.class, jdbcType=JdbcType.VARCHAR),
		@Result(property="subCategory", column="subcategory", javaType=String.class, jdbcType=JdbcType.VARCHAR),
		@Result(property="participators", column="participators", javaType=List.class, typeHandler=JsonStr2ListHandler.class ,jdbcType=JdbcType.VARCHAR),
		@Result(property="school", column="school", javaType=String.class, jdbcType=JdbcType.VARCHAR),
		@Result(property="teacher", column="teacher", javaType=String.class, jdbcType=JdbcType.VARCHAR),
		@Result(property="score", column="score", javaType=String.class, jdbcType=JdbcType.INTEGER),
		@Result(property="attachmentId", column="attachment_id", javaType=String.class, jdbcType=JdbcType.VARCHAR)
	})
	@Select("SELECT * FROM project_table where project_id=#{projectId};")
	public InnovationProject getInnovationProjectByProjectID(@Param("projectId")int projectId );
	
	@Results({
		@Result(property="projectId", column="project_id", javaType=int.class, jdbcType=JdbcType.INTEGER),
		@Result(property="title", column="title", javaType=String.class, jdbcType=JdbcType.VARCHAR),
		@Result(property="mainCategory", column="category", javaType=String.class, jdbcType=JdbcType.VARCHAR),
		@Result(property="subCategory", column="subcategory", javaType=String.class, jdbcType=JdbcType.VARCHAR),
		@Result(property="participators", column="participators", javaType=List.class, typeHandler=JsonStr2ListHandler.class ,jdbcType=JdbcType.VARCHAR),
		@Result(property="school", column="school", javaType=String.class, jdbcType=JdbcType.VARCHAR),
		@Result(property="teacher", column="teacher", javaType=String.class, jdbcType=JdbcType.VARCHAR),
		@Result(property="score", column="score", javaType=String.class, jdbcType=JdbcType.INTEGER),
		@Result(property="attachmentId", column="attachment_id", javaType=String.class, jdbcType=JdbcType.VARCHAR)
	})
	@Insert("INSERT INTO project_table VALUES(#{project.title}, #{project.mainCategory}, "
			+ "#{project.subCategory}, #{project.participators}, #{project.school}, #{project.teacher},"
			+ "#{project.score}, #{project.attachmentId});")
	public int addNewProject(@Param("project")InnovationProject project);
	
	@Results({
		@Result(property="projectId",column="project_id",javaType=Integer.class,jdbcType=JdbcType.INTEGER),
		@Result(property="school",column="school",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="teacher",column="teacher",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="score",column="score",javaType = Integer.class,jdbcType=JdbcType.INTEGER),
		@Result(property="mainCategory",column="category",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="subCategory",column="subcategory",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="title",column="title",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="participators",column="participators",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="attachmentId",column="attachment_id",javaType=String.class,jdbcType=JdbcType.VARCHAR),
	})
	@Insert("INSERT INTO `dcnh`.`project_table` "
			+ "(`school`, `teacher`, `score`, `category`, `subcategory`, `title`, `participators`, `attachment_id`) "
			+ "VALUES ('asd', 'asd', '1', 'as', 'as', 'asd', 'asd', '1');")
	public int insertNewProject(@Param("project") InnovationProject project);
	
	@Results({
		@Result(property="projectId",column="project_id",javaType=Integer.class,jdbcType=JdbcType.INTEGER),
		@Result(property="school",column="school",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="teacher",column="teacher",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="score",column="score",javaType = Integer.class,jdbcType=JdbcType.INTEGER),
		@Result(property="mainCategory",column="category",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="subCategory",column="subcategory",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="title",column="title",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="participators",column="participators",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="attachmentId",column="attachment_id",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		
		
	})
	@Select("SELECT * FROM project_table where project_id=#{id};")
	public InnovationProject getInnovationProject(int id);
	
	@Results({
		@Result(property="projectId",column="project_id",javaType=Integer.class,jdbcType=JdbcType.INTEGER),
		@Result(property="school",column="school",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="teacher",column="teacher",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="score",column="score",javaType = Integer.class,jdbcType=JdbcType.INTEGER),
		@Result(property="mainCategory",column="category",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="subCategory",column="subcategory",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="title",column="title",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="participators",column="participators",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="attachmentId",column="attachment_id",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		
		
	})
	@Select("SELECT * FROM project_table;")
	public List<InnovationProject> getAllInnovationProject();
}
