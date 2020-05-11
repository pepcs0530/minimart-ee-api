package com.th.app.estock.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.th.app.estock.bean.TbHistoryBean;
import com.th.app.estock.bean.TbProductBean;
import com.th.app.estock.bean.TbShopTypeBean;
import com.th.app.estock.enums.FwEnumUtils;
import com.th.app.estock.response.FwResponseEntity;
import com.th.app.estock.response.Paginator;
import com.th.app.estock.utils.FwPersistenceUtils;

@Component("fwPersistence")
public class FwPersistence {
	
	private static final Logger logger = LoggerFactory.getLogger(FwPersistence.class);

	public FwResponseEntity<TbProductBean> getProductList(EntityManager entityManager) {
		logger.info("========== Start getProductList Persistence ==========");
		FwResponseEntity<TbProductBean> response = new FwResponseEntity<TbProductBean>();
		Map<String, Object> conditions = new HashMap<String, Object>();
		StringBuilder sqlStr = new StringBuilder();
		try {
			sqlStr.append(" SELECT ");
			sqlStr.append(" 	T1.PRODUCT_PK, T1.PRODUCT_BARCODE, T1.PRODUCT_NAME, T1.PRODUCT_DESC, T1.PRODUCT_TYPE, T1.PRICE, T1.QUANTITY ");
			sqlStr.append(" FROM TB_PRODUCT T1 ");
			sqlStr.append(" WHERE 1=1 ");
			sqlStr.append(" ORDER BY T1.PRODUCT_PK ");
			
			ArrayList<TbProductBean> productList = (ArrayList<TbProductBean>) FwPersistenceUtils.querySql(entityManager, sqlStr.toString(),
					TbProductBean.class, conditions);
			
			
			response.setResults(productList);
			response.setMessageCode(FwEnumUtils.SUCCES_CODE_200.getValue());
			response.setMessageDetail(FwEnumUtils.SUCCES_DETAIL_200.getValue());
			return response;
		} catch (Exception e) {
			logger.debug("เกิดข้อผิดพลาด => "+e);
			response.setMessageCode(FwEnumUtils.SUCCES_CODE_200.getValue());
			response.setMessageDetail(e.getMessage());
			return response;
		}
	}

	public FwResponseEntity<TbProductBean> getProductListByCondition(EntityManager entityManager, TbProductBean tbProductBean) {
		logger.info("========== Start getProductListByCondition Persistence ==========");
		FwResponseEntity<TbProductBean> response = new FwResponseEntity<TbProductBean>();
		Map<String, Object> conditions = new HashMap<String, Object>();
		StringBuilder sqlCount = new StringBuilder();
		StringBuilder sqlData = new StringBuilder();
		StringBuilder sqlStatement = new StringBuilder();
		try {
			sqlCount.append(" SELECT COUNT(*) AS COUNT ");
			
			sqlData.append(" SELECT ");
			sqlData.append(" 	T1.PRODUCT_PK, T1.PRODUCT_BARCODE, T1.PRODUCT_NAME, T1.PRODUCT_DESC, T1.PRODUCT_TYPE, T1.PRICE, T1.QUANTITY, T1.EXP_DATE ");
			
			sqlStatement.append(" FROM TB_PRODUCT T1 ");
			sqlStatement.append(" WHERE 1=1 ");
			
			if(!StringUtils.isEmpty(tbProductBean.getProductBarcode())){
				sqlStatement.append(" AND T1.PRODUCT_BARCODE = :productBarcode ");
				conditions.put("productBarcode", tbProductBean.getProductBarcode());
			}
			
			if(!StringUtils.isEmpty(tbProductBean.getProductName())){
				sqlStatement.append(" AND T1.PRODUCT_NAME LIKE :productName ");
				conditions.put("productName", "%" + tbProductBean.getProductName() + "%");
			}
			
			if(!StringUtils.isEmpty(tbProductBean.getProductType())){
				sqlStatement.append(" AND T1.PRODUCT_TYPE = :productType ");
				conditions.put("productType", tbProductBean.getProductType());
			}
			
			if(!StringUtils.isEmpty(tbProductBean.getExpDate())){
				//sqlStatement.append(" AND DATE_FORMAT(EXP_DATE, '%Y%m%d') BETWEEN '20190509' AND '20190511' ");
				sqlStatement.append(" AND DATE_FORMAT(EXP_DATE, '%Y%m%d') = :expDate ");
				conditions.put("expDate", tbProductBean.getExpDate());
			}
			
			sqlStatement.append(" ORDER BY T1.PRODUCT_PK ");
			
			ArrayList<TbProductBean> tbProductCount = (ArrayList<TbProductBean>) FwPersistenceUtils.querySql(entityManager, sqlCount.toString() + sqlStatement.toString(), TbProductBean.class, conditions);
			ArrayList<TbProductBean> tbProductList = (ArrayList<TbProductBean>) FwPersistenceUtils.querySqlLazy(entityManager, sqlData.toString() + sqlStatement.toString(), TbProductBean.class, conditions, tbProductBean.getOffset(), tbProductBean.getLimit());
			
			Paginator paginator = new Paginator();
			paginator.setOffset(tbProductBean.getOffset());
			paginator.setLimit(tbProductBean.getLimit());
			paginator.setCount(tbProductCount.get(0).getCount());
			
			response.setPaginator(paginator);
			response.setResults(tbProductList);
			response.setMessageCode(FwEnumUtils.SUCCES_CODE_200.getValue());
			response.setMessageDetail(FwEnumUtils.SUCCES_DETAIL_200.getValue());
			return response;
		} catch (Exception e) {
			logger.debug("เกิดข้อผิดพลาด => "+e);
			response.setMessageCode(FwEnumUtils.SUCCES_CODE_200.getValue());
			response.setMessageDetail(e.getMessage());
			return response;
		}
	}

	public List<TbProductBean> getProductByBarcode(EntityManager entityManager, String id) throws Exception {
		logger.info("========== Start getProductByBarcode Persistence ==========");
		Map<String, Object> conditions = new HashMap<String, Object>();
		StringBuilder sqlCount = new StringBuilder();
		StringBuilder sqlData = new StringBuilder();
		StringBuilder sqlStatement = new StringBuilder();
		try {
			sqlStatement.append(" SELECT T1.PRODUCT_PK, T1.PRODUCT_BARCODE, T1.PRODUCT_NAME, T1.PRODUCT_DESC, T1.PRODUCT_TYPE, T1.PRICE, T1.QUANTITY, T1.EXP_DATE ");
			sqlStatement.append(" FROM TB_PRODUCT T1 ");
			sqlStatement.append(" WHERE 1=1 ");
			
			if(!StringUtils.isEmpty(id)){
				sqlStatement.append(" AND T1.PRODUCT_BARCODE = :productBarcode ");
				conditions.put("productBarcode", id);
			}
			
			ArrayList<TbProductBean> tbProductList = (ArrayList<TbProductBean>) FwPersistenceUtils.querySql(entityManager, sqlData.toString() + sqlStatement.toString(), TbProductBean.class, conditions);
			return tbProductList;
			
		}catch(Exception e) {
			throw new Exception();
		}
	}

	public FwResponseEntity<TbHistoryBean> getHistoryListByCondition(EntityManager entityManager,
			TbHistoryBean tbHistoryBean) {
		logger.info("========== Start getHistoryListByCondition Persistence ==========");
		FwResponseEntity<TbHistoryBean> response = new FwResponseEntity<TbHistoryBean>();
		Map<String, Object> conditions = new HashMap<String, Object>();
		StringBuilder sqlCount = new StringBuilder();
		StringBuilder sqlData = new StringBuilder();
		StringBuilder sqlStatement = new StringBuilder();
		try {
			sqlCount.append(" SELECT COUNT(*) AS COUNT ");
			
			sqlData.append(" SELECT ");
			sqlData.append(" 	T1.HISTORY_PK, T1.SALE_DATE, T1.PRODUCT_BARCODE, T1.PRODUCT_NAME, T1.COST, T1.PRICE, T1.AMOUNT ");
			
			sqlStatement.append(" FROM TB_HISTORY T1 ");
			sqlStatement.append(" WHERE 1=1 ");
			
			
			if(!StringUtils.isEmpty(tbHistoryBean.getStartDate()) && !StringUtils.isEmpty(tbHistoryBean.getEndDate())){
				//sqlStatement.append(" AND DATE_FORMAT(EXP_DATE, '%Y%m%d') BETWEEN '20190509' AND '20190511' ");
				//sqlStatement.append(" AND DATE_FORMAT(EXP_DATE, '%Y%m%d') = :startDate ");
				
				sqlStatement.append(" AND DATE_FORMAT(T1.SALE_DATE, '%Y%m%d') BETWEEN :startDate AND :endDate ");
				
				conditions.put("startDate", tbHistoryBean.getStartDate());
				conditions.put("endDate", tbHistoryBean.getEndDate());
			}
			
			sqlStatement.append(" ORDER BY T1.HISTORY_PK ");
			
			ArrayList<TbHistoryBean> tbHistoryCount = (ArrayList<TbHistoryBean>) FwPersistenceUtils.querySql(entityManager, sqlCount.toString() + sqlStatement.toString(), TbHistoryBean.class, conditions);
			ArrayList<TbHistoryBean> tbHistoryList = (ArrayList<TbHistoryBean>) FwPersistenceUtils.querySqlLazy(entityManager, sqlData.toString() + sqlStatement.toString(), TbHistoryBean.class, conditions, tbHistoryBean.getOffset(), tbHistoryBean.getLimit());
			
			Paginator paginator = new Paginator();
			paginator.setOffset(tbHistoryBean.getOffset());
			paginator.setLimit(tbHistoryBean.getLimit());
			paginator.setCount(tbHistoryCount.get(0).getCount());
			
			response.setPaginator(paginator);
			response.setResults(tbHistoryList);
			response.setMessageCode(FwEnumUtils.SUCCES_CODE_200.getValue());
			response.setMessageDetail(FwEnumUtils.SUCCES_DETAIL_200.getValue());
			return response;
		} catch (Exception e) {
			logger.debug("เกิดข้อผิดพลาด => "+e);
			response.setMessageCode(FwEnumUtils.SUCCES_CODE_200.getValue());
			response.setMessageDetail(e.getMessage());
			return response;
		}
	}

	public FwResponseEntity<TbShopTypeBean> getAllShopTypeList(EntityManager entityManager,
			TbShopTypeBean tbShopTypeBean) {
		logger.info("========== Start getAllShopTypeList Persistence ==========");
		FwResponseEntity<TbShopTypeBean> response = new FwResponseEntity<>();
		Map<String, Object> conditions = new HashMap<String, Object>();
		StringBuilder sqlCount = new StringBuilder();
		StringBuilder sqlData = new StringBuilder();
		StringBuilder sqlStatement = new StringBuilder();
		try{
			sqlCount.append(" SELECT COUNT(*) AS COUNT ");
			
			sqlData.append(" SELECT ");
			sqlData.append(" 	t1.shop_type_pk, t1.shop_name ");
			
			sqlStatement.append(" FROM tb_shop_type t1 ");
			sqlStatement.append(" WHERE 1=1 ");
			
			sqlStatement.append(" ORDER BY t1.shop_type_pk ");
			
			ArrayList<TbShopTypeBean> tbShopTypeCount = (ArrayList<TbShopTypeBean>) FwPersistenceUtils.querySql(entityManager, sqlCount.toString() + sqlStatement.toString(), TbShopTypeBean.class, conditions);
			ArrayList<TbShopTypeBean> tbShopTypeList = (ArrayList<TbShopTypeBean>) FwPersistenceUtils.querySqlLazy(entityManager, sqlData.toString() + sqlStatement.toString(), TbShopTypeBean.class, conditions, 0, 100);
			
			Paginator paginator = new Paginator();
			paginator.setOffset(0);
			paginator.setLimit(100);
			paginator.setCount(tbShopTypeCount.get(0).getCount());
			
			response.setPaginator(paginator);
			response.setResults(tbShopTypeList);
			response.setMessageCode(FwEnumUtils.SUCCES_CODE_200.getValue());
			response.setMessageDetail(FwEnumUtils.SUCCES_DETAIL_200.getValue());
		} catch (Exception e) {
			response.setMessageCode(FwEnumUtils.ERROR_CODE_500.getValue());
			response.setMessageDetail(e.getMessage());
		} finally {
			logger.info("========== End getAllShopTypeList Persistence ==========");
		}
		return response;
	}
}
