package com.th.app.estock.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.th.app.estock.bean.TbProductTypeBean;
import com.th.app.estock.interfaces.BusinessService;
import com.th.app.estock.response.FwResponseEntity;

//@CrossOrigin(origins = "${api.cross.origin}", maxAge = 3600)
@RestController
@RequestMapping("/productType")
public class ProductTypeController {

	private static final Logger logger = LoggerFactory.getLogger(ProductTypeController.class);
	
	@Autowired
	private BusinessService businessService;
	
	@RequestMapping(value = "/getAllProductTypeList", method = RequestMethod.GET)
	public FwResponseEntity<TbProductTypeBean> getAllShopTypeList() throws Exception {
		logger.info("========== Enter getAllProductTypeList Controller ==========");
		FwResponseEntity<TbProductTypeBean> response = businessService.getAllProductTypeList();
		return response;
	}
}
