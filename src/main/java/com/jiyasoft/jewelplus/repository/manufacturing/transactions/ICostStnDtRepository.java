package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;

public interface ICostStnDtRepository extends
		JpaRepository<CostStnDt, Integer>, QueryDslPredicateExecutor<CostStnDt> {
	
	List<CostStnDt> findByCostingMtAndDeactive(CostingMt costingMt,Boolean deactive);
	
	List<CostStnDt> findByCostingDtAndDeactive(CostingDt costingDt,Boolean deactive);
	
	List<CostStnDt> findByItemNoAndOrderDtAndCostingMtAndDeactive(String itemNo,OrderDt orderDt,CostingMt costingMt,Boolean deactive);
	
	List<CostStnDt> findByItemNoAndCostingDtAndDeactive(String itemNo,CostingDt costingDt,Boolean deactive);
	
	
}
