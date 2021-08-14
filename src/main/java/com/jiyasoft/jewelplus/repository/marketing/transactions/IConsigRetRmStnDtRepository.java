package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetRmStnDt;

public interface IConsigRetRmStnDtRepository
		extends JpaRepository<ConsigRetRmStnDt, Integer>, QueryDslPredicateExecutor<ConsigRetRmStnDt> {

	List<ConsigRetRmStnDt> findByConsigRetMt(ConsigRetMt consigRetMt);

	ConsigRetRmStnDt findByUniqueId(Long uniqueId);
}
