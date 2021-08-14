package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMt;

public interface IStockMtRepository extends JpaRepository<StockMt, Integer>,
QueryDslPredicateExecutor<StockMt>{
	
	
StockMt findByBarcodeAndCurrStkAndDeactive(String barcode,Boolean currstk,Boolean deactive);

List<StockMt> findByLocationAndCurrStkAndDeactive(Department location,Boolean currstk,Boolean deactive);

StockMt findByRefTranIdAndTranTypeAndDeactive(Integer refTranId,String TranType,Boolean deactive);
	
List<StockMt> findByBarcodeAndDeactiveOrderByMtIdDesc(String barcode,Boolean deactive);

StockMt findByBarcodeAndCurrStkAndDeactiveAndLocation(String barcode,Boolean currstk,Boolean deactive,Department location);
}
