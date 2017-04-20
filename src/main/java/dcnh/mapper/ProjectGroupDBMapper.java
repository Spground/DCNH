package dcnh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import dcnh.mode.InnovationProject;

public interface ProjectGroupDBMapper {
	@Insert("INSERT INTO `project_group_table` "
			+ "(`project_id`, `project_group_id`) VALUES (#{projectId}, #{groupId});")
	public int insertProjectGroup(@Param("projectId") int projectId,@Param("groupId") int groupId);
	
	@Select("SELECT project_group_id FROM dcnh.project_group_table where project_id = #{projectId};")
	public int getGroupID(@Param("projectId") int projectId);
	
	@Delete("truncate table dcnh.project_group_table;")
	public void truncate();
	
	/*
	 * 获取一个组的所有的项目信息
	 */
	@Results({
		@Result(property="projectId",column="project_id",javaType=Integer.class,jdbcType=JdbcType.INTEGER),
		@Result(property="school",column="school",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="teacher",column="teacher",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="score",column="score",javaType = Integer.class,jdbcType=JdbcType.INTEGER),
		@Result(property="mainCategory",column="category",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="subCategory",column="subcategory",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="title",column="title",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="participators",column="participators",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="attachmentId",column="attachment_id",javaType=String.class,jdbcType=JdbcType.VARCHAR)
	})
	@Select("SELECT * FROM dcnh.project_table as project"
			+ " right join "
			+ "(SELECT project_id FROM dcnh.project_group_table where project_group_id=#{groupId})  as projectgroup"
			+ " on projectgroup.project_id = project.project_id;")
	public List<InnovationProject> getProjectGroupListById(@Param("groupId") int groupId);
	
	/*
	 * 获取某评委已评分项目
	 */
	@Results({
		@Result(property="projectId",column="project_id",javaType=Integer.class,jdbcType=JdbcType.INTEGER),
		@Result(property="school",column="school",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="teacher",column="teacher",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="score",column="score",javaType = Integer.class,jdbcType=JdbcType.INTEGER),
		@Result(property="mainCategory",column="category",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="subCategory",column="subcategory",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="title",column="title",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="participators",column="participators",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="attachmentId",column="attachment_id",javaType=String.class,jdbcType=JdbcType.VARCHAR)
	})
	@Select("select * from dcnh.project_table as project"
			+ " right join "
			+ "(select project_id from  dcnh.judgement_table where judge_name=#{userName}) as judgement_project"
			+ " on project.project_id = judgement_project.project_id;")
	public List<InnovationProject> getGradedProject(@Param("userName") String userName);
	
	/*
	 * 获取未评分项目
	 */
	@Results({
		@Result(property="projectId",column="project_id",javaType=Integer.class,jdbcType=JdbcType.INTEGER),
		@Result(property="school",column="school",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="teacher",column="teacher",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="score",column="score",javaType = Integer.class,jdbcType=JdbcType.INTEGER),
		@Result(property="mainCategory",column="category",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="subCategory",column="subcategory",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="title",column="title",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="participators",column="participators",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="attachmentId",column="attachment_id",javaType=String.class,jdbcType=JdbcType.VARCHAR)
	})
	public List<InnovationProject> getNotGradedProject(@Param("userName") String userName);
	
	/*
	 * 获得某组的所有项目的Id
	 */
	@Select("SELECT * FROM dcnh.project_group_table where project_group_id=#{groupId};")
	public List<Integer> getAllProjectId(@Param("groupId") int groupId);
	
	/*
	 * 获取已评分项目Id
	 */
	@Select("SELECT project_id FROM dcnh.judgement_table where judge_name=#{userName};")
	public List<Integer> getAllGradedProjectId(@Param("userName") String userName);
}
