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
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMetalRate;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMt;
import com.jiyasoft.jewelplus.repository.manufacturing.transactions.IQuotMetalRateRepository;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotMetalRateService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotMtService;

@Service
@Repository
@Transactional
public class QuotMetalRateService implements IQuotMetalRateService {
	
	@Autowired
	private IQuotMetalRateRepository quotMetalRateRepository;
	
	@Autowired
	private IQuotMtService quotMtService;

	@Autowired
	private IQuotDtService quotDtService;
	
	@Autowired
	private IMetalService metalService;

	@Override
	public QuotMetalRate findByQuotMtAndDeactiveAndMetal(QuotMt quotMt,
			Boolean deactive, Metal metal) {
		return quotMetalRateRepository.findByQuotMtAndDeactiveAndMetal(quotMt, deactive, metal);
	}
	
	
	@Override
	public List<QuotMetalRate> findByQuotMtAndDeactive(QuotMt quotMt,
			Boolean deactive) {
		return quotMetalRateRepository.findByQuotMtAndDeactive(quotMt, deactive);
	}

	
	@Override
	public QuotMetalRate findOne(int id) {
		return quotMetalRateRepository.findOne(id);
	}
	

	@Override
	public void save(QuotMetalRate quotMetalRate) {
		quotMetalRateRepository.save(quotMetalRate);
	}
	
	@Override
	public String quotMetalRateSave(String tabData, int quotMtId,
			Principal principal,Boolean netWtWithCompFlg) {
		
		String retVal = "-1";
		
		try {
			JSONArray jsonArrays = new JSONArray(tabData);
			
			QuotMt quotMt = quotMtService.findOne(quotMtId);
			
			for(int y=0;y<jsonArrays.length();y++){
				
				JSONObject jsonObject = (JSONObject) jsonArrays.get(y);
				
				if(jsonObject.getInt("id") > 0){
					//edit Mode
					
					QuotMetalRate quotMetalRate =  findOne(jsonObject.getInt("id"));
					quotMetalRate.setRate(Double.parseDouble(jsonObject.get("rate").toString() != "" ? jsonObject.get("rate").toString() : "0.0"));
					quotMetalRate.setLossPerc(Double.parseDouble(jsonObject.get("lossPerc").toString() != "" ? jsonObject.get("lossPerc").toString() : "0.0"));
					quotMetalRate.setModiBy(principal.getName());
					quotMetalRate.setModiDt(new java.util.Date());
					
					save(quotMetalRate);
					
					
				}else{
					//add Mode
					
					if(Double.parseDouble(jsonObject.get("rate").toString()) > 0 || Double.parseDouble(jsonObject.get("lossPerc").toString()) > 0){
					
					QuotMetalRate quotMetalRate =  new QuotMetalRate();
					quotMetalRate.setQuotMt(quotMt);
					quotMetalRate.setRate(Double.parseDouble(jsonObject.get("rate").toString() != "" ? jsonObject.get("rate").toString() : "0.0"));
					quotMetalRate.setLossPerc(Double.parseDouble(jsonObject.get("lossPerc").toString() != "" ? jsonObject.get("lossPerc").toString() : "0.0"));
					quotMetalRate.setMetal(metalService.findByName(jsonObject.get("metal").toString()));
					quotMetalRate.setCreatedBy(principal.getName());
					quotMetalRate.setCreateDate(new java.util.Date());
					
					save(quotMetalRate);
					}
					
				}
				
			}
			
			
			List<QuotDt> quotDts = quotDtService.findByQuotMtAndDeactive(quotMt, false);
			for(QuotDt quotDt:quotDts){
				//quotDtService.applyRate(quotDt, principal,netWtWithCompFlg);
				
				quotDtService.applyMetal(quotDt);
				quotDtService.updateFob(quotDt,netWtWithCompFlg);
			}
			
			
		} catch (Exception e) {
			retVal = "-2";
			e.printStackTrace();
		}
		
		return retVal;
		
	
	}
	
}
