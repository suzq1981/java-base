package com.djt.mvc.model;

import java.util.Collection;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysUser implements UserDetails {

	private static final long serialVersionUID = -5659466562485504075L;
	
	private Integer userId;
	private String username;
	private String password;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	@Getter(AccessLevel.NONE)
	private List<GrantedAuthority> auths;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.auths;
	}

}
