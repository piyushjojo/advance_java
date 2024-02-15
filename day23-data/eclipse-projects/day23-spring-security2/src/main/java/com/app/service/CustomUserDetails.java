package com.app.service;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.app.entities.UserEntity;

@SuppressWarnings("serial")
public class CustomUserDetails implements UserDetails {
	private UserEntity authUserDetails;

	public CustomUserDetails(UserEntity authUserDetails) {
		super();
		this.authUserDetails = authUserDetails;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//Map   role   -----> Collection : GrantedAuthority <----SimpleGrantedAuthority(String authority)		
		return Arrays.asList(new SimpleGrantedAuthority(authUserDetails.getRole().getRoleName().name()));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return authUserDetails.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return authUserDetails.getFirstName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
