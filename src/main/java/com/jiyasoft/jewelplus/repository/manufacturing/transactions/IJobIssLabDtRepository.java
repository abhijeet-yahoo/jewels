package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssMt;

public interface IJobIssLabDtRepository extends JpaRepository<JobIssLabDt, Integer>, QueryDslPredicateExecutor<JobIssLabDt>{

List<JobIssLabDt> findByJobIssMtAndDeactive(JobIssMt jobIssMt,Boolean deactive);
	
	List<JobIssLabDt> findByJobIssDtAndDeactive(JobIssDt jobIssDt,Boolean deactive);
	
	List<JobIssLabDt>findByJobIssDtAndMetalAndDeactive(JobIssDt JobIssDt,Metal metal,Boolean deactive );
}
