package com.th.app.estock.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the tb_product database table.
 * 
 */
@Entity
@Table(name="tb_product")
@NamedQuery(name="TbProduct.findAll", query="SELECT t FROM TbProduct t")
public class TbProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_pk")
	private Long productPk;

	@Column(name="product_name")
	private String productName;

	@Column(name="product_sub_type")
	private int productSubType;

	@Column(name="product_type")
	private int productType;

	@Column(name="price")
	private BigDecimal price;
	
	@Column(name="remark")
	private String remark;

	@Column(name="shopTypePk")
	private int shopTypePk;

	public TbProduct() {
	}

	public Long getProductPk() {
		return this.productPk;
	}

	public void setProductPk(Long productPk) {
		this.productPk = productPk;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductSubType() {
		return this.productSubType;
	}

	public void setProductSubType(int productSubType) {
		this.productSubType = productSubType;
	}

	public int getProductType() {
		return this.productType;
	}

	public void setProductType(int productType) {
		this.productType = productType;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getShopTypePk() {
		return this.shopTypePk;
	}

	public void setShopTypePk(int shopTypePk) {
		this.shopTypePk = shopTypePk;
	}

}