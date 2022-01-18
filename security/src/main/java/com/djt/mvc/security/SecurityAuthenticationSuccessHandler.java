package com.djt.mvc.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component("securityAuthenticationSuccessHandler")
public class SecurityAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		Map result = new HashMap();
		result.put("success", true);
		result.put("user", authentication.getDetails());
		
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write(objectMapper.writeValueAsString(result));
	}


}
