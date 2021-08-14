package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecMt;

public interface IJobRecCompDtRepository extends JpaRepository<JobRecCompDt, Integer>, QueryDslPredicateExecutor<JobRecCompDt>{
	
	 List<JobRecCompDt> findByJobRecMtAndDeactive(JobRecMt jobRecMt,Boolean deactive);
	
	 List<JobRecCompDt> findByJobRecDtAndDeactive(JobRecDt jobRecDt,Boolean deactive);
	
}
