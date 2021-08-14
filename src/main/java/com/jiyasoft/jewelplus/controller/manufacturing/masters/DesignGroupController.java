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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignGroup;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignGroupService;

@RequestMapping("/manufacturing/masters")
@Controller
public class DesignGroupController {
	
	@Autowired
	private IDesignGroupService designGroupService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@ModelAttribute("designGroup")
	public DesignGroup construct() {
		return new DesignGroup();
	}
	
	@RequestMapping("/designGroup")
	public String users(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("designGroup");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
	
			return "designGroup";
		}else
		
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}
		
		return "designGroup";
	}

	
	@RequestMapping("/designGroup/listing")
	@ResponseBody
	public String designGroupListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "opt", required = false) String opt,
			Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<DesignGroup> designGroups = null;
		
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("designGroup");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

		Long rowCount = null;

		designGroups = designGroupService.searchAll(limit, offset, sort, order, search, true);
		rowCount = designGroupService.countAll(sort, search, false);
		
		System.out.println("Hiiii" +designGroups.getSize());
		
		/*if (search == null && sort == null) {
			rowCount = designGroupService.count(sort, search, false);
			designGroups = designGroupService.findByName(limit, offset, sort, order,search, false);
		} else if (search != null && sort != null && sort.equalsIgnoreCase("name")) {
			rowCount = designGroupService.count(sort, search, false);
			designGroups = designGroupService.findByName(limit, offset, sort, order, search, false);
		} else if (search != null && sort != null) {
			rowCount = 0L;
			designGroups = new PageImpl<DesignGroup>(new ArrayList<DesignGroup>());
		} else {
			rowCount = designGroupService.count(sort, search, false);
			designGroups = designGroupService.findByName(limit, offset, sort, order,search, false);
		}*/

		sb.append("{\"total\":").append(rowCount).append(",\"rows\": [");

		for (DesignGroup designGroup : designGroups) {
			if(opt.equals("1")){
			
				sb.append("{\"id\":\"")
				.append(designGroup.getId())
				.append("\",\"name\":\"")
				.append(designGroup.getName())
				.append("\",\"updatedBy\":\"")
				.append((designGroup.getModiBy() == null ? "" : designGroup.getModiBy()))
				.append("\",\"updatedDt\":\"")
				.append((designGroup.getModiDt() == null ? "" : designGroup.getModiDt()))
				.append("\",\"deactive\":\"")
				.append((designGroup.getDeactive() == null ? "": (designGroup.getDeactive() ? "Deactive": "Active")))
				.append("\",\"deactiveDt\":\"")
				.append((designGroup.getDeactiveDt() == null ? "" : designGroup.getDeactiveDt()));
			
				if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
						userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
				
					sb.append("\",\"action1\":\"");
						sb.append("<a href='/jewels/manufacturing/masters/designGroup/edit/")
								.append(designGroup.getId()).append(".html'")
								.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
		
					sb.append("\",\"action2\":\"");
						sb.append(
								"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/designGroup/delete/")
								.append(designGroup.getId()).append(".html'")
								.append(" class='btn btn-xs btn-danger triggerRemove")
								.append(designGroup.getId())
								.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
								.append("\"},");
					
				}else{
					sb.append("\",\"action1\":\"");
					if (roleRights.getCanEdit()) {
						sb.append("<a href='/jewels/manufacturing/masters/designGroup/edit/")
								.append(designGroup.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
		
					sb.append("\",\"action2\":\"");
					if (roleRights.getCanDelete()) {
						sb.append(
								"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/designGroup/delete/")
								.append(designGroup.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-danger triggerRemove")
					.append(designGroup.getId())
					.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
					.append("\"},");
		
				}
				
			}else if(opt.equals("3")){
				sb.append("{\"id\":\"")
				.append(designGroup.getId())
				.append("\",\"name\":\"")
				.append(designGroup.getName())
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
	
	
	
	@RequestMapping("/designGroup/add")
	public String add(Model model,Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("designGroup");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "designGroup/add";

		}else	

		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}
		
		return "designGroup/add";
	}

	@RequestMapping(value = "/designGroup/add", method = RequestMethod.POST)
	public String addEditUser(@Valid @ModelAttribute("designGroup") DesignGroup designGroup,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "redirect:/manufacturing/masters/designGroup/add.html";

		if (result.hasErrors()) {
			return "designGroup/add";
		}
		
		if (id == null || id.equals("") || (id != null && id == 0)) {
			designGroup.setCreatedBy(principal.getName());
			designGroup.setCreatedDt(new java.util.Date());
		} else {
			designGroup.setId(id);
			designGroup.setModiBy(principal.getName());
			designGroup.setModiDt(new java.util.Date());
			action = "updated";
			retVal = "redirect:/manufacturing/masters/designGroup.html";
		}
		
		designGroupService.save(designGroup);
		
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;

	}

	@RequestMapping("/designGroup/edit/{id}")
	public String edit(@PathVariable int id, Model model,Principal principal) {
		DesignGroup designGroup = designGroupService.findOne(id);
		model.addAttribute("designGroup", designGroup);

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("designGroup");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "designGroup/add";

		}else	
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}

		
		
		return "designGroup/add";
	}

	@RequestMapping("/designGroup/delete/{id}")
	public String delete(@PathVariable int id) {
		designGroupService.delete(id);

		return "redirect:/manufacturing/masters/designGroup.html";
	}
	
	
	@RequestMapping("/designGroupAvailable")
	@ResponseBody
	public String designGroupAvailable(@RequestParam String name,
			@RequestParam Integer id) {
		
		Boolean designGroupAvailable = true;

		if (id == null) {
			designGroupAvailable = (designGroupService.findByName(name) == null);
		} else {
			DesignGroup designGroup = designGroupService.findOne(id);
			if (!(name.equalsIgnoreCase(designGroup.getName()))) {
				designGroupAvailable = (designGroupService.findByName(name) == null);
			}
		}

		return designGroupAvailable.toString();
	}
	

}
