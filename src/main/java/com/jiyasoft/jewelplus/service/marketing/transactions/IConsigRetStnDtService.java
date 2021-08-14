package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetStnDt;

public interface IConsigRetStnDtService {

	public void save(ConsigRetStnDt consigRetStnDt);

	public void delete(int id);
	
	public ConsigRetStnDt findOne(int id);
	
	public List<ConsigRetStnDt>findByConsigRetDt(ConsigRetDt consigRetDt);

	public String consigRetStnDtListing(Integer dtId, String disableFlg);
}
