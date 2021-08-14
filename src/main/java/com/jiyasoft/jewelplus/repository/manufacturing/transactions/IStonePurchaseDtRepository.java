package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StonePurchaseDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StonePurchaseMt;

public interface IStonePurchaseDtRepository extends JpaRepository<StonePurchaseDt, Integer>, QueryDslPredicateExecutor<StonePurchaseDt> {
	
	StonePurchaseDt findByUniqueId(Long uniqueId);

	List<StonePurchaseDt> findByStonePurchaseMtAndDeactive(StonePurchaseMt stonePurchaseMt, Boolean deactive);

}
