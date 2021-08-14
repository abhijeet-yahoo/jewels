package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfStnDt;

public interface IStkTrfStnDtService {
	
	public void save(StkTrfStnDt stkTrfStnDt);

	public void delete(int id);
	
	public StkTrfStnDt findOne(int id);
	
	public List<StkTrfStnDt>findByStkTrfDt(StkTrfDt stkTrfDt);

	public String stkTrfStnDtListing(Integer dtId,String disableFlg);


}
