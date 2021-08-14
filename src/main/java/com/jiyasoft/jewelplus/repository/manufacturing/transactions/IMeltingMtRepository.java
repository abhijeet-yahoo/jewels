package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MeltingMt;

public interface IMeltingMtRepository extends
		JpaRepository<MeltingMt, Integer>, QueryDslPredicateExecutor<MeltingMt> {
	
	MeltingMt findByInvNoAndDeactive(String invNo,Boolean deactive);
	
	MeltingMt findByUniqueId(Long uniqueId);

}
