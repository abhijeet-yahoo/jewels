package com.jiyasoft.jewelplus.controller.manufacturing.masters;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.HandlingMasterFl;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IHandlingMasterFLService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;

@RequestMapping("/manufacturing/masters")
@Controller
public class HandlingMasterFLController {


	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private IHandlingMasterFLService handlingMasterFLService;
	
	
	
	@ModelAttribute("handlingMasterFl")
	public HandlingMasterFl construct(){
		return new HandlingMasterFl();
	}
	
	/*@RequestMapping("/handlingMasterFl")
	public String users(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("handlingMasterFl");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
	
			return "handlingMasterFl";
		}else
		
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}
		
		
		return "handlingMasterFl";
	}
	*/
	@RequestMapping("/handlingMasterFl/listing")
	@ResponseBody
	public String handlingMasterFlListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "partyNm", required = true) String partyNm,
			Principal principal) {
		
		
		
		Party party = partyService.findByName(partyNm);
		if(party ==null){
			String retVal="{\"total\":0,\"rows\": []}";
			return retVal;
		}
		
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("handlingMaster");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		
		

		StringBuilder sb = new StringBuilder();
		Page<HandlingMasterFl> handlingMasterFls = null;
			
		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}
		
		handlingMasterFls = handlingMasterFLService.findByParty(limit, offset, sort, order, search,true,party);
		
		sb.append("{\"total\":").append(handlingMasterFls.getTotalElements()).append(",\"rows\": [");
		
		for (HandlingMasterFl handlingMasterFl : handlingMasterFls) {
				sb.append("{\"id\":\"")
						.append(handlingMasterFl.getId())
						.append("\",\"party\":\"")
						.append(handlingMasterFl.getParty() != null ? handlingMasterFl.getParty().getName() : "")
						.append("\",\"fromDiaRate\":\"")
						.append(handlingMasterFl.getFromDiaRate() != null ? handlingMasterFl.getFromDiaRate() : "")
						.append("\",\"toDiaRate\":\"")
						.append(handlingMasterFl.getToDiaRate()!=null ?handlingMasterFl.getToDiaRate():"")
						.append("\",\"rate\":\"")
						.append(handlingMasterFl.getRate()!=null ?handlingMasterFl.getRate():"")
						.append("\",\"percentage\":\"")
						.append(handlingMasterFl.getPercentage()!=null ?handlingMasterFl.getPercentage():"");
				
				if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
						userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
				
					sb.append("\",\"action1\":\"")
							.append("<a onClick='javascript:popHandlingFlEdit(event,"+handlingMasterFl.getId()+ " )'")
							.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
							
							sb.append("\",\"action2\":\"")
								.append("<a onClick='javascript:confirmHandlingFlDelete(event,"+handlingMasterFl.getId()+ " )'")
								.append(" class='btn btn-xs btn-danger triggerRemove")
								.append(handlingMasterFl.getId())
								.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
								.append("\"},");
		
						}else{
							sb.append("\",\"action1\":\"");
							if (roleRights.getCanEdit()) {
								sb.append("<a onClick='javascript:popHandlingFlEdit(event,"+handlingMasterFl.getId()+ " )'");
							} else {
								sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
							}
								sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
								sb.append("\",\"action2\":\"");
							if (roleRights.getCanDelete()) {
								sb.append("<a onClick='javascript:confirmHandlingFlDelete(event,"+handlingMasterFl.getId()+ " )'");
							} else {
								sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
							}
							  sb.append(" class='btn btn-xs btn-danger triggerRemove")
									.append(handlingMasterFl.getId())
									.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
									.append("\"},");
		
						}
			

		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
		
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/handlingMasterFl/add", method = RequestMethod.POST)
	public String addEditHandlingMasterFl(@Valid @ModelAttribute("handlingMasterFl") HandlingMasterFl handlingMasterFl,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value="vPartyNm")String vPartyNm,
			HttpSession httpSession,Principal principal) {
		
		String retVal = "error";
		synchronized (this) {
			try {
				
				
				
				if (result.hasErrors()) {
					return "Error Contact Admin";
				}
				Party party=partyService.findByName(vPartyNm);
				if(party ==null){
					return "Party Not Valid";
					
				}
				
				handlingMasterFl.setParty(party);
				
				if(handlingMasterFl.getRate() == null){
					handlingMasterFl.setRate(0.0);
				}
				
				if(handlingMasterFl.getPercentage() == null){
					handlingMasterFl.setPercentage(0.0);
				}
				
			retVal =  handlingMasterFLService.CheckDuplicate(handlingMasterFl.getFromDiaRate(), handlingMasterFl.getToDiaRate(), id,handlingMasterFl.getParty().getId());		
			
			if(retVal == "true"){
				return retVal = "Dublicate entry found, check the entry";
			}
			
			if(handlingMasterFl.getToDiaRate() < handlingMasterFl.getFromDiaRate()){
				return retVal = "ToDiaRate Always Greater Than FromDiaRate";
			}
			
			if(handlingMasterFl.getRate() > 0 && handlingMasterFl.getPercentage() > 0){
				return retVal = "Either Rate Or Percentage Is Required, Not Both";
			}
			
			if(handlingMasterFl.getRate() == 0 && handlingMasterFl.getPercentage() == 0){
				return retVal = "Only One is compulsary , Either Rate Or Percentage";
			}
						
				if (id == null || id.equals("") || (id != null && id == 0)) {
					
					
					
					handlingMasterFl.setCreatedBy(principal.getName());
					handlingMasterFl.setCreatedDt(new java.util.Date());
					retVal = "1";
				}else{
					
					
			
									
					handlingMasterFl.setId(id);
					handlingMasterFl.setModiBy(principal.getName());
					handlingMasterFl.setModiDt(new java.util.Date());
					retVal = "1";
				 }

				handlingMasterFLService.save(handlingMasterFl);
			} catch (Exception e) {
				e.printStackTrace();
				retVal = "Erorr : Record Not Saved("+e+") Contact Support";
			}
			
		}
		
		return retVal;

	}
	
	@ResponseBody
	@RequestMapping("/handlingMasterFl/edit/{id}")
	public String  edit(@PathVariable int id, Model model,HttpSession httpSession,Principal principal) {
			
		HandlingMasterFl handlingMasterFl =handlingMasterFLService.findOne(id);
		
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", handlingMasterFl.getId());
		jsonObject.put("fromDiaRate", handlingMasterFl.getFromDiaRate() !=null?handlingMasterFl.getFromDiaRate():0.0);
		jsonObject.put("toDiaRate", handlingMasterFl.getToDiaRate()!=null?handlingMasterFl.getToDiaRate():0.0);
		jsonObject.put("rate", handlingMasterFl.getRate()!=null?handlingMasterFl.getRate():"");
		jsonObject.put("party", handlingMasterFl.getParty()!=null?handlingMasterFl.getParty().getName():"");
		jsonObject.put("percentage", handlingMasterFl.getPercentage()!=null?handlingMasterFl.getPercentage():"");
		
		return jsonObject.toString();
		
	}
	
	@ResponseBody
	@RequestMapping("/handlingMasterFl/delete/{id}")
	public String delete(@PathVariable int id) {
		
		handlingMasterFLService.delete(id);

		return "redirect:/manufacturing/masters/handlingMasterFl.html";
	}
	
	
}
