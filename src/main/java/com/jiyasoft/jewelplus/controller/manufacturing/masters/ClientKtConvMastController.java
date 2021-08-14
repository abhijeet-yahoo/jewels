package com.jiyasoft.jewelplus.controller.manufacturing.masters;

import java.security.Principal;

import javax.validation.Valid;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ClientKtConvMast;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IClientKtConvService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
@RequestMapping("/manufacturing/masters")
@Controller
public class ClientKtConvMastController {
	
	@Autowired
	private IClientKtConvService clientKtConvService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;
	
	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired 
	private UserRoleService userRoleService;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private IPurityService purityService;
	
	@ModelAttribute("clientKtConvMast")
	public ClientKtConvMast construct() {
		return new ClientKtConvMast();
	}

	@RequestMapping("/clientKtConvMast")
	public String users(Model model, Principal principal){
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("clientKtConvMast");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
	
			return "clientKtConvMast";
		}else
		
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}
		
		return "clientKtConvMast";
	}
	
	  @RequestMapping("/clientKtConvMast/listing") 
	  @ResponseBody public String marketTypeMastListing(Model model,
	  @RequestParam(value = "limit", required = false) Integer limit,
	  @RequestParam(value = "offset", required = false) Integer offset,	  
	  @RequestParam(value = "sort", required = false) String sort,	  
	  @RequestParam(value = "order", required = false) String order,	  
	  @RequestParam(value = "search", required = false) String search,	  
	  @RequestParam(value = "opt", required = true) String opt, Principal
	  principal) {
	  
	  StringBuilder sb = new StringBuilder(); 
	  Page<ClientKtConvMast> clientKtConvMasts = null;
	 
	  User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("clientKtConvMast");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
	
		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}
	
	
		clientKtConvMasts = clientKtConvService.searchAll(limit, offset, sort, order, search);
	
	sb.append("{\"total\":").append(clientKtConvMasts.getTotalElements()).append(",\"rows\":[");
	
	for(ClientKtConvMast clientKtConvMast : clientKtConvMasts) {
		
	
		sb.append("{\"id\":\"")
		.append(clientKtConvMast.getId())
		.append("\",\"party\":\"")
		.append(clientKtConvMast.getParty()!=null ?  clientKtConvMast.getParty().getName():"")
		.append("\",\"purity\":\"")
		.append(clientKtConvMast.getPurity()!=null ?  clientKtConvMast.getPurity().getName():"")
		.append("\",\"purityConv\":\"")
		.append(clientKtConvMast.getPurityConv());
		

		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
				sb.append("\",\"action1\":\"")
						.append("<a href='/jewels/manufacturing/masters/clientKtConvMast/edit/")
						.append(clientKtConvMast.getId()).append(".html'")
						.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

				sb.append("\",\"action2\":\"")
						.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/clientKtConvMast/delete/")
						.append(clientKtConvMast.getId()).append(".html'")
						.append(" class='btn btn-xs btn-danger triggerRemove")
						.append(clientKtConvMast.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
						.append("\"},");
					
						}else{
							sb.append("\",\"action1\":\"");
							if (roleRights.getCanEdit()) {
								sb.append("<a href='/jewels/manufacturing/masters/clientKtConvMast/edit/")
									.append(clientKtConvMast.getId()).append(".html'");
							} else {
								sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
							}
							sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
				
							sb.append("\",\"action2\":\"");
							if (roleRights.getCanDelete()) {
								sb.append(
										"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/clientKtConvMast/delete/")
										.append(clientKtConvMast.getId()).append(".html'");
							} else {
								sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
							}
							sb.append(" class='btn btn-xs btn-danger triggerRemove")
									.append(clientKtConvMast.getId())
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

	
		@RequestMapping("/clientKtConvMast/add")
		public String add(Model model, Principal principal) {
			User user = userService.findByName(principal.getName());
			UserRole userRole = userRoleService.findByUser(user);
			MenuMast menuMast = menuMastService.findByMenuNm("clientKtConvMast");
			RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
			
			if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
					userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
			
				model.addAttribute("canAdd", true);
				model.addAttribute("canEdit", true);
				model.addAttribute("canDelete", true);
				model.addAttribute("partyMap",partyService.getPartyList());
				model.addAttribute("purityMap",purityService.getPurityList());

				return "clientKtConvMast/add";

			}else	

			
			if(roleRights == null){
				return "accesDenied";
			}else{
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("partyMap",partyService.getPartyList());
				model.addAttribute("purityMap",purityService.getPurityList());
				
			}
			
			return "clientKtConvMast/add";
		}
	
	
		@ResponseBody
		@RequestMapping(value = "/clientKtConvMast/add", method = RequestMethod.POST)
		public String addEditAutoStyleParameter(
				@Valid @ModelAttribute("clientKtConvMast") ClientKtConvMast clientKtConvMast,
				BindingResult result, @RequestParam(value = "id") Integer id,
				RedirectAttributes redirectAttributes, Principal principal) {

			String action = "added";
			String retVal = "/jewels/manufacturing/masters/clientKtConvMast/add.html?success=true";
			

			if (result.hasErrors()) {
				return "clientKtConvMast/add";
			}

			if (id == null || id.equals("") || (id != null && id == 0)) {
				
				ClientKtConvMast clientKtConvMast2 =
						
				clientKtConvService.findByPartyAndPurityAndDeactive(clientKtConvMast.getParty(),clientKtConvMast.getPurity(),false);
				if(clientKtConvMast2 != null){
					return retVal = "-1";
				}
				
				
				clientKtConvMast.setCreatedBy(principal.getName());
				clientKtConvMast.setCreatedDate(new java.util.Date());
				
			} else {
				
				ClientKtConvMast clientKtConvMast2 = clientKtConvService.findByPartyAndPurityAndDeactive(clientKtConvMast.getParty(),clientKtConvMast.getPurity(),false);
				
				if(clientKtConvMast2 != null){
					
					if(clientKtConvMast2.getId().equals(clientKtConvMast.getId())){
					}else{
						return retVal="-1"; 
					}
					
				}
				
				clientKtConvMast.setModiBy(principal.getName());
				clientKtConvMast.setModiDate(new java.util.Date());

				if (clientKtConvMast.isDeactive() != clientKtConvService.findOne(id).isDeactive()) {
					clientKtConvMast.setDeactiveDt(new java.util.Date());
				} else {
					clientKtConvMast.setDeactiveDt(clientKtConvService.findOne(id).getDeactiveDt());
				}
				clientKtConvMast.setId(id);
				
				action = "updated";
				retVal = "/jewels/manufacturing/masters/clientKtConvMast.html?success=true";
				
			}
			
					
			clientKtConvService.save(clientKtConvMast);
			
			
			redirectAttributes.addFlashAttribute("success", true);
			redirectAttributes.addFlashAttribute("action", action);

			return retVal;
		}	
		
	
		@RequestMapping("/clientKtConvMast/edit/{id}")
		public String edit(@PathVariable int id, Model model, Principal principal) {
		
			ClientKtConvMast clientKtConvMast = clientKtConvService.findOne(id);
			model.addAttribute("clientKtConvMast", clientKtConvMast);
			User user = userService.findByName(principal.getName());
			UserRole userRole = userRoleService.findByUser(user);
			MenuMast menuMast = menuMastService.findByMenuNm("clientKtConvMast");
			RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
			
			
			if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
					userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
			
				model.addAttribute("canAdd", true);
				model.addAttribute("canEdit", true);
				model.addAttribute("canDelete", true);
				model.addAttribute("partyMap",partyService.getPartyList());		
				model.addAttribute("purityMap", purityService.getPurityList());
				
				return "clientKtConvMast/add";
	
			}else
				
				if(roleRights == null){
					return "accesDenied";
				}else{
					model.addAttribute("canAdd", roleRights.getCanAdd());
					model.addAttribute("canEdit", roleRights.getCanEdit());
					model.addAttribute("canDelete", roleRights.getCanDelete());
					model.addAttribute("partyMap",partyService.getPartyList());		
					model.addAttribute("purityMap", purityService.getPurityList());
				}	
				
				return "clientKtConvMast/add";
			}
	
	
		
		@RequestMapping("/clientKtConvMast/delete/{id}")
		public String delete(@PathVariable int id) {
			clientKtConvService.delete(id);
			return "redirect:/manufacturing/masters/clientKtConvMast.html";
		}
	
	
	
	
}
