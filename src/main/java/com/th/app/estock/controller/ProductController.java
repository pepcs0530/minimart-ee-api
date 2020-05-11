package com.th.app.estock.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.th.app.estock.bean.TbProductBean;
import com.th.app.estock.interfaces.BusinessService;
import com.th.app.estock.response.FwResponseEntity;

@CrossOrigin(origins = "${api.cross.origin}", maxAge = 3600)
@RestController
@RequestMapping("/product")
public class ProductController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private BusinessService businessService;
	
	@RequestMapping(value = "/getProductList", method = RequestMethod.GET)
	public FwResponseEntity<?> getProductList() throws Exception {
		logger.info("========== Enter getProductList Controller ==========");
		FwResponseEntity<TbProductBean> response = businessService.getProductList();
		return response;
	}
	
	@RequestMapping(value = "/getProductListByCondition", method = RequestMethod.POST)
	public FwResponseEntity<TbProductBean> getProductListByCondition(@RequestBody TbProductBean tbProductBean) throws Exception {
		logger.info("========== Enter getProductListByCondition Controller ==========");
		FwResponseEntity<TbProductBean> response = businessService.getProductListByCondition(tbProductBean);
		return response;
	}
	
	@RequestMapping(value = "/getProductByBarcode/{id}", method = RequestMethod.GET)
	public FwResponseEntity<TbProductBean> getProductByBarcode(@PathVariable("id") String id) throws Exception {
		logger.info("========== Enter getProductByBarcode Controller ==========");
		FwResponseEntity<TbProductBean> response = businessService.getProductByBarcode(id);
		return response;
	}
	
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public FwResponseEntity<TbProductBean> addProduct(@RequestBody TbProductBean tbProductBean) throws Exception {
		logger.info("========== Enter addProduct Controller ==========");
		FwResponseEntity<TbProductBean> response = businessService.addTbProduct(tbProductBean);
		return response;
	}
	
	@RequestMapping(value = "/editProduct", method = RequestMethod.POST)
	public FwResponseEntity<TbProductBean> editProduct(@RequestBody TbProductBean tbProductBean) throws Exception {
		logger.info("========== Enter editProduct Controller ==========");
		FwResponseEntity<TbProductBean> response = businessService.editProduct(tbProductBean);
		return response;
	}
	
	@RequestMapping(value = "/deleteProduct/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteProduct(@PathVariable("id") long id) throws Exception {
		logger.info("========== Enter deleteProductById Controller ==========");
		businessService.deleteProduct(id);
		return new ResponseEntity<>(HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() throws Exception {
		logger.info("========== Enter test Controller ==========");
		return "test";
	}
}
