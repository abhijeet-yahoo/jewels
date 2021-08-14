package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigMt;

public interface IConsigDtRepository  extends JpaRepository<ConsigDt,Integer>,QueryDslPredicateExecutor<ConsigDt> {

	List<ConsigDt> findByConsigMt(ConsigMt ConsigMt);
	
	ConsigDt findByRefTranDtIdAndTranType(Integer refTranDtId,String tranType);
	
	ConsigDt findByConsigMtAndBarcode(ConsigMt ConsigMt,String barcode);
}
