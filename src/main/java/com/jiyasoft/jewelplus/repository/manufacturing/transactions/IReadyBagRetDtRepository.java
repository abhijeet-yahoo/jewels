package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ReadyBag;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ReadyBagRetDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ReadyBagRetMt;

public interface IReadyBagRetDtRepository extends JpaRepository<ReadyBagRetDt, Integer>, QueryDslPredicateExecutor<ReadyBagRetDt> {

	List<ReadyBagRetDt> findByReadyBagRetMt(ReadyBagRetMt readyBagRetMt);
	
	List<ReadyBagRetDt> findByBagMt(BagMt bagMt);
	
	List<ReadyBagRetDt> findByPendApprovalFlg(Boolean pendApprovalFlg);
	
}
