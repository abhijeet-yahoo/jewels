package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MakingMt;

public interface IMakingMtRepository extends JpaRepository<MakingMt, Integer>,
		QueryDslPredicateExecutor<MakingMt> {
	
	MakingMt findByInvNoAndDeactive(String invNo,Boolean deactive);
	
	MakingMt findByUniqueId(Long uniqueId);
	

	

}
