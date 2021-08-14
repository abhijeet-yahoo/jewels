package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;

public interface IOrderMtRepository extends JpaRepository<OrderMt, Integer>, QueryDslPredicateExecutor<OrderMt> {

	public OrderMt findByInvNoAndDeactive(String invNo,Boolean deactive);

	public OrderMt findByUniqueId(Long uniqueId);
	
	List<OrderMt> findByPartyAndDeactive(Party party,Boolean deactive);
	
	List<OrderMt> findByOrderCloseAndDeactive(Boolean orderClose,Boolean deactive);

	 OrderMt findByInvNoAndDeactiveAndPendApprovalFlg(String invNo,Boolean deactive,Boolean pendApprovalFlg);
}
