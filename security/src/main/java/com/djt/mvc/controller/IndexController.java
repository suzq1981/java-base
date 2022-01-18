package com.djt.mvc.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.djt.mvc.service.IUserinfoService;
import com.djt.mvc.utils.PictureVerification;

@Controller
public class IndexController {

	@Resource(name = "userinfoService")
	private IUserinfoService userinfoService;

	@Autowired
	private PictureVerification pictureVerification;

	@RequestMapping("/index")
	public ModelAndView index() {
		// UserinfoDto condition = UserinfoDto.builder().userName("苏").build();

		// List<Userinfo> users =
		// userinfoService.findUserByCondition(condition);

		ModelAndView mv = new ModelAndView("index");
		// mv.addObject("users", users);

		return mv;
	}

	@RequestMapping("/loginPage")
	public String loginPage() {
		return "login";
	}

	@RequestMapping("/denied")
	public ModelAndView error() {
		ModelAndView mv = new ModelAndView("denied");
		return mv;
	}

	@RequestMapping("/imageCode")
	public void imageCode(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("img/png");
		// 设置响应头信息，通知浏览器不要缓存数据
		response.setHeader("Pragma", "No-catch");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0);
		pictureVerification.createImage(request, response);
	}

}
