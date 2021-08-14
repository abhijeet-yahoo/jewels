package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRmCompDt;

public interface IConsigRmCompDtRepository extends JpaRepository<ConsigRmCompDt	, Integer>, QueryDslPredicateExecutor<ConsigRmCompDt> {

	List<ConsigRmCompDt> findByConsigMt(ConsigMt consigMt);
	
	ConsigRmCompDt findByUniqueId(Long uniqueId);
}
