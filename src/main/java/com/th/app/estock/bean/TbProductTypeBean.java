package com.th.app.estock.bean;

public class TbProductTypeBean {

	private Long productTypePk;
	private Long shopTypePk;
	private String productTypeName;
	
	public Long getProductTypePk() {
		return productTypePk;
	}
	public void setProductTypePk(Long productTypePk) {
		this.productTypePk = productTypePk;
	}
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	public Long getShopTypePk() {
		return shopTypePk;
	}
	public void setShopTypePk(Long shopTypePk) {
		this.shopTypePk = shopTypePk;
	}
}
