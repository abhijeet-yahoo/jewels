package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.DustDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.DustMt;

public interface IDustDtRepository extends JpaRepository<DustDt, Integer>,QueryDslPredicateExecutor<DustDt> {

	List<DustDt> findByDustMtAndDeactive(DustMt dustMt,Boolean deactive);
	
}
