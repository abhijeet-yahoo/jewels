package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DecimalFormat;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ChangingList;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.DiamondBagging;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IDiamondBaggingService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMtService;


@Controller
@RequestMapping("manufacturing/transactions")
public class DiamondRateChangeController {

	@Autowired
	private IBagMtService bagMtService;
	
	@Autowired
	private IOrderDtService orderDtService;
	
	@Autowired
	private ITranMtService tranMtService;
	
	@Autowired
	private IDiamondBaggingService diamondBaggingService;
	
	@Value("${upload.directory.name}")
	private String uploadDirecotryName;
	
	
	@ModelAttribute("diamondBagging")
	public DiamondBagging construct(){
		return new DiamondBagging();
	}
	
	
	@RequestMapping("/diamondRateChange")
	public String diamondRateChange(){
		return "diamondRateChange";
	}
	
	
	
	@RequestMapping("/diaRateChange/diamondChanging/details")
	@ResponseBody
	public String bagDetails(
			@RequestParam(value = "bagName", required = false) String name) {

		String uploadFilePath = "/jewels/uploads/" + uploadDirecotryName.replaceAll("\\\\", "/") + "/design/";
		return bagMtService.diaRateChngDetails(name, uploadFilePath);
	
	}
	
	
	//-----------//--diamond Breakup list---//
	
	@RequestMapping("/impstnAdj/diamondRateChange/listing")
	@ResponseBody
	public String impStnAdjDetails(
			@RequestParam(value = "bagName", required = false) String name) {
		
		StringBuilder sb = new StringBuilder();
		
		BagMt bagMt = bagMtService.findByName(name);
		
		List<ChangingList> changingLists = diamondBaggingService.getListForDiamondChanging(bagMt.getId());
		
		DecimalFormat df = new DecimalFormat("#.###");
		
		sb.append("{\"total\":").append(1000).append(",\"rows\": [");
		

		for(ChangingList diamondBagging:changingLists){
			
			if(diamondBagging.getBalCarat() > 0 && diamondBagging.getBalStone() > 0){
			
				sb.append("{\"id\":\"")
						.append(diamondBagging.getId())
						.append("\",\"stoneType\":\"")
						.append((diamondBagging.getStoneType() != null ? diamondBagging.getStoneType().getName() : ""))
						.append("\",\"shape\":\"")
						.append((diamondBagging.getShape() != null ? diamondBagging.getShape().getName() : ""))
						.append("\",\"subShape\":\"")
						.append((diamondBagging.getSubShape() != null ? diamondBagging.getSubShape().getName() : ""))
						.append("\",\"quality\":\"")
						.append((diamondBagging.getQuality() != null ? diamondBagging.getQuality().getName() : ""))
						.append("\",\"mm\":\"")
						.append((diamondBagging.getSize() != null ? diamondBagging.getSize() : ""))
						.append("\",\"sieve\":\"")
						.append((diamondBagging.getSieve() != null ? diamondBagging.getSieve() : ""))
						.append("\",\"sizeGroup\":\"")
						.append((diamondBagging.getSizeGroup() != null ? diamondBagging.getSizeGroup().getName() : ""))
						.append("\",\"stones\":\"")
						.append(diamondBagging.getBalStone())
						.append("\",\"carat\":\"")
						.append(df.format(diamondBagging.getBalCarat()))
						.append("\",\"stnRate\":\"")
						.append((diamondBagging.getStnRate() != null ? diamondBagging.getStnRate() : ""))
						.append("\",\"setting\":\"")
						.append((diamondBagging.getSetting() != null ? diamondBagging.getSetting().getName() : ""))
						.append("\",\"setType\":\"")
						.append((diamondBagging.getSettingType() != null ? diamondBagging.getSettingType().getName() : ""))
						.append("\",\"action1\":\"")
						.append("<a href='/jewels/manufacturing/transactions/diamondChangingBreakup/")
						.append(diamondBagging.getId())
						.append(".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-plus'></span>&nbsp;Add</a>")
						.append("\"},");
				
				}
					
			}
			
	
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";
		
		
		return str;
		
	}
	
	
	
	@ResponseBody
	@RequestMapping(value="/diamondRate/changeUpdate", method=RequestMethod.POST)
	public String updateRate(
			@Valid @ModelAttribute("diamondBagging") DiamondBagging diamondBagging,
			BindingResult result, 
			@RequestParam(value = "id") Integer id,
			@RequestParam(value = "pODIds") String pOIds,
			@RequestParam(value = "tempStnRate") String tempRate,
			RedirectAttributes redirectAttributes, Principal principal){
		
		
		
		System.out.println(pOIds);
		System.out.println(tempRate);
		
		String retVal = "-1";
		
		String pId[] 	= pOIds.split(",");
		String pRate[]  = tempRate.split(",");
		
		
		for(int i = 0; i < pId.length ; i++){
			Double rate = Double.parseDouble(pRate[i]);
			
			if(rate == 0){
				retVal = "-2";
			}
		}
		
		
		
		for(int j = 0; j < pId.length; j++){
			
			DiamondBagging diamondBagging2 = diamondBaggingService.findOne(Integer.parseInt(pId[j]));
			
			List<DiamondBagging> diaBag = diamondBaggingService.findByBagMtAndStoneInwardDtAndDeactive(diamondBagging2.getBagMt(), diamondBagging2.getStoneInwardDt(), false);
			
			
			
			for(DiamondBagging diamondBaggingUpdate : diaBag){
				
			
				
				diamondBaggingUpdate.setStnRate(Double.parseDouble(pRate[j]));
				diamondBaggingUpdate.setModiBy(principal.getName());
				diamondBaggingUpdate.setModiDate(new java.util.Date());
				
				diamondBaggingService.save(diamondBaggingUpdate);
				
			}
			
		}
		
		return retVal;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping("/diaRateChange/autoFill/list")
	@ResponseBody
	public String bagList(
			@RequestParam(value = "term", required = false) String name) {
		
		Page<BagMt> bagMtList = bagMtService.findByName(15, 0, "name", "ASC", name.toUpperCase(), true);
		
		StringBuilder sb = new StringBuilder();

		for (BagMt bagMt : bagMtList) {
			sb.append("\"").append(bagMt.getName()).append("\", ");
		}

		String str = "[" + sb.toString().trim();
		str = (str.lastIndexOf(",") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]";

		return str;
	}
	
	
	
}
