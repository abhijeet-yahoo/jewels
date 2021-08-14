package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecMt;

public interface IJobRecLabDtRepository extends JpaRepository<JobRecLabDt, Integer>, QueryDslPredicateExecutor<JobRecLabDt>{

	List<JobRecLabDt> findByJobRecMtAndDeactive(JobRecMt jobRecMt,Boolean deactive);
	
	List<JobRecLabDt> findByJobRecDtAndDeactive(JobRecDt jobRecDt,Boolean deactive);
	
	List<JobRecLabDt>findByJobRecDtAndMetalAndDeactive(JobRecDt JobRecDt,Metal metal,Boolean deactive );
}
