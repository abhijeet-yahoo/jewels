package com.jiyasoft.jewelplus.service.manufacturing.transactions;


import java.security.Principal;
import java.text.ParseException;
import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneInwardMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StonePurchaseMt;

public interface IStonePurchaseMtService {
	
	public void save(StonePurchaseMt stonePurchaseMt);

	public void delete(int id);

	public StonePurchaseMt findOne(int id);

	public StonePurchaseMt findByInvNoAndDeactive(String invNo,Boolean deactive);

	public StonePurchaseMt findByUniqueId(Long uniqueId);
	
	public Page<StonePurchaseMt> searchAll(Integer limit, Integer offset,	String sort, String order, String search, Boolean onlyActive);
	
	public String stonePurcDelete(int id, Principal principal);
	
	public List<StonePurchaseMt>getInvoiceList(String inwardTypeIds,String fromDate, String toDate)throws ParseException;


}
