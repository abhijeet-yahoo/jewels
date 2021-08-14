package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalPurchaseDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalPurchaseMt;

public interface IMetalPurchaseDtService {
	
	public void save(MetalPurchaseDt metalPurchaseDt);

	public void delete(int id);

	public MetalPurchaseDt findOne(int id);

	public List<MetalPurchaseDt> findByMetalPurchaseMtAndDeactive(MetalPurchaseMt metalPurchaseMt, Boolean deactive); 
	
	public String metalPurchaseSave(MetalPurchaseDt metalPurchaseDt,Integer id,String pInvNo,Principal principal);
	
	public String metalPurchaseDelete(Integer id, Principal principal);
	
	public MetalPurchaseDt findByMetalPurchaseMtAndMetal(MetalPurchaseMt metalPurchaseMt,Metal metal); 
	
	public List<MetalPurchaseDt> findByMetalAndDeactive(Metal metal,Boolean deactive);
	
	public Page<MetalPurchaseDt> balanceInvoice(Integer limit, Integer offset,
			String sort, String order, String search,Metal metal,Boolean in999);
	
	

}
