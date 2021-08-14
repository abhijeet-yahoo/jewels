package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;










import com.jiyasoft.jewelplus.domain.manufacturing.masters.ClientWastage;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMetalRate;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMt;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IClientWastageService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotMetalRateService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotMtService;

@Controller
@RequestMapping("/manufacturing/transactions")
public class QuotMetalRateController {
	
	
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private IMetalService metalService;
	
	@Autowired
	private IQuotMtService quotMtService;
	
	@Autowired
	private IQuotMetalRateService quotMetalRateService;
	
	@Autowired
	private IClientWastageService clientWastageService;
	
	@Autowired
	private IQuotDtService quotDtService;
	
	@Value("${netWtWithComp}")
	private Boolean netWtWithCompFlg;
	
	
		//-----listing from quotRate------//
		@RequestMapping("/quotMetal/rateMaster/listing")
		@ResponseBody
		public String quotMetalRateListing(
				@RequestParam(value = "partyId", required = false) Integer partyId,
				@RequestParam(value = "quotMtId", required = false) Integer quotMtId) {
			
			//List<Metal> metalList = metalSEr 
			
			//System.err.println("partyId  ====== "+partyId);
			//System.err.println("quotMtId ====== "+quotMtId);
			
			Party party = partyService.findOne(partyId);
			QuotMt quotMt = quotMtService.findOne(quotMtId);
			
			List<Metal> metalList = metalService.findActiveMetals();
			List<QuotMetalRate> quotMetalRates = quotMetalRateService.findByQuotMtAndDeactive(quotMt, false);
			
			List<QuotMetalRate> finalQuotMetalRateList = new ArrayList<QuotMetalRate>();
			
			if(quotMetalRates.size() > 0){
				//list is not empty
				
				for(Metal metal:metalList){
					Boolean contains = false;
					
					QuotMetalRate quotMetalRateNew = new QuotMetalRate();
					
					quotMetalRateNew.setMetal(metal);
					
					for(QuotMetalRate quotMetalRate:quotMetalRates){
						if(metal.getId().equals(quotMetalRate.getMetal().getId())){
							
							System.err.println("metalId = "+quotMetalRate.getMetal().getId());
							System.err.println("id = "+quotMetalRate.getId());
							
							quotMetalRateNew.setId(quotMetalRate.getId());
							quotMetalRateNew.setRate(quotMetalRate.getRate());
							quotMetalRateNew.setLossPerc(quotMetalRate.getLossPerc());
							contains = true;
							break;
						}
					}
					
					if(!contains){
						ClientWastage clientWastage = clientWastageService.findByMetalAndPartyAndDeactive(metal, party, false);
						quotMetalRateNew.setId(0);
						quotMetalRateNew.setRate(0.0);
						quotMetalRateNew.setLossPerc(clientWastage != null ? clientWastage.getWastagePerc() : 0.0);
					}
					
					finalQuotMetalRateList.add(quotMetalRateNew);
					
				}
				
			}else{
				//list is empty
				
				for(Metal metal:metalList){
					
					ClientWastage clientWastage = clientWastageService.findByMetalAndPartyAndDeactive(metal, party, false);
					QuotMetalRate quotMetalRate = new QuotMetalRate();
					quotMetalRate.setId(0);
					quotMetalRate.setMetal(metal);
					quotMetalRate.setRate(0.0);
					quotMetalRate.setLossPerc(clientWastage != null ? clientWastage.getWastagePerc() : 0.0);
					finalQuotMetalRateList.add(quotMetalRate);
					
				}
				
			}
			
			
			
				StringBuilder sb = new StringBuilder();
				
				sb.append("{\"total\":").append(finalQuotMetalRateList.size()).append(",\"rows\": [");
			
				for(QuotMetalRate quotMetalRate:finalQuotMetalRateList){
					sb.append("{\"id\":\"")
						.append(quotMetalRate.getId())
						.append("\",\"metal\":\"")
						.append(quotMetalRate.getMetal() != null ? quotMetalRate.getMetal().getName() : "")
						.append("\",\"rate\":\"")
						.append(quotMetalRate.getRate())
						.append("\",\"lossPerc\":\"")
						.append(quotMetalRate.getLossPerc())
					.append("\"},");
				}
			
			
				String str = sb.toString();
				str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
				str += "]}";
				
				System.out.println(str);
				
				return str;
		}
	
		
		
		@ResponseBody
		@RequestMapping("/metalrate/addTabData")
		public String quotMetalRateSave(
				@RequestParam(value = "tabData") String tabData,
				@RequestParam(value = "quotMtId") int quotMtId,Principal principal){
			
		
			String retVal= quotMetalRateService.quotMetalRateSave(tabData, quotMtId, principal,netWtWithCompFlg);
			
			
			
			return retVal;
		}
		

}
