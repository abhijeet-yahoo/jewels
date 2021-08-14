package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDt;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostMetalDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingDtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class CostMetalDtController {
	
	@Autowired
	private ICostMetalDtService costMetalDtService;
	
	@Autowired
	private ICostingDtService costingDtService;
	
	@RequestMapping("/costMetalDt/listing")
	@ResponseBody
	public String costMetalListing(Model model,
			
			@RequestParam(value = "costDtId", required = false) Integer costDtId,Principal principal) {

		StringBuilder sb = new StringBuilder();
		
		CostingDt costingDt = costingDtService.findOne(costDtId);
		List<CostMetalDt> cotMetalDts = costMetalDtService.findByCostingDtAndDeactive(costingDt, false);
		
		sb.append("{\"total\":").append(cotMetalDts.size()).append(",\"rows\": [");
		
		for(CostMetalDt cotMetalDt:cotMetalDts){
		
			sb.append("{\"id\":\"")
				.append(cotMetalDt.getId())
				.append("\",\"purity\":\"")
				.append((cotMetalDt.getPurity() != null ? cotMetalDt.getPurity().getName() : ""))
				.append("\",\"color\":\"")
				.append((cotMetalDt.getColor() != null ? cotMetalDt.getColor().getName() : ""))
				.append("\",\"partNm\":\"")
				.append((cotMetalDt.getPartNm() != null ? cotMetalDt.getPartNm().getFieldValue() : ""))
				//.append("\",\"waxWt\":\"")
				//.append((quotMetal.getWaxWt() != null ? quotMetal.getWaxWt() : ""))
				.append("\",\"qty\":\"")
				.append((cotMetalDt.getMetalPcs() != null ? cotMetalDt.getMetalPcs() : ""))
				.append("\",\"metalWt\":\"")
				.append((cotMetalDt.getMetalWeight() != null ? cotMetalDt.getMetalWeight() : ""))
				.append("\",\"metalRate\":\"")
				.append((cotMetalDt.getMetalRate() != null ? cotMetalDt.getMetalRate() : ""))
				.append("\",\"perGramRate\":\"")
				.append((cotMetalDt.getPerGramRate() != null ? cotMetalDt.getPerGramRate() : ""))
				.append("\",\"lossPerc\":\"")
				.append((cotMetalDt.getLossPerc() != null ? cotMetalDt.getLossPerc() : ""))
				.append("\",\"metalValue\":\"")
				.append((cotMetalDt.getMetalValue() != null ? cotMetalDt.getMetalValue() : ""))
				.append("\",\"mainMetal\":\"")
				.append(cotMetalDt.getMainMetal());
				
				/*sb.append("\",\"action1\":\"");
				//if (roleRights.getCanEdit()) {
					sb.append("<a href='javascript:editMetal(").append(quotMetal.getId());
				//} else {
					//sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				//}
				sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
				
				
				sb.append("\",\"action2\":\"");
				//if (roleRights.getCanDelete()) {
					sb.append("<a onClick='javascript:deleteDesignMetal(event, ")
						.append(quotMetal.getId()).append(", 0);' href='javascript:void(0);'");
				//} else {
					//sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				//}
				sb.append(" class='btn btn-xs btn-danger triggerRemove")
				 .append(quotMetal.getId())
				 .append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")*/
				 sb.append("\"},");
			
			}
		
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
		
	}
	

	

}
