package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecStnDt;

public interface IJobRecStnDtRepository extends JpaRepository<JobRecStnDt, Integer>, QueryDslPredicateExecutor<JobRecStnDt>{

	List<JobRecStnDt> findByJobRecMtAndDeactive(JobRecMt jobRecMt,Boolean deactive);
	
	List<JobRecStnDt> findByJobRecDtAndDeactive(JobRecDt jobRecDt,Boolean deactive);
	
	
}
