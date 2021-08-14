package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleMt;


public interface ISaleMetalDtRepository extends JpaRepository<SaleMetalDt, Integer>, QueryDslPredicateExecutor<SaleMetalDt>{

	List<SaleMetalDt> findBySaleMt(SaleMt saleMt);
	
	List<SaleMetalDt> findBySaleDt(SaleDt saleDt);
	
	SaleMetalDt findBySaleDtAndMainMetal(SaleDt saleDt,Boolean mainMetal);
	
}
