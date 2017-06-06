package dcnh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import dcnh.model.MainCategory;
import dcnh.model.SubCategory;

public interface CategoryDBMapper {
	@Results({
			@Result(property = "mainCategoryId", column = "maincategory_id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "mainCategoryName", column = "maincategory_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "proportion", column = "proportion", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "subCategories", column = "maincategory_id", many = @Many(select = "dcnh.mapper.CategoryDBMapper.getAllSubCategory")) })
	@Select("select * from maincategory_table where maincategory_id=#{id}")
	public MainCategory findMainCategoryById(int id);

	@Results({
			@Result(property = "mainCategoryId", column = "maincategory_id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "mainCategoryName", column = "maincategory_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "proportion", column = "proportion", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "subCategories", column = "maincategory_id", many = @Many(select = "dcnh.mapper.CategoryDBMapper.getAllSubCategory")), })
	@Select("select * from maincategory_table;")
	public List<MainCategory> getAllMainCategory();

	@Results({
			@Result(property = "subCategoryId", column = "subcategory_id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "subCategoryName", column = "subcategory_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "mainCategoryId", column = "maincategory_id", javaType = Integer.class, jdbcType = JdbcType.INTEGER) })
	@Select("select * from subcategory_table where maincategory_id=#{id};")
	public List<SubCategory> getAllSubCategory(@Param("id") int mainCategoryId);

	@Update("UPDATE `dcnh`.`maincategory_table` SET `proportion`=#{proportion} WHERE `maincategory_name`=#{categoryName};")
	public int updateMainCategoryProportion(@Param("categoryName") String mainCategoryName,
			@Param("proportion") int proportion);
}
