package com.djt.mvc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.djt.mvc.dao.SysUserRolePermMapper;
import com.djt.mvc.model.SysPermission;
import com.djt.mvc.model.SysUser;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private SysUserRolePermMapper mapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//
		// if (StringUtils.equals(username, "suzq")) {
		// user = new User(username, "123", true, true, false, true,
		// AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
		// }
		// if (StringUtils.equals(username, "admin")) {
		// user = new User(username, "123",
		// AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER,ROLE_ADMIN"));
		// }
		//
		// if (StringUtils.equals(username, "root")) {
		// user = new User(username, "123",
		// AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER,ROLE_ADMIN,ROLE_DBA"));
		// }

		SysUser user = mapper.findUserByUsername(username);
		
		if(user == null){
			throw new BadCredentialsException("用户名不存在");
		}
		
		if (user != null) {
			// List<SysRole> roles = mapper.findRoleByUserId(user.getUserId());
			List<SysPermission> perms = mapper.findPermissionByUsername(username);

			List<GrantedAuthority> authorities = new ArrayList<>(perms.size());
			for (SysPermission perm : perms) {
				authorities.add(new SimpleGrantedAuthority(perm.getPmsUri()));
			}

			user.setAuths(authorities);
		}

		return user;
	}
}
