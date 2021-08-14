package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;

public interface ICostingDtRepository extends

	JpaRepository<CostingDt, Integer>, QueryDslPredicateExecutor<CostingDt> {

	CostingDt findByUniqueId(Long uniqueId);
	
	List<CostingDt> findByCostingMtAndDeactive(CostingMt costingMt,Boolean deactive);
	
	List<CostingDt> findByItemNoAndOrderDtAndCostingMtAndDeactive(String itemNo,OrderDt orderDt,CostingMt costingMt,Boolean deactive);
	List<CostingDt> findByOrderDtAndCostingMtAndDeactive(OrderDt orderDt,CostingMt costingMt,Boolean deactive);
	
	List<CostingDt> findByItemNoAndDeactive(String itemNo,Boolean deactive);
	
	
	
 }
