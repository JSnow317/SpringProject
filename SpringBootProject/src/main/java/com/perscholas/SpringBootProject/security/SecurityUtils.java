package com.perscholas.SpringBootProject.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public final class SecurityUtils {

	// method to find login user via email (using sessions )
	private SecurityUtils() {
	}

	public static String getUserName() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		String userName = null;
		if (authentication != null) {

			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			userName = userDetails.getUsername();

		}
		return userName;
	}

}
