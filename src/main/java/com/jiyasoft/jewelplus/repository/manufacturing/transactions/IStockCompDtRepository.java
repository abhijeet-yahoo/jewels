package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMt;

public interface IStockCompDtRepository extends JpaRepository<StockCompDt, Integer>,
QueryDslPredicateExecutor<StockCompDt>{

	List<StockCompDt> findByStockMtAndDeactive(StockMt stockMt,Boolean deactive);
}
