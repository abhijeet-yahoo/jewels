package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobStnIssDt;

public interface IJobStnIssDtRepository extends JpaRepository<JobStnIssDt, Integer>,QueryDslPredicateExecutor<JobStnIssDt>{

	List<JobStnIssDt> findByJobIssMt(JobIssMt jobIssMt);

	List<JobStnIssDt> findByJobIssMtAndDeactive(JobIssMt jobIssMt, Boolean deactive);
	
	JobStnIssDt findByUniqueId(Long uniqueId);

}
