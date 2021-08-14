package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetMetalDt;

public interface ISaleRetMetalDtRepository extends JpaRepository<SaleRetMetalDt	, Integer>, QueryDslPredicateExecutor<SaleRetMetalDt>{

	List<SaleRetMetalDt>findBySaleRetDt(SaleRetDt saleRetDt);
	
	SaleRetMetalDt findBySaleRetDtAndPartNm(SaleRetDt saleRetDt,LookUpMast lookUpMast);
	
	SaleRetMetalDt findBySaleRetDtAndMainMetal(SaleRetDt saleRetDt,Boolean mainMetal);
}
