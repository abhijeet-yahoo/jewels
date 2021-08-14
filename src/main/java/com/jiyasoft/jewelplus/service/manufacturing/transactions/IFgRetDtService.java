package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;


import com.jiyasoft.jewelplus.domain.manufacturing.transactions.FgRetDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.FgRetMt;

public interface IFgRetDtService {
	

	public void save(FgRetDt fgRetDt);

	public void delete(int id);

	public FgRetDt findOne(int id);

	public List<FgRetDt> findByFgRetMt(FgRetMt fgRetMt);
	
	public String fgRetAddBarcodeItem(Integer mtId,String barcode,Principal principal,Integer locationId);
	
	public String fgRetListing(Integer mtId);

	public String fgRetDtDelete(Integer dtId,Principal principal);
	
	FgRetDt findByTranMtId(Integer tranMtId);
}
