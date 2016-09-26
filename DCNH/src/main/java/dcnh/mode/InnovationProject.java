package dcnh.mode;

import java.util.List;

import lombok.Data;

@Data
public class InnovationProject {
	private int projectId;//项目编号
	private String title;//项目名称
	private String mainCategory;//所属大类
	private List<String> participators;//参与者
	private String school;//所在学校
	private String subCategory;//所属子类
	private String teacher;//指导教师
	private String attachmentId;//附件
	private String score;//评分
	
}
