package dcnh.mode;

import lombok.Data;

@Data
public class Judgement {
	private String judge;//评委名称
	private int judgeId;//评委ID
	private int projectId;//大创项目ID
	private String projectTitle;//大创项目名称
}
