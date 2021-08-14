package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetRmMetalDt;

public interface ISaleRetRmMetalDtRepository extends JpaRepository< SaleRetRmMetalDt, Integer>, QueryDslPredicateExecutor<SaleRetRmMetalDt> {

	SaleRetRmMetalDt findByUniqueId(Long uniqueId);

	List<SaleRetRmMetalDt> findBySaleRetMt(SaleRetMt saleRetMt);
}
