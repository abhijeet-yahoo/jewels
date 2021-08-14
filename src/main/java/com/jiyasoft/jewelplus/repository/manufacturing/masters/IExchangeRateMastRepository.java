package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.ExchangeRateMaster;

public interface IExchangeRateMastRepository extends JpaRepository<ExchangeRateMaster, Integer>,QueryDslPredicateExecutor<ExchangeRateMaster> {


	
	
}
