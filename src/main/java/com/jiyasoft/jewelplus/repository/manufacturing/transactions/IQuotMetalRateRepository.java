package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMetalRate;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMt;

public interface IQuotMetalRateRepository extends JpaRepository<QuotMetalRate, Integer>,
QueryDslPredicateExecutor<QuotMetalRate>{
	
	QuotMetalRate findByQuotMtAndDeactiveAndMetal(QuotMt quotMt,Boolean deactive,Metal metal);
	
	List<QuotMetalRate> findByQuotMtAndDeactive(QuotMt quotMt,Boolean deactive);

}
