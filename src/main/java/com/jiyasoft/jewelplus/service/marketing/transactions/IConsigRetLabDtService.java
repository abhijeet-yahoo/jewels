package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetLabDt;

public interface IConsigRetLabDtService {
	
	public void save(ConsigRetLabDt consigRetLabDt);

	public void delete(int id);
	
	public ConsigRetLabDt findOne(int id);
	
	public List<ConsigRetLabDt>findByConsigRetDt(ConsigRetDt consigRetDt);
	
	public List<ConsigRetLabDt> findByConsigRetDtAndMetal(ConsigRetDt consigRetDt,Metal metal);

	public String consigRetLabDtListing(Integer dtId,String disableFlg);

}
