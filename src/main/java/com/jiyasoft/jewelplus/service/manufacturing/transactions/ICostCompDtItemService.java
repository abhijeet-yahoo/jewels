package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Component;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostCompDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;

public interface ICostCompDtItemService {

public List<CostCompDtItem> findAll();
	
	public void save(CostCompDtItem costCompDtItem);
	
	public void delete(int id);
	
	public Long count();
	
	public CostCompDtItem findOne(int id);
	
	public List<CostCompDtItem> findByCostingMtAndDeactive(CostingMt costingMt,Boolean deactive);
	
	public List<CostCompDtItem> findByCostingDtItemAndDeactive(CostingDtItem costingDtItem,Boolean deactive);
	
	public List<CostCompDtItem> getCostCompDtList(String itemNo,OrderDt orderDt,CostingMt costingMt);
	
	public List<CostCompDtItem> findByItemNoAndCostingDtItemAndDeactive(String itemNo,CostingDtItem costingDtItem,Boolean deactive);
	
	public void lockUnlockCompDt(Integer CostMtId,Boolean status);
	
	public void updateItemNo(Integer costDtId,String itemNo);
	
	public CostCompDtItem findByCostingDtItemAndComponentAndPurityAndColor(CostingDtItem costingDtItem, Component component, Purity purity, Color color);
	
}
