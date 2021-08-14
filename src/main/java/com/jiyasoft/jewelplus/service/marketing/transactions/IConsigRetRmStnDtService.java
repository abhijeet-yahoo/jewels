package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetRmStnDt;

public interface IConsigRetRmStnDtService {

	public List<ConsigRetRmStnDt> findByConsigRetMt(ConsigRetMt consigRetMt);

	public ConsigRetRmStnDt findByUniqueId(Long uniqueId);

	public void save(ConsigRetRmStnDt consigRetRmStnDt);

	public void delete(int id);

	public ConsigRetRmStnDt findOne(int id);

	public String consigRetRmStnDtSave(ConsigRetRmStnDt consigRetRmStnDt, Integer id, Integer mtId,
			Principal principal);

	public String consigRetRmStnDtDelete(Integer id, Principal principal);
	
	public String consigRetRmStnDtListing(Integer mtId,String disableFlg,Principal principal);
	
	public String consigStoneRowDataPickup(Integer pMtId,String data,Principal principal);
}
