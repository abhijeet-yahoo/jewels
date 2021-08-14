package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigDt;

public interface IConsigCompDtService {

	public void save(ConsigCompDt consigCompDt);

	public void delete(int id);
	
	public ConsigCompDt findOne(int id);
	
	public List<ConsigCompDt>findByConsigDt(ConsigDt consigDt);
	
	public String consigCompDtListing(Integer dtId);

}
