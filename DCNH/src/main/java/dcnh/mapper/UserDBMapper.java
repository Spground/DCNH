package dcnh.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import dcnh.globalInfo.UserPermission;
import dcnh.mode.BaseUser;
import dcnh.myutil.GenericEnumCodeHandler;

public interface UserDBMapper {
	@Results({
		@Result(property="userName",column="user_name",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="password",column="password",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="school",column="school",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="permission",column="permission",javaType = UserPermission.class,typeHandler=GenericEnumCodeHandler.class,jdbcType=JdbcType.TINYINT),
		@Result(property="phoneNumber",column="phone_number",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="email",column="email",javaType=String.class,jdbcType=JdbcType.VARCHAR)
		
	})
	@Select("SELECT * FROM user_table where user_name=#{userName};")
	public BaseUser getUserByUserName(String userName);
	
	@Results({
		@Result(property="userName",column="name",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="password",column="title",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="permission",column="permission",javaType = UserPermission.class,typeHandler=GenericEnumCodeHandler.class,jdbcType=JdbcType.TINYINT),
		@Result(property="phoneNumber",column="phone_number",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="email",column="email",javaType=String.class,jdbcType=JdbcType.VARCHAR)
	})
	@Insert("INSERT INTO user_table (`user_name`, `password`, `permission`, `school`, `phone_number`, `email`)"
			+ " VALUES (#{user.userName},#{user.password}, '1',#{user.school},#{user.phoneNumber}, #{user.email});")
	public int addNewUser(BaseUser user);
}
