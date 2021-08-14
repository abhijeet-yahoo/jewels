package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleMt;


public interface ISaleCompDtRepository extends JpaRepository<SaleCompDt, Integer>, QueryDslPredicateExecutor<SaleCompDt> {


	List<SaleCompDt> findBySaleMt(SaleMt saleMt);
	
	List<SaleCompDt> findBySaleDt(SaleDt saleDt);
}
