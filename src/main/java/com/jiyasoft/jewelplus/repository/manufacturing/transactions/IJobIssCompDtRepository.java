package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssMt;

public interface IJobIssCompDtRepository extends JpaRepository<JobIssCompDt, Integer>, QueryDslPredicateExecutor<JobIssCompDt>{
	
	 List<JobIssCompDt> findByJobIssMtAndDeactive(JobIssMt jobIssMt,Boolean deactive);
	
	 List<JobIssCompDt> findByJobIssDtAndDeactive(JobIssDt jobIssDt,Boolean deactive);
	
}
