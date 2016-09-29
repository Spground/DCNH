package dcnh.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface JudgeGroupDBMapper {
	
	@Select("SELECT judge_group_id FROM dcnh.judge_group_table where judge_name=#{judgeName};")
	public int getJudgeGroupId(@Param("judgeName") String judgeName);
	
	@Insert("INSERT INTO `dcnh`.`judge_group_table` (`judge_name`, `judge_group_id`)"
			+ " VALUES (#{judgeName}, #{judgeGroupId});")
	public int insertJudgeGroup(@Param("judgeName") String judgeName,@Param("judgeGroupId") int groupId);
	
	@Delete("truncate table dcnh.judge_group_table;")
	public int truncateJudgeGroupTable();
}
