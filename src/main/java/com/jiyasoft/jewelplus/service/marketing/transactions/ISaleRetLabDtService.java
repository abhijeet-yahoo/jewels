package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetLabDt;

public interface ISaleRetLabDtService {

	public void save(SaleRetLabDt saleRetLabDt);

	public void delete(int id);
	
	public SaleRetLabDt findOne(int id);
	
	public List<SaleRetLabDt>findBySaleRetDt(SaleRetDt saleRetDt);
	
	public List<SaleRetLabDt> findBySaleRetDtAndMetal(SaleRetDt saleRetDt,Metal metal);

	public String saleRetLabDtListing(Integer dtId,String disableFlg);
}
