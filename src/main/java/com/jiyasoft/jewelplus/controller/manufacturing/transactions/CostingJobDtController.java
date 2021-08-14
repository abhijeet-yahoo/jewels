package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostJobCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostJobLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostJobStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingJobDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingJobMt;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostJobCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostJobLabDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostJobStnDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingJobDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingJobMtService;


@RequestMapping("/manufacturing/transactions")
@Controller
public class CostingJobDtController {



		@Autowired
		private ICostingJobDtService costingJobDtService;
		
		@Autowired
		private ICostingJobMtService costingJobMtService;
		
		@Autowired
		private ICostJobStnDtService costJobStnDtService;
		
		@Autowired
		private ICostJobCompDtService costJobCompDtService;
		
		@Autowired
		private ICostJobLabDtService costJobLabDtService;
		
		@Autowired
		private IPartyService partyService;
		

			
		
		@RequestMapping("/costingJobDt/listing")
		@ResponseBody
		public String costingDtListing(Model model,
				@RequestParam(value = "limit", required = false) Integer limit,
				@RequestParam(value = "offset", required = false) Integer offset,
				@RequestParam(value = "sort", required = false) String sort,
				@RequestParam(value = "order", required = false) String order,
				@RequestParam(value = "search", required = false) String search,
				@RequestParam(value = "pInvNo", required = false) String pInvNo) {

			StringBuilder sb = new StringBuilder();
			
			
			
			sb.append("{\"total\":").append(costingJobDtService.count())
			.append(",\"rows\": [");
			
			CostingJobMt costingJobMt = costingJobMtService.findByInvNoAndDeactive(pInvNo.trim(), false);
			List<CostingJobDt> costingJobDts = costingJobDtService.findByCostingJobMtAndDeactive(costingJobMt, false);
			
			
			if(costingJobDts.size() > 0){
				for(CostingJobDt costingJobDt:costingJobDts){
					
				sb.append("{\"id\":\"")
					.append(costingJobDt.getId())
					.append("\",\"itemNo\":\"")
					.append((costingJobDt.getItemNo() != null ? costingJobDt.getItemNo() : ""))
					.append("\",\"party\":\"")
					.append((costingJobDt.getOrderDt().getOrderMt().getParty() != null ? costingJobDt.getOrderDt().getOrderMt().getParty().getPartyCode() : ""))
					.append("\",\"ordNo\":\"")
					.append((costingJobDt.getOrderDt().getOrderMt().getInvNo() != null ? costingJobDt.getOrderDt().getOrderMt().getInvNo() : ""))
					.append("\",\"ordRefNo\":\"")
					.append((costingJobDt.getOrderDt().getOrderMt().getRefNo() != null ? costingJobDt.getOrderDt().getOrderMt().getRefNo() : ""))
					.append("\",\"style\":\"")
					.append((costingJobDt.getDesign() != null ? costingJobDt.getDesign().getMainStyleNo() : ""))
					.append("\",\"bag\":\"")
					.append((costingJobDt.getBagMt() != null ? costingJobDt.getBagMt().getName() : ""))
					.append("\",\"purity\":\"")
					.append((costingJobDt.getPurity() != null ? costingJobDt.getPurity().getName() : ""))
					.append("\",\"color\":\"")
					.append((costingJobDt.getColor() != null ? costingJobDt.getColor().getName() : ""))
					.append("\",\"pcs\":\"")
					.append((costingJobDt.getPcs() != null ? costingJobDt.getPcs() : ""))
					.append("\",\"grossWt\":\"")
					.append((costingJobDt.getGrossWt() != null ? costingJobDt.getGrossWt() : ""))
					.append("\",\"netWt\":\"")
					.append((costingJobDt.getNetWt() != null ? costingJobDt.getNetWt() : ""))
					.append("\",\"metalRate\":\"")
					.append((costingJobDt.getMetalRate() != null ? costingJobDt.getMetalRate() : ""))
					.append("\",\"metalValue\":\"")
					.append((costingJobDt.getMetalValue() != null ? costingJobDt.getMetalValue() : ""))
					.append("\",\"stoneValue\":\"")
					.append((costingJobDt.getStoneValue() != null ? costingJobDt.getStoneValue() : ""))
					.append("\",\"compValue\":\"")
					.append((costingJobDt.getCompValue() != null ? costingJobDt.getCompValue() : ""))
					.append("\",\"labourValue\":\"")
					.append((costingJobDt.getLabValue() != null ? costingJobDt.getLabValue() : ""))
					.append("\",\"setValue\":\"")
					.append((costingJobDt.getSetValue() != null ? costingJobDt.getSetValue() : ""))
					.append("\",\"handlingCost\":\"")
					.append((costingJobDt.getHdlgValue() != null ? costingJobDt.getHdlgValue() : ""))
					.append("\",\"fob\":\"")
					.append((costingJobDt.getFob() != null ? costingJobDt.getFob() : ""))
					.append("\",\"other\":\"")
					.append((costingJobDt.getOther() != null ? costingJobDt.getOther() : ""))
					.append("\",\"finalPrice\":\"")
					.append((costingJobDt.getFinalPrice() != null ? costingJobDt.getFinalPrice() : ""))
					.append("\",\"image\":\"")
					.append(costingJobDt.getDesign().getDefaultImage() != null ? costingJobDt.getDesign().getDefaultImage() :"blank.png")
					.append("\",\"rLock\":\"")
					.append((costingJobDt.getrLock() == null ? "": (costingJobDt.getrLock() ? "Lock": "Unlock"))) //1 = lock & 0 = unlock
					.append("\",\"actionLock\":\"")
					.append("<a href='javascript:doCostJobDtLockUnLock(")
					.append(costingJobDt.getId())
					.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
					.append("\",\"action1\":\"")
					.append("<a href='javascript:editCostingJobDt(")
					.append(costingJobDt.getId())
					.append(");' class='btn btn-xs btn-warning'  ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
					.append("\",\"action2\":\"")
					.append("<a  href='javascript:deleteCostingJobDt(event,")
					.append(costingJobDt.getId())
					.append(");' class='btn btn-xs btn-danger triggerRemove")
					.append(costingJobDt.getId())
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
		@RequestMapping("/costJobDt/saveEdit")
		public String addEditCostingDt(
				@RequestParam(value="costJobDtId") Integer costDtId,
				@RequestParam(value="grossWt") Double grossWt,
				@RequestParam(value="otherWt") Double other,Principal principal){
			
			
			DecimalFormat df = new DecimalFormat("#.###");
			String retVal = "-1";
			
			CostingJobDt costingJobDt = costingJobDtService.findOne(costDtId);

			List<CostJobStnDt> costJobStnDts = costJobStnDtService.findByCostingJobDtAndDeactive(costingJobDt, false);
			Double totStnCarat = 0.0;
			for(CostJobStnDt costJobStnDt:costJobStnDts){
				totStnCarat += costJobStnDt.getCarat(); 
			}

			Double temVal = totStnCarat/5;
			Double tempNetWt = grossWt-temVal;
			
			
			List<CostJobCompDt> costJobCompDts = costJobCompDtService.findByCostingJobDtAndDeactive(costingJobDt, false);
			Double totCompMetalWt = 0.0;
			for(CostJobCompDt costJobCompDt:costJobCompDts){
				totCompMetalWt += costJobCompDt.getMetalWt();
			}
			
			Double actualNetWt = tempNetWt - totCompMetalWt;
			
			costingJobDt.setGrossWt(grossWt);
			costingJobDt.setNetWt(Double.parseDouble(df.format(actualNetWt)));
			costingJobDt.setOther(other);
			costingJobDt.setModiBy(principal.getName());
			costingJobDt.setModiDate(new java.util.Date());
			costingJobDtService.save(costingJobDt);
			
			Party party = partyService.findByDefaultFlag(true);
			costingJobDtService.applyRate(costingJobDt, party);

			return retVal;
		}
		
		
		
		@ResponseBody
		@RequestMapping("/costingJobDt/lockUnlock")
		public String lockUnlockCostDt(
				@RequestParam(value="dtId")Integer dtId){
			
			String retVal = "-1";
			
			CostingJobDt costingJobDt = costingJobDtService.findOne(dtId);
			
			
			
				List<CostJobStnDt> costJobStnDts = costJobStnDtService.findByCostingJobDtAndDeactive(costingJobDt, false);
				List<CostJobCompDt> costJobCompDts = costJobCompDtService.findByCostingJobDtAndDeactive(costingJobDt, false);
				List<CostJobLabDt> costJobLabDts = costJobLabDtService.findByCostingJobDtAndDeactive(costingJobDt, false);
				
				
				
					if(costingJobDt.getrLock() == true){
						costingJobDt.setrLock(false);
						
						for(CostJobStnDt costJobStnDt : costJobStnDts){
							costJobStnDt.setrLock(false);
							costJobStnDtService.save(costJobStnDt);
						}
						
						for(CostJobCompDt costJobCompDt : costJobCompDts){
							costJobCompDt.setrLock(false);
							costJobCompDtService.save(costJobCompDt);
						}
						
						for(CostJobLabDt costJobLabDt : costJobLabDts){
							costJobLabDt.setrLock(false);
							costJobLabDtService.save(costJobLabDt);
						}
						
					}else{
						costingJobDt.setrLock(true);
						
						for(CostJobStnDt costJobStnDt : costJobStnDts){
							costJobStnDt.setrLock(true);
							costJobStnDtService.save(costJobStnDt);
						}
						
						for(CostJobCompDt costJobCompDt : costJobCompDts){
							costJobCompDt.setrLock(true);
							costJobCompDtService.save(costJobCompDt);
						}
						
						for(CostJobLabDt costJobLabDt : costJobLabDts){
							costJobLabDt.setrLock(true);
							costJobLabDtService.save(costJobLabDt);
						}
					
						
					}
		
					costingJobDtService.save(costingJobDt);
					
			
			
			
			
			
			return retVal;
		}
		
		
		
		
		
		
		@RequestMapping("/costingJobDt/validation")
		@ResponseBody
		public String validation(
				@RequestParam(value = "dtId")Integer dtId){
			
			String retVal = "1";
			
			CostingJobDt costingJobDt = costingJobDtService.findOne(dtId);
			
			if(costingJobDt.getrLock() == true){
				return retVal = "-1";
			}
			
			return retVal;
		}
		
		
		
		@RequestMapping("/costingJobDt/edit/{id}")
		public String editcostingDtt(@PathVariable int id,Model model){

			CostingJobDt costingJobDt = costingJobDtService.findOne(id);
			model.addAttribute("costingJobDt", costingJobDt);
			return "costingJobDt/add";
		}
		
		
		@ResponseBody
		@RequestMapping("/costingJobDt/delete/{id}")
		public String delete(@PathVariable int id){
			
			String retVal = "1";
			
			CostingJobDt costingJobDt = costingJobDtService.findOne(id);
		
			
				costingJobDtService.delete(id);
				
				List<CostJobStnDt> costJobStnDts = costJobStnDtService.findByCostingJobDtAndDeactive(costingJobDt, false);
				for(CostJobStnDt costJobStnDt:costJobStnDts){
					costJobStnDtService.delete(costJobStnDt.getId());
				}
				
				List<CostJobCompDt> costJobCompDts = costJobCompDtService.findByCostingJobDtAndDeactive(costingJobDt, false);
				for(CostJobCompDt costJobCompDt:costJobCompDts){
					costJobCompDtService.delete(costJobCompDt.getId());
				}
				
				List<CostJobLabDt> costJobLabDts = costJobLabDtService.findByCostingJobDtAndDeactive(costingJobDt, false);
				for(CostJobLabDt costJobLabDt:costJobLabDts){
					costJobLabDtService.delete(costJobLabDt.getId());
				}
			
			return retVal;
			
		}
		
		
		
		
		@ResponseBody
		@RequestMapping("/costingJobDt/dtLockUnlockAll")
		public String lockUnlockAll(
				@RequestParam(value="mtId")Integer mtId,
				@RequestParam(value="status")Integer status){
			
			String retVal = "-1";
			CostingJobMt 		costingJobMt 	= costingJobMtService.findOne(mtId);
			List<CostingJobDt> 	costingJobDts 	= costingJobDtService.findByCostingJobMtAndDeactive(costingJobMt, false);
			List<CostJobStnDt> 	costJobStnDts 	= costJobStnDtService.findByCostingJobMtAndDeactive(costingJobMt, false);
			List<CostJobCompDt> costJobCompDts 	= costJobCompDtService.findByCostingJobMtAndDeactive(costingJobMt, false);
			List<CostJobLabDt> 	costJobLabDts 	= costJobLabDtService.findByCostingJobMtAndDeactive(costingJobMt, false);
			Boolean setValue ;
			
			if(status == 1){
				setValue = true;
			}else{
				setValue = false;
			}
			
			for(CostingJobDt costingJobDt:costingJobDts){
				costingJobDt.setrLock(setValue);
				costingJobDtService.save(costingJobDt);
			}
			
			for(CostJobStnDt costJobStnDt:costJobStnDts){
				costJobStnDt.setrLock(setValue);
				costJobStnDtService.save(costJobStnDt);
			}
			

			for(CostJobCompDt costJobCompDt:costJobCompDts){
				costJobCompDt.setrLock(setValue);
				costJobCompDtService.save(costJobCompDt);
			}
			
			
			for(CostJobLabDt costJobLabDt:costJobLabDts){
				costJobLabDt.setrLock(setValue);
				costJobLabDtService.save(costJobLabDt);
			}
			
			return retVal;
		}
	

	
	
}