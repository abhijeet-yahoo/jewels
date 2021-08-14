package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostLabDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;

public interface ICostLabDtItemService {

	public void save(CostLabDtItem costLabDtItem);

	public void delete(int id);

	public Long count();

	public CostLabDtItem findOne(int id);
	
	public List<CostLabDtItem> findByCostingMtAndDeactive(CostingMt costingMt,Boolean deactive);
	
	public List<CostLabDtItem> findByCostingDtItemAndDeactive(CostingDtItem costingDtItem,Boolean deactive);
	
	public int labAsPerMaster(Integer costMtId);
	
	public List<CostLabDtItem> findByItemNoAndCostingDtItemAndDeactive(String itemNo,CostingDtItem costingDtItem,Boolean deactive);
	
	public void lockUnlockLabDt(Integer CostMtId,Boolean status);
	
	public void updateItemNo(Integer costDtId,String itemNo);
	
	public List<CostLabDtItem>findByCostingDtItemAndMetalAndDeactive(CostingDtItem costingDtItem,Metal metal,Boolean deactive );
	
	public String costLabDtItemSave(CostLabDtItem costLabDtItem, Integer id,Integer costMtId, Integer costDtId, Principal principal,Boolean netWtWithCompFlg);
	
	
}
