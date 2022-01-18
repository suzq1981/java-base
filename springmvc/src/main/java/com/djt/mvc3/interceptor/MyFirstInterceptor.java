package com.djt.mvc3.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyFirstInterceptor implements HandlerInterceptor {
	
	/**
	 * 在调用controller之前拦截
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("AAAAAAAAAAAAAAAAAAAAAAA=======preHandle");
		return true;
	}

	/**
	 * 在调用controller之后拦截
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("BBBBBBBBBBBBBBBBBBBBBB=======postHandle");
	}

	/**
	 * 在请求处理完成，页面渲染之后
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("CCCCCCCCCCCCCCCCCCCCCC=======afterCompletion");
	}

}
