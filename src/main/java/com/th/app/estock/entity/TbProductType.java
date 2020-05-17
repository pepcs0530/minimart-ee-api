package com.th.app.estock.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_product_type database table.
 * 
 */
@Entity
@Table(name="tb_product_type")
@NamedQuery(name="TbProductType.findAll", query="SELECT t FROM TbProductType t")
public class TbProductType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_type_pk")
	private Long productTypePk;

	@Column(name="product_type_name")
	private String productTypeName;

	@Column(name="shop_type_pk")
	private int shopTypePk;

	public TbProductType() {
	}

	public Long getProductTypePk() {
		return this.productTypePk;
	}

	public void setProductTypePk(Long productTypePk) {
		this.productTypePk = productTypePk;
	}

	public String getProductTypeName() {
		return this.productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public int getShopTypePk() {
		return this.shopTypePk;
	}

	public void setShopTypePk(int shopTypePk) {
		this.shopTypePk = shopTypePk;
	}

}