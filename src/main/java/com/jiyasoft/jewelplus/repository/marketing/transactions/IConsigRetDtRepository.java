package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleMt;

public interface IConsigRetDtRepository extends JpaRepository<ConsigRetDt,Integer>,QueryDslPredicateExecutor<ConsigRetDt> {

	List<ConsigRetDt> findByConsigRetMt(ConsigRetMt consigRetMt);
	
	ConsigRetDt findByRefConsigDtId(Integer refConsigDtId);

	ConsigRetDt findByConsigRetMtAndBarcode(ConsigRetMt consigRetMt,String barcode);
}

