package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRmStnDt;

public interface ISaleRmStnDtRepository extends JpaRepository<SaleRmStnDt, Integer>, QueryDslPredicateExecutor<SaleRmStnDt> {

	List<SaleRmStnDt> findBySaleMt(SaleMt saleMt);
	
	SaleRmStnDt findByUniqueId(Long uniqueId);
}
