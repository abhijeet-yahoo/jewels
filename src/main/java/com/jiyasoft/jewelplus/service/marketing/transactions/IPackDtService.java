package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.marketing.transactions.PackCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackStnDt;

public interface IPackDtService {

	public void save(PackDt packDt);

	public void delete(int id);
	
	public PackDt findOne(int id);
	
	public String addBarcodeItem(Integer mtId,String barcode,Principal principal,Integer partyId,Integer locationId);
	
	public List<PackDt> findByPackMt(PackMt packMt);
	
	public String deletePackDt(Integer dtId);
	
	public String applyRate(PackDt packDt, Principal principal); 
	
	public String applyMetal(PackDt packDt);
	
	public String applyStoneRate(List<PackStnDt> packStnDts);
	
	public PackStnDt applySettingRate(PackStnDt packStnDt);
	
	public PackStnDt applyPackStoneRate(PackStnDt packStnDt);
	
	public PackStnDt applyHandlingRate(PackStnDt packStnDt);
	
	public String applyCompRate(List<PackCompDt>packCompDts);
	
	public PackCompDt applyPackCompRate(PackCompDt packCompDt);
	
	public String applyLabRate(PackDt packDt,Principal principal);
	 
	public String updateFob(PackDt packDt);
	
	public String locationWiseStockTransferInPacking(Integer pMtId,String data,Principal principal);	
	
	public String packDtListing(Integer mtId,String flag);
	
	public String packDtData(Integer dtId);
	
	public PackDt findByPackMtAndBarcode(PackMt packMt,String barcode);
	
	public String updateFobAsPer999(PackMt packMt);
}
