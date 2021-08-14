package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRmMetalDt;

public interface IConsigRmMetalDtRepository extends JpaRepository<ConsigRmMetalDt, Integer> , QueryDslPredicateExecutor<ConsigRmMetalDt> {

	 List<ConsigRmMetalDt> findByConsigMt(ConsigMt consigMt);
	 
	 ConsigRmMetalDt findByUniqueId(Long uniqueId);
}
