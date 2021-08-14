package com.jiyasoft.jewelplus.controller.manufacturing.transactions;


import java.security.Principal;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddCompAdj;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddCompInv;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddCompAdjService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddCompInvService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class VAddCompAdjController {
	
	
	@Autowired
	private IVAddCompAdjService vAddCompAdjService;
	
	@Autowired
	private IVAddCompInvService vAddCompInvService;
	
	@ModelAttribute("vAddCompAdj")
	private VAddCompAdj construct(){
		return new VAddCompAdj();
	}
	
	
	
	
	@RequestMapping("/vAddCompAdj/listing")
	@ResponseBody
	public String vAddCompAdjListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "vAddCompInvId", required = false) Integer vAddCompInvId) {

		StringBuilder sb = new StringBuilder();
		
		
		
		VAddCompInv vAddCompInv = vAddCompInvService.findOne(vAddCompInvId);
		List<VAddCompAdj> vAddCompAdjs = vAddCompAdjService.findByVAddCompInvAndDeactive(vAddCompInv, false);
		sb.append("{\"total\":").append(vAddCompAdjs.size()).append(",\"rows\": [");
		
			for(VAddCompAdj vAddCompAdj:vAddCompAdjs){
				
			sb.append("{\"id\":\"")
				.append(vAddCompAdj.getId())
				.append("\",\"invNo\":\"")
				.append((vAddCompAdj.getCompInvNo() != null ? vAddCompAdj.getCompInvNo() : ""))
				.append("\",\"invDate\":\"")
				.append((vAddCompAdj.getInvDateStr() != null ? vAddCompAdj.getInvDateStr() : ""))
				.append("\",\"adjustmentWt\":\"")
				.append(vAddCompAdj.getAdjustmentWt())
				.append("\",\"adjustmentPcs\":\"")
				.append(vAddCompAdj.getAdjustmentPcs())
				.append("\",\"componentName\":\"")
				.append((vAddCompAdj.getComponentPurchaseDt().getComponent() != null ? vAddCompAdj.getComponentPurchaseDt().getComponent().getName() : ""))
				.append("\",\"purity\":\"")
				.append((vAddCompAdj.getComponentPurchaseDt().getPurity() != null ? vAddCompAdj.getComponentPurchaseDt().getPurity().getName() : ""))
				.append("\",\"purityConv\":\"")
				.append((vAddCompAdj.getComponentPurchaseDt().getPurity() != null ? vAddCompAdj.getComponentPurchaseDt().getPurity().getPurityConv() : ""))
				
				.append("\",\"color\":\"")
				.append((vAddCompAdj.getComponentPurchaseDt().getColor() != null ? vAddCompAdj.getComponentPurchaseDt().getColor().getName() : ""))
				.append("\"},");
				
			}
		
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
		
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/vAddCompAdj/add", method=RequestMethod.POST)
	public String addEdit(@Valid
			@ModelAttribute("vAddCompAdj") VAddCompAdj vAddCompAdj,
			@RequestParam(value="tempCompPurcId" ,required=false) String tempCompPurcId,
			@RequestParam(value="costMtIdPkforComp" ,required=false) Integer costMtIdPk,
			@RequestParam(value="vAddCompInvPkId" ,required=false) Integer vAddCompInvPkId,
			@RequestParam(value="totAdjCompWt" ,required=false) Double totAdjCompWt,
			@RequestParam(value="totCompPcs" ,required=false) Double totCompPcs,
			BindingResult result,RedirectAttributes redirectAttributes, Principal principal){
		
		
		String retVal="-1";
		
		try {
			
			retVal= vAddCompAdjService.saveCompAdjustment(tempCompPurcId,totAdjCompWt,costMtIdPk,vAddCompInvPkId,totCompPcs, principal);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return retVal;
		
	}
	
	
	
	
	
	
}
