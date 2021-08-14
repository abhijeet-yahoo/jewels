package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobStnRecDt;

public interface IJobStnRecDtRepository extends JpaRepository<JobStnRecDt, Integer>, QueryDslPredicateExecutor<JobStnRecDt>{

	List<JobStnRecDt> findByJobRecMtAndDeactive(JobRecMt jobRecMt, Boolean deactive);
	
	JobStnRecDt findByUniqueId(Long uniqueId);

}
