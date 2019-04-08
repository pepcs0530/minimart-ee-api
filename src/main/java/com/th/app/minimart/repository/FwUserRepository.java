package com.th.app.minimart.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.th.app.minimart.entity.FwUser;

@Repository
public interface FwUserRepository extends CrudRepository<FwUser, Long>{

}
