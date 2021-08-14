package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILabourTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostLabDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingMtService;


@RequestMapping("/manufacturing/transactions")
@Controller
public class CostLabDtController {

	
	@Autowired
	private ICostLabDtService costLabDtService;
	
	@Autowired
	private ICostingMtService costingMtService;
	
	@Autowired
	private ICostingDtService costingDtService;
	
	@Autowired
	private ILabourTypeService labourTypeService;
	
	@Autowired
	private IMetalService metalService;
	
	
	@RequestMapping("/costLabDt/listing")
	@ResponseBody
	public String costLabDtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "costDtId", required = false) Integer costDtId) {

		StringBuilder sb = new StringBuilder();
		
		
		
		sb.append("{\"total\":").append(costLabDtService.count())
		.append(",\"rows\": [");
		
		CostingDt costingDt = costingDtService.findOne(costDtId);
		List<CostLabDt> costLabDts = costLabDtService.findByCostingDtAndDeactive(costingDt, false);
		
		
		if(costLabDts.size() > 0){
			for(CostLabDt costLabDt:costLabDts){
				
			sb.append("{\"id\":\"")
				.append(costLabDt.getId())
				.append("\",\"metal\":\"")
				.append((costLabDt.getMetal() != null ? costLabDt.getMetal().getName() : ""))
				.append("\",\"labourType\":\"")
				.append((costLabDt.getLabourType() != null ? costLabDt.getLabourType().getName() : ""))
				.append("\",\"rate\":\"")
				.append((costLabDt.getLabourRate() != null ? costLabDt.getLabourRate() : ""))
				.append("\",\"value\":\"")
				.append((costLabDt.getLabourValue() != null ? costLabDt.getLabourValue() : ""))
				.append("\",\"rLock\":\"")
				.append(costLabDt.getrLock()) //1 = lock & 0 = unlock
				.append("\",\"perPcs\":\"")
				.append(costLabDt.getPcsWise())
				.append("\",\"perGram\":\"")
				.append(costLabDt.getGramWise())
				.append("\",\"percent\":\"")
				.append(costLabDt.getPercentWise())
				.append("\",\"perCaratRate\":\"")
				.append(costLabDt.getPerCaratRate())
				.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doLabDtLockUnLock(")
				.append(costLabDt.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				.append("\",\"action1\":\"")
				.append("<a href='javascript:editCostLabDt(")
				.append(costLabDt.getId())
				.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a  href='javascript:deleteCostLabDt(event,")
				.append(costLabDt.getId())
				.append(");' class='btn btn-xs btn-danger triggerRemove")
				.append(costLabDt.getId())
				.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
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
	@RequestMapping(value="/costLabDt/add", method = RequestMethod.POST)
	public String addEditCostLabDt(@Valid @ModelAttribute("costLabDt") CostLabDt costLabDt,
			BindingResult bindingResult,
			@RequestParam(value="id")Integer id,
			@RequestParam(value="forLabCostMtId")Integer costMtId,
			@RequestParam(value="forLabCostDtId")Integer costDtId,
			RedirectAttributes redirectAttributes,Principal principal){
		
		
		String retVal = "-1";
		
		if(bindingResult.hasErrors()){
			return "xyz";
		}
		
		try {
			
			retVal=costLabDtService.costLabDtSave(costLabDt, id, costMtId, costDtId, principal);
			
		}  catch (Exception e) {
			e.printStackTrace();
			retVal = "error";
		}
		
		
		
		return retVal;

		
	}
	
	
	@ResponseBody
	@RequestMapping("/costLabDt/lockUnlock")
	public String lockUnlockLabCompDt(
			@RequestParam(value="labDtId")Integer labDtId){
		
		String retVal = "-1";
		CostLabDt costLabDt = costLabDtService.findOne(labDtId);
		
		
		if(costLabDt.getCostingMt().getExpClose() == true){
			retVal = "-2";
		}else{
		
			if(costLabDt.getrLock() == true){
				costLabDt.setrLock(false);
			}else{
				costLabDt.setrLock(true);
			}
		
			costLabDtService.save(costLabDt);
		}
		
		return retVal;
	}
	
	
	
	
	@RequestMapping("/costLabDt/validationEdit")
	@ResponseBody
	public String validation(
			@RequestParam(value = "labId")Integer labId){
		
		String retVal = "";
		
		CostLabDt costLabDt = costLabDtService.findOne(labId);
		
		if(costLabDt.getCostingMt().getExpClose() == true){
			return retVal = "-2";
		}
		
		if(costLabDt.getrLock() == true){
			retVal = "-1";
		}
		return retVal;
	}
	
	
	@RequestMapping("/costLabDt/edit/{id}")
	public String editCosttLabDt(@PathVariable int id,Model model){
		CostLabDt costLabDt = costLabDtService.findOne(id);
		model.addAttribute("costLabDt",costLabDt);
		model.addAttribute("labourTypeMap",labourTypeService.getLabourTypeList());
		model.addAttribute("metalMap", metalService.getMetalList());
		
		return "costLabDt/add";
	}
	
	

	
	@ResponseBody
	@RequestMapping("/costLabDt/delete/{id}")
	public String delete(@PathVariable int id, Principal principal){
		
		String retVal = "-1";
		
		CostLabDt costLabDt = costLabDtService.findOne(id);
		
		if(costLabDt.getCostingMt().getExpClose() == true){
			retVal = "-2";
		}else{
			costLabDtService.delete(id);
			costingDtService.updateFob(costLabDt.getCostingDt());
			
		}
		
		return retVal;
	}
	
	
	@ResponseBody
	@RequestMapping("/costLabDt/LockUnlockAll")
	public String lockUnlockAll(
			@RequestParam(value="mtId")Integer mtId,
			@RequestParam(value="status")Integer status){
		
		String retVal = "-1";
		CostingMt costingMt = costingMtService.findOne(mtId);
		List<CostLabDt> costLabDts = costLabDtService.findByCostingMtAndDeactive(costingMt, false);
		Boolean setValue ;
		
		if(status == 1){
			setValue = true;
		}else{
			setValue = false;
		}
		
		for(CostLabDt costLabDt:costLabDts){
			costLabDt.setrLock(setValue);
			costLabDtService.save(costLabDt);
		}
		
		return retVal;
	}
	
	
	
	@ResponseBody
	@RequestMapping("/labourAsPerMaster/save")
	public String updateLabourAsMaster(
			@RequestParam(value="mtId") Integer costMtId,Principal principal){
		
		@SuppressWarnings("unused")
		Integer res = costLabDtService.labAsPerMaster(costMtId);

		return "-1";
		
	}
	
	
	
	
}
