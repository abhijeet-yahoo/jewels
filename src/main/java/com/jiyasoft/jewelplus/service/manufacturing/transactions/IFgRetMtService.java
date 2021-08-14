package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.text.ParseException;

import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.FgRetMt;

public interface IFgRetMtService {

	public void save(FgRetMt fgRetMt);

	public void delete(int id);

	public FgRetMt findOne(int id);
	
	public Page<FgRetMt> searchAll(Integer limit, Integer offset,	String sort, String order, String search, Boolean onlyActive);
	
	public Integer getMaxInvSrno();
	
	public String saveFgRetMt(FgRetMt fgRetMt, Integer id, RedirectAttributes redirectAttributes, Principal principal,BindingResult result,
			String vTranDate,Integer pLocationToIds,Integer pLocationIds) throws ParseException;

	public String fgRetMtListing(Integer limit,Integer offset,String sort,String order,String search, Principal principal) throws ParseException;
	
	public String fgRetMtDelete(Integer mtId,Principal principal);
}
