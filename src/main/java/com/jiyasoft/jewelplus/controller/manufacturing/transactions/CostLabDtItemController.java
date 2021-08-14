package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import javax.persistence.EntityManager;
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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LabourCharge;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LabourType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostCompDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostLabDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QCostMetalDtItem;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILabourChargeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILabourTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostLabDtItemService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingDtItemService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingMtService;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQuery;

@RequestMapping("/manufacturing/transactions")
@Controller
public class CostLabDtItemController {

	@Value("${netWtWithComp}")
	private Boolean netWtWithCompFlg;
	
	@Autowired
	private ICostLabDtItemService costLabDtItemService;
	
	@Autowired
	private ICostingMtService costingMtService;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private ICostingDtItemService costingDtItemService;
	
	@Autowired
	private ILabourTypeService labourTypeService;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private ILabourChargeService labourChargeService;
	
	@Autowired
	private IMetalService metalService;
	
	
	@RequestMapping("/costLabDtItem/listing")
	@ResponseBody
	public String costLabDtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "costDtId", required = false) Integer costDtId) {

		StringBuilder sb = new StringBuilder();
		
		
		
		
		
		CostingDtItem costingDtItem = costingDtItemService.findOne(costDtId);
		List<CostLabDtItem> costLabDtItems = costLabDtItemService.findByCostingDtItemAndDeactive(costingDtItem, false);
		
		sb.append("{\"total\":").append(costLabDtItems.size()).append(",\"rows\": [");
		
		if(costLabDtItems.size() > 0){
			for(CostLabDtItem costLabDtItem:costLabDtItems){
				
			sb.append("{\"id\":\"")
				.append(costLabDtItem.getId())
				.append("\",\"metal\":\"")
				.append((costLabDtItem.getMetal() != null ? costLabDtItem.getMetal().getName() : ""))
				.append("\",\"labourType\":\"")
				.append((costLabDtItem.getLabourType() != null ? costLabDtItem.getLabourType().getName() : ""))
				.append("\",\"rate\":\"")
				.append((costLabDtItem.getLabourRate() != null ? costLabDtItem.getLabourRate() : ""))
				.append("\",\"value\":\"")
				.append((costLabDtItem.getLabourValue() != null ? costLabDtItem.getLabourValue() : ""))
				.append("\",\"rLock\":\"")
				.append(costLabDtItem.getrLock()) //1 = lock & 0 = unlock
				.append("\",\"perPcs\":\"")
				.append(costLabDtItem.getPerPcRate())
				.append("\",\"perGram\":\"")
				.append(costLabDtItem.getPerGramRate())
				.append("\",\"percent\":\"")
				.append(costLabDtItem.getPercentage())
				.append("\",\"perCaratRate\":\"")
				.append(costLabDtItem.getPerCaratRate())
				.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doLabDtLockUnLockItem(")
				.append(costLabDtItem.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				.append("\",\"action1\":\"")
				.append("<a href='javascript:editCostLabDtItem(")
				.append(costLabDtItem.getId())
				.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a  href='javascript:deleteCostLabDtItem(event,")
				.append(costLabDtItem.getId())
				.append(");' class='btn btn-xs btn-danger triggerRemove")
				.append(costLabDtItem.getId())
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
	@RequestMapping(value="/costLabDtItem/add", method = RequestMethod.POST)
	public String addEditCostLabDtItem(@Valid @ModelAttribute("costLabDtItem") CostLabDtItem costLabDtItem,
			BindingResult bindingResult,
			@RequestParam(value="id", required=false)Integer id,
			@RequestParam(value="forLabCostMtItemId", required=false)Integer forLabCostMtItemId,
			@RequestParam(value="forLabCostDtItemId", required=false)Integer forLabCostDtItemId,
			RedirectAttributes redirectAttributes,Principal principal){
		
		
		String retVal = "-1";
		
		if(bindingResult.hasErrors()){
			return "xyz";
		}
		
		try {
			
		retVal=costLabDtItemService.costLabDtItemSave(costLabDtItem, id,forLabCostMtItemId, forLabCostDtItemId, principal,netWtWithCompFlg);
			
		}  catch (Exception e) {
			e.printStackTrace();
			retVal = "error";
		}
		
		
		
		return retVal;

		
	}
	
	
	@ResponseBody
	@RequestMapping("/costLabDtItem/lockUnlock")
	public String lockUnlockLabCompDt(
			@RequestParam(value="labDtId")Integer labDtId){
		
		String retVal = "-1";
		CostLabDtItem costLabDtItem = costLabDtItemService.findOne(labDtId);
		
		
		if(costLabDtItem.getCostingMt().getExpClose() == true){
			retVal = "-2";
		}else{
		
			if(costLabDtItem.getrLock() == true){
				costLabDtItem.setrLock(false);
			}else{
				costLabDtItem.setrLock(true);
			}
		
			costLabDtItemService.save(costLabDtItem);
		}
		
		return retVal;
	}
	
	
	

	@RequestMapping("/costLabDtItem/validationEdit")
	@ResponseBody
	public String validation(
			@RequestParam(value = "labDtId")Integer labDtId){
		
		String retVal = "";
		
		CostLabDtItem costLabDtItem = costLabDtItemService.findOne(labDtId);
		
		if(costLabDtItem.getCostingMt().getExpClose() == true){
			return retVal = "-2";
		}
		
		if(costLabDtItem.getrLock() == true){
			retVal = "-1";
		}
		return retVal;
	}
	
	
	@RequestMapping("/costLabDtItem/edit/{id}")
	public String editCosttLabDt(@PathVariable int id,Model model){
		
		if(id>0){
			CostLabDtItem costLabDtItem = costLabDtItemService.findOne(id);
			model.addAttribute("costLabDtItem",costLabDtItem);
			model.addAttribute("labourTypeMap",labourTypeService.getLabourTypeList());
			model.addAttribute("metalMap", metalService.getMetalList());	
		}else{
			CostLabDtItem costLabDtItem = new CostLabDtItem();
			model.addAttribute("costLabDtItem",costLabDtItem);
			model.addAttribute("labourTypeMap",labourTypeService.getLabourTypeList());
			model.addAttribute("metalMap", metalService.getMetalList());
			
			
		}
		
		
		
		return "costLabDtItem/add";
	}
	
	


	@ResponseBody
	@RequestMapping("/costLabDtItem/delete/{id}")
	public String delete(@PathVariable int id, Principal principal){
		
		String retVal = "-1";
		
		CostLabDtItem costLabDtItem = costLabDtItemService.findOne(id);
		
		if(costLabDtItem.getCostingMt().getExpClose() == true){
			retVal = "-2";
		}else{
			costLabDtItemService.delete(id);
			
			costingDtItemService.updateItemFob(costLabDtItem.getCostingDtItem(),netWtWithCompFlg);
			
		}
		
		return retVal;
	}
	/*
	
	
	
	
	@ResponseBody
	@RequestMapping("/labourAsPerMaster/save")
	public String updateLabourAsMaster(
			@RequestParam(value="mtId") Integer costMtId,Principal principal){
		
		@SuppressWarnings("unused")
		Integer res = costLabDtService.labAsPerMaster(costMtId);

		return "-1";
		
	}
	
	*/
	
	
	
	@ResponseBody
	@RequestMapping("/costLabDtItem/labRateFromMaster")
	public String labRateFromMaster(
			@RequestParam(value="metalId") Integer metalId,
			@RequestParam(value="labourId") Integer labourId,
			@RequestParam(value="costLabDtItemId") Integer costLabDtItemId,
			@RequestParam(value="costDtItemId") Integer costDtItemId){
		
		
		
		CostingDtItem costingDtItem =costingDtItemService.findOne(costDtItemId);
		
		Metal metal=metalService.findOne(metalId);
		LabourType labourType =labourTypeService.findOne(labourId);
			

		
		QCostMetalDtItem qCostMetalDtItem = QCostMetalDtItem.costMetalDtItem;
		JPAQuery query=new JPAQuery(entityManager);
		
		List<Tuple> costMetalItemList=null;
		
		costMetalItemList = query.from(qCostMetalDtItem).
				where(qCostMetalDtItem.deactive.eq(false).and(qCostMetalDtItem.costingDtItem.id.eq(costDtItemId)).and(qCostMetalDtItem.purity.metal.eq(metal)))
				.groupBy(qCostMetalDtItem.purity.metal).list(qCostMetalDtItem.purity,qCostMetalDtItem.purity.metal.name,qCostMetalDtItem.metalWeight.sum(),qCostMetalDtItem.metalPcs.sum());
		
		List<LabourCharge> labourCharges=null;
		
		for(Tuple tuple : costMetalItemList){
			
			Double vMetalWt=Math.round((tuple.get(qCostMetalDtItem.metalWeight.sum())/costingDtItem.getPcs())*1000.0)/1000.0;
			
			
			
			
			 labourCharges =labourChargeService.getPurityLabourRates(costingDtItem.getCostingMt().getParty(), costingDtItem.getDesign().getCategory(),
					vMetalWt,false, metal,labourType,tuple.get(qCostMetalDtItem.purity));
			 
			 if(labourCharges.size()<=0){
				 
				 labourCharges =labourChargeService.getLabourRates(costingDtItem.getCostingMt().getParty(), costingDtItem.getDesign().getCategory(),
							vMetalWt,false, metal,labourType);
				 
			 }
			
		}
		
		if(labourCharges.size()>0){
			LabourCharge labourCharge = labourCharges.get(0);
			
			CostLabDtItem costLabDtItem=null;
			
			if(costLabDtItemId==null){
				 costLabDtItem = new CostLabDtItem();
				
				costLabDtItem.setMetal(metal);
				costLabDtItem.setLabourType(labourType);
				
				costLabDtItem.setLabourRate(labourCharge.getRate());

				if(labourCharge.getPerPcsRate() == true){
					costLabDtItem.setPerPcRate(true);
				}else if(labourCharge.getPerGramRate() == true){
					costLabDtItem.setPerGramRate(true);
				}else if(labourCharge.getPercentage() == true){
					costLabDtItem.setPercentage(true);
				}
				else if(labourCharge.getPerCaratRate() == true){
					costLabDtItem.setPerCaratRate(true);
				}
				
				
				
			}else{
				
				 costLabDtItem = costLabDtItemService.findOne(costLabDtItemId);
				costLabDtItem.setMetal(metal);
				costLabDtItem.setLabourType(labourType);
				
				costLabDtItem.setLabourRate(labourCharge.getRate());
				costLabDtItem.setPerPcRate(false);
				costLabDtItem.setPerGramRate(false);
				costLabDtItem.setPercentage(false);
				costLabDtItem.setPerCaratRate(false);


				if(labourCharge.getPerPcsRate() == true){
					costLabDtItem.setPerPcRate(true);
				}else if(labourCharge.getPerGramRate() == true){
					costLabDtItem.setPerGramRate(true);
				}else if(labourCharge.getPercentage() == true){
					costLabDtItem.setPercentage(true);
				}
				else if(labourCharge.getPerCaratRate() == true){
					costLabDtItem.setPerCaratRate(true);
				}
				
				
				
				
			}
			
			
			JSONObject jsonObject = new JSONObject();
			
			jsonObject.put("labourRate", costLabDtItem.getLabourRate() );
			jsonObject.put("perPcRate", costLabDtItem.getPerPcRate());
			jsonObject.put("perGramRate", costLabDtItem.getPerGramRate());
			jsonObject.put("percentage", costLabDtItem.getPercentage());
			jsonObject.put("perCaratRate", costLabDtItem.getPerCaratRate());
			
		
			
			return jsonObject.toString();
			
			
			
		}else{
			
			
			return null;
		}
		
		
		
		
		
		
		
	}
	
}
