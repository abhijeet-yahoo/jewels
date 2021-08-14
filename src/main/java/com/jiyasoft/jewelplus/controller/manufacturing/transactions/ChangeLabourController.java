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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.InwardType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LabourType;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IInwardTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILabourChargeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILabourTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostLabDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostStnDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class ChangeLabourController {

	
	@Autowired
	private ILabourChargeService labourChargeService;
	
	@Autowired
	private ILabourTypeService labourTypeService;
	
	@Autowired
	private ICostLabDtService costLabDtService;
	
	@Autowired
	private ICostingMtService costingMtService; 
	
	@Autowired
	private ICostingDtService costingDtService; 
	
	@Autowired
	private IInwardTypeService inwardTypeService;
	
	@Autowired
	private ICostStnDtService costStnDtService;
	
	
	@ModelAttribute("costingMt")
	public CostingMt construct() {
		return new CostingMt();
	}
	
	@ModelAttribute("costLabDt")
	public CostLabDt constructCostLabDt() {
		return new CostLabDt();
	}
	
	@ModelAttribute("costStnDt")
	public CostStnDt constructCostStnDt() {
		return new CostStnDt();
	}
	
	
	@RequestMapping("/changeLabour")
	public String changeLabour(Model model) {
		return "changeLabour";
	}
	
	
	
	//------------------------inward type------>>>>>>>>
	
	
	@RequestMapping("/changeInwardType/listing")
	@ResponseBody
	public String changeInwardTypeListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			Principal principal) {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("{\"total\":").append(inwardTypeService.count()).append(",\"rows\": [");
		
		List<InwardType> inwardTypes = inwardTypeService.findAll();
		
		for (InwardType inwardType : inwardTypes) {

			sb.append("{\"id\":\"")
					.append(inwardType.getId())
					.append("\",\"inwardType\":\"")
					.append((inwardType.getName() == null ? "" : inwardType.getName()))
					.append("\",\"handlingRate\":\"")
					.append((inwardType.getHandlingPercentage() == null ? "" : inwardType.getHandlingPercentage()))
					.append("\"},");
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;

	}
	
	
	
	
	
	
	@ResponseBody
	@RequestMapping(value = "/changeInward/setIRate", method = RequestMethod.POST)
	public String addEditInward(@Valid @ModelAttribute("costStnDt") CostStnDt costStnDt,
			BindingResult result, 
			@RequestParam(value = "id") Integer id,
			@RequestParam(value = "inwTypeId") String inwType,
			@RequestParam(value = "inwRate") String inwRate,
			@RequestParam(value = "iCostMtId") Integer  CostMtId,
			@RequestParam(value = "inwType") String  inwTypeNm,
			RedirectAttributes redirectAttributes, Principal principal) {

		String retVal = "-1";

		if (result.hasErrors()) {
			return "error";
		}
		
		
		String invoiceTypeArr[] = inwType.split(",");
		String vInwRate[]		= inwRate.split(",");
		String vInwTypeNm[]		= inwTypeNm.split(",");
		
		
		CostingMt costingMt = costingMtService.findOne(CostMtId);
		
		List<CostStnDt> costStnDts = costStnDtService.findByCostingMtAndDeactive(costingMt, false);
		
		
		for(int i=0; i<invoiceTypeArr.length; i++){
			
			
			for(CostStnDt costStn: costStnDts){
				
				if(costStn.getStoneInwardDt().getStoneInwardMt().getInwardType() != null){
		
					if(costStn.getStoneInwardDt().getStoneInwardMt().getInwardType().getName().equalsIgnoreCase(vInwTypeNm[i])){
						
						if(costStn.getrLock() == false){
						
							costStn.setHandlingRate(Double.parseDouble(vInwRate[i]));
							costStn.setModiBy(principal.getName());
							costStn.setModiDate(new java.util.Date());
							costStnDtService.save(costStn);
						
						}
	
					}
				}
				
			}
			
			
		} // ending main for loop
		

		
		return retVal;

	}
	
	
	
	
	//----------------Labour Type----//-->>>>>>>>
	
	
	@RequestMapping("/changeLabourType/listing")
	@ResponseBody
	public String changeLabourTypeListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			Principal principal) {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("{\"total\":").append(labourTypeService.count()).append(",\"rows\": [");
		
		List<LabourType> labourTypes = labourTypeService.findAll();
		
		Double rate = 0.0;
		
		for (LabourType labType : labourTypes) {
			
			
			if(labType.getName().equalsIgnoreCase("loss") || labType.getName().equalsIgnoreCase("oths")){

				sb.append("{\"id\":\"")
						.append(labType.getId())
						.append("\",\"labType\":\"")
						.append((labType.getName() == null ? "" : labType.getName()))
						.append("\",\"rate\":\"")
						.append(rate)
						.append("\"},");
				
			}
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;

	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/changeLabourRate/setRate", method = RequestMethod.POST)
	public String addEditLabour(@Valid @ModelAttribute("costLabDt") CostLabDt costLabDt,
			BindingResult result, 
			@RequestParam(value = "labIdPk") String labIdPk,
			@RequestParam(value = "labRate") String labRate,
			@RequestParam(value = "mtIdCost") Integer costMtId,
			RedirectAttributes redirectAttributes, Principal principal) {

		String retVal = "-1";

		if (result.hasErrors()) {
			return "error";
		}
		
		
		System.out.println("pk=============="+labIdPk);
		System.out.println("rate============"+labRate);
		
		String labIdpkArray[] = labIdPk.split(",");
		String labRateArray[] = labRate.split(",");
		
		if(labIdpkArray.length <= 0){
			return retVal = "-2";
		}
		
		
		CostingMt costingMt = costingMtService.findOne(costMtId);
		
		List<CostLabDt> costLabDts = costLabDtService.findByCostingMtAndDeactive(costingMt, false);
		
		
		for(int i=0;i<labIdpkArray.length;i++){
			
			LabourType labourType = labourTypeService.findOne(Integer.parseInt(labIdpkArray[i]));
			
			for(CostLabDt costLabDtUpdate:costLabDts){
				
				if(costLabDtUpdate.getLabourType().getName().equalsIgnoreCase(labourType.getName())){
						
					if(costLabDtUpdate.getrLock() == false){
						costLabDtUpdate.setLabourRate(Double.parseDouble(labRateArray[i]));
						costLabDtUpdate.setModiBy(principal.getName());
						costLabDtUpdate.setModiDate(new java.util.Date());
						costLabDtService.save(costLabDtUpdate);
					}
				}
				
			}
			
			
		}
		
		
		
		return retVal;
		
		
	}
	
	
}
