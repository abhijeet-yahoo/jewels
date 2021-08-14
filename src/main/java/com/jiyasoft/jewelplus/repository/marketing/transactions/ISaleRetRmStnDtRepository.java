package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetRmStnDt;

public interface ISaleRetRmStnDtRepository
		extends JpaRepository<SaleRetRmStnDt, Integer>, QueryDslPredicateExecutor<SaleRetRmStnDt> {

	SaleRetRmStnDt findByUniqueId(Long uniqueId);

	List<SaleRetRmStnDt> findBySaleRetMt(SaleRetMt saleRetMt);

}
