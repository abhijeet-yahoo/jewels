package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ReadyBagRetDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ReadyBagRetMt;


public interface IReadyBagRetDtService {

	
	public void save(ReadyBagRetDt readyBagRetDt);

	public void delete(int id);

	public ReadyBagRetDt findOne(int id);
	
	public String readyBagRetDtListing(Integer mtId);
	
	public List<ReadyBagRetDt> findByReadyBagRetMt(ReadyBagRetMt readyBagRetMt);
	
	public String ReadyBagRetDtListing(Integer mtId,String disableFlg);
	
	public String returnReadyBagTrfListing(Integer mtId,Principal principal); 
	
	public String returnReadyBagIss(String pBagIds,Integer readyBagRetMtId,Principal principal);
	
	public String deleteDt(Integer bagId);
	
	public 	List<ReadyBagRetDt> findByBagMt(BagMt bagMt);
	
	 public String getReadyBagPendingApproval(Integer locationId, String bagIds);
	 
	 public List<ReadyBagRetDt> findByPendApprovalFlg(Boolean pendApprovalFlg);
	 
	 public String ReadyBagRetDtPendingApprovalListing();
	 
	 
	 public String readyBagRetApprove(String pBagIds,Principal principal);
	 
	 
	 
	 
	
}
