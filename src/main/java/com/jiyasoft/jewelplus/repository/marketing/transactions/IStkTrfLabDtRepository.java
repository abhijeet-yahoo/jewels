package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigLabDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfLabDt;

public interface IStkTrfLabDtRepository extends JpaRepository<StkTrfLabDt,Integer>,
QueryDslPredicateExecutor<ConsigLabDt>{
	
	List<StkTrfLabDt> findByStkTrfDt(StkTrfDt stkTrfDt);

	List<StkTrfLabDt> findByStkTrfDtAndMetal(StkTrfDt stkTrfDt,Metal metal);
	

}
