package com.djt.mapper.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.djt.mapper.dao.UserinfoMapper;
import com.djt.mapper.dto.UserinfoDto;
import com.djt.mapper.model.Userinfo;
import com.djt.mapper.service.IUserinfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

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

		result = userinfoMapper.findUserByCondition(condition);

		return result;
	}

	@Override
	public Userinfo findUserById(Integer userId) {
		Userinfo userinfo = userinfoMapper.findUserById(userId);

		return userinfo;
	}

	@Override
	public PageInfo<Userinfo> pageUserByCondition(UserinfoDto condition, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);

		if (StringUtils.isEmpty(condition.getUserName())) {
			return new PageInfo<>();
		}

		List<Userinfo> list = userinfoMapper.findUserByCondition(condition);

		return new PageInfo<>(list, pageSize);
	}

}
