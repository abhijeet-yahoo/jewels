package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.text.ParseException;

import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairRetMt;

public interface IRepairRetMtService {

	public Page<RepairRetMt> searchAll(Integer limit, Integer offset,	String sort, String order, String search, Boolean onlyActive);
	
	public Integer getMaxInvSrno();
	
	public void save(RepairRetMt repairRetMt);

	public void delete(int id);
	
	public RepairRetMt findOne(int id);
	
	public String deleteRepairRetMt(Integer mtId);

	public String repairRetListing(Integer limit,Integer offset,String sort,String order,String search, Principal principal) throws ParseException;
	
	public String saveRepairRetMt(RepairRetMt repairRetMt, Integer id, RedirectAttributes redirectAttributes, Principal principal,BindingResult result,Integer pPartyIds,Integer pLocationIds,String vTranDate) throws ParseException;
	
	public String repairReturnTransferList(Integer partyId,Integer locationId);
}
