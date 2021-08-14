package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnBflDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneInwardDt;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IQualityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISizeGroupService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStnBflDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStnBflMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneInwardDtService;




@RequestMapping("/manufacturing/transactions")
@Controller
public class StnBflMtNewBreakupController {
	
	@Autowired
	private IStoneTypeService stoneTypeService;
	
	@Autowired
	private IStnBflDtService stnBflDtService;

	@Autowired
	private IStnBflMtService stnBflMtService;
	
	@Autowired
	private IDepartmentService departmentService;

	@Autowired
	private IShapeService shapeService;
	
	@Autowired
	private ISettingService settingService;
	
	@Autowired
	private IStoneInwardDtService stoneInwardDtService;
	
	@Autowired
	private IQualityService qualityService;
	
	@Autowired
	private ISizeGroupService sizeGroupService;

	@Autowired
	private ISettingTypeService settingTypeService;
	
	@ModelAttribute("stoneInwardDt")
	public StoneInwardDt constructStoneInwardDt() {
		return new StoneInwardDt();
	}
	
	
	@ModelAttribute("stnBflDt")
	public StnBflDt construct() {
		return new StnBflDt();
	}
	
	
	@RequestMapping("/stnBflMtNewBreakup")
	public String goTNextPage(Model model){
		model = populateModel(model);
		return "stnBflMtNewBreakup";
	}
	
	
	private Model populateModel(Model model) {
		model.addAttribute("stoneTypeMap", stoneTypeService.getStoneTypeList());
		model.addAttribute("shapeMap", shapeService.getShapeList());
		
		model.addAttribute("settingMap",settingService.getSettingList());
		model.addAttribute("settingTypeMap",settingTypeService.getSettingTypeList());
		
		return model;
	}
	

	@RequestMapping("/load/stoneInwardDt/data")
	@ResponseBody
	public String stnBreakupDetails(
			@RequestParam(value = "stoneType", required = false) Integer stoneTypeId,
			@RequestParam(value = "shape", required = false) Integer shapeId,
			@RequestParam(value = "quality", required = false) Integer qualityId,
			@RequestParam(value = "size", required = false) String sizeStr,
			@RequestParam(value = "sizeGroup", required = false) Integer sizeGroupId,
			@RequestParam(value = "typeFormat", required = false) String typeFormat){
		
		
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
		
		Integer trfLossStone = 0;
		Double trfLossCarat = 0.0;

		for (StoneInwardDt stoneInwardDt : stoneInwardDts) {
			
			
			if(typeFormat.equalsIgnoreCase("fallenType")){
			/*	System.out.println(""+typeFormat);*/
				if (stoneInwardDt.getFallCarat() <= 0) {
					continue;
					
					
				}
				
			}else {
				/*System.out.println(""+typeFormat);*/
				if (stoneInwardDt.getBrkCarat() <= 0) {
					continue;
				}
			}
			
			sb.append("{\"id\":\"")
			.append(stoneInwardDt.getId())
			.append("\",\"inwardType\":\"")
			.append((stoneInwardDt.getStoneInwardMt().getInwardType() != null ? stoneInwardDt.getStoneInwardMt().getInwardType().getName() : ""))
			.append("\",\"invNo\":\"")
			.append((stoneInwardDt.getStoneInwardMt() != null ? stoneInwardDt.getStoneInwardMt().getInvNo() : ""))
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
			.append(stoneInwardDt.getRate());
			
			if(typeFormat.equalsIgnoreCase("fallenType")){
				sb.append("\",\"brkStone\":\"")
				.append(stoneInwardDt.getFallStone())	
				.append("\",\"brkCarat\":\"")
				.append(stoneInwardDt.getFallCarat());
				
			}else{
				sb.append("\",\"brkStone\":\"")
				.append(stoneInwardDt.getBrkStone())	
				.append("\",\"brkCarat\":\"")
				.append(stoneInwardDt.getBrkCarat());
			}
						
			sb.append("\",\"adjStone\":\"")
			.append(trfStone)
			.append("\",\"adjCarat\":\"")
			.append(trfCarat)
			.append("\",\"lossStone\":\"")
			.append(trfLossStone)
			.append("\",\"lossCarat\":\"")
			.append(trfLossCarat)
			.append("\"},");
			
		}
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
		

	}
	
	
	
	@RequestMapping(value ="newBreakup/frmStoneInwardDt", method = RequestMethod.POST)
	@ResponseBody
	public String transferAdjustment(@Valid @ModelAttribute("stnBflDt") StnBflDt stnBflDt,
			BindingResult result, 
			@RequestParam(value = "pODIds") String pOIds,
			@RequestParam(value = "adjStone") String trfAdjStone,
			@RequestParam(value = "adjCarat") String trfAdjCarat,
			@RequestParam(value = "pTypeFormat") String pTypeFormat,
			@RequestParam(value = "lossStone") String trfLossStone,
			@RequestParam(value = "lossCarat") String trfLossCarat,
			@RequestParam(value = "pMtId") Integer pMtId,
			RedirectAttributes redirectAttributes,Principal principal) {

	
		String retVal = "";
	
		
		if(result.hasErrors()){
			return "stnBflMtNewBreakup";
		}
		
		synchronized (this) {
			retVal=stnBflMtService.saveData(pOIds, trfAdjStone, trfAdjCarat, pTypeFormat, trfLossStone, trfLossCarat, pMtId, principal);	
		}
		
		return retVal;
	
	}
	
	
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	
	
	
	

}
