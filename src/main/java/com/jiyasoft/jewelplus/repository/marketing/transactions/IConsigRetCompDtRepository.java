package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetDt;

public interface IConsigRetCompDtRepository  extends JpaRepository<ConsigRetCompDt, Integer>, QueryDslPredicateExecutor<ConsigRetCompDt>{

	List<ConsigRetCompDt>findByConsigRetDt(ConsigRetDt consigRetDt);
}
