package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;

public interface ICostingMtRepository extends
		JpaRepository<CostingMt, Integer>, QueryDslPredicateExecutor<CostingMt> {
	
	CostingMt findByInvNoAndDeactive(String invNo,Boolean deactive);
	
	CostingMt findByUniqueId(Long uniqueId);
}
