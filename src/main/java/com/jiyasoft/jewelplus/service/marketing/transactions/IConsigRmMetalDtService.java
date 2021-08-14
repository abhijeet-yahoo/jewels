package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRmMetalDt;

public interface IConsigRmMetalDtService {

	 public List<ConsigRmMetalDt> findByConsigMt(ConsigMt consigMt);

	 public ConsigRmMetalDt findByUniqueId(Long uniqueId);
	 
	 public void save(ConsigRmMetalDt consigRmMetalDt);
	
	 public void delete(int id);
	
	 public ConsigRmMetalDt findOne(int id);
	
	 public Map<Integer, String> getConsigRmMetalDtList();
	
	 public String consigRmMetalDtSave(ConsigRmMetalDt consigRmMetalDt,Integer id,Integer mtId, Principal principal);
	
	 public String consigRmMetalDtDelete(Integer id, Principal principal);

	 public String consigRmMetalDtListing(Integer mtId,String disableFlg);
	 
	 public String rowMetalDetails(Integer mtId);
}
