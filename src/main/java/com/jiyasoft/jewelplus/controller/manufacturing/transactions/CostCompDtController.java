package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DecimalFormat;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompInwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IComponentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompInwardDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostMetalDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class CostCompDtController {
	
	@Value("${netWtWithComp}")
	private Boolean netWtWithCompFlg;

	@Autowired
	private ICostCompDtService costCompDtService;
	
	@Autowired
	private ICostingMtService costingMtService;
	
	@Autowired
	private ICostingDtService costingDtService;
	
	@Autowired
	private IComponentService componentService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IColorService colorService;
	
	
	@Autowired
	private ICostMetalDtService costMetalDtService;
	
	
	@Autowired
	private ICompInwardDtService compInwardDtService;
	
	
	
	@RequestMapping("/costCompDt/listing")
	@ResponseBody
	public String costCompListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "costDtId", required = false) Integer costDtId) {

		StringBuilder sb = new StringBuilder();
		
		
		
		sb.append("{\"total\":").append(costCompDtService.count())
		.append(",\"rows\": [");
		
		CostingDt costingDt = costingDtService.findOne(costDtId);
		
			
		CostMetalDt costMetalDt =  costMetalDtService.findByCostingDtAndDeactiveAndMainMetal(costingDt,false,true);
		String vMetalKtNm = costMetalDt.getPurity().getName(); 
		
		List<CostCompDt> costCompDts = costCompDtService.findByCostingDtAndDeactive(costingDt, false);
		
		
		if(costCompDts.size() > 0){
			for(CostCompDt costCompDt:costCompDts){
				
			sb.append("{\"id\":\"")
				.append(costCompDt.getId())
				.append("\",\"compName\":\"")
				.append((costCompDt.getComponent() != null ? costCompDt.getComponent().getName() : ""))
				.append("\",\"kt\":\"")
				.append((costCompDt.getPurity() != null ? costCompDt.getPurity().getName() : ""))
				.append("\",\"vMetalKtNm\":\"")
				.append((vMetalKtNm))
				.append("\",\"color\":\"")
				.append((costCompDt.getColor() != null ? costCompDt.getColor().getName() : ""))
				.append("\",\"metalWt\":\"")
				.append((costCompDt.getMetalWt() != null ? costCompDt.getMetalWt() : ""))
				.append("\",\"rate\":\"")
				.append((costCompDt.getCompRate() != null ? costCompDt.getCompRate() : ""))
				.append("\",\"value\":\"")
				.append((costCompDt.getCompValue() != null ? costCompDt.getCompValue() : ""))
				.append("\",\"compPcs\":\"")
				.append((costCompDt.getCompPcs() != null ? costCompDt.getCompPcs() : ""))
				.append("\",\"rLock\":\"")
				.append((costCompDt.getrLock())) //1 = lock & 0 = unlock
				.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doCompDtLockUnLock(")
				.append(costCompDt.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				.append("\",\"action1\":\"")
				.append("<a href='javascript:editCostCompDt(")
				.append(costCompDt.getId())
				.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a  href='javascript:deleteCostCompDt(event,")
				.append(costCompDt.getId())
				.append(");' class='btn btn-xs btn-danger triggerRemove")
				.append(costCompDt.getId())
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
	@RequestMapping(value = "/costCompDt/add" , method = RequestMethod.POST)
	public String addEdit(@Valid @ModelAttribute("costCompDt") CostCompDt costCompDt,
			BindingResult result,
			@RequestParam(value = "id") Integer id,
			@RequestParam(value = "forCompCostMtId") Integer costMtId,
			@RequestParam(value = "forCompCostDtId") Integer costDtId,
			RedirectAttributes redirectAttributes, Principal principal,
			@RequestParam(value="costingType", required=false) String costingType){
		
		
		String action = "added";
		String retVal = "-1";

		if (result.hasErrors()) {
			return "xyz";
		}
		
		CostingMt costingMt = costingMtService.findOne(costMtId);
		CostingDt costingDt = costingDtService.findOne(costDtId);
		
		
		if (id == null || id.equals("") || (id != null && id == 0)) {
			
			costCompDt.setCostingMt(costingMt);
			costCompDt.setCostingDt(costingDt);
			costCompDt.setItemNo(costingDt.getItemNo());
			costCompDt.setPurityConv(costingDt.getPurityConv());
			costCompDt.setBagMt(costingDt.getBagMt());
			costCompDt.setOrderDt(costingDt.getOrderDt());
			costCompDt.setClientPurityConv(costingDt.getClientPurityConv() != null ? costingDt.getClientPurityConv() : 0.0);
			
			costCompDt.setCreatedBy(principal.getName());
			costCompDt.setCreatedDate(new java.util.Date());
		}else{
			costCompDt.setCostingMt(costingMt);
			costCompDt.setCostingDt(costingDt);
			costCompDt.setItemNo(costingDt.getItemNo());
			costCompDt.setPurityConv(costingDt.getPurityConv());
			costCompDt.setBagMt(costingDt.getBagMt());
			costCompDt.setOrderDt(costingDt.getOrderDt());
			costCompDt.setClientPurityConv(costingDt.getClientPurityConv() != null ? costingDt.getClientPurityConv() : 0.0);
			
			costCompDt.setId(id);
			costCompDt.setModiBy(principal.getName());
			costCompDt.setModiDate(new java.util.Date());
			action = "updated";
			retVal = "-2";
		}
		
		
		costCompDtService.save(costCompDt);
		
		costingDtService.updateNetWt(costingDt, netWtWithCompFlg);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);
		
		if(costingType != null && costingType.equalsIgnoreCase("merge")){
				
		}else{
			costingDtService.updateFob(costingDt);
		}
		
	
		
		return retVal;
		
	}
	
	
	
	@ResponseBody
	@RequestMapping("/costCompDt/lockUnlock")
	public String lockUnlockCostCompDt(
			@RequestParam(value="compDtId")Integer compDtId){
		
		String retVal = "-1";
		CostCompDt costCompDt = costCompDtService.findOne(compDtId);
		
		
			if(costCompDt.getCostingMt().getExpClose() == true){
				return retVal = "-2";
			}
		
		
			if(costCompDt.getrLock() == true){
				costCompDt.setrLock(false);
			}else{
				costCompDt.setrLock(true);
			}
			
		costCompDtService.save(costCompDt);
		
		return retVal;
	}
	
	
	
	
	@RequestMapping("/costCompDt/validationEdit")
	@ResponseBody
	public String validation(
			@RequestParam(value = "compId")Integer compId){
		
		String retVal = "";
		
		CostCompDt costCompDt = costCompDtService.findOne(compId);
		
		if(costCompDt.getCostingMt().getExpClose() == true){
			return retVal = "-2";
		}
	
		if(costCompDt.getrLock() == true){
			retVal = "-1";
		}
		
		return retVal;
	}
	
	
	
	
	
	@RequestMapping("/costCompDt/edit/{id}")
	public String editCostCompDt(@PathVariable int id,Model model,
			@RequestParam(value="costingType", required=false) String costingType){
		CostCompDt costCompDt = costCompDtService.findOne(id);
		model.addAttribute("costCompDt",costCompDt);
		model.addAttribute("componentMap", componentService.getComponentList());
		model.addAttribute("purityMap", purityService.getPurityList());
		model.addAttribute("colorMap", colorService.getColorList());
		if(costingType != null && costingType.equalsIgnoreCase("merge")){
			model.addAttribute("costingType", "merge");
		}
		
		return "costCompDt/add";
	}
	
	
	@ResponseBody
	@RequestMapping("/costCompDt/delete/{id}")
	public String delete(@PathVariable int id, Principal principal){
		
		String retVal = "-1";
		
		CostCompDt costCompDt = costCompDtService.findOne(id);
		
		if(costCompDt.getCostingMt().getExpClose() == true){
			retVal = "-2";
		}else{
			costCompDtService.delete(id);
			costingDtService.updateNetWt(costCompDt.getCostingDt(), netWtWithCompFlg);
			 costingDtService.updateFob(costCompDt.getCostingDt());
			
		}
		
		return retVal;
	}
	
	
	@ResponseBody
	@RequestMapping("/costCompDt/LockUnlockAll")
	public String lockUnlockAll(
			@RequestParam(value="mtId")Integer mtId,
			@RequestParam(value="status")Integer status){
		
		String retVal = "-1";
		CostingMt costingMt = costingMtService.findOne(mtId);
		List<CostCompDt> costCompDts = costCompDtService.findByCostingMtAndDeactive(costingMt, false);
		Boolean setValue ;
		
		if(status == 1){
			setValue = true;
		}else{
			setValue = false;
		}
		
		for(CostCompDt costCompDt:costCompDts){
			costCompDt.setrLock(setValue);
			costCompDtService.save(costCompDt);
		}
		
		return retVal;
	}
	
	
	//---------applying the finding Rate---//--------//
	
	@ResponseBody
	@RequestMapping(value="/finding/setRate" , method = RequestMethod.POST)
	public String applyFindingRate(
			@ModelAttribute("costCompDt") CostCompDt costCompDt,
			@RequestParam(value="compInwDtPk") String compInwPks,
			@RequestParam(value="vRate") String vRate,
			@RequestParam(value="costingMtId") Integer costingMtId,
			Principal principal){
		
		String retVal = "1";
		
		
		if(compInwPks.length() > 0){
			
			//CostingDt costingDt = costingDtService.findOne(costingDtId);
			
			CostingMt costingMt = costingMtService.findOne(costingMtId);
			
			//List<CostCompDt> costCompDts = costCompDtService.findByCostingDtAndDeactive(costingDt, false);
			
			List<CostingDt> costingDts = costingDtService.findByCostingMtAndDeactive(costingMt, false);
			
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
		
				for(CostingDt costingDt:costingDts){
				
					List<CostCompDt> costCompDts = costCompDtService.findByCostingDtAndDeactive(costingDt, false);
					
					for(CostCompDt costCompDtt:costCompDts){
						if(compInwardDt.getComponent().getName().equals(costCompDtt.getComponent().getName())  &&
								compInwardDt.getPurity().getName().equals(costCompDtt.getPurity().getName()) &&
								compInwardDt.getColor().getName().equals(costCompDtt.getColor().getName())){
							
							costCompDtt.setCompRate(Double.parseDouble((tempRate[i])));
							costCompDtt.setCompValue(Double.parseDouble(df.format(costCompDtt.getMetalWt()*Double.parseDouble(tempRate[i]))));
							costCompDtt.setModiBy(principal.getName());
							costCompDtt.setModiDate(new java.util.Date());
							costCompDtService.save(costCompDtt);
							
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
