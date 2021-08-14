package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetMt;

public interface ISaleRetMtRepository extends JpaRepository<SaleRetMt, Integer>, QueryDslPredicateExecutor<SaleRetMt>{

	
	SaleRetMt findByInvNo(String invNo);
	
	List<SaleRetMt> findByParty(Party party);
	

}
