package com.mycomp.dashboard.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class CustomAuthProvider implements AuthenticationProvider {

	//this instance can potentially be null here. check it
	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		CustomUserDetails user = userDetailsService.loadUserByUsername(authentication.getName());
		if (user == null)
			throw new BadCredentialsException("Username not found");
		if (user.getFirstName() != null) {
			return new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(),
					user.getAuthorities());
		} else
			throw new BadCredentialsException("Invalid token");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
