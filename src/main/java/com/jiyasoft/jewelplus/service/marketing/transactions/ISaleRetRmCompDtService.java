package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetRmCompDt;

public interface ISaleRetRmCompDtService {

	public List<SaleRetRmCompDt> findBySaleRetMt(SaleRetMt saleRetMt);

	public SaleRetRmCompDt findByUniqueId(Long uniqueId);

	public void save(SaleRetRmCompDt saleRetRmCompDt);

	public void delete(int id);

	public SaleRetRmCompDt findOne(int id);

	public String saleRetRmCompDtSave(SaleRetRmCompDt saleRetRmCompDt, Integer id, Integer mtId,
			Principal principal);

	public String saleRetRmCompDtDelete(Integer id, Principal principal);
	
	public String saleRetRmCompDtListing(Integer mtId,String disableFlg);
	
	public String saleCompRowDataPickupSave(Integer pMtId,String data,Principal principal);

}
