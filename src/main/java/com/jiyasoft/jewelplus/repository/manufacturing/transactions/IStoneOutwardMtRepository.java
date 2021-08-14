package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneOutwardMt;

public interface IStoneOutwardMtRepository extends JpaRepository<StoneOutwardMt, Integer>,
QueryDslPredicateExecutor<StoneOutwardMt>{
	
		
	StoneOutwardMt  findByInvNoAndDeactive(String invNo,Boolean deactive);
	
	StoneOutwardMt findByUniqueId(Long uniqueId);
	

}
