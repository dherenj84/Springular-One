package com.mycomp.dashboard.security;

import java.util.Collection;
import java.util.List;

import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;

public class LdapUserDetailsMapper implements UserDetailsContextMapper {
	@SuppressWarnings("unchecked")
	@Override
	public UserDetails mapUserFromContext(DirContextOperations ctx, String username,
			Collection<? extends GrantedAuthority> authorities) {
		CustomUserDetails userDetails = new CustomUserDetails();
		userDetails.setFullName((String) ctx.getObjectAttribute("cn"));
		userDetails.setLastName((String) ctx.getObjectAttribute("sn"));
		userDetails.setUsername(username);
		userDetails.setAuthorities((List<Role>) authorities);
		return userDetails;
	}

	@Override
	public void mapUserToContext(UserDetails arg0, DirContextAdapter arg1) {
		// TODO Auto-generated method stub

	}

}
