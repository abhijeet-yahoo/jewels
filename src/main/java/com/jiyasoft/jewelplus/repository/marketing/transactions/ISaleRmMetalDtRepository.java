package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRmMetalDt;

public interface ISaleRmMetalDtRepository extends JpaRepository<SaleRmMetalDt, Integer>, QueryDslPredicateExecutor<SaleRmMetalDt> {

	 List<SaleRmMetalDt> findBySaleMt(SaleMt saleMt);
	 
	 SaleRmMetalDt findByUniqueId(Long uniqueId);
	
}
