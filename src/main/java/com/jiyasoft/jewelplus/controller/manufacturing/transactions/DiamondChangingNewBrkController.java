package com.jiyasoft.jewelplus.controller.manufacturing.transactions;


import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.DiamondBagging;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneInwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneInwardMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IQualityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISizeGroupService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneChartService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IDiamondBaggingService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneInwardDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneTranService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMtService;


@RequestMapping("/manufacturing/transactions")
@Controller
public class DiamondChangingNewBrkController {

	
	@Autowired
	private IDiamondBaggingService diamondBaggingService;

	
	@Autowired
	private IStoneTypeService stoneTypeService;
	
	@Autowired
	private IBagMtService bagMtService;
	
	@Autowired
	private ITranMetalService tranMetalService;
	
	@Autowired
	private ITranMtService tranMtService;

	@Autowired
	private IShapeService shapeService;


	@Autowired
	private IQualityService qualityService;

	@Autowired
	private IStoneChartService stoneChartService;

	@Autowired
	private ISizeGroupService sizeGroupService;

	@Autowired
	private ISettingService settingService;

	@Autowired
	private ISettingTypeService settingTypeService;

	@Autowired
	private IStoneInwardDtService stoneInwardDtService;

	@Autowired
	private IStoneTranService stoneTranService;

	
	
	@Value("${diamond.stocktype}")
	private String diamondStockType;
	
	@Value("${changingIssStk}")
	private String changingIssStk;
	
	
	@Value("${allowNegativeDiamond}")
	private Boolean allowNegativeDiamond;

	
	
	@ModelAttribute("diamondBagging")
	public DiamondBagging constructDiamondBagging() {
		return new DiamondBagging();
	}
	
	
	@ModelAttribute("stoneInwardDt")
	public StoneInwardDt constructStoneInwardDt() {
		return new StoneInwardDt();
	}
	
	
	@RequestMapping("/diamondChangingNewBreakup")
	public String diamondChangingNewBreakUp(Model model) {
		model = populateModel(model);
		return "diamondChangingNewBreakup";
	}

	
	private Model populateModel(Model model) {
		model.addAttribute("stoneTypeMap", stoneTypeService.getStoneTypeList());
		model.addAttribute("shapeMap", shapeService.getShapeList());
		model.addAttribute("settingMap",settingService.getSettingList());
		model.addAttribute("settingTypeMap",settingTypeService.getSettingTypeList());
		model.addAttribute("stockType",diamondStockType);
		return model;
	}
	
	
	@RequestMapping("/dc/quality/list")
	@ResponseBody
	public String productQualityList(
			@RequestParam(value = "shapeId") Integer shapeId,
			@ModelAttribute("stoneInwardMt") StoneInwardMt stoneInwardMt) {
		
		String quality = qualityService.getQualityListDropDown(shapeId);
		return quality;
	}
	
	
	@RequestMapping("/dc/size/list")
	@ResponseBody
	public String dcSizeList(
			@RequestParam(value = "shapeId") Integer shapeId,
			@ModelAttribute("stoneInwardMt") StoneInwardMt stoneInwardMt) {
		String size = stoneChartService.getStoneChartListDropDownForDc(shapeId);
		return size;
	}
	
	@RequestMapping("/dc/sieve/list")
	@ResponseBody
	public String dcSieveList(
			@RequestParam(value = "shapeId") Integer shapeId,
			@ModelAttribute("stoneInwardMt") StoneInwardMt stoneInwardMt) {
		String size = stoneChartService.getStoneChartSieveListDropDown(shapeId);
		return size;
	}
	
	
	
	@RequestMapping("/dc/sizeGroup/list")
	@ResponseBody
	public String dcSizeGroupList(
			@RequestParam(value = "shapeId") Integer shapeId,
			@ModelAttribute("stoneInwardMt") StoneInwardMt stoneInwardMt) {
		String sizeGroup = sizeGroupService.getSizeGroupListDropDown(shapeId);
		return sizeGroup;
	}

	@RequestMapping("/dc/groupWiseSize/list")
	@ResponseBody
	public String dcGroupWiseSizeist(
			@RequestParam(value = "sizeGroupId") Integer sizeGroupId,
			@ModelAttribute("stoneInwardMt") StoneInwardMt stoneInwardMt) {
		String size = stoneChartService.getStoneChartSizeListDropDown(sizeGroupId);
		return size;
	}
	
	
	@RequestMapping("/dc/load/stoneInwardDt/data")
	@ResponseBody
	public String stoneBreakupDetails(
			@RequestParam(value = "stoneType", required = false) Integer stoneTypeId,
			@RequestParam(value = "shape", required = false) Integer shapeId,
			@RequestParam(value = "quality", required = false) Integer qualityId,
			@RequestParam(value = "size", required = false) String sizeStr,
			@RequestParam(value = "sizeGroup", required = false) Integer sizeGroupId,
			@RequestParam(value = "bagNm", required = false) String bagNm,Principal principal){
		
		
		StringBuilder sb = new StringBuilder();
		sb.append("{\"total\":").append(stoneInwardDtService.count()).append(",\"rows\": [");
	
		List<Object[]> stnList =  stoneTranService.getStoneTranList(stoneTypeId, shapeId, qualityId, sizeGroupId, sizeStr, changingIssStk,bagNm,principal);
		
		int srNo = 0;
		
		if(stnList !=null) {
		
		for(Object[] obj:stnList){
			
			 sb.append("{\"stoneType\":\"")
				.append(obj[0]!=null? obj[0]:"")
				.append("\",\"shape\":\"")
				.append(obj[1]!=null? obj[1]:"")
				.append("\",\"subShape\":\"")
				.append(obj[2]!=null? obj[2]:"")
				.append("\",\"quality\":\"")
				.append(obj[3]!=null? obj[3]:"")
				.append("\",\"size\":\"")
				.append(obj[4]!=null? obj[4]:"")
				.append("\",\"sieve\":\"")
				.append(obj[5]!=null? obj[5]:"")
				.append("\",\"sizeGroup\":\"")
				.append(obj[6]!=null? obj[6]:"")
				.append("\",\"centerStone\":\"")
				.append((obj[7] != null ? (obj[7].toString() == "1" ? true : false) : false))
				.append("\",\"balStone\":\"")
				.append(obj[8] != null? obj[8]: "")
				.append("\",\"balCarat\":\"")
				.append(obj[9]!=null? obj[9]:"")
				.append("\",\"trfStone\":\"")
				.append("0")
				.append("\",\"trfCarat\":\"")
				.append("0.0")
				.append("\",\"setting\":\"")
				.append("")
				.append("\",\"settingType\":\"")
				.append("")
				.append("\",\"serialNo\":\"")
				.append(srNo++)
			.append("\"},");
			
		}
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
		
		}else {
			return "User wise location rights not given or location stockstk flag is false";
		}
		
		
	}
	

	
	
	@RequestMapping(value = "/newBreakupImpStnAdj/frmStoneInwardDt", method = RequestMethod.POST)
	@ResponseBody
	public String newBreakUpDiamondTransferFromStoneInwardDt(
			@RequestParam(value = "pBagName") String bagNm,
			@RequestParam(value = "pPartId") Integer pPartId,
		    @RequestParam(value = "data") String jsonData,
		    @RequestParam(value = "vTrandate") String vTranDate,
		    @RequestParam(value = "vSize", required = false) String vSize,
			Principal principal) throws ParseException {
		
		String retVal = "1";
		
		Date date= new Date();
		
		if(vTranDate !=null && !vTranDate.isEmpty()){
			DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			date = originalFormat.parse(vTranDate);
				
		}
		
		synchronized (this) {
			try {				
				retVal=diamondBaggingService.diamondChangingNewBreakUpTrf(bagNm, jsonData, principal,pPartId,vSize,changingIssStk,allowNegativeDiamond,date);
				if(retVal == "-1"){
					retVal = "Data Transfered Successfully";
				}
			} catch (Exception e) {
				e.printStackTrace();
				retVal = "Warning:Error Occoured,Contact Support";
			}
		}
		
		
		
		
		
		return retVal;
		
		
	}
	
	
	
	@RequestMapping("/bag/partList")
	@ResponseBody
	public String getBagPart(@RequestParam(value="bagName" ) String bagName ) {
		
		
		BagMt bagMt = bagMtService.findByName(bagName);
		TranMt tranMt = tranMtService.findByBagMtCurrStk(bagMt, true);
		
		
		
		StringBuilder sb = new StringBuilder();
		Map<Integer, String> partMap = tranMetalService.getPartListByTranMt(tranMt);

		List<Map.Entry<Integer, String>> partMapGv = Lists.newArrayList(partMap.entrySet());
		Collections.sort(partMapGv, byMapValues);

		sb.append("<select id=\"partNmId\" name=\"partNmId\" class=\"form-control\" >");
		sb.append("<option value=\"\">- Select Part -</option>");

		Iterator<Entry<Integer, String>> iterator = partMapGv.iterator();
		while (iterator.hasNext()) {
			Entry<Integer, String> et = iterator.next();

			sb.append("<option value=\"").append(et.getKey()).append("\">")
					.append(et.getValue()).append("</option>");
		}

		sb.append("</select>");

		return sb.toString();
	}
	
	Ordering<Map.Entry<Integer, String>> byMapValues = new Ordering<Map.Entry<Integer, String>>() {
		@Override
		public int compare(Map.Entry<Integer, String> left,
				Map.Entry<Integer, String> right) {
			return left.getValue().compareTo(right.getValue());
		}
	};

	
	
 }
