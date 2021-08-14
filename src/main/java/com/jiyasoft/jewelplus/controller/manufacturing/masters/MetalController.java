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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;

@RequestMapping("/manufacturing/masters")
@Controller
public class MetalController {

	@Autowired
	private IMetalService metalService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@ModelAttribute("metal")
	public Metal construct() {
		return new Metal();
	}

	@RequestMapping("/metal")
	public String users(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("metal");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
	
			return "metal";
		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}

		return "metal";
	}

	@RequestMapping("/metal/listing")
	@ResponseBody
	public String metalListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "opt", required = true) String opt,
			Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<Metal> metals = null;

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("metal");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
	
		/*	model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		*/

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;	
		}

		Long rowCount = null;

		metals = metalService.searchAll(limit, offset, sort, order, search, false);
		rowCount = metalService.countAll(sort, search, false);
		
		// default search would be name wise
		/*if (search == null && sort == null) {
			rowCount = metalService.count(sort, search, false);
			metals = metalService.findByName(limit, offset, sort, order, search, false);
		} else if (search != null && sort != null && sort.equalsIgnoreCase("name")) {
			rowCount = metalService.count(sort, search, false);
			metals = metalService.findByName(limit, offset, sort, order, search, false);
		} else if (search != null && sort != null) {
			rowCount = 0L;
			metals = new PageImpl<Metal>(new ArrayList<Metal>());
		} else {
			rowCount = metalService.count(sort, search, false);
			metals = metalService.findByName(limit, offset, sort, order, search, false);
		}*/

		sb.append("{\"total\":").append(rowCount).append(",\"rows\": [");
		
		
		for (Metal metal : metals) {
			if (opt.equals("1")) {
				sb.append("{\"id\":\"")
						.append(metal.getId())
						.append("\",\"name\":\"")
						.append(metal.getName())
						.append("\",\"updatedBy\":\"")
						.append((metal.getModiBy() == null ? "" : metal
								.getModiBy()))
						.append("\",\"updatedDt\":\"")
						.append((metal.getModiDate() == null ? "" : metal
								.getModiDate()))
						.append("\",\"deactive\":\"")
						.append((metal.getDeactive() == null ? "" : metal
								.getDeactive() ? "Deactive" : "Active")) //true==deactive and false==active
						.append("\",\"deactiveDt\":\"")
						.append((metal.getDeactiveDt() == null ? "" : metal
								.getDeactiveDt()));
				
				if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
						userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
				
					sb.append("\",\"action1\":\"")
						.append("<a href='/jewels/manufacturing/masters/metal/edit/")
						.append(metal.getId()).append(".html'")
						.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
					sb.append("\",\"action2\":\"")
						.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/metal/delete/")
						.append(metal.getId()).append(".html'")
						.append(" class='btn btn-xs btn-danger triggerRemove")
						.append(metal.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
						.append("\"},");
				}else{
					sb.append("\",\"action1\":\"");
					if (roleRights.getCanEdit()) {
						sb.append("<a href='/jewels/manufacturing/masters/metal/edit/")
								.append(metal.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
					sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {
					sb.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/metal/delete/")
							.append(metal.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				 sb.append(" class='btn btn-xs btn-danger triggerRemove")
						.append(metal.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
						.append("\"},");
				}
				

				
			} else if (opt.equals("2")) {
				sb.append("{\"name\":\"")
						.append("<a href='javascript:getChild(")
						.append(metal.getId()).append(")'>")
						.append(metal.getName()).append("</a>\"},");
			} else if(opt.equals("3")){
				sb.append("{\"id\":\"")
				.append(metal.getId())
				.append("\",\"name\":\"")
				.append(metal.getName())
				.append("\"},");
			}
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}

	@RequestMapping("/metal/add")
	public String add(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("metal");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "metal/add";

		}else	
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}

		return "metal/add";
	}

	@RequestMapping(value = "/metal/add", method = RequestMethod.POST)
	public String addEditUser(@Valid @ModelAttribute("metal") Metal metal,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "redirect:/manufacturing/masters/metal/add.html";

		if (result.hasErrors()) {
			return "metal/add";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			metal.setCreatedBy(principal.getName());
			metal.setCreatedDate(new java.util.Date());
		} else {
			metal.setModiBy(principal.getName());
			metal.setModiDate(new java.util.Date());

			if (metal.getDeactive() != metalService.findOne(id).getDeactive()) {
				metal.setDeactiveDt(new java.util.Date());
			} else {
				metal.setDeactiveDt(metalService.findOne(id).getDeactiveDt());
			}
			metal.setId(id);

			action = "updated";
			retVal = "redirect:/manufacturing/masters/metal.html";
		}

		metalService.save(metal);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;
	}

	@RequestMapping("/metal/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		Metal metal = metalService.findOne(id);
		model.addAttribute("metal", metal);

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("metal");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "metal/add";

		}else
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}

		return "metal/add";
	}

	@RequestMapping("/metal/delete/{id}")
	public String delete(@PathVariable int id) {
		metalService.delete(id);

		return "redirect:/manufacturing/masters/metal.html";
	}
	
	@RequestMapping("/metalAvailable")
	@ResponseBody
	public String metalAvailable(@RequestParam String name,
			@RequestParam Integer id) {
		Boolean metalAvailable = true;

		if (id == null) {
			metalAvailable = (metalService.findByName(name) == null);
		} else {
			Metal metal = metalService.findOne(id);
			if (!(name.equalsIgnoreCase(metal.getName()))) {
				metalAvailable = (metalService.findByName(name) == null);
			}
		}

		return metalAvailable.toString();
	}

}
