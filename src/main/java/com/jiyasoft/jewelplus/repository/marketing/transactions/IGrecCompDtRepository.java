package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecMt;

public interface IGrecCompDtRepository extends JpaRepository<GrecCompDt, Integer>, QueryDslPredicateExecutor<GrecCompDt> {

	List<GrecCompDt> findByGrecMtAndGrecDt(GrecMt grecMt, GrecDt grecDt);
	
	List<GrecCompDt> findByGrecMt(GrecMt grecMt);

	List<GrecCompDt> findByGrecDt(GrecDt grecDt);
	
	
}
