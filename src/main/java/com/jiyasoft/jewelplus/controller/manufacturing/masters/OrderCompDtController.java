package com.jiyasoft.jewelplus.controller.manufacturing.masters;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.json.JSONObject;
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

import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Component;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignComponent;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.FindingRateMast;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderMt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IComponentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignComponentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IFindingRateMastService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderDtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderMtService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompInwardDtService;

@RequestMapping("/manufacturing/masters")
@Controller
public class OrderCompDtController {
	
	@Autowired
	private IDesignComponentService designComponentService;

	@Autowired
	private IOrderCompDtService orderCompDtService;
	
	@Autowired
	private IOrderMtService orderMtService;
	
	@Autowired
	private IOrderDtService orderDtService;
	
	@Autowired
	private IComponentService componentService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private ICompInwardDtService compInwardDtService;
	
	@Autowired
	private IFindingRateMastService findingRateMastService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private UserService userService;
	
	
	@ModelAttribute("orderCompDt")
	public OrderCompDt constructCompDt() {
		return new OrderCompDt();
	}
	
	@Value("${netWtWithComp}")
	private Boolean netWtWithCompFlg;
	
	
	@RequestMapping("/orderCompDt/listing")
	@ResponseBody
	public String costCompListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "orderDtId", required = false) Integer orderDtId,Principal principal,
			@RequestParam(value = "canViewFlag", required = false) Boolean canViewFlag) {
		
			User user = userService.findByName(principal.getName());
			UserRole userRole = userRoleService.findByUser(user);
			MenuMast menuMast = menuMastService.findByMenuNm("order");
			RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
			
		/*
		 * String disable = ""; if(canViewFlag){ disable = "disabled = 'disabled'";
		 * }else{ System.err.println("in else view"); }
		 */
		

		StringBuilder sb = new StringBuilder();
		
		
		
		sb.append("{\"total\":").append(orderCompDtService.count())
		.append(",\"rows\": [");
		
		OrderDt orderDt = orderDtService.findOne(orderDtId);
		List<OrderCompDt> orderCompDts = orderCompDtService.findByOrderDtAndDeactive(orderDt, false);
		
		
		if(orderCompDts.size() > 0){
			for(OrderCompDt orderCompDt:orderCompDts){
				
			sb.append("{\"id\":\"")
				.append(orderCompDt.getId())
				.append("\",\"compName\":\"")
				.append((orderCompDt.getComponent() != null ? orderCompDt.getComponent().getName() : ""))
				.append("\",\"kt\":\"")
				.append((orderCompDt.getPurity() != null ? orderCompDt.getPurity().getName() : ""))
				.append("\",\"color\":\"")
				.append((orderCompDt.getColor() != null ? orderCompDt.getColor().getName() : ""))
				.append("\",\"metalWt\":\"")
				.append((orderCompDt.getMetalWt() != null ? orderCompDt.getMetalWt() : ""))
				.append("\",\"rate\":\"")
				.append((orderCompDt.getCompRate() != null ? orderCompDt.getCompRate() : ""))
				.append("\",\"compQty\":\"")
				.append((orderCompDt.getCompQty() != null ? orderCompDt.getCompQty() : ""))
				.append("\",\"value\":\"")
				.append((orderCompDt.getCompValue() != null ? orderCompDt.getCompValue() : ""))
				.append("\",\"rLock\":\"")
				.append(orderCompDt.getrLock());
			
			if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
					userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
				if(!canViewFlag){
				
				sb.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doCompDtLockUnLock(")
				.append(orderCompDt.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>");
				
				sb.append("\",\"action1\":\"");
				
				sb.append("<a href='javascript:editOrderCompDt(")
				.append(orderCompDt.getId());	
				
				sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
				
				sb.append("\",\"action2\":\"");
				
					sb.append("<a href='javascript:deleteOrderCompDt(event,")
					.append(orderCompDt.getId());
						
				
				sb.append(");' class='btn btn-xs btn-danger triggerRemove")
				.append(orderCompDt.getId())
				.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
				
				}else{
					sb.append("\",\"action1\":\"")
					.append("")
					.append("\",\"action2\":\"")
					.append("")
					.append("\",\"actionLock\":\"")
					.append("");
				}
				
				sb.append("\"},");		
			
		}else
		{
			if(!canViewFlag){
				
				sb.append("\",\"actionLock\":\"")
				.append("<a href='javascript:doCompDtLockUnLock(")
				.append(orderCompDt.getId())
				.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Lock-Unlock</a>");
				
				sb.append("\",\"action1\":\"");
				if(roleRights.getCanEdit()){
				sb.append("<a href='javascript:editOrderCompDt(")
				.append(orderCompDt.getId());	
				}else{
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(");' class='btn btn-xs btn-warning'><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
				
				sb.append("\",\"action2\":\"");
				if(roleRights.getCanDelete()){
					sb.append("<a href='javascript:deleteOrderCompDt(event,")
					.append(orderCompDt.getId());
					
				}else{
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(");' class='btn btn-xs btn-danger triggerRemove")
				.append(orderCompDt.getId())
				.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
				
				}else{
					sb.append("\",\"action1\":\"")
					.append("")
					.append("\",\"action2\":\"")
					.append("")
					.append("\",\"actionLock\":\"")
					.append("");
				}
				
			sb.append("\"},");
			}
		
			}
		}
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		System.out.println(str);
		return str;
		
	}
	
	
	@RequestMapping("/orderCompDt/add")
	public String add(Model model,Principal principal) {
		model.addAttribute("componentMap", componentService.getComponentList());
		model.addAttribute("purityMap", purityService.getPurityList());
		model.addAttribute("colorMap", colorService.getColorList());
		return "orderCompDt/add";
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/orderCompDt/add" , method = RequestMethod.POST)
	public String addEdit(@Valid @ModelAttribute("orderCompDt") OrderCompDt orderCompDt,
			BindingResult result,
			@RequestParam(value = "id") Integer id,
			@RequestParam(value = "forCompOrderMtId") Integer orderMtId,
			@RequestParam(value = "forCompOrderDtId") Integer orderDtId,
			RedirectAttributes redirectAttributes, Principal principal){
		
		String retVal = "";

		try {
			retVal = orderCompDtService.transactionalSave(orderCompDt, id, orderMtId, orderDtId, principal,netWtWithCompFlg);
		} catch (Exception e) {
			e.printStackTrace();
			retVal = "error";
		}
		
		return retVal;
		
	}
	
	
	
	@ResponseBody
	@RequestMapping("/orderCompDt/lockUnlock")
	public String lockUnlockCostCompDt(
			@RequestParam(value="compDtId")Integer compDtId){
		
		String retVal = "-1";
		OrderCompDt orderCompDt = orderCompDtService.findOne(compDtId);
		
			if(orderCompDt.getrLock() == true){
				orderCompDt.setrLock(false);
			}else{
				orderCompDt.setrLock(true);
			}
			
		orderCompDtService.save(orderCompDt);
		
		return retVal;
	}
	
	
	@RequestMapping("/orderCompDt/validationEdit")
	@ResponseBody
	public String validation(
			@RequestParam(value = "compId")Integer compId){
		
		String retVal = "";
		
		OrderCompDt orderCompDt = orderCompDtService.findOne(compId);
		if(orderCompDt.getrLock() == true){
			retVal = "-1";
		}
		
		return retVal;
	}
	
	
	
	@RequestMapping("/orderCompDt/edit/{id}")
	public String editOrderCompDt(@PathVariable int id,Model model){
		OrderCompDt orderCompDt = orderCompDtService.findOne(id);
		model.addAttribute("orderCompDt",orderCompDt);
		model.addAttribute("componentMap", componentService.getComponentList());
		model.addAttribute("purityMap", purityService.getPurityList());
		model.addAttribute("colorMap", colorService.getColorList());
		
		return "orderCompDt/add";
	}
	
	
	@ResponseBody
	@RequestMapping("/orderCompDt/delete/{id}")
	public String delete(@PathVariable int id,Principal principal){
		
		String retVal = "-1";
		OrderCompDt orderCompDt = orderCompDtService.findOne(id);
		try {
			orderCompDtService.transactionalDelete(orderCompDt,netWtWithCompFlg);
		} catch (Exception e) {
			e.printStackTrace();
			retVal = "error";
		}
		
		return retVal;
	}
	
	
	@ResponseBody
	@RequestMapping("/orderCompDt/LockUnlockAll")
	public String lockUnlockAll(
			@RequestParam(value="mtId")Integer mtId,
			@RequestParam(value="status")Integer status){
		
		String retVal = "-1";
		OrderMt orderMt = orderMtService.findOne(mtId);
		List<OrderCompDt> orderCompDts = orderCompDtService.findByOrderMtAndDeactive(orderMt, false);
		Boolean setValue ;
				
		if(status == 1){
			setValue = true;
		}else{
			setValue = false;
		}
		
		for(OrderCompDt orderCompDt:orderCompDts){
			orderCompDt.setrLock(setValue);
			orderCompDtService.save(orderCompDt);
		}
		
		return retVal;
	}
	

	
	
	
	
		//--------comp rate-------//
		@ResponseBody
		@RequestMapping("/orderCompDt/rateFromMaster")
		public String compRateMast(
				@RequestParam(value="componentId") Integer componentId,
				@RequestParam(value="purityId") Integer purityId,
				@RequestParam(value="mtPartyId") Integer partyId){
			
			
			Component component = componentService.findOne(componentId);
			Purity 	  purity	= purityService.findOne(purityId);
			Party	  party		= partyService.findOne(partyId);
			
			
			JSONObject jsonObject = new JSONObject();
			
			FindingRateMast findingRateMast = findingRateMastService.findByComponentAndPartyAndPurityAndDeactive(component, party,purity,false);
			
			if(findingRateMast != null){
				if(findingRateMast.getPerPcRate().equals(true)){
					jsonObject.put("rate", findingRateMast.getRate());
					jsonObject.put("perPcRate", true);
					jsonObject.put("perGramRate", false);
				}else{
					jsonObject.put("rate", findingRateMast.getRate());
					jsonObject.put("perPcRate", false);
					jsonObject.put("perGramRate", true);
					
				}
			}
			
			return jsonObject.toString();
		}
	
		
		
		@RequestMapping("/orderCompDt/updateAsPerMaster")
		@ResponseBody
		public String compListing(@RequestParam(value = "orderDtId", required = false) Integer orderDtId, Principal principal) {
			
			String retVal="-1";
			
			OrderDt orderDt= orderDtService.findOne(orderDtId);
			List<OrderCompDt> orderCompDts= orderCompDtService.findByDesign(orderDt.getOrderMt(), orderDt, principal, false);
			for (OrderCompDt orderCompDt : orderCompDts) {
				
				orderCompDt.setDeactive(true);
				orderCompDt.setDeactiveDt(new Date());
				orderCompDtService.save(orderCompDt);
				
				
			}
			
			
			List<DesignComponent> designComponents = designComponentService.findByDesign(orderDt.getDesign());
			orderCompDtService.setOrderCompDt(designComponents, orderDt.getOrderMt(), orderDt, principal);
			
			retVal="1";
		
		
			
	return retVal;
		}
	
	
	
}
