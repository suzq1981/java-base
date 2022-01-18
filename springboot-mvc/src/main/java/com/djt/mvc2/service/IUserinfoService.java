package com.djt.mvc2.service;

import java.util.List;

import com.djt.mvc2.dto.UserinfoDto;
import com.djt.mvc2.model.Userinfo;

public interface IUserinfoService {
	
	List<Userinfo> findUserByCondition(UserinfoDto condition);
	
	Userinfo findUserById(Integer userId);

}
