package com.djt.mvc2.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.djt.mvc2.dto.User;

@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping("/load/{id}")
	public String load(@PathVariable Integer id, Model model) {
		User user = User.builder().id(id).username("苏忠清").age(40).email("suzq1106@qq.com").phone("18559696092").build();
		model.addAttribute("user", user);

		return "/user/update";
	}

	@RequestMapping("/update")
	public String update(@Valid User user, BindingResult result) {

		if (result.getErrorCount() > 0) {
			return "/user/update";
		}

		System.out.println(user);

		return "/user/result";
	}

	@RequestMapping("/put")
	@ResponseBody
	public String put(@RequestBody User user) {
		System.out.println(user);
		return "success:true";
	}

	@RequestMapping("/get/{id}")
	@ResponseBody
	public List<User> get(@PathVariable Integer id) {
		List<User> users = new ArrayList<User>();
		users.add(User.builder().id(id).username("苏忠清").age(40).email("suzq1106@qq.com").phone("18559696092").build());
		users.add(User.builder().id(id).username("张娟").age(37).email("zhangjuan@qq.com").phone("17759309008").build());

		return users;
	}

	@RequestMapping("/upload")
	public ModelAndView upload(@RequestParam("file") MultipartFile file,User user) throws Exception {
		if (!file.isEmpty()) {
			// 获取文件存储路径（绝对路径）
			String path = "D:\\test";
			// 获取原文件名
			String fileName = file.getOriginalFilename();
			// 创建文件实例
			File filePath = new File(path, fileName);
			// 如果文件目录不存在，创建目录
			if (!filePath.getParentFile().exists()) {
				filePath.getParentFile().mkdirs();
				System.out.println("创建目录" + filePath);
			}
			// 写入文件
			file.transferTo(filePath);
		}
		
		System.out.println(user);
		ModelAndView mv = new ModelAndView();
		mv.addObject("username", "God's son: upload file.");
		mv.setViewName("/result");
		return mv;
	}

}
