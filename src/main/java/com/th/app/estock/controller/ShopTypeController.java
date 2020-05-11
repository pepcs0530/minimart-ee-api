package com.th.app.estock.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.th.app.estock.bean.TbProductBean;
import com.th.app.estock.bean.TbShopTypeBean;
import com.th.app.estock.interfaces.BusinessService;
import com.th.app.estock.response.FwResponseEntity;

@CrossOrigin(origins = "${api.cross.origin}", maxAge = 3600)
@RestController
@RequestMapping("/shopType")
public class ShopTypeController {
	
	private static final Logger logger = LoggerFactory.getLogger(ShopTypeController.class);
	
	@Autowired
	private BusinessService businessService;

	@RequestMapping(value = "/getAllShopTypeList", method = RequestMethod.GET)
	public FwResponseEntity<TbShopTypeBean> getAllShopTypeList() throws Exception {
		logger.info("========== Enter getAllShopTypeList Controller ==========");
		FwResponseEntity<TbShopTypeBean> response = businessService.getAllShopTypeList();
		return response;
	}
}
