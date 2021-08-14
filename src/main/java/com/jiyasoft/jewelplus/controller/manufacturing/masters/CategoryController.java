package com.jiyasoft.jewelplus.controller.manufacturing.masters;

import java.security.Principal;
import java.util.List;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Category;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ICategoryService;

@RequestMapping("/manufacturing/masters")
@Controller
public class CategoryController {

	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private UserRoleService userRoleService;

	@ModelAttribute("category")
	public Category construct() {
		return new Category();
	}

	@RequestMapping("/category")
	public String users(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("category");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
	
			return "category";
		}else
		
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}
		
		return "category";
	}

	@RequestMapping("/category/listing")
	@ResponseBody
	public String categoryListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "opt", required = true) String opt,
			Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<Category> categories = null;

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("category");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

	//	Long rowCount = null;

				
		categories = categoryService.searchAll(limit, offset, sort, order, search, false);
	//	rowCount = categoryService.countAll(sort, search, false);

		sb.append("{\"total\":").append(categories.getTotalElements()).append(",\"rows\": [");

		for (Category category : categories) {
			if (opt.equals("1")) {
				sb.append("{\"id\":\"")
						.append(category.getId())
						.append("\",\"name\":\"")
						.append(category.getName())
						.append("\",\"categCode\":\"")
						.append((category.getCategCode() == null ? "" : category.getCategCode()))
						.append("\",\"labourRate\":\"")
						.append((category.getLabourRate() == null ? "" : category.getLabourRate()))
						.append("\",\"updatedBy\":\"")
						.append((category.getModiBy() == null ? "" : category
								.getModiBy()))
						.append("\",\"updatedDt\":\"")
						.append((category.getModiDt() == null ? "" : category
								.getModiDt()))
						.append("\",\"deactive\":\"")
						.append((category.getDeactive() == null ? "": (category.getDeactive() ? "Deactive": "Active")))
						.append("\",\"deactiveDt\":\"")
						.append((category.getDeactiveDt() == null ? ""
								: category.getDeactiveDt()));
				if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
						userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
					
					sb.append("\",\"action1\":\"");
						sb.append("<a href='/jewels/manufacturing/masters/category/edit/")
						  .append(category.getId()).append(".html'")
					
					   .append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
					sb.append("\",\"action2\":\"");
						sb.append(
								"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/category/delete/")
								.append(category.getId()).append(".html'")
					
							.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(category.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
							.append("\"},");
				}else{
					sb.append("\",\"action1\":\"");
					if (roleRights.getCanEdit()) {
						sb.append("<a href='/jewels/manufacturing/masters/category/edit/")
							.append(category.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
					sb.append("\",\"action2\":\"");
					if (roleRights.getCanDelete()) {
						sb.append(
								"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/category/delete/")
								.append(category.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(category.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
							.append("\"},");
				}
						
				

			} else if (opt.equals("2")) {
				sb.append("{\"name\":\"")
						.append("<a href='javascript:getChild(")
						.append(category.getId()).append(")'>")
						.append(category.getName()).append("</a>\"},");
			}else if(opt.equals("3")){
				sb.append("{\"id\":\"")
				.append(category.getId())
				.append("\",\"name\":\"")
				.append(category.getName())
				.append("\"},");
				
			}

		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
		
		
	}

	@RequestMapping("/category/add")
	public String add(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("category");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("adminRightsMap", true);
			
			return "category/add";

		}else	
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("adminRightsMap", false);
		}

		return "category/add";
	}

	@RequestMapping(value = "/category/add", method = RequestMethod.POST)
	public String addEditCategory(
			@Valid @ModelAttribute("category") Category category,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "redirect:/manufacturing/masters/category/add.html";

		if (result.hasErrors()) {
			return "category/add";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			category.setCreatedBy(principal.getName());
			category.setCreatedDt(new java.util.Date());
			category.setDeactive(false);
		} else {
			category.setModiBy(principal.getName());
			category.setModiDt(new java.util.Date());

			if (category.getDeactive() != categoryService.findOne(id).getDeactive()) {
				category.setDeactiveDt(new java.util.Date());
			} else {
				category.setDeactiveDt(categoryService.findOne(id).getDeactiveDt());
			}
			category.setId(id);

			action = "updated";
			retVal = "redirect:/manufacturing/masters/category.html";
		}

		categoryService.save(category);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;
	}

	@RequestMapping("/category/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		Category category = categoryService.findOne(id);
		model.addAttribute("category", category);

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("category");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("adminRightsMap", true);
			
			return "category/add";

		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("adminRightsMap", false);
		}

		return "category/add";
	}

	@RequestMapping("/category/delete/{id}")
	public String delete(@PathVariable int id) {
		categoryService.delete(id);
		return "redirect:/manufacturing/masters/category.html";
	}

	@RequestMapping("/categoryAvailable")
	@ResponseBody
	public String userAvailable(@RequestParam String name,
			@RequestParam Integer id) {
		
		Boolean categoryAvailable = true;

		name = name.trim();

		if (id == null) {
			categoryAvailable = (categoryService.findByName(name) == null);
		} else {
			Category category = categoryService.findOne(id);
			if (!(name.equalsIgnoreCase(category.getName()))) {
				categoryAvailable = (categoryService.findByName(name) == null);
			}
		}

		return categoryAvailable.toString();
	}
	
	@RequestMapping("/categoryCodeAvailable")
	@ResponseBody
	public String categoryCodeAvailable(@RequestParam String categCode,@RequestParam Integer id) {
		
		Boolean categoryAvailable = true;

		categCode = categCode.trim();

		if (id == null) {
			categoryAvailable = (categoryService.findByCategCode(categCode) == null);
		} else {
			Category category = categoryService.findOne(id);
			if (!(categCode.equalsIgnoreCase(category.getCategCode()))) {
				categoryAvailable = (categoryService.findByCategCode(categCode) == null);
			}
		}

		return categoryAvailable.toString();
	}
	
	
	
	@RequestMapping("/reportExcel")
	public ModelAndView getExcel(){
	       
	              List<Category> categoryList = categoryService.findAll(); 
	              
	              return new ModelAndView(new CategoryExcelReportView(), "categoryList", categoryList);
	       }
	
	
	
}
