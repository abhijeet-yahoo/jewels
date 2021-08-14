package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRmStnDt;

public interface ISaleRmStnDtService {

	public List<SaleRmStnDt> findBySaleMt(SaleMt saleMt);

	public SaleRmStnDt findByUniqueId(Long uniqueId);

	public SaleRmStnDt findOne(Integer id);

	public void save(SaleRmStnDt saleRmStnDt);

	public void delete(int id);

	public String saveSaleRmStnDt(SaleRmStnDt saleRmStnDt, Integer id, Integer mtId, Principal principal,
			String stockType, Boolean allowNegative);

	public String saleRmStnDtDelete(Integer id, Principal principal);
	
	public String saleRmStnDtListing(Integer mtId,String disableFlg);
	
	 public String saleRowStoneDtDetails(Integer mtId);

}
