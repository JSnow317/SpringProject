package com.perscholas.SpringBootProject.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	/*
	 * 
	 * Spring Security Config file
	 * 
	 */

	// wire services needed
	@Autowired
	UserDetailsService userDetailsService;
	
	@Bean
	public AuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(this.userDetailsService);
		return provider;
	}
	@Bean
	public static PasswordEncoder passwordEncoder() {
		PasswordEncoder defaultEncoder = new BCryptPasswordEncoder();// bcrypt as default
		String idForEncode = "bcrypt";
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		// add each type of encryption to the map
		encoders.put(idForEncode, new BCryptPasswordEncoder());// fast but not very secure
		encoders.put("noop", NoOpPasswordEncoder.getInstance());// plaintext no encryption
		encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
		encoders.put("scrypt", new SCryptPasswordEncoder());// very secure but large computational demand
		encoders.put("sha256", new StandardPasswordEncoder());
		DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(idForEncode, encoders);
		passwordEncoder.setDefaultPasswordEncoderForMatches(defaultEncoder);// set a default
		return passwordEncoder;
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		auth.inMemoryAuthentication().withUser("spring").password(encoder.encode("spring")).roles("ADMIN");
	}

	// method to config user details class
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		//PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//
//		//auth.inMemoryAuthentication().withUser("admin").password(encoder.encode("admin")).roles("ADMIN");
//		auth.userDetailsService(userDetailsService);
//		
//
//	}

//	// method to encode password in db
//	@Bean
//	public DaoAuthenticationProvider daoAuthenticationProvider() {
//		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//		authenticationProvider.setUserDetailsService(userDetailsService);
//		authenticationProvider.setPasswordEncoder(getPasswordEncoder());
//		return authenticationProvider;
//	}
//
//	// method to instantiate new object of BCrypt
//	@Bean
//	public BCryptPasswordEncoder getPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
	

	// main config method to allow users to which page
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers("/shop", "/shoppingCart").hasRole("USER").antMatchers("/", "/login").permitAll();
//				.antMatchers("/index")
//				.hasAnyRole("ADMIN", "USER")
				http.authorizeRequests()
				.and()
				
				.httpBasic()

				.and()
				
				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/")
				
				.and()
				
				.exceptionHandling().accessDeniedPage("/accessdenied")
				
				.and()
				
				.formLogin().loginPage("/login").loginProcessingUrl("/login/authenticate").permitAll()
				.failureUrl("/accessdenied").defaultSuccessUrl("/index").usernameParameter("username")
				.passwordParameter("password")
				
				.and()

				.csrf().disable();


				
	}

	// pages not to follow the security
	@Override
	public void configure(final WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}

}
