package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.FgIssMt;

public interface IFgIssMtRepository extends JpaRepository<FgIssMt, Integer>,
QueryDslPredicateExecutor<FgIssMt>{
	
	FgIssMt  findByInvNoAndDeactive(String invNo,Boolean deactive);
	

}
