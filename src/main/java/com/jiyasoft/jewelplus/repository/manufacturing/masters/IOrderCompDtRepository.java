package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;


public interface IOrderCompDtRepository extends JpaRepository<OrderCompDt, Integer>, QueryDslPredicateExecutor<OrderCompDt> {

	List<OrderCompDt> findByOrderMtAndOrderDt(OrderMt orderMt, OrderDt orderDt);
	
	List<OrderCompDt> findByOrderMtAndDeactive(OrderMt orderMt,Boolean deactive);

	List<OrderCompDt> findByOrderDtAndDeactive(OrderDt orderDt,Boolean deactive);
	
	Page<OrderCompDt> findByOrderMt(OrderMt orderMt, Pageable pageable);

}
