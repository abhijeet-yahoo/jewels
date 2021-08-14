package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockStnDt;

public interface IStockStnDtRepository extends JpaRepository<StockStnDt, Integer>,
QueryDslPredicateExecutor<StockStnDt>{
	
	List<StockStnDt> findByStockMtAndDeactive(StockMt stockMt,Boolean deactive);

}
