package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderStnDt;


public interface IOrderStnDtRepository extends JpaRepository<OrderStnDt, Integer>, QueryDslPredicateExecutor<OrderStnDt> {

	List<OrderStnDt>findByOrderMtAndDeactive(OrderMt orderMt,Boolean deactive);
	
	List<OrderStnDt> findByOrderDtAndDeactive(OrderDt orderDt,Boolean deactive);
	
	List<OrderStnDt> findByOrderDtAndDeactiveOrderByShapeAscSubShapeAscQualityAscSizeAscCaratAsc(OrderDt orderDt,Boolean deactive);
	
	Page<OrderStnDt> findByOrderMt(OrderMt orderMt, Pageable pageable);
	
	OrderStnDt findByOrderDtAndSrNoAndDeactive(OrderDt orderDt,Integer srNo,Boolean deactive);

}
