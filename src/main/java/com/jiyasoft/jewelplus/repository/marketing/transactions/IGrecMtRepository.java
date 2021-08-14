package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecMt;

public interface IGrecMtRepository extends JpaRepository<GrecMt, Integer>, QueryDslPredicateExecutor<GrecMt> {

	public GrecMt findByInvNoAndDeactive(String invNo,Boolean deactive);

	public GrecMt findByUniqueId(Long uniqueId);
	
	List<GrecMt> findByPartyAndDeactive(Party party,Boolean deactive);
	
	List<GrecMt> findByOrderCloseAndDeactive(Boolean orderClose,Boolean deactive);

	GrecMt findByInvNoAndDeactiveAndPendApprovalFlg(String invNo,Boolean deactive,Boolean pendApprovalFlg);
}
