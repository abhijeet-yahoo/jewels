package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostJobStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingJobDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingJobMt;

public interface ICostJobStnDtRepository extends
JpaRepository<CostJobStnDt, Integer>, QueryDslPredicateExecutor<CostJobStnDt> {
	
List<CostJobStnDt> findByCostingJobMtAndDeactive(CostingJobMt costingJobMt,Boolean deactive);

List<CostJobStnDt> findByCostingJobDtAndDeactive(CostingJobDt costingJobDt,Boolean deactive);

List<CostJobStnDt> findByItemNoAndOrderDtAndCostingJobMtAndDeactive(String itemNo,OrderDt orderDt,CostingJobMt costingJobMt,Boolean deactive);


}
