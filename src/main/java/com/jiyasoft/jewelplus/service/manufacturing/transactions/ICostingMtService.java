package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;

public interface ICostingMtService {
	
	public List<CostingMt> findAll();
	
	public Page<CostingMt> findAll(Integer limit, Integer offset, String sort, String order, String search);
	
	public void save(CostingMt costingMt);
	
	public void delete(int id);
	
	public Long count();
	
	public CostingMt findOne(int id);
	
	public CostingMt findByInvNoAndDeactive(String invNo,Boolean deactive);
	
	public Long count(String colName, String colValue, Boolean onlyActive);

	public Page<CostingMt> findByInvNo(Integer limit, Integer offset, String sort, String order, String name);
	
	public CostingMt findByUniqueId(Long uniqueId);
	 
	public Page<CostingMt> getInvNoAutoFill(Integer limit, Integer offset, String sort, String order, String invNo, Boolean onlyActive);
	
	public Map<Integer,String> getCostingList();
	
	public Page<CostingMt> findActiveCostingSortByInvDate();
	
	public String costingTransfer(String fromInvoice,String toInvoice,String dtids,Principal principal);
	
	public List<Object[]>  partyWiseAndDateWiseListing(Integer limit, Integer offset, String sort, String order, String search,String partyIds,String fromDate,String toDate) throws ParseException;
	
	public String addBagInCosting(String pOIds,Integer costingMtId,Principal principal, Integer setNo,Boolean netWtWithComp);
	
	public String deleteCostingMtInvoice(Integer costingMtId,Principal principal);
	
	public String exportCloseTransfer(Integer costingMtId,Principal principal);
	
	public List<Object[]>  exportInvoiceAllPartyWiseAndDateWiseListing(String search,String partyIds,String fromDate,String toDate) throws ParseException;
	
	public String updateDiaWtAsPerOrder(Integer costMtId);
	
}
