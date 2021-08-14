package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.text.ParseException;

import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BarcodeMt;

public interface IBarcodeMtService {

	public BarcodeMt findOne(int id);
	
	public void save(BarcodeMt barcodeMt);
	
	public void delete(int id);
	
	public BarcodeMt  findByInvNoAndDeactive(String invNo,Boolean deactive);

	public Page<BarcodeMt> searchAll(Integer limit, Integer offset,	String sort, String order, String search, Boolean onlyActive);
	
	public String barcodeMtListing(Integer limit, Integer offset,String sort, String order, String search,Principal principal) throws ParseException;
	
	public String barcodeDtListing(Integer mtId);
	
	public String saveBarcodeMt(BarcodeMt barcodeMt, Integer id, RedirectAttributes redirectAttributes, Principal principal,BindingResult result);
	
	public Integer getMaxInvSrno();
}
