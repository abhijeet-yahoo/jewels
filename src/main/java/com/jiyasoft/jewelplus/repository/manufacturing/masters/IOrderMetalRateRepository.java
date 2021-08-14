package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMetalRate;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;

public interface IOrderMetalRateRepository extends JpaRepository<OrderMetalRate, Integer>,
QueryDslPredicateExecutor<OrderMetalRate>{
	
OrderMetalRate findByOrderMtAndDeactiveAndMetal(OrderMt orderMt,Boolean deactive,Metal metal);
	
	List<OrderMetalRate> findByOrderMtAndDeactive(OrderMt orderMt,Boolean deactive);

	
}
