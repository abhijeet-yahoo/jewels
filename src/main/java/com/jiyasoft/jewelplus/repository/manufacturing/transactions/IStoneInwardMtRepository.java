package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneInwardMt;

public interface IStoneInwardMtRepository extends JpaRepository<StoneInwardMt, Integer>,
QueryDslPredicateExecutor<StoneInwardMt>{
	
	StoneInwardMt  findByInvNoAndDeactive(String invNo,Boolean deactive);
	
	StoneInwardMt findByUniqueId(Long uniqueId);

}
