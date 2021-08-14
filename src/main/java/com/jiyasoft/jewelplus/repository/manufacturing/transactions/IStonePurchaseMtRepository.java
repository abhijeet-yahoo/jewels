package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StonePurchaseMt;

public interface IStonePurchaseMtRepository extends JpaRepository<StonePurchaseMt, Integer>, QueryDslPredicateExecutor<StonePurchaseMt> {

	StonePurchaseMt  findByInvNoAndDeactive(String invNo,Boolean deactive);
	
	StonePurchaseMt findByUniqueId(Long uniqueId);

}
