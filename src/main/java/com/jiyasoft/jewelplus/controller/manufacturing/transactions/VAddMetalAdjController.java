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
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddMetalAdj;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddMetalInv;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddMetalAdjService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddMetalInvService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class VAddMetalAdjController {

	@Autowired
	private IVAddMetalAdjService vAddMetalAdjService;
	
	@Autowired
	private IVAddMetalInvService vAddMetalInvService;
	
	@ModelAttribute("vAddMetalAdj")
	private VAddMetalAdj construct(){
		return new VAddMetalAdj();
	}

	
	@RequestMapping("/vAddMetalAdj/listing")
	@ResponseBody
	public String vAddMetalAdjListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "vAddMetalInvId", required = false) Integer vAddMetalInvId) {

		StringBuilder sb = new StringBuilder();
		
		sb.append("{\"total\":").append(vAddMetalAdjService.count()).append(",\"rows\": [");
		
		VAddMetalInv vAddMetalInv = vAddMetalInvService.findOne(vAddMetalInvId);
		List<VAddMetalAdj> vAddMetalAdjs = vAddMetalAdjService.findByVAddMetalInv(vAddMetalInv);

		
			for(VAddMetalAdj vAddMetalAdj:vAddMetalAdjs){
				
				sb.append("{\"id\":\"")
					.append(vAddMetalAdj.getId())
					.append("\",\"invNo\":\"")
					.append((vAddMetalAdj.getMetalInvNo() != null ? vAddMetalAdj.getMetalInvNo() : ""))
					.append("\",\"invDate\":\"")
					.append((vAddMetalAdj.getInvDateStr() != null ? vAddMetalAdj.getInvDateStr() : ""))
					.append("\",\"adjustmentWt\":\"")
					.append(vAddMetalAdj.getAdjustmentWt())
					.append("\",\"rate\":\"")
					.append(vAddMetalAdj.getMetalPurchaseDt().getRate())
					.append("\",\"in999\":\"")
					.append(vAddMetalAdj.getMetalPurchaseDt().getIn999())
					.append("\"},");
				
			}
		
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
		
	}
	
	
	
	@RequestMapping(value = "/vAddMetalAdj/add", method=RequestMethod.POST)
	@ResponseBody
	public String addEdit(@Valid
			@ModelAttribute("vAddMetalAdj") VAddMetalAdj vAddMetalAdj,
			@RequestParam(value="tempMetalInwId" ,required=false) String tempMetalInwId,
			@RequestParam(value="adjustWt" ,required=false) String adjustWt,
			@RequestParam(value="costMtIdPk" ,required=false) Integer costMtIdPk,
			@RequestParam(value="vAddMetalInvPkId" ,required=false) Integer vAddMetalInvPkId,
			BindingResult result,RedirectAttributes redirectAttributes, Principal principal){
		

		String retVal = vAddMetalAdjService.saveMetalAdjustment(tempMetalInwId, adjustWt, costMtIdPk, vAddMetalInvPkId, principal);
		
		
		
		return retVal;
		
	}
	
	

}
