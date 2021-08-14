package com.jiyasoft.jewelplus.controller.manufacturing.masters;

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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMetalRate;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IClientWastageService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderMetalRateService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderMtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;


@RequestMapping("/manufacturing/masters")
@Controller
public class OrderMetalRateController {
	
	@Value("${netWtWithComp}")
	private Boolean netWtWithCompFlg;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private IOrderMtService orderMtService;
	
	@Autowired
	private IOrderMetalRateService orderMetalRateService;
	
	@Autowired
	private IMetalService metalService;
	
	@Autowired
	private IClientWastageService clientWastageService;
		

	@RequestMapping("/orderMetalRate/rateMaster/listing")
	@ResponseBody
	public String orderMetalRateListing(
			@RequestParam(value = "partyId", required = false) Integer partyId,
			@RequestParam(value = "orderMtId", required = false) Integer orderMtId) {
		
				
		Party party = partyService.findOne(partyId);
		OrderMt orderMt = orderMtService.findOne(orderMtId);
		
		List<Metal> metalList = metalService.findActiveMetals();
		List<OrderMetalRate> orderMetalRates = orderMetalRateService.findByOrderMtAndDeactive(orderMt, false);
		
		List<OrderMetalRate> finalOrderMetalRateList = new ArrayList<OrderMetalRate>();
		
		if(orderMetalRates.size() > 0){
			//list is not empty
			
			for(Metal metal:metalList){
				Boolean contains = false;
				
				OrderMetalRate orderMetalRateNew = new OrderMetalRate();
				
				orderMetalRateNew.setMetal(metal);
				
				for(OrderMetalRate orderMetalRate:orderMetalRates){
					
					if(metal.getId().equals(orderMetalRate.getMetal().getId())){
						orderMetalRateNew.setId(orderMetalRate.getId());
						orderMetalRateNew.setRate(orderMetalRate.getRate());
						orderMetalRateNew.setLossPerc(orderMetalRate.getLossPerc());
						contains = true;
						break;
					}
				}
				
				if(!contains){
					ClientWastage clientWastage = clientWastageService.findByMetalAndPartyAndDeactive(metal, party, false);
					orderMetalRateNew.setId(0);
					orderMetalRateNew.setRate(0.0);
					orderMetalRateNew.setLossPerc(clientWastage != null ? clientWastage.getWastagePerc() : 0.0);
				}
				
				finalOrderMetalRateList.add(orderMetalRateNew);
				
			}
			
		}else{
			//list is empty
			
			for(Metal metal:metalList){
				
				ClientWastage clientWastage = clientWastageService.findByMetalAndPartyAndDeactive(metal, party, false);
				OrderMetalRate orderMetalRate = new OrderMetalRate();
				orderMetalRate.setId(0);
				orderMetalRate.setMetal(metal);
				orderMetalRate.setRate(0.0);
				orderMetalRate.setLossPerc(clientWastage != null ? clientWastage.getWastagePerc() : 0.0);
				finalOrderMetalRateList.add(orderMetalRate);
				
			}
			
		}
		
		
		
			StringBuilder sb = new StringBuilder();
			
			sb.append("{\"total\":").append(finalOrderMetalRateList.size()).append(",\"rows\": [");
		
			for(OrderMetalRate orderMetalRate:finalOrderMetalRateList){
				sb.append("{\"id\":\"")
					.append(orderMetalRate.getId())
					.append("\",\"metal\":\"")
					.append(orderMetalRate.getMetal() != null ? orderMetalRate.getMetal().getName() : "")
					.append("\",\"rate\":\"")
					.append(orderMetalRate.getRate())
					.append("\",\"lossPerc\":\"")
					.append(orderMetalRate.getLossPerc())
				.append("\"},");
			}
		
		
			String str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
			str += "]}";
			
			System.out.println(str);
			
			return str;
	}

	
	
	@ResponseBody
	@RequestMapping("/orderMetalRate/metalrate/addTabData")
	public String orderMetalRateSave(
			@RequestParam(value = "tabData") String tabData,
			@RequestParam(value = "orderMtId") int orderMtId,Principal principal){
		
		String retVal= orderMetalRateService.orderMetalRateSave(tabData, orderMtId, principal,netWtWithCompFlg);
		
	
		
		
		
		return retVal;
	}
	

}
