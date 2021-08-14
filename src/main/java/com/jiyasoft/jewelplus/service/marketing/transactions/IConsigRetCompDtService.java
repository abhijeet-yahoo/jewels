package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.util.List;


import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetDt;

public interface IConsigRetCompDtService {
	
	public void save(ConsigRetCompDt consigRetCompDt);

	public void delete(int id);
	
	public ConsigRetCompDt findOne(int id);
	
	public List<ConsigRetCompDt>findByConsigRetDt(ConsigRetDt consigRetDt);
	
	public String consigRetCompDtListing(Integer dtId,String disableFlg);


}
