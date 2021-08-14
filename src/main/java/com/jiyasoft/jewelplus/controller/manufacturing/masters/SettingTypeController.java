package com.jiyasoft.jewelplus.controller.manufacturing.masters;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SettingType;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingTypeService;

@RequestMapping("/manufacturing/masters")
@Controller
public class SettingTypeController {
	
	@Autowired
	private ISettingTypeService settingTypeService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private UserRoleService userRoleService;

	@ModelAttribute("settingType")
	public SettingType construct() {
		return new SettingType();
	}

	@RequestMapping("/settingType")
	public String users(Model model,Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("settingType");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
	
			return "settingType";
		}else
		
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}

		return "settingType";
	}
	

	@RequestMapping("/settingType/listing")
	@ResponseBody
	public String settingTypeListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search, 
			@RequestParam(value = "opt", required = false) String opt,Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<SettingType> settingTypes = null;

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("settingType");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

		Long rowCount = null;
		if (search == null && sort == null) {
			rowCount = settingTypeService.count(sort, search, false);
			settingTypes = settingTypeService.findByName(limit, offset, sort, order,search, false);
		} 
		else if (search != null && sort != null && sort.equalsIgnoreCase("name")) {
			rowCount = settingTypeService.count(sort, search, false);
			settingTypes = settingTypeService.findByName(limit, offset, sort, order,search, false);
		} 
		else if (search != null && sort != null) {
			rowCount = 0L;
			settingTypes = new PageImpl<SettingType>(new ArrayList<SettingType>());
		} 
		else {
			rowCount = settingTypeService.count(sort, search, false);
			settingTypes = settingTypeService.findByName(limit, offset, sort, order,search, false);
		}

		sb.append("{\"total\":").append(rowCount).append(",\"rows\": [");


		for (SettingType settingType : settingTypes) {
			if(opt.equals("1")){
				sb.append("{\"id\":\"")
					.append(settingType.getId())
					.append("\",\"name\":\"")
					.append(settingType.getName())
					.append("\",\"code\":\"")
					.append(settingType.getCode())
					.append("\",\"deactive\":\"")
					.append(settingType.isDeactive() ? "Deactive" : "Active");
				
				if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
						userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
				
					sb.append("\",\"action1\":\"")
							.append("<a href='/jewels/manufacturing/masters/settingType/edit/")
							.append(settingType.getId()).append(".html'")
							.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
					sb.append("\",\"action2\":\"")
							.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/settingType/delete/")
							.append(settingType.getId()).append(".html'")
							.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(settingType.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
							.append("\"},");
					
				}else{
					
					sb.append("\",\"action1\":\"");
					if (roleRights.getCanEdit()) {
						sb.append("<a href='/jewels/manufacturing/masters/settingType/edit/")
							.append(settingType.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
					sb.append("\",\"action2\":\"");
					if (roleRights.getCanDelete()) {
						sb.append(
								"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/settingType/delete/")
								.append(settingType.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(settingType.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
							.append("\"},");
					
					
				}
				
					/*.append("\",\"action1\":\"")
					.append("<a href='/jewels/manufacturing/masters/settingType/edit/")
					.append(settingType.getId())
					.append(".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
					.append("\",\"action2\":\"")
					.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/settingType/delete/")
					.append(settingType.getId())
					.append(".html' class='btn btn-xs btn-danger triggerRemove")
					.append(settingType.getId())
					.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
					.append("\"},");*/
			}else if(opt.equals("3")){
				sb.append("{\"id\":\"")
				.append(settingType.getId())
				.append("\",\"name\":\"")
				.append(settingType.getName())
				.append("\",\"code\":\"")
				.append(settingType.getCode())
				.append("\"},");
			}
			
		
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;

	}

	@RequestMapping("/settingType/add")
	public String add(Model model,Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("settingType");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "settingType/add";

		}else	
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}
		
		return "settingType/add";
	}

	@RequestMapping(value = "/settingType/add", method = RequestMethod.POST)
	public String addEditUser(@Valid @ModelAttribute("settingType") SettingType settingType,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "redirect:/manufacturing/masters/settingType/add.html";

		if (result.hasErrors()) {
			return "settingType/add";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			settingType.setCreatedBy(principal.getName());
			settingType.setCreateDate(new java.util.Date());
		} else {
			settingType.setId(id);
			settingType.setModiBy(principal.getName());
			settingType.setModiDt(new java.util.Date());
			action = "updated";
			retVal = "redirect:/manufacturing/masters/settingType.html";
		}

		settingTypeService.save(settingType);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;

	}

	@RequestMapping("/settingType/edit/{id}")
	public String edit(@PathVariable int id, Model model,Principal principal) {
		SettingType settingType = settingTypeService.findOne(id);
		model.addAttribute("settingType", settingType);
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("settingType");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "settingType/add";

		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}

		return "settingType/add";
	}

	@RequestMapping("/settingType/delete/{id}")
	public String delete(@PathVariable int id) {
		settingTypeService.delete(id);

		return "redirect:/manufacturing/masters/settingType.html";
	}

	@RequestMapping("/settingTypeAvailable")
	@ResponseBody
	public String settingTypeAvailable(@RequestParam String name,
			@RequestParam Integer id) {
		Boolean settingTypeAvailable = true;

		if (id == null) {
			settingTypeAvailable = (settingTypeService.findByName(name) == null);
		} else {
			SettingType settingType = settingTypeService.findOne(id);
			if (!(name.equalsIgnoreCase(settingType.getName()))) {
				settingTypeAvailable = (settingTypeService.findByName(name) == null);
			}
		}

		return settingTypeAvailable.toString();
	}

	
	
	@ResponseBody
	@RequestMapping("/settingTypeList")
	public String settingTypeCombo(){
		
		List<SettingType> settingTypes = settingTypeService.findAll();
		JSONObject jsonObject = new JSONObject();
		
		for(SettingType settingType:settingTypes){
			jsonObject.put(settingType.getName(), settingType.getName());
		}
	
		return jsonObject.toString();
	}
	
	

}
