package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobCompRecDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecMt;

public interface IJobCompRecDtRepository extends JpaRepository<JobCompRecDt, Integer>, QueryDslPredicateExecutor<JobCompRecDt>{

	List<JobCompRecDt> findByJobRecMtAndDeactive(JobRecMt jobRecMt, Boolean deactive);
	
	JobCompRecDt findByUniqueId(Long uniqueId);
	
}
