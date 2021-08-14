package com.jiyasoft.jewelplus.repository.manufacturing.transactions;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;



public interface ICostMetalDtRepository extends JpaRepository<CostMetalDt, Integer>, QueryDslPredicateExecutor<CostMetalDt> {
	
	List<CostMetalDt> findByCostingMtAndDeactive(CostingMt costingMt,Boolean deactive);
	
	List<CostMetalDt> findByCostingDtAndDeactive(CostingDt costingDt,Boolean deactive);

	CostMetalDt findByCostingDtAndDeactiveAndMainMetal(CostingDt costingDt,Boolean deactive,Boolean mainMetal);

	CostMetalDt findByCostingDtAndDeactiveAndPartNm(CostingDt costingDt,Boolean deactive,LookUpMast lookUpMast);

}
