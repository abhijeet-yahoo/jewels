package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetDt;

public interface ISaleRetCompDtRepository extends JpaRepository<SaleRetCompDt, Integer>, QueryDslPredicateExecutor<SaleRetCompDt> {
	
	List<SaleRetCompDt>findBySaleRetDt(SaleRetDt saleRetDt);
}
