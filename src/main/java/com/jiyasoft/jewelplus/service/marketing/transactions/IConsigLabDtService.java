package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigLabDt;

public interface IConsigLabDtService {

	public void save(ConsigLabDt consigLabDt);

	public void delete(int id);
	
	public ConsigLabDt findOne(int id);
	
	public List<ConsigLabDt>findByConsigDt(ConsigDt consigDt);
	
	public List<ConsigLabDt> findByConsigDtAndMetal(ConsigDt consigDt,Metal metal);

	public String consigLabDtListing(Integer dtId,String disableFlg);
	
}
