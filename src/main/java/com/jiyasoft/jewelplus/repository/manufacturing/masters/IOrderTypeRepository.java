package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderType;

public interface IOrderTypeRepository extends
		JpaRepository<OrderType, Integer>, QueryDslPredicateExecutor<OrderType> {
	
	OrderType findByName(String name);
	
}
