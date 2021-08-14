package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssMt;

public interface IJobIssMetalDtRepository extends JpaRepository<JobIssMetalDt, Integer>, QueryDslPredicateExecutor<JobIssMetalDt> {

List<JobIssMetalDt> findByJobIssMtAndDeactive(JobIssMt jobIssMt,Boolean deactive);
	
	List<JobIssMetalDt> findByJobIssDtAndDeactive(JobIssDt jobIssDt,Boolean deactive);
	
	JobIssMetalDt findByJobIssDtAndDeactiveAndMainMetal(JobIssDt jobIssDt,Boolean deactive,Boolean mainMetal);
}
