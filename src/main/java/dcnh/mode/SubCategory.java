package dcnh.mode;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SubCategory extends BaseCategory {
	private int mainCategoryId;
}
