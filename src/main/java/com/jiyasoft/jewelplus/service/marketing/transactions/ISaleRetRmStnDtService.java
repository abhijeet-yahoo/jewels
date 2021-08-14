package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetRmStnDt;

public interface ISaleRetRmStnDtService {

	public List<SaleRetRmStnDt> findBySaleRetMt(SaleRetMt saleRetMt);

	public SaleRetRmStnDt findByUniqueId(Long uniqueId);

	public void save(SaleRetRmStnDt saleRetRmStnDt);

	public void delete(int id);

	public SaleRetRmStnDt findOne(int id);

	public String saleRetRmStnDtSave(SaleRetRmStnDt saleRetRmStnDt, Integer id, Integer mtId,
			Principal principal);

	public String saleRetRmStnDtDelete(Integer id, Principal principal);
	
	public String saleRetRmStnDtListing(Integer mtId,String disableFlg);
	
	public String saleStoneRowDataPickup(Integer pMtId,String data,Principal principal);
}
