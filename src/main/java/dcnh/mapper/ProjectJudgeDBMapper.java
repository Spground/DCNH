package dcnh.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/*
 * 大创项目分组和专家分组之间的对应关系，
 * 即给专家组分配的是哪一个大创项目组
 */
public interface ProjectJudgeDBMapper {

	@Delete("truncate table dcnh.project_judge_table;")
	public void truncateProjectJudgeTable();

	@Select("SELECT judge_group_id FROM dcnh.project_judge_table where project_group_id=#{projectGroupId};")
	public int getJudgeGroupId(@Param("projectGroupId") int projectGroupId);

	@Select("SELECT project_group_id FROM dcnh.project_judge_table where judge_group_id = #{judgeGroupId};")
	public int getProjectGroupId(@Param("judgeGroupId") int judgeGroupId);

	@Insert("INSERT INTO `project_judge_table` (`project_group_id`, `judge_group_id`) VALUES (#{projectGroupId},#{judgeGroupId});")
	public int insertProjectJudge(@Param("projectGroupId") int projectGroupId, @Param("judgeGroupId") int judgeGroupId);
}
