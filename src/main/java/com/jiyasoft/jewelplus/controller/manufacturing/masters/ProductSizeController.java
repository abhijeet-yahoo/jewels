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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ProductSize;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ICategoryService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IProductSizeService;

@RequestMapping("/manufacturing/masters")
@Controller
public class ProductSizeController {

	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private IProductSizeService productSizeService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private UserRoleService userRoleService;

	@ModelAttribute("productSize")
	public ProductSize construct() {
		return new ProductSize();
	}

	@RequestMapping("/productSize")
	public String users(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("productSize");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			
			
			return "productSize";
		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}

		return "productSize";
	}

	@RequestMapping("/productSize/listing")
	@ResponseBody
	public String productSizeListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "id", required = false) Integer id,
			Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<ProductSize> productSizes = null;

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("productSize");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

		/*Long rowCount = null;

		if (search == null) {
			if (id == null) {
				rowCount = productSizeService.count(sort, search, false);
				productSizes = productSizeService.findAll(limit, offset, sort,
						order, search);
			} else {
				rowCount = productSizeService.count(sort, search, false);
				productSizes = productSizeService.findByCategory(
						categoryService.findOne(id), limit, offset, sort,
						order, search);
			}
		} else {

			if (search != null && sort != null && sort.equalsIgnoreCase("name")) {
				rowCount = productSizeService.count(sort, search, false);
				productSizes = productSizeService.findByName(limit, offset,
						sort, order, search, false);
			} else if (search != null && sort != null) {
				rowCount = 0L;
				productSizes = new PageImpl<ProductSize>(
						new ArrayList<ProductSize>());
			} else {
				rowCount = productSizeService.count(sort, search, false);
				productSizes = productSizeService.findByName(limit, offset,
						sort, order, search, false);
			}
		}*/
		
		Long rowCount = null;

		productSizes = productSizeService.searchAll(limit, offset, sort, order, search, false);
		rowCount = productSizeService.countAll(sort, search, false);

		sb.append("{\"total\":").append(rowCount).append(",\"rows\": [");

		for (ProductSize productSize : productSizes) {
			sb.append("{\"id\":\"")
					.append(productSize.getId())
					.append("\",\"categName\":\"")
					.append((productSize.getCategory() != null ? productSize.getCategory().getName() : ""))
					.append("\",\"name\":\"")
					.append(productSize.getName())
					.append("\",\"updatedBy\":\"")
					.append((productSize.getModiBy() == null ? "" : productSize
							.getModiBy()))
					.append("\",\"updatedDt\":\"")
					.append((productSize.getModiDt() == null ? "" : productSize
							.getModiDt()))
					.append("\",\"deactive\":\"")
					.append((productSize.getDeactive() == null ? ""
							: (productSize.getDeactive() ? "Deactive"
									: "Active")))
					.append("\",\"deactiveDt\":\"")
					.append((productSize.getDeactiveDt() == null ? ""
							: productSize.getDeactiveDt()));
			
			if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
					userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
				
				sb.append("\",\"action1\":\"");
					sb.append(
							"<a href='/jewels/manufacturing/masters/productSize/edit/")
							.append(productSize.getId()).append(".html'")
							.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

				sb.append("\",\"action2\":\"");
					sb.append(
							"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/productSize/delete/")
							.append(productSize.getId()).append(".html'")
							.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(productSize.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
							.append("\"},");

			}else{
				sb.append("\",\"action1\":\"");
				if (roleRights.getCanEdit()) {
					sb.append(
							"<a href='/jewels/manufacturing/masters/productSize/edit/")
							.append(productSize.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

				sb.append("\",\"action2\":\"");
				if (roleRights.getCanEdit()) {
					sb.append(
							"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/productSize/delete/")
							.append(productSize.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-danger triggerRemove")
						.append(productSize.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
						.append("\"},");

			}
			

			
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}

	@RequestMapping("/productSize/add")
	public String add(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("productSize");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("categoryList", categoryService.getCategoryList());
			
			return "productSize/add";

		}else	
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("categoryList",categoryService.getCategoryList());
		}

		return "productSize/add";
	}

	@RequestMapping(value = "/productSize/add", method = RequestMethod.POST)
	public String addEditCategory(
			@Valid @ModelAttribute("productSize") ProductSize productSize,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "redirect:/manufacturing/masters/productSize/add.html";

		if (result.hasErrors()) {
			return "productSize/add";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			productSize.setCreatedBy(principal.getName());
			productSize.setCreateDate(new java.util.Date());
			productSize.setDeactive(false);
		} else {
			productSize.setModiBy(principal.getName());
			productSize.setModiDt(new java.util.Date());

			if (productSize.getDeactive() != productSizeService.findOne(id)
					.getDeactive()) {
				productSize.setDeactiveDt(new java.util.Date());
			} else {
				productSize.setDeactiveDt(productSizeService.findOne(id)
						.getDeactiveDt());
			}
			productSize.setId(id);

			action = "updated";
			retVal = "redirect:/manufacturing/masters/productSize.html";
		}
		productSizeService.save(productSize);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;
	}

	@RequestMapping("/productSize/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		ProductSize productSize = productSizeService.findOne(id);
		model.addAttribute("productSize", productSize);
		model.addAttribute("categoryList", categoryService.getCategoryList());

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("productSize");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("categoryList", categoryService.getCategoryList());
			
			return "productSize/add";

		}else	
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}

		return "productSize/add";
	}

	@RequestMapping("/productSize/delete/{id}")
	public String delete(@PathVariable int id) {
		productSizeService.delete(id);

		return "redirect:/manufacturing/masters/productSize.html";
	}

	@RequestMapping("/productSizeAvailable")
	@ResponseBody
	public String userAvailable(@RequestParam String name,
			@RequestParam Integer id) {
		Boolean productSizeAvailable = true;

		name = name.trim();

		if (id == null) {
			productSizeAvailable = (productSizeService.findByName(name) == null);
		} else {
			ProductSize productSize = productSizeService.findOne(id);
			if (!(name.equalsIgnoreCase(productSize.getName()))) {
				productSizeAvailable = (productSizeService.findByName(name) == null);
			}
		}

		return productSizeAvailable.toString();
	}

}
