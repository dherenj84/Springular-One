package com.mycomp.dashboard.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycomp.dashboard.security.CustomUserDetails;

@RestController
public class DashboardController extends BaseController {

	@GetMapping("/getLoggedInUser")
	public CustomUserDetails getLoggedInUser() {
		return getLoggedInUserDetails();
	}
}
