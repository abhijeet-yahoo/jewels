package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Component;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddDt;

public interface IVAddCompDtService {

	public void save(VAddCompDt vAddCompDt);
	
	public void saveAll(List<VAddDt> vaddDt);
	
	public void delete(int id);
	
	public VAddCompDt findOne(int id);
	
	public Long count();
	
	public List<VAddCompDt> findByVAddDtAndDeactive(VAddDt vAddDt, Boolean deactive);
	
	public List<VAddCompDt> getComponentStockListing(CostingMt costingMt,List<Component> component);
	
	public 	List<VAddCompDt> findByComponentAndPurityAndColor(Component component,Purity purity,Color color);
	
	public List<VAddCompDt> findByCostingMtAndDeactive(CostingMt costingMt,Boolean deactive);
	
}
