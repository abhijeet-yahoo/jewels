package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Setting;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingType;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostStnDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class CostStnDtController {
	
	
	@Autowired
	private ICostStnDtService costStnDtService;
	
	@Autowired
	private ICostingDtService costingDtService;
	
	@Autowired
	private ISettingService settingService;
	
	@Autowired
	private ISettingTypeService settingTypeService;
	
	@Autowired
	private ICostingMtService costingMtService;
	
	@Autowired
	private IPartyService partyService;
	
/*	@Autowired
	private ISettingChargeService settingChargeService;*/
	

	@RequestMapping("/costStnDt/listing")
	@ResponseBody
	public String costStnListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "costDtId", required = false) Integer costDtId) {

		StringBuilder sb = new StringBuilder();
		
		
		
		sb.append("{\"total\":").append(costStnDtService.count())
		.append(",\"rows\": [");
		
		CostingDt costingDt = costingDtService.findOne(costDtId);
		List<CostStnDt> costStnDts = costStnDtService.findByCostingDtAndDeactive(costingDt, false);
		
		
		
		if(costStnDts.size() > 0){
			for(CostStnDt costStnDt:costStnDts){
				
			sb.append("{\"id\":\"")
				.append(costStnDt.getId())
				.append("\",\"stoneType\":\"")
				.append((costStnDt.getStoneType() != null ? costStnDt.getStoneType().getName() : ""))
				.append("\",\"partNm\":\"")
				.append((costStnDt.getPartNm() != null ? costStnDt.getPartNm().getFieldValue() : ""))
				.append("\",\"shape\":\"")
				.append((costStnDt.getShape() != null ? costStnDt.getShape().getName() : ""))
				.append("\",\"quality\":\"")
				.append((costStnDt.getQuality() != null ? costStnDt.getQuality().getName() : ""))
				.append("\",\"size\":\"")
				.append((costStnDt.getSize() != null ? costStnDt.getSize() : ""))
				.append("\",\"sieve\":\"")
				.append((costStnDt.getSieve() != null ? costStnDt.getSieve() : ""))
				.append("\",\"sizeGroup\":\"")
				.append((costStnDt.getSizeGroup() != null ? costStnDt.getSizeGroup().getName() : ""))
				.append("\",\"stone\":\"")
				.append((costStnDt.getStone() != null ? costStnDt.getStone() : ""))
				.append("\",\"carat\":\"")
				.append((costStnDt.getCarat() != null ? costStnDt.getCarat() : ""))
				.append("\",\"rate\":\"")
				.append((costStnDt.getStnRate() != null ? costStnDt.getStnRate() : ""))
				.append("\",\"stoneValue\":\"")
				.append((costStnDt.getStoneValue() != null ? costStnDt.getStoneValue() : ""))
				.append("\",\"handlingRate\":\"")
				.append((costStnDt.getHandlingRate() != null ? costStnDt.getHandlingRate() : ""))
				.append("\",\"handlingValue\":\"")
				.append((costStnDt.getHandlingValue() != null ? costStnDt.getHandlingValue() : ""))
				.append("\",\"setting\":\"")
				.append((costStnDt.getSetting() != null ? costStnDt.getSetting().getName() : ""))
				.append("\",\"settingType\":\"")
				.append((costStnDt.getSettingType() != null ? costStnDt.getSettingType().getName() : ""))
				.append("\",\"settingRate\":\"")
				.append((costStnDt.getSetRate() != null ? costStnDt.getSetRate() : ""))
				.append("\",\"settingValue\":\"")
				.append((costStnDt.getSetValue() != null ? costStnDt.getSetValue() : ""))
				.append("\",\"centerStone\":\"")
				.append(costStnDt.getCenterStone())
				.append("\",\"rLock\":\"")
				.append((costStnDt.getrLock())) //1 = lock & 0 = unlock
				.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doStoneDtLockUnLock(")
				.append(costStnDt.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				.append("\",\"action1\":\"")
				.append("<a href='javascript:editCostStnDt(")
				.append(costStnDt.getId())
				.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a  href='javascript:deleteCostStnDt(event,")
				.append(costStnDt.getId())
				.append(");'  class='btn btn-xs btn-danger triggerRemove")
				.append(costStnDt.getId())
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
	@RequestMapping("/costStnDt/saveEdit")
	public String addEditCostStnDt(
			@RequestParam(value="costStnDtId") Integer costStnDtId,
			@RequestParam(value="settingId", required=false) Integer settingId,
			@RequestParam(value="setTypeId", required=false) Integer setTypeId,
			@RequestParam(value="stoneRate", required=false) Double stoneRate,
			@RequestParam(value="handRate", required=false) Double handRate,
			@RequestParam(value="centerStone", required=false) String centerStone,
			@RequestParam(value="setRate", required=false ) Double setRate,Principal principal,
			@RequestParam(value="costingType", required=false) String costingType){
		
		
		String retVal = "-1";
		
		
		Setting setting = settingService.findOne(settingId);
		SettingType settingType = settingTypeService.findOne(setTypeId);
		
		CostStnDt costStnDt = costStnDtService.findOne(costStnDtId);
		
		if(costingType != null && costingType.equalsIgnoreCase("merge")){
			costStnDt.setSetting(setting);
			costStnDt.setSettingType(settingType);	
			costStnDt.setModiBy(principal.getName());
			costStnDt.setModiDate(new java.util.Date());
			retVal = "-2";
			costStnDtService.save(costStnDt);
		}else{
		
		costStnDt.setSetting(setting);
		costStnDt.setSettingType(settingType);
		costStnDt.setStnRate(stoneRate);
		costStnDt.setHandlingRate(handRate);
		costStnDt.setSetRate(setRate);
		costStnDt.setCenterStone(Boolean.parseBoolean(centerStone) );
		costStnDt.setModiBy(principal.getName());
		costStnDt.setModiDate(new java.util.Date());
		retVal = "-2";
		costStnDtService.save(costStnDt);
		
	//	String tempApplyRate = costingDtService.applyRate(costStnDt.getCostingDt(), principal);
		 costingDtService.updateFob(costStnDt.getCostingDt());
		}
		return retVal;
		
	}
	
	
	
	@ResponseBody
	@RequestMapping("/costStnDt/lockUnlock")
	public String lockUnlockCostStnDt(
			@RequestParam(value="stnDtId")Integer stnDtId){
		
		String retVal = "-1";
		CostStnDt costStnDt = costStnDtService.findOne(stnDtId);
		
		
		if(costStnDt.getCostingMt().getExpClose() == true){
			return retVal = "-2";
		}
		
			if(costStnDt.getrLock() == true){
				costStnDt.setrLock(false);
			}else{
				costStnDt.setrLock(true);
			}
			
		costStnDtService.save(costStnDt);
		
		return retVal;
	}
	
	
	
	@RequestMapping("/costStnDt/validationEdit")
	@ResponseBody
	public String validation(
			@RequestParam(value = "stnId")Integer stnId){
		
		String retVal = "sucess";
		CostStnDt costStnDt = costStnDtService.findOne(stnId);
		
		if(costStnDt.getCostingMt().getExpClose() == true){
			return retVal = "-2";
		}
		
		if(costStnDt.getrLock() == true){
			retVal = "-1";
		}
		
		return retVal;
	}
	
	
	
	
	@RequestMapping("/costStnDt/edit/{id}")
	public String editcostStnDtt(@PathVariable int id,Model model,
			@RequestParam(value="costingType", required=false) String costingType){
		
		CostStnDt costStnDt = costStnDtService.findOne(id);
		model.addAttribute("costStnDt", costStnDt);
		model.addAttribute("settingMap",settingService.getSettingList());
		model.addAttribute("settingTypeMap",settingTypeService.getSettingTypeList());
		if(costingType != null && costingType.equalsIgnoreCase("merge")){
			model.addAttribute("costingType", "merge");
		}
		
		return "costStnDt/add";
	}
	
	
	@ResponseBody
	@RequestMapping("/costStnDt/delete/{id}")
	public String delete(@PathVariable int id, Principal principal ){
		
		CostStnDt costStnDt = costStnDtService.findOne(id);
		
		String retVal = "-1";
		
		if(costStnDt.getCostingMt().getExpClose() == true){
			retVal = "-2";
		}else{
			costStnDtService.delete(id);
			costingDtService.updateFob(costStnDt.getCostingDt());
			
		}
		
		return retVal;
	}
	
	
	@ResponseBody
	@RequestMapping("/costStnDt/stnLockUnlockAll")
	public String lockUnlockAll(
			@RequestParam(value="mtId")Integer mtId,
			@RequestParam(value="status")Integer status){
		
		String retVal = "-1";
		CostingMt costingMt = costingMtService.findOne(mtId);
		List<CostStnDt> costStnDts = costStnDtService.findByCostingMtAndDeactive(costingMt, false);
		Boolean setValue ;
		
		if(status == 1){
			setValue = true;
		}else{
			setValue = false;
		}
		
		for(CostStnDt costStnDt:costStnDts){
			costStnDt.setrLock(setValue);
			costStnDtService.save(costStnDt);
		}
		
		return retVal;
	}
	
	
	@ResponseBody
	@RequestMapping("/costStnDt/rateFromMaster")
	public String calSetRate(
			@RequestParam(value="setId") Integer setId,
			@RequestParam(value="setTypeId") Integer setTypeId,
			@RequestParam(value="costStnDtId") Integer costStnDtId){
		
		
		String retVal = "-1";
		
		Setting 	setting 	=  settingService.findOne(setId);
		SettingType settingType =  settingTypeService.findOne(setTypeId);
		CostStnDt 	costStnDt	=  costStnDtService.findOne(costStnDtId);
		Party partyDef = partyService.findByDefaultFlag(true);
		
		//SettingCharge settingCharge = settingChargeService.findByStoneTypeAndShapeAndPartyAndSettingAndSettingTypeAndDeactive(costStnDt.getStoneType(), costStnDt.getShape(),partyDef, setting, settingType, false);
		
		/*if(settingCharge != null){
			retVal = settingCharge.getRate()+"";
		}*/
		
		return retVal;
	}
	
	

}
