package com.djt.mvc.service;

import java.util.List;

import com.djt.mvc.dto.UserinfoDto;
import com.djt.mvc.model.Userinfo;

public interface IUserinfoService {
	
	List<Userinfo> findUserByCondition(UserinfoDto condition);
	
	Userinfo findUserById(Integer userId);

}
