package com.th.app.estock.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.th.app.estock.entity.FwUser;
import com.th.app.estock.entity.Role;
import com.th.app.estock.service.interfaces.UserService;

@Service
public class MyUserDetails implements UserDetailsService {
	
	private static final Logger logger = LoggerFactory.getLogger(MyUserDetails.class);
	
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String userId)
			throws UsernameNotFoundException {
		logger.info("========== Enter loadUserByUsername MyUserDetails  ==========");

		final FwUser user = userService.search(userId);
		if (user == null) {
			throw new UsernameNotFoundException("User '" + userId
					+ "' not found");
		}
		
		List<Role> fwRole = new ArrayList<Role>();
		return org.springframework.security.core.userdetails.User
				.withUsername(user.getUserId())
				.password(user.getPassword())
				.authorities(fwRole)
				.accountExpired(false)
				.accountLocked(false)
				.credentialsExpired(false)
				.disabled(false)
				.build();
	}
}
