package com.djt.mvc3.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.djt.mvc3.dto.EmployeeDto;

@Controller
public class HelloController {

	@RequestMapping(value = { "/first", "/hello" })
	public ModelAndView show(Model model) {
		ModelAndView mv = new ModelAndView();

		mv.addObject("username", "God's son");
		mv.setViewName("itlike");
		System.out.println(model);

		return mv;
	}

	@RequestMapping("/second")
	public String show2(HttpServletRequest request) {
		String empId = request.getParameter("empId");
		System.out.println(empId);
		return "itlike";
	}

	@RequestMapping("/third")
	public String show3(@RequestParam(value = "empId", required = false) Integer id, String empName) {
		System.out.println(id);
		System.out.println(empName);
		return "itlike";
	}

	@RequestMapping("/myform")
	public String myform(EmployeeDto emp) {
		System.out.println(emp);
		return "itlike";
	}

	@RequestMapping("/myform2")
	public String myform2(String[] hobbies) {
		System.out.println(Arrays.toString(hobbies));
		return "itlike";
	}

	@RequestMapping("/exception")
	public String exception() {
		int j = 1 / 0;
		System.out.println(j);
		return "itlike";
	}
	
	@RequestMapping("/locale")
	public String locale(){
		System.out.println("locale.......................");
		return "locale";
	}
	
	@RequestMapping("/locale2")
	public String local2(){
		return "locale2";
	}

}
