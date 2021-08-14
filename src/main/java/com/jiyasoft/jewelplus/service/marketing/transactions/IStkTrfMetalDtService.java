package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfMetalDt;

public interface IStkTrfMetalDtService {

	public void save(StkTrfMetalDt stkTrfMetalDt);

	public void delete(int id);
	
	public StkTrfMetalDt findOne(int id);
	
	public List<StkTrfMetalDt>findByStkTrfDt(StkTrfDt stkTrfDt);
	
	public StkTrfMetalDt findByStkTrfDtAndPartNm(StkTrfDt stkTrfDt,LookUpMast lookUpMast);
	
	public String stkTrfMetalDtListing(Integer dtId);
}
