package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfMt;

public interface IStkTrfMtService {
	
	public Page<StkTrfMt> searchAll(Integer limit, Integer offset,	String sort, String order, String search, Boolean onlyActive, String tranType);
	
	public Integer getMaxInvSrno();
	
	public void save(StkTrfMt stkTrfMt);

	public void delete(int id);
	
	public StkTrfMt findOne(int id);
	
	public String stkTrfMtTransfer(Integer mtId,String data,Principal principal);
	
	public String deleteStkTrfMt(Integer mtId,String tranType);
	
	public List<StkTrfMt> findByParty(Party party);
	
	public String stkTrfMtListing(Integer limit,Integer offset,String sort,String order,String search, Principal principal, String opt) throws ParseException;

	public String saveStkTrfMt(StkTrfMt stkTrfMt, Integer id, RedirectAttributes redirectAttributes, Principal principal,BindingResult result,Integer pLocationIds,Integer pToLocationIds,String vTranDate) throws ParseException;
	
	public String saveBranchStkTrfMt(StkTrfMt stkTrfMt, Integer id, RedirectAttributes redirectAttributes, Principal principal,BindingResult result,
			Integer pLocationIds,Integer pToLocationIds,String vTranDate,Double vOtherCharges,Double vFinalPrice,Double pIgst, Double pSgst,Double pCgst) throws ParseException;
	
	public String getDtItemSummary(Integer mtId);
	
	public String getBranchWiseGst(Integer locationId,Integer toLocationId);
	
	public String addSummaryDetails(Integer mtId,Double fob,Double sgst,Double cgst,Double igst,Double otherCharges,Double finalPrice,Principal principal);
	
	public List<StkTrfMt> findByToLocationAndTranType(Department toLocation,String tranType);
	
	public String getStkTrfPendingList(Integer locationId, String tranType) throws ParseException;
	
	public String savePendingApproval(String dtId,Principal principal);
}
