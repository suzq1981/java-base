package com.djt.mvc3.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.djt.mvc3.dto.Userinfo;

@Controller
@RequestMapping("/userinfo")
public class UserinfoController {

	@RequestMapping("/upload")
	public String upload(@ModelAttribute("user") Userinfo userinfo) throws Exception {
		if (userinfo.getFiles() != null && userinfo.getFiles().length > 0) {
			for (MultipartFile file : userinfo.getFiles()) {
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
			}
		}
		System.out.println(userinfo.getUsername());
		return "/userinfo/result";
	}

}
