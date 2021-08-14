package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.marketing.transactions.StockMeltingDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StockMeltingMt;

public interface IStockMeltingDtService {
	
	public void save(StockMeltingDt stockMeltingDt);

	public void delete(int id);

	public StockMeltingDt findOne(int id);

	public List<StockMeltingDt> findByStockMeltingMt(StockMeltingMt stockMeltingMt);

	public String stockMeltingTransfer(Principal principal,String barcode,Integer stkMeltMtId);
	
	public String stockMeltingDtListing(Integer limit, Integer offset, String sort, String order, String name,Integer mtId);
	
	public String stockMeltingPickupListing(Boolean approvalFlg);
	
	public List<StockMeltingDt>findByPendApprovalFlgAndCurrStk(Boolean pendApprovalFlg, Boolean currStk);
	
	public String stockMeltingApproval(Principal principal,String vDtId);
	
	public String stockMeltingDtDelete(Integer dtId,Principal principal);
	
	
	public Page<StockMeltingDt> searchAll(Integer limit, Integer offset, String sort, String order, String name,Integer mtId);
}
