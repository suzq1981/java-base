package com.djt.mvc.security;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alibaba.druid.util.StringUtils;

@Component("imageCodeAuthenticationFilter")
public class ImageCodeAuthenticationFilter extends OncePerRequestFilter {

	@Resource(name = "securityAuthenticationFailureHandler")
	private SecurityAuthenticationFailureHandler failureHandler;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

//		if (request.getRequestURI().contains("/springLogin")) {
//			String imageCode = request.getParameter("imageCode");
//			String randomCode = request.getSession().getAttribute("RandomCode").toString();
//
//			try {
//				if (StringUtils.isEmpty(imageCode)) {
//					throw new ImageCodeAuthenticationException("验证码不能为空");
//				}
//
//				if (!StringUtils.equalsIgnoreCase(imageCode, randomCode)) {
//					throw new ImageCodeAuthenticationException("验证码不一致");
//				}
//			} catch (AuthenticationException ae) {
//				failureHandler.onAuthenticationFailure(request, response, ae);
//				return;
//			}
//		}

		filterChain.doFilter(request, response);

	}
}
