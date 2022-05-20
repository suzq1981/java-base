package com.djt.mapper.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.djt.mapper.SpringMapperApplication;
import com.djt.mapper.dto.UserinfoDto;
import com.djt.mapper.model.Userinfo;
import com.djt.mapper.service.IUserinfoService;
import com.github.pagehelper.PageInfo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringMapperApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestUserinfoService {

	@Autowired
	private IUserinfoService userinfoService;
	
	@Test
	public void pageUserByCondition(){
		UserinfoDto condition = UserinfoDto.builder().userName("苏").build();
		PageInfo<Userinfo> pageInfo = userinfoService.pageUserByCondition(condition, 3, 5);
		System.out.println("XXXXXXXXXXX: " + pageInfo);
	}
}
