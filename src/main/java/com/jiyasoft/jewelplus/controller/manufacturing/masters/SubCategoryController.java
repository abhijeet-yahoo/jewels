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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SubCategory;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ICategoryService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISubCategoryService;

@RequestMapping("/manufacturing/masters")
@Controller
public class SubCategoryController {

	@Autowired
	private ICategoryService categoryService;;

	@Autowired
	private ISubCategoryService subCategoryService;;

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private UserRoleService userRoleService;

	@ModelAttribute("subCategory")
	public SubCategory construct() {
		return new SubCategory();
	}

	@RequestMapping("/subCategory")
	public String users(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("subCategory");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			
			
			return "subCategory";
		}else
		
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}

		return "subCategory";
	}

	@RequestMapping("/subCategory/listing")
	@ResponseBody
	public String subCategoryListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "opt", required = true) String opt,
			Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<SubCategory> subCategories = null;

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("subCategory");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

		/*Long rowCount = null;

		if (search == null) {
			if (id == null) {
				rowCount = subCategoryService.count(sort, search, false);
				subCategories = subCategoryService.findAll(limit, offset, sort,
						order, search);
			} else {
				rowCount = subCategoryService.count(sort, search, false);
				subCategories = subCategoryService.findByCategory(
						categoryService.findOne(id), limit, offset, sort,
						order, search);
			}
		} else {

			if (search != null && sort != null && sort.equalsIgnoreCase("name")) {
				rowCount = subCategoryService.count(sort, search, false);
				subCategories = subCategoryService.findByName(limit, offset,
						sort, order, search, false);
			} else if (search != null && sort != null) {
				rowCount = 0L;
				subCategories = new PageImpl<SubCategory>(
						new ArrayList<SubCategory>());
			} else {
				rowCount = subCategoryService.count(sort, search, false);
				subCategories = subCategoryService.findByName(limit, offset,
						sort, order, search, false);
			}
		}*/
		
		Long rowCount = null;

		subCategories = subCategoryService.searchAll(limit, offset, sort, order, search, false);
		rowCount = subCategoryService.countAll(sort, search, false);

		
		System.out.println("getTotalElements()  "+subCategories.getTotalElements());
		
		
		sb.append("{\"total\":").append(rowCount).append(",\"rows\": [");

		for (SubCategory subCategory : subCategories) {
			if(opt.equals("1")){
			
			sb.append("{\"id\":\"")
					.append(subCategory.getId())

					.append("\",\"categName\":\"")
					.append((subCategory.getCategory() != null ? subCategory.getCategory().getName() : ""))

					.append("\",\"name\":\"")
					.append(subCategory.getName())
					.append("\",\"sCategCode\":\"")
					.append((subCategory.getsCategCode() == null ? ""
							: subCategory.getsCategCode()))
					.append("\",\"updatedBy\":\"")
					.append((subCategory.getModiBy() == null ? "" : subCategory
							.getModiBy()))
					.append("\",\"updatedDt\":\"")
					.append((subCategory.getModiDt() == null ? "" : subCategory
							.getModiDt()))
					.append("\",\"deactive\":\"")
					.append((subCategory.getDeactive() == null ? ""
							: (subCategory.getDeactive() ? "Deactive"
									: "Active")))
					.append("\",\"deactiveDt\":\"")
					.append((subCategory.getDeactiveDt() == null ? ""
							: subCategory.getDeactiveDt()));
			
			if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
					userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
				
				sb.append("\",\"action1\":\"");
					sb.append(
							"<a href='/jewels/manufacturing/masters/subCategory/edit/")
							.append(subCategory.getId()).append(".html'")
							.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

				sb.append("\",\"action2\":\"");
					sb.append(
							"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/subCategory/delete/")
							.append(subCategory.getId()).append(".html'")
				
				.append(" class='btn btn-xs btn-danger triggerRemove")
						.append(subCategory.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
						.append("\"},");
			}else{
				sb.append("\",\"action1\":\"");
				if (roleRights.getCanEdit()) {
					sb.append(
							"<a href='/jewels/manufacturing/masters/subCategory/edit/")
							.append(subCategory.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {
					sb.append(
							"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/subCategory/delete/")
							.append(subCategory.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-danger triggerRemove")
						.append(subCategory.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
						.append("\"},");
				
			}
		

			
			}
			else if(opt.equals("3")){
				sb.append("{\"id\":\"")
				.append(subCategory.getId())
				.append("\",\"categName\":\"")
				.append((subCategory.getCategory() != null ? subCategory.getCategory().getName() : ""))
				.append("\",\"name\":\"")
				.append(subCategory.getName())
				.append("\"},");
			}

		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}
	
	
	
	
	
	
	
	@RequestMapping("/subCategory/report/listing")
	@ResponseBody
	public String subCategoryReportListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "catId", required = false) String catId,
			Principal principal) {
	
		
		String str = "";
				
		if(catId.equalsIgnoreCase("0")){
			
			StringBuilder sb = new StringBuilder();
			List<SubCategory> subCategList = subCategoryService.findByDeactive(false);
			
			sb.append("{\"total\":").append(subCategoryService.count()).append(",\"rows\": [");
			
			for(SubCategory subCategory : subCategList){
				
				sb.append("{\"id\":\"")
				.append(subCategory.getId())
				.append("\",\"name\":\"")
				.append(subCategory.getName() != null ? subCategory.getName() : "")
				.append("\"},");
				
			}
			
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
			str += "]}";

		}else{
			str = subCategoryService.getSubCategoryReportListDropDown(catId);
		}
	
		
		return str;	
	}
	
	
	
	

	@RequestMapping("/subCategory/add")
	public String add(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("subCategory");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("categoryList", categoryService.getCategoryList());
			
			return "subCategory/add";

		}else	
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			
		}
		
		model.addAttribute("categoryList", categoryService.getCategoryList());

		return "subCategory/add";
	}

	@RequestMapping(value = "/subCategory/add", method = RequestMethod.POST)
	public String addEditCategory(
			@Valid @ModelAttribute("subCategory") SubCategory subCategory,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "redirect:/manufacturing/masters/subCategory/add.html";

		if (result.hasErrors()) {
			return "subCategory/add";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			subCategory.setCreatedBy(principal.getName());
			subCategory.setCreateDate(new java.util.Date());
			subCategory.setDeactive(false);
		} else {
			subCategory.setModiBy(principal.getName());
			subCategory.setModiDt(new java.util.Date());

			if (subCategory.getDeactive() != subCategoryService.findOne(id).getDeactive()) {
				subCategory.setDeactiveDt(new java.util.Date());
			} else {
				subCategory.setDeactiveDt(subCategoryService.findOne(id).getDeactiveDt());
			}
			subCategory.setId(id);

			action = "updated";
			retVal = "redirect:/manufacturing/masters/subCategory.html";

		}
		subCategoryService.save(subCategory);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;
	}

	@RequestMapping("/subCategory/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		SubCategory subCategory = subCategoryService.findOne(id);
		model.addAttribute("subCategory", subCategory);
		model.addAttribute("categoryList", categoryService.getCategoryList());

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("subCategory");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "subCategory/add";

		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}

		return "subCategory/add";
	}

	@RequestMapping("/subCategory/delete/{id}")
	public String delete(@PathVariable int id) {
		subCategoryService.delete(id);

		return "redirect:/manufacturing/masters/subCategory.html";
	}

	@RequestMapping("/subCategoryAvailable")
	@ResponseBody
	public String userAvailable(@RequestParam String name,
			@RequestParam Integer id) {
		Boolean subCategoryAvailable = true;

		name = name.trim();

		if (id == null) {
			subCategoryAvailable = (subCategoryService.findByName(name) == null);
		} else {
			SubCategory subCategory = subCategoryService.findOne(id);
			if (!(name.equalsIgnoreCase(subCategory.getName()))) {
				subCategoryAvailable = (subCategoryService.findByName(name) == null);
			}
		}

		return subCategoryAvailable.toString();
	}

}
