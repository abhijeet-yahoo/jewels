package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;

public interface ICostingDtItemRepository extends JpaRepository<CostingDtItem, Integer>, QueryDslPredicateExecutor<CostingDtItem> {

	

	CostingDtItem findByUniqueId(Long uniqueId);
		
		List<CostingDtItem> findByCostingMtAndDeactive(CostingMt costingMt,Boolean deactive);
		
		
		CostingDtItem findByOrderDtAndCostingMtAndDeactive(OrderDt orderDt,CostingMt costingMt,Boolean deactive);
}
