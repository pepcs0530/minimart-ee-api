package com.th.app.estock.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class TbHistoryBean {
	private Long historyPk;
	private String productBarcode;
	private String productName;
	private BigDecimal cost;
	private BigDecimal price;
	private BigDecimal amount;
	private Timestamp saleDate;
	
	private Integer offset;
	private Integer limit;
	private Integer count;
	private String createBy;
	
	private String startDate;
	private String endDate;
	
	public Long getHistoryPk() {
		return historyPk;
	}
	
	public void setHistoryPk(Long historyPk) {
		this.historyPk = historyPk;
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
	
	public BigDecimal getCost() {
		return cost;
	}
	
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public Timestamp getSaleDate() {
		return saleDate;
	}
	
	public void setSaleDate(Timestamp saleDate) {
		this.saleDate = saleDate;
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}

