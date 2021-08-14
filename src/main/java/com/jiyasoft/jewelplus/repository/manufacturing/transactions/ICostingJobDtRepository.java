package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingJobDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingJobMt;

public interface ICostingJobDtRepository extends JpaRepository<CostingJobDt, Integer>,QueryDslPredicateExecutor<CostingJobDt> {
	
	
    CostingJobDt findByUniqueId(Long uniqueId);
	
	List<CostingJobDt> findByCostingJobMtAndDeactive(CostingJobMt costingJobMt,Boolean deactive);
	
	List<CostingJobDt> findByItemNoAndOrderDtAndCostingJobMtAndDeactive(String itemNo,OrderDt orderDt,CostingJobMt costingJobMt,Boolean deactive);
	

}
