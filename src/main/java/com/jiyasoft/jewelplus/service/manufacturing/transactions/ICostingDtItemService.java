package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostCompDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostStnDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.mysema.query.Tuple;

public interface ICostingDtItemService {
	
	public void delete(int id);
	
	public void save(CostingDtItem costingDtItem);
	
	public CostingDtItem findOne(int id);
	
	public CostingDtItem findByUniqueId(Long uniqueId);
	
	public List<CostingDtItem> findByCostingMtAndDeactive(CostingMt costingMt,Boolean deactive);
	
	public String applyItemRate(CostingDtItem costingDtItem, Principal principal,Boolean netWtWithCompFlg); 
	
	public Page<CostingDtItem> searchAll(Integer limit, Integer offset, String sort,
			String order, String name,Integer mtId);
	
	
	public Page<CostingDtItem> costingItemListBySetNo(Integer limit, Integer offset, String sort,
			String order, String name,String  mtIds,Integer setNo);
	

	public String applyItemMetal(CostingDtItem costingDtItem);
	
	public String applyItemStoneRate(List<CostStnDtItem> costStnDtItems);
	
	public CostStnDtItem applySettingRate(CostStnDtItem costStnDtItem);
	
	public CostStnDtItem applyHandlingRate(CostStnDtItem costStnDtItem);
	public CostStnDtItem applyStoneRate(CostStnDtItem costStnDtItem);
	
	public String applyItemCompRate(List<CostCompDtItem>costCompDtItems);
	
	public CostCompDtItem applyCompRate(CostCompDtItem costCompDtItem);
	
	public String applyItemLabRate(CostingDtItem costingDtItem,Principal principal);
	 
	public String updateItemFob(CostingDtItem costingDtItem,Boolean netWtWithCompFlg);
	
	public String costingDtItemSave( Integer costDtId,Principal principal);
	
	public List<Tuple> getCostingOrderDtList(Integer invId, Boolean deactive);
	
	public CostingDtItem findByOrderDtAndCostingMtAndDeactive(OrderDt orderDt,CostingMt costingMt,Boolean deactive);
	
	public String costingDtItemSave(Integer costDtItemId,Double perPcDiscAmount,Principal principal,Boolean netWtWithCompFlg);
	
	public String deleteCostDtItem(Integer costDtId, Principal principal);
	
	
	public String updateNetWt(CostingDtItem costingDtItem,Boolean netWtWithComp);
	
	public String updateDtNetWtAndMetalDetails(Principal principal,Integer dtId, Double netWt,Boolean netWtWithCompFlg);


	public int  getUpdateBarcode(Integer costMtId, Principal principal);
	
	public String costDtItemListing(Integer limit,Integer offset,String sort,String order,String search,String pInvNo);
	
	public String costDtItemReportListing(Integer limit,Integer offset,String sort,String order,String search,String mtIds,Integer setNoId);
}
