package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.security.Principal;
import java.util.List;


import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigStnDt;

public interface IConsigDtService {
	
	public void save(ConsigDt consigDt);

	public void delete(int id);
	
	public ConsigDt findOne(int id);
	
	public String addConsigBarcodeItem(Integer mtId,String barcode,Principal principal,Integer partyId,Integer locationId,Integer employeeId);
	
	public List<ConsigDt> findByConsigMt(ConsigMt consigMt);
	
	public String deleteConsigDt(Integer dtId);
	
	public String applyRate(ConsigDt consigDt, Principal principal); 
	
	public String applyMetal(ConsigDt consigDt);
	
	public String applyStoneRate(List<ConsigStnDt> consigStnDts);
	
	public ConsigStnDt applySettingRate(ConsigStnDt consigStnDt);
	
	public ConsigStnDt applyConsigStoneRate(ConsigStnDt consigStnDt);
	
	public ConsigStnDt applyHandlingRate(ConsigStnDt consigStnDt);
	
	public String applyCompRate(List<ConsigCompDt> consigCompDts);
	
	public ConsigCompDt applyConsigCompRate(ConsigCompDt consigCompDt);
	
	public String applyLabRate(ConsigDt consigDt,Principal principal);
	 
	public String updateFob(ConsigDt consigDt);
	
	public String consigDtListing(Integer mtId,String disableFlg);
	
	public String consigDtPickupListing(Integer mtId);
	
	public String packdtList(Integer mtId);
	
	public ConsigDt findByRefTranDtIdAndTranType(Integer refTranDtId,String tranType);
	
	public ConsigDt findByConsigMtAndBarcode(ConsigMt ConsigMt,String barcode);

}
