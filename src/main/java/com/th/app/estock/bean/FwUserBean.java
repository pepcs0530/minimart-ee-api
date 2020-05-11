package com.th.app.estock.bean;

import java.util.List;

import com.th.app.estock.enums.FwRoleEnum;

public class FwUserBean {
	
	private Long fwUserPk;
	private String userId;
	private String userName;
	private String password;
	private String createBy;
	private String updateBy;
	private List<FwRoleEnum> roles;
	
	public FwUserBean(){
		super();
	}
	
	public FwUserBean(String userId, String userName, String password, List<FwRoleEnum> roles) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.roles = roles;
		this.userId = userId;
	}
	
	public Long getFwUserPk() {
		return fwUserPk;
	}
	
	public void setFwUserPk(Long fwUserPk) {
		this.fwUserPk = fwUserPk;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getCreateBy() {
		return createBy;
	}
	
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
	public String getUpdateBy() {
		return updateBy;
	}
	
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	
	public List<FwRoleEnum> getRoles() {
		return roles;
	}
	
	public void setRoles(List<FwRoleEnum> roles) {
		this.roles = roles;
	}
}
