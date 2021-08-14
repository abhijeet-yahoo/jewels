package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingJobMt;

public interface ICostingJobMtRepository extends JpaRepository<CostingJobMt, Integer>,QueryDslPredicateExecutor<CostingJobMt>{

	CostingJobMt findByInvNoAndDeactive(String invNo,Boolean deactive);
	
	CostingJobMt findByUniqueId(Long uniqueId);
	 
}
