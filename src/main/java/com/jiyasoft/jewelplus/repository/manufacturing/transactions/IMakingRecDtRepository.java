package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MakingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MakingRecDt;


public interface IMakingRecDtRepository extends
		JpaRepository<MakingRecDt, Integer>,
		QueryDslPredicateExecutor<MakingRecDt> {
	
	List<MakingRecDt> findByMakingMtAndDeactive(MakingMt makingMt, Boolean deactive); 
	
	MakingRecDt findByUniqueId(Long uniqueId);

}
