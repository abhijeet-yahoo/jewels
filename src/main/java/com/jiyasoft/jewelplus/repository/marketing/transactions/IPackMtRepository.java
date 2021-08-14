package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackMt;

public interface IPackMtRepository extends JpaRepository<PackMt,Integer>,
QueryDslPredicateExecutor<PackMt>{
	
	PackMt  findByInvNo(String invNo);
	
	List<PackMt> findByParty(Party party);

}
