package com.mycomp.dashboard.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Override
	public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		CustomUserDetails user = new CustomUserDetails();
		user.setUsername(username);
		user.setPassword("password");
		user.setFirstName("Dheren");
		user.setLastName("Jain");
		user.setFullName("Dheren Jain");
		List<Role> userRoles = new ArrayList<Role>();
		Role role = new Role();
		role.setName("ROLE_ADMIN");
		userRoles.add(role);
		user.setAuthorities(userRoles);
		return user;
	}

}
