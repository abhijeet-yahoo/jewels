package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostMetalRate;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;

public interface ICostMetalRateService {
	
	public CostMetalRate findByCostingMtAndDeactiveAndMetal(CostingMt costingMt,Boolean deactive,Metal metal);
	
	public List<CostMetalRate> findByCostingMtAndDeactive(CostingMt costingMt,Boolean deactive);
	
	public CostMetalRate findOne(int id);
    
    public void save(CostMetalRate costMetalRate);
    
    public String costMetalRateSave(String tabData,int costMtId,Principal principal,String costingType,Boolean netWtWithCompFlg);

}
