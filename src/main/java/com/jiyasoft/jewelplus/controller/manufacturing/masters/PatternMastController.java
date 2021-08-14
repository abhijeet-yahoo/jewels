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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.PatternMast;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPatternMastService;

@RequestMapping("/manufacturing/masters")
@Controller
public class PatternMastController {
	
	@Autowired
	private IPatternMastService patternMastService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;

	@Autowired
	private UserRoleService userRoleService;
	

	@ModelAttribute("patternmast")
	public PatternMast construct() {
		return new PatternMast();
	}
	
	@RequestMapping("/patternmast")
	public String users(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("patternmast");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
	
			return "patternmast";
		}else
		
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}
		
		return "patternmast";
	}



	@RequestMapping("/patternmast/listing")
	@ResponseBody
	public String colorListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "opt", required = true) String opt, Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<PatternMast> patternmasts = null;

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("patternmast");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if ((search != null) && (search.trim().length() == 0)) {
			search = null;}
			
		patternmasts = patternMastService.searchAll(limit, offset, sort, order, search, false);
			sb.append("{\"total\":").append(patternmasts.getSize()).append(",\"rows\": [");

			for (PatternMast patternmast : patternmasts) {
				if(opt.equals("1")){
					
				
			 {
					sb.append("{\"id\":\"")
							.append(patternmast.getId())
							.append("\",\"name\":\"")
							.append(patternmast.getName())
							.append("\",\"code\":\"")
							.append((patternmast.getCode() == null ? ""
									: patternmast.getCode()))
							.append("\",\"updatedBy\":\"")
							.append((patternmast.getModiBy() == null ? "" : patternmast
									.getModiBy()))
							.append("\",\"updatedDt\":\"")
							.append((patternmast.getModiDt() == null ? "" : patternmast
									.getModiDt()))
							.append("\",\"deactive\":\"")
							.append((patternmast.getDeactive() == null ? "": (patternmast.getDeactive() ? "Deactive": "Active")))
							.append("\",\"deactiveDt\":\"")
							.append((patternmast.getDeactiveDt() == null ? ""
									: patternmast.getDeactiveDt()));
					
					if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
							userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){

							sb.append("\",\"action1\":\"")
									.append("<a href='/jewels/manufacturing/masters/patternmast/edit/")
									.append(patternmast.getId()).append(".html'")
									.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
			
							sb.append("\",\"action2\":\"")
									.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/patternmast/delete/")
									.append(patternmast.getId()).append(".html'")
									.append(" class='btn btn-xs btn-danger triggerRemove")
									.append(patternmast.getId())
									.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
									.append("\"},");
					}else{
						
						sb.append("\",\"action1\":\"");
						if (roleRights.getCanEdit()) {
							sb.append("<a href='/jewels/manufacturing/masters/patternmast/edit/")
								.append(patternmast.getId()).append(".html'");
						} else {
							sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
						}
						sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
		
						sb.append("\",\"action2\":\"");
						if (roleRights.getCanDelete()) {
							sb.append(
									"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/patternmast/delete/")
									.append(patternmast.getId()).append(".html'");
						} else {
							sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
						}
						sb.append(" class='btn btn-xs btn-danger triggerRemove")
								.append(patternmast.getId())
								.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
								.append("\"},");
					}		
							
			 }
			 }else if(opt.equals("3")){
				 sb.append("{\"id\":\"")
					.append(patternmast.getId())
					.append("\",\"name\":\"")
					.append(patternmast.getName())
					.append("\",\"code\":\"")
					.append((patternmast.getCode() == null ? ""	: patternmast.getCode()))
					.append("\"},");
			 }

				

			

			
			
			}String str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";

			return str;

	}
	
	@RequestMapping("/patternmast/add")
	public String add(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("patternmast");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "patternmast/add";

		}else	

		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}

		return "patternmast/add";
	}
	
	@RequestMapping(value = "/patternmast/add", method = RequestMethod.POST)
	public String addEditCategory(
			@Valid @ModelAttribute("patternmast") PatternMast patternmast,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "redirect:/manufacturing/masters/patternmast/add.html";

		if (result.hasErrors()) {
			return "patternmast/add";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			patternmast.setCreatedBy(principal.getName());
			patternmast.setCreatedDt(new java.util.Date());
			patternmast.setDeactive(false);
		} else {
			patternmast.setModiBy(principal.getName());
			patternmast.setModiDt(new java.util.Date());

			if (patternmast.getDeactive() != patternMastService.findOne(id).getDeactive()) {
				patternmast.setDeactiveDt(new java.util.Date());
			} else {
				patternmast.setDeactiveDt(patternMastService.findOne(id).getDeactiveDt());
			}
			patternmast.setId(id);

			action = "updated";
			retVal = "redirect:/manufacturing/masters/patternmast.html";
		}

		patternMastService.save(patternmast);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;
	}
	
	@RequestMapping("/patternmast/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		PatternMast patternmast = patternMastService.findOne(id);
		model.addAttribute("patternmast", patternmast);

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("lookmast");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "patternmast/add";

		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}

		return "patternmast/add";
	}
	
	@RequestMapping("/patternmast/delete/{id}")
	public String delete(@PathVariable int id) {
		patternMastService.delete(id);
		
		return "redirect:/manufacturing/masters/patternmast.html";
	}
	
	@RequestMapping("/patternmastAvailable")
	@ResponseBody
	public String userAvailable(@RequestParam String name,
			@RequestParam Integer id) {
		
		Boolean patternmastAvailable = true;

		name = name.trim();

		if (id == null) {
			patternmastAvailable = (patternMastService.findByName(name) == null);
		} else {
			PatternMast patternmast = patternMastService.findOne(id);
			if (!(name.equalsIgnoreCase(patternmast.getName()))) {
				patternmastAvailable = (patternMastService.findByName(name) == null);
			}
		}

		return patternmastAvailable.toString();
	}
}
