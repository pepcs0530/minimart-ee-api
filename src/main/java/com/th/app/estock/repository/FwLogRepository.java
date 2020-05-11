package com.th.app.estock.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.th.app.estock.entity.FwLog;

@Repository
public interface FwLogRepository extends CrudRepository<FwLog, Long> {

}
