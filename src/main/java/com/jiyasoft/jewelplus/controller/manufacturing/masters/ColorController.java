package com.jiyasoft.jewelplus.controller.manufacturing.masters;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.json.JSONObject;
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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;

@RequestMapping("/manufacturing/masters")
@Controller
public class ColorController {
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;

	@Autowired
	private UserRoleService userRoleService;

	@ModelAttribute("color")
	public Color construct() {
		return new Color();
	}

	@RequestMapping("/color")
	public String users(Model model,Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("color");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
	
			return "color";
		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}
		return "color";
	}
	

	@RequestMapping("/color/listing")
	@ResponseBody
	public String colorListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "opt", required = true) String opt,
			Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<Color> colors = null;

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("color");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}
		
		colors = colorService.searchAll(limit, offset, sort, order, search, false);


		sb.append("{\"total\":").append(colors.getTotalElements()).append(",\"rows\": [");

		for (Color color : colors) {
			if(opt.equals("1")){
			
			sb.append("{\"id\":\"")
					.append(color.getId())
					.append("\",\"name\":\"")
					.append(color.getName())
					.append("\",\"desc\":\"")
					.append(color.getDesc()!=null?color.getDesc():"")
					.append("\",\"twoTone\":\"")
					.append(color.getTwoTone()!=null?color.getTwoTone():"")
					.append("\",\"colorTone\":\"")
					.append(color.getColorTone()!=null?color.getColorTone():"")
					.append("\",\"deactive\":\"")
					.append(color.isDeactive() ? "Yes":"No")
					.append("\",\"defColor\":\"")
					.append(color.getDefColor() ? "Yes":"No");
			
			if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
					userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
				
				sb.append("\",\"action1\":\"");
					sb.append("<a href='/jewels/manufacturing/masters/color/edit/")
						.append(color.getId()).append(".html'")
						.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
				sb.append("\",\"action2\":\"");
					sb.append(
							"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/color/delete/")
							.append(color.getId()).append(".html'")
							.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(color.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
							.append("\"},");
	
			}else{
				sb.append("\",\"action1\":\"");
				if (roleRights.getCanEdit()) {
					sb.append("<a href='/jewels/manufacturing/masters/color/edit/")
						.append(color.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {
					sb.append(
							"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/color/delete/")
							.append(color.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-danger triggerRemove")
						.append(color.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
						.append("\"},");
	
			}
			
								
		}
			else if(opt.equals("2")){
				sb.append("{\"id\":\"")
				.append(color.getId())
				.append("\",\"name\":\"")
				.append(color.getName())
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

	@RequestMapping("/color/add")
	public String add(Model model,Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("color");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "color/add";

		}else	
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}
		
		return "color/add";
	}

	@RequestMapping(value = "/color/add", method = RequestMethod.POST)
	public String addEditUser(@Valid @ModelAttribute("color") Color color,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "redirect:/manufacturing/masters/color/add.html";

		if (result.hasErrors()) {
			return "color/add";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			color.setCreatedBy(principal.getName());
			color.setCreatedDate(new java.util.Date());
		} else {
			color.setId(id);
			color.setModiBy(principal.getName());
			color.setModiDate(new java.util.Date());
			action = "updated";
			retVal = "redirect:/manufacturing/masters/color.html";
		}

		colorService.save(color);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;

	}

	@RequestMapping("/color/edit/{id}")
	public String edit(@PathVariable int id, Model model,Principal principal) {
		Color color = colorService.findOne(id);
		model.addAttribute("color", color);

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("color");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "color/add";

		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}
		
		
		return "color/add";
	}

	@RequestMapping("/color/delete/{id}")
	public String delete(@PathVariable int id) {
		colorService.delete(id);

		return "redirect:/manufacturing/masters/color.html";
	}

	@RequestMapping("/colorAvailable")
	@ResponseBody
	public String colorAvailable(@RequestParam String name,
			@RequestParam Integer id) {
		Boolean colorAvailable = true;

		if (id == null) {
			colorAvailable = (colorService.findByName(name) == null);
		} else {
			Color color = colorService.findOne(id);
			if (!(name.equalsIgnoreCase(color.getName()))) {
				colorAvailable = (colorService.findByName(name) == null);
			}
		}

		return colorAvailable.toString();
	}

	
	@ResponseBody
	@RequestMapping("/colorlist")
	public String colorCombo(){
		
		List<Color> colors = colorService.findAll();
		JSONObject jsonObject = new JSONObject();
		
		for(Color color:colors){
			jsonObject.put(color.getName(), color.getName());
		}
	
		return jsonObject.toString();
	}
	
	
	@ResponseBody
	@RequestMapping("/defColorAvailable")
	public String defaultColorAvailable(@RequestParam Integer id, @RequestParam Boolean defColor){
		
		System.out.println("in color     "+defColor);
		Boolean defColorAvailable = true;
		
		defColorAvailable =(colorService.findByDefColorAndDeactive(defColor, false)== null);
		
		Color color =  colorService.findByDefColorAndDeactive(defColor, false);
		
		if(color != null && color.getId() == id){
			defColorAvailable =true;
		}
		
		return defColorAvailable.toString();
		
	}
	
}
