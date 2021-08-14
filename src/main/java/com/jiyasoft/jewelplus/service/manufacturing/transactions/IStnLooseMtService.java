package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.text.ParseException;

import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnLooseMt;

public interface IStnLooseMtService {

	public Page<StnLooseMt> searchAll(Integer limit, Integer offset,String sort, String order, String search);

	public void save(StnLooseMt stnLooseMt);

	public void delete(int id);

	public StnLooseMt findOne(int id);
	
	public String deleteMt(Integer mtId,Principal principal);
	
	public Integer getMaxInvSrno();

	public String saveStnLooseMt(StnLooseMt stnLooseMt, Integer id, RedirectAttributes redirectAttributes, Principal principal,BindingResult result,String vTranDate) throws ParseException;

	public StnLooseMt findByInvNo(String invNo);
	
	public String stnLoosePickupList(Principal principal) throws ParseException;
	
	public String looseConversionPickupList(Integer partyId,Principal principal) throws ParseException;
	
	public String stnLoosePickupPartyWiseList(Integer partyId,Principal principal) throws ParseException;
}
