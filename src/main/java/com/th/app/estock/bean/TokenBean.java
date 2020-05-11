package com.th.app.estock.bean;

import com.th.app.estock.entity.FwUser;

public class TokenBean {
	private String token;
	private FwUser userInfo;
	private String menu;
	
	public TokenBean() {
	}
	
	public TokenBean(String token, FwUser userInfo) {
		super();
		this.token = token;
		this.userInfo = userInfo;
	}
	
	public TokenBean(String token, FwUser userInfo, String menu ) {
		super();
		this.token = token;
		this.userInfo = userInfo;
		this.menu = menu;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public FwUser getUserInfo() {
		return userInfo;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public void setUserInfo(FwUser userInfo) {
		this.userInfo = userInfo;
	}
}
