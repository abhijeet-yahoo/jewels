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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LabourType;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostJobLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingJobMt;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IInwardTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILabourChargeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILabourTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostJobLabDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingJobDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingJobMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class ChangeLabourJobController {
	
	
	
	@Autowired
	private ILabourChargeService labourChargeService;
	
	@Autowired
	private ILabourTypeService labourTypeService;
	
	@Autowired
	private ICostJobLabDtService costJobLabDtService;
	
	@Autowired
	private ICostingJobMtService costingJobMtService; 
	
	@Autowired
	private ICostingJobDtService costingJobDtService; 
	
	@Autowired
	private IInwardTypeService inwardTypeService;
	
	
	
	
	
	//----------------Labour Type----//-->>>>>>>>
	
	
		@RequestMapping("/changeLabourJobType/listing")
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
		@RequestMapping(value = "/changeLabour/JobRate/setRate", method = RequestMethod.POST)
		public String addEditLabour(@Valid @ModelAttribute("costLabJobDt") CostJobLabDt costJobLabDt,
				BindingResult result, 
				@RequestParam(value = "labJobIdPk") String labIdPk,
				@RequestParam(value = "labJobRate") String labRate,
				@RequestParam(value = "mtIdCostJob") Integer costJobMtId,
				RedirectAttributes redirectAttributes, Principal principal) {

			String retVal = "-1";

			if (result.hasErrors()) {
				return "error";
			}
			
			
			String labIdpkArray[] = labIdPk.split(",");
			String labRateArray[] = labRate.split(",");
			
			if(labIdpkArray.length <= 0){
				return retVal = "-2";
			}
			
			
			CostingJobMt costingJobMt = costingJobMtService.findOne(costJobMtId);
			
			List<CostJobLabDt> costJobLabDts = costJobLabDtService.findByCostingJobMtAndDeactive(costingJobMt, false);
			
			
			for(int i=0;i<labIdpkArray.length;i++){
				
				LabourType labourType = labourTypeService.findOne(Integer.parseInt(labIdpkArray[i]));
				
				for(CostJobLabDt costJobLabDtUpdate:costJobLabDts){
					
					if(costJobLabDtUpdate.getLabourType().getName().equalsIgnoreCase(labourType.getName())){
							
						if(costJobLabDtUpdate.getrLock() == false){
							costJobLabDtUpdate.setLabourRate(Double.parseDouble(labRateArray[i]));
							costJobLabDtUpdate.setModiBy(principal.getName());
							costJobLabDtUpdate.setModiDate(new java.util.Date());
							costJobLabDtService.save(costJobLabDtUpdate);
						}
					}
					
				}
				
			}
			
			
			
			return retVal;
			
			
		}
	
	

}
