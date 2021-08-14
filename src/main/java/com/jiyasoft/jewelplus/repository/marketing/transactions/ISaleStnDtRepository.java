package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleStnDt;



public interface ISaleStnDtRepository extends JpaRepository<SaleStnDt, Integer>,QueryDslPredicateExecutor<SaleStnDt> {

	
	List<SaleStnDt> findBySaleMt(SaleMt saleMt);

	List<SaleStnDt> findBySaleDt(SaleDt saleDt);
}
