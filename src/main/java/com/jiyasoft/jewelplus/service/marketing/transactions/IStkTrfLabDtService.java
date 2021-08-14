package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfLabDt;

public interface IStkTrfLabDtService {

	public void save(StkTrfLabDt stkTrfLabDt);

	public void delete(int id);
	
	public StkTrfLabDt findOne(int id);
	
	public List<StkTrfLabDt>findByStkTrfDt(StkTrfDt stkTrfDt);
	
	public List<StkTrfLabDt> findByStkTrfDtAndMetal(StkTrfDt stkTrfDt,Metal metal);

	public String stkTrfLabDtListing(Integer dtId,String disableFlg);
}
