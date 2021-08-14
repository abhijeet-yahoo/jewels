package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VaddMetalDt;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVAddStnDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVaddMetalDtService;




@RequestMapping("/manufacturing/transactions")
@Controller
public class VAddDtController {

	
	
	@Autowired
	private ICostingMtService costingtMtService;
	
	@Autowired
	private IVAddDtService vAddDtService;
	
	
	@Autowired
	private IVAddStnDtService vAddStnDtService  ;
	
	@Autowired
	private IVaddMetalDtService vaddMetalDtService;
	
	@Autowired
	private IVAddCompDtService vAddCompDtService;
	
	@Value("${companyName}")
	private String companyName;
	
	
	
	
	@ModelAttribute("costingMt")
	public CostingMt construct() {
		return new CostingMt();
	}
	
	@ModelAttribute("vAddDt")
	public VAddDt constructDt() {
		return new VAddDt();
	}
	
	@ModelAttribute("vAddStnDt")
	public VAddStnDt constructStnDt() {
		return new VAddStnDt();
	}
	
	
	@RequestMapping("/vAddCostDt/listing")
	@ResponseBody
	public String vAddDtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "pInvNo", required = false) String pInvNo) {

		return vAddDtService.vaddDtListing(limit, offset, sort, order, search, pInvNo);
		
	}
	
	 
	
	
	
	@ResponseBody
	@RequestMapping(value = "/load/valueAddition" ,  method = RequestMethod.POST)
	public String loadButtonData(
				@RequestParam(value="pInvNo") String invNo,Principal principal){
		
		
		String retVal = "-1";
		
		if(companyName.equalsIgnoreCase("flawless")){
			retVal =vAddDtService.loadValuAdditionFl(invNo, principal);
		}else{
			retVal =vAddDtService.loadValuAddition(invNo, principal);
		}
		
		
		return retVal;
		
	}
	
	
	
	
	
	
	@RequestMapping(value = "/vAddDt/applyRate", method = RequestMethod.POST)
	@ResponseBody
	public String vaddApplyRate(@RequestParam(value="invNo" , required=false) String invNo, Principal principal,
			@RequestParam(value="vAdded", required=false) Double vAdded){
		
		

		
		CostingMt costingMt = costingtMtService.findByInvNoAndDeactive(invNo, false);
		costingMt.setvAdded(vAdded);

		costingtMtService.save(costingMt);
		
		List<VAddDt> vAddDts = vAddDtService.findByCostingMtAndDeactive(costingMt, false);
		
		for (VAddDt vAddDt : vAddDts) {
			
			if(vAddDt.getrLock() == true){
				continue;
			}

			vAddDtService.applyRate(vAddDt, principal);
		}
		
		
		return "-1";
		
	}
	
	
	
	//---------//--edit-Wastage Percent----//
	
	@ResponseBody
	@RequestMapping("/vaddDt/update/wastagePercent")
	public String editWastage(@RequestParam(value="pWastage") Double pWastage,
			@RequestParam(value="vAddDtId") Integer vAddDtId){
		
		String retVal = "-1";

		
		if(pWastage == null || pWastage.equals("")){
			retVal = "-2";
		}else{
		
			try{
				VAddDt vAddDt = vAddDtService.findOne(vAddDtId);
				
				
				vAddDtService.save(vAddDt);
			
			}catch(Exception e){
				retVal = "-1";
			}
			
		}
			
		return retVal;
		
	}
	
	
	
	//-----Lock-Unlock---//----//
	
	@ResponseBody
	@RequestMapping("/vAddDt/lockUnlock")
	public String lockUnlock(@RequestParam(value="vAddDtId") Integer vAddDtId){
		
		String retVal = "-1";
		
		VAddDt vAddDt = vAddDtService.findOne(vAddDtId);
		List<VAddStnDt> vAddStnDts = vAddStnDtService.findByVAddDtAndDeactive(vAddDt, false);
		List<VAddCompDt> vAddCompDts = vAddCompDtService.findByVAddDtAndDeactive(vAddDt, false);
		List<VaddMetalDt>vaddMetalDts =vaddMetalDtService.findByVaddDtAndDeactive(vAddDt, false);
		
		
		
		if(vAddDt.getrLock() == true ){
			vAddDt.setrLock(false);
			
			for(VAddStnDt vAddStnDt:vAddStnDts){
				vAddStnDt.setrLock(false);
				vAddStnDtService.save(vAddStnDt);
			}
			
			for(VAddCompDt vAddCompDt:vAddCompDts){
				vAddCompDt.setrLock(false);
				vAddCompDtService.save(vAddCompDt);
			}
			
			
			for(VaddMetalDt vaddMetalDt :vaddMetalDts){
				vaddMetalDt.setrLock(false);
				vaddMetalDtService.save(vaddMetalDt);
			}
			
		}else{
			vAddDt.setrLock(true);
			
			for(VAddStnDt vAddStnDt:vAddStnDts){
				vAddStnDt.setrLock(true);
				vAddStnDtService.save(vAddStnDt);
			}
			
			for(VAddCompDt vAddCompDt:vAddCompDts){
				vAddCompDt.setrLock(true);
				vAddCompDtService.save(vAddCompDt);
			}
			
			
			for(VaddMetalDt vaddMetalDt :vaddMetalDts){
				vaddMetalDt.setrLock(true);
				vaddMetalDtService.save(vaddMetalDt);
			}
			
			
		}
		
		vAddDtService.save(vAddDt);
		
		retVal="1";
		
		return retVal;
	}
	
	
	
	@ResponseBody
	@RequestMapping("/vaddDt/changeWastPerc")
	public String editWastagePercent(
			@RequestParam(value = "pLossPerc") Double pLossPerc,
			@RequestParam(value = "pInvNo") String pInvo){
		
		String retVal = "-1";
		
		try{
			CostingMt costingMt = costingtMtService.findByInvNoAndDeactive(pInvo, false);
			costingMt.setvAddWastage(pLossPerc);
			costingtMtService.save(costingMt);
			vAddDtService.updateWastagePerc(costingMt.getId(), pLossPerc);
		}catch(Exception e){
			System.out.println(e);
			retVal = "-2";
		}
		
		return retVal;
		
	}
	
	
	@ResponseBody
	@RequestMapping("/vAddDt/getData/{dtId}")
	public String getVaddDtData(@PathVariable int dtId){
		
		
		VAddDt vAddDt = vAddDtService.findOne(dtId);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("grossWt", vAddDt.getGrossWt());
		jsonObject.put("netWt", vAddDt.getNetWt());
		jsonObject.put("lossPerc", vAddDt.getLossPercent());
		jsonObject.put("lossWt", vAddDt.getLossWt());
		jsonObject.put("metalValue", vAddDt.getMetalValue());
		jsonObject.put("lossValue", vAddDt.getLossValue());
		jsonObject.put("totMetalValue",Math.round((vAddDt.getMetalValue()+vAddDt.getLossValue())*100.0)/100.0);
		jsonObject.put("stoneValue", vAddDt.getStoneValue());
		jsonObject.put("fob", vAddDt.getFob());
		jsonObject.put("finalPrice", vAddDt.getFinalPrice());
		jsonObject.put("perPcsPrice", vAddDt.getFinalPricePerPcs());
		
		
		
		
	

		

		
		return jsonObject.toString();
		
		
	}
	
	
	
	
}
