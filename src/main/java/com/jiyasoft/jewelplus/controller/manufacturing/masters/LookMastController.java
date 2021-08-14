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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookMast;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILookMastService;


@RequestMapping("/manufacturing/masters")
@Controller
public class LookMastController {
	
	@Autowired
	private ILookMastService lookMastService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;

	@Autowired
	private UserRoleService userRoleService;

	@ModelAttribute("lookmast")
	public LookMast construct() {
		return new LookMast();
	}
	
	@RequestMapping("/lookmast")
	public String users(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("lookmast");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
	
			return "lookmast";
		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}
		
		return "lookmast";
	}

	@RequestMapping("/lookmast/listing")
	@ResponseBody
	public String colorListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "opt", required = true) String opt, Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<LookMast>lookmasts = null;

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("lookmast");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if ((search != null) && (search.trim().length() == 0)) {
			search = null;}
			
			lookmasts = lookMastService.searchAll(limit, offset, sort, order, search, false);
			sb.append("{\"total\":").append(lookmasts.getSize()).append(",\"rows\": [");

			for (LookMast lookmast : lookmasts) {
				if(opt.equals("1")){
			 {
					sb.append("{\"id\":\"")
							.append(lookmast.getId())
							.append("\",\"name\":\"")
							.append(lookmast.getName())
							.append("\",\"code\":\"")
							.append((lookmast.getCode() == null ? ""
									: lookmast.getCode()))
							.append("\",\"updatedBy\":\"")
							.append((lookmast.getModiBy() == null ? "" : lookmast
									.getModiBy()))
							.append("\",\"updatedDt\":\"")
							.append((lookmast.getModiDt() == null ? "" : lookmast
									.getModiDt()))
							.append("\",\"deactive\":\"")
							.append((lookmast.getDeactive() == null ? "": (lookmast.getDeactive() ? "Deactive": "Active")))
							.append("\",\"deactiveDt\":\"")
							.append((lookmast.getDeactiveDt() == null ? ""
									: lookmast.getDeactiveDt()));
					
					if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
							userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){

							sb.append("\",\"action1\":\"")
									.append("<a href='/jewels/manufacturing/masters/lookmast/edit/")
									.append(lookmast.getId()).append(".html'")
									.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
			
							sb.append("\",\"action2\":\"")
									.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/lookmast/delete/")
									.append(lookmast.getId()).append(".html'")
									.append(" class='btn btn-xs btn-danger triggerRemove")
									.append(lookmast.getId())
									.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
									.append("\"},");
					}else{
						
								sb.append("\",\"action1\":\"");
								if (roleRights.getCanEdit()) {
									sb.append("<a href='/jewels/manufacturing/masters/lookmast/edit/")
										.append(lookmast.getId()).append(".html'");
								} else {
									sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
								}
								sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
				
								sb.append("\",\"action2\":\"");
								if (roleRights.getCanDelete()) {
									sb.append(
											"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/lookmast/delete/")
											.append(lookmast.getId()).append(".html'");
								} else {
									sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
								}
								sb.append(" class='btn btn-xs btn-danger triggerRemove")
										.append(lookmast.getId())
										.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
										.append("\"},");
					}
			 }
							
			 }else if(opt.equals("3")){
				 sb.append("{\"id\":\"")
					.append(lookmast.getId())
					.append("\",\"name\":\"")
					.append(lookmast.getName())
					.append("\",\"code\":\"")
					.append((lookmast.getCode() == null ? "" : lookmast.getCode()))
					.append("\"},");
				 
			 }

				

			

			
			
			}String str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";

			return str;

	}
	
	@RequestMapping("/lookmast/add")
	public String add(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("lookmast");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "lookmast/add";

		}else	
		
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}

		return "lookmast/add";
	}
	
	@RequestMapping(value = "/lookmast/add", method = RequestMethod.POST)
	public String addEditCategory(
			@Valid @ModelAttribute("lookmast") LookMast lookmast,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "redirect:/manufacturing/masters/lookmast/add.html";

		if (result.hasErrors()) {
			return "category/add";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			lookmast.setCreatedBy(principal.getName());
			lookmast.setCreatedDt(new java.util.Date());
			lookmast.setDeactive(false);
		} else {
			lookmast.setModiBy(principal.getName());
			lookmast.setModiDt(new java.util.Date());

			if (lookmast.getDeactive() != lookMastService.findOne(id).getDeactive()) {
				lookmast.setDeactiveDt(new java.util.Date());
			} else {
				lookmast.setDeactiveDt(lookMastService.findOne(id).getDeactiveDt());
			}
			lookmast.setId(id);

			action = "updated";
			retVal = "redirect:/manufacturing/masters/lookmast.html";
		}

		lookMastService.save(lookmast);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;
	}
	
	@RequestMapping("/lookmast/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		LookMast lookmast = lookMastService.findOne(id);
		model.addAttribute("lookmast", lookmast);

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("lookmast");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "lookmast/add";

		}else	
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}

		return "lookmast/add";
	}
	
	@RequestMapping("/lookmast/delete/{id}")
	public String delete(@PathVariable int id) {
		lookMastService.delete(id);
		return "redirect:/manufacturing/masters/lookmast.html";
	}
	
	@RequestMapping("/lookmastAvailable")
	@ResponseBody
	public String userAvailable(@RequestParam String name,
			@RequestParam Integer id) {
		
		Boolean lookmastAvailable = true;

		name = name.trim();

		if (id == null) {
			lookmastAvailable = (lookMastService.findByName(name) == null);
		} else {
			LookMast lookmast = lookMastService.findOne(id);
			if (!(name.equalsIgnoreCase(lookmast.getName()))) {
				lookmastAvailable = (lookMastService.findByName(name) == null);
			}
		}

		return lookmastAvailable.toString();
	}
}


























