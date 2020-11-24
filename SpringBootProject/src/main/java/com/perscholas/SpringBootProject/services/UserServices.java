package com.perscholas.SpringBootProject.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perscholas.SpringBootProject.dao.IUserRepo;
import com.perscholas.SpringBootProject.models.User;
import com.perscholas.SpringBootProject.security.SecurityConfiguration;

@Service
	public class UserServices{
		@Autowired
		IUserRepo userRepo;
		
		public List<User> findAll() {
			return userRepo.findAll();
		}
		
		public User findById(Integer id) {
			return userRepo.findById(id).get();
		}
		
		public boolean existsById(Integer id) {
			return userRepo.existsById(id);
		}
		
//		public boolean existsByUsername(String username) {
//			if(null == userRepo.findByUsername(username))
//				return true;
//			else
//				return false;
//		}
		
		public void deleteById(Integer id) {
			if (existsById(id))
				userRepo.deleteById(id);
		}
		
		public boolean UserExist(User user) {
			boolean UserAlreadyExists = false;
			User existingUser = userRepo.findByEmail(user.getEmail());
			// If user is found in database, then then user already exists.
			if(existingUser != null){
				UserAlreadyExists = true; 
			}
				return UserAlreadyExists;
		}
			
		
		public void save(User user) {
			user.setRole("ROLE_USER");
			user.setPassword(SecurityConfiguration.passwordEncoder().encode(user.getPassword()));
			userRepo.save(user);
		}

}
