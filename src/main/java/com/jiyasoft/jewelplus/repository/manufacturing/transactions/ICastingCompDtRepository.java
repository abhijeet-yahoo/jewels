package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CastingCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CastingMt;

public interface ICastingCompDtRepository extends
		JpaRepository<CastingCompDt, Integer>,
		QueryDslPredicateExecutor<CastingCompDt> {
	
	
	List<CastingCompDt> findByCastingMtAndDeactive(CastingMt castingMt,Boolean deactive);

}
