package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalInwardMt;

public interface IMetalInwardMtRepository extends
		JpaRepository<MetalInwardMt, Integer>,
		QueryDslPredicateExecutor<MetalInwardMt> {

	public MetalInwardMt findByInvNoAndDeactive(String invNo,Boolean deactive);
	
	MetalInwardMt findByUniqueId(Long uniqueId);

}