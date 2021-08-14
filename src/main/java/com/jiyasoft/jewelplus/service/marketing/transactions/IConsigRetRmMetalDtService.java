package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetRmMetalDt;

public interface IConsigRetRmMetalDtService {

	public List<ConsigRetRmMetalDt> findByConsigRetMt(ConsigRetMt consigRetMt);

	public ConsigRetRmMetalDt findByUniqueId(Long uniqueId);

	public void save(ConsigRetRmMetalDt consigRetRmMetalDt);

	public void delete(int id);

	public ConsigRetRmMetalDt findOne(int id);

	public String consigRetRmMetalDtSave(ConsigRetRmMetalDt consigRetRmMetalDt, Integer id, Integer mtId,
			Principal principal);

	public String consigRetRmMetalDtDelete(Integer id, Principal principal);
	
	public String consigRetRmMetalDtListing(Integer mtId,String disableFlg);
	
	public String consigMetalRowDataPickup(Integer pMtId,String data,Principal principal);
}
