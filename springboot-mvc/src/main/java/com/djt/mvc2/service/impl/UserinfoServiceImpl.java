package com.djt.mvc2.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.djt.mvc2.dao.UserinfoMapper;
import com.djt.mvc2.dto.UserinfoDto;
import com.djt.mvc2.model.Userinfo;
import com.djt.mvc2.service.IUserinfoService;

@Service("userinfoService")
public class UserinfoServiceImpl implements IUserinfoService {

	@Autowired
	private UserinfoMapper userinfoMapper;

	@Override
	public List<Userinfo> findUserByCondition(UserinfoDto condition) {
		List<Userinfo> result = new ArrayList<Userinfo>();

		if (StringUtils.isEmpty(condition.getUserName())) {
			return result;
		}
		
		System.out.println(userinfoMapper);

		result = userinfoMapper.findUserByCondition(condition);

		return result;
	}

	@Override
	public Userinfo findUserById(Integer userId) {
		Userinfo userinfo = userinfoMapper.findUserById(userId);

		return userinfo;
	}

}
