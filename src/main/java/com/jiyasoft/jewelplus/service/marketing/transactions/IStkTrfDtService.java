package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfMt;

public interface IStkTrfDtService {
	
	public void save(StkTrfDt stkTrfDt);

	public void delete(int id);
	
	public StkTrfDt findOne(int id);
	
	public String addStkTrfBarcodeItem(Integer mtId,String barcode,Principal principal,String tranType);
	
	public List<StkTrfDt> findByStkTrfMt(StkTrfMt stkTrfMt);
	
	public String deleteStkTrfDt(Integer dtId,String tranType);
	
	public String stkTrfDtListing(Integer mtId,String disableFlg);
	
	
}
