package com.mycomp.dashboard.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class LdapAuthProvider implements AuthenticationProvider {

	private static final Logger log = LoggerFactory.getLogger(LdapAuthProvider.class);

	private AttributesMapper<Map<String, List<String>>> ldapMapper;

	@Value("${ldap.host}")
	private String host;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	private String DOMAIN = "mycomp.com";
	// Search both the Remote and Local LDAP tree
	private String[] BASE = { "OU=mycomp Users,DC=mycomp,DC=com",
			"CN=wikiuser,OU=Local Users,OU=mycomp Users,DC=mycomp,DC=com" };

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		CustomUserDetails user = new CustomUserDetails();
		Attributes ldapAttr = authenticateUser(authentication.getName(), (String) authentication.getCredentials());
		if (ldapAttr != null) {
			user.setUsername(authentication.getName());
			user.setFullName(ldapAttr.get("cn").toString());
			try {
				Map<String, List<String>> attrMap = ldapMapper.mapFromAttributes(ldapAttr);
				user.setFullName(attrMap.get("displayName").get(0));
				user.setAuthorities(extractAuthorities(attrMap.get("memberOf")));
				user.setEmail(attrMap.get("mail").get(0));
			} catch (NamingException e) {
				log.error("unable to load ldap attributes for the logged in user");
			}
		} else
			throw new BadCredentialsException("Username not found");
		return new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(), user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	private Attributes authenticateUser(String username, String password) {
		Attributes attributes = null;
		// Create the search controls
		String returnedAtts[] = { "memberOf", "displayName", "mail", "cn" };
		String searchFilter = "(&(objectClass=user)(sAMAccountName=" + username + "))";

		SearchControls searchControls = new SearchControls();
		searchControls.setReturningAttributes(returnedAtts);

		// Specify the search scope
		searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		Hashtable<String, String> environment = new Hashtable<String, String>();

		// Use Sun LDAP Provider
		environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");

		// Use starndard Port, check your instalation
		environment.put(Context.PROVIDER_URL, "ldap://" + host + ":389");
		environment.put(Context.SECURITY_AUTHENTICATION, "simple");
		environment.put(Context.SECURITY_PRINCIPAL, username + "@" + DOMAIN);
		environment.put(Context.SECURITY_CREDENTIALS, password);

		LdapContext ldapContext = null;
		try {
			ldapContext = new InitialLdapContext(environment, null);

			// Perform the search
			NamingEnumeration enums = null;
			for (String searchBase : BASE) {
				enums = ldapContext.search(searchBase, searchFilter, searchControls);
				if (enums.hasMore()) {
					log.debug("User found in LDAP base: " + searchBase);
					break;
				} else {
					log.debug("User not found in LDAP base: " + searchBase);
				}
			}
			while (enums.hasMoreElements()) {
				SearchResult searchResult = (SearchResult) enums.next();
				attributes = searchResult.getAttributes();
			}
		} catch (javax.naming.CommunicationException ce) {
			log.error("*** LDAP server maybe unavailable: " + ce.toString());
		} catch (Exception e) {
			log.error("*** " + e.toString());
		} finally {
			try {
				ldapContext.close();
			} catch (Exception e) {
			}
		}

		return attributes;
	}

	@PostConstruct
	public void init() {
		ldapMapper = new AttributesMapper<Map<String, List<String>>>() {
			@Override
			public Map<String, List<String>> mapFromAttributes(Attributes attributes) throws NamingException {
				Map<String, List<String>> attrsMap = new HashMap<String, List<String>>();
				NamingEnumeration<String> attrIdEnum = attributes.getIDs();
				while (attrIdEnum.hasMoreElements()) {
					// Get attribute id:
					String attrId = attrIdEnum.next();
					// Get all attribute values:
					Attribute attr = attributes.get(attrId);
					NamingEnumeration<?> attrValuesEnum = attr.getAll();
					while (attrValuesEnum.hasMore()) {
						if (!attrsMap.containsKey(attrId))
							attrsMap.put(attrId, new ArrayList<String>());
						attrsMap.get(attrId).add(attrValuesEnum.next().toString());
					}
				}
				return attrsMap;
			}
		};
	}

	private List<Role> extractAuthorities(List<String> authorityAttrs) {
		List<Role> authorities = new ArrayList<Role>();
		for (String authAttrRec : authorityAttrs) {
			for (String authAttr : authAttrRec.split(",")) {
				if (authAttr.startsWith("CN=")) {
					Role authority = new Role();
					authority.setName(authAttr.split("=")[1]);
					authorities.add(authority);
				}
			}
		}
		return authorities;
	}

}
