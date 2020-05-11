package com.th.app.estock.service.interfaces;

import javax.servlet.http.HttpServletRequest;

import com.th.app.estock.bean.FwUserBean;
import com.th.app.estock.entity.FwUser;

public interface UserService {
	public FwUser search(String userId);
	public String signin(String userId, String password, String remoteAddr);
	public String signup(FwUserBean user);
	public void delete(String userId);
	public FwUser whoami(HttpServletRequest req);
}
