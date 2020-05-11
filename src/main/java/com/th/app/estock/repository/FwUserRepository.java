package com.th.app.estock.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.th.app.estock.entity.FwUser;

@Repository
public interface FwUserRepository extends CrudRepository<FwUser, Long>{

}
