package com.djt.mvc.security;

import org.springframework.security.core.AuthenticationException;

public class ImageCodeAuthenticationException extends AuthenticationException {

	private static final long serialVersionUID = -4833357283199473798L;

	public ImageCodeAuthenticationException(String msg, Throwable t) {
		super(msg, t);
	}

	public ImageCodeAuthenticationException(String msg) {
		super(msg);
	}

}
