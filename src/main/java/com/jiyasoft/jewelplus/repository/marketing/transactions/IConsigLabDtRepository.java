package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigLabDt;

public interface IConsigLabDtRepository extends JpaRepository<ConsigLabDt,Integer>,
QueryDslPredicateExecutor<ConsigLabDt>{
	
	List<ConsigLabDt>findByConsigDt(ConsigDt consigDt);

	List<ConsigLabDt> findByConsigDtAndMetal(ConsigDt consigDt,Metal metal);
	
}
