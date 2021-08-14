package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecMt;

public interface IGrecDtRepository extends JpaRepository<GrecDt, Integer>, QueryDslPredicateExecutor<GrecDt> {
	
	GrecDt findByUniqueId(Long uniqueId);
	
	List<GrecDt> findByGrecMt(GrecMt grecMt);
}
