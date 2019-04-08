package com.th.app.minimart.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtTokenFilterConfigurer extends
	SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtTokenFilterConfigurer.class);

	private JwtTokenProvider jwtTokenProvider;

	public JwtTokenFilterConfigurer(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		logger.info("========== Enter configure JwtTokenFilterConfigurer  ==========");
		JwtTokenFilter customFilter = new JwtTokenFilter(jwtTokenProvider);
		http.addFilterBefore(customFilter,
				UsernamePasswordAuthenticationFilter.class);
	}
}
