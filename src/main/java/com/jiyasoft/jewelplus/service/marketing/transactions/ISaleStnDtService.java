package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleStnDt;

public interface ISaleStnDtService {


	public void save(SaleStnDt saleStnDt);
	
	public void delete(int id);
	
	public SaleStnDt findOne(int id);
	
	public List<SaleStnDt> findBySaleMt(SaleMt saleMt);
	
	public List<SaleStnDt> findBySaleDt(SaleDt saleDt);

	public String saleStnDtListing(Integer dtId,String disableFlg);
}
