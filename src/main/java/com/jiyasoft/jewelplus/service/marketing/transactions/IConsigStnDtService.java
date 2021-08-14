package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigStnDt;

public interface IConsigStnDtService {

	public void save(ConsigStnDt consigStnDt);

	public void delete(int id);
	
	public ConsigStnDt findOne(int id);
	
	public List<ConsigStnDt>findByConsigDt(ConsigDt consigDt);

	public String consigStnDtListing(Integer dtId);


}
