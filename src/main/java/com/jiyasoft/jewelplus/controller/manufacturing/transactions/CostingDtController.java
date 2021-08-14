package com.jiyasoft.jewelplus.controller.manufacturing.transactions;


import java.security.Principal;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QCostingDt;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostLabDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostMetalDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostStnDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingMtService;
import com.mysema.query.Tuple;

@RequestMapping("/manufacturing/transactions")
@Controller
public class CostingDtController {
	
	@Value("${netWtWithComp}")
	private Boolean netWtWithCompFlg;

	@Autowired
	private ICostingDtService costingDtService;
	
	@Autowired
	private ICostingMtService costingtMtService;
	
	@Autowired
	private ICostStnDtService costStnDtService;
	
	@Autowired
	private ICostCompDtService costCompDtService;
	
	@Autowired
	private ICostLabDtService costLabDtService;
	
	@Autowired
	private IPartyService partyService;
	
	


	
	@RequestMapping("/costingDt/listing")
	@ResponseBody
	public String costingDtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "pInvNo", required = false) String pInvNo) {
		
		
		
		return costingDtService.costDtListing(limit, offset, sort, order, search, pInvNo,netWtWithCompFlg);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	@ResponseBody
	@RequestMapping("/costDt/saveEdit")
	public String addEditCostingDt(
			@RequestParam(value="costDtId", required =false) Integer costDtId,
			@RequestParam(value="grossWt", required =false) Double grossWt,
			@RequestParam(value="otherWt", required =false) Double other,
			@RequestParam(value="partyId", required =false) Integer partyId,
			@RequestParam(value="itemNo", required =false)  String itemNo,
			@RequestParam(value="dispPercentDt", required =false)  Double dispPercentDt,
			@RequestParam(value="lossPercDt", required =false)  Double lossPercDt,
			@RequestParam(value="costingType", required=false) String costingType,
			Principal principal){
		
		String retVal = costingDtService.costingDtSave(costDtId, grossWt, other, partyId, itemNo, dispPercentDt, lossPercDt, principal,costingType);
		
		return retVal;
	}
	
	
	
	@ResponseBody
	@RequestMapping("/costingDt/lockUnlock")
	public String lockUnlockCostDt(
			@RequestParam(value="dtId")Integer dtId){
		
		String retVal = "-1";
		
		CostingDt costingDt = costingDtService.findOne(dtId);
		
		if(costingDt.getCostingMt().getExpClose() == true){
			retVal = "-2";
		}else{
		
			List<CostStnDt> costStnDts = costStnDtService.findByCostingDtAndDeactive(costingDt, false);
			List<CostCompDt> costCompDts = costCompDtService.findByCostingDtAndDeactive(costingDt, false);
			List<CostLabDt> costLabDts = costLabDtService.findByCostingDtAndDeactive(costingDt, false);
			
			
			
				if(costingDt.getrLock() == true){
					costingDt.setrLock(false);
					
					for(CostStnDt costStnDt : costStnDts){
						costStnDt.setrLock(false);
						costStnDtService.save(costStnDt);
					}
					
					for(CostCompDt costCompDt : costCompDts){
						costCompDt.setrLock(false);
						costCompDtService.save(costCompDt);
					}
					
					for(CostLabDt costLabDt : costLabDts){
						costLabDt.setrLock(false);
						costLabDtService.save(costLabDt);
					}
					
				}else{
					costingDt.setrLock(true);
					
					for(CostStnDt costStnDt : costStnDts){
						costStnDt.setrLock(true);
						costStnDtService.save(costStnDt);
					}
					
					for(CostCompDt costCompDt : costCompDts){
						costCompDt.setrLock(true);
						costCompDtService.save(costCompDt);
					}
					
					for(CostLabDt costLabDt : costLabDts){
						costLabDt.setrLock(true);
						costLabDtService.save(costLabDt);
					}
				
					
				}
	
				costingDtService.save(costingDt);
				
		}
		
		
		
		
		return retVal;
	}
	
	
	
	
	
	
	@RequestMapping("/costingDt/validation")
	@ResponseBody
	public String validation(
			@RequestParam(value = "dtId")Integer dtId){
		
		String retVal = "1";
		
		CostingDt costingDt = costingDtService.findOne(dtId);
		
		
		if(costingDt.getCostingMt().getExpClose() == true){
			return retVal = "-2";
		}
		
		
		if(costingDt.getrLock() == true){
			return retVal = "-1";
		}
		
		return retVal;
	}
	
	
	
	
	
	
	
	@RequestMapping("/costingDt/edit/{id}")
	public String editcostingDtt(@PathVariable int id,Model model,
			@RequestParam(value="costingType", required=false) String costingType){

		CostingDt costingDt = costingDtService.findOne(id);
		model.addAttribute("allPartyMap", partyService.getPartyList());
		model.addAttribute("costingDt", costingDt);
		if(costingType != null && costingType.equalsIgnoreCase("merge")){
			model.addAttribute("costingType", "merge");
		}
		
		return "costingDt/add";
	}
	
	
	@ResponseBody
	@RequestMapping("/costingDt/delete/{id}")
	public String delete(@PathVariable int id){
		
		String retVal = costingDtService.deleteCostingDt(id);
		
		return retVal;
		
	}
	
	
	
	@ResponseBody
	@RequestMapping("/costingDt/dtLockUnlockAll")
	public String lockUnlockAll(
			@RequestParam(value="mtId")Integer mtId,
			@RequestParam(value="status")Integer status){
		
		String retVal = "-1";
		Boolean setValue ;
		
		if(status == 1){
			setValue = true;
		}else{
			setValue = false;
		}
		
		costingDtService.lockUnlockDt(mtId, setValue);
		costStnDtService.lockUnlockStnDt(mtId, setValue);
		costCompDtService.lockUnlockCompDt(mtId, setValue);
		costLabDtService.lockUnlockLabDt(mtId, setValue);
		
		return retVal;
	}
	
	
	
	@ResponseBody
	@RequestMapping("/costingDt/updateDispPercDt")
	public String updateDtDisplayPercent(
			@RequestParam(value="mtId") Integer mtId,
			@RequestParam(value="dispPerc") Double dispPerc){
		
		String retVal = "-1";
		
		if (mtId == null || mtId.equals("") || (mtId != null && mtId == 0)) {
			retVal = "-2";
		}else{
			CostingMt costingMt = costingtMtService.findOne(mtId);
			costingMt.setDispPercent(dispPerc);
			costingtMtService.save(costingMt);
			costingDtService.updateCostingDtDispPercent(mtId, dispPerc);
		}
		
		return retVal; 
	}
	
	
	@ResponseBody
	@RequestMapping("/costingDt/getData/{dtId}")
	public String getCostData(@PathVariable int dtId){
		
		
		CostingDt costingDt = costingDtService.findOne(dtId);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("grossWt", costingDt.getGrossWt());
		jsonObject.put("netWt", costingDt.getNetWt());
		jsonObject.put("metalValue", costingDt.getMetalValue());
		jsonObject.put("stoneValue", costingDt.getStoneValue());
		jsonObject.put("compValue", costingDt.getCompValue());
		jsonObject.put("labourValue", costingDt.getLabValue());
		jsonObject.put("setValue", costingDt.getSetValue());
		jsonObject.put("handlingCost", costingDt.getHdlgValue());
		jsonObject.put("fob", costingDt.getFob());
		jsonObject.put("other", costingDt.getOther());
		jsonObject.put("finalPrice", costingDt.getFinalPrice());
		jsonObject.put("dispPercentDt", costingDt.getDispPercentDt());
		jsonObject.put("discAmount", costingDt.getDiscAmount());
		jsonObject.put("lossPercDt", costingDt.getLossPercDt());
		
		
		return jsonObject.toString();
		
		
	}
	
	@RequestMapping("/costingDt/getMaxSetNo")
	@ResponseBody
	public String getMaxSetNo(
			@RequestParam(value = "mtId")Integer mtId){
		
	
		Integer retVal =  costingDtService.getMaxSetNo(mtId);
		
		return retVal.toString();
	}
	
	
	@RequestMapping("/costingDt/checkDiaTolerance")
	@ResponseBody
	public String checkDiaTolerance(
			@RequestParam(value = "bagNo")String bagNo){
		
	
		String retVal =  costingDtService.checkDiaTolerance(bagNo);
		
		return retVal;
	}
	
	@RequestMapping("/costDtSetNo/listing")
	@ResponseBody
	public String costSetNoListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "costMtId", required = false) Integer costMtId) {

		StringBuilder sb = new StringBuilder();
		
	
		System.out.println(" costMtId   "+costMtId);
		
		QCostingDt qCostingDt = QCostingDt.costingDt;
		
		List<Tuple> getSetNoList = costingDtService.getSetNoList(costMtId);
		sb.append("{\"total\":").append(getSetNoList.size()).append(",\"rows\": [");
		
		for (Tuple tuple : getSetNoList) {
			
			System.out.println("lock    "+tuple.get(qCostingDt.rLock));
			
			
			sb.append("{\"costMtid\":\"")
			.append(tuple.get(qCostingDt.costingMt.id))
			.append("\",\"setNo\":\"")
			.append(tuple.get(qCostingDt.setNo) != null ? tuple.get(qCostingDt.setNo) :"")
			.append("\",\"rLock\":\"")
			.append(tuple.get(qCostingDt.rLock)); //1 = lock & 0 = unlock
			if(tuple.get(qCostingDt.rLock) == true){
				sb.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doCostDtInvoLockUnLock(")
				.append("")
				.append(");' class='btn btn-xs btn-danger'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock</a>");	
			}else{
				
				sb.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doCostDtInvoLockUnLock(")
				.append("")
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Unlock</a>");
			}
			
			sb.append("\"},");
		}
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}
	
		@ResponseBody
		@RequestMapping("/costingDt/lockInvoice")
		public String lockInvoice(
			@RequestParam(value="mtId", required=false) Integer mtId,
			@RequestParam(value="setNo", required=false) Integer setNo,
			@RequestParam(value="vRlock", required=false) Boolean vRlock,Principal principal){
			
			
			
			Boolean rLock = true;
			
			if(vRlock == true){
				rLock = false;
			}else if(vRlock == false){
				rLock = true;
			}
			
			int retVal=costingDtService.lockDtInvoice(mtId, setNo, principal,rLock);
			
			return retVal+"";

	}
	
	
}
