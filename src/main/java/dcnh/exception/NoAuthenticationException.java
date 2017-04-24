package dcnh.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
*@author WuJie
*@date 2017年4月24日上午9:42:33
*@version 1.0
**/
@ResponseStatus(code=HttpStatus.FORBIDDEN, reason="No Authentication")
public class NoAuthenticationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8185514907414378544L;

}
