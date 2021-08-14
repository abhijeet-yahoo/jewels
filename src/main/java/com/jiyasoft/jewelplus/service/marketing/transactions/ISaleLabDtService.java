package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleLabDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleMt;

public interface ISaleLabDtService {


	public List<SaleLabDt> findBySaleMt(SaleMt saleMt);
	
	public List<SaleLabDt> findBySaleDt(SaleDt saleDt);
	
	public List<SaleLabDt>findBySaleDtAndMetal(SaleDt SaleDt,Metal metal);

	public void save(SaleLabDt saleLabDt);

	public void delete(int id);

	public SaleLabDt findOne(int id);
	
	public String saleLabDtListing(Integer dtId,String disableFlg);
}
