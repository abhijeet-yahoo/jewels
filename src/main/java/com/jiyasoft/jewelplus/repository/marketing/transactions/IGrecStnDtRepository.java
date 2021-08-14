package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecStnDt;

public interface IGrecStnDtRepository extends JpaRepository<GrecStnDt, Integer>, QueryDslPredicateExecutor<GrecStnDt> {

	List<GrecStnDt> findByGrecMt(GrecMt grecMt);
	
	List<GrecStnDt> findByGrecDt(GrecDt grecDt);
	
}
