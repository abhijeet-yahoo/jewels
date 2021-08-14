package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Component;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ComponentPurchaseDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ComponentPurchaseMt;

public interface IComponentPurchaseDtService {
	
	public List<ComponentPurchaseDt> findByComponentPurchaseMtAndDeactive(ComponentPurchaseMt componentPurchaseMt, Boolean deactive);
	
	public ComponentPurchaseDt findByUniqueId(Long uniqueId);
	
	public void save(ComponentPurchaseDt ComponentPurchaseDt);
	
	public void delete(int id);
	
	public ComponentPurchaseDt findOne(int id);
			
	public String componentPurchaseDtSave(ComponentPurchaseDt componentPurchaseDt,Integer id,Integer mtId, Principal principal);
	
	public String componentPurchaseDtDelete(Integer id, Principal principal);

	public List<ComponentPurchaseDt> findByMetalAndComponentAndPurityAndColorAndDeactive(Metal metal,Component component,Purity purity,Color color,Boolean deactive);

}
