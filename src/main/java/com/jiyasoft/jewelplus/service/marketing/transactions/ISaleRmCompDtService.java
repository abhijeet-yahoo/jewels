package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRmCompDt;

public interface ISaleRmCompDtService {

	
public List<SaleRmCompDt> findBySaleMt(SaleMt saleMt);
	
	public SaleRmCompDt findByUniqueId(Long uniqueId);

	public void save(SaleRmCompDt saleRmCompDt);

	public void delete(int id);

	public SaleRmCompDt findOne(int id);

	public String saveSaleRmCompDt(SaleRmCompDt saleRmCompDt, Integer id, Integer mtId, Principal principal);

	public String saleRmCompDtDelete(Integer id, Principal principal);
	
	public String saleRmCompDtListing (Integer mtId,String disableFlg);
	
	 public String saleRowCompDtDetails(Integer mtId);
}
