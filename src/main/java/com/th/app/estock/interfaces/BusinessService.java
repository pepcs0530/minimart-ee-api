package com.th.app.estock.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.th.app.estock.bean.TbHistoryBean;
import com.th.app.estock.bean.TbProductBean;
import com.th.app.estock.bean.TbShopTypeBean;
import com.th.app.estock.response.FwResponseEntity;

public interface BusinessService {

	FwResponseEntity<TbProductBean> getProductList() throws Exception;
	
	FwResponseEntity<TbProductBean> getProductListByCondition(TbProductBean tbProductBean) throws Exception;

	FwResponseEntity<TbProductBean> addTbProduct(TbProductBean tbProductBean) throws Exception;
	
	FwResponseEntity<TbProductBean> editProduct(TbProductBean tbProductBean) throws Exception;

	FwResponseEntity<TbProductBean> getProductByBarcode(String id) throws Exception;

	ResponseEntity<?> deleteProduct(long id) throws Exception;

	FwResponseEntity<TbHistoryBean> addHistoryList(List<TbHistoryBean> tbHistoryList) throws Exception;

	FwResponseEntity<TbHistoryBean> getHistoryListByCondition(TbHistoryBean tbHistoryBean) throws Exception;

	FwResponseEntity<TbShopTypeBean> getAllShopTypeList() throws Exception;

}
