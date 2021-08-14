package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetStnDt;

public interface IConsigRetStnDtRepository extends JpaRepository<ConsigRetStnDt, Integer>,
QueryDslPredicateExecutor<ConsigRetStnDt>{
	
	List<ConsigRetStnDt>findByConsigRetDt(ConsigRetDt consigRetDt);

}
