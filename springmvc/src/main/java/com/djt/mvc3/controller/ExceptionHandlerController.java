package com.djt.mvc3.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception ex) {
		System.out.println(ex.getMessage());
		
		return "/exception";
	}

}
