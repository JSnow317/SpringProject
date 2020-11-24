package com.perscholas.SpringBootProject.security;

import java.util.Optional;

import com.perscholas.SpringBootProject.dao.IUserRepo;
import com.perscholas.SpringBootProject.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {

	// created a query to find user by email
	@Autowired
	IUserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// find if user exist
		Optional<User> user = userRepo.findByUsername(username);
		// throw an error if not
		user.orElseThrow(() -> new UsernameNotFoundException("Not Found: " + username));
		// get me the user
		
		return user.map(MyUserDetails::new).get();
	}

}
