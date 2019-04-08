package com.th.app.minimart.bean;

public class FwUserBean {
	private Long fwUserPk;
	private String userId;
	private String password;
	private String createBy;
	private String updateBy;
	
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
}
