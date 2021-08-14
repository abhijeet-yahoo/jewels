package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DecimalFormat;
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
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompInwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostJobCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingJobDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingJobMt;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IComponentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompInwardDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostJobCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingJobDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingJobMtService;


@RequestMapping("/manufacturing/transactions")
@Controller
public class CostJobCompDtController {

	
	@Autowired
	private ICostJobCompDtService costJobCompDtService;
	
	@Autowired
	private ICostingJobMtService costingJobMtService;
	
	@Autowired
	private ICostingJobDtService costingJobDtService;
	
	@Autowired
	private IComponentService componentService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private ICompInwardDtService compInwardDtService;
	
	
	
	@RequestMapping("/costJobCompDt/listing")
	@ResponseBody
	public String costCompListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "costJobDtId", required = false) Integer costJobDtId) {

		StringBuilder sb = new StringBuilder();
		
		
		
		sb.append("{\"total\":").append(costJobCompDtService.count())
		.append(",\"rows\": [");
		
		CostingJobDt costingJobDt = costingJobDtService.findOne(costJobDtId);
		List<CostJobCompDt> costJobCompDts = costJobCompDtService.findByCostingJobDtAndDeactive(costingJobDt, false);
		
		
		if(costJobCompDts.size() > 0){
			for(CostJobCompDt costJobCompDt:costJobCompDts){
				
			sb.append("{\"id\":\"")
				.append(costJobCompDt.getId())
				.append("\",\"compName\":\"")
				.append((costJobCompDt.getComponent() != null ? costJobCompDt.getComponent().getName() : ""))
				.append("\",\"kt\":\"")
				.append((costJobCompDt.getPurity() != null ? costJobCompDt.getPurity().getName() : ""))
				.append("\",\"color\":\"")
				.append((costJobCompDt.getColor() != null ? costJobCompDt.getColor().getName() : ""))
				.append("\",\"metalWt\":\"")
				.append((costJobCompDt.getMetalWt() != null ? costJobCompDt.getMetalWt() : ""))
				.append("\",\"rate\":\"")
				.append((costJobCompDt.getCompRate() != null ? costJobCompDt.getCompRate() : ""))
				.append("\",\"value\":\"")
				.append((costJobCompDt.getCompValue() != null ? costJobCompDt.getCompValue() : ""))
				.append("\",\"rLock\":\"")
				.append((costJobCompDt.getrLock() == null ? "": (costJobCompDt.getrLock() ? "Lock": "Unlock"))) //1 = lock & 0 = unlock
				.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doCompJobDtLockUnLock(")
				.append(costJobCompDt.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				.append("\",\"action1\":\"")
				.append("<a href='javascript:editCostJobCompDt(")
				.append(costJobCompDt.getId())
				.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a  href='javascript:deleteCostJobCompDt(event,")
				.append(costJobCompDt.getId())
				.append(");' class='btn btn-xs btn-danger triggerRemove")
				.append(costJobCompDt.getId())
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
	@RequestMapping(value = "/costJobCompDt/add" , method = RequestMethod.POST)
	public String addEdit(@Valid @ModelAttribute("costJobCompDt") CostJobCompDt costJobCompDt,
			BindingResult result,
			@RequestParam(value = "id") Integer id,
			@RequestParam(value = "forCompCostJobMtId") Integer costJobMtId,
			@RequestParam(value = "forCompCostJobDtId") Integer costJobDtId,
			RedirectAttributes redirectAttributes, Principal principal){
		
		
		String action = "added";
		String retVal = "-1";

		if (result.hasErrors()) {
			return "xyz";
		}
		
		CostingJobMt costingJobMt = costingJobMtService.findOne(costJobMtId);
		CostingJobDt costingJobDt = costingJobDtService.findOne(costJobDtId);
		
		
		if (id == null || id.equals("") || (id != null && id == 0)) {
			
			costJobCompDt.setCostingJobMt(costingJobMt);
			costJobCompDt.setCostingJobDt(costingJobDt);
			costJobCompDt.setItemNo(costingJobDt.getItemNo());
			costJobCompDt.setPurityConv(costingJobDt.getPurityConv());
			costJobCompDt.setBagMt(costingJobDt.getBagMt());
			costJobCompDt.setOrderDt(costingJobDt.getOrderDt());
			costJobCompDt.setClientPurityConv(costingJobDt.getClientPurityConv() != null ? costingJobDt.getClientPurityConv() : 0.0);
			
			costJobCompDt.setCreatedBy(principal.getName());
			costJobCompDt.setCreatedDate(new java.util.Date());
		}else{
			costJobCompDt.setCostingJobMt(costingJobMt);
			costJobCompDt.setCostingJobDt(costingJobDt);
			costJobCompDt.setItemNo(costingJobDt.getItemNo());
			costJobCompDt.setPurityConv(costingJobDt.getPurityConv());
			costJobCompDt.setBagMt(costingJobDt.getBagMt());
			costJobCompDt.setOrderDt(costingJobDt.getOrderDt());
			costJobCompDt.setClientPurityConv(costingJobDt.getClientPurityConv() != null ? costingJobDt.getClientPurityConv() : 0.0);
			
			costJobCompDt.setId(id);
			costJobCompDt.setModiBy(principal.getName());
			costJobCompDt.setModiDate(new java.util.Date());
			action = "updated";
			retVal = "-2";
		}
		
		
		costJobCompDtService.save(costJobCompDt);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);
		
		Party party = partyService.findByDefaultFlag(true);
		String tempApplyRateVal = costingJobDtService.applyRate(costingJobDt, party);
		String tempUpdateFobVal = costingJobDtService.updateFob(costingJobDt);
		
		return retVal+"_"+tempApplyRateVal+"_"+tempUpdateFobVal;
		
	}
	
	
	
	@ResponseBody
	@RequestMapping("/costJobCompDt/lockUnlock")
	public String lockUnlockCostCompDt(
			@RequestParam(value="compJobDtId")Integer compJobDtId){
		
		String retVal = "-1";
		CostJobCompDt costJobCompDt = costJobCompDtService.findOne(compJobDtId);
		
			if(costJobCompDt.getrLock() == true){
				costJobCompDt.setrLock(false);
			}else{
				costJobCompDt.setrLock(true);
			}
			
		costJobCompDtService.save(costJobCompDt);
		
		return retVal;
	}
	
	
	
	
	@RequestMapping("/costJobCompDt/validationEdit")
	@ResponseBody
	public String validation(
			@RequestParam(value = "compJobId")Integer compJobId){
		
		String retVal = "";
		
		CostJobCompDt costJobCompDt = costJobCompDtService.findOne(compJobId);
	
		if(costJobCompDt.getrLock() == true){
			retVal = "-1";
		}
		
		return retVal;
	}
	
	
	
	
	
	@RequestMapping("/costJobCompDt/edit/{id}")
	public String editCostCompDt(@PathVariable int id,Model model){
		CostJobCompDt costJobCompDt = costJobCompDtService.findOne(id);
		model.addAttribute("costJobCompDt",costJobCompDt);
		model.addAttribute("componentMap", componentService.getComponentList());
		model.addAttribute("purityMap", purityService.getPurityList());
		model.addAttribute("colorMap", colorService.getColorList());
		
		return "costJobCompDt/add";
	}
	
	
	@ResponseBody
	@RequestMapping("/costJobCompDt/delete/{id}")
	public String delete(@PathVariable int id){
		
		String retVal = "-1";
		
		CostJobCompDt costJobCompDt = costJobCompDtService.findOne(id);
		
			costJobCompDtService.delete(id);
			Party party = partyService.findByDefaultFlag(true);
			String vApplyRate = costingJobDtService.applyRate(costJobCompDt.getCostingJobDt(), party);
			String vUpdateFob = costingJobDtService.updateFob(costJobCompDt.getCostingJobDt());
			retVal = retVal+"_"+vApplyRate+"_"+vUpdateFob;
		
		
		return retVal;
	}
	
	
/*	@ResponseBody
	@RequestMapping("/costJobCompDt/LockUnlockAll")
	public String lockUnlockAll(
			@RequestParam(value="mtId")Integer mtId,
			@RequestParam(value="status")Integer status){
		
		String retVal = "-1";
		CostingJobMt costingJobMt = costingJobMtService.findOne(mtId);
		List<CostJobCompDt> costJobCompDts = costJobCompDtService.findByCostingJobMtAndDeactive(costingJobMt, false);
		Boolean setValue ;
		
		if(status == 1){
			setValue = true;
		}else{
			setValue = false;
		}
		
		for(CostJobCompDt costJobCompDt:costJobCompDts){
			costJobCompDt.setrLock(setValue);
			costJobCompDtService.save(costJobCompDt);
		}
		
		return retVal;
	}*/
	
	
	//---------applying the finding Rate---//--------//
	
	@ResponseBody
	@RequestMapping(value="job/finding/setRate" , method = RequestMethod.POST)
	public String applyFindingRate(
			@ModelAttribute("costJobCompDt") CostJobCompDt costJobCompDt,
			@RequestParam(value="compInwDtPk") String compInwPks,
			@RequestParam(value="vRate") String vRate,
			@RequestParam(value="costingJobMtId") Integer costingJobMtId,
			Principal principal){
		
		String retVal = "1";
		
		
		if(compInwPks.length() > 0){
			
			CostingJobMt costingJobMt = costingJobMtService.findOne(costingJobMtId);
			
			List<CostingJobDt> costingJobDts = costingJobDtService.findByCostingJobMtAndDeactive(costingJobMt, false);
			
			//List<CostCompDt> costCompDts = costCompDtService.findByCostingDtAndDeactive(costingDt, false);
			
			String tempCostInwDtId[] = compInwPks.split(",");
			String tempRate[]		  = vRate.split(",");
			
			DecimalFormat df = new DecimalFormat("#.##");
			
			
			for(int i=0;i<tempCostInwDtId.length;i++){
				
				CompInwardDt compInwardDtfirstLoop = compInwardDtService.findOne(Integer.parseInt(tempCostInwDtId[i]));
				
					for(int j=tempCostInwDtId.length-1; j>i; j--){
						
						CompInwardDt compInwardDtReverseLoop = compInwardDtService.findOne(Integer.parseInt(tempCostInwDtId[j]));
						
						if(compInwardDtfirstLoop.getComponent().getName().equals(compInwardDtReverseLoop.getComponent().getName())  &&
								compInwardDtfirstLoop.getPurity().getName().equals(compInwardDtReverseLoop.getPurity().getName()) &&
								compInwardDtfirstLoop.getColor().getName().equals(compInwardDtReverseLoop.getColor().getName())){
							
							return retVal = "-1";	
							
						}
						
					}
				
				
				
			}
			
			
			for(int i=0; i<tempCostInwDtId.length;i++){
				
				CompInwardDt compInwardDt = compInwardDtService.findOne(Integer.parseInt(tempCostInwDtId[i]));
		
				for(CostingJobDt costingJobDt:costingJobDts){
				
					List<CostJobCompDt> costJobCompDts = costJobCompDtService.findByCostingJobDtAndDeactive(costingJobDt, false);
					
					for(CostJobCompDt costCompDtt:costJobCompDts){
						if(compInwardDt.getComponent().getName().equals(costCompDtt.getComponent().getName())  &&
								compInwardDt.getPurity().getName().equals(costCompDtt.getPurity().getName()) &&
								compInwardDt.getColor().getName().equals(costCompDtt.getColor().getName())){
							
							costCompDtt.setCompRate(Double.parseDouble((tempRate[i])));
							costCompDtt.setCompValue(Double.parseDouble(df.format(costCompDtt.getMetalWt()*Double.parseDouble(tempRate[i]))));
							costCompDtt.setModiBy(principal.getName());
							costCompDtt.setModiDate(new java.util.Date());
							costJobCompDtService.save(costCompDtt);
							
						}
					}
					
				}
				
			}
			
			
			
			
			
		}else{
			
			retVal = "2";
			
		}// ending the main if
		
		
		return retVal;
		
	}
	
	
	
}
