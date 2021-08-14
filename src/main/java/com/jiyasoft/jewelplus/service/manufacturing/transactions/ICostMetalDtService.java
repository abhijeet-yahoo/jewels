package com.jiyasoft.jewelplus.service.manufacturing.transactions;


import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;

public interface ICostMetalDtService {
	
	
	public List<CostMetalDt> findAll();
	
	public void save(CostMetalDt costMetalDt);

	public void delete(int id);

	public Long count();

	public CostMetalDt findOne(int id);
	
	public List<CostMetalDt> findByCostingMtAndDeactive(CostingMt costingMt,Boolean deactive);
	
	public List<CostMetalDt> findByCostingDtAndDeactive(CostingDt costingDt,Boolean deactive);
	
	public CostMetalDt findByCostingDtAndDeactiveAndMainMetal(CostingDt costingDt,Boolean deactive,Boolean mainMetal);

	public CostMetalDt findByCostingDtAndDeactiveAndPartNm(CostingDt costingDt,Boolean deactive,LookUpMast lookUpMast);
	
	

}
