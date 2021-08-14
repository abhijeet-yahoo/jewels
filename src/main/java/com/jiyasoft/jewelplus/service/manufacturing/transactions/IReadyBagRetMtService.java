package com.jiyasoft.jewelplus.service.manufacturing.transactions;


import java.security.Principal;
import java.text.ParseException;

import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ReadyBagRetMt;

public interface IReadyBagRetMtService {

	public void save(ReadyBagRetMt readyBagRetMt);
	
	public void delete(int id);
	
	public Page<ReadyBagRetMt> searchAll(Integer limit, Integer offset, String sort, String order, String search,Boolean onlyActive);

	public String readyBagRetMtListing(Integer limit, Integer offset, String sort, String order, String search,Principal principal) throws ParseException;
	
	public String readyBagRetMtSave(ReadyBagRetMt readyBagRetMt, Integer id, RedirectAttributes redirectAttributes,
			Principal principal, BindingResult result,Integer pLocationIds, String vTranDate) throws ParseException;
	
	public String deleteMt(Integer mtId);

	 public ReadyBagRetMt findOne(int id);
	 
	 public String readyBagPendingApprovalList(Integer locationId, Principal principal);
	 
	 public Integer getMaxInvSrno();
	 
	
}
