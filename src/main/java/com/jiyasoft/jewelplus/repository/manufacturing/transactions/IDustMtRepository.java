package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.DustMt;


public interface IDustMtRepository extends JpaRepository<DustMt, Integer>,QueryDslPredicateExecutor<DustMt> {
	
	
	DustMt findByInvNoAndDeactive(String invNo,Boolean deactive);
	
	DustMt findByUniqueId(Long uniqueId);
	
	DustMt findByFromPeriodAndToPeriodAndDeactive(Date fromPeriod,Date toPeriod,Boolean deactive);

}
