package dcnh.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import dcnh.mode.Attachement;

public interface AttachementDBMapper {
	
	@Results({
		@Result(property="projectId",column="attachement_id",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="school",column="attachement_filepath",javaType=String.class,jdbcType=JdbcType.VARCHAR)		
	})
	@Insert("INSERT INTO `dcnh`.`attachement_table` (`attachement_id`, `attachement_filepath`) VALUES (#{attachement.attachementId}, #{attachement.filePath});")
	public int insertAttachementDBMapper(@Param("attachement") Attachement attachement);
	
	@Results({
		@Result(property="projectId",column="attachement_id",javaType=String.class,jdbcType=JdbcType.VARCHAR),
		@Result(property="school",column="attachement_filepath",javaType=String.class,jdbcType=JdbcType.VARCHAR)		
	})
	@Select("SELECT * FROM dcnh.attachement_table where attachement_id=#{attachementId};")
	public Attachement getAttachementById(@Param("attachementId") String attachementId);
}
