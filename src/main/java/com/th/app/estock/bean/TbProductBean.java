package com.th.app.estock.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class TbProductBean {

	private Long productPk;
	private Long shopTypePk;
	private Long productSubType;
	private Long productType;
	private String productName;
	private BigDecimal price;
	private String remark;
	private String imagePath;
	
	public Long getProductPk() {
		return productPk;
	}
	public void setProductPk(Long productPk) {
		this.productPk = productPk;
	}
	public Long getShopTypePk() {
		return shopTypePk;
	}
	public void setShopTypePk(Long shopTypePk) {
		this.shopTypePk = shopTypePk;
	}
	public Long getProductSubType() {
		return productSubType;
	}
	public void setProductSubType(Long productSubType) {
		this.productSubType = productSubType;
	}
	public Long getProductType() {
		return productType;
	}
	public void setProductType(Long productType) {
		this.productType = productType;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
