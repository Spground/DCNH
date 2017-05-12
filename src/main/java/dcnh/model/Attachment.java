package dcnh.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
@JsonIgnoreProperties({"filePath"})
@Data
public class Attachment {
	private String attachmentId;
	private String filePath;
	private int projectId;
}
