package com.jiyasoft.jewelplus.repository.marketing.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.marketing.transactions.StockMeltingMt;

public interface IStockMeltingMtRepository extends JpaRepository<StockMeltingMt, Integer>,
QueryDslPredicateExecutor<StockMeltingMt>{

}
