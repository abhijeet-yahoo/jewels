package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecMt;

public interface IJobRecDtRepository extends JpaRepository<JobRecDt, Integer>, QueryDslPredicateExecutor<JobRecDt> {

	JobRecDt findByUniqueId(Long uniqueId);
	
	List<JobRecDt> findByJobRecMtAndDeactive(JobRecMt jobRecMt,Boolean deactive);
	
	List<JobRecDt> findByOrderDtAndJobRecMtAndDeactive(OrderDt orderDt,JobRecMt jobRecMt,Boolean deactive);
	
}
