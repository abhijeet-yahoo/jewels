package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.KtConversionDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.KtConversionMt;

public interface IKtConversionDtRepository extends
		JpaRepository<KtConversionDt, Integer>,
		QueryDslPredicateExecutor<KtConversionDt> {
	
	List<KtConversionDt> findByKtConversionMtAndDeactive(KtConversionMt ktConversionMt,Boolean deactive);
	
	KtConversionDt findByUniqueId(Long uniqueId);
}
