package com.th.app.estock.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class TbProductBean {

	private Long productPk;
	private String productBarcode;
	private String productName;
	private String productDesc;
	private String productType;
	private BigDecimal price;
	private BigDecimal quantity;
	private Timestamp storeDate;
	private Timestamp expDate;
	
	private Integer offset;
	private Integer limit;
	private Integer count;
	private String createBy;
	private String updateBy;
	
	public Long getProductPk() {
		return productPk;
	}
	
	public void setProductPk(Long productPk) {
		this.productPk = productPk;
	}
	
	public String getProductBarcode() {
		return productBarcode;
	}
	
	public void setProductBarcode(String productBarcode) {
		this.productBarcode = productBarcode;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getProductDesc() {
		return productDesc;
	}
	
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	
	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public BigDecimal getQuantity() {
		return quantity;
	}
	
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
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

	public Timestamp getStoreDate() {
		return storeDate;
	}

	public void setStoreDate(Timestamp storeDate) {
		this.storeDate = storeDate;
	}

	public Timestamp getExpDate() {
		return expDate;
	}

	public void setExpDate(Timestamp expDate) {
		this.expDate = expDate;
	}
	
}
