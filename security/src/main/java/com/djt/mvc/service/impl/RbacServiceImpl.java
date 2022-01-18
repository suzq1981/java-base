package com.djt.mvc.service.impl;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

@Component("rbacService")
public class RbacServiceImpl {

	private AntPathMatcher antPathMatcher = new AntPathMatcher();

	public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
		boolean hasPermission = false;
		Object principal = authentication.getPrincipal();
		if (principal instanceof UserDetails) {
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			for (GrantedAuthority grantedAuthority : authorities) {
				if (antPathMatcher.match(grantedAuthority.getAuthority(), request.getRequestURI())) {
					hasPermission = true;
					break;
				}
			}
		}
		return hasPermission;
	}

}
