package dcnh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface SchoolDBMapper {
	@Select("SELECT school_name FROM school_table;")
	public List<String> getAllSchool();
}
