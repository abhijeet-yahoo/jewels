package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;



public interface IOrderDtRepository extends JpaRepository<OrderDt, Integer>, QueryDslPredicateExecutor<OrderDt> {

		OrderDt findByUniqueId(Long uniqueId);
		
		List<OrderDt> findByOrderMtAndDeactive(OrderMt orderMt,Boolean deactive);
		

}
