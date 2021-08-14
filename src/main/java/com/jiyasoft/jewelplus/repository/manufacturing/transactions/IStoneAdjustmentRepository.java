package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneAdjustment;

public interface IStoneAdjustmentRepository extends JpaRepository<StoneAdjustment, Integer>,
QueryDslPredicateExecutor<StoneAdjustment>{
	
	StoneAdjustment findByUniqueId(Long uniqueId);

}
