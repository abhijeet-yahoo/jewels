package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigMetalDt;

public interface IConsigMetalDtRepository  extends JpaRepository<ConsigMetalDt, Integer>,
QueryDslPredicateExecutor<ConsigMetalDt>{

List<ConsigMetalDt>findByConsigDt(ConsigDt consigDt);
	
ConsigMetalDt findByConsigDtAndPartNm(ConsigDt consigDt,LookUpMast lookUpMast);
	
}
