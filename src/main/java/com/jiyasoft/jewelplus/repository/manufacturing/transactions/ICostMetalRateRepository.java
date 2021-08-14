package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostMetalRate;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;

public interface ICostMetalRateRepository extends JpaRepository<CostMetalRate, Integer>, 
QueryDslPredicateExecutor<CostMetalRate> {
	
	CostMetalRate findByCostingMtAndDeactiveAndMetal(CostingMt costingMt,Boolean deactive,Metal metal);
	
	List<CostMetalRate> findByCostingMtAndDeactive(CostingMt costingMt,Boolean deactive);

}
