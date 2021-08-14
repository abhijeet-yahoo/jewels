package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostJobCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostJobLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingJobDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingJobMt;

public interface ICostJobLabDtRepository extends JpaRepository<CostJobLabDt, Integer>, QueryDslPredicateExecutor<CostJobCompDt> {
	
List<CostJobLabDt> findByCostingJobMtAndDeactive(CostingJobMt costingJobMt,Boolean deactive);

List<CostJobLabDt> findByCostingJobDtAndDeactive(CostingJobDt costingJobDt,Boolean deactive);

}
