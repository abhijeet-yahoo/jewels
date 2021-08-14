package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfMt;

public interface IStkTrfMtRepository extends JpaRepository<StkTrfMt,Integer>,QueryDslPredicateExecutor<StkTrfMt> {

	StkTrfMt findByInvNo(String invNo);
	
	List<StkTrfMt> findByParty(Party party);
	
	List<StkTrfMt> findByToLocationAndTranType(Department toLocation,String tranType);
	

}
