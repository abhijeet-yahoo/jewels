package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetMt;

public interface IConsigRetMtService {

public Integer getMaxInvSrno();
	
	public void save(ConsigRetMt consigRetMt);

	public void delete(int id);
	
	public ConsigRetMt findOne(int id);
	
	public String deleteConsigRetMt(Integer mtId);
	
	public List<ConsigRetMt> findByParty(Party party);
	
	public String consigRetMtListing(Integer limit,Integer offset,String sort,String order,String search, Principal principal) throws ParseException;

	public String saveConsigRetMt(ConsigRetMt consigRetMt, Integer id, RedirectAttributes redirectAttributes, Principal principal,BindingResult result,
			Integer pPartyIds,Integer pLocationIds,Integer pEmployeeIds,String vTranDate) throws ParseException;
	
	public Page<ConsigRetMt> searchAll(Integer limit, Integer offset,	String sort, String order, String search, Boolean onlyActive);
	
	public String consignmetTransfer(Integer pMtId,String data,Principal principal);
}
