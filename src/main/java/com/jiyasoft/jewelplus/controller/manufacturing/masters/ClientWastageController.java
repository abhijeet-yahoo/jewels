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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ClientWastage;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ICategoryService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IClientWastageService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignGroupService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISubCategoryService;

@RequestMapping("/manufacturing/masters")
@Controller
public class ClientWastageController {

	@Autowired
	private IClientWastageService clientWastageService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private IDesignGroupService designGroupService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private ISubCategoryService	subCategoryService;
	
	@Autowired
	private IMetalService metalService;
	
	@Autowired
	private UserRoleService userRoleService;

	@ModelAttribute("clientWastage")
	public ClientWastage construct() {
		return new ClientWastage();
	}

	@RequestMapping("/clientWastage")
	public String users(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("clientWastage");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
	
			return "clientWastage";
		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}
		
		return "clientWastage";
	}

	@RequestMapping("/clientWastage/listing")
	@ResponseBody
	public String clientWastageListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<ClientWastage> clientWastages = null;

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("clientWastage");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

	
		clientWastages = clientWastageService.searchAll(limit, offset, sort, order,search);
		
		
		sb.append("{\"total\":").append(clientWastages.getTotalElements()).append(",\"rows\": [");

		for (ClientWastage clientWastage : clientWastages) {
			
				sb.append("{\"id\":\"")
						.append(clientWastage.getId())
						.append("\",\"party\":\"")
						.append(clientWastage.getParty()!=null ?  clientWastage.getParty().getPartyCode():"")
						.append("\",\"designGroup\":\"")
						.append(clientWastage.getDesignGroup()!=null ? clientWastage.getDesignGroup().getName():"")
					    .append("\",\"category\":\"") 
					    .append(clientWastage.getCategory()!=null ? clientWastage.getCategory().getName():"")
					    .append("\",\"subCategory\":\"")
					    .append(clientWastage.getSubCategory()!=null  ? clientWastage.getSubCategory().getName():"")
						.append("\",\"metal\":\"")
						.append(clientWastage.getMetal().getName())
						.append("\",\"wastagePerc\":\"")
						.append(clientWastage.getWastagePerc());
				
				if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
						userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
				
						sb.append("\",\"action1\":\"")
								.append("<a href='/jewels/manufacturing/masters/clientWastage/edit/")
								.append(clientWastage.getId()).append(".html'")
								.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
		
						sb.append("\",\"action2\":\"")
								.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/clientWastage/delete/")
								.append(clientWastage.getId()).append(".html'")
								.append(" class='btn btn-xs btn-danger triggerRemove")
								.append(clientWastage.getId())
								.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
								.append("\"},");
				}else{
					sb.append("\",\"action1\":\"");
					if (roleRights.getCanEdit()) {
						sb.append("<a href='/jewels/manufacturing/masters/clientWastage/edit/")
							.append(clientWastage.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
					sb.append("\",\"action2\":\"");
					if (roleRights.getCanDelete()) {
						sb.append(
								"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/clientWastage/delete/")
								.append(clientWastage.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(clientWastage.getId())
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

	@RequestMapping("/clientWastage/add")
	public String add(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("clientWastage");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("partyMap",partyService.getPartyList());
			model.addAttribute("designGroupMap",designGroupService.getDesignGroupList());
			model.addAttribute("categoryMap",categoryService.getCategoryList());

			model.addAttribute("metalMap", metalService.getMetalList());
			
			return "clientWastage/add";

		}else	

		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("partyMap",partyService.getPartyList());
			model.addAttribute("designGroupMap",designGroupService.getDesignGroupList());
			model.addAttribute("categoryMap",categoryService.getCategoryList());
			model.addAttribute("metalMap", metalService.getMetalList());
		}
		
		return "clientWastage/add";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/clientWastage/add", method = RequestMethod.POST)
	public String addEditClientWastage(
			@Valid @ModelAttribute("clientWastage") ClientWastage clientWastage,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "/jewels/manufacturing/masters/clientWastage/add.html?success=true";
		
		

		if (result.hasErrors()) {
			return "clientWastage/add";
		}
		
		if (clientWastage.getDesignGroup().getId() == null) {
			clientWastage.setDesignGroup(null);
		}
		
		if (clientWastage.getCategory().getId() == null) {
			clientWastage.setCategory(null);
		}
		
		
		  if (clientWastage.getSubCategory().getId() == null) {
		  clientWastage.setSubCategory(null); }
		 
	

		if (id == null || id.equals("") || (id != null && id == 0)) {
			
				
				/*
				 * ClientWastage
				 * clientWastage2=clientWastageService.findByMetalAndPartyAndDeactive(
				 * clientWastage.getMetal(),clientWastage.getParty(), false); if(clientWastage2
				 * != null){ return retVal = "-1"; }
				 */
				
				ClientWastage clientWastage2=clientWastageService.findByMetalAndPartyAndDesignGroupAndCategoryAndSubCategoryAndDeactive(clientWastage.getMetal(),clientWastage.getParty(), clientWastage.getDesignGroup(), clientWastage.getCategory(), clientWastage.getSubCategory(), false);
				if(clientWastage2 != null){
					return retVal = "-1";
				}
			
			
			
			
			clientWastage.setCreatedBy(principal.getName());
			clientWastage.setCreatedDt(new java.util.Date());
			
		} else {
			
			ClientWastage clientWastage2=clientWastageService.findByMetalAndPartyAndDesignGroupAndCategoryAndSubCategoryAndDeactive(clientWastage.getMetal(),clientWastage.getParty(), clientWastage.getDesignGroup(), clientWastage.getCategory(), clientWastage.getSubCategory(), false);
			
			if(clientWastage2 != null){
				
				if(clientWastage2.getId().equals(clientWastage.getId())){
				}else{
					return retVal="-1"; 
				}
				
			}
			
			clientWastage.setModiBy(principal.getName());
			clientWastage.setModiDt(new java.util.Date());

			action = "updated";
			retVal = "/jewels/manufacturing/masters/clientWastage.html?success=true";
			
		}
		clientWastageService.save(clientWastage);
		
		
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;
	}

	@RequestMapping("/clientWastage/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
	
		ClientWastage clientWastage = clientWastageService.findOne(id);
		model.addAttribute("clientWastage", clientWastage);
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("clientWastage");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		model = editDetails(id, model, principal);
		
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("partyMap",partyService.getPartyList());
			model.addAttribute("designGroupMap",designGroupService.getDesignGroupList());
			model.addAttribute("categoryMap",categoryService.getCategoryList());
			model.addAttribute("metalMap", metalService.getMetalList());
			
			if (clientWastage.getCategory() != null) {
				
				model.addAttribute("subCategoryMap", (subCategoryService.getSubCategoryList(clientWastage.getCategory().getId())));
					
			}
			
			return "clientWastage/add";

		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("partyMap",partyService.getPartyList());
			model.addAttribute("designGroupMap",designGroupService.getDesignGroupList());
			model.addAttribute("categoryMap",categoryService.getCategoryList());
			model.addAttribute("metalMap", metalService.getMetalList());
			
	if (clientWastage.getCategory() != null) {
				
				model.addAttribute("subCategoryMap", (subCategoryService.getSubCategoryList(clientWastage.getCategory().getId())));
					
			}
		}

		
		
		
		return "clientWastage/add";
	}

	@RequestMapping("/clientWastage/delete/{id}")
	public String delete(@PathVariable int id) {
		clientWastageService.delete(id);
		return "redirect:/manufacturing/masters/clientWastage.html";
	}

	/*@RequestMapping("/clientWastageAvailable")
	@ResponseBody
	public String clientWastageAvailable(@RequestParam String name,
			@RequestParam Integer id) {
		
		Boolean clientWastageAvailable = true;

		name = name.trim();

		if (id == null) {
			clientWastageAvailable = (clientWastageService.findByName(name) == null);
		} else {
			Category category = categoryService.findOne(id);
			if (!(name.equalsIgnoreCase(category.getName()))) {
				categoryAvailable = (categoryService.findByName(name) == null);
			}
		}

		return categoryAvailable.toString();
	}*/
	
	
	
	private Model editDetails(int id, Model model, Principal principal) {
		
		ClientWastage clientwastage = clientWastageService.findOne(id);
	
			if (clientwastage.getCategory() != null) {
			
			model.addAttribute("subCategoryMap", (subCategoryService.getSubCategoryList(clientwastage.getCategory().getId())));
		
			
			}
			
			return model;
	}

	
	
	
	
	
	
}
