package com.djt.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dba")
public class DbaController {

	@RequestMapping("/add")
	public String add() {
		return "dba/dbaAdd";
	}

}
