package dcnh.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import dcnh.model.School;

public interface SchoolDBMapper {
	@Select("SELECT school_name FROM school_table;")
	public List<String> getAllSchool();

	@Results({ @Result(property = "schoolId", column = "school_id", javaType = int.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "schoolName", column = "school_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "schoolCode", column = "school_code", javaType = String.class, jdbcType = JdbcType.VARCHAR) })
	@Select("SELECT * FROM school_table;")
	@MapKey("schoolId")
	public Map<Integer, School> getAllSchoolObjectMappedBySchoolId();

	@Results({ @Result(property = "schoolId", column = "school_id", javaType = int.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "schoolName", column = "school_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "schoolCode", column = "school_code", javaType = String.class, jdbcType = JdbcType.VARCHAR) })
	@Select("SELECT * FROM school_table;")
	@MapKey("schoolName")
	public Map<String, School> getAllSchoolObjectMappedBySchoolName();

}
