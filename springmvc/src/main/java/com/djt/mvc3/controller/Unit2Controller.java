package com.djt.mvc3.controller;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/unit2")
public class Unit2Controller {

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "unit2/index";
	}

	@RequestMapping("/convert")
	public ModelAndView convert(String age, Date date) {
		System.out.println(age);
		System.out.println(date);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("unit2/result");

		return mv;
	}

	@RequestMapping(value = "/headers", headers = { "Referer=http://localhost:8080/mvc3/unit2" })
	public ModelAndView headers() {
		System.out.println("...........headers..........");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("unit2/result");

		return mv;
	}

	@RequestMapping("/antpath/*/invoke")
	public ModelAndView antPath() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("unit2/result");
		System.out.println("ant path.....");
		return mv;
	}

	@RequestMapping("/rest/{id}")
	public String rest(@PathVariable int id) {
		System.out.println(id);
		System.out.println("rest path....");
		return "unit2/result";
	}

	@RequestMapping(value = "/delput/{id}", method = { RequestMethod.DELETE, RequestMethod.PUT })
	public String delPut(@PathVariable int id) {
		System.out.println("**************method delete and put, id=" + id);
		// return "redirect:/unit2/location";
		return "unit2/result";// 设置result.jsp isErrorPage="true"
	}

	@RequestMapping("/location")
	public String location() {
		return "unit2/result";
	}

	@RequestMapping("/reqheader")
	public String testRequestHeader(@RequestHeader("Host") String host, @RequestHeader("User-Agent") String agent,
			HttpServletResponse response) {
		System.out.println("test request header..." + agent);
		Cookie token = new Cookie("token", "87390376620");
		token.setPath("/");
		response.addCookie(token);

		return "unit2/result";
	}

	@RequestMapping("/cookie")
	public String testCookie(@CookieValue("token") String token) {
		System.out.println(token);
		return "unit2/result";
	}

}
