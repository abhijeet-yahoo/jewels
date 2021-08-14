package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleMt;



public interface ISaleMetalDtService {

	
	public void save(SaleMetalDt saleMetalDt);
	
	public void delete(int id);
	
	public SaleMetalDt findOne(int id);
	
	public List<SaleMetalDt> findBySaleMt(SaleMt saleMt);
	
	public List<SaleMetalDt> findBySaleDt(SaleDt saleDt);
	
	public SaleMetalDt findBySaleDtAndMainMetal(SaleDt saleDt,Boolean mainMetal);

	public String saleMetalDtListing(Integer dtId);
	
	
}
