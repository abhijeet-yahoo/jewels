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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.InwardType;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IInwardTypeService;

@RequestMapping("/manufacturing/masters")
@Controller
public class InwardTypeController {

	@Autowired
	private IInwardTypeService inwardTypeService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private UserRoleService userRoleService;

	@ModelAttribute("inwardType")
	public InwardType construct() {
		return new InwardType();
	}

	@RequestMapping("/inwardType")
	public String users(Model model,Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("inwardType");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
	
			return "inwardType";
		}else
		
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}

		return "inwardType";
	}

	@RequestMapping("/inwardType/listing")
	@ResponseBody
	public String inwardTypeListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search, Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<InwardType> inwardTypes = null;

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("inwardType");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

		Long rowCount = null;
		
		inwardTypes = inwardTypeService.searchAll(limit, offset, sort, order, search, false);
		rowCount = inwardTypeService.countAll(sort, search, false);
		
		/*if (search == null && sort == null) {
			rowCount = inwardTypeService.count(sort, search, false);
			inwardTypes = inwardTypeService.findByName(limit, offset, sort, order,search, false);
		} 
		else if (search != null && sort != null && sort.equalsIgnoreCase("name")) {
			rowCount = inwardTypeService.count(sort, search, false);
			inwardTypes = inwardTypeService.findByName(limit, offset, sort, order,search, false);
		} 
		else if (search != null && sort != null) {
			rowCount = 0L;
			inwardTypes = new PageImpl<InwardType>(new ArrayList<InwardType>());
		} 
		else {
			rowCount = inwardTypeService.count(sort, search, false);
			inwardTypes = inwardTypeService.findByName(limit, offset, sort, order,search, false);
		}*/

		sb.append("{\"total\":").append(rowCount).append(",\"rows\": [");

		for (InwardType inwardType : inwardTypes) {
			sb.append("{\"id\":\"")
					.append(inwardType.getId())
					.append("\",\"name\":\"")
					.append(inwardType.getName())
					.append("\",\"code\":\"")
					.append(inwardType.getCode())
					.append("\",\"handPer\":\"")
					.append(inwardType.getHandlingPercentage())
					.append("\",\"deactive\":\"")
					.append(inwardType.isDeactive() ? "Yes" : "No");
			
			if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
					userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
			
					sb.append("\",\"action1\":\"")
							.append("<a href='/jewels/manufacturing/masters/inwardType/edit/")
							.append(inwardType.getId()).append(".html'")
							.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
		
					sb.append("\",\"action2\":\"")
							.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/inwardType/delete/")
							.append(inwardType.getId()).append(".html'")
							.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(inwardType.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
							.append("\"},");
					
			}else{
				sb.append("\",\"action1\":\"");
				if (roleRights.getCanEdit()) {
					sb.append("<a href='/jewels/manufacturing/masters/inwardType/edit/")
						.append(inwardType.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {
					sb.append(
							"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/inwardType/delete/")
							.append(inwardType.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-danger triggerRemove")
						.append(inwardType.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
						.append("\"},");
				
			}
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}

	@RequestMapping("/inwardType/add")
	public String add(Model model,Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("inwardType");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "inwardType/add";

		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}
		
		return "inwardType/add";
	}

	@RequestMapping(value = "/inwardType/add", method = RequestMethod.POST)
	public String addEditUser(
			@Valid @ModelAttribute("inwardType") InwardType inwardType,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "redirect:/manufacturing/masters/inwardType/add.html";

		if (result.hasErrors()) {
			return "inwardType/add";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			inwardType.setCreatedBy(principal.getName());
			inwardType.setCreatedDate(new java.util.Date());
		} else {
			inwardType.setId(id);
			inwardType.setModiBy(principal.getName());
			inwardType.setModiDate(new java.util.Date());
			action = "updated";
			retVal = "redirect:/manufacturing/masters/inwardType.html";
		}

		inwardTypeService.save(inwardType);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;

	}

	@RequestMapping("/inwardType/edit/{id}")
	public String edit(@PathVariable int id, Model model,Principal principal) {
		InwardType inwardType = inwardTypeService.findOne(id);
		model.addAttribute("inwardType", inwardType);
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("inwardType");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "inwardType/add";

		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}

		return "inwardType/add";
	}

	@RequestMapping("/inwardType/delete/{id}")
	public String delete(@PathVariable int id) {
		inwardTypeService.delete(id);

		return "redirect:/manufacturing/masters/inwardType.html";
	}

	@RequestMapping("/inwardTypeAvailable")
	@ResponseBody
	public String inwardTypeAvailable(@RequestParam String name,
			@RequestParam Integer id) {
		Boolean inwardTypeAvailable = true;

		if (id == null) {
			inwardTypeAvailable = (inwardTypeService.findByName(name) == null);
		} else {
			InwardType inwardType = inwardTypeService.findOne(id);
			if (!(name.equalsIgnoreCase(inwardType.getName()))) {
				inwardTypeAvailable = (inwardTypeService.findByName(name) == null);
			}
		}

		return inwardTypeAvailable.toString();
	}

}
