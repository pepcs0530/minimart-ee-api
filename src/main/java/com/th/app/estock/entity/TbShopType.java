package com.th.app.estock.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_shop_type database table.
 * 
 */
@Entity
@Table(name="tb_shop_type")
@NamedQuery(name="TbShopType.findAll", query="SELECT t FROM TbShopType t")
public class TbShopType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="shop_type_pk")
	private Long shopTypePk;

	@Column(name="shop_name")
	private String shopName;

	public TbShopType() {
	}

	public Long getShopTypePk() {
		return this.shopTypePk;
	}

	public void setShopTypePk(Long shopTypePk) {
		this.shopTypePk = shopTypePk;
	}

	public String getShopName() {
		return this.shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

}