package dcnh.sqlprovider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

/**
 * @author WuJie
 * @date 2017年5月10日下午4:26:10
 * @version 1.0
 **/
public class ProjectExpertAssignmentSqlProvider {

	public String getAssignments(final Map<String, Object> where) {
		return new SQL() {
			{
				SELECT("*");
				FROM("project_expert_assignment_table");
				if (where.containsKey("finished")) {
					WHERE("finished=#{finished}");
				}
				if (where.containsKey("expertId")) {
					WHERE("expert_id=#{expertId}");
				}
				if (where.containsKey("projectId")) {
					WHERE("project_id=#{projectId}");
				}
				if (where.containsKey("score")) {
					WHERE("score>=#{score}");
				}
			}
		}.toString();
	}
}
