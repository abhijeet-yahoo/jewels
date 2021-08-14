package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.marketing.transactions.StockMeltingDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StockMeltingMt;

public interface IStockMeltingDtRepository extends JpaRepository<StockMeltingDt, Integer>,
QueryDslPredicateExecutor<StockMeltingDt>{

	List<StockMeltingDt>findByStockMeltingMtOrderByIdDesc(StockMeltingMt stockMeltingMt);
	
	List<StockMeltingDt>findByPendApprovalFlgAndCurrStk(Boolean pendApprovalFlg, Boolean currStk);
}
