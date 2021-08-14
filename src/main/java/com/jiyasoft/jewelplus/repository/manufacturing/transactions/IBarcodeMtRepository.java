package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BarcodeMt;

public interface IBarcodeMtRepository extends JpaRepository<BarcodeMt, Integer>, QueryDslPredicateExecutor<BarcodeMt> {
	
	BarcodeMt  findByInvNoAndDeactive(String invNo,Boolean deactive);

}
