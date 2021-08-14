package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecMt;

public interface IJobRecMetalDtRepository extends JpaRepository<JobRecMetalDt, Integer>, QueryDslPredicateExecutor<JobRecMetalDt> {

List<JobRecMetalDt> findByJobRecMtAndDeactive(JobRecMt jobRecMt,Boolean deactive);
	
	List<JobRecMetalDt> findByJobRecDtAndDeactive(JobRecDt jobRecDt,Boolean deactive);
	
	JobRecMetalDt findByJobRecDtAndDeactiveAndMainMetal(JobRecDt jobRecDt,Boolean deactive,Boolean mainMetal);
}
