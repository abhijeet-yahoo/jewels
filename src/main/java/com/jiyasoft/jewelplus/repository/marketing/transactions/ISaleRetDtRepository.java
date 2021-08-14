package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetMt;

public interface ISaleRetDtRepository extends JpaRepository<SaleRetDt, Integer>, QueryDslPredicateExecutor<SaleRetDt> {

	SaleRetDt findByRefSaleDtId(Integer refSaleDtId);
	
	List<SaleRetDt> findBySaleRetMt(SaleRetMt saleRetMt);
	
	SaleRetDt findBySaleRetMtAndBarcode(SaleRetMt saleRetMt,String barcode);
}
