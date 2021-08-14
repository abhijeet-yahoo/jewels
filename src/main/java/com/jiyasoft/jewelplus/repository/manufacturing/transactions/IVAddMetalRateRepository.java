package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddMetalRate;

public interface IVAddMetalRateRepository extends JpaRepository<VAddMetalRate, Integer>, QueryDslPredicateExecutor<VAddMetalRate>{

	VAddMetalRate findByCostingMtAndDeactiveAndMetal(CostingMt costingMt,Boolean deactive,Metal metal);
	
	List<VAddMetalRate> findByCostingMtAndDeactive(CostingMt costingMt,Boolean deactive);
}
