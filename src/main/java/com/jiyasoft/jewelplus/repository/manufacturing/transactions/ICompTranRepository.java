package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Component;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompTran;


public interface ICompTranRepository extends JpaRepository<CompTran, Integer>,
		QueryDslPredicateExecutor<CompTran> {

	List<CompTran> findByRefTranIdAndTranType(Integer refTranId, String tranType);

	CompTran RefTranIdAndTranType(Integer refTranId, String tranType);
	
	List<CompTran> findByBagMtAndTranTypeAndInOutFld(BagMt bagMt, String tranType, String inOutFld);
	
	List<CompTran>  findByBagMtAndDeactive(BagMt bagMt,Boolean deactive);
	
	List<CompTran> findByBagMtAndPurityAndColorAndComponent(BagMt bagMt,Purity purity,Color color,Component component);
	
	List<CompTran>  findByTranMtIdAndDeactive(Integer tranMtId,Boolean deactive);

}
