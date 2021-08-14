package com.jiyasoft.jewelplus.service.jpa.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostMetalRate;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.ICostMetalRateRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostMetalRateService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingDtItemService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingMtService;

@Service
@Repository
@Transactional
public class CostMetalRateService implements ICostMetalRateService {
	
	@Autowired
	private ICostMetalRateRepository costMetalRateRepository;
	
	@Autowired
	private ICostingMtService costingMtService;
	
	@Autowired
	private ICostingDtService costingDtService;
	
	@Autowired
	private ICostingDtItemService costingDtItemService;
	
	@Autowired
	private IMetalService metalService;

	@Override
	public CostMetalRate findByCostingMtAndDeactiveAndMetal(
			CostingMt costingMt, Boolean deactive, Metal metal) {
		// TODO Auto-generated method stub
		return costMetalRateRepository.findByCostingMtAndDeactiveAndMetal(costingMt, deactive, metal);
	}

	@Override
	public List<CostMetalRate> findByCostingMtAndDeactive(CostingMt costingMt,
			Boolean deactive) {
		// TODO Auto-generated method stub
		return costMetalRateRepository.findByCostingMtAndDeactive(costingMt, deactive);
	}

	@Override
	public CostMetalRate findOne(int id) {
		// TODO Auto-generated method stub
		return costMetalRateRepository.findOne(id);
	}

	@Override
	public void save(CostMetalRate costMetalRate) {
		// TODO Auto-generated method stub
		costMetalRateRepository.save(costMetalRate);
	}

	@Override
	public String costMetalRateSave(String tabData, int costMtId,
			Principal principal,String costingType,Boolean netWtWithCompFlg) {
		
		String retVal = "-1";
		
		try {
			JSONArray jsonArrays = new JSONArray(tabData);
			
			CostingMt costingMt = costingMtService.findOne(costMtId);
			
			for(int y=0;y<jsonArrays.length();y++){
				
				JSONObject jsonObject = (JSONObject) jsonArrays.get(y);
				
				if(jsonObject.getInt("id") > 0){
									
					CostMetalRate costMetalRate =  findOne(jsonObject.getInt("id"));
					costMetalRate.setRate(Double.parseDouble(jsonObject.get("rate").toString() != "" ? jsonObject.get("rate").toString() : "0.0"));
					costMetalRate.setLossPerc(Double.parseDouble(jsonObject.get("lossPerc").toString() != "" ? jsonObject.get("lossPerc").toString() : "0.0"));
					costMetalRate.setModiBy(principal.getName());
					costMetalRate.setModiDt(new java.util.Date());
					
					save(costMetalRate);
					
					
				}else{
					//add Mode
					
					if(Double.parseDouble(jsonObject.get("rate").toString()) > 0 || Double.parseDouble(jsonObject.get("lossPerc").toString()) > 0){
					
					CostMetalRate costMetalRate = new CostMetalRate();
					costMetalRate.setCostingMt(costingMt);
					costMetalRate.setRate(Double.parseDouble(jsonObject.get("rate").toString() != "" ? jsonObject.get("rate").toString() : "0.0"));
					costMetalRate.setLossPerc(Double.parseDouble(jsonObject.get("lossPerc").toString() != "" ? jsonObject.get("lossPerc").toString() : "0.0"));
					costMetalRate.setMetal(metalService.findByName(jsonObject.get("metal").toString()));
					costMetalRate.setCreatedBy(principal.getName());
					costMetalRate.setCreateDate(new java.util.Date());
					
					save(costMetalRate);
					}
					
				}
				
			}
			
			
			if(costingType.equalsIgnoreCase("merge")){
				
				List<CostingDtItem> costingDtItems = costingDtItemService.findByCostingMtAndDeactive(costingMt, false);
				for (CostingDtItem costingDtItem : costingDtItems) {
				costingDtItemService.applyItemMetal(costingDtItem);
				costingDtItemService.updateItemFob(costingDtItem,netWtWithCompFlg);
				}
				
			}else{
			
				List<CostingDt> costingDts = costingDtService.findByCostingMtAndDeactive(costingMt, false);
				for(CostingDt costingDt:costingDts){
					costingDtService.applyRate(costingDt, principal);
				}
				
			}
			
			
			
			
			
		} catch (Exception e) {
			retVal = "-2";
			e.printStackTrace();
		}
		return retVal;
		
	
	}

}
