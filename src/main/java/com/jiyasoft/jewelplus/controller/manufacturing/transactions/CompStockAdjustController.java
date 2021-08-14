package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompInwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ComponentPurchaseDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddCompAdj;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddCompInv;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompInwardDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IComponentPurchaseDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddCompAdjService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddCompInvService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class CompStockAdjustController {
	
	
	@Autowired
	private ICostingMtService costingMtService;
	
	
	@Autowired
	private IVAddCompDtService vAddCompDtService;
	
	@Autowired
	private IVAddCompInvService vAddCompInvService;
	
	@Autowired
	private ICompInwardDtService compInwardDtService;
	
	@Autowired
	private IVAddCompAdjService vAddCompAdjService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IComponentPurchaseDtService componentPurchaseDtService;
	
	@ModelAttribute("vAddCompInv")
	public VAddCompInv construct() {
		return new VAddCompInv();
	}

	
	@RequestMapping("/vAddCompStockAdjust/listing")
	@ResponseBody
	public String vAddCompStockAdjustList(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "vAddMtId", required = false) Integer vAddMtId) {

		DecimalFormat df = new DecimalFormat("#.###");
		
		StringBuilder sb = new StringBuilder();
		
		CostingMt costingMt = costingMtService.findOne(vAddMtId);
		
		sb.append("{\"total\":").append(vAddCompDtService.count()).append(",\"rows\": [");
		
		List<VAddCompInv> vAddCompInvs = vAddCompInvService.findByCostingMtAndDeactive(costingMt, false);
		
		
			for(VAddCompInv vAddCompInv:vAddCompInvs){
				
			sb.append("{\"id\":\"")
				.append(vAddCompInv.getId())
				.append("\",\"compName\":\"")
				.append((vAddCompInv.getComponent() != null ? vAddCompInv.getComponent().getName() : ""))
				.append("\",\"purity\":\"")
				.append((vAddCompInv.getPurity() != null ? vAddCompInv.getPurity().getName() : ""))
				.append("\",\"purityConv\":\"")
				.append((vAddCompInv.getPurity() != null ? vAddCompInv.getPurity().getPurityConv() : ""))
				.append("\",\"metal\":\"")
				.append((vAddCompInv.getPurity().getMetal() != null ? vAddCompInv.getPurity().getMetal().getName() : ""))
				.append("\",\"color\":\"")
				.append((vAddCompInv.getColor() != null ? vAddCompInv.getColor().getName() : ""))
				.append("\",\"componentid\":\"")
				.append((vAddCompInv.getComponent() != null ? vAddCompInv.getComponent().getId() : ""))
				.append("\",\"purityid\":\"")
				.append((vAddCompInv.getPurity() != null ? vAddCompInv.getPurity().getId() : ""))
				.append("\",\"metalid\":\"")
				.append((vAddCompInv.getPurity().getMetal() != null ? vAddCompInv.getPurity().getMetal().getId() : ""))
				.append("\",\"colorid\":\"")
				.append((vAddCompInv.getColor() != null ? vAddCompInv.getColor().getId() : ""))
				.append("\",\"metalWt\":\"")
				.append(df.format(vAddCompInv.getCompWt()))
				.append("\",\"compPcs\":\"")
				.append(vAddCompInv.getCompPcs())
				.append("\",\"adjusted\":\"")
				.append(vAddCompInv.getAdjusted() ? "Yes" : "No")
				.append("\",\"action2\":\"")
				.append("<a onClick='javascript:doDelete(event, this)' href='javascript:popDeleteCompAdjustment(")
				.append(vAddCompInv.getId())
				.append(");' class='btn btn-xs btn-danger triggerRemove")
				.append(vAddCompInv.getId())
				.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete Adjustment</a>")
				.append("\"},");
				
			}
		
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		System.out.println("str   "+str);

		return str;
		
	}
	
	
	@ResponseBody
	@RequestMapping("/add/vAddCompInv")
	public String setData(
			@RequestParam(value = "vAddMtId",required = true) Integer vAddMtId,Principal principal){
		
		String retVal = "-1";
		
		DecimalFormat df = new DecimalFormat("#.###");
		
		CostingMt costingMt = costingMtService.findOne(vAddMtId);
		
		
		List<VAddCompInv> vAddCompInvs = vAddCompInvService.findByCostingMtAndDeactive(costingMt, false);
		
		if(vAddCompInvs.size() > 0){
			
			List<VAddCompInv> vAddCompInvProc = vAddCompInvService.getCompStockList(costingMt.getId());
			
			for(VAddCompInv vAddMet : vAddCompInvs ){
				
				if(vAddMet.getAdjusted().equals(true)){
					retVal = "-2";
				}else{
					
					
					for(VAddCompInv vAddMetalInv : vAddCompInvProc){
						
						if(vAddMet.getComponent().getId().equals(vAddMetalInv.getComponent().getId()) && vAddMet.getPurity().getId().equals(vAddMetalInv.getPurity().getId()) &&
								vAddMet.getColor().getId().equals(vAddMetalInv.getColor().getId())	){
							
							vAddMet.setCompWt(Double.parseDouble(df.format(vAddMetalInv.getCompWt())));
							vAddMet.setCompPcs(vAddMetalInv.getCompPcs());
							vAddMet.setModiBy(principal.getName());
							vAddMet.setModiDate(new java.util.Date());
							vAddCompInvService.save(vAddMet);
						}
						
						
						
					}
					
				}
				
			}
			
			
		}else{
			
			List<VAddCompInv> vAddCompInvProc = vAddCompInvService.getCompStockList(costingMt.getId());
			
			for(VAddCompInv vAddCompDt : vAddCompInvProc){
				
				//--new entry--//-----//
				
				VAddCompInv vAddCompInvNew = new VAddCompInv();
							
				
				Purity purityObj = purityService.findOne(vAddCompDt.getPurity().getId());
				
				vAddCompInvNew.setCostingMt(vAddCompDt.getCostingMt());
				vAddCompInvNew.setComponent(vAddCompDt.getComponent());
				vAddCompInvNew.setPurity(vAddCompDt.getPurity());
				vAddCompInvNew.setPurityConv(purityObj.getPurityConv() != null ? purityObj.getPurityConv() : 0.0);
				vAddCompInvNew.setColor(vAddCompDt.getColor());
				vAddCompInvNew.setCompWt(Double.parseDouble(df.format(vAddCompDt.getCompWt())));
				vAddCompInvNew.setCompPcs(vAddCompDt.getCompPcs());
				vAddCompInvNew.setAdjusted(false);
				vAddCompInvNew.setCreatedBy(principal.getName());
				vAddCompInvNew.setCreatedDate(new java.util.Date());
				vAddCompInvNew.setDeactive(false);
				
				vAddCompInvService.save(vAddCompInvNew);
				
			}
			
			
			
			
			
		}
		
		return retVal;
		
	}
	
	
	
	//----------//----------from comp inward list----//
	
	
	@RequestMapping("/fromCompInwardList/listing")
	@ResponseBody
	public String compInwardList(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "vAddCompInvId", required = false) Integer vAddCompInvId) {

		StringBuilder sb = new StringBuilder();
		
		sb.append("{\"total\":").append(compInwardDtService.count()).append(",\"rows\": [");
		
		VAddCompInv vAddCompInv = vAddCompInvService.findOne(vAddCompInvId);
		
		DecimalFormat df = new DecimalFormat("#.###");
	
		//	if(vAddCompInv.getAdjusted().equals(false)){
			
				List<CompInwardDt> compInwardDts = compInwardDtService.findByComponentAndPurityAndColorAndDeactive(vAddCompInv.getComponent(), vAddCompInv.getPurity(), vAddCompInv.getColor(),false);
				
				Double adjWt;
				
				
				
				for (CompInwardDt compInwardDt : compInwardDts) {
					
					if(compInwardDt.getBalance() > 0){
						
						adjWt = 0.0;
						
						sb.append("{\"id\":\"")
								.append(compInwardDt.getId())
								.append("\",\"invNo\":\"")
								.append((compInwardDt.getCompInwardMt() != null ? compInwardDt.getCompInwardMt().getInvNo() : ""))
								.append("\",\"invDate\":\"")
								.append((compInwardDt.getCompInwardMt() != null ? compInwardDt.getCompInwardMt().getInvDateStr() : ""))
								.append("\",\"component\":\"")
								.append((compInwardDt.getComponent() != null ? compInwardDt.getComponent().getName() : ""))
								.append("\",\"purity\":\"")
								.append((compInwardDt.getPurity() != null ? compInwardDt.getPurity().getName() : ""))
								.append("\",\"color\":\"")
								.append((compInwardDt.getColor() != null ? compInwardDt.getColor().getName() : ""))
								.append("\",\"qty\":\"")
								.append(compInwardDt.getQty() != null ? compInwardDt.getQty() :"")
								.append("\",\"balance\":\"")
								.append(df.format(compInwardDt.getBalance()))
								.append("\",\"adjWt\":\"")
								.append(adjWt)
								.append("\"},");
					}
				}
				
			
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		

		return str;
		
		
		
	}
	
	
	//----delete--//---//
	
	@ResponseBody
	@RequestMapping("/vAddCompInv/delete/{id}")
	public String delete(@PathVariable int id,Principal principal) {
		
		
		VAddCompInv vAddCompInv = vAddCompInvService.findOne(id);
		
		if(vAddCompInv.getAdjusted().equals(false)){
			return "-2";
		}else {
		
		List<VAddCompAdj> vAddCompAdjs = vAddCompAdjService.findByVAddCompInvAndDeactive(vAddCompInv, false);
		
		for(VAddCompAdj vAddCompAdj: vAddCompAdjs){
			
			ComponentPurchaseDt componentPurchaseDt = componentPurchaseDtService.findOne(vAddCompAdj.getComponentPurchaseDt().getId());
			Double tempBalance = (componentPurchaseDt.getBalance() + vAddCompAdj.getAdjustmentWt());
			
			if(componentPurchaseDt.getMetalWt() < tempBalance){
				componentPurchaseDt.setBalance(componentPurchaseDt.getMetalWt());
			}else{
				componentPurchaseDt.setBalance(componentPurchaseDt.getBalance() + vAddCompAdj.getAdjustmentWt());
			}
			
			componentPurchaseDt.setModiBy(principal.getName());
			componentPurchaseDt.setModiDt(new java.util.Date());
			componentPurchaseDtService.save(componentPurchaseDt);
			
			vAddCompAdjService.delete(vAddCompAdj.getId());
			
		}
		
		
		vAddCompInv.setAdjusted(false);
		vAddCompInvService.save(vAddCompInv);
		

		return "-1";
	}
	}
	
	
	@ResponseBody
	@RequestMapping("/vAddCompInv/deleteAllCompAdjument")
	public String deleteAllCompAdjument(@RequestParam("mtId") Integer mtId,Principal principal) {
		String retVal ="";
		
		try {
		
			retVal = vAddCompInvService.deleteAllCompAdjustment(mtId, principal);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		return retVal;
		
	}
	
}
