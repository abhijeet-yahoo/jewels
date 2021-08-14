package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRmMetalDt;

public interface ISaleRmMetalDtService {
	
	public List<SaleRmMetalDt> findBySaleMt(SaleMt saleMt);
	
	public SaleRmMetalDt findByUniqueId(Long uniqueId);

	public void save(SaleRmMetalDt saleRmMetalDt);

	public void delete(int id);

	public SaleRmMetalDt findOne(int id);

	public Map<Integer, String> getSaleRmMetalDtList();

	public String saleRmMetalDtSave(SaleRmMetalDt saleRmMetalDt, Integer id, Integer mtId, Principal principal);

	public String saleRmMetalDtDelete(Integer id, Principal principal);
	
	public String saleRmMetalListing(Integer mtId,String disableFlg);
	
	 public String saleRowMetalDetails(Integer mtId);
}
