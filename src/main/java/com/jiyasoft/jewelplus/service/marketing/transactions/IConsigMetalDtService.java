package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigMetalDt;

public interface IConsigMetalDtService {
	
	public void save(ConsigMetalDt consigMetalDt);

	public void delete(int id);
	
	public ConsigMetalDt findOne(int id);
	
	public List<ConsigMetalDt>findByConsigDt(ConsigDt consigDt);
	
	public ConsigMetalDt findByConsigDtAndPartNm(ConsigDt consigDt,LookUpMast lookUpMast);
	
	public String consigMetalDtListing(Integer dtId);


}
