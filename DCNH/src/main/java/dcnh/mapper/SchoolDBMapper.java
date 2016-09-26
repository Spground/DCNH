package dcnh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

public interface SchoolDBMapper {
	@Select("SELECT school_name FROM school_table;")
	public List<String> getAllSchool();
}
