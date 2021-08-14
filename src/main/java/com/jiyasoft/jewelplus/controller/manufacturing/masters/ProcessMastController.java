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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ProcessMast;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILookUpMastService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IProcessMastService;

@RequestMapping("/manufacturing/masters")
@Controller
public class ProcessMastController {

	@Autowired
	private IProcessMastService processMastService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private ILookUpMastService lookUpMastService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	
	@Autowired
	private UserRoleService userRoleService;
	

	@ModelAttribute("processMast")
	public ProcessMast construct() {
		return new ProcessMast();
	}

	@RequestMapping("/processMast")
	public String users(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("processMast");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
	
			return "processMast";
		}else
		
	
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}
		
		return "processMast";
	}

	@RequestMapping("/processMast/listing")
	@ResponseBody
	public String processMastListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<ProcessMast> processMasts = null;

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("processMast");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

		Long rowCount = null;
		
		
		rowCount = processMastService.countAll(search);
		processMasts = processMastService.searchAll(limit, offset, sort, order,search);
		
		
		
		
		sb.append("{\"total\":").append(rowCount).append(",\"rows\": [");

		for (ProcessMast processMast : processMasts) {
			
				sb.append("{\"id\":\"")
						.append(processMast.getId())
						.append("\",\"process\":\"")
						.append(processMast.getProcessLookUp().getFieldValue())
						.append("\",\"deptNm\":\"")
						.append(processMast.getDepartment().getName())
						.append("\",\"seqNo\":\"")
						.append(processMast.getSeqNo());	
						
				if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
						userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
						sb.append("\",\"action1\":\"");
							sb.append("<a href='/jewels/manufacturing/masters/processMast/edit/")
							  .append(processMast.getId()).append(".html'")
							  .append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
		
						sb.append("\",\"action2\":\"");
							sb.append(
									"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/processMast/delete/")
									.append(processMast.getId()).append(".html'")
									.append(" class='btn btn-xs btn-danger triggerRemove")
									.append(processMast.getId())
									.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
									.append("\"},");
				}else{
				
					sb.append("\",\"action1\":\"");
					if (roleRights.getCanEdit()) {
						sb.append("<a href='/jewels/manufacturing/masters/processMast/edit/")
							.append(processMast.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
					sb.append("\",\"action2\":\"");
					if (roleRights.getCanDelete()) {
						sb.append(
								"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/processMast/delete/")
								.append(processMast.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(processMast.getId())
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

	@RequestMapping("/processMast/add")
	public String add(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("processMast");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("processLookUpMap",lookUpMastService.getActiveLookUpMastByType("Mfg Process"));
			model.addAttribute("departmentMap",departmentService.getDepartmentList());
			return "processMast/add";

		}else	
		

		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("processLookUpMap",lookUpMastService.getActiveLookUpMastByType("Mfg Process"));
			model.addAttribute("departmentMap",departmentService.getDepartmentList());
		}

		return "processMast/add";
	}

	@ResponseBody
	@RequestMapping(value = "/processMast/add", method = RequestMethod.POST)
	public String addEditProcessMast(
			@Valid @ModelAttribute("processMast") ProcessMast processMast,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "/jewels/manufacturing/masters/processMast/add.html?success=true";

		if (result.hasErrors()) {
			return "processMast/add";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			
				
			
			processMast.setCreatedBy(principal.getName());
			processMast.setCreatedDt(new java.util.Date());
			
		} else {
			
			
			
			
			processMast.setModiBy(principal.getName());
			processMast.setModiDt(new java.util.Date());

			action = "updated";
			retVal = "/jewels/manufacturing/masters/processMast.html?success=true";
			
		}

		processMastService.save(processMast);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;
	}

	@RequestMapping("/processMast/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		ProcessMast processMast = processMastService.findOne(id);
		model.addAttribute("processMast", processMast);

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("processMast");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("processLookUpMap",lookUpMastService.getActiveLookUpMastByType("Mfg Process"));
			model.addAttribute("departmentMap",departmentService.getDepartmentList());
			return "processMast/add";

		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("processLookUpMap",lookUpMastService.getActiveLookUpMastByType("Mfg Process"));
			model.addAttribute("departmentMap",departmentService.getDepartmentList());
		
		}

		return "processMast/add";
	}

	@RequestMapping("/processMast/delete/{id}")
	public String delete(@PathVariable int id) {
		processMastService.delete(id);
		return "redirect:/manufacturing/masters/processMast.html";
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
}
