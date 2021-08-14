package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DecimalFormat;
import java.util.List;

import javax.validation.Valid;

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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Component;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ComponentPurchaseDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ComponentPurchaseMt;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IComponentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IComponentPurchaseDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IComponentPurchaseMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class ComponentPurchaseDtController {

	@Autowired
	private IComponentPurchaseMtService componentPurchaseMtService;
	
	@Autowired
	private IComponentPurchaseDtService componentPurchaseDtService;
	
	@Autowired
	private IMetalService metalService;
	
	@Autowired
	private IComponentService componentService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IColorService colorService;
	
	@RequestMapping("/componentPurchaseDt/listing")
	@ResponseBody
	public String componentPurchaseDtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "mtId", required = false) Integer mtId,
			Principal principal) {

		StringBuilder sb = new StringBuilder();
		
		ComponentPurchaseMt componentPurchaseMt = componentPurchaseMtService.findOne(mtId);

		List<ComponentPurchaseDt> componentPurchaseDts= componentPurchaseDtService.findByComponentPurchaseMtAndDeactive(componentPurchaseMt, false);
		
		sb.append("{\"total\":").append(componentPurchaseDts.size()).append(",\"rows\": [");
		
		for (ComponentPurchaseDt componentPurchaseDt : componentPurchaseDts) {
			sb.append("{\"id\":\"")
					.append(componentPurchaseDt.getId())
					.append("\",\"metal\":\"")
					.append((componentPurchaseDt.getMetal() != null ? componentPurchaseDt.getMetal().getName() : ""))
					.append("\",\"component\":\"")
					.append((componentPurchaseDt.getComponent() != null ? componentPurchaseDt.getComponent().getName() : ""))
					.append("\",\"purity\":\"")
					.append((componentPurchaseDt.getPurity() != null ? componentPurchaseDt.getPurity().getName() : ""))
					.append("\",\"color\":\"")
					.append((componentPurchaseDt.getColor() != null ? componentPurchaseDt.getColor().getName() : ""))
					.append("\",\"department\":\"")
					.append((componentPurchaseDt.getDepartment() != null ? componentPurchaseDt.getDepartment().getName() : ""))
					.append("\",\"metalWt\":\"")
					.append(componentPurchaseDt.getMetalWt())
					.append("\",\"rate\":\"")
					.append(componentPurchaseDt.getRate())
					.append("\",\"amount\":\"")
					.append(componentPurchaseDt.getAmount())
					.append("\",\"qty\":\"")
					.append(componentPurchaseDt.getQty())
					.append("\",\"deactive\":\"")
					.append(componentPurchaseDt.getDeactive() ? "Yes" : "No");
								
					sb.append("\",\"action1\":\"")
					
					.append("<a href='javascript:editComponentPurchaseDt(").append(componentPurchaseDt.getId())
					.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				
					.append("\",\"action2\":\"")
					.append("<a href='javascript:deleteComponentPurchaseDt(event,")
					  .append(componentPurchaseDt.getId()).append(", 0);' href='javascript:void(0);'")
					.append(" class='btn btn-xs btn-danger triggerRemove")
					.append(componentPurchaseDt.getId())
					.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
		
					sb.append("\"},");

		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}
	
	@RequestMapping(value = "/componentPurchaseDt/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditUser(
			@Valid @ModelAttribute("componentPurchaseDt") ComponentPurchaseDt componentPurchaseDt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "vCompPurcMtId", required = true) Integer vCompPurcMtId,
			
			RedirectAttributes redirectAttributes, Principal principal) {
		
		String retVal = "-1";
		
			retVal= componentPurchaseDtService.componentPurchaseDtSave(componentPurchaseDt, id, vCompPurcMtId, principal); 
	
		return retVal;			
		}


	@ResponseBody
	@RequestMapping(value = "/componentPurchaseDt/edit/{id}")
	public String edit(@PathVariable int id, Model model) {

		ComponentPurchaseDt componentPurchaseDt = componentPurchaseDtService.findOne(id);
		
		Double prevBalance = Math.round((componentPurchaseDt.getMetalWt()*componentPurchaseDt.getPurity().getPurityConv())*1000.0)/1000.0;
		  
		  if(componentPurchaseDt.getBalance()<prevBalance){
			  return "-2"; 
		  }

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("modalCompPurcDtId", componentPurchaseDt.getId());
		jsonObject.put("metal\\.id", componentPurchaseDt.getMetal().getId());
		jsonObject.put("component\\.id", componentPurchaseDt.getComponent().getId());
		jsonObject.put("purity\\.id", componentPurchaseDt.getPurity().getId());
		jsonObject.put("color\\.id", componentPurchaseDt.getColor().getId());
		jsonObject.put("qty", componentPurchaseDt.getQty());
		jsonObject.put("metalWt", componentPurchaseDt.getMetalWt());
		jsonObject.put("rate", componentPurchaseDt.getRate());
		jsonObject.put("amount", componentPurchaseDt.getAmount());
		jsonObject.put("vCompPurcMtId", componentPurchaseDt.getComponentPurchaseMt().getId());
		
		
		return jsonObject.toString();
	}
	
	@RequestMapping("/componentPurchaseDt/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable int id, Principal principal) {
		
	String retVal = "-1";
		
		retVal = componentPurchaseDtService.componentPurchaseDtDelete(id, principal);
		if(retVal == "-2"){
			return "-2";
		}
		
		return retVal;
	}
	
	
	@RequestMapping("/fromCompPurcList/listing")
	@ResponseBody
	public String compPurcList(
			@RequestParam(value = "metalid", required = false) Integer metalid,
			@RequestParam(value = "componentid", required = false) Integer componentid,
			@RequestParam(value = "purityid", required = false) Integer purityid,
			@RequestParam(value = "colorid", required = false) Integer colorid) {

		StringBuilder sb = new StringBuilder();
		
		
		Metal metal = metalService.findOne(metalid);
		Component component = componentService.findOne(componentid);
		Purity purity = purityService.findOne(purityid);
		Color color = colorService.findOne(colorid);
		
		DecimalFormat df = new DecimalFormat("#.###");
	
		List<ComponentPurchaseDt> componentPurchaseDts = componentPurchaseDtService.findByMetalAndComponentAndPurityAndColorAndDeactive(metal, component, purity, color, false);
				sb.append("{\"total\":").append(componentPurchaseDts.size()).append(",\"rows\": [");
				
				for (ComponentPurchaseDt componentPurchaseDt : componentPurchaseDts) {
					
					if(componentPurchaseDt.getBalance() > 0){
						
						sb.append("{\"id\":\"")
						.append(componentPurchaseDt.getId())
						.append("\",\"invNo\":\"")
						.append((componentPurchaseDt.getComponentPurchaseMt() != null ? componentPurchaseDt.getComponentPurchaseMt().getInvNo() : ""))
						.append("\",\"invDate\":\"")
						.append((componentPurchaseDt.getComponentPurchaseMt().getInvDateStr()))
						.append("\",\"metal\":\"")
						.append((componentPurchaseDt.getMetal() != null ? componentPurchaseDt.getMetal().getName() : ""))
						.append("\",\"component\":\"")
						.append((componentPurchaseDt.getComponent() != null ? componentPurchaseDt.getComponent().getName() : ""))
						.append("\",\"purity\":\"")
						.append((componentPurchaseDt.getPurity() != null ? componentPurchaseDt.getPurity().getName() : ""))
						.append("\",\"color\":\"")
						.append((componentPurchaseDt.getColor() != null ? componentPurchaseDt.getColor().getName() : ""))
						.append("\",\"department\":\"")
						.append((componentPurchaseDt.getDepartment() != null ? componentPurchaseDt.getDepartment().getName() : ""))
						.append("\",\"metalWt\":\"")
						.append(componentPurchaseDt.getMetalWt())
						.append("\",\"rate\":\"")
						.append(componentPurchaseDt.getRate())
						.append("\",\"amount\":\"")
						.append(componentPurchaseDt.getAmount())
						.append("\",\"qty\":\"")
						.append(componentPurchaseDt.getQty())
						.append("\",\"balance\":\"")
						.append(componentPurchaseDt.getBalance())
						.append("\",\"deactive\":\"")
						.append(componentPurchaseDt.getDeactive() ? "Yes" : "No");
			
						sb.append("\"},");

					}
				}
				
			
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		

		return str;
		
		
		
	}

}
