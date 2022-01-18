package com.djt.mvc2.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Userinfo {

	private Integer userId;
	private String phone;
	private String userName;
	private Date birthday;

}
