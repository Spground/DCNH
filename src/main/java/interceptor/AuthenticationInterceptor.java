package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import dcnh.global.SessionKey;
import lombok.extern.slf4j.Slf4j;

/**
*@author WuJie
*@date 2017年4月21日上午9:42:54
*@version 1.0
**/
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		 HttpSession session = request.getSession();
		 System.out.println(request.getRequestURI());
		 if(session.getAttribute(SessionKey.USERNAME.name()) == null) {
			 log.warn("####access failed: beacause you do not login####");
			 response.sendRedirect(request.getContextPath()+"/dcnh");
			 return false;
		 } else
			 return true;
	}
}
