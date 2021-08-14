package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssMt;

public interface IJobIssDtRepository extends JpaRepository<JobIssDt, Integer>, QueryDslPredicateExecutor<JobIssDt> {

	JobIssDt findByUniqueId(Long uniqueId);
	
	List<JobIssDt> findByJobIssMtAndDeactive(JobIssMt jobIssMt,Boolean deactive);
	
	List<JobIssDt> findByItemNoAndOrderDtAndJobIssMtAndDeactive(String itemNo,OrderDt orderDt,JobIssMt jobIssMt,Boolean deactive);
	List<JobIssDt> findByOrderDtAndJobIssMtAndDeactive(OrderDt orderDt,JobIssMt jobIssMt,Boolean deactive);
	
	List<JobIssDt> findByItemNoAndDeactive(String itemNo,Boolean deactive);
	
}
