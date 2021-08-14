package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;

public interface ICostCompDtRepository extends
		JpaRepository<CostCompDt, Integer>,
		QueryDslPredicateExecutor<CostCompDt> {
	
	List<CostCompDt> findByCostingMtAndDeactive(CostingMt costingMt,Boolean deactive);

	List<CostCompDt> findByCostingDtAndDeactive(CostingDt costingDt,Boolean deactive);
	
	List<CostCompDt> findByItemNoAndOrderDtAndCostingMtAndDeactive(String itemNo,OrderDt orderDt,CostingMt costingMt,Boolean deactive);
	
	List<CostCompDt> findByItemNoAndCostingDtAndDeactive(String itemNo,CostingDt costingDt,Boolean deactive);
}
