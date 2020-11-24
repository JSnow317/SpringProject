package com.perscholas.SpringBootProject.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;




@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer acc_id;
	
	@Column(unique=true)
	@NotNull(message = "Field is required!")
	@Size(min = 6, max = 14, message="Username must be between 6 and 14 characters.")
	private String username;
	
	
	@NotNull(message = "Field is required!")
	@Size(min = 8, message="Email must be larger than 6 characters.")
	@Email(message="Please use proper syntax.")
	private String email;

	@Size(min = 6, message="Password must be larger than 6 characters.")
	private String password;
	
	private String role;

	
	public User() {
	}


	public User(			
			@NotNull(message = "Field is required!") @Size(min = 6, max = 14, message = "Field must be more than Six letters!") String username,
			@NotNull(message = "Field is required!") @Size(min = 6, max = 14, message = "Email must be larger than 6 characters.") String email, 
			@NotNull(message = "Field is required!") @Size(min = 6, message = "Password must be larger than 6 characters.")String password) {
		System.out.println("Constructor used");
		this.username = username;
		this.email = email;
		this.password = password;
	}


	public Integer getAcc_id() {
		return acc_id;
	}

	public void setAcc_id(Integer acc_id) {
		this.acc_id = acc_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	
	

}
