package com.perscholas.SpringBootProject.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.perscholas.SpringBootProject.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



public class MyUserDetails implements UserDetails{

	/*
	 * Implementing methods from user details 
	 */
	private static final long serialVersionUID = 1L;
	private Integer acc_id;
	private String username;
	private String email;
	private String password;
	private String role;
	private List<GrantedAuthority> authorities;
	

	
	public MyUserDetails(User user) {
	this.acc_id = user.getAcc_id();
	this.username=user.getUsername();
	this.email=user.getEmail();
	this.password = user.getPassword();
	this.role = user.getRole();

	this.authorities = Arrays.stream(user.getRole().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		System.out.println(authorities);
		return authorities;
	}



	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}



	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
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