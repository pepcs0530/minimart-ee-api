package com.th.app.estock.service;

import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.th.app.estock.bean.FwUserBean;
import com.th.app.estock.config.JwtTokenProvider;
import com.th.app.estock.entity.FwUser;
import com.th.app.estock.exception.CustomException;
import com.th.app.estock.repository.FwLogRepository;
import com.th.app.estock.repository.UserRepository;
import com.th.app.estock.service.interfaces.UserService;
import com.th.app.estock.utils.AccessLog;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private  FwLogRepository fwLogRepository;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	//@Autowired
	//private Persistence persistence;
	
	@PersistenceContext
	private EntityManager entityManager;

	public FwUser search(String userId) {
		FwUser user = userRepository.findByUserId(userId);
		if (user == null) {
			throw new CustomException("The user doesn't exist",
					HttpStatus.NOT_FOUND);
		}
		return user;
	}

	public String signin(String userId, String password, String remoteAddr) {
		AccessLog accessLog = new AccessLog();
		try {
			FwUser fwUser = userRepository.findByUserId(userId);
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userId, password));
			accessLog.AddLog(userId, remoteAddr, "1", "Login Successful", "", fwLogRepository);
			return jwtTokenProvider.createToken(userId);
		} catch (AuthenticationException e) {
			accessLog.AddLog(userId, remoteAddr, "0", "Login Failed", "", fwLogRepository);
			throw new CustomException("Invalid userId/password supplied",
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	public String signup(FwUserBean user) {
		if (!userRepository.existsByUserId(user.getUserId())) {
			FwUser fwUser = new FwUser();
			fwUser.setUserId(user.getUserId());
			fwUser = userRepository.save(fwUser);
			return jwtTokenProvider.createToken(user.getUserId());
			
		} else {
			throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	public void delete(String userId) {
		FwUser fwUser = userRepository.findByUserId(userId);
		userRepository.delete(fwUser);
	}

	public FwUser whoami(HttpServletRequest req) {
		return userRepository.findByUserId(jwtTokenProvider
				.getUsername(jwtTokenProvider.resolveToken(req)));
	}

}
