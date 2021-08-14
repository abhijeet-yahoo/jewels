package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalTran;

public interface IMetalTranRepository extends
		JpaRepository<MetalTran, Integer>, QueryDslPredicateExecutor<MetalTran> {

	List<MetalTran> findByRefTranIdAndTranTypeAndDeactive(Integer refTranId, String tranType,Boolean deactive);
	
	MetalTran RefTranIdAndTranType(Integer refTranId, String tranType);
		
	MetalTran findByRefTranIdAndTranTypeAndPcsWtAndStubWtAndDeactiveAndBagMt(Integer refTranId, String tranType, Boolean pcsWt, Boolean stubWt,Boolean deactive,BagMt bagMt);
	
	List<MetalTran> findByRefTranIdAndTranTypeAndPcsWtAndStubWtAndSrNoAndDeactive(Integer refTranId, String tranType, Boolean pcsWt, Boolean stubWt, Integer srNo,Boolean deactive);
	
	
	 MetalTran findByRefTranIdAndTranTypeAndSrNo(Integer refTranId, String tranType, Integer srNo);
	 
	 //melting
	 MetalTran findByRefTranIdAndTranTypeAndInOutFld(Integer refTranId,String tranType,String inOutFld);
	 
	 //metalAddition
	 List<MetalTran> findByBagMtAndTranTypeAndInOutFld(BagMt bagMt, String tranType, String inOutFld);
	 
	 MetalTran findByRefTranIdAndTranTypeAndBagMtAndPcsWtAndDeactive(Integer refTranId, String tranType,BagMt bagMt,Boolean pcsWt,Boolean deactive);

	//List<MetalTran> findByTranTypeAndInOutFld(String tranType, String inOutFld);
	
	List<MetalTran> findByStyleIdAndTranTypeAndInOutFld(Integer styleId,String tranType, String inOutFld);
	 
	MetalTran findByTranTypeAndBagMtAndPcsWtAndDeactiveAndPartNm(String tranType,BagMt bagMt,Boolean pcsWt,Boolean deactive,LookUpMast partNm);
	
	List<MetalTran>  findByTranMtIdAndDeactive(Integer tranMtId,Boolean deactive);
	
	 
}
