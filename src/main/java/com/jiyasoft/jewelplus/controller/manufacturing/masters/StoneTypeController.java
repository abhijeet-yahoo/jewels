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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;

@RequestMapping("/manufacturing/masters")
@Controller
public class StoneTypeController {

	@Autowired
	private IStoneTypeService stoneTypeService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private UserRoleService userRoleService;

	@ModelAttribute("stoneType")
	public StoneType construct() {
		return new StoneType();
	}

	@RequestMapping("/stoneType")
	public String users(Model model,Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stoneType");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
	
			return "stoneType";
		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}
		
		return "stoneType";
	}

	@RequestMapping("/stoneType/listing")
	@ResponseBody
	public String stoneTypeListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "opt", required = false) String opt, Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<StoneType> stoneTypes = null;

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stoneType");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		
		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}
		
		stoneTypes = stoneTypeService.searchAll(limit, offset, sort, order, search, false);

		sb.append("{\"total\":").append(stoneTypes.getTotalElements()).append(",\"rows\": [");
		
		
		if(opt.equals("1")){
			
			for (StoneType stoneType : stoneTypes) {
				sb.append("{\"id\":\"")
						.append(stoneType.getId())
						.append("\",\"name\":\"")
						.append(stoneType.getName())
						.append("\",\"code\":\"")
						.append(stoneType.getCode()!= null ? stoneType.getCode():"")
						.append("\",\"transferRatePerc\":\"")
						.append(stoneType.getTransferRatePerc() != null ? stoneType.getTransferRatePerc() : "")
						.append("\",\"factoryRatePerc\":\"")
						.append(stoneType.getFactoryRatePerc() != null ? stoneType.getFactoryRatePerc() : "")
						.append("\"},");
			}
			
		}else if(opt.equals("2")){

		for (StoneType stoneType : stoneTypes) {
			sb.append("{\"id\":\"")
					.append(stoneType.getId())
					.append("\",\"name\":\"")
					.append(stoneType.getName())
					.append("\",\"code\":\"")
					.append(stoneType.getCode()!= null ? stoneType.getCode():"")
					.append("\",\"transferRatePerc\":\"")
					.append(stoneType.getTransferRatePerc() != null ? stoneType.getTransferRatePerc() : "")
					.append("\",\"factoryRatePerc\":\"")
					.append(stoneType.getFactoryRatePerc() != null ? stoneType.getFactoryRatePerc() : "")
					.append("\",\"deactive\":\"")
					.append(stoneType.isDeactive() ? "Yes":"No");
			
			if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
					userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
			
					sb.append("\",\"action1\":\"")
							.append("<a href='/jewels/manufacturing/masters/stoneType/edit/")
							.append(stoneType.getId()).append(".html'")
							.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
		
					sb.append("\",\"action2\":\"")
							.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/stoneType/delete/")
							.append(stoneType.getId()).append(".html'")
							.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(stoneType.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
							.append("\"},");

			}else{
				sb.append("\",\"action1\":\"");
				if (roleRights.getCanEdit()) {
					sb.append("<a href='/jewels/manufacturing/masters/stoneType/edit/")
						.append(stoneType.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {
					sb.append(
							"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/stoneType/delete/")
							.append(stoneType.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-danger triggerRemove")
						.append(stoneType.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
						.append("\"},");

			}
			}	
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;

	}

	@RequestMapping("/stoneType/add")
	public String add(Model model,Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stoneType");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "stoneType/add";

		}else	
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}
		
		return "stoneType/add";
	}

	@RequestMapping(value = "/stoneType/add", method = RequestMethod.POST)
	public String addEditUser(
			@Valid @ModelAttribute("stoneType") StoneType stoneType,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "redirect:/manufacturing/masters/stoneType/add.html";

		if (result.hasErrors()) {
			return "stoneType/add";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			stoneType.setCreatedBy(principal.getName());
			stoneType.setCreatedDate(new java.util.Date());
		} else {
			stoneType.setId(id);
			stoneType.setModiBy(principal.getName());
			stoneType.setModiDt(new java.util.Date());

			action = "updated";
			retVal = "redirect:/manufacturing/masters/stoneType.html";
		}
		
		
		if(stoneType.getTransferRatePerc()==null) {
			stoneType.setTransferRatePerc(0.0);
		}
		
		if(stoneType.getFactoryRatePerc()==null) {
			stoneType.setFactoryRatePerc(0.0);
		}

		stoneTypeService.save(stoneType);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;

	}

	@RequestMapping("/stoneType/edit/{id}")
	public String edit(@PathVariable int id, Model model,Principal principal) {
		StoneType stoneType = stoneTypeService.findOne(id);
		model.addAttribute("stoneType", stoneType);

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stoneType");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "stoneType/add";

		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}
		
		return "stoneType/add";
	}

	@RequestMapping("/stoneType/delete/{id}")
	public String delete(@PathVariable int id) {
		stoneTypeService.delete(id);

		return "redirect:/manufacturing/masters/stoneType.html";
	}

	@RequestMapping("/stoneTypeAvailable")
	@ResponseBody
	public String stoneTypeAvailable(@RequestParam String name,
			@RequestParam Integer id) {
		Boolean stoneTypeAvailable = true;

		if (id == null) {
			stoneTypeAvailable = (stoneTypeService.findByName(name) == null);
		} else {
			StoneType stoneType = stoneTypeService.findOne(id);
			if (!(name.equalsIgnoreCase(stoneType.getName()))) {
				stoneTypeAvailable = (stoneTypeService.findByName(name) == null);
			}
		}

		return stoneTypeAvailable.toString();
	}

}
