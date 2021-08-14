package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalPurchaseMt;

public interface IMetalPurchaseMtRepository extends JpaRepository<MetalPurchaseMt	,Integer>, QueryDslPredicateExecutor<MetalPurchaseMt>{
	

	 MetalPurchaseMt findByInvNoAndDeactive(String invNo,Boolean deactive);
	
	MetalPurchaseMt findByUniqueId(Long uniqueId);

}
