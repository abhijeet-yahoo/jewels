package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetMt;

public interface IConsigRetDtService {

	public void save(ConsigRetDt consigRetDt);

	public void delete(int id);
	
	public ConsigRetDt findOne(int id);
	
	public List<ConsigRetDt> findByConsigRetMt(ConsigRetMt consigRetMt);
	
	public String deleteConsigRetDt(Integer dtId);
	
	public String consigRetDtListing(Integer mtId,String disableFlg);
	
	public ConsigRetDt findByRefConsigDtId(Integer refConsigDtId);
		
	public ConsigRetDt findByConsigRetMtAndBarcode(ConsigRetMt consigRetMt,String barcode);
}
