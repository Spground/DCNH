package dcnh.sqlprovider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

/**
 * @author WuJie
 * @date 2017年5月9日下午7:09:38
 * @version 1.0
 **/
public class ProjectSqlProvider {

	/**
	 * 通过条件查询所有的创新项目 学校名schoolName, 主分类 mainCategory, 子分类 subCategory, 上传者
	 * uploader, 项目状态 projectStatus, 指导教师 teacher,年度 year
	 * 
	 * @param where
	 * @return
	 */
	public String getAllInnovationProjects(final Map<String, Object> where) {
		return new SQL() {
			{
				SELECT("*");
				FROM("project_table");
				if (where.containsKey("schoolName")) {
					WHERE("school_name=#{schoolName}");
				}
				if (where.containsKey("mainCategory")) {
					WHERE("maincategory=#{mainCategory}");
				}
				if (where.containsKey("subCategory")) {
					WHERE("subcategory=#{subCategory}");
				}
				if (where.containsKey("uploader")) {
					WHERE("uploader=#{uploader}");
				}
				if (where.containsKey("projectStatus")) {
					WHERE("project_status=#{projectStatus}");
				}
				if (where.containsKey("teacher")) {
					WHERE("teacher=#{teacher}");
				}
				if (where.containsKey("year")) {
					WHERE("year=#{year}");
				}
			}
		}.toString();
	}

}
