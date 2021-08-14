package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;

public interface ICostStnDtService {

	public List<CostStnDt> findAll();
	
	public void save(CostStnDt costStnDt);

	public void delete(int id);

	public Long count();

	public CostStnDt findOne(int id);
	
	public List<CostStnDt> findByCostingMtAndDeactive(CostingMt costingMt,Boolean deactive);
	
	public List<CostStnDt> findByCostingDtAndDeactive(CostingDt costingDt,Boolean deactive);
	
	public List<CostStnDt> findByItemNoAndOrderDtAndCostingMtAndDeactive(String itemNo,OrderDt orderDt,CostingMt costingMt,Boolean deactive);
	
	public List<CostStnDt> findByItemNoAndCostingDtAndDeactive(String itemNo,CostingDt costingDt,Boolean deactive);
	
	public void lockUnlockStnDt(Integer CostMtId,Boolean status);
	
	public void updateItemNo(Integer costDtId,String itemNo);

}
