package com.th.app.estock.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.th.app.estock.bean.FwUserBean;
import com.th.app.estock.bean.MenuLabelBean;
import com.th.app.estock.bean.TokenBean;
import com.th.app.estock.entity.FwUser;
import com.th.app.estock.service.interfaces.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "${api.cross.origin}", maxAge = 3600)
public class AuthorizationController {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthorizationController.class);
	private RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping("/signin")
	public ResponseEntity<TokenBean> signin(@RequestBody com.th.app.estock.bean.FwUserBean user, HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException {
		
		logger.info("========== Enter signin AuthorizationController ==========");
		
		String token = userService.signin(user.getUserId(), user.getPassword(), request.getRemoteAddr());
		FwUser u = userService.search(user.getUserId());
		TokenBean res = new TokenBean(token, u);
		
		/*List<MenuLabelBean> roleMember = userService.getRoleMemberList(user.getUserId());
		logger.debug("[roleMember] => "+roleMember.size());*/
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonMenu = "";
		//jsonMenu = mapper.writeValueAsString(roleMember);
		//String menu = userService.getMenu( token );
		//res.setMenu(jsonMenu);
		return new ResponseEntity<TokenBean>(res, HttpStatus.OK);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<TokenBean> signup( @RequestBody FwUserBean user) {
		logger.info("========== Enter signup AuthorizationController ==========");
		String token = userService.signup(user);
		FwUser u = userService.search(user.getUserName());
		logger.info(user.toString());
		return new ResponseEntity<TokenBean>(new TokenBean(token, u), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{userId}")
	public String delete( @PathVariable String userId) {
		logger.info("========== Enter delete AuthorizationController ==========");
		userService.delete(userId);
		return userId;
	}

	@GetMapping(value = "/{userId}")
	public FwUser search(@PathVariable String userId) {
		logger.info("========== Enter search AuthorizationController ==========");
		return modelMapper.map(userService.search(userId), FwUser.class);
	}

	@GetMapping(value = "/me")
	public FwUser whoami(HttpServletRequest req) {
		logger.info("========== Enter whoami AuthorizationController ==========");
		return modelMapper.map(userService.whoami(req), FwUser.class);
	}
}
