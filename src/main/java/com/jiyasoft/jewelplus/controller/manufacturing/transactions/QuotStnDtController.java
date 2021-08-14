package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignStone;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.HandlingMasterFl;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Setting;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingCharge;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingQualityRate;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneRateMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotStnDt;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignStoneService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IHandlingMasterFLService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILookUpMastService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IQualityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingChargeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingQualityRateService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISizeGroupService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneChartService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneRateMastService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISubShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotStnDtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class QuotStnDtController {
	
	@Autowired
	private IQuotStnDtService quotStnDtService;
	
	@Autowired
	private IQuotDtService quotDtService;
	
	@Autowired
	private IDesignStoneService designStoneService;
	
	@Autowired
	private ISettingService settingService;
	
	@Autowired
	private ISettingTypeService settingTypeService;
	
	@Autowired
	private IQuotMtService quotMtService;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private IShapeService shapeService;
	
	@Autowired
	private IQualityService qualityService;
	
	@Autowired
	private IStoneRateMastService stoneRateMastService;
	
	@Autowired
	private IStoneChartService stoneChartService;
	
	@Autowired
	private IStoneTypeService stoneTypeService;
	
	@Autowired
	private ISizeGroupService sizeGroupService;
	
	
	@Autowired
	private ILookUpMastService lookUpMastService;
	
	@Autowired
	private IQuotMetalService quotMetalService;
	
	@Autowired
	private IHandlingMasterFLService handlingMasterFLService;
	
	@Autowired
	private ISettingChargeService settingChargeService;
	
	@Autowired
	private ISettingQualityRateService settingQualityRateService;
	
	@Autowired
	private ISubShapeService subShapeService;

	
	@Value("${netWtWithComp}")
	private Boolean netWtWithCompFlg;
	
	@ModelAttribute("quotStnDt")
	public QuotStnDt constructStnDt() {
		return new QuotStnDt();
	}
	
	
	
	
	
	@RequestMapping("/quotStnDt/updateStoneAsPerDesign")
	@ResponseBody
	public String updateStoneAsPerDesign(@RequestParam(value = "quotDtId", required = false) Integer quotDtId,Principal principal) {
		
		QuotDt quotDt = quotDtService.findOne(quotDtId);
		List<QuotStnDt> quotStnDts = quotStnDtService.findByQuotDtAndDeactive(quotDt, false);
		for(QuotStnDt quotStnDt :quotStnDts){
			quotStnDtService.delete(quotStnDt.getId());
		}
		
		
		List<DesignStone>designStones =designStoneService.findByDesign(quotDt.getDesign());
		
		quotStnDtService.setQuotStnDt(designStones, quotDt.getQuotMt(), quotDt, principal);
		quotDtService.updateQltyDesc(quotDt.getId());
		
		
		return "1";
	}

	
	
	@RequestMapping("/quotStnDt/listing")
	@ResponseBody
	public String costStnListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "quotDtId", required = false) Integer quotDtId,Principal principal,
			@RequestParam(value = "canViewFlag", required = false) Boolean canViewFlag) {
		
		StringBuilder sb = new StringBuilder();
		QuotDt quotDt = quotDtService.findOne(quotDtId);
		List<QuotStnDt> quotStnDts = quotStnDtService.findByQuotDtAndDeactive(quotDt, false);
		
		
		sb.append("{\"total\":").append(quotStnDts.size())
		.append(",\"rows\": [");
		
		for(QuotStnDt quotStnDt:quotStnDts){
				
			sb.append("{\"id\":\"")
				.append(quotStnDt.getId())
				.append("\",\"partNm\":\"")
				.append((quotStnDt.getPartNm() != null ? quotStnDt.getPartNm().getFieldValue() : ""))
				.append("\",\"stoneType\":\"")
				.append((quotStnDt.getStoneType() != null ? quotStnDt.getStoneType().getName() : ""))
				.append("\",\"shape\":\"")
				.append((quotStnDt.getShape() != null ? quotStnDt.getShape().getName() : ""))
				.append("\",\"quality\":\"")
				.append((quotStnDt.getQuality() != null ? quotStnDt.getQuality().getName() : ""))
				.append("\",\"diaCateg\":\"")
				.append((quotStnDt.getDiaCateg() != null ? quotStnDt.getDiaCateg() : ""))
				.append("\",\"size\":\"")
				.append((quotStnDt.getSize() != null ? quotStnDt.getSize() : ""))
				.append("\",\"sieve\":\"")
				.append((quotStnDt.getSieve() != null ? quotStnDt.getSieve() : ""))
				.append("\",\"sizeGroup\":\"")
				.append((quotStnDt.getSizeGroup() != null ? quotStnDt.getSizeGroup().getName() : ""))
				.append("\",\"stone\":\"")
				.append((quotStnDt.getStone() != null ? quotStnDt.getStone() : ""))
				.append("\",\"carat\":\"")
				.append((quotStnDt.getCarat() != null ? quotStnDt.getCarat() : ""))
				.append("\",\"rate\":\"")
				.append((quotStnDt.getStnRate() != null ? quotStnDt.getStnRate() : ""))
				.append("\",\"stoneValue\":\"")
				.append((quotStnDt.getStoneValue() != null ? quotStnDt.getStoneValue() : ""))
				.append("\",\"handlingRate\":\"")
				.append((quotStnDt.getHandlingRate() != null ? quotStnDt.getHandlingRate() : ""))
				.append("\",\"handlingValue\":\"")
				.append((quotStnDt.getHandlingValue() != null ? quotStnDt.getHandlingValue() : ""))
				.append("\",\"setting\":\"")
				.append((quotStnDt.getSetting() != null ? quotStnDt.getSetting().getName() : ""))
				.append("\",\"settingType\":\"")
				.append((quotStnDt.getSettingType() != null ? quotStnDt.getSettingType().getName() : ""))
				.append("\",\"settingRate\":\"")
				.append((quotStnDt.getSetRate() != null ? quotStnDt.getSetRate() : ""))
				.append("\",\"settingValue\":\"")
				.append((quotStnDt.getSetValue() != null ? quotStnDt.getSetValue() : ""))
				.append("\",\"rLock\":\"")
				.append(quotStnDt.getrLock()); //1 = lock & 0 = unlock
				
			if(!canViewFlag){
				
			sb.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doStoneDtLockUnLock(")
				.append(quotStnDt.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>")
				.append("\",\"action1\":\"")
				.append("<a href='javascript:editQuotStnDt(")
				.append(quotStnDt.getId())
				.append(");' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a href='javascript:deleteQuotStnDt(event,")
				.append(quotStnDt.getId())
				.append(");' class='btn btn-xs btn-danger triggerRemove")
				.append(quotStnDt.getId())
				.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
				}else{
					sb.append("\",\"action1\":\"")
					.append("")
					.append("\",\"action2\":\"")
					.append("")
					.append("\",\"actionLock\":\"")
					.append("");
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
	@RequestMapping(value ="/quotStnDt/addEdit", method=RequestMethod.POST)
	public String addEditQuotStnDt(
			@ModelAttribute("quotStnDt") QuotStnDt quotStnDt,
			@RequestParam("id") Integer id,
			@RequestParam("pQuotMtId") Integer quotMtId,
			@RequestParam("pQuotDtId") Integer quotDtId,
			@RequestParam("pSieve") String pSieve,
			@RequestParam("pSizeGroup") String pSizeGroup,
			Principal principal){
		
		String retVal = "";
		
		try {
			retVal = quotStnDtService.transactionalSave(quotStnDt, id, quotMtId, quotDtId, pSieve, pSizeGroup, principal,netWtWithCompFlg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return retVal;
		
		
	}
	
	
	
	
	
	@RequestMapping("/add/quotStnDt")
	public String addQuotStnDtPage(Model model,@RequestParam(value = "quotDtId") Integer quotDtId){
		
		QuotDt quotDt = quotDtService.findOne(quotDtId);
		if(quotDt != null){
			
			List<QuotMetal> quotMetals = quotMetalService.findByQuotDtAndDeactive(quotDt,false);
			
			Map<Integer, String> map = new HashMap<Integer, String>();
			for(QuotMetal quotMetal:quotMetals){
				map.put(quotMetal.getPartNm().getId(), quotMetal.getPartNm().getFieldValue());
			}
			model.addAttribute("lookUpMastStoneMap", map);
			
		}
		
		model.addAttribute("partMap", lookUpMastService.getActiveLookUpMastByType("Design Part"));
		model.addAttribute("settingMap",settingService.getSettingList());
		model.addAttribute("settingTypeMap",settingTypeService.getSettingTypeList());
		model.addAttribute("shapeMap", shapeService.getShapeList());
		model.addAttribute("stoneTypeMap", stoneTypeService.getStoneTypeList());
		return "quotStnDt/add";
		
		
		
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("/quotStnDt/lockUnlock")
	public String lockUnlockQuotStnDt(
			@RequestParam(value="stnDtId")Integer stnDtId){
		
		String retVal = "-1";
		QuotStnDt quotStnDt = quotStnDtService.findOne(stnDtId);
		
			if(quotStnDt.getrLock() == true){
				quotStnDt.setrLock(false);
			}else{
				quotStnDt.setrLock(true);
			}
			
		quotStnDtService.save(quotStnDt);
		
		return retVal;
	}
	
	
	
	@RequestMapping("/quotStnDt/validationEdit")
	@ResponseBody
	public String validation(
			@RequestParam(value = "stnId")Integer stnId){
		
		String retVal = "";
		QuotStnDt quotStnDt = quotStnDtService.findOne(stnId);
		if(quotStnDt.getrLock() == true){
			retVal = "-1";
		}
		
		return retVal;
	}
	
	
	
	@RequestMapping("/quotStnDt/edit/{id}")
	public String editquotStnDtt(@PathVariable int id,Model model){
		
		QuotStnDt quotStnDt = quotStnDtService.findOne(id);
		model.addAttribute("quotStnDt", quotStnDt);
		model.addAttribute("partMap", lookUpMastService.getActiveLookUpMastByType("Design Part"));
		model.addAttribute("stoneTypeMap",stoneTypeService.getStoneTypeList());
		model.addAttribute("shapeMap",shapeService.getShapeList());
		model.addAttribute("settingMap",settingService.getSettingList());
		model.addAttribute("settingTypeMap",settingTypeService.getSettingTypeList());
		
		if (quotStnDt != null) {
			Shape shape = quotStnDt.getShape();
			model.addAttribute("subShapeMap", subShapeService.getSubShapeList(shape.getId()));
			model.addAttribute("qualityMap",qualityService.getQualityList(shape.getId()));
			model.addAttribute("chartMap", stoneChartService.getStoneChartList(shape.getId()));
			model.addAttribute("sizeGroupMap", sizeGroupService.getSizeGroupList(shape.getId()));
			
			List<QuotMetal> quotMetals = quotMetalService.findByQuotDtAndDeactive(quotStnDt.getQuotDt(), false);
			
			Map<Integer, String> map = new HashMap<Integer, String>();
			for(QuotMetal quotMetal:quotMetals){
				map.put(quotMetal.getPartNm().getId(), quotMetal.getPartNm().getFieldValue());
			}
			model.addAttribute("lookUpMastStoneMap", map);
			
		}
		
		if(quotStnDt.getSizeGroup() != null) {
			model.addAttribute("sizeGroupName", sizeGroupService.findOne(quotStnDt.getSizeGroup().getId()).getName());
			model.addAttribute("sieve", quotStnDt.getSieve());	
		} 
		
		return "quotStnDt/add";
	}
	
	
	@ResponseBody
	@RequestMapping("/quotStnDt/delete/{id}")
	public String delete(@PathVariable int id,Principal principal){
		
		String retVal = "-1";
		QuotStnDt quotStnDt = quotStnDtService.findOne(id);
		try {
			quotStnDtService.transactionDelete(quotStnDt,netWtWithCompFlg);
		} catch (Exception e) {
			retVal = "-2";
			e.printStackTrace();
		}	
	
		return retVal;
	}
	
	
	
	@ResponseBody
	@RequestMapping("/quotStnDt/stnLockUnlockAll")
	public String lockUnlockAll(
			@RequestParam(value="mtId")Integer mtId,
			@RequestParam(value="status")Integer status){
		
		String retVal = "-1";
		QuotMt quotMt = quotMtService.findOne(mtId);
		List<QuotStnDt> quotStnDts = quotStnDtService.findByQuotMtAndDeactive(quotMt, false);
		Boolean setValue ;
		
		if(status == 1){
			setValue = true;
		}else{
			setValue = false;
		}
		
		for(QuotStnDt quotStnDt:quotStnDts){
			quotStnDt.setrLock(setValue);
			quotStnDtService.save(quotStnDt);
		}
		
		return retVal;
	}
	
	

	
	
	//-------stone rate--------//
	@ResponseBody
	@RequestMapping("/quotStnDt/stoneRateFromMaster")
	public String stoneRate(
			@RequestParam(value="qualityId") Integer qualityId,
			@RequestParam(value="sizeGroupStr") String sizeGroupStr,
			@RequestParam(value="shapeId") Integer shapeId,
			@RequestParam(value="mtPartyId") Integer partyId,
			@RequestParam(value="stoneTypeId") Integer stoneTypeId,
			@RequestParam(value="sieve") String sieve){
		
		String retVal = "0.0";
		
		Shape shape = shapeService.findOne(shapeId);
		SizeGroup sizeGroup = sizeGroupService.findByShapeAndNameAndDeactive(shape, sizeGroupStr, false);
		
		List<StoneRateMast> stoneRateMast=stoneRateMastService.getStoneRate(stoneTypeId,shapeId,qualityId,sizeGroup.getId(),partyId,sieve);
		
		if(stoneRateMast.size() > 0){
			if(stoneRateMast.get(0).getPerPcRate() > 0){
				retVal = stoneRateMast.get(0).getPerPcRate().toString();
			}else{
				retVal = stoneRateMast.get(0).getStoneRate().toString();
			}
		}
		
		return retVal;
	}
	
	
	//--------handling rate-------//
	@ResponseBody
	@RequestMapping("/quotStnDt/handlingRateFromMaster")
	public String handlingRateMast(
			@RequestParam(value="quotDtId") Integer quotDtId,
			@RequestParam(value="partId") Integer partId,
			@RequestParam(value="shapeId") Integer shapeId,
			@RequestParam(value="stoneTypeId") Integer stoneTypeId,
			@RequestParam(value="mtPartyId") Integer partyId,
			@RequestParam(value="stnRate") Double stnRate){
		
		Double retVal = 0.0;
		
		if(stnRate>0){
			QuotDt quotDt = quotDtService.findOne(quotDtId);
			List<HandlingMasterFl> handlingList = handlingMasterFLService.getRates(quotDt.getQuotMt().getParty(),stnRate);
			
			
			if(handlingList.size() > 0){
				if(handlingList.get(0).getPercentage() > 0){
					retVal=Math.round(((stnRate * handlingList.get(0).getPercentage())/100)*100.0)/100.0;
					
				}else{
					
					retVal=handlingList.get(0).getRate();
					
					
				}
			}
			
			
		
		}
		
		
		
		
		return retVal.toString();
	}
	
	
	//--------setting rate-------//
	@ResponseBody
	@RequestMapping("/quotStnDt/settingRateFromMaster")
	public String settingRateMast(
			@RequestParam(value="quotDtId") Integer quotDtId,
			@RequestParam(value="partId") Integer partId,
			@RequestParam(value="shapeId") Integer shapeId,
			@RequestParam(value="stoneTypeId") Integer stoneTypeId,
			@RequestParam(value="settingId") Integer settingId,
			@RequestParam(value="settingTypeId") Integer settingTypeId,
			@RequestParam(value="qualityId") Integer qualityId,
			@RequestParam(value="mtPartyId") Integer partyId,
			@RequestParam(value="stone", required=true) String stone,
			@RequestParam(value="carat", required=true) String carat){
		
		String retVal = "0.0";
		
		QuotDt quotDt 			= quotDtService.findOne(quotDtId);
		LookUpMast lookUpMast 	= lookUpMastService.findOne(partId);
		Shape shape 			= shapeService.findOne(shapeId);
		StoneType stoneType 	= stoneTypeService.findOne(stoneTypeId);
		Party party 			= partyService.findOne(partyId);
		Setting setting			= settingService.findOne(settingId);
		SettingType settingType = settingTypeService.findOne(settingTypeId);
		Quality quality			= qualityService.findOne(qualityId);
		QuotMetal quotMetal 	= quotMetalService.findByQuotDtAndDeactiveAndPartNm(quotDt,false,lookUpMast);
		
		Integer stoneVal = Integer.parseInt(stone);
		Double  caratVal = Double.parseDouble(carat);
		
		
		if(quotMetal != null){
			
			Double pointerWt =Math.round((caratVal/stoneVal)*1000.0)/1000.0 ;
			
			List<SettingCharge> settingChargeList = settingChargeService.getRates(party,pointerWt,
					false,quotMetal.getPurity().getMetal(),stoneType,shape,setting,settingType);
			
			SettingCharge settingCharge=null;
			
			if(settingChargeList.size()>0){
				settingCharge = settingChargeList.get(0);
			}
			
								
			if(settingCharge != null){
				
				if(settingCharge.getQualityWiseRate().equals(true)){
					
					List<SettingQualityRate>settingQualityRates=settingQualityRateService.findBySettingChargeAndDeactive(settingCharge, false);
					Boolean isAvailable=false;
					for(SettingQualityRate settingQualityRate:settingQualityRates){
						if(settingQualityRate.getQuality().equals(quality)){
							retVal = settingQualityRate.getQualityRate().toString();
							isAvailable=true;
						}
					}
					
					if(isAvailable.equals(false)){
						retVal = settingCharge.getRate().toString();
					}
					
					
				}else{
					retVal = settingCharge.getRate().toString();
				}
			
			}
		
		
		 }
		
		return retVal;
	}
	
	
	@ResponseBody
	@RequestMapping("/quotStnDt/updateQlty")
	public String updateQlty(
			@RequestParam(value="dtId")Integer dtId,
			@RequestParam(value="shapeId")Integer shapeId,
			@RequestParam(value="qualityId")Integer qualityId){
		
		String retVal = "-1";
		
		if((shapeId !=null && shapeId>0) && (qualityId!=null && qualityId >0)){
		
		QuotDt quotDt = quotDtService.findOne(dtId);
		Quality quality =qualityService.findOne(qualityId);
		
		List<QuotStnDt>quotStnDtList =quotStnDtService.findByQuotDtAndDeactive(quotDt, false);
		for(QuotStnDt quotStnDt : quotStnDtList){
			if(quotStnDt.getShape().getId().equals(shapeId)){
				quotStnDt.setQuality(quality);
				quotStnDtService.save(quotStnDt);
				
			}
		}
		
	
		
		quotDtService.updateQltyDesc(quotDt.getId());
		
		retVal="1";
		}
		
		return retVal;
	}
	
	
	
	
	@RequestMapping("/qualityQuotDt/list")
	@ResponseBody
	public String qualityList(@RequestParam(value = "shapeId") Integer shapeId) {
		return qualityService.getQualityListDropDownForQuot(shapeId);
	}

	

}
