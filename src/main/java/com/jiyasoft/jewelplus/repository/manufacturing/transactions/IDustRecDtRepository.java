package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.DustMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.DustRecDt;

public interface IDustRecDtRepository extends JpaRepository<DustRecDt, Integer>,QueryDslPredicateExecutor<Integer> {
	
	List<DustRecDt> findByDustMtAndDeactive(DustMt dustMt,Boolean deactive);
	
	DustRecDt findByUniqueId(Long uniqueId);

}
