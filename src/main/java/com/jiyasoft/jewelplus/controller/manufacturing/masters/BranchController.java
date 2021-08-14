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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.BranchMaster;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IBranchService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ICountryService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStateMasterService;

@RequestMapping("/manufacturing/masters")
@Controller
public class BranchController {
	
	@Autowired
	private IBranchService branchService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;
	
	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired 
	private UserRoleService userRoleService;
	
	
	@Autowired
	private ICountryService countryService;
	
	@Autowired
	private IStateMasterService stateMasterService;
	
	@ModelAttribute("branchMaster")
	public BranchMaster construct() {
		return new BranchMaster();
	}

	@RequestMapping("/branchMaster")
	public String users(Model model, Principal principal) {
	User user = userService.findByName(principal.getName());
	UserRole userRole = userRoleService.findByUser(user);
	MenuMast menuMast = menuMastService.findByMenuNm("branchMaster");
	RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
	
	if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
			userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
	
		model.addAttribute("canAdd", true);
	
		return "branchMaster";
		
	}else if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}
	return "branchMaster";
	}
	
	@RequestMapping("/branchMaster/listing")
	@ResponseBody
	public String branchMasterListing(Model model,


			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "opt", required = true) String opt,
			Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<BranchMaster> branchMasters = null;
	
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("branchMasters");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}
	
		branchMasters = branchService.searchAll(limit, offset, sort, order, search,true);
		


		sb.append("{\"total\":").append(branchMasters.getTotalElements()).append(",\"rows\": [");

		for (BranchMaster branchMaster : branchMasters) {
			if(opt.equals("1")){
			
			sb.append("{\"id\":\"")
					.append(branchMaster.getId())
					.append("\",\"name\":\"")
					.append(branchMaster.getName())
					.append("\",\"code\":\"")
					.append(branchMaster.getCode()!=null?branchMaster.getCode():"");
				
					
			if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
					userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
				
				sb.append("\",\"action1\":\"");
					sb.append("<a href='/jewels/manufacturing/masters/branchMaster/edit/")
						.append(branchMaster.getId()).append(".html'")
						.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
				sb.append("\",\"action2\":\"");
					sb.append(
							"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/branchMaster/delete/")
							.append(branchMaster.getId()).append(".html'")
							.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(branchMaster.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
							.append("\"},");
	
			}else{
				sb.append("\",\"action1\":\"");
				if (roleRights.getCanEdit()) {
					sb.append("<a href='/jewels/manufacturing/masters/branchMaster/edit/")
						.append(branchMaster.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {
					sb.append(
							"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/branchMaster/delete/")
							.append(branchMaster.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-danger triggerRemove")
						.append(branchMaster.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
						.append("\"},");
	
			}
			
								
		}
			else if(opt.equals("2")){
				sb.append("{\"id\":\"")
				.append(branchMaster.getId())
				.append("\",\"name\":\"")
				.append(branchMaster.getName())
				.append("\"},");
			}
		}		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		System.err.println(str);
		
		return str;
	}
	
	
	
	@RequestMapping("/branchMaster/add")
	public String add(Model model,Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("branchMaster");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		model.addAttribute("countryMap", countryService.getCountryList());
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "branchMaster/add";

		}else	
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}
		
		return "branchMaster/add";
	}
	
	
	@RequestMapping(value = "/branchMaster/add", method = RequestMethod.POST)
	public String addEditUser(@Valid @ModelAttribute("branchMaster") BranchMaster branchMaster,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "redirect:/manufacturing/masters/branchMaster/add.html";

		if (result.hasErrors()) {
			return "branchMaster/add";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			branchMaster.setCreatedBy(principal.getName());
			branchMaster.setCreatedDate(new java.util.Date());
		} else {
			branchMaster.setId(id);
			branchMaster.setModiBy(principal.getName());
			branchMaster.setModiDate(new java.util.Date());
			action = "updated";
			retVal = "redirect:/manufacturing/masters/branchMaster.html";
		}
		
		if(branchMaster.getCountry().getId() == null) {
			branchMaster.setCountry(null);
		}
		
		if(branchMaster.getStateMast().getId() == null) {
			branchMaster.setStateMast(null);
		}

		branchService.save(branchMaster);
		
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;

	}
	
	@RequestMapping("/branchMaster/edit/{id}")
	public String edit(@PathVariable int id, Model model,Principal principal) {
		BranchMaster branchMaster = branchService.findOne(id);
		model.addAttribute("branchMaster", branchMaster);

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("branchMaster");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		model.addAttribute("countryMap", countryService.getCountryList());
		if(branchMaster.getCountry() != null){
			model.addAttribute("stateMap", stateMasterService.getStateListByCountry(branchMaster.getCountry().getId()));
			
			}
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "branchMaster/add";

		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}
		
		
		return "branchMaster/add";
	}
	
	
	@RequestMapping("/branchMaster/delete/{id}")
	public String delete(@PathVariable int id) {
		branchService.delete(id);

		return "redirect:/manufacturing/masters/branchMaster.html";
	}
	
	@RequestMapping("/branchMasterAvailable")
	@ResponseBody
	public String branchMasterAvailable(@RequestParam String name,
			@RequestParam Integer id) {
		Boolean branchMasterAvailable = true;

		if (id == null) {
			branchMasterAvailable = (branchService.findByNameAndDeactive(name,false) == null);
		} else {
			BranchMaster branchMaster = branchService.findOne(id);
			if (!(name.equalsIgnoreCase(branchMaster.getName()))) {
				branchMasterAvailable = (branchService.findByNameAndDeactive(name,false) == null);
			}
		}

		return branchMasterAvailable.toString();
	}
	
	

	@RequestMapping("/branchMasterCodeAvailable")
	@ResponseBody
	public String branchMasterCodeAvailable(@RequestParam String code,
			@RequestParam Integer id) {
		Boolean branchMasterCodeAvailable = true;

		if (id == null) {
			branchMasterCodeAvailable = (branchService.findByCodeAndDeactive(code, false) == null);
		} else {
			BranchMaster branchMaster = branchService.findOne(id);
			if (!(code.equalsIgnoreCase(branchMaster.getCode()))) {
				branchMasterCodeAvailable = (branchService.findByCodeAndDeactive(code, false) == null);
			}
		}

		return branchMasterCodeAvailable.toString();
	}
	
	
	
	
}
