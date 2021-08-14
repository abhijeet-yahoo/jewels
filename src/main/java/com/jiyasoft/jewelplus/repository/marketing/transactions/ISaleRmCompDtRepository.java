package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRmCompDt;

public interface ISaleRmCompDtRepository extends JpaRepository<SaleRmCompDt, Integer>, QueryDslPredicateExecutor<SaleRmCompDt> {

	List<SaleRmCompDt> findBySaleMt(SaleMt saleMt);
	
	SaleRmCompDt findByUniqueId(Long uniqueId);
}
