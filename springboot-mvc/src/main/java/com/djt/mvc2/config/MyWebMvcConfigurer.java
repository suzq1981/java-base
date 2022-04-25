package com.djt.mvc2.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.djt.mvc2.interceptor.MyFirstInterceptor;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

	@Value("${spring.messages.basename}")
	private String basename;

	/**
	 * 在没有具体获取数据的业务代码，只是请求页面，不用在Controller中写具体的方法映射对应的视图 路径，
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/user").setViewName("/user/index");
		registry.addViewController("/viewc").setViewName("view_controller");
		registry.addViewController("/viewc2").setViewName("view_controller2");
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		// 开启路径后缀匹配
		configurer.setUseRegisteredSuffixPatternMatch(true);
	}

	//国际化
	@Bean("messageSource")
	public ResourceBundleMessageSource initMessageSource() {
		System.out.println("basename: " + basename);
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename(basename);

		return messageSource;
	}

	//中英文切换
	@Bean("localeResolver")
	public SessionLocaleResolver initSessionLocaleResolver() {
		return new SessionLocaleResolver();
	}

	//中英文切换
	@Bean
	public LocaleChangeInterceptor initLocaleChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName(basename);

		return localeChangeInterceptor;
	}

	@Bean
	public MyFirstInterceptor initMyFirstInterceptor() {
		return new MyFirstInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//中英文切换
		registry.addInterceptor(initLocaleChangeInterceptor()).addPathPatterns("/locale*");
		registry.addInterceptor(initMyFirstInterceptor());
	}

	//
	// /**
	// * 设置匹配.action后缀的请求
	// *
	// * @param dispatcherServlet
	// * @return
	// */
	// @Bean
	// public ServletRegistrationBean servletRegistrationBean(DispatcherServlet
	// dispatcherServlet) {
	// ServletRegistrationBean bean = new
	// ServletRegistrationBean(dispatcherServlet);
	// bean.addUrlMappings("*.action");
	// return bean;
	// }

}
