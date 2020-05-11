package com.th.app.estock.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FW_LOG")
public class FwLog {
	private Long fwLogPk;
	private String clientIpAddress;
	private String eventMessage;
	private String eventSts;
	private String programId;
	private String serverHostName;
	private String serverIpAddress;
	private String sessionId;
	private String tokenId;
	private String userId;
	
	private String createBy;
	private Timestamp createDate;
	
	public FwLog() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FW_LOG_PK")
	public Long getFwLogPk() {
		return fwLogPk;
	}

	public void setFwLogPk(Long fwLogPk) {
		this.fwLogPk = fwLogPk;
	}

	@Column(name="CLIENT_IP_ADDRESS")
	public String getClientIpAddress() {
		return clientIpAddress;
	}

	public void setClientIpAddress(String clientIpAddress) {
		this.clientIpAddress = clientIpAddress;
	}

	@Column(name="EVENT_MESSAGE")
	public String getEventMessage() {
		return eventMessage;
	}

	public void setEventMessage(String eventMessage) {
		this.eventMessage = eventMessage;
	}

	@Column(name="EVENT_STS")
	public String getEventSts() {
		return eventSts;
	}

	public void setEventSts(String eventSts) {
		this.eventSts = eventSts;
	}

	@Column(name="PROGRAM_ID")
	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

	@Column(name="SERVER_HOST_NAME")
	public String getServerHostName() {
		return serverHostName;
	}

	public void setServerHostName(String serverHostName) {
		this.serverHostName = serverHostName;
	}

	@Column(name="SERVER_IP_ADDRESS")
	public String getServerIpAddress() {
		return serverIpAddress;
	}

	public void setServerIpAddress(String serverIpAddress) {
		this.serverIpAddress = serverIpAddress;
	}

	@Column(name="SESSION_ID")
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@Column(name="TOKEN_ID")
	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	@Column(name="USER_ID")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name="CREATE_BY")
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Column(name="CREATE_DATE")
	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	
	
}
