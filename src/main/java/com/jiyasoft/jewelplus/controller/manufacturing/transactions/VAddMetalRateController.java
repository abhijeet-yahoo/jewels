package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.ClientWastage;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddMetalRate;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IClientWastageService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostMetalRateService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddMetalRateService;

@Controller
@RequestMapping("/manufacturing/transactions")
public class VAddMetalRateController {
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private IMetalService metalService;
	
	@Autowired
	private ICostingMtService costingMtService;
	
	@Autowired
	private ICostMetalRateService costMetalRateService;
	
	@Autowired
	private IClientWastageService clientWastageService;
	
	@Autowired
	private IVAddDtService dtService;
	
	@Autowired
	private IVAddMetalRateService metalRateService;

	
	//-----listing from costRate------//
	@RequestMapping("/costValueMetal/rateMaster/listing")
	@ResponseBody
	public String costMetalRateListing(
			@RequestParam(value = "partyId", required = false) Integer partyId,
			@RequestParam(value = "costMtId", required = false) Integer costMtId) {
		
		//List<Metal> metalList = metalSEr 
		
		//System.err.println("partyId  ====== "+partyId);
		//System.err.println("quotMtId ====== "+quotMtId);
		
		Party party = partyService.findOne(partyId);
		CostingMt costingMt = costingMtService.findOne(costMtId);
		
		List<Metal> metalList = metalService.findActiveMetals();
		List<VAddMetalRate> addMetalRates = metalRateService.findByCostingMtAndDeactive(costingMt, false);

		
		List<VAddMetalRate> finalCostMetalRateList = new ArrayList<VAddMetalRate>();
		
		if(addMetalRates.size() > 0){
			//list is not empty
			
			for(Metal metal:metalList){
				Boolean contains = false;
				
				VAddMetalRate addMetalRateNew = new VAddMetalRate();
				
				addMetalRateNew.setMetal(metal);
				
				for(VAddMetalRate addMetalRate:addMetalRates){
					if(metal.getId().equals(addMetalRate.getMetal().getId())){
						
						System.err.println("metalId = "+addMetalRate.getMetal().getId());
						System.err.println("id = "+addMetalRate.getId());
						
						addMetalRateNew.setId(addMetalRate.getId());
						addMetalRateNew.setRate(addMetalRate.getRate());
						addMetalRateNew.setLossPerc(addMetalRate.getLossPerc());
						contains = true;
						break;
					}
				}
				
				if(!contains){
					ClientWastage clientWastage = clientWastageService.findByMetalAndPartyAndDeactive(metal, party, false);
					addMetalRateNew.setId(0);
					addMetalRateNew.setRate(0.0);
					addMetalRateNew.setLossPerc(clientWastage != null ? clientWastage.getWastagePerc() : 0.0);
				}
				
				finalCostMetalRateList.add(addMetalRateNew);
				
			}
			
		}else{
			//list is empty
			
			for(Metal metal:metalList){
				
				ClientWastage clientWastage = clientWastageService.findByMetalAndPartyAndDeactive(metal, party, false);
				VAddMetalRate addMetalRate = new VAddMetalRate();
				addMetalRate.setId(0);
				addMetalRate.setMetal(metal);
				addMetalRate.setRate(0.0);
				addMetalRate.setLossPerc(clientWastage != null ? clientWastage.getWastagePerc() : 0.0);
				finalCostMetalRateList.add(addMetalRate);
				
			}
			
		}
		
		
		
			StringBuilder sb = new StringBuilder();
			
			sb.append("{\"total\":").append(finalCostMetalRateList.size()).append(",\"rows\": [");
		
			for(VAddMetalRate addMetalRate:finalCostMetalRateList){
				sb.append("{\"id\":\"")
					.append(addMetalRate.getId())
					.append("\",\"metal\":\"")
					.append(addMetalRate.getMetal() != null ? addMetalRate.getMetal().getName() : "")
					.append("\",\"rate\":\"")
					.append(addMetalRate.getRate())
					.append("\",\"lossPerc\":\"")
					.append(addMetalRate.getLossPerc())
				.append("\"},");
			}
		
		
			String str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
			str += "]}";
			
			System.out.println(str);
			
			return str;
	}
	
	
	@ResponseBody
	@RequestMapping("/costValueMetal/addTabData")
	public String costMetalRateSave(
			@RequestParam(value = "tabData") String tabData,
			@RequestParam(value = "costMtId") int costMtId,
			@RequestParam(value = "applyVal") int applyVal,Principal principal){
		
		
		String retVal= metalRateService.vAddMetalRateSave(tabData, costMtId,applyVal,principal);
		
		
		return retVal;
	}
	
}
