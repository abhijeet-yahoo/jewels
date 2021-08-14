package com.jiyasoft.jewelplus.service.manufacturing.transactions;


import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VaddMetalDt;

public interface IVaddMetalDtService {
	
	
	public List<VaddMetalDt> findAll();
	
	public void save(VaddMetalDt vaddMetalDt);

	public void delete(int id);

	public Long count();

	public VaddMetalDt findOne(int id);
	
	public List<VaddMetalDt> findByCostingMtAndDeactive(CostingMt costingMt,Boolean deactive);
	
	public List<VaddMetalDt> findByVaddDtAndDeactive(VAddDt vAddDt,Boolean deactive);
	
	public VaddMetalDt findByVAddDtAndDeactiveAndPartNm(VAddDt vAddDt,Boolean deactive,LookUpMast lookUpMast);
	
	

}
