package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnBflMt;

public interface IStnBflMtRepository extends JpaRepository<StnBflMt, Integer>,QueryDslPredicateExecutor<StnBflMt>{

	
     StnBflMt findByInvNoAndDeactive(String invNo,Boolean deactive);
	
	 StnBflMt findByUniqueId(Long uniqueId);
}
