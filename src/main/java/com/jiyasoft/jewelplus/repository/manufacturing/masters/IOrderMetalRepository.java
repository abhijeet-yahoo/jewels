package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;


public interface IOrderMetalRepository extends JpaRepository<OrderMetal, Integer>,
QueryDslPredicateExecutor<OrderMetal>{
	
List<OrderMetal> findByOrderDtAndDeactive(OrderDt orderDt,Boolean deactive);
	
	OrderMetal findByOrderDtAndDeactiveAndMainMetal(OrderDt orderDt,Boolean deactive,Boolean mainMetal);

	OrderMetal findByOrderDtAndDeactiveAndPartNm(OrderDt orderDt,Boolean deactive,LookUpMast lookUpMast);

	List<OrderMetal> findByOrderMtAndDeactive(OrderMt orderMt,Boolean deactive);



}
