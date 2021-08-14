package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobMtlIssDt;

public interface IJobMtlIssDtRepository extends JpaRepository<JobMtlIssDt, Integer>, QueryDslPredicateExecutor<JobMtlIssDt>{

	 List<JobMtlIssDt> findByJobIssMt(JobIssMt jobIssMt);

	 List<JobMtlIssDt> findByJobIssMtAndDeactive(JobIssMt jobIssMt, Boolean deactive);
	 
	 JobMtlIssDt findByUniqueId(Long uniqueId);

}
