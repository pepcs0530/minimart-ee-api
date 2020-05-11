package com.th.app.estock.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.th.app.estock.entity.FwLog;
import com.th.app.estock.repository.FwLogRepository;

public class AccessLog {
	private static final Logger logger = LoggerFactory.getLogger(AccessLog.class);
	
	public void AddLog(String userId, String clientIp, String eventSts, String eventMessage, String programId, FwLogRepository fwLogRepository){
		logger.info("========== Enter AddLog AccessLog ==========");
		try {
			Date date = Calendar.getInstance().getTime();
			Timestamp timestamp = new Timestamp(date.getTime());
			InetAddress ip = InetAddress.getLocalHost();
			FwLog entity = new FwLog();
			entity.setEventSts(eventSts);
			entity.setEventMessage(eventMessage);
			entity.setProgramId(programId); 
			entity.setUserId(userId);
			entity.setCreateBy(userId);
			entity.setCreateDate(timestamp);
			entity.setClientIpAddress(clientIp);
			entity.setServerHostName(ip.getHostName());
			entity.setServerIpAddress(ip.getHostAddress());
			entity = fwLogRepository.save(entity);
			
		} catch (UnknownHostException e) {
			//e.printStackTrace();
			logger.error("[เกิดข้อผิดพลาด] :: "+e);
		}
	}
}
