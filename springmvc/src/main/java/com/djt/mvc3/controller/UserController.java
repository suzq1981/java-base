package com.djt.mvc3.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.djt.mvc3.dto.User;

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
	public ModelAndView upload(User user) throws Exception {
		MultipartFile file = user.getFile();
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

	/**
	 * 小文件下载
	 */
	@RequestMapping("/download2/{fileName:.+}")
	public ResponseEntity<byte[]> download2(@PathVariable String fileName) throws Exception {
		// 1. 获取文件路径
		String realPath = "D:\\test\\" + fileName;

		// 2. 把文件读到程序中
		InputStream fis = new FileInputStream(realPath);
		byte[] body = new byte[fis.available()];
		fis.read(body);

		fis.close();

		// 3.创建响应头
		fileName = URLEncoder.encode(fileName, "UTF-8");
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-Disposition", "attachment;filename=" + fileName);

		ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(body, headers, HttpStatus.OK);

		return responseEntity;
	}

	// 大文件下载
	@RequestMapping("/download/{dir}/{fileName:.+}")
	public void download(@PathVariable("dir") String dir, @PathVariable("fileName") String fileName,
			HttpServletResponse response) throws Exception {
		// 1. 获取文件路径
		String realPath = "D:\\test\\" + fileName;

		// 2. 设置响应头
		response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
		InputStream is = null;
		try {
			// 读取文件以流的形式响应
			is = new FileInputStream(realPath);
			OutputStream os = response.getOutputStream();

			int read = 0;
			byte[] body = new byte[1024 * 100];

			while ((read = is.read(body)) != -1) {
				os.write(body, 0, read);
			}
		} finally {
			if (is != null) {
				is.close();
			}
		}

	}
}
