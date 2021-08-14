package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetLabDt;

public interface IConsigRetLabDtRepository extends JpaRepository<ConsigRetLabDt,Integer>,
QueryDslPredicateExecutor<ConsigRetLabDt>{

	List<ConsigRetLabDt>findByConsigRetDt(ConsigRetDt consigRetDt);

	List<ConsigRetLabDt> findByConsigRetDtAndMetal(ConsigRetDt consigRetDt,Metal metal);
	
}
