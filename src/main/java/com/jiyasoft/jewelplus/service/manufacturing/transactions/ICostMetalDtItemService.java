package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostMetalDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;

public interface ICostMetalDtItemService {
	
public List<CostMetalDtItem> findAll();
	
	public void save(CostMetalDtItem costMetalDtItem);

	public void delete(int id);

	public Long count();

	public CostMetalDtItem findOne(int id);
	
	public List<CostMetalDtItem> findByCostingMtAndDeactive(CostingMt costingMt,Boolean deactive);
	
	public List<CostMetalDtItem> findByCostingDtItemAndDeactive(CostingDtItem costingDtItem,Boolean deactive);
	
	public CostMetalDtItem findByCostingDtItemAndDeactiveAndMainMetal(CostingDtItem costingDtItem,Boolean deactive,Boolean mainMetal);

	public CostMetalDtItem findByCostingDtItemAndDeactiveAndPartNm(CostingDtItem costingDtItem,Boolean deactive,LookUpMast lookUpMast);


}
