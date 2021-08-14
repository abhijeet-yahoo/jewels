package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.ClientWastage;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostMetalRate;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IClientWastageService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostMetalRateService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingMtService;


@Controller
@RequestMapping("/manufacturing/transactions")
public class CostMetalRateController {
	
	@Value("${netWtWithComp}")
	private Boolean netWtWithCompFlg;
	
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
	private ICostingDtService costingDtService;
	
	
	
	//-----listing from costRate------//
			@RequestMapping("/costMetal/rateMaster/listing")
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
				List<CostMetalRate> costMetalRates = costMetalRateService.findByCostingMtAndDeactive(costingMt, false);

				
				List<CostMetalRate> finalCostMetalRateList = new ArrayList<CostMetalRate>();
				
				if(costMetalRates.size() > 0){
					//list is not empty
					
					for(Metal metal:metalList){
						Boolean contains = false;
						
						CostMetalRate costMetalRateNew = new CostMetalRate();
						
						costMetalRateNew.setMetal(metal);
						
						for(CostMetalRate costMetalRate:costMetalRates){
							if(metal.getId().equals(costMetalRate.getMetal().getId())){
								costMetalRateNew.setId(costMetalRate.getId());
								costMetalRateNew.setRate(costMetalRate.getRate());
								costMetalRateNew.setLossPerc(costMetalRate.getLossPerc());
								contains = true;
								break;
							}
						}
						
						if(!contains){
							ClientWastage clientWastage = clientWastageService.findByMetalAndPartyAndDeactive(metal, party, false);
							costMetalRateNew.setId(0);
							costMetalRateNew.setRate(0.0);
							costMetalRateNew.setLossPerc(clientWastage != null ? clientWastage.getWastagePerc() : 0.0);
						}
						
						finalCostMetalRateList.add(costMetalRateNew);
						
					}
					
				}else{
					//list is empty
					
					for(Metal metal:metalList){
						
						ClientWastage clientWastage = clientWastageService.findByMetalAndPartyAndDeactive(metal, party, false);
						CostMetalRate costMetalRate = new CostMetalRate();
						costMetalRate.setId(0);
						costMetalRate.setMetal(metal);
						costMetalRate.setRate(0.0);
						costMetalRate.setLossPerc(clientWastage != null ? clientWastage.getWastagePerc() : 0.0);
						finalCostMetalRateList.add(costMetalRate);
						
					}
					
				}
				
				
				
					StringBuilder sb = new StringBuilder();
					
					sb.append("{\"total\":").append(finalCostMetalRateList.size()).append(",\"rows\": [");
				
					for(CostMetalRate costMetalRate:finalCostMetalRateList){
						sb.append("{\"id\":\"")
							.append(costMetalRate.getId())
							.append("\",\"metal\":\"")
							.append(costMetalRate.getMetal() != null ? costMetalRate.getMetal().getName() : "")
							.append("\",\"rate\":\"")
							.append(costMetalRate.getRate())
							.append("\",\"lossPerc\":\"")
							.append(costMetalRate.getLossPerc())
						.append("\"},");
					}
				
				
					String str = sb.toString();
					str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
					str += "]}";
					
					System.out.println(str);
					
					return str;
			}
		
			
			
			@ResponseBody
			@RequestMapping("/costMetalrate/addTabData")
			public String costMetalRateSave(
					@RequestParam(value = "tabData") String tabData,
					@RequestParam(value = "costMtId") int costMtId,Principal principal,
					@RequestParam(value = "costingType", required = false) String costingType){
				
				
				String retVal= costMetalRateService.costMetalRateSave(tabData, costMtId, principal,costingType,netWtWithCompFlg);
				
				
				return retVal;
			}
			

	

	
	
	

}
