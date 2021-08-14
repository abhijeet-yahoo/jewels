package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostMetalDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDtItem;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostMetalDtItemService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingDtItemService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class CostMetalDtItemController {
	
	@Autowired
	private ICostMetalDtItemService costMetalDtItemService;
	
	@Autowired
	private ICostingDtItemService costingDtItemService;
	
	

	@RequestMapping("/costMetalDtItem/listing")
	@ResponseBody
	public String costMetalItemListing(Model model,
			
			@RequestParam(value = "costDtId", required = false) Integer costDtId,Principal principal) {

		StringBuilder sb = new StringBuilder();
		
		CostingDtItem costingDtItem = costingDtItemService.findOne(costDtId);
		List<CostMetalDtItem> cotMetalDtItems = costMetalDtItemService.findByCostingDtItemAndDeactive(costingDtItem, false);
		
		sb.append("{\"total\":").append(cotMetalDtItems.size()).append(",\"rows\": [");
		
		for(CostMetalDtItem cotMetalDtItem:cotMetalDtItems){
		
			sb.append("{\"id\":\"")
				.append(cotMetalDtItem.getId())
				.append("\",\"purity\":\"")
				.append((cotMetalDtItem.getPurity() != null ? cotMetalDtItem.getPurity().getName() : ""))
				.append("\",\"color\":\"")
				.append((cotMetalDtItem.getColor() != null ? cotMetalDtItem.getColor().getName() : ""))
				.append("\",\"partNm\":\"")
				.append((cotMetalDtItem.getPartNm() != null ? cotMetalDtItem.getPartNm().getFieldValue() : ""))
				.append("\",\"qty\":\"")
				.append((cotMetalDtItem.getMetalPcs() != null ? cotMetalDtItem.getMetalPcs() : ""))
				.append("\",\"metalWt\":\"")
				.append((cotMetalDtItem.getMetalWeight() != null ? cotMetalDtItem.getMetalWeight() : ""))
				.append("\",\"actMetalWt\":\"")
				.append((cotMetalDtItem.getActMetalWeight() != null ? cotMetalDtItem.getActMetalWeight() : ""))
				.append("\",\"metalRate\":\"")
				.append((cotMetalDtItem.getMetalRate() != null ? cotMetalDtItem.getMetalRate() : ""))
				.append("\",\"perGramRate\":\"")
				.append((cotMetalDtItem.getPerGramRate() != null ? cotMetalDtItem.getPerGramRate() : ""))
				.append("\",\"lossPerc\":\"")
				.append((cotMetalDtItem.getLossPerc() != null ? cotMetalDtItem.getLossPerc() : ""))
				.append("\",\"metalValue\":\"")
				.append((cotMetalDtItem.getMetalValue() != null ? cotMetalDtItem.getMetalValue() : ""))
				.append("\",\"mainMetal\":\"")
				.append(cotMetalDtItem.getMainMetal());
				 sb.append("\"},");
			
			}
		
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
		
	}
	

	

}
