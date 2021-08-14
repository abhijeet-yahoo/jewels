package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LedgerGroup;



public interface ILedgerGroupRepository extends JpaRepository<LedgerGroup, Integer>, QueryDslPredicateExecutor<LedgerGroup>{

	
	LedgerGroup findByName(String name);
	
	
}
