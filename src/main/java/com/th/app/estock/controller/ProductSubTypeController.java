package com.th.app.estock.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.th.app.estock.bean.TbProductSubTypeBean;
import com.th.app.estock.interfaces.BusinessService;
import com.th.app.estock.response.FwResponseEntity;

@CrossOrigin(origins = "${api.cross.origin}", maxAge = 3600)
@RestController
@RequestMapping("/productSubType")
public class ProductSubTypeController {

	private static final Logger logger = LoggerFactory.getLogger(ProductSubTypeController.class);
	
	@Autowired
	private BusinessService businessService;
	
	@RequestMapping(value = "/getAllProductSubTypeList", method = RequestMethod.GET)
	public FwResponseEntity<TbProductSubTypeBean> getAllProductSubTypeList() throws Exception {
		logger.info("========== Enter getAllProductSubTypeList Controller ==========");
		FwResponseEntity<TbProductSubTypeBean> response = businessService.getAllProductSubTypeList();
		return response;
	}
	
}
