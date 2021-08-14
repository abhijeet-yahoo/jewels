package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompOutwardMt;

public interface ICompOutwardMtRepository extends JpaRepository<CompOutwardMt, Integer>,
QueryDslPredicateExecutor<CompOutwardMt>{
	
	CompOutwardMt findByInvNo(String invNo);
	
	CompOutwardMt findByInvNoAndDeactive(String invNo, Boolean deactive);

	CompOutwardMt findByUniqueId(Long uniqueId);
}
