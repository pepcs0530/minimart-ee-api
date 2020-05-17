package com.th.app.estock.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.th.app.estock.bean.TbProductBean;
import com.th.app.estock.interfaces.BusinessService;
import com.th.app.estock.request.FwRequestData;
import com.th.app.estock.response.FwResponseEntity;

@RestController
@RequestMapping("/product")
public class ProductController {

private static final Logger logger = LoggerFactory.getLogger(ProductController	.class);
	
	@Autowired
	private BusinessService businessService;
	
	@RequestMapping(value = "/getProductListByCondition", method = RequestMethod.POST)
	public FwResponseEntity<TbProductBean> getProductListByCondition(@RequestBody FwRequestData<TbProductBean> tbProductBean) throws Exception {
		logger.info("========== Enter getProductListByCondition Controller ==========");
		FwResponseEntity<TbProductBean> response = businessService.getProductListByCondition(tbProductBean);
		return response;
	}
	
}
