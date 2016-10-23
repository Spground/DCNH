package dcnh.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import dcnh.mode.Judgement;

public interface JudgementDBMapper {
	
	@Results({
		@Result(property="judgeName",column="judge_name",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="projectId",column="project_id",javaType=String.class,jdbcType=JdbcType.VARCHAR)
	})
	@Select("SELECT * FROM dcnh.judgement_table where judge_name=#{judgeName} and project_id=#{projectId};")
	public Judgement getJudgement(@Param("judgeName") String judgeName,@Param("projectId") int projectId);


	@Results({
		@Result(property="judgeName",column="judge_name",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="projectId",column="project_id",javaType=String.class,jdbcType=JdbcType.VARCHAR)
	})
	@Insert("INSERT INTO `dcnh`.`judgement_table` "
			+ "(`judge_name`, `project_id`, `score`) "
			+ "VALUES (#{judgement.judgeName}, #{judgement.projectId},#{judgement.score});")
	public int addNewJudgement(@Param("judgement") Judgement judgement);
	
	@Select("SELECT sum(score)/count(project_id) "
			+ "FROM dcnh.judgement_table where project_id=#{projectId};")
	public double getAvgScore(@Param("projectId") int projectId);
	
	@Delete("truncate table dcnh.judgement_table;")
	public int truncateJudgementTable();
}
