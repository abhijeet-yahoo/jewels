package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompInwardMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ComponentPurchaseMt;

public interface IComponentPurchaseMtService {

	public ComponentPurchaseMt  findByInvNoAndDeactive(String invNo,Boolean deactive);
	
	public ComponentPurchaseMt findByUniqueId(Long uniqueId);
	
	public void save(ComponentPurchaseMt componentPurchaseMt);

	public void delete(int id);

	public ComponentPurchaseMt findOne(int id);

	public Page<ComponentPurchaseMt> searchAll(Integer limit, Integer offset,	String sort, String order, String search, Boolean onlyActive);
	
	public String componentPurchaseMtDelete(int id);
	


}
