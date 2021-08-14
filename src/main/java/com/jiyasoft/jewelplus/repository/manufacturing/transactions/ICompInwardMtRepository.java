package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompInwardMt;

public interface ICompInwardMtRepository extends JpaRepository<CompInwardMt, Integer>,
QueryDslPredicateExecutor<CompInwardMt>{
	
	CompInwardMt  findByInvNoAndDeactive(String invNo,Boolean deactive);
	
	CompInwardMt findByUniqueId(Long uniqueId);

}
