package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DecimalFormat;
import java.util.Date;
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

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostJobCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostJobLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostJobStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingJobDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingJobMt;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostJobCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostJobLabDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostJobStnDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostLabDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostStnDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingJobDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingJobMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class CostingJobMtTransferController {

	
	@Autowired
	private ICostingDtService costingDtService;
	
	@Autowired
	private ICostStnDtService costStnDtService;
	
	@Autowired
	private ICostCompDtService costCompDtService;
	
	@Autowired
	private ICostLabDtService costLabDtService;
	
	@Autowired
	private ICostingJobMtService  costingJobMtService;
	
	@Autowired
	private ICostingJobDtService costingJobDtService;
	
	@Autowired
	private ICostJobStnDtService costJobStnDtService;
	
	@Autowired
	private ICostJobCompDtService costJobCompDtService;
	
	@Autowired
	private ICostJobLabDtService costJobLabDtService;
	
	
	@ModelAttribute("costingJobMt")
	public CostingJobMt construct() {
		return new CostingJobMt();
	}
	
	
	
	@ModelAttribute("costingDt")
	public CostingDt constructDt(){
		return new CostingDt();
	}
	
	
	
	
	
	
	@RequestMapping("/costingJobMtTransfer")
	public String addScreen(){
		return "costingJobMtTransfer";
	}
	
	
	@RequestMapping("/fromCostingDt/listing")
	@ResponseBody
	public String costingJobMtTransferListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "itemNo", required = false) String itemNo) {

		StringBuilder sb = new StringBuilder();

		
		sb.append("{\"total\":").append(costingDtService.count()).append(",\"rows\": [");
		
		
		if(itemNo.trim().equalsIgnoreCase("fake")){
			
			String str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
			return str;
			
		}

		List<CostingDt> costingDts = costingDtService.findByItemNoAndDeactive(itemNo.trim(), false);
				
			DecimalFormat df = new DecimalFormat("#.###");
				
				Integer trfQty = 0;
				
				for (CostingDt costingDt : costingDts) {
			
					sb.append("{\"id\":\"")
							.append(costingDt.getId())
							.append("\",\"itemNo\":\"")
							.append((costingDt.getItemNo() != null ? costingDt.getItemNo() : ""))
							.append("\",\"orderNo\":\"")
							.append((costingDt.getOrderDt() != null ? costingDt.getOrderDt().getOrderMt().getInvNo() : ""))
							.append("\",\"bagNo\":\"")
							.append((costingDt.getBagMt() != null ? costingDt.getBagMt().getName() : ""))
							.append("\",\"pcs\":\"")
							.append((costingDt.getPcs() != null ? costingDt.getPcs() : ""))
							.append("\",\"style\":\"")
							.append((costingDt.getOrderDt() != null ? costingDt.getOrderDt().getDesign().getMainStyleNo() : ""))
							.append("\",\"grossWt\":\"")
							.append(df.format(costingDt.getGrossWt()))
							.append("\",\"trfQty\":\"")
							.append(trfQty)
							
							.append("\"},");
				}
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
		
	}
	
	
	
	
	@ResponseBody
	@RequestMapping(value = "/transfer/costingJobDt", method=RequestMethod.POST)
	public String transferCostDtData(
			@Valid @ModelAttribute("costingDt") CostingDt costDt,
			BindingResult result, 
			@RequestParam(value="costingJobMtId") Integer costJobMtId,
			@RequestParam(value="pCostDtIds") String pCostDtIds,
			@RequestParam(value="pTrfQty") String pTrfQty,Principal principal){
		
		
		
		String retVal = "-1";
		
	/*	System.out.println("-------costJobMtId--------"+costJobMtId);
		System.out.println("-------pCostDtIds------"+pCostDtIds);
		System.out.println("-------pTrfQty---------"+pTrfQty);
		System.out.println("printing the length === "+pCostDtIds.length());
		
		*/
		
		if(pCostDtIds.length() == 0){
			return retVal = "-2";
		}
		
		
		String vCostDtIds[] = pCostDtIds.split(",");
		String vTrfQty[] 	  = pTrfQty.split(",");
		
		for(int i = 0;i<vTrfQty.length;i++){
			Integer tQty = Integer.parseInt(vTrfQty[i]);
			if(tQty == 0){
				return retVal = "-3";
			}
		}
		
		
		Date aDate = new java.util.Date();
		CostingJobMt costingJobMt = costingJobMtService.findOne(costJobMtId);
		DecimalFormat df = new DecimalFormat("#.###");
		
		for(int i=0;i<vCostDtIds.length;i++){
			
			
			CostingDt costingDt = costingDtService.findOne(Integer.parseInt(vCostDtIds[i]));
			
			Integer tempQty = Integer.parseInt(vTrfQty[i]);
			
			CostingJobDt costingJobDt = new CostingJobDt();
			costingJobDt.setCostingJobMt(costingJobMt);
			costingJobDt.setDesign(costingDt.getDesign());
			costingJobDt.setStyleNo(costingDt.getStyleNo());
			costingJobDt.setVersion(costingDt.getVersion());
			costingJobDt.setBagMt(costingDt.getBagMt());
			costingJobDt.setBagNm(costingDt.getBagMt().getName());
			costingJobDt.setPurity(costingDt.getPurity());
			costingJobDt.setPurityNm(costingDt.getPurityNm());
			costingJobDt.setColor(costingDt.getColor());
			costingJobDt.setColorNm(costingDt.getColorNm());
			costingJobDt.setOrderDt(costingDt.getOrderDt());
			costingJobDt.setDepartment(costingDt.getDepartment());
			costingJobDt.setGrossWt(Double.parseDouble(df.format((costingDt.getGrossWt()/costingDt.getPcs())*tempQty)));
			costingJobDt.setPcs(tempQty.doubleValue());
			costingJobDt.setMetalRate(costingDt.getMetalRate());
			costingJobDt.setPurityConv(costingDt.getPurityConv());
			costingJobDt.setCreatedBy(principal.getName());
			costingJobDt.setCreatedDate(new java.util.Date());
			costingJobDt.setUniqueId(aDate.getTime()+i);
			costingJobDt.setItemNo(costingDt.getItemNo());
			
			costingJobDtService.save(costingJobDt);
			
			Long costJobDtUniqueId = costingJobDt.getUniqueId(); 
			
			
			
			
			CostingJobDt uniqueCostJobDt = costingJobDtService.findByUniqueId(costJobDtUniqueId);
			
			
			//--------new entry in CostJobStnDt-------//
			
			List<CostStnDt> costStnDts = costStnDtService.findByItemNoAndCostingDtAndDeactive(uniqueCostJobDt.getItemNo(), costingDt, false);
			
			
			for(CostStnDt costStnDt : costStnDts){
				
				CostJobStnDt costJobStnDt = new CostJobStnDt();
				
				costJobStnDt.setStoneType(costStnDt.getStoneType());
				costJobStnDt.setCostingJobDt(uniqueCostJobDt);
				costJobStnDt.setCostingJobMt(costingJobMt);
				costJobStnDt.setShape(costStnDt.getShape());
				costJobStnDt.setShapeNm(costStnDt.getShape().getName());
				costJobStnDt.setQuality(costStnDt.getQuality());
				costJobStnDt.setQualityNm(costStnDt.getQuality().getName());
				costJobStnDt.setSizeGroup(costStnDt.getSizeGroup());
				costJobStnDt.setSizeGroupNm((costStnDt.getSizeGroup() != null ? costStnDt.getSizeGroup().getName() : ""));
				costJobStnDt.setSetting(costStnDt.getSetting());
				costJobStnDt.setSettingType(costStnDt.getSettingType());
				costJobStnDt.setOrderDt(costStnDt.getOrderDt());
				costJobStnDt.setBagSrNo(costStnDt.getBagSrNo());
				costJobStnDt.setSize(costStnDt.getSize());
				costJobStnDt.setSieve(costStnDt.getSieve());
				Double temp = (costStnDt.getStone()/costingDt.getPcs())*tempQty;
				Long l = Math.round(temp);
				costJobStnDt.setStone(Integer.valueOf(l.intValue()));
				costJobStnDt.setCarat(Double.parseDouble(df.format((costStnDt.getCarat()/costingDt.getPcs())*tempQty)));
				costJobStnDt.setStnRate(costStnDt.getStnRate());
				costJobStnDt.setCreatedBy(principal.getName());
				costJobStnDt.setCreatedDate(new java.util.Date());
				costJobStnDt.setItemNo(costStnDt.getItemNo());
				costJobStnDt.setStoneInwardDt(costJobStnDt.getStoneInwardDt());
				costJobStnDt.setHandlingRate(costJobStnDt.getHandlingRate());
				costJobStnDt.setSetRate(costJobStnDt.getSetRate());
				
				costJobStnDtService.save(costJobStnDt);
				
			}
			
			
			
			//--------new entry in CostJobCompDt-------//
			
			List<CostCompDt> costCompDts =  costCompDtService.findByItemNoAndCostingDtAndDeactive(uniqueCostJobDt.getItemNo(), costingDt, false);
			
			
			for(CostCompDt costCompDt : costCompDts){
				
				CostJobCompDt costJobCompDt = new CostJobCompDt();
				
				costJobCompDt.setCostingJobDt(uniqueCostJobDt);
				costJobCompDt.setCostingJobMt(costingJobMt);
				costJobCompDt.setComponent(costCompDt.getComponent());
				costJobCompDt.setPurity(costCompDt.getPurity());
				costJobCompDt.setColor(costCompDt.getColor());
				costJobCompDt.setOrderDt(costCompDt.getOrderDt());
				costJobCompDt.setBagMt(costCompDt.getBagMt());	
				costJobCompDt.setMetalWt((costCompDt.getMetalWt()/costingDt.getPcs())*tempQty);
				costJobCompDt.setCompRate(costCompDt.getCompRate());
				costJobCompDt.setCompPcs(tempQty.doubleValue());
				costJobCompDt.setPurityConv(costCompDt.getPurityConv());
				costJobCompDt.setCreatedBy(principal.getName());
				costJobCompDt.setCreatedDate(new java.util.Date());
				costJobCompDt.setItemNo(costCompDt.getItemNo());
				costJobCompDt.setCompPcs(costCompDt.getCompPcs());
		
				costJobCompDtService.save(costJobCompDt);
				
			}
			
			
			//--------new entry in CostJobLabDt-------//
			
			
			List<CostLabDt> costLabDts = costLabDtService.findByItemNoAndCostingDtAndDeactive(uniqueCostJobDt.getItemNo(), costingDt, false);
			
			for(CostLabDt costLabDt:costLabDts){
				
				CostJobLabDt costJobLabDt = new CostJobLabDt();
				
				costJobLabDt.setCostingJobMt(costingJobMt);
				costJobLabDt.setCostingJobDt(uniqueCostJobDt);
				costJobLabDt.setLabourType(costLabDt.getLabourType());
				costJobLabDt.setLabourRate(costLabDt.getLabourRate());
				costJobLabDt.setOrderDt(costLabDt.getOrderDt());
				costJobLabDt.setPcsWise(costLabDt.getPcsWise());
				costJobLabDt.setGramWise(costLabDt.getGramWise());
				costJobLabDt.setPercentWise(costLabDt.getPercentWise());
				costJobLabDt.setBagmt(costLabDt.getBagmt());
				costJobLabDt.setItemNo(costLabDt.getItemNo());
				costJobLabDt.setCreatedDate(new java.util.Date());
				costJobLabDt.setCreatedBy(principal.getName());
				
				costJobLabDtService.save(costJobLabDt);
				
			}
			
		}
		
	
		return retVal;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*@ResponseBody
	@RequestMapping("/addItemNoWise")
	public String transferCostingData(@RequestParam(value="vItemNo") String itemNo,
			@RequestParam(value="mtId") Integer costJobMtId,Principal principal){
		
		
		String retVal = "-1";
		
		
		try {
			
		CostingJobMt costingJobMt = costingJobMtService.findOne(costJobMtId);
		
		List<CostingDt> costingDts = costingDtService.findByItemNoAndDeactive(itemNo.trim(), false);
		
		Date aDate = new java.util.Date();
		int incrementOperator = 0;
		
		for(CostingDt costingDt:costingDts){
			
			incrementOperator = incrementOperator++;
			CostingJobDt costingJobDt = new CostingJobDt();
			costingJobDt.setCostingJobMt(costingJobMt);
			costingJobDt.setDesign(costingDt.getDesign());
			costingJobDt.setStyleNo(costingDt.getStyleNo());
			costingJobDt.setVersion(costingDt.getVersion());
			costingJobDt.setBagMt(costingDt.getBagMt());
			costingJobDt.setBagNm(costingDt.getBagMt().getName());
			costingJobDt.setPurity(costingDt.getPurity());
			costingJobDt.setPurityNm(costingDt.getPurityNm());
			costingJobDt.setColor(costingDt.getColor());
			costingJobDt.setColorNm(costingDt.getColorNm());
			costingJobDt.setOrderDt(costingDt.getOrderDt());
			costingJobDt.setDepartment(costingDt.getDepartment());
			costingJobDt.setGrossWt(costingDt.getGrossWt());
			costingJobDt.setNetWt(costingDt.getNetWt());
			costingJobDt.setPcs(costingDt.getPcs());
			costingJobDt.setPurityConv(costingDt.getPurityConv());
			costingJobDt.setCreatedBy(principal.getName());
			costingJobDt.setCreatedDate(new java.util.Date());
			costingJobDt.setUniqueId(aDate.getTime()+incrementOperator);
			costingJobDt.setItemNo(costingDt.getItemNo());
			
			costingJobDtService.save(costingJobDt);
			
			Long costJobDtUniqueId = costingJobDt.getUniqueId(); 
			
			
			
			
			CostingJobDt uniqueCostJobDt = costingJobDtService.findByUniqueId(costJobDtUniqueId);
			
			
			//--------new entry in CostJobStnDt-------//
			
			List<CostStnDt> costStnDts = costStnDtService.findByItemNoAndCostingDtAndDeactive(itemNo, costingDt, false);
			
			
			for(CostStnDt costStnDt : costStnDts){
				
				CostJobStnDt costJobStnDt = new CostJobStnDt();
				
				costJobStnDt.setStoneType(costStnDt.getStoneType());
				costJobStnDt.setCostingJobDt(uniqueCostJobDt);
				costJobStnDt.setCostingJobMt(costingJobMt);
				costJobStnDt.setShape(costStnDt.getShape());
				costJobStnDt.setShapeNm(costStnDt.getShape().getName());
				costJobStnDt.setQuality(costStnDt.getQuality());
				costJobStnDt.setQualityNm(costStnDt.getQuality().getName());
				costJobStnDt.setSizeGroup(costStnDt.getSizeGroup());
				costJobStnDt.setSizeGroupNm((costStnDt.getSizeGroup() != null ? costStnDt.getSizeGroup().getName() : ""));
				costJobStnDt.setSetting(costStnDt.getSetting());
				costJobStnDt.setSettingType(costStnDt.getSettingType());
				costJobStnDt.setOrderDt(costStnDt.getOrderDt());
				costJobStnDt.setBagSrNo(costStnDt.getBagSrNo());
				costJobStnDt.setSize(costStnDt.getSize());
				costJobStnDt.setSieve(costStnDt.getSieve());
				costJobStnDt.setStone(costStnDt.getStone());
				costJobStnDt.setCarat(costStnDt.getCarat());
				costJobStnDt.setStnRate(costStnDt.getStnRate());
				costJobStnDt.setCreatedBy(principal.getName());
				costJobStnDt.setCreatedDate(new java.util.Date());
				costJobStnDt.setItemNo(itemNo);
				costJobStnDt.setStoneInwardDt(costJobStnDt.getStoneInwardDt());
				costJobStnDt.setHandlingRate(costJobStnDt.getHandlingRate());
				costJobStnDt.setSetRate(costJobStnDt.getSetRate());
				
				costJobStnDtService.save(costJobStnDt);
				
			}
			
			
			
			//--------new entry in CostJobCompDt-------//
			
			List<CostCompDt> costCompDts =  costCompDtService.findByItemNoAndCostingDtAndDeactive(itemNo, costingDt, false);
			
			
			for(CostCompDt costCompDt : costCompDts){
				
				CostJobCompDt costJobCompDt = new CostJobCompDt();
				
				costJobCompDt.setCostingJobDt(uniqueCostJobDt);
				costJobCompDt.setCostingJobMt(costingJobMt);
				costJobCompDt.setComponent(costCompDt.getComponent());
				costJobCompDt.setPurity(costCompDt.getPurity());
				costJobCompDt.setColor(costCompDt.getColor());
				costJobCompDt.setOrderDt(costCompDt.getOrderDt());
				costJobCompDt.setBagMt(costCompDt.getBagMt());	
				costJobCompDt.setMetalWt(costCompDt.getMetalWt());
				costJobCompDt.setPurityConv(costCompDt.getPurityConv());
				costJobCompDt.setCreatedBy(principal.getName());
				costJobCompDt.setCreatedDate(new java.util.Date());
				costJobCompDt.setItemNo(itemNo);
				costJobCompDt.setCompPcs(costCompDt.getCompPcs());
		
				costJobCompDtService.save(costJobCompDt);
				
			}
			
			
			//--------new entry in CostJobLabDt-------//
			
			
			List<CostLabDt> costLabDts = costLabDtService.findByItemNoAndCostingDtAndDeactive(itemNo, costingDt, false);
			
			for(CostLabDt costLabDt:costLabDts){
				
				CostJobLabDt costJobLabDt = new CostJobLabDt();
				
				costJobLabDt.setCostingJobMt(costingJobMt);
				costJobLabDt.setCostingJobDt(uniqueCostJobDt);
				costJobLabDt.setLabourType(costLabDt.getLabourType());
				costJobLabDt.setLabourRate(costLabDt.getLabourRate());
				costJobLabDt.setOrderDt(costLabDt.getOrderDt());
				costJobLabDt.setPcsWise(costLabDt.getPcsWise());
				costJobLabDt.setGramWise(costLabDt.getGramWise());
				costJobLabDt.setPercentWise(costLabDt.getPercentWise());
				costJobLabDt.setBagmt(costLabDt.getBagmt());
				costJobLabDt.setItemNo(itemNo);
				costJobLabDt.setCreatedDate(new java.util.Date());
				costJobLabDt.setCreatedBy(principal.getName());
				
				costJobLabDtService.save(costJobLabDt);
				
			}
			
			
			
		} // main for loop ---->>>>
		
		
		
		} catch (Exception e) {
			System.out.println("------->>>>>>>"+e);
			retVal = "-2";
		
		}
		
		
		return retVal;
		
	}*/
	
	
	
	
	
	
}
