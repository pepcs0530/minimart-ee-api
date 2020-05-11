package com.th.app.estock.bean;

import java.util.List;

public class MenuLabelBean {
	private String label;
	private String icon;
	private String badge;
	private String badgeStyleClass;
	private String roleId;
	private List<Items> items;
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getIcon() {
		return icon;
	}
	
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public String getBadge() {
		return badge;
	}
	
	public void setBadge(String badge) {
		this.badge = badge;
	}
	
	public String getBadgeStyleClass() {
		return badgeStyleClass;
	}
	
	public void setBadgeStyleClass(String badgeStyleClass) {
		this.badgeStyleClass = badgeStyleClass;
	}
	
	public String getRoleId() {
		return roleId;
	}
	
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	public List<Items> getItems() {
		return items;
	}
	
	public void setItems(List<Items> items) {
		this.items = items;
	}
}
