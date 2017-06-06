package dcnh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import dcnh.model.Assignment;
import dcnh.model.Expert;
import dcnh.model.Project;

public interface ProjectExpertAssignmentDBMapper {

	@Results({
			@Result(property = "expert", column = "expert_id", javaType = Expert.class, jdbcType = JdbcType.INTEGER, one = @One(select = "dcnh.mapper.UserDBMapper.getExpertByExpertId")),
			@Result(property = "project", column = "project_id", javaType = Project.class, jdbcType = JdbcType.INTEGER, one = @One(select = "dcnh.mapper.ProjectDBMapper.getInnovationProjectByProjectId")),
			@Result(property = "score", column = "score", javaType = int.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "finished", column = "finished", javaType = int.class, jdbcType = JdbcType.INTEGER) })
	@Select("SELECT * FROM `dcnh`.`project_expert_assignment_table` WHERE expert_id=#{expertId} AND project_id=#{projectId};")
	public Assignment getAssignmentByExpertIdAndProjectId(@Param("expertId") int expertId,
			@Param("projectId") int projectId);

	@Results({
			@Result(property = "expert", column = "expert_id", javaType = Expert.class, jdbcType = JdbcType.INTEGER, one = @One(select = "dcnh.mapper.UserDBMapper.getExpertByExpertId")),
			@Result(property = "project", column = "project_id", javaType = Project.class, jdbcType = JdbcType.INTEGER, one = @One(select = "dcnh.mapper.ProjectDBMapper.getInnovationProjectByProjectId")),
			@Result(property = "score", column = "score", javaType = int.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "finished", column = "finished", javaType = int.class, jdbcType = JdbcType.INTEGER) })
	@Select("SELECT * FROM `dcnh`.`project_expert_assignment_table` WHERE expert_id=#{expertId};")
	public List<Assignment> getAssignmentsByExpertId(@Param("expertId") int expertId);

	@Results({
			@Result(property = "expert", column = "expert_id", javaType = Expert.class, jdbcType = JdbcType.INTEGER, one = @One(select = "dcnh.mapper.UserDBMapper.getExpertByExpertId")),
			@Result(property = "project", column = "project_id", javaType = Project.class, jdbcType = JdbcType.INTEGER, one = @One(select = "dcnh.mapper.ProjectDBMapper.getInnovationProjectByProjectId")),
			@Result(property = "score", column = "score", javaType = int.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "finished", column = "finished", javaType = int.class, jdbcType = JdbcType.INTEGER) })
	@Select("SELECT * FROM `dcnh`.`project_expert_assignment_table` WHERE expert_id=#{expertId} AND finished=1;")
	public List<Assignment> getFinishedAssignmentsByExpertId(@Param("expertId") int expertId);

	@Results({
			@Result(property = "expert", column = "expert_id", javaType = Expert.class, jdbcType = JdbcType.INTEGER, one = @One(select = "dcnh.mapper.UserDBMapper.getExpertByExpertId")),
			@Result(property = "project", column = "project_id", javaType = Project.class, jdbcType = JdbcType.INTEGER, one = @One(select = "dcnh.mapper.ProjectDBMapper.getInnovationProjectByProjectId")),
			@Result(property = "score", column = "score", javaType = int.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "finished", column = "finished", javaType = int.class, jdbcType = JdbcType.INTEGER) })
	@Select("SELECT * FROM `dcnh`.`project_expert_assignment_table` WHERE expert_id=#{expertId} AND finished=0;")
	public List<Assignment> getUnFinishedAssignmentsByExpertId(@Param("expertId") int expertId);

	@Insert("INSERT INTO `dcnh`.`project_expert_assignment_table` " + "(`expert_id`, `project_id`, `score`) "
			+ "VALUES (#{assignment.expert.userId}, #{assignment.project.projectId}, #{assignment.score});")
	@Options(keyProperty = "assignment.id", useGeneratedKeys = true)
	public int addAssignment(@Param("assignment") Assignment assignment);

	@Select("SELECT sum(score) / count(project_id) FROM `dcnh`.`project_expert_assignment_table` WHERE project_id=#{projectId};")
	public double getInnovationProjectAvgScore(@Param("projectId") int projectId);

	@Update("UPDATE `dcnh`.`project_expert_assignment_table` SET finished=#{assignment.finished}, score=#{assignment.score} WHERE expert_id=#{assignment.expert.userId} AND project_id=#{assignment.project.projectId}")
	public int updateAssignment(@Param("assignment") Assignment assignment);

	@Delete("truncate table `dcnh`.`project_expert_assignment_table`;")
	public int truncateProjectExpertAssignmentTable();

}
