package dcnh.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ProjectGroupDBMapper {
	@Insert("INSERT INTO `project_group_table` "
			+ "(`project_id`, `project_group_id`) VALUES (#{projectId}, #{groupId});")
	public int insertProjectGroup(@Param("projectId") int projectId,@Param("groupId") int groupId);
	
	@Select("SELECT project_group_id FROM dcnh.project_group_table where project_id = #{projectId};")
	public int getGroupID(@Param("projectId") int projectId);
	
	@Delete("truncate table dcnh.project_group_table;")
	public void truncate();
}
