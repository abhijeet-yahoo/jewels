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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ProductTypeMast;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IProductTypeService;


@RequestMapping("/manufacturing/masters")
@Controller
public class ProductTypeController {
	
	@Autowired
	private IProductTypeService productTypeService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;
	
	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired 
	private UserRoleService userRoleService;
	
	
	@ModelAttribute("productTypeMast")
	public ProductTypeMast construct() {
		return new ProductTypeMast();
	}
	

	@RequestMapping("/productTypeMast")
	public String users(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("productTypeMast");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
	
			return "productTypeMast";
		}else
		
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}
		
		return "productTypeMast";
	}
	
	
	@RequestMapping("/productTypeMast/listing")
	@ResponseBody
	public String productTypeMastListing(Model model,


			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "opt", required = true) String opt,
			Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<ProductTypeMast> productTypeMasts = null;

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("productTypeMast");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}
		
		
	
		
		productTypeMasts = productTypeService.searchAll(limit, offset, sort, order, search,true);
		


		sb.append("{\"total\":").append(productTypeMasts.getTotalElements()).append(",\"rows\": [");

		for (ProductTypeMast productTypeMast : productTypeMasts) {
			if(opt.equals("1")){
			
			sb.append("{\"id\":\"")
					.append(productTypeMast.getId())
					.append("\",\"name\":\"")
					.append(productTypeMast.getName())
					.append("\",\"code\":\"")
					.append(productTypeMast.getCode()!=null?productTypeMast.getCode():"");
				
					
			if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
					userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
				
				sb.append("\",\"action1\":\"");
					sb.append("<a href='/jewels/manufacturing/masters/productTypeMast/edit/")
						.append(productTypeMast.getId()).append(".html'")
						.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
				sb.append("\",\"action2\":\"");
					sb.append(
							"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/productTypeMast/delete/")
							.append(productTypeMast.getId()).append(".html'")
							.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(productTypeMast.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
							.append("\"},");
	
			}else{
				sb.append("\",\"action1\":\"");
				if (roleRights.getCanEdit()) {
					sb.append("<a href='/jewels/manufacturing/masters/productTypeMast/edit/")
						.append(productTypeMast.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {
					sb.append(
							"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/productTypeMast/delete/")
							.append(productTypeMast.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-danger triggerRemove")
						.append(productTypeMast.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
						.append("\"},");
	
			}
			
								
		}
			else if(opt.equals("2")){
				sb.append("{\"id\":\"")
				.append(productTypeMast.getId())
				.append("\",\"name\":\"")
				.append(productTypeMast.getName())
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
	
	
	

	
	@RequestMapping("/productTypeMast/add")
	public String add(Model model,Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("productTypeMast");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "productTypeMast/add";

		}else	
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}
		
		return "productTypeMast/add";
	}
	
	
	
	
	@RequestMapping(value = "/productTypeMast/add", method = RequestMethod.POST)
	public String addEditUser(@Valid @ModelAttribute("productTypeMast") ProductTypeMast productTypeMast,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "redirect:/manufacturing/masters/productTypeMast/add.html";

		if (result.hasErrors()) {
			return "productTypeMast/add";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			productTypeMast.setCreatedBy(principal.getName());
			productTypeMast.setCreatedDate(new java.util.Date());
		} else {
			productTypeMast.setId(id);
			productTypeMast.setModiBy(principal.getName());
			productTypeMast.setModiDate(new java.util.Date());
			action = "updated";
			retVal = "redirect:/manufacturing/masters/productTypeMast.html";
		}

		productTypeService.save(productTypeMast);
		
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;

	}
	
	
	
	
	@RequestMapping("/productTypeMast/edit/{id}")
	public String edit(@PathVariable int id, Model model,Principal principal) {
		ProductTypeMast productTypeMast = productTypeService.findOne(id);
		model.addAttribute("productTypeMast", productTypeMast);

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("productTypeMast");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "productTypeMast/add";

		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}
		
		
		return "productTypeMast/add";
	}
	
	
	
	@RequestMapping("/productTypeMast/delete/{id}")
	public String delete(@PathVariable int id) {
		productTypeService.delete(id);

		return "redirect:/manufacturing/masters/productTypeMast.html";
	}
	
	
	
	
	@RequestMapping("/productTypeMastAvailable")
	@ResponseBody
	public String productTypeMastAvailable(@RequestParam String name,
			@RequestParam Integer id) {
		Boolean productTypeMastAvailable = true;

		if (id == null) {
			productTypeMastAvailable = (productTypeService.findByNameAndDeactive(name,false) == null);
		} else {
			ProductTypeMast productTypeMast = productTypeService.findOne(id);
			if (!(name.equalsIgnoreCase(productTypeMast.getName()))) {
				productTypeMastAvailable = (productTypeService.findByNameAndDeactive(name,false) == null);
			}
		}

		return productTypeMastAvailable.toString();
	}
	
	

	@RequestMapping("/productTypeMastCodeAvailable")
	@ResponseBody
	public String productTypeMastCodeAvailable(@RequestParam String code,
			@RequestParam Integer id) {
		Boolean productTypeMastCodeAvailable = true;

		if (id == null) {
			productTypeMastCodeAvailable = (productTypeService.findByCodeAndDeactive(code, false) == null);
		} else {
			ProductTypeMast productTypeMast = productTypeService.findOne(id);
			if (!(code.equalsIgnoreCase(productTypeMast.getCode()))) {
				productTypeMastCodeAvailable = (productTypeService.findByCodeAndDeactive(code, false) == null);
			}
		}

		return productTypeMastCodeAvailable.toString();
	}


}
