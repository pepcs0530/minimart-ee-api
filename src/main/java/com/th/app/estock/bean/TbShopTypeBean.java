package com.th.app.estock.bean;

public class TbShopTypeBean {
	private Long shopTypePk;
	private String shopName;
	
	private Integer offset;
	private Integer limit;
	private Integer count;
	public Long getShopTypePk() {
		return shopTypePk;
	}
	public void setShopTypePk(Long shopTypePk) {
		this.shopTypePk = shopTypePk;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}
