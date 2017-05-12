package dcnh.model;

import lombok.Data;

@Data
public class Assignment {
	private int id;
	private Expert expert;
	private Project project;
	private int score;
	private int finished;
}
