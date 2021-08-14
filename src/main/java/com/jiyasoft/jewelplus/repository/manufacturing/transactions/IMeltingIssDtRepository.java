package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MeltingIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MeltingMt;

public interface IMeltingIssDtRepository extends
		JpaRepository<MeltingIssDt, Integer>,
		QueryDslPredicateExecutor<MeltingIssDt> {
	
	List<MeltingIssDt> findByMeltingMtAndDeactive(MeltingMt meltingMt,Boolean deactive);
	
	List<MeltingIssDt> findByMeltingMtAndDeactiveAndBagMt(MeltingMt meltingMt,Boolean deactive,BagMt bagMt);
	
	MeltingIssDt findByUniqueId(Long uniqueId);
	
	MeltingIssDt findByTranTypeAndRefTranMetalIdAndDeactive(String tranType, Integer refTranMetalId,Boolean deactive);
	
	List<MeltingIssDt> findByMeltingMtAndBarcodeAndDeactive(MeltingMt meltingMt,String barcode,Boolean deactive);

}
