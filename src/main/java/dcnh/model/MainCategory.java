package dcnh.model;

import java.util.List;

import lombok.Data;

@Data
public class MainCategory {
	private int mainCategoryId;
	private String mainCategoryName;
	private int proportion;
	private List<SubCategory> subCategories;
}
