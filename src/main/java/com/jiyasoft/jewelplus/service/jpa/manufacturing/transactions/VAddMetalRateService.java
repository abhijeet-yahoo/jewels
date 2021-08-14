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
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddMetalRate;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IVAddMetalRateRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddMetalRateService;


@Service
@Repository
@Transactional
public class VAddMetalRateService implements IVAddMetalRateService{
	
	@Autowired
	private IVAddMetalRateRepository metalRateRepository;
	
	@Autowired
	private ICostingMtService costingMtService;
	
	@Autowired
	private IMetalService metalService;
	
	@Autowired
	private IVAddDtService addDtService;

	@Override
	public VAddMetalRate findByCostingMtAndDeactiveAndMetal(
			CostingMt costingMt, Boolean deactive, Metal metal) {
		// TODO Auto-generated method stub
		return metalRateRepository.findByCostingMtAndDeactiveAndMetal(costingMt, deactive, metal);
	}

	@Override
	public List<VAddMetalRate> findByCostingMtAndDeactive(CostingMt costingMt,
			Boolean deactive) {
		// TODO Auto-generated method stub
		return metalRateRepository.findByCostingMtAndDeactive(costingMt, deactive);
	}

	@Override
	public VAddMetalRate findOne(int id) {
		// TODO Auto-generated method stub
		return metalRateRepository.findOne(id);
	}

	@Override
	public void save(VAddMetalRate vAddMetalRate) {
		// TODO Auto-generated method stub
		metalRateRepository.save(vAddMetalRate);
	}

	@Override
	public String vAddMetalRateSave(String tabData, int costMtId,int applyVal,
			Principal principal) {
		
		String retVal = "-1";
		
		try {
			JSONArray jsonArrays = new JSONArray(tabData);
			
			CostingMt costingMt = costingMtService.findOne(costMtId);
			
			for(int y=0;y<jsonArrays.length();y++){
				
				JSONObject jsonObject = (JSONObject) jsonArrays.get(y);
				
				if(jsonObject.getInt("id") > 0){
						
					
					VAddMetalRate addMetalRate =  findOne(jsonObject.getInt("id"));
					addMetalRate.setRate(Double.parseDouble(jsonObject.get("rate").toString() != "" ? jsonObject.get("rate").toString() : "0.0"));
					addMetalRate.setLossPerc(Double.parseDouble(jsonObject.get("lossPerc").toString() != "" ? jsonObject.get("lossPerc").toString() : "0.0"));
					addMetalRate.setModiBy(principal.getName());
					addMetalRate.setModiDt(new java.util.Date());
					
					save(addMetalRate);
					
					
				}else{
					//add Mode
					
					if(Double.parseDouble(jsonObject.get("rate").toString()) > 0 || Double.parseDouble(jsonObject.get("lossPerc").toString()) > 0){
					
					VAddMetalRate addMetalRate = new VAddMetalRate();
					addMetalRate.setCostingMt(costingMt);
					addMetalRate.setRate(Double.parseDouble(jsonObject.get("rate").toString() != "" ? jsonObject.get("rate").toString() : "0.0"));
					addMetalRate.setLossPerc(Double.parseDouble(jsonObject.get("lossPerc").toString() != "" ? jsonObject.get("lossPerc").toString() : "0.0"));
					addMetalRate.setMetal(metalService.findByName(jsonObject.get("metal").toString()));
					addMetalRate.setCreatedBy(principal.getName());
					addMetalRate.setCreateDate(new java.util.Date());
					
					save(addMetalRate);
					}
					
				}
				
			}
			
			
			List<VAddDt> vAddDts = addDtService.findByCostingMtAndDeactive(costingMt, false);
			for(VAddDt vAddDt:vAddDts){
				if(applyVal == 1){
				addDtService.applyRate(vAddDt, principal);
				}else if(applyVal == 2){
					addDtService.applyWastage(vAddDt);
					
								
				}else if(applyVal == 3){
					addDtService.applyMetal(vAddDt);
								
				}
				
				if(applyVal !=1){
					VAddDt vAddDt2 = addDtService.findOne(vAddDt.getId());
					
					addDtService.updateValueAddition(vAddDt2);
				}
				
			}
			
			
		} catch (Exception e) {
			retVal = "-2";
			e.printStackTrace();
		}
		return retVal;	}

}
