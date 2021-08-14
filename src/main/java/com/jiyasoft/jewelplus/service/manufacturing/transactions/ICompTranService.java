package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Component;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompTran;
import com.mysema.query.Tuple;

public interface ICompTranService {

	public List<CompTran> findAll();

	public void save(CompTran compTran);

	public void delete(int id);
	
	public CompTran findOne(int id);
	
	public Long count();

	public List<CompTran> findByRefTranIdAndTranType(Integer refTranId,String tranType);

	public CompTran RefTranIdAndTranType(Integer refTranId, String tranType);
	
	public Double getStockBalance(Integer purityId,Integer colorId,Integer locationId,Integer componentId);
	
	public List<CompTran> findByBagMtAndTranTypeAndInOutFld(BagMt bagMt, String tranType, String inOutFld);
	
	public List<CompTran>  findByBagMtAndDeactive(BagMt bagMt,Boolean deactive);
	
	public List<CompTran> findByBagMtAndPurityAndColorAndComponent(BagMt bagMt,Purity purity,Color color,Component component);
	
	public List<CompTran> getCompTranListForCosting(Integer bagId);
	
	public List<Tuple> getCompTranTupleList(Integer bagId);
	
	public String compAdditionSave(CompTran compTran,Integer id,String vBagNo,Double vQty,String vPresentDept,String findingFlg,Principal principal,Date vTranDate);
	
	public String compDeductionSave(CompTran compTran,Integer id,String vBagNo,Double vQty,String vPresentDept,String findingFlg,Principal principal,Date vTranDate);
	
	public String multiCompAdditionSave(CompTran compTran,String vBagNo,Double vTotQty,Integer vPresentDeptId,String findingFlg,Principal principal, Date tranDate);
	
	public String multiCompDeductionSave(CompTran compTran,String vBagNo,Double vTotQty,String vPresentDept,String findingFlg,Principal principal);
	
	public List<CompTran>  findByTranMtIdAndDeactive(Integer tranMtId,Boolean deactive);

	public String compDeductionSave(String vBagNo,String vPresentDept,String deductionData,Principal principal,Date vTranDate,Integer departmentId);
	
	public Double getCompMetalRate(Integer purityId,Integer colorId,Integer locationId,Integer componentId,Double adjWt);

	
}
