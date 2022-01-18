package com.djt.mvc2.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.djt.mvc2.dto.UserinfoDto;
import com.djt.mvc2.model.Userinfo;

@Mapper
public interface UserinfoMapper {

	public Userinfo findUserById(Integer userId);

	public List<Userinfo> findUserByCondition(UserinfoDto condition);


}
