package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ComponentPurchaseMt;

public interface IComponentPurchaseMtRepository extends JpaRepository<ComponentPurchaseMt, Integer>, QueryDslPredicateExecutor<ComponentPurchaseMt>{

	ComponentPurchaseMt  findByInvNoAndDeactive(String invNo,Boolean deactive);
	
	ComponentPurchaseMt findByUniqueId(Long uniqueId);

}
