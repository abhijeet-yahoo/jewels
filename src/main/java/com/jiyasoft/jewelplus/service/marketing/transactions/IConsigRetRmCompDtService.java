package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetRmCompDt;

public interface IConsigRetRmCompDtService {

	public List<ConsigRetRmCompDt> findByConsigRetMt(ConsigRetMt consigRetMt);

	public ConsigRetRmCompDt findByUniqueId(Long uniqueId);

	public void save(ConsigRetRmCompDt consigRetRmCompDt);

	public void delete(int id);

	public ConsigRetRmCompDt findOne(int id);

	public String consigRetRmCompDtSave(ConsigRetRmCompDt consigRetRmCompDt, Integer id, Integer mtId,
			Principal principal);

	public String consigRetRmCompDtDelete(Integer id, Principal principal);
	
	public String consigRetRmCompDtListing(Integer mtId,String disableFlg);
	
	public String consigCompRowDataPickup(Integer pMtId,String data,Principal principal);

}
