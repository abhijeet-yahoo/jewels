package com.jiyasoft.jewelplus.service.manufacturing.transactions;


import java.security.Principal;
import java.text.ParseException;

import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ReadyBagIssMt;

public interface IReadyBagIssMtService {

	public void save(ReadyBagIssMt readyBagIssMt);
	
	public void delete(int id);
	
	public Page<ReadyBagIssMt> searchAll(Integer limit, Integer offset, String sort, String order, String search,Boolean onlyActive);

	public String readyBagIssMtListing(Integer limit, Integer offset, String sort, String order, String search,Principal principal) throws ParseException;
	
	public String readyBagMtSave(ReadyBagIssMt readyBagIssMt, Integer id, RedirectAttributes redirectAttributes,
			Principal principal, BindingResult result,Integer pLocationIds,String vTranDate) throws ParseException;
	
	public String deleteMt(Integer mtId);

	 public ReadyBagIssMt findOne(int id);
	 
	 public String readyBagPendingApprovalList(Integer locationId, Principal principal);
	 
	 public Integer getMaxInvSrno();
	 
	
}
