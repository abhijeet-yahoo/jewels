package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddStnAdj;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IQualityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISizeGroupService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddStnAdjService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class VAaddStnAdjController {
	
	@Autowired
	private IVAddStnAdjService stnAdjService;
	
	@Autowired
	private ICostingMtService costingMtService;

	@Autowired
	private IStoneTypeService stoneTypeService;
	
	@Autowired
	private IShapeService shapeService;
	
	@Autowired
	private IQualityService qualityService;
	
	@Autowired
	private ISizeGroupService sizeGroupService;
	
	@Value("${vaddstnadjtype}")
	private String vaddstnadjtype;

	

	
	@ModelAttribute("vAddStnAdj")
	private VAddStnAdj construct(){
		return new VAddStnAdj();
		
	}
	
	
	@RequestMapping("/vAddStnAdj/adjustmentListing")
	@ResponseBody
	public String vAddStnAdjListing(@RequestParam(value="size" ,required=false) String size,
			@RequestParam(value="vAddMtId" ,required=false) Integer costMtIdPk,
			@RequestParam(value="stonetypeid" ,required=false) Integer stonetypeid,
			@RequestParam(value="shapeid" ,required=false) Integer shapeid,
			@RequestParam(value="qualityid" ,required=false) Integer qualityid,
			@RequestParam(value="sizegroupid" ,required=false) Integer sizegroupid,
			@RequestParam(value="rate" ,required=false) Double rate
			) {

		return stnAdjService.adjListing(size, costMtIdPk, stonetypeid, shapeid, qualityid, sizegroupid, vaddstnadjtype.trim());
		
	}
	
	
	
	
	@RequestMapping("/vAddStnAdj/allFifoAdjustment")
	@ResponseBody
	public String allFifoAdjustment(@RequestParam(value = "vAddMtId", required = false) Integer vAddMtId,Principal principal) {

		return stnAdjService.fifoAllAdjustment(vAddMtId, vaddstnadjtype.trim(),principal );
		
		
	}

	
	
	@RequestMapping(value = "/vAddStnDtAdj/saveAdj", method=RequestMethod.POST)
	@ResponseBody
	public String addEdit(@Valid
			@RequestParam(value="dtId" ,required=false) String dtIds,
			@RequestParam(value="size" ,required=false) String size,
			@RequestParam(value="vAddMtId" ,required=false) Integer costMtIdPk,
			@RequestParam(value="stonetypeid" ,required=false) Integer stonetypeid,
			@RequestParam(value="shapeid" ,required=false) Integer shapeid,
			@RequestParam(value="qualityid" ,required=false) Integer qualityid,
			@RequestParam(value="sizegroupid" ,required=false) Integer sizegroupid,
			@RequestParam(value="rate" ,required=false) Double rate,
			@RequestParam(value="carat" ,required=false) Double carat,
			@RequestParam(value="adjCarat" ,required=false) String adjCarat,
			
			Principal principal){
		
		String retVal = "";
		
		synchronized (this) {
		
		 retVal = stnAdjService.saveStoneAdjustment(dtIds, costMtIdPk, stonetypeid, shapeid, qualityid, sizegroupid, size, rate, carat, principal,adjCarat,vaddstnadjtype.trim());
		}
		
			return retVal;
			
	}
	
	
	@RequestMapping("/vAddStnDtAdj/deleteAdjument")
	@ResponseBody
	public String deleteAdjument(@RequestParam(value="size" ,required=false) String size,
			@RequestParam(value="vAddMtId" ,required=false) Integer costMtIdPk,
			@RequestParam(value="stonetypeid" ,required=false) Integer stonetypeid,
			@RequestParam(value="shapeid" ,required=false) Integer shapeid,
			@RequestParam(value="qualityid" ,required=false) Integer qualityid,
			@RequestParam(value="sizegroupid" ,required=false) Integer sizegroupid,
			@RequestParam(value="rate" ,required=false) Double rate,
			 Principal principal) {
		
	String retVal = "";
			
			synchronized (this) {
			
				retVal =stnAdjService.deleteAdjustment(costMtIdPk, stonetypeid, shapeid, qualityid, sizegroupid, size, rate, principal,vaddstnadjtype.trim());
			}
			
				return retVal;
			
		}
	
	

	@RequestMapping("/vAddStnDtAdj/deleteAllAdjument")
	@ResponseBody
	public String deleteAllAdjument(
			@RequestParam(value="vAddMtId" ,required=false) Integer costMtIdPk,
				 Principal principal) {
		
	String retVal = "";
			
			synchronized (this) {
			
				retVal =stnAdjService.deleteAllAdjustment(costMtIdPk,principal,vaddstnadjtype.trim());
			}
			
				return retVal;
			
		}	
	
	}
