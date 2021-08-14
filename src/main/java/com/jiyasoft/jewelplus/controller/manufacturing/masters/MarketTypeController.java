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

import com.jiyasoft.jewelplus.domain.manufacturing.masters.MarketTypeMast;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMarketTypeService;

@RequestMapping("/manufacturing/masters")
@Controller
public class MarketTypeController {

	@Autowired
	private IMarketTypeService	marketTypeService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;
	
	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired 
	private UserRoleService userRoleService;
	
	@ModelAttribute("marketTypeMast")
	public MarketTypeMast construct() {
		return new MarketTypeMast();
	}
	

	@RequestMapping("/marketTypeMast")
	public String users(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("marketTypeMast");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
	
			return "marketTypeMast";
		}else
		
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}
		
		return "marketTypeMast";
	}
	
	
	@RequestMapping("/marketTypeMast/listing")
	@ResponseBody
	public String marketTypeMastListing(Model model,


			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "opt", required = true) String opt,
			Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<MarketTypeMast> marketTypeMasts = null;

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("marketTypeMast");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}
		
		
	
		
		marketTypeMasts = marketTypeService.searchAll(limit, offset, sort, order, search,true);
		


		sb.append("{\"total\":").append(marketTypeMasts.getTotalElements()).append(",\"rows\": [");

		for (MarketTypeMast marketTypeMast : marketTypeMasts) {
			if(opt.equals("1")){
			
			sb.append("{\"id\":\"")
					.append(marketTypeMast.getId())
					.append("\",\"name\":\"")
					.append(marketTypeMast.getName())
					.append("\",\"code\":\"")
					.append(marketTypeMast.getCode()!=null?marketTypeMast.getCode():"");
				
					
			if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
					userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
				
				sb.append("\",\"action1\":\"");
					sb.append("<a href='/jewels/manufacturing/masters/marketTypeMast/edit/")
						.append(marketTypeMast.getId()).append(".html'")
						.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
				sb.append("\",\"action2\":\"");
					sb.append(
							"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/marketTypeMast/delete/")
							.append(marketTypeMast.getId()).append(".html'")
							.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(marketTypeMast.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
							.append("\"},");
	
			}else{
				sb.append("\",\"action1\":\"");
				if (roleRights.getCanEdit()) {
					sb.append("<a href='/jewels/manufacturing/masters/marketTypeMast/edit/")
						.append(marketTypeMast.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {
					sb.append(
							"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/marketTypeMast/delete/")
							.append(marketTypeMast.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-danger triggerRemove")
						.append(marketTypeMast.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
						.append("\"},");
	
			}
			
								
		}
			else if(opt.equals("2")){
				sb.append("{\"id\":\"")
				.append(marketTypeMast.getId())
				.append("\",\"name\":\"")
				.append(marketTypeMast.getName())
				.append("\"},");
			}
		}		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		System.out.println(str);
		
		return str;
	}
	
	
	

	
	@RequestMapping("/marketTypeMast/add")
	public String add(Model model,Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("marketTypeMast");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "marketTypeMast/add";

		}else	
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}
		
		return "marketTypeMast/add";
	}
	
	
	
	
	@RequestMapping(value = "/marketTypeMast/add", method = RequestMethod.POST)
	public String addEditUser(@Valid @ModelAttribute("marketTypeMast") MarketTypeMast marketTypeMast,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "redirect:/manufacturing/masters/marketTypeMast/add.html";

		if (result.hasErrors()) {
			return "marketTypeMast/add";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			marketTypeMast.setCreatedBy(principal.getName());
			marketTypeMast.setCreatedDate(new java.util.Date());
		} else {
			marketTypeMast.setId(id);
			marketTypeMast.setModiBy(principal.getName());
			marketTypeMast.setModiDate(new java.util.Date());
			action = "updated";
			retVal = "redirect:/manufacturing/masters/marketTypeMast.html";
		}

		marketTypeService.save(marketTypeMast);
		
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;

	}
	
	
	
	
	@RequestMapping("/marketTypeMast/edit/{id}")
	public String edit(@PathVariable int id, Model model,Principal principal) {
		MarketTypeMast marketTypeMast = marketTypeService.findOne(id);
		model.addAttribute("marketTypeMast", marketTypeMast);

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("marketTypeMast");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "marketTypeMast/add";

		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}
		
		
		return "marketTypeMast/add";
	}
	
	
	
	@RequestMapping("/marketTypeMast/delete/{id}")
	public String delete(@PathVariable int id) {
		
		marketTypeService.delete(id);

		return "redirect:/manufacturing/masters/marketTypeMast.html";
	}
	
	
	
	
	@RequestMapping("/marketTypeMastAvailable")
	@ResponseBody
	public String marketTypeMastAvailable(@RequestParam String name,
			@RequestParam Integer id) {
		Boolean marketTypeMastAvailable = true;

		if (id == null) {
			marketTypeMastAvailable = (marketTypeService.findByNameAndDeactive(name, false) == null);
		} else {
			MarketTypeMast marketTypeMast = marketTypeService.findOne(id);
			if (!(name.equalsIgnoreCase(marketTypeMast.getName()))) {
				marketTypeMastAvailable = (marketTypeService.findByNameAndDeactive(name, false) == null);
			}
		}

		return marketTypeMastAvailable.toString();
	}
	
	

	@RequestMapping("/marketTypeMastCodeAvailable")
	@ResponseBody
	public String marketTypeMastCodeAvailable(@RequestParam String code,
			@RequestParam Integer id) {
		Boolean marketTypeMastCodeAvailable = true;

		if (id == null) {
			marketTypeMastCodeAvailable = (marketTypeService.findByCodeAndDeactive(code, false) == null);
		} else {
			MarketTypeMast marketTypeMast = marketTypeService.findOne(id);
			if (!(code.equalsIgnoreCase(marketTypeMast.getCode()))) {
				marketTypeMastCodeAvailable = (marketTypeService.findByCodeAndDeactive(code, false) == null);
			}
		}

		return marketTypeMastCodeAvailable.toString();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
	}//End of Class

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

