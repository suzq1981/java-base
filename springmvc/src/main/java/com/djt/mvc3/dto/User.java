package com.djt.mvc3.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

	private Integer id;
	@NotBlank(message = "用户名不能为空")
	private String username;
	@Max(value = 200, message = "请输入合法年龄")
	@Min(value = 0, message = "年龄不能小于0岁")
	private Integer age;
	@Email(message = "请输入合法的邮箱地址")
	private String email;
	@Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$", message = "请输入正确手机号")
	private String phone;
	private String[] hobby;
	MultipartFile file;

}
