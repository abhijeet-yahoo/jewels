package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMetal;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILookUpMastService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotMetalService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class QuotMetalController {
	
	@Autowired
	private IQuotDtService quotDtService;
	
	@Autowired
	private IQuotMetalService quotMetalService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private ILookUpMastService lookUpMastService;
	
	@Autowired
	private IDesignMetalService designMetalService;
	
	@Autowired
	private IDesignService designService;
	
	@Value("${netWtWithComp}")
	private Boolean netWtWithCompFlg;
	
	
	@ModelAttribute("quotMetal")
	public QuotMetal construct() {
		return new QuotMetal();
	}
	
	@RequestMapping("/quotMetal")
	public String quotMetal(Model model) {
		return "quotMetal";
	}
	
	
	
	
	
	
	@RequestMapping("/quotMetal/listing")
	@ResponseBody
	public String costMetalListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "quotDtId", required = false) Integer quotDtId,Principal principal) {

		StringBuilder sb = new StringBuilder();
		
		QuotDt quotDt = quotDtService.findOne(quotDtId);
		List<QuotMetal> quotMetals = quotMetalService.findByQuotDtAndDeactive(quotDt, false);
		
		sb.append("{\"total\":").append(quotMetals.size()).append(",\"rows\": [");
		
		for(QuotMetal quotMetal:quotMetals){
		
			sb.append("{\"id\":\"")
				.append(quotMetal.getId())
				.append("\",\"purity\":\"")
				.append((quotMetal.getPurity() != null ? quotMetal.getPurity().getName() : ""))
				.append("\",\"color\":\"")
				.append((quotMetal.getColor() != null ? quotMetal.getColor().getName() : ""))
				.append("\",\"partNm\":\"")
				.append((quotMetal.getPartNm() != null ? quotMetal.getPartNm().getFieldValue() : ""))
				//.append("\",\"waxWt\":\"")
				//.append((quotMetal.getWaxWt() != null ? quotMetal.getWaxWt() : ""))
				.append("\",\"qty\":\"")
				.append((quotMetal.getMetalPcs() != null ? quotMetal.getMetalPcs() : ""))
				.append("\",\"metalWt\":\"")
				.append((quotMetal.getMetalWeight() != null ? quotMetal.getMetalWeight() : ""))
				.append("\",\"metalRate\":\"")
				.append((quotMetal.getMetalRate() != null ? quotMetal.getMetalRate() : ""))
				.append("\",\"perGramRate\":\"")
				.append((quotMetal.getPerGramRate() != null ? quotMetal.getPerGramRate() : ""))
				.append("\",\"lossPerc\":\"")
				.append((quotMetal.getLossPerc() != null ? quotMetal.getLossPerc() : ""))
				.append("\",\"metalValue\":\"")
				.append((quotMetal.getMetalValue() != null ? quotMetal.getMetalValue() : ""))
				.append("\",\"mainMetal\":\"")
				.append((quotMetal.getMainMetal() != null ? (quotMetal.getMainMetal() ? quotMetal.getMainMetal() : false) : false));
				
				/*sb.append("\",\"action1\":\"");
				//if (roleRights.getCanEdit()) {
					sb.append("<a href='javascript:editMetal(").append(quotMetal.getId());
				//} else {
					//sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				//}
				sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
				
				
				sb.append("\",\"action2\":\"");
				//if (roleRights.getCanDelete()) {
					sb.append("<a onClick='javascript:deleteDesignMetal(event, ")
						.append(quotMetal.getId()).append(", 0);' href='javascript:void(0);'");
				//} else {
					//sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				//}
				sb.append(" class='btn btn-xs btn-danger triggerRemove")
				 .append(quotMetal.getId())
				 .append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")*/
				 sb.append("\"},");
			
			}
		
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
		
	}
	
	

	
	
	
	
	
	
	//-----listing from quotMetal------//
	@RequestMapping("/quotMetal/listing/fromQuotDt")
	@ResponseBody
	public String quotMetalListing(
			@RequestParam(value = "dtId", required = false) Integer dtId) {


			StringBuilder sb = new StringBuilder();
			QuotDt quotDt = quotDtService.findOne(dtId);

			List<QuotMetal> quotMetals = quotMetalService.findByQuotDtAndDeactive(quotDt, false);
			int srNo =0; 
			sb.append("{\"total\":").append(quotMetals.size()).append(",\"rows\": [");
			for (QuotMetal quotMetal : quotMetals) {

				sb.append("{\"id\":\"")
				.append(quotMetal.getId())
				
				.append("\",\"srNo\":\"")
				.append(srNo++)
				.append("\",\"purity\":\"")
				.append((quotMetal.getPurity() != null ? quotMetal.getPurity().getName() : ""))
				.append("\",\"color\":\"")
				.append((quotMetal.getColor() != null ? quotMetal.getColor().getName() : ""))
				.append("\",\"partNm\":\"")
				.append((quotMetal.getPartNm() != null ? quotMetal.getPartNm().getFieldValue() : ""))
				.append("\",\"processLoss\":\"")
				.append((quotMetal.getProcessLoss() != null ? quotMetal.getProcessLoss() : 0.0))
				.append("\",\"qty\":\"")
				.append((quotMetal.getMetalPcs() != null ? quotMetal.getMetalPcs() : ""))
				.append("\",\"metalWt\":\"")
				.append((quotMetal.getMetalWeight() != null ? quotMetal.getMetalWeight() : ""))
				.append("\",\"mainMetal\":\"")
				.append((quotMetal.getMainMetal() != null ? (quotMetal.getMainMetal() ? quotMetal.getMainMetal() : false) : false))
				.append("\"},");
			
				
			}
		

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";

		return str;
	}
	
	
	//-----listing from design------//
	@RequestMapping("/quotMetal/listing/fromDesign")
	@ResponseBody
	public String quotMetalDesignListing(
			@RequestParam(value = "mainStyleNo", required = false) String mainStyleNo,
			@RequestParam(value = "colorId", required = false) Integer colorId,
			@RequestParam(value = "purityId", required = false) Integer purityId) {
		
		
		Purity purity =null;
		if(purityId !=null ){
			purity=purityService.findOne(purityId);
		}
		
		Color color =null;
		if(colorId !=null){
			color=colorService.findOne(colorId);	
		}


			StringBuilder sb = new StringBuilder();
			
			Design design = designService.findByMainStyleNoAndDeactive(mainStyleNo, false);
			List<DesignMetal> designMetals = designMetalService.findByDesignAndDeactive(design, false);
			

			sb.append("{\"total\":").append(designMetals.size()).append(",\"rows\": [");
			
			int srNo =0; 
			for (DesignMetal designMetal : designMetals) {

				sb.append("{\"id\":\"")
					.append(0)
					.append("\",\"srNo\":\"")
					.append(srNo++)
					.append("\",\"purity\":\"")
					.append(purity !=null? purity.getName():"")
					.append("\",\"color\":\"")
					.append(color !=null? color.getName():"")
					.append("\",\"partNm\":\"")
					.append((designMetal.getPartNm() != null ? designMetal.getPartNm().getFieldValue() : ""))
					.append("\",\"waxWt\":\"")
					.append((designMetal.getWaxWt() != null ? designMetal.getWaxWt() : ""))
					.append("\",\"processLoss\":\"")
					.append((designMetal.getLossPerc() != null ? designMetal.getLossPerc() : 0.0))
					.append("\",\"qty\":\"")
					.append((designMetal.getMetalPcs() != null ? designMetal.getMetalPcs() : ""))
					.append("\",\"metalWt\":\"")
					.append((designMetal.getMetalWeight() != null ? Math.round((designMetal.getMetalWeight()-(designMetal.getMetalWeight()*designMetal.getLossPerc()/100))*1000.0)/1000.0 : ""))
					.append("\",\"mainMetal\":\"")
					.append((designMetal.getMainMetal() != null ? (designMetal.getMainMetal() ? designMetal.getMainMetal() : false) : false))
					.append("\"},");
				
			}
		

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";

		return str;
	}
	
	
	private Model populateModel(Model model) {
		model.addAttribute("purityMap", purityService.getPurityList());
		model.addAttribute("colorMap", colorService.getColorList());
		model.addAttribute("lookUpMastMap", lookUpMastService.getActiveLookUpMastByType("Design Part"));
		return model;
	}
	
	@RequestMapping("/quotMetal/add")
	public String add(Model model) {
		model = populateModel(model);
		return "quotMetal/add";
	}
	
	
	
	@RequestMapping(value = "/quotMetal/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditUser(@Valid @ModelAttribute("quotMetal") QuotMetal quotMetal,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "vQuotDtId", required = false) Integer dtId,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "-1";

		synchronized (this) {
			
			if (result.hasErrors()) {
				return "quotMetal/add";
			}

			
			QuotDt quotDt = quotDtService.findOne(dtId);
			
			
			
			
			if (id == null || id.equals("") || (id != null && id == 0)) {
				
				QuotMetal quotMetalCheck = quotMetalService.findByQuotDtAndDeactiveAndMainMetal(quotDt, false, true);
				if(quotMetalCheck != null){
					if(quotMetal.getMainMetal()){
						return retVal = "-3";	
					}
					
				}
				
				quotMetal.setCreatedBy(principal.getName());
				quotMetal.setCreateDate(new java.util.Date());
				quotMetal.setQuotDt(quotDt);
				quotMetal.setQuotMt(quotDt.getQuotMt());
				
				
				
				
				retVal = "1";
				
			} else {
				
				QuotMetal quotMetalCheck = quotMetalService.findByQuotDtAndDeactiveAndMainMetal(quotDt, false, true);
				if(quotMetalCheck != null && (!quotMetalCheck.getId().equals(quotMetal.getId()))){
					if(quotMetal.getMainMetal()){
						return retVal = "-3";	
					}
					
				}
				
				quotMetal.setId(id);
				quotMetal.setModiBy(principal.getName());
				quotMetal.setModiDt(new java.util.Date());
				quotMetal.setQuotDt(quotDt);
				quotMetal.setQuotMt(quotDt.getQuotMt());
				action = "updated";
				
				
				retVal = "-2";
			}

			quotMetalService.save(quotMetal);
			redirectAttributes.addFlashAttribute("success", true);
			redirectAttributes.addFlashAttribute("action", action);
			
		}
		
		return retVal;
	}
	
	
	@RequestMapping(value="/quotMetal/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		QuotMetal quotMetal = null;

		model = populateModel(model);
		if (id != 0) {
			quotMetal = quotMetalService.findOne(id);
			model.addAttribute("quotMetal", quotMetal);		
		}

		return "quotMetal/add";
	}
	
	
	
	@RequestMapping("/quotMetal/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable int id) {
		quotMetalService.delete(id);
		return "1";
	}
	
	
	
	@ResponseBody
	@RequestMapping("/quotMetal/getPartNm")
	public String getPartNm(@RequestParam(value = "quotDtPkId") Integer dtId){
		
		QuotDt quotDt = quotDtService.findOne(dtId);	
		QuotMetal quotMetal = quotMetalService.findByQuotDtAndDeactiveAndMainMetal(quotDt, false, true);
		String retVal = "";
		if(quotMetal != null){
			retVal = quotMetal.getPartNm().getId().toString();
		}
		
		return retVal;
	}
	
	
	@ResponseBody
	@RequestMapping("/quotMetal/conversion")
	public String waxWtConversion(
			@RequestParam(value = "purityNm") String purityNm,
			@RequestParam(value = "mainStyleNo") String mainStyleNo,
			@RequestParam(value = "partNm") String partNm){
		
		
		String retVal = "-1";
		
		Purity purity = purityService.findByName(purityNm);
		Design design = designService.findByMainStyleNoAndDeactive(mainStyleNo, false);
		LookUpMast lookUpMast = lookUpMastService.findByNameAndFieldValueAndDeactive("Design Part", partNm, false);
		
		DesignMetal designMetal = designMetalService.findByDesignAndPartNmAndDeactive(design, lookUpMast, false);
		
		Double waxWt = 0.0;
		Double waxWtConv = 0.0;
		if(designMetal != null){
			waxWt = designMetal.getWaxWt()!=null?designMetal.getWaxWt():0;
		}
		
		
		if(purity != null){
			if(purity.getPurityConv() != null){
				waxWtConv = purity.getWaxWtConv()!=null?purity.getWaxWtConv():0;
			}
		}
		
		Double tempData = waxWt*waxWtConv;
		Double val = Math.round((tempData)*1000.0)/1000.0;
		
		retVal = val+"";
		
		return retVal;
		
	}
	
	
	@ResponseBody
	@RequestMapping("/quotMetal/updateLossPerc")
	public String updateLossPerc(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "lossPerc", required = false) Double lossPerc,Principal principal) {
		
		String retVal="-1";
		retVal=quotMetalService.updateLossPerc(principal, id, lossPerc, netWtWithCompFlg);
		
		return retVal;
		
	}
	

}
