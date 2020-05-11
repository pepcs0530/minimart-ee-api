package com.th.app.estock.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.th.app.estock.bean.TbHistoryBean;
import com.th.app.estock.bean.TbProductBean;
import com.th.app.estock.bean.TbShopTypeBean;
import com.th.app.estock.entity.TbHistory;
import com.th.app.estock.entity.TbProduct;
import com.th.app.estock.enums.FwEnumUtils;
import com.th.app.estock.interfaces.BusinessService;
import com.th.app.estock.persistence.FwPersistence;
import com.th.app.estock.repository.TbHistoryRepository;
import com.th.app.estock.repository.TbProductRepository;
import com.th.app.estock.response.FwResponseEntity;

@Service
public class BusinessServiceImpl implements BusinessService {
	
	private static final Logger logger = LoggerFactory.getLogger(BusinessServiceImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private FwPersistence persistence;
	
	@Autowired
	private TbProductRepository tbProductRepository;
	
	@Autowired
	private TbHistoryRepository tbHistoryRepository;

	@Override
	public FwResponseEntity<TbProductBean> getProductList() throws Exception {
		logger.info("========== Enter getProductList BusinessServiceImpl ==========");
		FwResponseEntity<TbProductBean> response = new FwResponseEntity<TbProductBean>();
		response =   persistence.getProductList(entityManager);
		return response;
	}
	
	@Override
	public FwResponseEntity<TbProductBean> getProductListByCondition(TbProductBean tbProductBean) throws Exception {
		logger.info("========== Enter getProductListByCondition BusinessServiceImpl ==========");
		FwResponseEntity<TbProductBean> response = new FwResponseEntity<TbProductBean>();
		response =   persistence.getProductListByCondition(entityManager, tbProductBean);
		return response;
	}

	@Transactional
	public FwResponseEntity<TbProductBean> addTbProduct(TbProductBean tbProductBean) throws Exception {
		logger.info("========== Enter addTbProduct BusinessServiceImpl ==========");
		FwResponseEntity<TbProductBean> response = new FwResponseEntity<>();
		try {
			Date date = Calendar.getInstance().getTime();
			Timestamp timestamp = new Timestamp(date.getTime());
			TbProduct entity = new TbProduct();
			entity.setProductBarcode(tbProductBean.getProductBarcode());
			entity.setProductName(tbProductBean.getProductName());
			entity.setProductDesc(tbProductBean.getProductDesc());
			entity.setProductType(tbProductBean.getProductType());
			entity.setPrice(tbProductBean.getPrice());
			entity.setStoreDate(tbProductBean.getStoreDate());
			entity.setExpDate(tbProductBean.getExpDate());
			entity.setCreateBy(tbProductBean.getCreateBy());
			entity.setCreateDate(timestamp);
			entity = tbProductRepository.save(entity);
			tbProductBean.setProductPk(entity.getProductPk());
			
			response.setResult(tbProductBean);
			response.setMessageCode(FwEnumUtils.SUCCES_CODE_200.getValue());
			response.setMessageDetail(FwEnumUtils.SUCCES_DETAIL_200.getValue());
			return response;
		}catch(Exception ex) {
			logger.info("[เกิดข้อผิดพลาด] => "+ex);
			response.setMessageCode(FwEnumUtils.ERROR_CODE_500.getValue());
			response.setMessageDetail(ex.getMessage());
			return response;
		}
	}

	@Transactional
	public FwResponseEntity<TbProductBean> editProduct(TbProductBean tbProductBean) throws Exception {
		logger.info("========== Enter editProduct BusinessServiceImpl ==========");
		FwResponseEntity<TbProductBean> response = new FwResponseEntity<>();
		try {
			TbProduct entity = new TbProduct();
			Date date = Calendar.getInstance().getTime();
			Timestamp timestamp = new Timestamp(date.getTime());
			entity = tbProductRepository.findById(tbProductBean.getProductPk()).get();
			entity.setProductBarcode(tbProductBean.getProductBarcode());
			entity.setProductName(tbProductBean.getProductName());
			entity.setProductDesc(tbProductBean.getProductDesc());
			entity.setProductType(tbProductBean.getProductType());
			entity.setPrice(tbProductBean.getPrice());
			entity.setStoreDate(tbProductBean.getStoreDate());
			entity.setExpDate(tbProductBean.getExpDate());
			entity.setUpdateBy(tbProductBean.getUpdateBy());
			entity.setUpdateDate(timestamp);
			tbProductRepository.save(entity);
			response.setResult(tbProductBean);
			response.setMessageCode(FwEnumUtils.SUCCES_CODE_200.getValue());
			response.setMessageDetail(FwEnumUtils.SUCCES_DETAIL_200.getValue());
			return response;
		}catch(Exception ex) {
			logger.info("[เกิดข้อผิดพลาด] => "+ex);
			response.setMessageCode(FwEnumUtils.ERROR_CODE_500.getValue());
			response.setMessageDetail(ex.getMessage());
			return response;
		}
	}
	
	@Override
	public FwResponseEntity<TbProductBean> getProductByBarcode(String id) throws Exception {
		logger.info("========== Enter getProductByBarcode BusinessServiceImpl ==========");
		FwResponseEntity<TbProductBean> response = new FwResponseEntity<>();
		try {
			List<TbProductBean> productList =   persistence.getProductByBarcode(entityManager, id);
			
			response.setResults(productList);
			response.setMessageCode(FwEnumUtils.SUCCES_CODE_200.getValue());
			response.setMessageDetail(FwEnumUtils.SUCCES_DETAIL_200.getValue());
			return response;
		} catch(Exception ex) {
			logger.info("[เกิดข้อผิดพลาด] => "+ex);
			response.setMessageCode(FwEnumUtils.ERROR_CODE_500.getValue());
			response.setMessageDetail(ex.getMessage());
			return response;
		}
		
	}

	@Override
	public ResponseEntity<?> deleteProduct(long id) throws Exception {
		logger.info("========== Enter deleteProduct BusinessServiceImpl ==========");
		tbProductRepository.deleteById(id);
		return new ResponseEntity(HttpStatus.OK);
	}

	@Override
	public FwResponseEntity<TbHistoryBean> addHistoryList(List<TbHistoryBean> tbHistoryList) throws Exception {
		logger.info("========== Enter addHistoryList BusinessServiceImpl ==========");
		FwResponseEntity<TbHistoryBean> response = new FwResponseEntity<>();
		try {
			Date date = Calendar.getInstance().getTime();
			Timestamp timestamp = new Timestamp(date.getTime());
			TbHistory entity = new TbHistory();
			
			for (TbHistoryBean bean : tbHistoryList) 
			{ 
			    entity.setSaleDate(timestamp);
			    entity.setProductBarcode(bean.getProductBarcode());
			    entity.setProductName(bean.getProductName());
			    entity.setAmount(bean.getAmount());
			    entity.setCost(bean.getCost());
			    entity.setPrice(bean.getPrice());
			    entity.setCreateBy(bean.getCreateBy());
			    entity.setCreateDate(timestamp);
			    tbHistoryRepository.save(entity);
			}			
			
			response.setResults(tbHistoryList);
			response.setMessageCode(FwEnumUtils.SUCCES_CODE_200.getValue());
			response.setMessageDetail(FwEnumUtils.SUCCES_DETAIL_200.getValue());
			return response;
		}catch(Exception ex) {
			logger.info("[เกิดข้อผิดพลาด] => "+ex);
			response.setMessageCode(FwEnumUtils.ERROR_CODE_500.getValue());
			response.setMessageDetail(ex.getMessage());
			return response;
		}
	}

	@Override
	public FwResponseEntity<TbHistoryBean> getHistoryListByCondition(TbHistoryBean tbHistoryBean) throws Exception {
		logger.info("========== Enter getProductListByCondition BusinessServiceImpl ==========");
		FwResponseEntity<TbHistoryBean> response = new FwResponseEntity<TbHistoryBean>();
		response =   persistence.getHistoryListByCondition(entityManager, tbHistoryBean);
		return response;
	}

	@Override
	public FwResponseEntity<TbShopTypeBean> getAllShopTypeList() throws Exception {
		logger.info("========== Enter getAllShopTypeList BusinessServiceImpl ==========");
		FwResponseEntity<TbShopTypeBean> response = new FwResponseEntity<>();
		TbShopTypeBean tbShopTypeBean = new TbShopTypeBean();
		response =   persistence.getAllShopTypeList(entityManager, tbShopTypeBean);
		return response;
	}

}
