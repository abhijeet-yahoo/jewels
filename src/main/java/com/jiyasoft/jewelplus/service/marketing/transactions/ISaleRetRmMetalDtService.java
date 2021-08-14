package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetRmMetalDt;

public interface ISaleRetRmMetalDtService {

	public List<SaleRetRmMetalDt> findBySaleRetMt(SaleRetMt saleRetMt);

	public SaleRetRmMetalDt findByUniqueId(Long uniqueId);

	public void save(SaleRetRmMetalDt saleRetRmMetalDt);

	public void delete(int id);

	public SaleRetRmMetalDt findOne(int id);

	public String saleRetRmMetalDtSave(SaleRetRmMetalDt saleRetRmMetalDt, Integer id, Integer mtId,
			Principal principal);

	public String saleRetRmMetalDtDelete(Integer id, Principal principal);
	
	public String saleRetRmMetalDtListing(Integer mtId,String disableFlg);
	
	public String saleMetalRowDataPickupSave(Integer pMtId,String data,Principal principal);
	
}
