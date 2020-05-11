package com.th.app.estock.config;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.th.app.estock.exception.CustomException;
import com.th.app.estock.service.MyUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Component
public class JwtTokenProvider {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
	
	@Value("${security.jwt.token.secret-key:secret-key}")
	private String secretKey;

	@Value("${security.jwt.token.expire-length:999999999}")
	private long validityInMilliseconds = 999999999; // 1h

	@Autowired
	private MyUserDetails myUserDetails;

	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}
	
	public String createToken(String userId) {
		logger.info("========== Enter createToken JwtTokenProvider  ==========");
		Claims claims = Jwts.claims().setSubject(userId);
		List<SimpleGrantedAuthority> list = new LinkedList<SimpleGrantedAuthority>();
		claims.put("auth", list);
		Date now = new Date();
		Date validity = new Date(now.getTime() + validityInMilliseconds);
		
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(validity); 
		c.add(Calendar.DATE, 1);
		dt = c.getTime();

		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(dt)
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
	}

	public Authentication getAuthentication(String token) {
		logger.info("========== Enter getAuthentication JwtTokenProvider  ==========");
		UserDetails userDetails = myUserDetails
				.loadUserByUsername(getUsername(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "",
				userDetails.getAuthorities());
	}

	public String getUsername(String token) {
		logger.info("========== Enter getUsername JwtTokenProvider  ==========");
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
				.getBody().getSubject();
	}

	public String resolveToken(HttpServletRequest req) {
		logger.info("========== Enter resolveToken JwtTokenProvider  ==========");
		String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

	public boolean validateToken(String token) {
		logger.info("========== Enter validateToken JwtTokenProvider  ==========");
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			throw new CustomException("Expired or invalid JWT token",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
