package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.security.Principal;
import java.util.List;


import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetMt;

public interface ISaleRetDtService {


	public void save(SaleRetDt saleRetDt);

	public void delete(int id);
	
	public SaleRetDt findOne(int id);
	
	public List<SaleRetDt> findBySaleRetMt(SaleRetMt saleRetMt);
	
	public String deleteSaleRetDt(Integer dtId);
	
	
	public String saleRetDtListing(Integer mtId,String disableFlg);
	
	public 	SaleRetDt findByRefSaleDtId(Integer refSaleDtId);
	
	public SaleRetDt findBySaleRetMtAndBarcode(SaleRetMt saleRetMt,String barcode);
	
	public String transactionalSave(SaleRetDt saleRetDt,Integer id,Integer saleRetMtIdPk,String metalDtData,Principal principal);
		
}
