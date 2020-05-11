package com.th.app.estock.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.th.app.estock.exception.CustomException;

public class JwtTokenFilter extends GenericFilterBean {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);
	
	private JwtTokenProvider jwtTokenProvider;
	
	public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.info("========== Enter doFilter JwtTokenFilter  ==========");
		String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
	    try {
	      if (token != null && jwtTokenProvider.validateToken(token)) {
	        Authentication auth = token != null ? jwtTokenProvider.getAuthentication(token) : null;
	        SecurityContextHolder.getContext().setAuthentication(auth);
	      }
	    } catch (CustomException ex) {
	      HttpServletResponse httpServletResponse = (HttpServletResponse) response;
	      httpServletResponse.sendError(ex.getHttpStatus().value(), ex.getMessage());
	      return;
	    }

	    chain.doFilter(request, response);
		
	}

}
