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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Setting;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingService;


@RequestMapping("/manufacturing/masters")
@Controller
public class SettingController 
{
	@Autowired
	private ISettingService settingService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;
	
	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private MenuMastService menuMastService;
	
	@ModelAttribute("setting")
	public Setting construct(){
		return new Setting();
	}
	
	@RequestMapping("/setting")
	public String users(Model model,Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("setting");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
	
			return "setting";
		}else
	
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}

		return "setting";
	}
	
	@RequestMapping("/setting/listing")
	@ResponseBody
	public String settingListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "opt", required = true) String opt, Principal principal){
		
		StringBuilder sb = new StringBuilder();
		Page<Setting> settings = null;

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("setting");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

		Long rowCount = null;
		if (search == null && sort == null) {
			rowCount = settingService.count(sort, search, false);
			settings = settingService.findByName(limit, offset, sort, order,search, false);
		} 
		else if (search != null && sort != null && sort.equalsIgnoreCase("name")) {
			rowCount = settingService.count(sort, search, false);
			settings = settingService.findByName(limit, offset, sort, order,search, false);
		} 
		else if (search != null && sort != null) {
			rowCount = 0L;
			settings = new PageImpl<Setting>(new ArrayList<Setting>());
		} 
		else {
			rowCount = settingService.count(sort, search, false);
			settings = settingService.findByName(limit, offset, sort, order,search, false);
		}

		sb.append("{\"total\":").append(rowCount).append(",\"rows\": [");

		//UPDATED	
		for (Setting setting : settings) {
			if (opt.equals("1")){
				sb.append("{\"id\":\"")
					.append(setting.getId())
					.append("\",\"name\":\"")
					.append(setting.getName())
					.append("\",\"code\":\"")
					.append(setting.getCode())
					.append("\",\"deactive\":\"")
					.append(setting.isDeactive() ? "Yes":"No");
				
				if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
						userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
			
				
					sb.append("\",\"action1\":\"")
							.append("<a href='/jewels/manufacturing/masters/setting/edit/")
							.append(setting.getId()).append(".html'")
							.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
					sb.append("\",\"action2\":\"")
							.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/setting/delete/")
							.append(setting.getId()).append(".html'")
							.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(setting.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
							.append("\"},");
				}else{
					
					sb.append("\",\"action1\":\"");
					if (roleRights.getCanEdit()) {
						sb.append("<a href='/jewels/manufacturing/masters/setting/edit/")
							.append(setting.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
					sb.append("\",\"action2\":\"");
					if (roleRights.getCanDelete()) {
						sb.append(
								"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/setting/delete/")
								.append(setting.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(setting.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
							.append("\"},");
					
				}
				
			}else if (opt.equals("2")) {
				sb.append("{\"name\":\"")
				.append("<a href='javascript:getChild(")
				.append(setting.getId()).append(")'>")
				.append(setting.getName()).append("</a>\"},");
	}
		}
			
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
		
		}
	
	@RequestMapping("/setting/add")
	public String add(Model model,Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("setting");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "setting/add";

		}else	

		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}
		
		
		return "setting/add";
	}

	@RequestMapping(value = "/setting/add", method = RequestMethod.POST)
	public String addEditUser(
			@Valid @ModelAttribute("setting") Setting setting,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "redirect:/manufacturing/masters/setting/add.html";

		if (result.hasErrors()) {
			return "setting/add";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			setting.setCreatedBy(principal.getName());
			setting.setCreateDate(new java.util.Date());
		} else {
			setting.setId(id);
			setting.setModiBy(principal.getName());
			setting.setModiDt(new java.util.Date());

			action = "updated";
			retVal = "redirect:/manufacturing/masters/setting.html";
		}
	
		settingService.save(setting);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;
		
	}  
	@RequestMapping("/setting/edit/{id}")
	public String edit(@PathVariable int id, Model model,Principal principal) {
		Setting setting = settingService.findOne(id);
		model.addAttribute("setting", setting);
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("setting");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "setting/add";

		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}
		

		return "setting/add";
	}

	@RequestMapping("/setting/delete/{id}")
	public String delete(@PathVariable int id) {
		settingService.delete(id);

		return "redirect:/manufacturing/masters/setting.html";
	}
	
	@RequestMapping("/settingAvailable")
	@ResponseBody
	public String settingAvailable(@RequestParam String name, @RequestParam Integer id) {
		Boolean settingAvailable = true;

		if (id == null) {
			settingAvailable = (settingService.findByName(name) == null);
		} else {
			Setting setting = settingService.findOne(id);
			if (!(name.equalsIgnoreCase(setting.getName()))) {
				settingAvailable = (settingService.findByName(name) == null);				
			}
		}
		

		return settingAvailable.toString();
	}
	
	
	@ResponseBody
	@RequestMapping("/settinglist")
	public String settingCombo(){
		
		List<Setting> settings = settingService.findAll();
		
		JSONObject jsonObject = new JSONObject();
		
		for(Setting setting:settings){
			jsonObject.put(setting.getName(), setting.getName());
		}
		
	
		return jsonObject.toString();
	}
	

}
