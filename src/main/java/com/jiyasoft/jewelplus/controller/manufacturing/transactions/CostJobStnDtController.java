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
//import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingCharge;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingType;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostJobStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingJobDt;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
//import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingChargeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostJobStnDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingJobDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingJobMtService;


@RequestMapping("/manufacturing/transactions")
@Controller
public class CostJobStnDtController {

	
	@Autowired
	private ICostJobStnDtService costJobStnDtService;
	
	@Autowired
	private ICostingJobDtService costingJobDtService;
	
	@Autowired
	private ISettingService settingService;
	
	@Autowired
	private ISettingTypeService settingTypeService;
	
	@Autowired
	private ICostingJobMtService costingJobMtService;
	
	@Autowired
	private IPartyService partyService;
	
	/*@Autowired
	private ISettingChargeService settingChargeService;*/
	 
	@RequestMapping("/costJobStnDt/listing")
	@ResponseBody
	public String costStnListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "costJobDtId", required = false) Integer costJobDtId) {

		StringBuilder sb = new StringBuilder();
		
		
		
		sb.append("{\"total\":").append(costJobStnDtService.count()).append(",\"rows\": [");
		
		CostingJobDt costingJobDt = costingJobDtService.findOne(costJobDtId);
		List<CostJobStnDt> costStnJobDts = costJobStnDtService.findByCostingJobDtAndDeactive(costingJobDt, false);
		
		
		
		if(costStnJobDts.size() > 0){
			
			for(CostJobStnDt costJobStnDt:costStnJobDts){
				
			sb.append("{\"id\":\"")
				.append(costJobStnDt.getId())
				.append("\",\"stoneType\":\"")
				.append((costJobStnDt.getStoneType() != null ? costJobStnDt.getStoneType().getName() : ""))
				.append("\",\"shape\":\"")
				.append((costJobStnDt.getShape() != null ? costJobStnDt.getShape().getName() : ""))
				.append("\",\"quality\":\"")
				.append((costJobStnDt.getQuality() != null ? costJobStnDt.getQuality().getName() : ""))
				.append("\",\"size\":\"")
				.append((costJobStnDt.getSize() != null ? costJobStnDt.getSize() : ""))
				.append("\",\"sizeGroup\":\"")
				.append((costJobStnDt.getSizeGroup() != null ? costJobStnDt.getSizeGroup().getName() : ""))
				.append("\",\"stone\":\"")
				.append((costJobStnDt.getStone() != null ? costJobStnDt.getStone() : ""))
				.append("\",\"carat\":\"")
				.append((costJobStnDt.getCarat() != null ? costJobStnDt.getCarat() : ""))
				.append("\",\"rate\":\"")
				.append((costJobStnDt.getStnRate() != null ? costJobStnDt.getStnRate() : ""))
				.append("\",\"stoneValue\":\"")
				.append((costJobStnDt.getStoneValue() != null ? costJobStnDt.getStoneValue() : ""))
				.append("\",\"handlingRate\":\"")
				.append((costJobStnDt.getHandlingRate() != null ? costJobStnDt.getHandlingRate() : ""))
				.append("\",\"handlingValue\":\"")
				.append((costJobStnDt.getHandlingValue() != null ? costJobStnDt.getHandlingValue() : ""))
				.append("\",\"setting\":\"")
				.append((costJobStnDt.getSetting() != null ? costJobStnDt.getSetting().getName() : ""))
				.append("\",\"settingType\":\"")
				.append((costJobStnDt.getSettingType() != null ? costJobStnDt.getSettingType().getName() : ""))
				.append("\",\"settingRate\":\"")
				.append((costJobStnDt.getSetRate() != null ? costJobStnDt.getSetRate() : ""))
				.append("\",\"settingValue\":\"")
				.append((costJobStnDt.getSetValue() != null ? costJobStnDt.getSetValue() : ""))
				.append("\",\"rLock\":\"")
				.append((costJobStnDt.getrLock() == null ? "": (costJobStnDt.getrLock() ? "Lock": "Unlock"))) //1 = lock & 0 = unlock
				.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doStoneJobDtLockUnLock(")
				.append(costJobStnDt.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				.append("\",\"action1\":\"")
				.append("<a href='javascript:editCostJobStnDt(")
				.append(costJobStnDt.getId())
				.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a  href='javascript:deleteCostJobStnDt(event,")
				.append(costJobStnDt.getId())
				.append(");'  class='btn btn-xs btn-danger triggerRemove")
				.append(costJobStnDt.getId())
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
	@RequestMapping("/costJobStnDt/saveEdit")
	public String addEditCostStnDt(
			@RequestParam(value="costJobStnDtId") Integer costJobStnDtId,
			@RequestParam(value="settingId") Integer settingId,
			@RequestParam(value="setTypeId") Integer setTypeId,
			@RequestParam(value="stoneRate") Double stoneRate,
			@RequestParam(value="handRate") Double handRate,
			@RequestParam(value="setRate") Double setRate,Principal principal){
		
		String retVal = "-1";
		
		Setting setting = settingService.findOne(settingId);
		SettingType settingType = settingTypeService.findOne(setTypeId);
		
		CostJobStnDt costJobStnDt = costJobStnDtService.findOne(costJobStnDtId);
		costJobStnDt.setSetting(setting);
		costJobStnDt.setSettingType(settingType);
		costJobStnDt.setStnRate(stoneRate);
		costJobStnDt.setHandlingRate(handRate);
		costJobStnDt.setSetRate(setRate);
		costJobStnDt.setModiBy(principal.getName());
		costJobStnDt.setModiDate(new java.util.Date());
		
		costJobStnDtService.save(costJobStnDt);
		
		Party party = partyService.findByDefaultFlag(true);
		String tempApplyRate = costingJobDtService.applyRate(costJobStnDt.getCostingJobDt(), party);
		String tempUpdateFob = costingJobDtService.updateFob(costJobStnDt.getCostingJobDt());

		return retVal+"_"+tempApplyRate+"_"+tempUpdateFob;
	}
	
	
	
	@ResponseBody
	@RequestMapping("/costJobStnDt/lockUnlock")
	public String lockUnlockCostStnDt(
			@RequestParam(value="stnJobDtId")Integer stnJobDtId){
		
		String retVal = "-1";
		CostJobStnDt costJobStnDt = costJobStnDtService.findOne(stnJobDtId);
		
			if(costJobStnDt.getrLock() == true){
				costJobStnDt.setrLock(false);
			}else{
				costJobStnDt.setrLock(true);
			}
			
			costJobStnDtService.save(costJobStnDt);
		
		return retVal;
	}
	
	
	
	@RequestMapping("/costJobStnDt/validationEdit")
	@ResponseBody
	public String validation(
			@RequestParam(value = "stnId")Integer stnId){
		
		String retVal = "sucess";
		CostJobStnDt costJobStnDt = costJobStnDtService.findOne(stnId);
		
		if(costJobStnDt.getrLock() == true){
			retVal = "-1";
		}
		
		return retVal;
	}
	
	
	
	
	@RequestMapping("/costJobStnDt/edit/{id}")
	public String editcostStnDtt(@PathVariable int id,Model model){
		
		CostJobStnDt costJobStnDt = costJobStnDtService.findOne(id);
		model.addAttribute("costJobStnDt", costJobStnDt);
		model.addAttribute("settingMap",settingService.getSettingList());
		model.addAttribute("settingTypeMap",settingTypeService.getSettingTypeList());
		return "costJobStnDt/add";
	}
	
	
	@ResponseBody
	@RequestMapping("/costJobStnDt/delete/{id}")
	public String delete(@PathVariable int id){
		
		CostJobStnDt costJobStnDt = costJobStnDtService.findOne(id);
		
		String retVal = "-1";
		
		costJobStnDtService.delete(id);
			Party party = partyService.findByDefaultFlag(true);
			String vApplyRate = costingJobDtService.applyRate(costJobStnDt.getCostingJobDt(), party);
			String vUpdateFob = costingJobDtService.updateFob(costJobStnDt.getCostingJobDt());
			retVal = retVal+"_"+vApplyRate+"_"+vUpdateFob;
		
		
		return retVal;
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("/costJobStnDt/rateFromMaster")
	public String calSetRate(
			@RequestParam(value="setId") Integer setId,
			@RequestParam(value="setTypeId") Integer setTypeId,
			@RequestParam(value="costJobStnDtId") Integer costJobStnDtId){
		
		
		String retVal = "-1";
		
		Setting 	setting 	=  settingService.findOne(setId);
		SettingType settingType =  settingTypeService.findOne(setTypeId);
		CostJobStnDt 	costJobStnDt	=  costJobStnDtService.findOne(costJobStnDtId);
		Party partyDef = partyService.findByDefaultFlag(true);
		
		//SettingCharge settingCharge = settingChargeService.findByStoneTypeAndShapeAndPartyAndSettingAndSettingTypeAndDeactive(costJobStnDt.getStoneType(), costJobStnDt.getShape(),partyDef, setting, settingType, false);
		
		/*if(settingCharge != null){
			retVal = settingCharge.getRate()+"";
		}*/
		
		return retVal;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/*@ResponseBody
	@RequestMapping("/costJobStnDt/stnLockUnlockAll")
	public String lockUnlockAll(
			@RequestParam(value="mtId")Integer mtId,
			@RequestParam(value="status")Integer status){
		
		String retVal = "-1";
		CostingJobMt costingJobMt = costingJobMtService.findOne(mtId);
		List<CostJobStnDt> costStnJobDts = costStnJobDtService.findByCostingJobMtAndDeactive(costingJobMt, false);
		Boolean setValue ;
		
		if(status == 1){
			setValue = true;
		}else{
			setValue = false;
		}
		
		for(CostJobStnDt costJobStnDt:costStnJobDts){
			costJobStnDt.setrLock(setValue);
			costStnJobDtService.save(costJobStnDt);
		}
		
		return retVal;
	}*/
	
	
}