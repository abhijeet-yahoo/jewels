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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostJobLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingJobDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingJobMt;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILabourChargeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILabourTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostJobLabDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingJobDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingJobMtService;


@RequestMapping("/manufacturing/transactions")
@Controller
public class CostJobLabDtController {

	@Autowired
	private ICostJobLabDtService costJobLabDtService;
	
	@Autowired
	private ICostingJobMtService costingJobMtService; 
	
	@Autowired
	private ICostingJobDtService costingJobDtService; 
	
	@Autowired
	private ILabourTypeService labourTypeService;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private ILabourChargeService labourChargeService;
	
	
	@RequestMapping("/costJobLabDt/listing")
	@ResponseBody
	public String costJobLabDtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "costJobDtId", required = false) Integer costJobDtId) {

		StringBuilder sb = new StringBuilder();
		
		
		
		sb.append("{\"total\":").append(costJobLabDtService.count())
		.append(",\"rows\": [");
		
		CostingJobDt costingJobDt = costingJobDtService.findOne(costJobDtId);
		List<CostJobLabDt> costJobLabDts = costJobLabDtService.findByCostingJobDtAndDeactive(costingJobDt, false);
		
		
		if(costJobLabDts.size() > 0){
			for(CostJobLabDt costJobLabDt:costJobLabDts){
				
			sb.append("{\"id\":\"")
				.append(costJobLabDt.getId())
				.append("\",\"labourType\":\"")
				.append((costJobLabDt.getLabourType() != null ? costJobLabDt.getLabourType().getName() : ""))
				.append("\",\"rate\":\"")
				.append((costJobLabDt.getLabourRate() != null ? costJobLabDt.getLabourRate() : ""))
				.append("\",\"value\":\"")
				.append((costJobLabDt.getLabourValue() != null ? costJobLabDt.getLabourValue() : ""))
				.append("\",\"rLock\":\"")
				.append((costJobLabDt.getrLock() == null ? "": (costJobLabDt.getrLock() ? "Lock": "Unlock"))) //1 = lock & 0 = unlock
				.append("\",\"perPcs\":\"")
				.append((costJobLabDt.getPcsWise() != null ? costJobLabDt.getPcsWise() : ""))
				.append("\",\"perGram\":\"")
				.append((costJobLabDt.getGramWise() != null ? costJobLabDt.getGramWise() : ""))
				.append("\",\"percent\":\"")
				.append((costJobLabDt.getPercentWise() != null ? costJobLabDt.getPercentWise() : ""))
				.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doLabJobDtLockUnLock(")
				.append(costJobLabDt.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				.append("\",\"action1\":\"")
				.append("<a href='javascript:editCostJobLabDt(")
				.append(costJobLabDt.getId())
				.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a  href='javascript:deleteCostJobLabDt(event,")
				.append(costJobLabDt.getId())
				.append(");' class='btn btn-xs btn-danger triggerRemove")
				.append(costJobLabDt.getId())
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
	@RequestMapping(value="/costJobLabDt/add", method = RequestMethod.POST)
	public String addEditCostJobLabDt(@Valid @ModelAttribute("costJobLabDt") CostJobLabDt costJobLabDt,
			BindingResult bindingResult,
			@RequestParam(value="id")Integer id,
			@RequestParam(value="forLabCostJobMtId")Integer costJobMtId,
			@RequestParam(value="forLabCostJobDtId")Integer costJobDtId,
			RedirectAttributes redirectAttributes,Principal principal){
		
		String action = "added";
		String retVal = "-1";
		
		if(bindingResult.hasErrors()){
			return "xyz";
		}
		
		CostingJobMt costingJobMt = costingJobMtService.findOne(costJobMtId);
		CostingJobDt costingJobDt = costingJobDtService.findOne(costJobDtId);
		
		
		if(costJobLabDt.getPcsWise() == true && costJobLabDt.getGramWise() == true ){
			return retVal = "-11";
		}
		
		if(costJobLabDt.getPcsWise() == true && costJobLabDt.getPercentWise() == true ){
			return retVal = "-11";
		}
		
		if(costJobLabDt.getGramWise() == true && costJobLabDt.getPercentWise() == true){
			return retVal = "-11";
		}
		
		
		if (id == null || id.equals("") || (id != null && id == 0)) {
			
			costJobLabDt.setCostingJobMt(costingJobMt);
			costJobLabDt.setCostingJobDt(costingJobDt);
			costJobLabDt.setItemNo(costingJobDt.getItemNo());
			costJobLabDt.setBagmt(costingJobDt.getBagMt());
			
			costJobLabDt.setCreatedBy(principal.getName());
			costJobLabDt.setCreatedDate(new java.util.Date());
			costJobLabDt.setOrderDt(costingJobDt.getOrderDt());
		}else{
			costJobLabDt.setCostingJobMt(costingJobMt);
			costJobLabDt.setCostingJobDt(costingJobDt);
			costJobLabDt.setItemNo(costingJobDt.getItemNo());
			costJobLabDt.setBagmt(costingJobDt.getBagMt());
			costJobLabDt.setOrderDt(costingJobDt.getOrderDt());
			
			costJobLabDt.setId(id);
			costJobLabDt.setModiBy(principal.getName());
			costJobLabDt.setModiDate(new java.util.Date());
			action = "updated";
			retVal = "-2";
		}
		
		
		costJobLabDtService.save(costJobLabDt);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);
		
		if(retVal == "-1" || retVal == "-2"){
			Party party = partyService.findByDefaultFlag(true);
			String tempApplyRate = costingJobDtService.applyRate(costingJobDt, party);
			String tempUpdateFob = costingJobDtService.updateFob(costingJobDt);
			
			retVal = retVal+"_"+tempApplyRate+"_"+tempUpdateFob;
			
		}
		
		
		return retVal;
		

		
	}
	
	
	@ResponseBody
	@RequestMapping("/costJobLabDt/lockUnlock")
	public String lockUnlockLabCompDt(
			@RequestParam(value="labCostDtId")Integer labCostDtId){
		
		String retVal = "-1";
		CostJobLabDt costJobLabDt = costJobLabDtService.findOne(labCostDtId);
	
			if(costJobLabDt.getrLock() == true){
				costJobLabDt.setrLock(false);
			}else{
				costJobLabDt.setrLock(true);
			}
		
			costJobLabDtService.save(costJobLabDt);
		
		
		return retVal;
	}
	
	
	
	
	@RequestMapping("/costJobLabDt/validationEdit")
	@ResponseBody
	public String validation(
			@RequestParam(value = "labCostId")Integer labCostId){
		
		String retVal = "";
		
		CostJobLabDt costJobLabDt = costJobLabDtService.findOne(labCostId);
		
		if(costJobLabDt.getrLock() == true){
			retVal = "-1";
		}
		return retVal;
	}
	
	
	@RequestMapping("/costJobLabDt/edit/{id}")
	public String editCosttLabJobDt(@PathVariable int id,Model model){
		CostJobLabDt costJobLabDt = costJobLabDtService.findOne(id);
		model.addAttribute("costJobLabDt",costJobLabDt);
		model.addAttribute("labourTypeMap",labourTypeService.getLabourTypeList());
		
		return "costJobLabDt/add";
	}
	
	

	
	@ResponseBody
	@RequestMapping("/costJobLabDt/delete/{id}")
	public String delete(@PathVariable int id){
		
		String retVal = "-1";
		
		CostJobLabDt costJobLabDt = costJobLabDtService.findOne(id);
		
			costJobLabDtService.delete(id);
			Party party = partyService.findByDefaultFlag(true);
			String vApplyRate = costingJobDtService.applyRate(costJobLabDt.getCostingJobDt(), party);
			String vUpdateFob = costingJobDtService.updateFob(costJobLabDt.getCostingJobDt());
			retVal = retVal+"_"+vApplyRate+"_"+vUpdateFob;
		
		
		return retVal;
	}
	
	
	@ResponseBody
	@RequestMapping("/labourAsPerMaster/job/save")
	public String updateLabourAsMaster(
			@RequestParam(value="mtId") Integer costJobMtId,Principal principal){
		
		@SuppressWarnings("unused")
		Integer res = costJobLabDtService.labAsPerMaster(costJobMtId);
		return "-1";
	
	
	}
	
	
	

	
	/*
		@ResponseBody
		@RequestMapping("/costJobLabDt/LockUnlockAll")
		public String lockUnlockAll(
				@RequestParam(value="mtId")Integer mtId,
				@RequestParam(value="status")Integer status){
			
			String retVal = "-1";
			CostingJobMt costingJobMt = costingJobMtService.findOne(mtId);
			List<CostJobLabDt> costJobLabDts = costJobLabDtService.findByCostingJobMtAndDeactive(costingJobMt, false);
			Boolean setValue ;
			
			if(status == 1){
				setValue = true;
			}else{
				setValue = false;
			}
			
			for(CostJobLabDt costJobLabDt:costJobLabDts){
				costJobLabDt.setrLock(setValue);
				costJobLabDtService.save(costJobLabDt);
			}
			
			return retVal;
		}
		
		
		
		
			
	}*/
	
	
 }
