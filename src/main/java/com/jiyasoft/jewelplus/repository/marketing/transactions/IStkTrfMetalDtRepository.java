package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfMetalDt;

public interface IStkTrfMetalDtRepository extends JpaRepository<StkTrfMetalDt, Integer>,
QueryDslPredicateExecutor<StkTrfMetalDt>{

List<StkTrfMetalDt>findByStkTrfDt(StkTrfDt stkTrfDt);
	
StkTrfMetalDt findByStkTrfDtAndPartNm(StkTrfDt stkTrfDt,LookUpMast lookUpMast);
	
}
