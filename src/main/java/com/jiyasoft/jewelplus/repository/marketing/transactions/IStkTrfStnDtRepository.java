package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;


import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfStnDt;

public interface IStkTrfStnDtRepository extends JpaRepository<StkTrfStnDt, Integer>,
QueryDslPredicateExecutor<StkTrfStnDt>{

	List<StkTrfStnDt> findByStkTrfDt(StkTrfDt stkTrfDt);

}
