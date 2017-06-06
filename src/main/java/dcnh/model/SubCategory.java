package dcnh.model;

import lombok.Data;

@Data
public class SubCategory {
	private int subCategoryId;
	private String subCategoryName;
	private int mainCategoryId;
}
