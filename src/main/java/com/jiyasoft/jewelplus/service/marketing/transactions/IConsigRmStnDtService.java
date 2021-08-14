package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRmStnDt;

public interface IConsigRmStnDtService {

	public List<ConsigRmStnDt> findByConsigMt(ConsigMt consigMt);
	
	public ConsigRmStnDt findByUniqueId(Long uniqueId);
	
	public ConsigRmStnDt findOne(Integer id);
	
	public void save(ConsigRmStnDt consigRmStnDt);
	
	public void delete(int id);
	
	public String saveConsigRmStnDt(ConsigRmStnDt consigRmStnDt,Integer id,Integer mtId, Principal principal,String stockType,Boolean allowNegative);
	
	public String consigRmStnDtDelete(Integer id, Principal principal);
	
	public String consigRmStnDtListing(Integer mtId);
	
	 public String rowStoneDtDetails(Integer mtId);
	
}
