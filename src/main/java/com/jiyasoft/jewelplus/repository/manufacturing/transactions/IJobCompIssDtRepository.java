package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobCompIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssMt;

public interface IJobCompIssDtRepository extends JpaRepository<JobCompIssDt, Integer>, QueryDslPredicateExecutor<JobCompIssDt>{

	List<JobCompIssDt> findByJobIssMtAndDeactive(JobIssMt jobIssMt, Boolean deactive);
	
	JobCompIssDt findByUniqueId(Long uniqueId);

}
