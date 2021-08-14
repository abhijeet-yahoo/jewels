package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetRmMetalDt;

public interface IConsigRetRmMetalDtRepository extends JpaRepository<ConsigRetRmMetalDt, Integer>, QueryDslPredicateExecutor<ConsigRetRmMetalDt> {

	ConsigRetRmMetalDt findByUniqueId(Long uniqueId);

	List<ConsigRetRmMetalDt> findByConsigRetMt(ConsigRetMt consigRetMt);
}
