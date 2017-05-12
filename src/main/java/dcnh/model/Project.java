package dcnh.model;

import java.util.List;

import lombok.Data;

@Data
public class Project {
	private int projectId;// 项目编号
	private String title;// 项目名称
	private String mainCategory;// 所属大类
	private String subCategory;// 所属子类
	private List<String> participators;// 参与者
	private String schoolName;// 所在学校
	private String teacher;// 指导教师
	private Attachment attachment;// 附件
	private int score;// 评分
	private int uploader;// 上传者id
	private long uploadTime;// 上传时间
	private int projectStatus;// 项目状态
	private int year;// 年度
}
