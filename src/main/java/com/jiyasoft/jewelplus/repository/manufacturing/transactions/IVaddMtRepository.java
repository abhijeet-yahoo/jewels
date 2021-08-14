package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;

public interface IVaddMtRepository extends 
			JpaRepository<CostingMt, Integer>,QueryDslPredicateExecutor<CostingMt>{
	
	CostingMt findByInvNo(String invNo);
	
	CostingMt findByUniqueId(Long uniqueId);

}
