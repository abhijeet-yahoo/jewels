package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.JobIssMt;

public interface IJobIssMtRepository extends JpaRepository<JobIssMt, Integer>, QueryDslPredicateExecutor<JobIssMt>{

	JobIssMt findByInvNoAndDeactive(String invNo,Boolean deactive);
		
	JobIssMt findByUniqueId(Long uniqueId);
}
