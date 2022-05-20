package com.djt.mapper.service;

import java.util.List;

import com.djt.mapper.dto.UserinfoDto;
import com.djt.mapper.model.Userinfo;
import com.github.pagehelper.PageInfo;

public interface IUserinfoService {

	List<Userinfo> findUserByCondition(UserinfoDto condition);

	Userinfo findUserById(Integer userId);

	PageInfo<Userinfo> pageUserByCondition(UserinfoDto condition, int pageNum, int pageSize);

}
