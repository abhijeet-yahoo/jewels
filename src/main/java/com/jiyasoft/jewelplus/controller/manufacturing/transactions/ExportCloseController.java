package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class ExportCloseController {
	
	
	@Autowired
	private ICostingMtService costingMtService;
	
	
	@ModelAttribute("costingMt")
	public CostingMt construct(){
		return new CostingMt();
	}
	
	@RequestMapping("/exportClose")
	public String exportClose(Model model){
		model = populateModel(model);
		return "exportClose";
	}
	
	
	public Model populateModel(Model model){
		model.addAttribute("costMtMap", costingMtService.getCostingList());
		return model;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/exportClose/transfer", method=RequestMethod.POST)
	public String transfer(@RequestParam(value="costMtId") Integer costMtId,
			Principal principal){
		
		String retVal="";
		synchronized (this) {
			retVal = costingMtService.exportCloseTransfer(costMtId, principal);
		}
		return retVal;
	}
	
	
	
	
	
	
	@RequestMapping("/invNo/autoList")
	@ResponseBody
	public String InvList(@RequestParam(value = "term", required = false) String InvNo) {
		
		Page<CostingMt> costMtList = costingMtService.getInvNoAutoFill(15, 0, "invNo",  "ASC", InvNo.toUpperCase(), true);
		
		StringBuilder sb = new StringBuilder();

		for (CostingMt costingMt : costMtList) {
			
			System.out.println("id--------------"+costingMt.getId());
			
			sb.append("\"")
				.append(costingMt.getInvNo())
				.append("\", ");
		}

		String str = "[" + sb.toString().trim();
		str = (str.lastIndexOf(",") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]";

		return str;
	}
	

}
