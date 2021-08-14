package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetLabDt;

public interface ISaleRetLabDtRepository extends JpaRepository<SaleRetLabDt	, Integer>, QueryDslPredicateExecutor<SaleRetLabDt>{


	List<SaleRetLabDt>findBySaleRetDt(SaleRetDt saleRetDt);

	List<SaleRetLabDt> findBySaleRetDtAndMetal(SaleRetDt saleRetDt,Metal metal);
}
