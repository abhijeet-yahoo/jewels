package com.jiyasoft.jewelplus.service.marketing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackMetalRate;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackMt;

public interface IPackMetalRateService {

	public List<PackMetalRate> findByPackMt(PackMt packMt);
	
    public String packMetalRateSave(String tabData,int packMtId,Principal principal);
    
    public PackMetalRate findOne(int id);
    
    public void save(PackMetalRate packMetalRate);
    
    public void delete(int id);
    
    public PackMetalRate findByPackMtAndMetal(PackMt packMt,Metal metal);
	
}
