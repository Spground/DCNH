package dcnh.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import dcnh.model.Attachment;

public interface AttachmentDBMapper {

	@Results({
			@Result(property = "projectId", column = "attachment_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "school", column = "attachment_filepath", javaType = String.class, jdbcType = JdbcType.VARCHAR) })
	@Insert("INSERT INTO `dcnh`.`attachment_table` (`attachment_id`, `attachment_filepath`) VALUES (#{attachment.attachmentId}, #{attachment.filePath});")
	public int insertAttachmentDBMapper(@Param("attachment") Attachment attachment);

	@Results({
			@Result(property = "attachmentId", column = "attachment_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "filePath", column = "attachment_filepath", javaType = String.class, jdbcType = JdbcType.VARCHAR) })
	@Select("SELECT * FROM dcnh.attachment_table where attachment_id=#{attachment_id};")
	public Attachment getAttachmentById(@Param("attachment_id") String attachmentId);
}
