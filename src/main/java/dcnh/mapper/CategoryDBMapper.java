package dcnh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import dcnh.mode.BaseCategory;
import dcnh.mode.SubCategory;

public interface CategoryDBMapper {

	@Results({ @Result(property = "id", column = "id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "name", column = "category", javaType = String.class, jdbcType = JdbcType.VARCHAR) })
	@Select("select * from category_table;")
	public List<BaseCategory> getAllMainCategory();

	@Results({ @Result(property = "id", column = "id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "name", column = "subcategory_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "mainCategoryId", column = "category_id", javaType = Integer.class, jdbcType = JdbcType.INTEGER) })
	@Select("select * from subcategory_table where category_id=#{id};")
	public List<SubCategory> getAllSubCategory(@Param("id") int id);

	@Update("UPDATE `dcnh`.`category_table` SET `proportion`=#{proportion} WHERE `category`=#{category};")
	public int updateCategoryproportion(@Param("category") String category, @Param("proportion") int proportion);
}
