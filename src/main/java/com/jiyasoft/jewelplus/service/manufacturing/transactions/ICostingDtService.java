package com.jiyasoft.jewelplus.service.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.mysema.query.Tuple;

public interface ICostingDtService {

	public List<CostingDt> findAll();
	
	public void save(CostingDt costingDt);
	
	public void delete(int id);
	
	public Long count();
	
	public CostingDt findOne(int id);
	
	public CostingDt findByUniqueId(Long uniqueId);
	
	public List<CostingDt> findByCostingMtAndDeactive(CostingMt costingMt,Boolean deactive);
	
	public String applyRate(CostingDt costingDt, Principal principal); 
	
	 
	
	public String calculateFinalPrice(String finalPrice);
	
	public List<CostingDt> findByItemNoAndOrderDtAndCostingMtAndDeactive(String itemNo,OrderDt orderDt,CostingMt costingMt,Boolean deactive);
	
	public List<CostingDt> findByOrderDtAndCostingMtAndDeactive(OrderDt orderDt,CostingMt costingMt,Boolean deactive);
	
	public List<CostingDt> getCostingDtList(CostingMt costingMt);
	
	public Page<CostingDt> findByItemNo(Integer limit, Integer offset, String sort, String order, String itemNo, Boolean onlyActive);
	
	public List<CostingDt> findByItemNoAndDeactive(String itemNo,Boolean deactive);
	
	public void lockUnlockDt(Integer CostMtId,Boolean status);
	
	public void resetStyle(Integer orderDtId,Integer styleId,String styleNo,String version);
	
	public void updateCostingDtDispPercent(Integer mtId,Double dispPerc);
	
	
	public Page<CostingDt> searchAll(Integer limit, Integer offset, String sort,
			String order, String name,Integer mtId);
	

	public String applyMetal(CostingDt costingDt);
	
	public String applyStoneRate(List<CostStnDt> costStnDts);
	
	public String applyCompRate(List<CostCompDt>costCompDts);
	
	public String applyLabRate(CostingDt costingDt,Principal principal);
	 
	public String updateFob(CostingDt costingDt);
	
	public String costingDtSave( Integer costDtId,Double grossWt,Double other,Integer partyId,String itemNo,Double dispPercentDt,Double lossPercDt,Principal principal, String costingType);
	
	public Integer getMaxSetNo(Integer mtId);
	
	public List<Tuple> getSetNoList(Integer costMtId);
	
	public int lockDtInvoice(Integer costMtId, Integer setNo, Principal principal, Boolean vRLock);
	
	public Integer getMaxSetNoByOrder(Integer mtId,Integer sordDtId);
	
	public String deleteCostingDt(Integer dtId);
	
	public String checkDiaTolerance(String bagNm);
	
	public String costDtListing(Integer limit,Integer offset,String sort,String order,String search,String pInvNo,Boolean netWtWithComp);
	
	public String updateNetWt(CostingDt costingDt,Boolean netWtWithComp);
	
}
