package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssStnDt;

public interface IJobIssStnDtRepository extends JpaRepository<JobIssStnDt, Integer>, QueryDslPredicateExecutor<JobIssStnDt>{

List<JobIssStnDt> findByJobIssMtAndDeactive(JobIssMt jobIssMt,Boolean deactive);
	
	List<JobIssStnDt> findByJobIssDtAndDeactive(JobIssDt jobIssDt,Boolean deactive);
	
	
}
