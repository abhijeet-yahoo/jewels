package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostMetalDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;

public interface ICostMetalDtItemRepository extends JpaRepository<CostMetalDtItem, Integer>,QueryDslPredicateExecutor<CostMetalDtItem>{

List<CostMetalDtItem> findByCostingMtAndDeactive(CostingMt costingMt,Boolean deactive);
	
	List<CostMetalDtItem> findByCostingDtItemAndDeactive(CostingDtItem costingDtItem,Boolean deactive);

	CostMetalDtItem findByCostingDtItemAndDeactiveAndMainMetal(CostingDtItem costingDtItem,Boolean deactive,Boolean mainMetal);

	CostMetalDtItem findByCostingDtItemAndDeactiveAndPartNm(CostingDtItem costingDtItem,Boolean deactive,LookUpMast lookUpMast);

}
