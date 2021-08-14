package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigDt;

public interface IConsigCompDtRepository extends JpaRepository<ConsigCompDt, Integer>, QueryDslPredicateExecutor<ConsigCompDt>{

	List<ConsigCompDt>findByConsigDt(ConsigDt consigDt);
}
