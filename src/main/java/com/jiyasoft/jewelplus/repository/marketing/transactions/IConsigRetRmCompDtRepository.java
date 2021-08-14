package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetRmCompDt;

public interface IConsigRetRmCompDtRepository
		extends JpaRepository<ConsigRetRmCompDt, Integer>, QueryDslPredicateExecutor<ConsigRetRmCompDt> {

	List<ConsigRetRmCompDt> findByConsigRetMt(ConsigRetMt consigRetMt);

	ConsigRetRmCompDt findByUniqueId(Long uniqueId);
}
