package com.th.app.estock.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TB_PRODUCT")
public class TbProduct extends AbstractAudit {
	private Long productPk;
	private String productBarcode;
	private String productName;
	private String productDesc;
	private String productType;
	private BigDecimal price;
	private BigDecimal quantity;
	private Timestamp storeDate;
	private Timestamp expDate;
	
	public TbProduct() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCT_PK")
	public Long getProductPk() {
		return productPk;
	}
	
	public void setProductPk(Long productPk) {
		this.productPk = productPk;
	}
	
	@Column(name = "PRODUCT_BARCODE")
	public String getProductBarcode() {
		return productBarcode;
	}
	
	public void setProductBarcode(String productBarcode) {
		this.productBarcode = productBarcode;
	}
	
	@Column(name = "PRODUCT_NAME")
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	@Column(name = "PRODUCT_DESC")
	public String getProductDesc() {
		return productDesc;
	}
	
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	
	@Column(name = "PRODUCT_TYPE")
	public String getProductType() {
		return productType;
	}
	
	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	@Column(name = "PRICE")
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	@Column(name = "QUANTITY")
	public BigDecimal getQuantity() {
		return quantity;
	}
	
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	@Column(name = "STORE_DATE")
	public Timestamp getStoreDate() {
		return storeDate;
	}

	public void setStoreDate(Timestamp storeDate) {
		this.storeDate = storeDate;
	}

	@Column(name = "EXP_DATE")
	public Timestamp getExpDate() {
		return expDate;
	}

	public void setExpDate(Timestamp expDate) {
		this.expDate = expDate;
	}
}
