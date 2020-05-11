package com.th.app.estock.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.th.app.estock.bean.TbHistoryBean;
import com.th.app.estock.bean.TbProductBean;
import com.th.app.estock.interfaces.BusinessService;
import com.th.app.estock.response.FwResponseEntity;

@CrossOrigin(origins = "${api.cross.origin}", maxAge = 3600)
@RestController
@RequestMapping("/history")
public class HistoryController {
private static final Logger logger = LoggerFactory.getLogger(HistoryController.class);
	
	@Autowired
	private BusinessService businessService;
	
	@RequestMapping(value = "/addHistoryList", method = RequestMethod.POST)
	public FwResponseEntity<TbHistoryBean> addHistoryList(@RequestBody List<TbHistoryBean> TbHistoryList) throws Exception {
		logger.info("========== Enter addHistoryList Controller ==========");
		System.out.println("TbHistoryList => "+TbHistoryList);
		FwResponseEntity<TbHistoryBean> response = businessService.addHistoryList(TbHistoryList);
		return response;
	}
	
	@RequestMapping(value = "/getHistoryListByCondition", method = RequestMethod.POST)
	public FwResponseEntity<TbHistoryBean> getHistoryListByCondition(@RequestBody TbHistoryBean tbHistoryBean) throws Exception {
		logger.info("========== Enter getProductListByCondition Controller ==========");
		FwResponseEntity<TbHistoryBean> response = businessService.getHistoryListByCondition(tbHistoryBean);
		return response;
	}
}
