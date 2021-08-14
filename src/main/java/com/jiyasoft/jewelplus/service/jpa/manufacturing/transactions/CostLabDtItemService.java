package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostLabDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.ICostLabDtItemRepository;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostLabDtItemService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingDtItemService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingMtService;

@Service
@Repository
@Transactional
public class CostLabDtItemService implements ICostLabDtItemService{
	
	@Autowired
	private ICostLabDtItemRepository costLabDtItemRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private ICostingDtItemService costingDtItemService;
	
	@Autowired
	private ICostingMtService costingMtService;

	@Override
	public void save(CostLabDtItem costLabDtItem) {
		// TODO Auto-generated method stub
		costLabDtItemRepository.save(costLabDtItem);
	}

	@Override
	public void delete(int id) {
		
		costLabDtItemRepository.delete(id);
		
		/*
		 * CostLabDtItem costLabDtItem = costLabDtItemRepository.findOne(id);
		 * costLabDtItem.setDeactive(true); costLabDtItem.setDeactiveDt(new Date());
		 * costLabDtItemRepository.save(costLabDtItem);
		 */
		
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CostLabDtItem findOne(int id) {
		// TODO Auto-generated method stub
		return costLabDtItemRepository.findOne(id);
	}

	@Override
	public List<CostLabDtItem> findByCostingMtAndDeactive(CostingMt costingMt,
			Boolean deactive) {
		// TODO Auto-generated method stub
		return costLabDtItemRepository.findByCostingMtAndDeactive(costingMt, deactive);
	}

	@Override
	public List<CostLabDtItem> findByCostingDtItemAndDeactive(
			CostingDtItem costingDtItem, Boolean deactive) {
		// TODO Auto-generated method stub
		return costLabDtItemRepository.findByCostingDtItemAndDeactive(costingDtItem, deactive);
	}

	@Override
	public int labAsPerMaster(Integer costMtId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CostLabDtItem> findByItemNoAndCostingDtItemAndDeactive(
			String itemNo, CostingDtItem costingDtItem, Boolean deactive) {
		// TODO Auto-generated method stub
		return costLabDtItemRepository.findByItemNoAndCostingDtItemAndDeactive(itemNo, costingDtItem, deactive);
	}

	@Override
	public void lockUnlockLabDt(Integer CostMtId, Boolean status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateItemNo(Integer costDtId, String itemNo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CostLabDtItem> findByCostingDtItemAndMetalAndDeactive(
			CostingDtItem costingDtItem, Metal metal, Boolean deactive) {
		// TODO Auto-generated method stub
		return costLabDtItemRepository.findByCostingDtItemAndMetalAndDeactive(costingDtItem, metal, deactive);
	}

	@Override
	public String costLabDtItemSave(CostLabDtItem costLabDtItem, Integer id,
			Integer costMtId, Integer costDtId, Principal principal,Boolean netWtWithCompFlg) {
		
		String retVal ="-1";
		
		try {
			
			CostingMt costingMt = costingMtService.findOne(costMtId);
			CostingDtItem costingDtItem = costingDtItemService.findOne(costDtId);
			
			
		/*	if(costLabDtItem.getPcsWise() == true && costLabDtItem.getGramWise() == true ){
				return retVal = "-11";
			}
			
			if(costLabDtItem.getPcsWise() == true && costLabDtItem.getPercentWise() == true ){
				return retVal = "-11";
			}
			
			if(costLabDtItem.getGramWise() == true && costLabDtItem.getPercentWise() == true){
				return retVal = "-11";
			}
			if(costLabDtItem.getPcsWise() == false && costLabDtItem.getGramWise() == false && costLabDtItem.getPercentWise() == false){
				return retVal = "-12";
			}*/
			
			
			int i=0;
			if(costLabDtItem.getPerPcRate() == true){
				i +=1;
			}
			
			if(costLabDtItem.getPerGramRate() == true){
				i +=1;
			}
			
			if(costLabDtItem.getPercentage() == true){
				i +=1;
			}
			
			if(costLabDtItem.getPerCaratRate() == true){
				i +=1;
			}
			
			if(i >1){
				return retVal = "-11";
			}else if(i==0){
				return retVal = "-12";
			}
			
			
			
			
			if (id == null || id.equals("") || (id != null && id == 0)) {
				
				costLabDtItem.setCostingMt(costingMt);
				costLabDtItem.setCostingDtItem(costingDtItem);
				costLabDtItem.setItemNo(costingDtItem.getItemNo());
		//		costLabDtItem.setBagmt(costingDtItem.getBagMt());
				costLabDtItem.setMetal(costLabDtItem.getMetal());
				costLabDtItem.setCreatedBy(principal.getName());
				costLabDtItem.setCreatedDate(new java.util.Date());
				costLabDtItem.setOrderDt(costLabDtItem.getOrderDt());
				
				
			}else{
				costLabDtItem.setId(id);
				costLabDtItem.setCostingMt(costingMt);
				costLabDtItem.setCostingDtItem(costingDtItem);
				costLabDtItem.setItemNo(costLabDtItem.getItemNo());
				costLabDtItem.setMetal(costLabDtItem.getMetal());
		//		costLabDtItem.setBagmt(costLabDtItem.getBagMt());
				costLabDtItem.setOrderDt(costLabDtItem.getOrderDt());
				costLabDtItem.setModiBy(principal.getName());
				costLabDtItem.setModiDate(new java.util.Date());
				retVal = "-2";
			}
			
			
			save(costLabDtItem);
			
			
				
		//costingDtItemService.applyItemLabRate(costingDtItem, principal);
		costingDtItemService.updateItemFob(costingDtItem,netWtWithCompFlg);
					
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retVal;
	}
	
	}

