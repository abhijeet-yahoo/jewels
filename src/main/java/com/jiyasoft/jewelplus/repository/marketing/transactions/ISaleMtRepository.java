package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleMt;


public interface ISaleMtRepository extends JpaRepository<SaleMt, Integer>, QueryDslPredicateExecutor<SaleMt> {

	
	SaleMt findByInvNo(String invNo);
	
	List<SaleMt> findByParty(Party party);

	
}
