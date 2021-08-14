package com.jiyasoft.jewelplus.service.marketing.transactions;


import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetMetalDt;

public interface IConsigRetMetalDtService {

	public void save(ConsigRetMetalDt consigRetMetalDt);

	public void delete(int id);
	
	public ConsigRetMetalDt findOne(int id);
	
	public List<ConsigRetMetalDt>findByConsigRetDt(ConsigRetDt consigRetDt);
	
	public ConsigRetMetalDt findByConsigRetDtAndPartNm(ConsigRetDt consigRetDt,LookUpMast lookUpMast);
	
	public String consigRetMetalDtListing(Integer dtId);
	
	

}
