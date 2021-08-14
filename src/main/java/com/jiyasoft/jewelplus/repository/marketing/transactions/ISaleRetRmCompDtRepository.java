package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetRmCompDt;

public interface ISaleRetRmCompDtRepository extends JpaRepository<SaleRetRmCompDt, Integer>, QueryDslPredicateExecutor<SaleRetRmCompDt> {

	
	SaleRetRmCompDt findByUniqueId(Long uniqueId);
	List<SaleRetRmCompDt> findBySaleRetMt(SaleRetMt saleRetMt);

	
}
