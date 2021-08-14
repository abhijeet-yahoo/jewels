package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetMetalDt;

public interface IConsigRetMetalDtRepository  extends JpaRepository<ConsigRetMetalDt, Integer>,
QueryDslPredicateExecutor<ConsigRetMetalDt> {

	List<ConsigRetMetalDt>findByConsigRetDt(ConsigRetDt consigRetDt);
	
	ConsigRetMetalDt findByConsigRetDtAndPartNm(ConsigRetDt consigRetDt,LookUpMast lookUpMast);

}
