package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddMetalRate;

public interface IVAddMetalRateService {
	
public VAddMetalRate findByCostingMtAndDeactiveAndMetal(CostingMt costingMt,Boolean deactive,Metal metal);
	
	public List<VAddMetalRate> findByCostingMtAndDeactive(CostingMt costingMt,Boolean deactive);
	
	public VAddMetalRate findOne(int id);
    
    public void save(VAddMetalRate vAddMetalRate);
    
    public String vAddMetalRateSave(String tabData,int costMtId,int applyVal,Principal principal);

}
