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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderType;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderTypeService;


@RequestMapping("/manufacturing/masters")
@Controller
public class OrderTypeController {
	
	@Autowired
	private IOrderTypeService orderTypeService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private UserRoleService userRoleService;

	@ModelAttribute("orderType")
	public OrderType construct() {
		return new OrderType();
	}

	@RequestMapping("/orderType")
	public String users(Model model,Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("orderType");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
	
			return "orderType";
		}else
		
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}

		return "orderType";
	}
	

	@RequestMapping("/orderType/listing")
	@ResponseBody
	public String orderTypeListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "opt", required = false) String opt,
			Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<OrderType> orderTypes = null;

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("orderType");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

		Long rowCount = null;
		
		orderTypes = orderTypeService.searchAll(limit, offset, sort, order, search, false);
		rowCount = orderTypeService.countAll(sort, search, false);
		

		// default search would be name wise
		/*if (search == null && sort == null) {
			rowCount = orderTypeService.count(sort, search, false);
			orderTypes = orderTypeService.findByName(limit, offset, sort, order,
					search, false);
		} else if (search != null && sort != null
				&& sort.equalsIgnoreCase("name")) {
			rowCount = orderTypeService.count(sort, search, false);
			orderTypes = orderTypeService.findByName(limit, offset, sort, order,
					search, false);
		} else if (search != null && sort != null) {
			rowCount = 0L;
			orderTypes = new PageImpl<OrderType>(new ArrayList<OrderType>());
		} else {
			rowCount = orderTypeService.count(sort, search, false);
			orderTypes = orderTypeService.findByName(limit, offset, sort, order,
					search, false);
		}*/
		
		
		sb.append("{\"total\":").append(rowCount).append(",\"rows\": [");


		for (OrderType orderType : orderTypes) {
			if(opt.equals("1")){
	
			sb.append("{\"id\":\"")
					.append(orderType.getId())
					.append("\",\"name\":\"")
					.append(orderType.getName())
					.append("\",\"code\":\"")
					.append(orderType.getCode())
					.append("\",\"bagPrefix\":\"")
					.append(orderType.getBagPrefix() !=null ?orderType.getBagPrefix():"")
					.append("\",\"deactive\":\"")
					.append(orderType.isDeactive() ? "Deactive" : "Active");
			
					if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
							userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
						
						sb.append("\",\"action1\":\"")
								.append("<a href='/jewels/manufacturing/masters/orderType/edit/")
								.append(orderType.getId()).append(".html'")
								.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
			
						sb.append("\",\"action2\":\"")
								.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/orderType/delete/")
								.append(orderType.getId()).append(".html'")
								.append(" class='btn btn-xs btn-danger triggerRemove")
								.append(orderType.getId())
								.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
								.append("\"},");
			
						
					}else{
						sb.append("\",\"action1\":\"");
						if (roleRights.getCanEdit()) {
							sb.append("<a href='/jewels/manufacturing/masters/orderType/edit/")
								.append(orderType.getId()).append(".html'");
						} else {
							sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
						}
						sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
			
						sb.append("\",\"action2\":\"");
						if (roleRights.getCanDelete()) {
							sb.append(
									"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/orderType/delete/")
									.append(orderType.getId()).append(".html'");
						} else {
							sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
						}
						sb.append(" class='btn btn-xs btn-danger triggerRemove")
								.append(orderType.getId())
								.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
								.append("\"},");
			
					}
			
						}else if(opt.equals("3")){
				sb.append("{\"id\":\"")
				.append(orderType.getId())
				.append("\",\"name\":\"")
				.append(orderType.getName())
				.append("\",\"code\":\"")
				.append(orderType.getCode())
				.append("\"},");
			}
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";

		System.out.println("program "+str);
		return str;

	}

	@RequestMapping("/orderType/add")
	public String add(Model model,Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("orderType");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "orderType/add";

		}else	
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}
		
		return "orderType/add";
	}

	@RequestMapping(value = "/orderType/add", method = RequestMethod.POST)
	public String addEditUser(@Valid @ModelAttribute("orderType") OrderType orderType,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "redirect:/manufacturing/masters/orderType/add.html";

		if (result.hasErrors()) {
			return "orderType/add";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			orderType.setCreatedBy(principal.getName());
			orderType.setCreateDate(new java.util.Date());
		} else {
			orderType.setId(id);
			orderType.setModiBy(principal.getName());
			orderType.setModiDt(new java.util.Date());
			action = "updated";
			retVal = "redirect:/manufacturing/masters/orderType.html";
		}

		orderTypeService.save(orderType);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;

	}

	@RequestMapping("/orderType/edit/{id}")
	public String edit(@PathVariable int id, Model model,Principal principal) {
		OrderType orderType = orderTypeService.findOne(id);
		model.addAttribute("orderType", orderType);

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("orderType");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "orderType/add";

		}else	
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}
		
		
		return "orderType/add";
	}

	@RequestMapping("/orderType/delete/{id}")
	public String delete(@PathVariable int id) {
		orderTypeService.delete(id);

		return "redirect:/manufacturing/masters/orderType.html";
	}

	@RequestMapping("/orderTypeAvailable")
	@ResponseBody
	public String orderTypeAvailable(@RequestParam String name,
			@RequestParam Integer id) {
		Boolean orderTypeAvailable = true;

		if (id == null) {
			orderTypeAvailable = (orderTypeService.findByName(name) == null);
		} else {
			OrderType orderType = orderTypeService.findOne(id);
			if (!(name.equalsIgnoreCase(orderType.getName()))) {
				orderTypeAvailable = (orderTypeService.findByName(name) == null);
			}
		}

		return orderTypeAvailable.toString();
	}

}
