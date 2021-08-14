package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobRecMt;

public interface IJobRecMtRepository extends JpaRepository<JobRecMt, Integer>, QueryDslPredicateExecutor<JobRecMt>{

	JobRecMt findByInvNoAndDeactive(String invNo,Boolean deactive);
		
	JobRecMt findByUniqueId(Long uniqueId);
}
