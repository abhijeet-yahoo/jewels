package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;

public interface IBagMtRepository extends JpaRepository<BagMt, Integer>,
		QueryDslPredicateExecutor<BagMt> {

	BagMt findByName(String name);

	List<BagMt> findByOrderMt(OrderMt orderMt);

	List<BagMt> findByOrderMtAndOrderDt(OrderMt orderMt, OrderDt orderDt);
	
	List<BagMt> findByItemNo(String itemNo);
	
	BagMt findByItemNoAndDeactive(String itemNo,Boolean deactive);
	
	List<BagMt> findByOrderDtAndDeactive(OrderDt orderDt, Boolean deactive);
	
	BagMt findByBarcode(String barcode);

 }
