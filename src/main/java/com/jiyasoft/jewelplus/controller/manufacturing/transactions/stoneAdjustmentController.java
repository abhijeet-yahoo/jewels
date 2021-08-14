package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DecimalFormat;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneAdjustment;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneInwardDt;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IQualityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISizeGroupService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneChartService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISubShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneAdjustmentService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneInwardDtService;



@RequestMapping("/manufacturing/transactions")
@Controller
public class stoneAdjustmentController {


	@Autowired
	private IStoneTypeService stoneTypeService;

	@Autowired
	private IShapeService shapeService;
	
	@Autowired
	private IStoneAdjustmentService stoneAdjustmentService;

	@Autowired
	private ISubShapeService subShapeService;

	@Autowired
	private IQualityService qualityService;

	@Autowired
	private IStoneChartService stoneChartService;

	@Autowired
	private ISizeGroupService sizeGroupService;
	
	@Autowired
	private IStoneInwardDtService stoneInwardDtService;
	
	@ModelAttribute("stoneInwardDt")
	public StoneInwardDt constructStoneInwardDt() {
		return new StoneInwardDt();
	}
	
	@ModelAttribute("stoneAdjustment")
	public StoneAdjustment constructstoneAdjustment() {
		return new StoneAdjustment();
	}
	
	
	@RequestMapping("/stoneAdjustment")
	public String stoneAdjustment(Model model){
		model = populateModel(model);
		return "stoneAdjustment";
	}
	
	private Model populateModel(Model model) {
		model.addAttribute("stoneTypeMap", stoneTypeService.getStoneTypeList());
		model.addAttribute("shapeMap", shapeService.getShapeList());
		return model;
	}
	
	
	
	
	@RequestMapping(value ="stoneAdjustment/save", method = RequestMethod.POST)
	@ResponseBody
	public String save(@Valid @ModelAttribute("stoneAdjustment") StoneAdjustment stoneAdjustment,
			BindingResult result, 
			@RequestParam(value = "pODIds") String pOIds,
			@RequestParam(value = "paddCarat") String addCarat,
			@RequestParam(value = "pdedCarat") String dedCarat,
			RedirectAttributes redirectAttributes,Principal principal) {
		
		
		if(result.hasErrors()){
			return "stoneAdjustment";
		}

		String retVal = "";		
		synchronized (this) {
			retVal=stoneAdjustmentService.saveAdj(pOIds, addCarat, dedCarat, principal);	
		}
		
		return retVal;
	
	}
	
	
	
	
	
	@RequestMapping("/stoneAdj/load/stoneInwardDt/data")
	@ResponseBody
	public String stoneBreakupDetails(
			@RequestParam(value = "stoneType", required = false) Integer stoneTypeId,
			@RequestParam(value = "shape", required = false) Integer shapeId,
			@RequestParam(value = "quality", required = false) Integer qualityId,
			@RequestParam(value = "size", required = false) String sizeStr,
			@RequestParam(value = "sizeGroup", required = false) Integer sizeGroupId){
		DecimalFormat df = new DecimalFormat("#.###");
		
		
		StringBuilder sb = new StringBuilder();
		sb.append("{\"total\":").append(stoneInwardDtService.count()).append(",\"rows\": [");
		
		StoneType stoneType = stoneTypeService.findOne(stoneTypeId);
		Shape shape = shapeService.findOne(shapeId);
		Quality quality = qualityService.findOne(qualityId);
		Quality qualityByShape = qualityService.findByShapeAndNameAndDeactive(shape, quality.getName(),false);
		
		
		List<StoneInwardDt> stoneInwardDts = null;
				
		if(sizeStr != "" && sizeGroupId == null){
			stoneInwardDts = stoneInwardDtService.findByStoneTypeAndShapeAndQualityAndSizeAndDeactive(stoneType, shape, qualityByShape, sizeStr,false);
		}else if(sizeGroupId != null && sizeStr == ""){
			SizeGroup sizeGroup = sizeGroupService.findOne(sizeGroupId);
			stoneInwardDts = stoneInwardDtService.findByStoneTypeAndShapeAndQualityAndSizeGroupAndDeactive(stoneType, shape, qualityByShape, sizeGroup,false);
		}else if(sizeStr != null && sizeGroupId != null){
			SizeGroup sizeGroup = sizeGroupService.findOne(sizeGroupId);
			stoneInwardDts = stoneInwardDtService.findByStoneTypeAndShapeAndQualityAndSizeAndSizeGroupAndDeactive(stoneType, shape, qualityByShape, sizeStr, sizeGroup,false);
		}else if(sizeStr == "" && sizeGroupId == null){
			stoneInwardDts = stoneInwardDtService.findByStoneTypeAndShapeAndQualityAndDeactiveOrderByIdAsc(stoneType, shape, qualityByShape,false);
		}
		
		
		Integer trfStone = 0;
		Double trfCarat = 0.0;
		String vSetting = "-";
		String vSettingType = "-";
		String centerStone = "";
		
		for (StoneInwardDt stoneInwardDt : stoneInwardDts) {
			
			sb.append("{\"id\":\"")
			.append(stoneInwardDt.getId())
			.append("\",\"inwardType\":\"")
			.append((stoneInwardDt.getStoneInwardMt().getInwardType() != null ? stoneInwardDt.getStoneInwardMt().getInwardType().getName() : ""))
			.append("\",\"invNo\":\"")
			.append((stoneInwardDt.getStoneInwardMt() != null ? stoneInwardDt.getStoneInwardMt().getInvNo() : ""))
			.append("\",\"invDate\":\"")
			.append((stoneInwardDt.getStoneInwardMt() != null ? stoneInwardDt.getStoneInwardMt().getInvDateStr() : ""))
			.append("\",\"stoneType\":\"")
			.append((stoneInwardDt.getStoneType() != null ? stoneInwardDt.getStoneType().getName() : ""))
			.append("\",\"shape\":\"")
			.append((stoneInwardDt.getShape() != null ? stoneInwardDt.getShape().getName() : ""))
			.append("\",\"subShape\":\"")
			.append((stoneInwardDt.getSubShape() != null ? stoneInwardDt.getSubShape().getName() : ""))	
			.append("\",\"quality\":\"")
			.append((stoneInwardDt.getQuality() != null ? stoneInwardDt.getQuality().getName() : ""))					
			.append("\",\"mm\":\"")
			.append((stoneInwardDt.getSize() != null ? stoneInwardDt.getSize() : ""))
			.append("\",\"sieve\":\"")
			.append(stoneInwardDt.getSieve())	
			.append("\",\"sizeGroup\":\"")
			.append((stoneInwardDt.getSizeGroup().getName()))
			.append("\",\"rate\":\"")
			.append(stoneInwardDt.getRate())
			.append("\",\"balStone\":\"")
			.append(stoneInwardDt.getBalStone())	
			.append("\",\"balCarat\":\"")
			.append(df.format(stoneInwardDt.getBalCarat()))
			.append("\",\"stoneValue\":\"")
			.append(stoneInwardDt.getBalCarat()*stoneInwardDt.getRate())
			.append("\",\"trfStone\":\"")
			.append(trfStone)
			.append("\",\"trfCarat\":\"")
			.append(trfCarat)
			.append("\",\"addCarat\":\"")
			.append(trfCarat)
			.append("\",\"dedCarat\":\"")
			.append(trfCarat)
			.append("\",\"centerStone\":\"")
			.append(centerStone)
			.append("\",\"setting\":\"")
			.append(vSetting)
			.append("\",\"settingType\":\"")
			.append(vSettingType)
			.append("\"},");
			
		}
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
		
		
	}
	
	
	
	
	
	
}
