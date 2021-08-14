package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Component;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostCompDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;

public interface ICostCompDtItemRepository extends JpaRepository<CostCompDtItem, Integer>, QueryDslPredicateExecutor<CostCompDtItem> {
	
	List<CostCompDtItem> findByCostingMtAndDeactive(CostingMt costingMt,Boolean deactive);

	List<CostCompDtItem> findByCostingDtItemAndDeactive(CostingDtItem costingDtItem,Boolean deactive);
	
	List<CostCompDtItem> findByItemNoAndCostingDtItemAndDeactive(String itemNo,CostingDtItem costingDtItem,Boolean deactive);

	CostCompDtItem findByCostingDtItemAndComponentAndPurityAndColorAndDeactive(CostingDtItem costingDtItem, Component component, Purity purity, Color color,Boolean deactive);
}
