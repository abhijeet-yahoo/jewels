package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRmStnDt;

public interface IConsigRmStnDtRepository extends JpaRepository<ConsigRmStnDt, Integer>, QueryDslPredicateExecutor<ConsigRmStnDt>{

	List<ConsigRmStnDt> findByConsigMt(ConsigMt consigMt);
	
	ConsigRmStnDt findByUniqueId(Long uniqueId);
}
