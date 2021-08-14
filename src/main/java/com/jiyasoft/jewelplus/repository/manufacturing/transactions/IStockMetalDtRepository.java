package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMt;

public interface IStockMetalDtRepository extends JpaRepository<StockMetalDt, Integer>,
QueryDslPredicateExecutor<StockMetalDt>{
	
	List<StockMetalDt> findByStockMtAndDeactive(StockMt stockMt,Boolean deactive);

}
