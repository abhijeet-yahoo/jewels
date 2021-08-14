package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleMt;



public interface ISaleCompDtService {

	public void save(SaleCompDt saleCompDt);

	public void delete(int id);

	public SaleCompDt findOne(int id);
	
	public List<SaleCompDt> findBySaleMt(SaleMt saleMt);
	
	public List<SaleCompDt> findBySaleDt(SaleDt saleDt);
	
	public String saleCompDtListing(Integer dtId,String disableFlg);
}
