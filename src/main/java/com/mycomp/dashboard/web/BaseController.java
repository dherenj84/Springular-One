package com.mycomp.dashboard.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import com.mycomp.dashboard.security.CustomUserDetails;
import com.mycomp.dashboard.security.CustomUserDetailsService;

@RestController
public class BaseController {

	@Autowired
	CustomUserDetailsService userDetailsService;

	public CustomUserDetails getLoggedInUserDetails() {
		CustomUserDetails userDetails = new CustomUserDetails();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof CustomUserDetails)
			userDetails = (CustomUserDetails) principal;
		return userDetails;
	}
}
