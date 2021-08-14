package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.KtConversionMt;

public interface IKtConversionMtRepository extends
		JpaRepository<KtConversionMt, Integer>,
		QueryDslPredicateExecutor<KtConversionMt> {
	
	
	KtConversionMt findByInvNoAndDeactive(String invNo,Boolean deactive);

	KtConversionMt findByUniqueId(Long uniqueId);

}
