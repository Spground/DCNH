package dcnh.model;

import lombok.Data;

@Data
public class ResponseMessage {
	private int code;
	private String message;
	private String href;
}
