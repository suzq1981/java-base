package com.djt.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.djt.mvc.dto.UserinfoDto;
import com.djt.mvc.model.SysUser;
import com.djt.mvc.model.Userinfo;

@Mapper
public interface UserinfoMapper {

	/**
	 * 根据用户ID查询用户信息
	 * 
	 * @param userId 用户ID
	 * @return 用户信息
	 */
	public Userinfo findUserById(Integer userId);

	/**
	 * 根据条件查询用户信息
	 * 
	 * @param condition 查询条件
	 * @return 用户信息列表
	 */
	public List<Userinfo> findUserByCondition(UserinfoDto condition);


}
