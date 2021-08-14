package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;

public interface ICostLabDtService {
	
	public List<CostLabDt> findAll();
	
	public void save(CostLabDt costLabDt);

	public void delete(int id);

	public Long count();

	public CostLabDt findOne(int id);
	
	public List<CostLabDt> findByCostingMtAndDeactive(CostingMt costingMt,Boolean deactive);
	
	public List<CostLabDt> findByCostingDtAndDeactive(CostingDt costingDt,Boolean deactive);
	
	public int labAsPerMaster(Integer costMtId);
	
	public List<CostLabDt> findByItemNoAndCostingDtAndDeactive(String itemNo,CostingDt costingDt,Boolean deactive);
	
	public void lockUnlockLabDt(Integer CostMtId,Boolean status);
	
	public void updateItemNo(Integer costDtId,String itemNo);
	
	public List<CostLabDt>findByCostingDtAndMetalAndDeactive(CostingDt costingDt,Metal metal,Boolean deactive );
	
	public String costLabDtSave(CostLabDt costLabDt, Integer id,Integer costMtId, Integer costDtId, Principal principal);
	
}
