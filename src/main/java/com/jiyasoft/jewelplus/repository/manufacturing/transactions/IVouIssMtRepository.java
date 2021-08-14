package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VouIssMt;

public interface IVouIssMtRepository extends JpaRepository<VouIssMt, Integer>,QueryDslPredicateExecutor<VouIssMt> {

	 VouIssMt findByInvNoAndDeactive(String invNo, Boolean deactive); 
	
}
