package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalTran;

public interface IMetalTranService {

	public List<MetalTran> findAll();

	public void save(MetalTran metalTran);

	public void delete(int id);
	
	public MetalTran findOne(int id);
	
	public Long count();

	public List<MetalTran> findByRefTranIdAndTranTypeAndDeactive(Integer refTranId, String tranType,Boolean deactive);
	
	public Double getStockBalance(Integer purityId,Integer colorId,Integer locationId);
	
	/*public Double getUsedMetalStockBalance(Integer purityId,Integer colorId,Integer locationId);*/
	
	public Double getPcsWtStockBalance(Integer purityId,Integer colorId,Integer locationId);
	
	public MetalTran findByRefTranIdAndTranTypeAndPcsWtAndStubWtAndDeactiveAndBagMt(Integer refTranId, String tranType, Boolean pcsWt, Boolean stubWt,Boolean deactive,BagMt bagMt);
	
	public List<MetalTran> findByRefTranIdAndTranTypeAndPcsWtAndStubWtAndSrNoAndDeactive(Integer refTranId, String tranType, Boolean pcsWt, Boolean stubWt, Integer srNo,Boolean deactive);
	
	public MetalTran RefTranIdAndTranType(Integer refTranId, String tranType);
	
	public MetalTran findByRefTranIdAndTranTypeAndSrNo(Integer refTranId, String tranType, Integer srNo);
	
	public MetalTran findByRefTranIdAndTranTypeAndInOutFld(Integer refTranId,String tranType,String inOutFld);

	public  List<MetalTran> findByBagMtAndTranTypeAndInOutFld(BagMt bagMt, String tranType, String inOutFld);

	//public  List<MetalTran> findByTranTypeAndInOutFld(String tranType, String inOutFld);
	
	public List<MetalTran> findByStyleIdAndTranTypeAndInOutFld(Integer styleId,String tranType, String inOutFld);
	
	public MetalTran findByRefTranIdAndTranTypeAndBagMtAndPcsWtAndDeactive(Integer refTranId, String tranType,BagMt bagMt,Boolean pcsWt,Boolean deactive);
	
	//public MetalTran getMetalTranByRefTranIdAndTranTypeAndDeactive(Integer refTranId, String tranType, Boolean deactive);
	
	public String metalAdditionSave(String vBagNo,MetalTran metalTran,String vPresentDept,Principal principal,Date vTranDate);
	
	public String metalDeductionSave(String vBagNo,MetalTran metalTran,String vPresentDept,Principal principal,Date vTranDate);
	
	
	public MetalTran findByTranTypeAndBagMtAndPcsWtAndDeactiveAndPartNm(String tranType,BagMt bagMt,Boolean pcsWt,Boolean deactive,LookUpMast partNm);
	
	public List<MetalTran> findByTranMtIdAndDeactive(Integer tranMtId,Boolean deactive);
	
	
	public Double getMetalRate(Integer purityId,Integer colorId,Integer locationId,Double adjWt);
	
	

}
