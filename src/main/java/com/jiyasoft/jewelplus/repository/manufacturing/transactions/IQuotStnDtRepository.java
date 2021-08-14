package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotStnDt;

public interface IQuotStnDtRepository extends JpaRepository<QuotStnDt, Integer>,
QueryDslPredicateExecutor<QuotStnDt>{
	
	List<QuotStnDt> findByQuotMtAndDeactive(QuotMt quotMt,Boolean deactive);
	
	List<QuotStnDt> findByQuotDtAndDeactive(QuotDt quotDt,Boolean deactive);
	
	
	


}
