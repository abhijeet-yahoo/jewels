package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotQuality;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMt;

public interface IQuotQualityRepository extends JpaRepository<QuotQuality, Integer>,QueryDslPredicateExecutor<QuotQuality>  {
	
	List<QuotQuality> findByQuotMtAndDeactive(QuotMt quotMt,Boolean deactive);
	
}
