package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetMt;

public interface ISaleRetMetalDtService {

	public void save(SaleRetMetalDt saleRetMetalDt);

	public void delete(int id);
	
	public SaleRetMetalDt findOne(int id);
	
	public List<SaleRetMetalDt>findBySaleRetDt(SaleRetDt saleRetDt);
	
	public SaleRetMetalDt findBySaleRetDtAndPartNm(SaleRetDt saleRetDt,LookUpMast lookUpMast);
	
	public String saleRetMetalDtListing(Integer dtId);
	
	public void addSaleRetMetal(String metalData,SaleRetMt saleRetMt,SaleRetDt saleRetDt,Principal  principal);
	
	public SaleRetMetalDt findBySaleRetDtAndMainMetal(SaleRetDt saleRetDt,Boolean mainMetal);
}
