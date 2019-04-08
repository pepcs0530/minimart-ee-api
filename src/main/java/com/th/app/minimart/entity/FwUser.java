package com.th.app.minimart.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="FW_USER")
public class FwUser implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long fwUserPk;
	private String userId;
	private String password;
	private String createBy;
	private Timestamp createDate;
	private String updateBy;
	private Timestamp updateDate;
	private Long version = 1L;
	
	public FwUser() {
	}
	
	@Id
	//@SequenceGenerator(name = "FW_USER_PK_GENERATOR", sequenceName = "FW_USER_PK", allocationSize = 1)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FW_USER_PK_GENERATOR")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "FW_USER_PK")
	public Long getFwUserPk() {
		return this.fwUserPk;
	}
	
	public void setFwUserPk(Long fwUserPk) {
		this.fwUserPk = fwUserPk;
	}
	
	@Column(name = "USER_ID")
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Column(name = "PASSWORD")
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name="CREATE_BY")
	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Column(name="CREATE_DATE")
	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}	
	
	@Column(name="UPDATE_BY")
	public String getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	@Column(name="UPDATE_DATE")
	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	
	@Version
	@Column(name="VERSION")
	public Long getVersion() {
		return this.version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}
