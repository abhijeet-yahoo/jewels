package com.jiyasoft.jewelplus.service.jpa.marketing.transactions;

import java.security.Principal;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackMetalRate;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackMt;
import com.jiyasoft.jewelplus.repository.marketing.transactions.IPackMetalRateRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackMetalRateService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackMtService;

@Service
@Repository
@Transactional
public class PackMetalRateService implements IPackMetalRateService{
	
	@Autowired
	private IPackMetalRateRepository packMetalRateRepository;
	
	@Autowired
	private IPackMtService packMtService;
	
	@Autowired
	private IMetalService metalService;
	
	@Autowired
	private IPackDtService packDtService;

	@Override
	public List<PackMetalRate> findByPackMt(PackMt packMt) {
		// TODO Auto-generated method stub
		return packMetalRateRepository.findByPackMt(packMt);
	}

	@Override
	public String packMetalRateSave(String tabData, int packMtId, Principal principal) {
	String retVal = "-1";
		
		try {
			JSONArray jsonArrays = new JSONArray(tabData);
			
			PackMt packMt = packMtService.findOne(packMtId);
			
			for(int y=0;y<jsonArrays.length();y++){
				
				JSONObject jsonObject = (JSONObject) jsonArrays.get(y);
				
				if(jsonObject.getInt("id") > 0){
									
					PackMetalRate packMetalRate =  findOne(jsonObject.getInt("id"));
					packMetalRate.setRate(Double.parseDouble(jsonObject.get("rate").toString() != "" ? jsonObject.get("rate").toString() : "0.0"));
					packMetalRate.setLossPerc(Double.parseDouble(jsonObject.get("lossPerc").toString() != "" ? jsonObject.get("lossPerc").toString() : "0.0"));
					packMetalRate.setModiBy(principal.getName());
					packMetalRate.setModiDt(new java.util.Date());
					
					save(packMetalRate);
					
					
				}else{
					//add Mode
					
					if(Double.parseDouble(jsonObject.get("rate").toString()) > 0 || Double.parseDouble(jsonObject.get("lossPerc").toString()) > 0){
					
					PackMetalRate packMetalRate = new PackMetalRate();
					packMetalRate.setPackMt(packMt);
					packMetalRate.setRate(Double.parseDouble(jsonObject.get("rate").toString() != "" ? jsonObject.get("rate").toString() : "0.0"));
					packMetalRate.setLossPerc(Double.parseDouble(jsonObject.get("lossPerc").toString() != "" ? jsonObject.get("lossPerc").toString() : "0.0"));
					packMetalRate.setMetal(metalService.findByName(jsonObject.get("metal").toString()));
					packMetalRate.setCreatedBy(principal.getName());
					packMetalRate.setCreatedDate(new java.util.Date());
					
					save(packMetalRate);
					}
					
				}
				
			}
			
			
	
			
			
			List<PackDt> packDts = packDtService.findByPackMt(packMt);
			for (PackDt packDt : packDts) {
				packDtService.applyMetal(packDt);
				packDtService.updateFob(packDt);
			}
			
			
			
		} catch (Exception e) {
			retVal = "-2";
			e.printStackTrace();
		}
		return retVal;
	}

	@Override
	public PackMetalRate findOne(int id) {
		// TODO Auto-generated method stub
		return packMetalRateRepository.findOne(id);
	}

	@Override
	public void save(PackMetalRate packMetalRate) {
		// TODO Auto-generated method stub
		packMetalRateRepository.save(packMetalRate);
		
	}

	@Override
	public PackMetalRate findByPackMtAndMetal(PackMt packMt, Metal metal) {
		// TODO Auto-generated method stub
		return packMetalRateRepository.findByPackMtAndMetal(packMt, metal);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		packMetalRateRepository.delete(id);
	}

}
