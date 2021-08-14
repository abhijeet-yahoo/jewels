package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecMt;

public interface IGrecMetalDtRepository extends JpaRepository<GrecMetalDt, Integer>, QueryDslPredicateExecutor<GrecMetalDt>{

	GrecMetalDt findByGrecDtAndMainMetal(GrecDt grecDt,Boolean mainMetal);

	GrecMetalDt findByGrecDtAndPartNm(GrecDt grecDt,LookUpMast lookUpMast);

	List<GrecMetalDt> findByGrecMt(GrecMt grecMt);
	
	List<GrecMetalDt> findByGrecDt(GrecDt grecDt);
	

}
