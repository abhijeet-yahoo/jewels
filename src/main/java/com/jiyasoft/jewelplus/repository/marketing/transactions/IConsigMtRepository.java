package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigMt;

public interface IConsigMtRepository extends JpaRepository<ConsigMt,Integer>,
QueryDslPredicateExecutor<ConsigMt> {

	List<ConsigMt> findByParty(Party party);

}
