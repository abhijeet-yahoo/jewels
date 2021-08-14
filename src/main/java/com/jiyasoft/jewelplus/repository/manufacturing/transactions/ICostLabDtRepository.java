package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;

public interface ICostLabDtRepository extends
		JpaRepository<CostLabDt, Integer>, QueryDslPredicateExecutor<CostLabDt> {
	
	List<CostLabDt> findByCostingMtAndDeactive(CostingMt costingMt,Boolean deactive);
	
	List<CostLabDt> findByCostingDtAndDeactive(CostingDt costingDt,Boolean deactive);
	
	List<CostLabDt> findByItemNoAndCostingDtAndDeactive(String itemNo,CostingDt costingDt,Boolean deactive);
	
	List<CostLabDt>findByCostingDtAndMetalAndDeactive(CostingDt costingDt,Metal metal,Boolean deactive );
}
