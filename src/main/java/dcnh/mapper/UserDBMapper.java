package dcnh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import dcnh.global.UserPermission;
import dcnh.model.Expert;
import dcnh.model.User;
import dcnh.utils.GenericEnumCodeHandler;

public interface UserDBMapper {
	@Results({ @Result(property = "userId", column = "user_id", javaType = int.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "userName", column = "user_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "password", column = "password", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "schoolName", column = "school_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "permission", column = "permission", javaType = UserPermission.class, typeHandler = GenericEnumCodeHandler.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "phoneNumber", column = "phone_number", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "email", column = "email", javaType = String.class, jdbcType = JdbcType.VARCHAR) 
	})
	@Select("SELECT * FROM user_table WHERE user_name=#{userName};")
	public User getUserByUserName(@Param("userName") String userName);

	@Results({ @Result(property = "userId", column = "user_id", javaType = int.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "userName", column = "user_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "password", column = "password", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "schoolName", column = "school_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "permission", column = "permission", javaType = UserPermission.class, typeHandler = GenericEnumCodeHandler.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "phoneNumber", column = "phone_number", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "email", column = "email", javaType = String.class, jdbcType = JdbcType.VARCHAR) })
	@Select("SELECT * FROM user_table WHERE user_id=#{userId};")
	public User getUserByUserId(@Param("userId") int userId);

	@Results({ @Result(property = "userId", column = "user_id", javaType = int.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "userName", column = "user_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "schoolName", column = "school_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "phoneNumber", column = "phone_number", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "email", column = "email", javaType = String.class, jdbcType = JdbcType.VARCHAR) })
	@Select("SELECT * FROM user_table WHERE user_id=#{expertId} AND permission=3 ;")
	public Expert getExpertByExpertId(@Param("expertId") int expertId);

	@Results({
			@Result(property = "userName", column = "user_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "password", column = "password", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "permission", column = "permission", javaType = UserPermission.class, typeHandler = GenericEnumCodeHandler.class, jdbcType = JdbcType.INTEGER),
			@Result(property = "phoneNumber", column = "phone_number", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "email", column = "email", javaType = String.class, jdbcType = JdbcType.VARCHAR) })
	@Insert("INSERT INTO user_table (`user_name`, `password`, `permission`, `school_name`, `phone_number`, `email`)"
			+ " VALUES (#{user.userName},#{user.password},#{user.permission.code} ,#{user.schoolName},#{user.phoneNumber}, #{user.email});")
	public int addNewUser(@Param("user") User user);

	@Delete("DELETE FROM `user_table` WHERE `user_name`=#{userName};")
	public int deleteUser(@Param("userName") String userName);

	@Results({ 
		@Result(property = "userId", column = "user_id", javaType = int.class, jdbcType = JdbcType.INTEGER),
		@Result(property = "userName", column = "user_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
		@Result(property = "password", column = "password", javaType = String.class, jdbcType = JdbcType.VARCHAR),
		@Result(property = "schoolName", column = "school_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
		@Result(property = "permission", column = "permission", javaType = UserPermission.class, typeHandler = GenericEnumCodeHandler.class, jdbcType = JdbcType.INTEGER),
		@Result(property = "phoneNumber", column = "phone_number", javaType = String.class, jdbcType = JdbcType.VARCHAR),
		@Result(property = "email", column = "email", javaType = String.class, jdbcType = JdbcType.VARCHAR) 
	})
	@Select("SELECT * FROM dcnh.user_table where permission=#{permission};")
	public List<User> getAllUserInfoByPermission(@Param("permission") int permission);

	@Results({ 
		@Result(property = "userId", column = "user_id", javaType = int.class, jdbcType = JdbcType.INTEGER),
		@Result(property = "userName", column = "user_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
		@Result(property = "password", column = "password", javaType = String.class, jdbcType = JdbcType.VARCHAR),
		@Result(property = "schoolName", column = "school_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
		@Result(property = "permission", column = "permission", javaType = UserPermission.class, typeHandler = GenericEnumCodeHandler.class, jdbcType = JdbcType.INTEGER),
		@Result(property = "phoneNumber", column = "phone_number", javaType = String.class, jdbcType = JdbcType.VARCHAR),
		@Result(property = "email", column = "email", javaType = String.class, jdbcType = JdbcType.VARCHAR) 
	})
	@Select("SELECT * FROM dcnh.user_table where school_name=#{school_name};")
	public List<User> getAllUserInfoBySchoolName(@Param("school_name") String school_name);

}
