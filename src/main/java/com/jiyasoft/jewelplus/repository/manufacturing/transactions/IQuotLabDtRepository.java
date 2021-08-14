package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMt;


public interface IQuotLabDtRepository extends JpaRepository<QuotLabDt, Integer>,
QueryDslPredicateExecutor<QuotLabDt>{
	
	List<QuotLabDt> findByQuotMtAndDeactive(QuotMt quotMt,Boolean deactive);
	
	List<QuotLabDt> findByQuotDtAndDeactive(QuotDt quotDt,Boolean deactive);
	
	List<QuotLabDt>findByQuotDtAndMetalAndDeactive(QuotDt quotDt,Metal metal,Boolean deactive );


}
