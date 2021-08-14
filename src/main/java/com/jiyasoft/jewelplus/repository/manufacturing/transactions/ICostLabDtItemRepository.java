package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostLabDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;

public interface ICostLabDtItemRepository extends JpaRepository<CostLabDtItem, Integer>, QueryDslPredicateExecutor<CostLabDtItem> {

	List<CostLabDtItem> findByCostingMtAndDeactive(CostingMt costingMt,Boolean deactive);
	
	List<CostLabDtItem> findByCostingDtItemAndDeactive(CostingDtItem costingDtItem,Boolean deactive);
	
	List<CostLabDtItem> findByItemNoAndCostingDtItemAndDeactive(String itemNo,CostingDtItem costingDtItem,Boolean deactive);
	
	List<CostLabDtItem>findByCostingDtItemAndMetalAndDeactive(CostingDtItem costingDtItem,Metal metal,Boolean deactive );
}
