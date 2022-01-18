package com.djt.mvc.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.djt.mvc.SpringMVCApplication;
import com.djt.mvc.dao.SysUserRolePermMapper;
import com.djt.mvc.model.SysUser;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringMVCApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestSysUserRolePermMapper {
	
	@Autowired
	private SysUserRolePermMapper urpMapper;
	
	private PasswordEncoder pe = new BCryptPasswordEncoder();

	@Test
	public void testUpdatePassword() {
		SysUser user = SysUser.builder().username("root").password(pe.encode("123")).build();
		urpMapper.updateUserPassword(user);
	}

}
