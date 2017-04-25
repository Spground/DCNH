package dcnh.pagecontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author WuJie
 * @date 2017年4月21日下午8:56:50
 * @version 1.0
 **/
@Controller
public class OtherPageController {

	@RequestMapping("/dcnh/exception/nopermission")
	public String nopermission() {
		return "dcnh/nopermission";
	}
}
