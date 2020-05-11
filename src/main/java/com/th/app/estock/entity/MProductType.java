package com.th.app.estock.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="M_PRODUCT_TYPE")
public class MProductType {
	
	private Long productTypePk;
	private String productTypeName;
	
	public MProductType() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCT_TYPE_PK")
	public Long getProductTypePk() {
		return productTypePk;
	}
	
	public void setProductTypePk(Long productTypePk) {
		this.productTypePk = productTypePk;
	}
	
	@Column(name = "PRODUCT_TYPE_NAME")
	public String getProductTypeName() {
		return productTypeName;
	}
	
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	
	
}
