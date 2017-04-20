package interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SuperAdminInterceptor {

	@Pointcut("execution(public * dcnh.controller.SuperAdminController.* )")
	public void checkPermission() {
	}

	@Before("checkPermission()")
	public void doBefore(JoinPoint joinPoint) {

	}
}
