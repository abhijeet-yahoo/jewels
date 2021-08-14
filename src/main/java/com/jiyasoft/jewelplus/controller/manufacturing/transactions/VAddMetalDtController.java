package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VaddMetalDt;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVaddMetalDtService;


@RequestMapping("/manufacturing/transactions")
@Controller
public class VAddMetalDtController {

	@Autowired
	private IVaddMetalDtService vaddMetalDtService;

	@Autowired
	private IVAddDtService vAddDtService;
	
	@ModelAttribute("costingMt")
	public CostingMt construct() {
		return new CostingMt();
	}
	
	
	@ModelAttribute("vAddDt")
	public VAddDt constructDt() {
		return new VAddDt();
	}
	
	@ModelAttribute("vAddMetalDt")
	public VaddMetalDt construMetal(){
		return new VaddMetalDt();
	}
	
	@RequestMapping("/vAddCostMetalDt/listing")
	@ResponseBody
	public String vAddMetalListing(@RequestParam(value = "vAddDtId", required = false) Integer vAddDtId){
		
		StringBuilder sb = new StringBuilder();
		
		VAddDt vAddDt = vAddDtService.findOne(vAddDtId);
		List<VaddMetalDt> vaddMetalDts = vaddMetalDtService.findByVaddDtAndDeactive(vAddDt, false);
		
		sb.append("{\"total\":").append(vaddMetalDts.size()).append(",\"rows\": [");
		
		for(VaddMetalDt vaddMetalDt:vaddMetalDts){
		
			sb.append("{\"id\":\"")
				.append(vaddMetalDt.getId())
				.append("\",\"purity\":\"")
				.append((vaddMetalDt.getPurity() != null ? vaddMetalDt.getPurity().getName() : ""))
				.append("\",\"color\":\"")
				.append((vaddMetalDt.getColor() != null ? vaddMetalDt.getColor().getName() : ""))
				.append("\",\"partNm\":\"")
				.append((vaddMetalDt.getPartNm() != null ? vaddMetalDt.getPartNm().getFieldValue() : ""))
				.append("\",\"qty\":\"")
				.append((vaddMetalDt.getMetalPcs() != null ? vaddMetalDt.getMetalPcs() : ""))
				.append("\",\"metalWt\":\"")
				.append((vaddMetalDt.getMetalWeight() != null ? vaddMetalDt.getMetalWeight() : ""))
				.append("\",\"metalRate\":\"")
				.append((vaddMetalDt.getMetalRate() != null ? vaddMetalDt.getMetalRate() : ""))
				.append("\",\"perGramRate\":\"")
				.append((vaddMetalDt.getPerGramRate() != null ? vaddMetalDt.getPerGramRate() : ""))
				.append("\",\"lossPerc\":\"")
				.append((vaddMetalDt.getLossPerc() != null ? vaddMetalDt.getLossPerc() : ""))
				.append("\",\"lossWt\":\"")
				.append((vaddMetalDt.getLossWt() != null ? vaddMetalDt.getLossWt() : ""))
				.append("\",\"lossValue\":\"")
				.append((vaddMetalDt.getLossValue() != null ? vaddMetalDt.getLossValue() : ""))
				.append("\",\"metalValue\":\"")
				.append((vaddMetalDt.getMetalValue() != null ? vaddMetalDt.getMetalValue() : ""))
				.append("\",\"mainMetal\":\"")
				.append(vaddMetalDt.getMainMetal())
				.append("\"},");
			
			}
		
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}
}
