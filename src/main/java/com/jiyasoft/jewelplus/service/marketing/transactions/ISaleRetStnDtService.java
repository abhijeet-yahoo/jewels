package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignStone;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetStnDt;

public interface ISaleRetStnDtService {

	public void save(SaleRetStnDt saleRetStnDt);

	public void delete(int id);
	
	public SaleRetStnDt findOne(int id);
	
	public List<SaleRetStnDt>findBySaleRetDt(SaleRetDt saleRetDt);

	public String saleRetStnDtListing(Integer dtId,String disableFlg);
	
	 public void setSaleRetStnDt(List<DesignStone> designStones,SaleRetMt saleRetMt,SaleRetDt saleRetDt,Principal principal);
}
