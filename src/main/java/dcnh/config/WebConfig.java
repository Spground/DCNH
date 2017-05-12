package dcnh.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import dcnh.interceptor.AuthenticationInterceptor;

/**
 * @author WuJie
 * @date 2017年4月21日上午10:27:23
 * @version 1.0
 **/
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// addPathPatterns 用于添加拦截规则
		registry.addInterceptor(new AuthenticationInterceptor()).addPathPatterns("/dcnh/**")
				.excludePathPatterns("/dcnh").excludePathPatterns("/dcnh/login");
		super.addInterceptors(registry);
	}

}
