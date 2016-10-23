package dcnh.mode;

import lombok.Data;

@Data
public class Judgement {
	private String judgeName;//评委名称
	private int score;//评分
	private int projectId;//大创项目ID
	private String projectTitle;//大创项目名称
}
