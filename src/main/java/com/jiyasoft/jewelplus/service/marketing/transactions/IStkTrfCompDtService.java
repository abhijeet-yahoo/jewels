package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfDt;

public interface IStkTrfCompDtService {
	
	public void save(StkTrfCompDt stkTrfCompDt);

	public void delete(int id);
	
	public StkTrfCompDt findOne(int id);
	
	public List<StkTrfCompDt>findByStkTrfDt(StkTrfDt stkTrfDt);
	
	public String stkTrfCompDtListing(Integer dtId,String disableFlg);

}
