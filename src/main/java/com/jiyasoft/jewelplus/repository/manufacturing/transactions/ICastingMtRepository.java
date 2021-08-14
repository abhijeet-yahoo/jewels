package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CastingMt;

public interface ICastingMtRepository extends JpaRepository<CastingMt, Integer> ,
QueryDslPredicateExecutor<CastingMt>{
	
	
	public CastingMt findByCDateAndTreeNo(Date cDate,String treeNo);
	
	public CastingMt findByUniqueId(Long uniqueId);
	
	public List<CastingMt> findByCDate(Date cDAte);
}
