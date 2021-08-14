package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleMt;



public interface ISaleDtRepository extends JpaRepository<SaleDt, Integer>,QueryDslPredicateExecutor<SaleDt> {
	
	List<SaleDt> findBySaleMt(SaleMt saleMt);
	
	SaleDt findByRefTranDtIdAndTranType(Integer refTranDtId,String tranType);
	
	SaleDt findBySaleMtAndBarcode(SaleMt saleMt,String barcode);
}
