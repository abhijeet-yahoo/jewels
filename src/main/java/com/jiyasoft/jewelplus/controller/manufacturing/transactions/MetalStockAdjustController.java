package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalPurchaseDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddMetalInv;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalPurchaseDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddMetalInvService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class MetalStockAdjustController {
	
	
	@Autowired
	private ICostingMtService costingMtService;
	
	@Autowired
	private IVAddMetalInvService vAddMetalInvService;
	
	
	
	@Autowired
	private IMetalPurchaseDtService metalPurchaseDtService;
	
	
	
	@ModelAttribute("vAddMetalInv")
	public VAddMetalInv construct(){
		return new VAddMetalInv();
	}
	
	
	@RequestMapping("/vAddMetalStockAdjust/listing")
	@ResponseBody
	public String vAddMetalStockAdjustList(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "vAddMtId", required = false) Integer vAddMtId) {

		StringBuilder sb = new StringBuilder();
		
		CostingMt costingMt = costingMtService.findOne(vAddMtId);
		
		sb.append("{\"total\":").append(vAddMetalInvService.count()).append(",\"rows\": [");
		
		List<VAddMetalInv> vAddMetalInvs = vAddMetalInvService.findByCostingMt(costingMt);

		
			for(VAddMetalInv vAddMetalInv:vAddMetalInvs){
				
			sb.append("{\"id\":\"")
				.append(vAddMetalInv.getId())
				.append("\",\"metalName\":\"")
				.append((vAddMetalInv.getMetal() != null ? vAddMetalInv.getMetal().getName() : ""))
				.append("\",\"netWt\":\"")
				.append((vAddMetalInv.getNetWt() != null ? vAddMetalInv.getNetWt() : ""))
				.append("\",\"pure\":\"")
				.append((vAddMetalInv.getPureWt() != null ? vAddMetalInv.getPureWt() : ""))
				.append("\",\"adjusted\":\"")
				.append(vAddMetalInv.getAdjusted() ? "Yes" : "No")
				.append("\",\"action2\":\"")
				.append("<a onClick='javascript:doDelete(event, this)' href='javascript:popDeleteMetalAdjustment(")
				.append(vAddMetalInv.getId())
				.append(");' class='btn btn-xs btn-danger triggerRemove")
				.append(vAddMetalInv.getId())
				.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete Adjustment</a>")
				.append("\"},");
				
			}
		
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
		
	}
	
	
	

	@ResponseBody
	@RequestMapping("/add/vAddMetalInv")
	public String setData(
			@RequestParam(value = "vAddMtId",required = true) Integer vAddMtId,Principal principal){
		
		String retVal = "-1";
		
		
		CostingMt costingMt = costingMtService.findOne(vAddMtId);
		
		
		List<VAddMetalInv> vAddMetalInvs = vAddMetalInvService.findByCostingMt(costingMt);
		
		if(vAddMetalInvs.size() > 0){
			
			List<VAddMetalInv> vAddMetalInvProc = vAddMetalInvService.getMetalInvStockList(costingMt.getId());
			
			for(VAddMetalInv vAddMet : vAddMetalInvs ){
				
				if(vAddMet.getAdjusted().equals(true)){
					retVal = "-2";
				}else{
					
					
					for(VAddMetalInv vAddMetalInv : vAddMetalInvProc){
						
						if(vAddMet.getMetal().getId().equals(vAddMetalInv.getMetal().getId())){
							
							vAddMet.setNetWt(vAddMetalInv.getNetWt());
							vAddMet.setPureWt(vAddMetalInv.getPureWt());
							vAddMet.setModiBy(principal.getName());
							vAddMet.setModiDate(new java.util.Date());
							vAddMetalInvService.save(vAddMet);
						}
						
					}
					
				}
				
			}
			
			
		}else{
			
			List<VAddMetalInv> vAddMetalInvProc = vAddMetalInvService.getMetalInvStockList(costingMt.getId());
			
			for(VAddMetalInv vAddMetalInv : vAddMetalInvProc){
				
				//--new entry--//-----//
				
				VAddMetalInv vAddMetalInvNew = new VAddMetalInv();
				
				vAddMetalInvNew.setCostingMt(costingMt);
				vAddMetalInvNew.setMetal(vAddMetalInv.getMetal());
				vAddMetalInvNew.setNetWt(vAddMetalInv.getNetWt());
				vAddMetalInvNew.setPureWt(vAddMetalInv.getPureWt());
				vAddMetalInvNew.setCreatedBy(principal.getName());
				vAddMetalInvNew.setCreatedDate(new java.util.Date());
								
				vAddMetalInvService.save(vAddMetalInvNew);
				
			}
			
			
			
			
			
		}
		
		
		
				
		
		return retVal;
		
	}
	
	
	
	//----------//----------from Metal inward list----//
	
	
		@RequestMapping("/fromMetalInwardList/listing")
		@ResponseBody
		public String metalInwardList(Model model,
				@RequestParam(value = "limit", required = false) Integer limit,
				@RequestParam(value = "offset", required = false) Integer offset,
				@RequestParam(value = "sort", required = false) String sort,
				@RequestParam(value = "order", required = false) String order,
				@RequestParam(value = "search", required = false) String search,
				@RequestParam(value = "vAddMetalInvId", required = false) Integer vAddMetalInvId) {

			StringBuilder sb = new StringBuilder();
			
						
		
			
			VAddMetalInv vAddMetalInv = vAddMetalInvService.findOne(vAddMetalInvId);
			
				Page<MetalPurchaseDt> metalInwardDts = metalPurchaseDtService.balanceInvoice(limit, offset, sort, order, search,vAddMetalInv.getMetal(),vAddMetalInv.getCostingMt().getVaddIn999());
				
								
				sb.append("{\"total\":").append(metalInwardDts.getTotalElements()).append(",\"rows\": [");
				
				for (MetalPurchaseDt metalInwardDt : metalInwardDts) {
					
					if(metalInwardDt.getBalance() > 0){
						
						sb.append("{\"id\":\"")
								.append(metalInwardDt.getId())
								.append("\",\"invNo\":\"")
								.append((metalInwardDt.getMetalPurchaseMt() != null ? metalInwardDt.getMetalPurchaseMt().getInvNo() : ""))
								.append("\",\"invDate\":\"")
								.append((metalInwardDt.getMetalPurchaseMt() != null ? metalInwardDt.getMetalPurchaseMt().getInvDateStr() : ""))
								.append("\",\"balance\":\"")
								.append(metalInwardDt.getBalance())
								.append("\",\"rate\":\"")
								.append(metalInwardDt.getRate())
								.append("\",\"in999\":\"")
								.append(metalInwardDt.getIn999())
								.append("\",\"adjWt\":\"")
								.append(0.0)
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
		@RequestMapping("/vAddMetalInv/delete/{id}")
		public String delete(@PathVariable int id,Principal principal) {
			
			vAddMetalInvService.deleteMetalAdjustment(id, principal);
			
			return "-1";
			
		}
		
		@ResponseBody
		@RequestMapping("/vAddMetalInv/deleteAllMetalAdjument")
		public String deleteAllMetalAdjument(@RequestParam("mtId") Integer mtId,Principal principal) {
			
			String retVal = vAddMetalInvService.deleteAllMetalAdjustment(mtId, principal);
			
			return retVal;
			
		}
		
}



