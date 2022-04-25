package com.djt.mvc2.controller;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.djt.mvc2.config.SpringContextUtil;
import com.djt.mvc2.dto.UserinfoDto;
import com.djt.mvc2.model.Userinfo;
import com.djt.mvc2.service.IUserinfoService;

@Controller
public class IndexController {

	@Resource(name = "userinfoService")
	private IUserinfoService userinfoService;

	@RequestMapping(value = { "/index" })
	public ModelAndView index() {
		UserinfoDto condition = UserinfoDto.builder().userName("苏").build();

		List<Userinfo> users = userinfoService.findUserByCondition(condition);

		ModelAndView mv = new ModelAndView("index");
		mv.addObject("users", users);

		return mv;
	}
	
	@RequestMapping("/hello")
	public ModelAndView hello(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/index-bk");
		return mv;
	}

	@RequestMapping(value = "/myform", method = RequestMethod.POST)
	public String myform(UserinfoDto userinfo) {
		System.out.println(userinfo);
		return "itlike";
	}

	@RequestMapping("/convert")
	public String convert(Date date) {
		System.out.println(date);
		return "itlike";
	}
	
	@ResponseBody
	@RequestMapping(value = "/delput/{id}", method = { RequestMethod.DELETE, RequestMethod.PUT })
	public String delPut(@PathVariable int id) {
		System.out.println("**************method delete and put, id=" + id);
		return "json:true";
	}
	
	@RequestMapping("/locale")
	public String locale(Locale locale){
		System.out.println(locale);
		System.out.println(SpringContextUtil.getBean("messageSource"));
		return "locale";
	}
	
	@RequestMapping("/mylocale")
	public String local2(){
		return "locale2";
	}

}
