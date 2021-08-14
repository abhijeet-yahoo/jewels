package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StonePurchaseDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StonePurchaseMt;

public interface IStonePurchaseDtService {

	public StonePurchaseDt findByUniqueId(Long uniqueId);

	public List<StonePurchaseDt> findByStonePurchaseMtAndDeactive(StonePurchaseMt stonePurchaseMt, Boolean deactive);

	public void save(StonePurchaseDt stonePurchaseDt);

	public void delete(int id);

	public StonePurchaseDt findOne(int id);
	
	public String stonePurchaseSave(StonePurchaseDt purchaseDt,Integer id,Integer mtId,Principal principal);
	
	public String stonePurchaseDtDelete(Integer id, Principal principal);

	
	

}
