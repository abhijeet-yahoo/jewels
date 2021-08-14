package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMt;


public interface IQuotCompDtRepository extends JpaRepository<QuotCompDt, Integer>,
QueryDslPredicateExecutor<QuotCompDt>{
	
	List<QuotCompDt> findByQuotMtAndDeactive(QuotMt quotMt,Boolean deactive);
	
	List<QuotCompDt> findByQuotDtAndDeactive(QuotDt quotDt,Boolean deactive);


}
