package dcnh.mode;

import java.util.LinkedList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MainCategory extends BaseCategory {
	private List<SubCategory> allsubCategorys = new LinkedList<SubCategory>();
	private int proportion;

}
