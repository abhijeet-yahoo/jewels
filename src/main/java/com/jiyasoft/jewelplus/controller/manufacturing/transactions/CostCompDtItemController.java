package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.json.JSONObject;
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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Component;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;


import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostCompDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IComponentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostCompDtItemService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingDtItemService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class CostCompDtItemController {
	
	@Value("${netWtWithComp}")
	private Boolean netWtWithCompFlg;
	
	@Autowired
	private ICostCompDtItemService costCompDtItemService;
	

	
	@Autowired
	private ICostingMtService costingMtService;
	
	@Autowired
	private ICostingDtItemService costingDtItemService;
	
	@Autowired
	private IComponentService componentService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IColorService colorService;
	
	
	@RequestMapping("/costCompDtItem/listing")
	@ResponseBody
	public String costCompListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "costDtId", required = false) Integer costDtId) {

		StringBuilder sb = new StringBuilder();
		
		
		
		
		
		CostingDtItem costingDtItem = costingDtItemService.findOne(costDtId);
		List<CostCompDtItem> costCompDtItems = costCompDtItemService.findByCostingDtItemAndDeactive(costingDtItem, false);
		sb.append("{\"total\":").append(costCompDtItems.size()).append(",\"rows\": [");
		
		if(costCompDtItems.size() > 0){
			for(CostCompDtItem costCompDtItem:costCompDtItems){
				
			sb.append("{\"id\":\"")
				.append(costCompDtItem.getId())
				.append("\",\"compName\":\"")
				.append((costCompDtItem.getComponent() != null ? costCompDtItem.getComponent().getName() : ""))
				.append("\",\"kt\":\"")
				.append((costCompDtItem.getPurity() != null ? costCompDtItem.getPurity().getName() : ""))
				.append("\",\"color\":\"")
				.append((costCompDtItem.getColor() != null ? costCompDtItem.getColor().getName() : ""))
				.append("\",\"metalWt\":\"")
				.append((costCompDtItem.getMetalWt() != null ? costCompDtItem.getMetalWt() : ""))
				.append("\",\"rate\":\"")
				.append((costCompDtItem.getCompRate() != null ? costCompDtItem.getCompRate() : ""))
				.append("\",\"value\":\"")
				.append((costCompDtItem.getCompValue() != null ? costCompDtItem.getCompValue() : ""))
				.append("\",\"compPcs\":\"")
				.append((costCompDtItem.getCompPcs() != null ? costCompDtItem.getCompPcs() : ""))
				.append("\",\"metalRate\":\"")
				.append((costCompDtItem.getMetalRate() != null ? costCompDtItem.getMetalRate() : ""))
				.append("\",\"perGramMetalRate\":\"")
				.append((costCompDtItem.getPerGramMetalRate() != null ? costCompDtItem.getPerGramMetalRate() : ""))
				.append("\",\"metalValue\":\"")
				.append((costCompDtItem.getMetalValue() != null ? costCompDtItem.getMetalValue() : ""))
				.append("\",\"rLock\":\"")
				.append((costCompDtItem.getrLock())) //1 = lock & 0 = unlock
				.append("\",\"perGramRate\":\"")
				.append((costCompDtItem.getPerGramRate()))
				.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doCompDtLockUnLockItem(")
				.append(costCompDtItem.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				.append("\",\"action1\":\"")
				.append("<a href='javascript:editCostCompDtItem(")
				.append(costCompDtItem.getId())
				.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a  href='javascript:deleteCostCompDtItem(event,")
				.append(costCompDtItem.getId())
				.append(");' class='btn btn-xs btn-danger triggerRemove")
				.append(costCompDtItem.getId())
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
	@RequestMapping(value = "/costCompDtItem/add" , method = RequestMethod.POST)
	public String addEdit(@Valid @ModelAttribute("costCompDtItem") CostCompDtItem costCompDtItem,
			BindingResult result,
			@RequestParam(value = "id") Integer id,
			@RequestParam(value = "forCompCostMtItemId") Integer costMtId,
			@RequestParam(value = "forCompCostDtItemId") Integer costDtId,
			RedirectAttributes redirectAttributes, Principal principal){
		
		
		
		String retVal = "-1";

		if (result.hasErrors()) {
			return "error";
		}
		
		CostingMt costingMt = costingMtService.findOne(costMtId);
		CostingDtItem costingDtItem = costingDtItemService.findOne(costDtId);
		
		if (id == null || id.equals("") || (id != null && id == 0)){
			
			
		costCompDtItem.setCostingMt(costingMt);
		costCompDtItem.setCostingDtItem(costingDtItem);
		costCompDtItem.setItemNo(costingDtItem.getItemNo());
		costCompDtItem.setPurityConv(costingDtItem.getPurityConv());
		costCompDtItem.setClientPurityConv(costingDtItem.getClientPurityConv() != null ? costingDtItem.getClientPurityConv() : 0.0);
		
		if(costCompDtItem.getPerGramRate().equals(true)){
			costCompDtItem.setPerPcRate(false);
		}else{
			costCompDtItem.setPerPcRate(true);
		}
				
		costCompDtItemService.save(costCompDtItem);
		
		costingDtItem.setGrossWt(costingDtItem.getGrossWt()+costCompDtItem.getMetalWt());
		
		costingDtItemService.save(costingDtItem);
		costingDtItemService.updateNetWt(costingDtItem,netWtWithCompFlg);
		
		
		
		
		retVal = "-2";
				
				
			}else{
				
			
				
				
				
				CostCompDtItem costCompDtItemOld=costCompDtItemService.findOne(id);
				
				Double vDiff =Math.round((costCompDtItem.getMetalWt()-costCompDtItemOld.getMetalWt())*1000.0)/1000.0 ;
				
				costCompDtItem.setCostingMt(costingMt);
				costCompDtItem.setCostingDtItem(costingDtItem);
				costCompDtItem.setItemNo(costingDtItem.getItemNo());
				costCompDtItem.setPurityConv(costingDtItem.getPurityConv());
				costCompDtItem.setClientPurityConv(costingDtItem.getClientPurityConv() != null ? costingDtItem.getClientPurityConv() : 0.0);
					
				costCompDtItem.setId(id);
				costCompDtItem.setModiBy(principal.getName());
				costCompDtItem.setModiDate(new java.util.Date());
				
				if(costCompDtItem.getPerGramRate().equals(true)){
					costCompDtItem.setPerPcRate(false);
				}else{
					costCompDtItem.setPerPcRate(true);
				}
									
				costCompDtItemService.save(costCompDtItem);
				
				costingDtItem.setGrossWt(costingDtItem.getGrossWt()+vDiff);
				
				costingDtItemService.save(costingDtItem);
				costingDtItemService.updateNetWt(costingDtItem,netWtWithCompFlg);
				
				
				
				
				retVal = "-2";
				
			}
		
		
		
		
		
		
		costingDtItemService.updateItemFob(costCompDtItem.getCostingDtItem(),netWtWithCompFlg);
		
		return retVal;
		
	}
	
	
	@RequestMapping("/costCompDtItem/validationEdit")
	@ResponseBody
	public String validation(
			@RequestParam(value = "compId")Integer compId){
		
		String retVal = "";
		
		CostCompDtItem costCompDtItem = costCompDtItemService.findOne(compId);
		
		if(costCompDtItem.getCostingMt().getExpClose() == true){
			return retVal = "-2";
		}
	
		if(costCompDtItem.getrLock() == true){
			retVal = "-1";
		}
		
		return retVal;
	}
	
	
	@RequestMapping("/costCompDtItem/edit/{id}")
	public String editCostCompDt(@PathVariable int id,Model model,@RequestParam(value="costMtId") Integer costMtId){
		
		if(id>0){
			
			CostCompDtItem costCompDtItem = costCompDtItemService.findOne(id);
			model.addAttribute("costCompDtItem",costCompDtItem);
			model.addAttribute("costMtId",costMtId);
			
			
		}else{
			
			CostCompDtItem costCompDtItem = new CostCompDtItem();
			
			model.addAttribute("costCompDtItem",costCompDtItem);
			model.addAttribute("costMtId",costMtId);
			
		}
		
		
		model.addAttribute("componentMap", componentService.getComponentList());
		model.addAttribute("purityMap", purityService.getPurityList());
		model.addAttribute("colorMap", colorService.getColorList());
		
		return "costCompDtItem/add";
	}
	
	
	@ResponseBody
	@RequestMapping("/costCompDtItem/delete/{id}")
	public String delete(@PathVariable int id, Principal principal){
		
		String retVal = "-1";
		
		CostCompDtItem costCompDtItem = costCompDtItemService.findOne(id);
		
		if(costCompDtItem.getCostingMt().getExpClose() == true){
			retVal = "-2";
		}else{
			costCompDtItemService.delete(id);
			costingDtItemService.updateItemFob(costCompDtItem.getCostingDtItem(),netWtWithCompFlg);
			
		}
		
		return retVal;
	}
	
		
	@ResponseBody
	@RequestMapping("/costCompDtItem/lockUnlock")
	public String lockUnlockCostCompDt(
			@RequestParam(value="compDtId")Integer compDtId){
		
		String retVal = "-1";
		CostCompDtItem costCompDtItem = costCompDtItemService.findOne(compDtId);
		
		
			if(costCompDtItem.getCostingMt().getExpClose() == true){
				return retVal = "-2";
			}
		
		
			if(costCompDtItem.getrLock() == true){
				costCompDtItem.setrLock(false);
			}else{
				costCompDtItem.setrLock(true);
			}
			
			costCompDtItemService.save(costCompDtItem);
		
		return retVal;
	}
	
	
	
	@ResponseBody
	@RequestMapping("/costCompDtItem/compRateFromMaster")
	public String compRateFromMaster(
			@RequestParam(value="componentId") Integer componentId,
			@RequestParam(value="purityId") Integer purityId,
			@RequestParam(value="colorId") Integer colorId,
			@RequestParam(value="costCompDtItemId") Integer costCompDtItemId,
			@RequestParam(value="costMtId") Integer costMtId){
		
		
		Component component =componentService.findOne(componentId);
		Purity purity =purityService.findOne(purityId);
		Color color =colorService.findOne(colorId);
		CostCompDtItem costCompDtItem=null;
		if(costCompDtItemId==null){
			costCompDtItem = new CostCompDtItem();
			
			costCompDtItem.setCostingMt(costingMtService.findOne(costMtId));
			costCompDtItem.setComponent(component);
			costCompDtItem.setPurity(purity);
			costCompDtItem.setColor(color);
			
			costCompDtItem=costingDtItemService.applyCompRate(costCompDtItem);
			
			
		}else{
			
			
			costCompDtItem	=  costCompDtItemService.findOne(costCompDtItemId);

			costCompDtItem.setComponent(component);
			costCompDtItem.setPurity(purity);
			costCompDtItem.setColor(color);
			
			costCompDtItem=costingDtItemService.applyCompRate(costCompDtItem);
			
			
			
			
		}
		
		
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("compRate", costCompDtItem.getCompRate());
		jsonObject.put("perGramRate", costCompDtItem.getPerGramRate());
		
		return jsonObject.toString();
		
		
	}
	
	
	

}
