package com.jiyasoft.jewelplus.service.manufacturing.masters;


import java.security.Principal;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignCost;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;


public interface IDesignCostService {

	public void save(DesignCost designCost);
	
	public void delete(int id);
	
	public Long count();
	
	public DesignCost findOne(int id);
	
	public String applyRate(Design design, Party party,Double goldRate,Double addedPerc,Double tagPerc,Double dispPerc,Double handlingPerc,Purity purity,Principal principal); 
	
	public String updateFob(Design design,Double addedPerc,Double dispPerc);
	
	public DesignCost findByDesignAndDeactive(Design design,Boolean deactive);
	
}
