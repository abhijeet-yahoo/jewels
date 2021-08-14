package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigStnDt;

public interface IConsigStnDtRepository extends JpaRepository<ConsigStnDt, Integer>,
QueryDslPredicateExecutor<ConsigStnDt>{

	List<ConsigStnDt>findByConsigDt(ConsigDt consigDt);
}
