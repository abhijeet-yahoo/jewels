package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetMt;

public interface IConsigRetMtRepository extends JpaRepository<ConsigRetMt,Integer>, QueryDslPredicateExecutor<ConsigRetMt> {

	ConsigRetMt findByInvNo(String invNo);
	
	List<ConsigRetMt> findByParty(Party party);
}
