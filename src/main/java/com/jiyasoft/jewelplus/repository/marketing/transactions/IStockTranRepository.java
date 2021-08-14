package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.marketing.transactions.StockTran;

public interface IStockTranRepository extends JpaRepository<StockTran,Integer>,
QueryDslPredicateExecutor<StockTran>{

	 StockTran findByBarcodeAndCurrStk(String barcode,Boolean currStk);
	 
	 StockTran findByTranTypeAndRefTranIdAndCurrStk(String tranType,Integer refTranId,Boolean currStk);
	 
	 StockTran findByTranTypeAndRefTranIdAndCurrStatus(String tranType,Integer refTranId,String currStatus);
	 
	 List <StockTran> findByBarcode(String barcode);
	 
	 List <StockTran> findByTranTypeAndRefTranId(String tranType,Integer refTranId);
	
}
