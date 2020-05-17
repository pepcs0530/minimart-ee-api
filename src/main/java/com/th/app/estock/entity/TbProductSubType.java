package com.th.app.estock.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_product_sub_type database table.
 * 
 */
@Entity
@Table(name="tb_product_sub_type")
@NamedQuery(name="TbProductSubType.findAll", query="SELECT t FROM TbProductSubType t")
public class TbProductSubType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_sub_type_pk")
	private Long productSubTypePk;

	@Column(name="product_sub_type_name")
	private String productSubTypeName;

	public TbProductSubType() {
	}

	public Long getProductSubTypePk() {
		return this.productSubTypePk;
	}

	public void setProductSubTypePk(Long productSubTypePk) {
		this.productSubTypePk = productSubTypePk;
	}

	public String getProductSubTypeName() {
		return this.productSubTypeName;
	}

	public void setProductSubTypeName(String productSubTypeName) {
		this.productSubTypeName = productSubTypeName;
	}

}