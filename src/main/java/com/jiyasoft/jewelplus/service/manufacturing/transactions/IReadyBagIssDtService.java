package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ReadyBagIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ReadyBagIssMt;

public interface IReadyBagIssDtService {

	
	public void save(ReadyBagIssDt readyBagIssDt);

	public void delete(int id);

	public ReadyBagIssDt findOne(int id);
	
	public String readyBagIssDtListing(Integer mtId);
	
	public List<ReadyBagIssDt> findByReadyBagIssMt(ReadyBagIssMt readyBagIssMt);
	
	public String ReadyBagIssDtListing(Integer mtId,String disableFlg);
	
	public String readyBagTrfListing(Principal principal); 
	
	public String transferReadyBagIss(String pBagIds,Integer readtBagIssMtId,Principal principal);
	
	public String deleteDt(Integer bagId);
	
	public 	List<ReadyBagIssDt> findByBagMt(BagMt bagMt);
	
	 public String getReadyBagPendingApproval(Integer locationId, String bagIds);
}
