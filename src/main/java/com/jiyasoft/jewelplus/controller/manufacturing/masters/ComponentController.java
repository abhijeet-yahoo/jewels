package com.jiyasoft.jewelplus.controller.manufacturing.masters;

import java.security.Principal;
import java.util.List;

import javax.persistence.EntityManager;
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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Component;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QCompTran;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IComponentService;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQuery;

@RequestMapping("/manufacturing/masters")
@Controller
public class ComponentController {
	@Autowired
	private IComponentService componentService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;

	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private EntityManager entityManager;

	@ModelAttribute("component")
	public Component construct() {
		return new Component();
	}

	@RequestMapping("/component")
	public String users(Model model,Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("component");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
	
			return "component";
		}else
		
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}

		return "component";
	}

	@RequestMapping("/component/listing")
	@ResponseBody
	public String componentListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "opt", required = true) String opt,
			Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<Component> components = null;

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("component");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;	
		}

	//	Long rowCount = null;
		

		components = componentService.searchAll(limit, offset, sort, order, search, false);
	//	rowCount = componentService.countAll(sort, search, false);

		/*// default search would be name wise
		if (search == null && sort == null) {
			rowCount = componentService.count(sort, search, false);
			components = componentService.findByName(limit, offset, sort, order, search, false);
			
		} else if (search != null && sort != null && sort.equalsIgnoreCase("name")) {
			rowCount = componentService.count(sort, search, false);
			components = componentService.findByName(limit, offset, sort, order, search, false);
			
		} else if (search != null && sort != null) {
			rowCount = 0L;
			components = new PageImpl<Component>(new ArrayList<Component>());
			
		} else {
			rowCount = componentService.count(sort, search, false);
			components = componentService.findByName(limit, offset, sort, order, search, false);
			
		}
*/
		sb.append("{\"total\":").append(components.getTotalElements()).append(",\"rows\": [");
		

		for (Component component : components) {
			if(opt.equals("1")){
			sb.append("{\"id\":\"")
					.append(component.getId())
					.append("\",\"name\":\"")
					.append(component.getName())
					.append("\",\"waxWt\":\"")
					.append(component.getWaxWt()!=null?component.getWaxWt():"")
					.append("\",\"remark\":\"")
					.append(component.getRemark()!=null?component.getRemark():"")
					.append("\",\"code\":\"")
					.append(component.getCode()!=null?component.getCode():"")
					.append("\",\"chargable\":\"")
					.append(component.getChargable() ? "Yes":"No")
					.append("\",\"notMetal\":\"")
					.append(component.getNotMetalFlg() ? "Yes":"No")
					.append("\",\"deactive\":\"")
					.append(component.isDeactive() ? "Yes":"No");


			if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
					userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
			
				sb.append("\",\"action1\":\"");
					sb.append("<a href='/jewels/manufacturing/masters/component/edit/")
						.append(component.getId()).append(".html'")
						.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

				sb.append("\",\"action2\":\"");
					sb.append(
							"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/component/delete/")
							.append(component.getId()).append(".html'")
							.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(component.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
							.append("\"},");

			}else{
				sb.append("\",\"action1\":\"");
				if (roleRights.getCanEdit()) {
					sb.append("<a href='/jewels/manufacturing/masters/component/edit/")
						.append(component.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {
					sb.append(
							"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/component/delete/")
							.append(component.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-danger triggerRemove")
				.append(component.getId())
				.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
				.append("\"},");

			}
			
						}
			else if(opt.equals("2")){
				sb.append("{\"id\":\"")
				.append(component.getId())
				.append("\",\"name\":\"")
				.append(component.getName())
				.append("\"},");
				
			}
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		System.out.println("------->>>>>"+str);

		return str;

	}

	@RequestMapping("/component/add")
	public String add(Model model,Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("component");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "component/add";

		}else	
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}
		
		return "component/add";
	}

	@RequestMapping(value = "/component/add", method = RequestMethod.POST)
	public String addEditUser(
			@Valid @ModelAttribute("component") Component component,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "redirect:/manufacturing/masters/component/add.html";
		Boolean compFlg = false;
		Boolean chgFlg = false;

		QCompTran qCompTran =QCompTran.compTran;
		JPAQuery query = new JPAQuery(entityManager);
		
		
		
		if (result.hasErrors()) {
			return "component/add";
		}
		
		
		
		
		if (id == null || id.equals("") || (id != null && id == 0)) {
			
			if(component.getNotMetalFlg()) {
				if(!component.getChargable()) {
					chgFlg = true;
				}
			}
			
			if(chgFlg){
				action = "For Non-Metal, Chargeable is compulsory ";
				redirectAttributes.addFlashAttribute("error", true);
			}else {
				component.setCreatedBy(principal.getName());
				component.setCreatedDate(new java.util.Date());
				componentService.save(component);
				redirectAttributes.addFlashAttribute("success", true);
			}
			
			
		
		} else {
						
			List<Tuple> compTrans = query.from(qCompTran).where(qCompTran.deactive.eq(false).and(qCompTran.component.id.eq(id))).limit(1).list(qCompTran.component,qCompTran.component.id);
			
			if(compTrans.size()>0) {
				compFlg = true;
			}else {
				compFlg = false;
			}
			
			if(component.getNotMetalFlg()) {
				if(!component.getChargable()) {
					chgFlg = true;
				}
			}
			
			

			if(!chgFlg) {
				
			if(!compFlg) {
			component.setModiBy(principal.getName());
			component.setModiDate(new java.util.Date());
			componentService.save(component);
			action = "updated";
			retVal = "redirect:/manufacturing/masters/component.html";
			redirectAttributes.addFlashAttribute("success", true);
			}else {
				
				action = "Can not edit, Component already used in transactions.";
				retVal = "redirect:/manufacturing/masters/component/edit/"+id+".html";
				redirectAttributes.addFlashAttribute("error", true);
				
			}
			}else {
				action = "For Non-Metal, Chargeable is compulsory ";
				redirectAttributes.addFlashAttribute("error", true);
				retVal = "redirect:/manufacturing/masters/component/edit/"+id+".html";
			}
			
		}
		
		
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;

	}

	@RequestMapping("/component/edit/{id}")
	public String edit(@PathVariable int id, Model model,Principal principal) {
		Component component = componentService.findOne(id);
		model.addAttribute("component", component);

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("component");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "component/add";

		}else	
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}
		
		return "component/add";
	}

	@RequestMapping("/component/delete/{id}")
	public String delete(@PathVariable int id) {
		componentService.delete(id);

		return "redirect:/manufacturing/masters/component.html";
	}

	@RequestMapping("/componentAvailable")
	@ResponseBody
	public String componentAvailable(@RequestParam String name,
			@RequestParam Integer id) {
		Boolean componentAvailable = true;

		if (id == null) {
			componentAvailable = (componentService.findByName(name) == null);
		} else {
			Component component = componentService.findOne(id);
			if (!(name.equalsIgnoreCase(component.getName()))) {
				componentAvailable = (componentService.findByName(name) == null);
			}
		}

		// System.out.println(id+" "+name);

		return componentAvailable.toString();
	}

}
