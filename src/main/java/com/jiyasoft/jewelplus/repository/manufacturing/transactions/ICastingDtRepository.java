package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CastingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CastingMt;

public interface ICastingDtRepository extends
		JpaRepository<CastingDt, Integer>, QueryDslPredicateExecutor<CastingDt> {

	// public CastingDt findByCDate(Date cDAte);

	List<CastingDt> findByCastingMtAndDeactive(CastingMt castingMt,Boolean deactive);
	
	List<CastingDt>findByCastingMtAndTransferAndDeactive(CastingMt castingMt, Boolean transfer,Boolean deactive);
	
	List<CastingDt> findByBagMtAndDeactive(BagMt bagMt,Boolean deactive);
	
	
	CastingDt findByRefMtIdAndDeactive(Integer refMtId,Boolean deactive);
	
	List<CastingDt> findByTransferTranMtIdAndDeactive(Integer transferTranMtId,Boolean deactive);
	
	
	
	
}
