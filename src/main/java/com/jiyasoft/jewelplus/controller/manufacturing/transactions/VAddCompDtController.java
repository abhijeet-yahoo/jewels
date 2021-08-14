package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddDt;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddDtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class VAddCompDtController {

	@Autowired
	private IVAddCompDtService vAddCompDtService;
	
	@Autowired
	private IVAddDtService vAddDtService;
	
	
	
	
	@RequestMapping("/vAddCostCompDt/listing")
	@ResponseBody
	public String vAddCostCompDtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "vAddDtId", required = false) Integer vAddDtId) {

		StringBuilder sb = new StringBuilder();
		
		
		VAddDt vAddDt = vAddDtService.findOne(vAddDtId);
		List<VAddCompDt> vAddCompDts = vAddCompDtService.findByVAddDtAndDeactive(vAddDt, false);
		
		sb.append("{\"total\":").append(vAddCompDts.size()).append(",\"rows\": [");
		
			for(VAddCompDt vAddCompDt:vAddCompDts){
				
			sb.append("{\"id\":\"")
				.append(vAddCompDt.getId())
				.append("\",\"compName\":\"")
				.append((vAddCompDt.getComponent() != null ? vAddCompDt.getComponent().getName() : ""))
				.append("\",\"kt\":\"")
				.append((vAddCompDt.getPurity() != null ? vAddCompDt.getPurity().getName() : ""))
				.append("\",\"color\":\"")
				.append((vAddCompDt.getColor() != null ? vAddCompDt.getColor().getName() : ""))
				.append("\",\"metalWt\":\"")
				.append((vAddCompDt.getMetalWt() != null ? vAddCompDt.getMetalWt() : ""))
				.append("\",\"perGramMetalRate\":\"")
				.append((vAddCompDt.getPerGramMetalRate() != null ? vAddCompDt.getPerGramMetalRate() : ""))
				.append("\",\"lossPerc\":\"")
				.append((vAddCompDt.getLossPerc() != null ? vAddCompDt.getLossPerc() : ""))
				.append("\",\"lossWt\":\"")
				.append((vAddCompDt.getLossWt() != null ? vAddCompDt.getLossWt() : ""))
				.append("\",\"lossValue\":\"")
				.append((vAddCompDt.getLossValue() != null ? vAddCompDt.getLossValue() : ""))
				.append("\",\"metalValue\":\"")
				.append((vAddCompDt.getMetalValue() != null ? vAddCompDt.getMetalValue() : ""))
				.append("\",\"rate\":\"")
				.append((vAddCompDt.getCompRate() != null ? vAddCompDt.getCompRate() : ""))
				.append("\",\"value\":\"")
				.append((vAddCompDt.getCompValue() != null ? vAddCompDt.getCompValue() : ""))
				.append("\",\"rLock\":\"")
				.append((vAddCompDt.getrLock() == null ? "": (vAddCompDt.getrLock() ? "Lock": "Unlock"))) //1 = lock & 0 = unlock
				.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doCompDtLockUnLock(")
				.append(vAddCompDt.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				.append("\"},");
				
			}
		
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
