package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignComponent;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetMt;

public interface ISaleRetCompDtService {

	public void save(SaleRetCompDt saleRetCompDt);

	public void delete(int id);
	
	public SaleRetCompDt findOne(int id);
	
	public List<SaleRetCompDt>findBySaleRetDt(SaleRetDt saleRetDt);
	
	public String saleRetCompDtListing(Integer dtId,String disableFlg);
	
	public void setSaleRetCompDt(List<DesignComponent> designComponents,SaleRetMt saleRetMt,SaleRetDt saleRetDt,Principal principal);
}
