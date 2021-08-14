package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMt;

public interface IQuotMetalRepository extends JpaRepository<QuotMetal, Integer>,QueryDslPredicateExecutor<QuotMetal> {
	
	List<QuotMetal> findByQuotDtAndDeactive(QuotDt quotDt,Boolean deactive);
	
	QuotMetal findByQuotDtAndDeactiveAndMainMetal(QuotDt quotDt,Boolean deactive,Boolean mainMetal);

	QuotMetal findByQuotDtAndDeactiveAndPartNm(QuotDt quotDt,Boolean deactive,LookUpMast lookUpMast);

	List<QuotMetal> findByQuotMtAndDeactive(QuotMt quotMt,Boolean deactive);
	
	


}
