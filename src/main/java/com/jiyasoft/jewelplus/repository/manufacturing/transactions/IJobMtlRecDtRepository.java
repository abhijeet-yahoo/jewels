package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobMtlIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobMtlRecDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecMt;

public interface IJobMtlRecDtRepository extends JpaRepository<JobMtlRecDt, Integer>, QueryDslPredicateExecutor<JobMtlRecDt>{

	 List<JobMtlRecDt> findByJobRecMtAndDeactive(JobRecMt jobRecMt, Boolean deactive);
	 
	 JobMtlRecDt findByUniqueId(Long uniqueId);


}
