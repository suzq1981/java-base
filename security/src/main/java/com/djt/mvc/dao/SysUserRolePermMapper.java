package com.djt.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.djt.mvc.model.SysPermission;
import com.djt.mvc.model.SysRole;
import com.djt.mvc.model.SysUser;

@Mapper
public interface SysUserRolePermMapper {

	/**
	 * 根据用户名获取用户信息
	 * @param username 用户名
	 * @return
	 */
	public SysUser findUserByUsername(String username);
	
	/**
	 * 根据用户ID，获取用户所有角色
	 * @param userId
	 * @return
	 */
	public List<SysRole> findRoleByUserId(Integer userId);
	
	/**
	 * 根据用户名获取用户权限
	 * @param username 用户名
	 * @return
	 */
	public List<SysPermission> findPermissionByUsername(String username);
	
	/**
	 * 根据用户名修改密码
	 * 
	 * @param user 用户信息
	 */
	public void updateUserPassword(SysUser user);
}
