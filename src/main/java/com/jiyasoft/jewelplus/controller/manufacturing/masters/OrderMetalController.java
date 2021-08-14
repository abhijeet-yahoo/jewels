package com.jiyasoft.jewelplus.controller.manufacturing.masters;

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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILookUpMastService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;



@RequestMapping("/manufacturing/masters")
@Controller
public class OrderMetalController {
	
	@Autowired
	private IOrderDtService orderDtService;
	
	@Autowired
	private IOrderMetalService orderMetalService;
	
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
	
	
	@ModelAttribute("orderMetal")
	public OrderMetal construct() {
		return new OrderMetal();
	}
	
	@RequestMapping("/orderMetal")
	public String orderMetal(Model model) {
		return "orderMetal";
	}
	
	
	
	
	
	
	@RequestMapping("/orderMetal/listing")
	@ResponseBody
	public String costMetalListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "orderDtId", required = false) Integer orderDtId,Principal principal) {

		StringBuilder sb = new StringBuilder();
		
		OrderDt orderDt = orderDtService.findOne(orderDtId);
		List<OrderMetal> orderMetals = orderMetalService.findByOrderDtAndDeactive(orderDt, false);
		
		sb.append("{\"total\":").append(orderMetals.size()).append(",\"rows\": [");
		
		for(OrderMetal orderMetal:orderMetals){
		
			sb.append("{\"id\":\"")
				.append(orderMetal.getId())
				.append("\",\"purity\":\"")
				.append((orderMetal.getPurity() != null ? orderMetal.getPurity().getName() : ""))
				.append("\",\"color\":\"")
				.append((orderMetal.getColor() != null ? orderMetal.getColor().getName() : ""))
				.append("\",\"partNm\":\"")
				.append((orderMetal.getPartNm() != null ? orderMetal.getPartNm().getFieldValue() : ""))
				//.append("\",\"waxWt\":\"")
				//.append((orderMetal.getWaxWt() != null ? orderMetal.getWaxWt() : ""))
				.append("\",\"qty\":\"")
				.append((orderMetal.getMetalPcs() != null ? orderMetal.getMetalPcs() : ""))
				.append("\",\"metalWt\":\"")
				.append((orderMetal.getMetalWeight() != null ? orderMetal.getMetalWeight() : ""))
				.append("\",\"metalRate\":\"")
				.append((orderMetal.getMetalRate() != null ? orderMetal.getMetalRate() : ""))
				.append("\",\"perGramRate\":\"")
				.append((orderMetal.getPerGramRate() != null ? orderMetal.getPerGramRate() : ""))
				.append("\",\"lossPerc\":\"")
				.append((orderMetal.getLossPerc() != null ? orderMetal.getLossPerc() : ""))
				.append("\",\"metalValue\":\"")
				.append((orderMetal.getMetalValue() != null ? orderMetal.getMetalValue() : ""))
				.append("\",\"mainMetal\":\"")
				.append((orderMetal.getMainMetal() != null ? (orderMetal.getMainMetal() ? orderMetal.getMainMetal() : false) : false));
				
				/*sb.append("\",\"action1\":\"");
				//if (roleRights.getCanEdit()) {
					sb.append("<a href='javascript:editMetal(").append(orderMetal.getId());
				//} else {
					//sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				//}
				sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
				
				
				sb.append("\",\"action2\":\"");
				//if (roleRights.getCanDelete()) {
					sb.append("<a onClick='javascript:deleteDesignMetal(event, ")
						.append(orderMetal.getId()).append(", 0);' href='javascript:void(0);'");
				//} else {
					//sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				//}
				sb.append(" class='btn btn-xs btn-danger triggerRemove")
				 .append(orderMetal.getId())
				 .append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")*/
				 sb.append("\"},");
			
			}
		
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
		
	}
	
	

	
	
	
	
	
	
	//-----listing from orderMetal------//
	@RequestMapping("/orderMetal/listing/fromOrderDt")
	@ResponseBody
	public String orderMetalListing(
			@RequestParam(value = "dtId", required = false) Integer dtId) {


			StringBuilder sb = new StringBuilder();
			OrderDt orderDt = orderDtService.findOne(dtId);

			List<OrderMetal> orderMetals = orderMetalService.findByOrderDtAndDeactive(orderDt, false);
			int srNo =0; 
			sb.append("{\"total\":").append(orderMetals.size()).append(",\"rows\": [");
			for (OrderMetal orderMetal : orderMetals) {

				Boolean flag =orderMetalService.orderPartValidation(orderMetal.getOrderDt().getId(),orderMetal.getPartNm().getId()); 
				
				
				sb.append("{\"id\":\"")
					.append(orderMetal.getId())
					
					.append("\",\"srNo\":\"")
					.append(srNo++)
					.append("\",\"purity\":\"")
					.append((orderMetal.getPurity() != null ? orderMetal.getPurity().getName() : ""))
					.append("\",\"color\":\"")
					.append((orderMetal.getColor() != null ? orderMetal.getColor().getName() : ""))
					.append("\",\"partNm\":\"")
					.append((orderMetal.getPartNm() != null ? orderMetal.getPartNm().getFieldValue() : ""))
					.append("\",\"processLoss\":\"")
					.append((orderMetal.getProcessLoss() != null ? orderMetal.getProcessLoss() : ""))
					.append("\",\"qty\":\"")
					.append((orderMetal.getMetalPcs() != null ? orderMetal.getMetalPcs() : ""))
					.append("\",\"flag\":\"")
					.append(flag)
					.append("\",\"metalWt\":\"")
					.append((orderMetal.getMetalWeight() != null ? orderMetal.getMetalWeight() : ""))
					.append("\",\"mainMetal\":\"")
					.append((orderMetal.getMainMetal() != null ? (orderMetal.getMainMetal() ? orderMetal.getMainMetal() : false) : false))
					.append("\"},");
				
			}
		

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";

		return str;
	}
	
	
	//-----listing from design------//
	@RequestMapping("/orderMetal/listing/fromDesign")
	@ResponseBody
	public String orderMetalDesignListing(
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
					.append((designMetal.getLossPerc() != null ? designMetal.getLossPerc() : ""))
					.append("\",\"qty\":\"")
					.append((designMetal.getMetalPcs() != null ? designMetal.getMetalPcs() : ""))
					.append("\",\"flag\":\"")
					.append(false)
					.append("\",\"metalWt\":\"")
					.append((designMetal.getMetalWeight() != null ?Math.round((designMetal.getMetalWeight()-(designMetal.getMetalWeight()*designMetal.getLossPerc()/100))*1000.0)/1000.0 : ""))
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
	
	@RequestMapping("/orderMetal/add")
	public String add(Model model) {
		model = populateModel(model);
		return "orderMetal/add";
	}
	
	
	
	@RequestMapping(value = "/orderMetal/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditUser(@Valid @ModelAttribute("orderMetal") OrderMetal orderMetal,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "vOrderDtId", required = false) Integer dtId,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "-1";

		synchronized (this) {
			
			if (result.hasErrors()) {
				return "orderMetal/add";
			}

			
			OrderDt orderDt = orderDtService.findOne(dtId);
			
			
			
			
			if (id == null || id.equals("") || (id != null && id == 0)) {
				
				OrderMetal orderMetalCheck = orderMetalService.findByOrderDtAndDeactiveAndMainMetal(orderDt, false, true);
				if(orderMetalCheck != null){
					if(orderMetal.getMainMetal()){
						return retVal = "-3";	
					}
					
				}
				
				orderMetal.setCreatedBy(principal.getName());
				orderMetal.setCreateDate(new java.util.Date());
				orderMetal.setOrderDt(orderDt);
				orderMetal.setOrderMt(orderDt.getOrderMt());
				
				
				
				
				retVal = "1";
				
			} else {
				
				OrderMetal orderMetalCheck = orderMetalService.findByOrderDtAndDeactiveAndMainMetal(orderDt, false, true);
				if(orderMetalCheck != null && (!orderMetalCheck.getId().equals(orderMetal.getId()))){
					if(orderMetal.getMainMetal()){
						return retVal = "-3";	
					}
					
				}
				
				orderMetal.setId(id);
				orderMetal.setModiBy(principal.getName());
				orderMetal.setModiDt(new java.util.Date());
				orderMetal.setOrderDt(orderDt);
				orderMetal.setOrderMt(orderDt.getOrderMt());
				action = "updated";
				
				
				retVal = "-2";
			}
			
			
			
			orderMetalService.save(orderMetal);
			redirectAttributes.addFlashAttribute("success", true);
			redirectAttributes.addFlashAttribute("action", action);
			
		}
		
		return retVal;
	}
	
	
	@RequestMapping(value="/orderMetal/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		OrderMetal orderMetal = null;

		model = populateModel(model);
		if (id != 0) {
			orderMetal = orderMetalService.findOne(id);
			model.addAttribute("orderMetal", orderMetal);		
		}

		return "orderMetal/add";
	}
	
	
	
	@RequestMapping("/orderMetal/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable int id) {
		orderMetalService.delete(id);
		return "1";
	}
	
	
	
	@ResponseBody
	@RequestMapping("/orderMetal/getPartNm")
	public String getPartNm(@RequestParam(value = "orderDtPkId") Integer dtId){
		
		OrderDt orderDt = orderDtService.findOne(dtId);	
		OrderMetal orderMetal = orderMetalService.findByOrderDtAndDeactiveAndMainMetal(orderDt, false, true);
		String retVal = "";
		if(orderMetal != null){
			retVal = orderMetal.getPartNm().getId().toString();
		}
		
		return retVal;
	}
	
	
	@ResponseBody
	@RequestMapping("/orderMetal/conversion")
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
		Double lossPerc =0.0;		
				if(designMetal != null){
			waxWt = designMetal.getWaxWt();
			lossPerc= designMetal.getLossPerc();
				}
		
		
		if(purity != null){
			if(purity.getPurityConv() != null){
				waxWtConv = purity.getWaxWtConv();
			}
		}
		
		Double tempData = waxWt*waxWtConv;
		
		
		Double val = Math.round(((tempData)-(tempData * lossPerc/100))*1000.0)/1000.0;
		
				
		retVal = val.toString();
		
		
		return retVal;
		
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("/orderMetal/updateLossPerc")
	public String updateLossPerc(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "lossPerc", required = false) Double lossPerc,Principal principal) {
		
		String retVal="-1";
		retVal=orderMetalService.updateLossPerc(principal, id, lossPerc, netWtWithCompFlg);
		
		return retVal;
		
	}
	
	
	
	

}
