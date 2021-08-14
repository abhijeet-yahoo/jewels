package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMt;

public interface IQuotMtRepository extends JpaRepository<QuotMt, Integer>,
QueryDslPredicateExecutor<QuotMt>{
	
	QuotMt findByInvNoAndDeactive(String invNo,Boolean deactive);
	
	QuotMt findByRefNoAndDeactive(String refNo,Boolean deactive);
	
	QuotMt findByUniqueId(Long uniqueId);

	QuotMt findByPartyAndMasterFlgAndDeactive(Party party,Boolean masterFlg,Boolean deactive);
	

}
