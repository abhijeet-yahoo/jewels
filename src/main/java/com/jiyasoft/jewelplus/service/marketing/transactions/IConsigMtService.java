package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigMt;

public interface IConsigMtService {

public Page<ConsigMt> searchAll(Integer limit, Integer offset,	String sort, String order, String search, Boolean onlyActive);
	
	
	public Integer getMaxInvSrno();
	
	public void save(ConsigMt consigMt);

	public void delete(int id);
	
	public ConsigMt findOne(int id);
	
	public String packingListTransfer(Integer pMtId,String data,Principal principal);
	
	public String locationWiseStockTransfer(Integer pMtId,String data,Principal principal);
	
	public String deleteConsigMt(Integer mtId,Principal principal);
	
	public List<ConsigMt> findByParty(Party party);
	
	public String consigMtListing(Integer limit,Integer offset,String sort,String order,String search, Principal principal) throws ParseException;

	public String saveConsigMt(ConsigMt consigMt, Integer id, RedirectAttributes redirectAttributes, Principal principal,BindingResult result,
			Integer pPartyIds,Integer pLocationIds,Integer pEmployeeIds,String vTranDate) throws ParseException;
	
	public String packingPickupList(Integer partyId);
	
	public String consignMentPickupListing(Integer partyId,String tranType);
	
	public String stockList(Integer locationId);
	
	public String getDtItemSummary(Integer mtId);
	
	public String consigRowMetalPickupList(Integer partyId);
	
	public String consigRowStonePickupList(Integer partyId);
	
	public String consigRowCompPickupList(Integer partyId);
	
}
