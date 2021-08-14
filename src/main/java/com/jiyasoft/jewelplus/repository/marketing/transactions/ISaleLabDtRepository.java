package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleLabDt;


public interface ISaleLabDtRepository extends JpaRepository<SaleLabDt, Integer>, QueryDslPredicateExecutor<SaleLabDt> {


	List<SaleLabDt> findBySaleDt(SaleDt saleDt);
	
	List<SaleLabDt> findBySaleDtAndMetal(SaleDt saleDt, Metal metal);
	
}
