package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfDt;

public interface IStkTrfCompDtRepository extends JpaRepository<StkTrfCompDt, Integer>,
QueryDslPredicateExecutor<StkTrfCompDt>{
	
	List<StkTrfCompDt>findByStkTrfDt(StkTrfDt stkTrfDt);

}
