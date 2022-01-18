package com.djt.mapper.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.djt.mapper.dto.UserinfoDto;
import com.djt.mapper.model.Userinfo;
import com.djt.mapper.service.IUserinfoService;

@Controller
public class IndexController {

	@Resource(name = "userinfoService")
	private IUserinfoService userinfoService;

	@RequestMapping("/index")
	public ModelAndView index() {
		UserinfoDto condition = UserinfoDto.builder().userName("苏").build();

		List<Userinfo> users = userinfoService.findUserByCondition(condition);

		ModelAndView mv = new ModelAndView("index");
		mv.addObject("users", users);

		return mv;
	}

}
