package com.th.app.estock.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.th.app.estock.repository.MProductTypeRepository;
import com.th.app.estock.service.interfaces.LovBusinessService;

@Service
public class LovBusinessServiceImpl implements LovBusinessService {
	
	private static final Logger logger = LoggerFactory.getLogger(LovBusinessServiceImpl.class);
	
	@Autowired
	private MProductTypeRepository mProductTypeRepository;

	@Override
	public List<Map<String, Object>> getMProductType() {
		logger.info("========== Enter getMProductType LovBusinessServiceImpl ==========");
		List<Map<String, Object>> result = new LinkedList<>();
		try {
			Map<String, Object> select = new HashMap<>();
			select.put("value", "");
			select.put("label", "<เลือก>");
			result.add(select);
			mProductTypeRepository.findAll().forEach(item->{
			Map<String, Object> value = new HashMap<>();
				value.put("value", item.getProductTypePk());
				value.put("label", item.getProductTypeName());
				result.add(value);
			});
			logger.debug("[size] => "+result.size());
		}catch(Exception e) {
			logger.info("[เกิดข้อผิดพลาด] => "+e.getMessage());
		}
		return result;
	}
	
}
