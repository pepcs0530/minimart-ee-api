package com.th.app.minimart.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.th.app.minimart.entity.FwUser;

@Repository
public interface UserRepository extends CrudRepository<FwUser, Long>{
	public boolean existsByUserId(String userId) ;
	public FwUser findByUserId(String userId);
}
