package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalPurchaseMt;

public interface IMetalPurchaseMtService {
	
	public void save(MetalPurchaseMt metalPurchaseMt);

	public void delete(int id);
	
	public MetalPurchaseMt findOne(int id);
	
	public MetalPurchaseMt  findByInvNoAndDeactive(String invNo,Boolean deactive);

	public MetalPurchaseMt findByUniqueId(Long uniqueId);
	
	public Page<MetalPurchaseMt> searchAll(Integer limit, Integer offset,	String sort, String order, String search, Boolean onlyActive);
	
	public String metalPurcDelete(int id);
	
	
}
