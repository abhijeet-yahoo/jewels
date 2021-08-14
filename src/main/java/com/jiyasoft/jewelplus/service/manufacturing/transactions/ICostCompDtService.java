package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;

public interface ICostCompDtService {
	
	public List<CostCompDt> findAll();
	
	public void save(CostCompDt costCompDt);
	
	public void delete(int id);
	
	public Long count();
	
	public CostCompDt findOne(int id);
	
	public List<CostCompDt> findByCostingMtAndDeactive(CostingMt costingMt,Boolean deactive);
	
	public List<CostCompDt> findByCostingDtAndDeactive(CostingDt costingDt,Boolean deactive);
	
	public List<CostCompDt> findByItemNoAndOrderDtAndCostingMtAndDeactive(String itemNo,OrderDt orderDt,CostingMt costingMt,Boolean deactive);
	
	public List<CostCompDt> getCostCompDtList(String itemNo,OrderDt orderDt,CostingMt costingMt);
	
	public List<CostCompDt> findByItemNoAndCostingDtAndDeactive(String itemNo,CostingDt costingDt,Boolean deactive);
	
	public void lockUnlockCompDt(Integer CostMtId,Boolean status);
	
	public void updateItemNo(Integer costDtId,String itemNo);
	
	// public List<CostCompDt> getCostCompDtListFromCompTran(Integer bagId);
	
}
