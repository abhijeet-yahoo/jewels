package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MeltingIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILocationRightsService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMeltingIssDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalTranService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class MeltingIssDtController {

	@Autowired
	private IMeltingIssDtService meltingIssDtService;

	@Autowired
	private ILocationRightsService locationRightsService;
	
	@Autowired
	private ITranMtService tranMtService;

	@Autowired
	private IPurityService purityService;

	@Autowired
	private IColorService colorService;

	@Autowired
	private IDepartmentService departmentService;

	@Autowired
	private IMetalTranService metalTranService;
	
	@Autowired
	private UserService userService;


	@RequestMapping("/meltingIssDt")
	public String users(Model model) {
		return "meltingIssDt";
	}
	
@RequestMapping("/meltingPickUp/add")
	public String meltingPickup(Model model) {
		/*model = populateModel(model);*/
		return "meltingPickUp/add";

	}

	@RequestMapping("/stockMeltingPickup/add")
	public String stockMeltingPickup(Model model) {
		/*model = populateModel(model);*/
		return "stockMeltingPickup/add";
	
	}




	@RequestMapping("/meltingIssDt/listing")
	@ResponseBody
	public String meltingMtListing(Model model,
			@RequestParam(value = "pInvNo", required = false) String pInvNo,
			@RequestParam(value = "canViewFlag", required = false) Boolean canViewFlag,Principal principal) throws ParseException {

	
		return meltingIssDtService.meltingIssDtListing(principal, pInvNo, canViewFlag);
	}

	@RequestMapping("/meltingIssDt/add")
	public String add(Model model) {
		return "meltingIssDt/add";

	}
	
	
	
	
	@RequestMapping(value = "/meltingPickup/save", method = RequestMethod.POST)
	@ResponseBody
	public String saveMeltingPickup(@RequestParam(value="meltingMtId",required = false) Integer meltingMtId,
			@RequestParam(value="tblData",required = false) String tblData,Principal principal){
		
	String retVal = "-1";
		
		synchronized (this) {
			
			JSONArray jsonTrfTblArray = new JSONArray(tblData);
			
			String vBagNm="";
			
			
			for (int i = 0; i < jsonTrfTblArray.length(); i++) {
				
				JSONObject jsonObject =   (JSONObject) jsonTrfTblArray.get(i);
				
				TranMt tranMt =tranMtService.findOne(Integer.parseInt(jsonObject.get("id").toString()));
				
				if(tranMt.getBagMt().getBagCloseFlg().equals(false) && tranMt.getDepartment().getName().equalsIgnoreCase("Refining") ){
					
				
					
					
				}else {
					if(vBagNm ==""){
						vBagNm=tranMt.getBagMt().getName();
						
					}else{
						vBagNm+=","+tranMt.getBagMt().getName();
					}
				}
				
				
				
			}
				
			if(vBagNm !=""){
				
				
				return "error : Can Not Transfer ,Bags are Not In Refining Department or Bag Is Closed ( "+vBagNm+")";
				
			}
				
			
			
			retVal=meltingIssDtService.meltingPickupsave(meltingMtId, jsonTrfTblArray, principal);
			
		}
		

	
		


		return retVal;
	}
	

	@RequestMapping(value = "/meltingIssDt/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditMeltingIssDt(
			@Valid @ModelAttribute("meltingIssDt") MeltingIssDt meltingIssDt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "pInvNo") String pInvNo,
			@RequestParam(value = "prevNetWt") Double prevNetWt,
			@RequestParam(value = "prevKt") Double prevKt,
			@RequestParam(value = "prevColor") Double prevColor,
			@RequestParam(value = "currNetWt") Double currNetWt,
			@RequestParam(value = "pExcpPureWt") Double pExcpPureWt,
			@RequestParam(value = "pFMetalWt") Double pFMetalWt,
			RedirectAttributes redirectAttributes, Principal principal) {

		
		String retVal = "-1";
		

		if (result.hasErrors()) {
			return "meltingIssDt/add";
		}
		
		retVal = meltingIssDtService.meltingIssDtSave(meltingIssDt, id, pInvNo, prevNetWt, pFMetalWt, prevKt, prevColor, currNetWt, pExcpPureWt, principal);
		
		if(retVal.contains(",")){
		String xy[]=retVal.split(",");
		
		redirectAttributes.addAttribute("success",true);
		redirectAttributes.addAttribute("action",xy[1]);
		return xy[0];
		}else{
			return retVal;
		}
	}
	
	@RequestMapping("/meltingIssDt/edit/{id}")
	public String edit(@PathVariable int id, Model model,Principal principal) {
		MeltingIssDt meltingIssDt = meltingIssDtService.findOne(id);
		
		User user =userService.findByName(principal.getName());
		
		model.addAttribute("meltingIssDt", meltingIssDt);
		model.addAttribute("purityMap", purityService.getPurityList());
		model.addAttribute("colorMap", colorService.getColorList());
		model.addAttribute("locationMetalMap", locationRightsService.getToLocationListFromUser(user.getId(),"metal"));

		return "meltingIssDt/add";
	}

	@RequestMapping("/meltingIssDt/delete/{id}")
	public String delete(@PathVariable int id) {
		
		String retVal=meltingIssDtService.meltingIssDtDelete(id);

		
		return retVal;
	}

	@RequestMapping("/pureId/purityConversion")
	@ResponseBody
	public String issPurityConvFetch(
			@RequestParam(value = "PurityId") Integer PurityId,
			@ModelAttribute("meltingIssDt") MeltingIssDt meltingIssDt) {

		StringBuilder sb = new StringBuilder();
		Purity purity = purityService.findOne(PurityId);

		double purityConv = 0.0;

		if (purity.getPurityConv() != null) {
			purityConv = purity.getPurityConv();
		}

		sb.append(purityConv);

		return sb.toString();
	}

	@RequestMapping("/meltingIssDt/stock")
	@ResponseBody
	public String stockCheck(
			@RequestParam(value = "metalWt", required = false) Double metalWt,
			@RequestParam(value = "purityId", required = false) Integer purityId,
			@RequestParam(value = "colorId", required = false) Integer colorId,
			@ModelAttribute("meltingIssDt") MeltingIssDt meltingIssDt) {

		StringBuilder sb = new StringBuilder();
		

		Integer locationId = departmentService.findByName("Central").getId();
		Double balance = metalTranService.getStockBalance(purityId, colorId,
				locationId);

		System.out.println("balance =" + balance);
		
		if(balance != null){
			sb.append(balance);
		}
		else{
			sb.append("-1");
		}

		return sb.toString();
	}
	
	
	
/*	@RequestMapping("/meltingIssDt/used/stock")
	@ResponseBody
	public String usedStockCheck(
			@RequestParam(value = "usedMetalWt", required = false) Double usedMetalWt,
			@RequestParam(value = "purityId", required = false) Integer purityId,
			@RequestParam(value = "colorId", required = false) Integer colorId,
			@ModelAttribute("meltingIssDt") MeltingIssDt meltingIssDt) {

		StringBuilder sb = new StringBuilder();

		Integer locationId = departmentService.findByName("Central").getId();
		Double balance = metalTranService.getUsedMetalStockBalance(purityId, colorId, locationId);
		
		if(balance != null){
			sb.append(balance);
		}
		else{
			sb.append("-1");
		}

		return sb.toString();
	}*/

	
	
	
	@RequestMapping("/meltingPickup/listing")
	@ResponseBody
	public String tranDtListing() {
		
				
		StringBuilder sb = new StringBuilder();
		
		Department department = departmentService.findByName("Refining");
		/*List<Object[]> tranMts = tranMtService.getBagsForCasting(department,castingMt.getPurity(),castingMt.getColor());*/
		
		
		
		List<TranMt>tranMts =tranMtService.getBagsNotCloseByDepartment(department.getId());
		
		sb.append("{\"total\":").append(tranMts.size()).append(",\"rows\": [");
		
		for (TranMt tranMt : tranMts) { 
			sb.append("{\"bagId\":\"")
					.append(tranMt.getBagMt().getId())
					.append("\",\"id\":\"")
					.append(tranMt.getId())
					.append("\",\"party\":\"")
					.append(tranMt.getBagMt().getOrderMt().getParty().getPartyCode())
					.append("\",\"orderNo\":\"")
					.append(tranMt.getBagMt().getOrderMt().getInvNo())
					.append("\",\"bagNo\":\"")
					.append(tranMt.getBagMt().getName())
					.append("\",\"style\":\"")
					.append(tranMt.getBagMt().getOrderDt().getDesign().getMainStyleNo())
					.append("\",\"grossWt\":\"")
					.append(tranMt.getRecWt())
					.append("\",\"purity\":\"")
					.append(tranMt.getOrderDt().getPurity().getName())
					.append("\",\"color\":\"")
					.append(tranMt.getOrderDt().getColor().getName())
					.append("\",\"pcs\":\"")
					.append(tranMt.getBagMt().getQty())
					.append("\"},");
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		
		
		return str;
		
	
		
	}	

	
	@RequestMapping("/melting/stnRec/listing")
	@ResponseBody
	public String stoneBreakupDetails(
			@RequestParam(value = "meltingMtId", required = false) Integer meltingMtId){
		
		Department department = departmentService.findByName("Refining");
		
		StringBuilder sb = new StringBuilder();
		
		List<Object[]> meltStnRecList =  meltingIssDtService.meltingStnRecList(meltingMtId, department.getId());
		sb.append("{\"total\":").append(meltStnRecList.size()).append(",\"rows\": [");

		int srNo = 1;
		for(Object[] obj:meltStnRecList){
	
			
			 sb.append("{\"stoneType\":\"")
				.append(obj[14]!=null? obj[14]:"")
				.append("\",\"shape\":\"")
				.append(obj[15]!=null? obj[15]:"")
				.append("\",\"quality\":\"")
				.append(obj[16]!=null? obj[16]:"")
				.append("\",\"size\":\"")
				.append(obj[3]!=null? obj[3]:"")
				.append("\",\"sieve\":\"")
				.append(obj[4]!=null? obj[4]:"")
				.append("\",\"sizeGroup\":\"")
				.append(obj[17]!=null? obj[17]:"")
				.append("\",\"stones\":\"")
				.append(obj[6] != null? obj[6]: "")
				.append("\",\"carat\":\"")
				.append(obj[7]!=null? obj[7]:"")
				.append("\",\"balStones\":\"")
				.append(obj[12] != null? obj[12]: "")
				.append("\",\"balCarat\":\"")
				.append(obj[13]!=null? obj[13]:"")
				.append("\",\"avgRate\":\"")
				.append(obj[8]!=null? obj[8]:"")
				.append("\",\"factoryRate\":\"")
				.append(obj[9]!=null? obj[9]:"")
				.append("\",\"rate\":\"")
				.append(obj[10]!=null? obj[10]:"")
				.append("\",\"trfRate\":\"")
				.append(obj[11]!=null? obj[11]:"")
				.append("\",\"trfStone\":\"")
				.append("0")
				.append("\",\"trfCarat\":\"")
				.append("0.0")
				.append("\",\"srNo\":\"")
				.append(srNo++)
			.append("\"},");
			
		}
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}
	
	
	@RequestMapping(value = "/meltingStnRec/transfer", method = RequestMethod.POST)
	@ResponseBody
	public String meltingStnRecTransfer(
			@RequestParam(value = "meltingMtId") Integer meltingMtId,
		    @RequestParam(value = "data") String jsonData,
		    @RequestParam(value = "locationId") Integer locationId,
			Principal principal) {
		
		String retVal ="-1";
		
		synchronized (this) {
			try {				
				retVal = meltingIssDtService.meltingStnTransfer(meltingMtId, jsonData,principal,locationId);
				if(retVal == "1"){
					retVal = "Data Transfered Successfully";
				}
			} catch (Exception e) {
				e.printStackTrace();
				retVal = "Warning:Error Occoured,Contact Support";
			}
		}
		
		
		return retVal;
	}
	
	
	
	
	@RequestMapping(value = "/stockIssueToMelting/save", method = RequestMethod.POST)
	@ResponseBody
	public String stockIssueToMelting(@RequestParam(value="meltingMtId",required = false) Integer meltingMtId,
		
			@RequestParam(value="vDtId",required = false) String vDtId,Principal principal){
		
	String retVal = "-1";
		
		synchronized (this) {
			
			retVal=meltingIssDtService.stockIssueToMeltingTransfer(meltingMtId, vDtId, principal);
			
		}
		


		return retVal;
	}

			
}


