package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRmCompDt;

public interface IConsigRmCompDtService {

	public List<ConsigRmCompDt> findByConsigMt(ConsigMt consigMt);
	
	public ConsigRmCompDt findByUniqueId(Long uniqueId);
	
	public void save(ConsigRmCompDt consigRmCompDt);

	public void delete(int id);
	
	public ConsigRmCompDt findOne(int id);

	public String saveConsigRmCompDt(ConsigRmCompDt consigRmCompDt,Integer id,Integer mtId, Principal principal);
	
	public String consigRmCompDtDelete(Integer id, Principal principal);
	
	public String consigRmCompDtListing (Integer mtId);
	
	 public String rowCompDtDetails(Integer mtId);
}
