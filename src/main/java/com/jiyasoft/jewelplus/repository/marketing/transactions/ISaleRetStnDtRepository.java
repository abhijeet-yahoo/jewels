package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetStnDt;

public interface ISaleRetStnDtRepository extends JpaRepository<SaleRetStnDt	, Integer>, QueryDslPredicateExecutor<SaleRetStnDt> {

	List<SaleRetStnDt>findBySaleRetDt(SaleRetDt saleRetDt);
}
