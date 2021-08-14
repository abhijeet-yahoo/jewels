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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ProdLabourType;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ICategoryService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IProdLabourTypeService;

@RequestMapping("/manufacturing/masters")
@Controller
public class ProdLabourTypeController {

	@Autowired
	private IProdLabourTypeService prodLabourTypeService;

	@Autowired
	private IDepartmentService departmentService;

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

	@ModelAttribute("prodLabourType")
	public ProdLabourType construct() {
		return new ProdLabourType();
	}

	@RequestMapping("/prodLabourType")
	public String users(Model model,Principal principal) {
		model = populateModel(model);
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("prodLabourType");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
	
			return "prodLabourType";
		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}
		return "prodLabourType";
	}

	@RequestMapping("/prodLabourType/listing")
	@ResponseBody
	public String prodLabourTypeListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "deptId", required = false) Integer deptId,
			Principal principal) {
		
		//System.out.println("\n\nprinting the category and department id=()()()()()=>>>CategoryId=="+id+"departmentId=="+deptId+"\n\n");

		StringBuilder sb = new StringBuilder();
		Page<ProdLabourType> prodLabourTypes = null;

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("prodLabourType");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

		Long rowCount = null;

		/*if (search == null) {
			if (id == null || deptId == null) {
				rowCount = prodLabourTypeService.count(sort, search, false);
				//prodLabourTypes = prodLabourTypeService.findByCategoryAndDepartment(categoryService.findOne(id),departmentService.findOne(deptId), limit, offset, sort, order, search);
				prodLabourTypes = prodLabourTypeService.findAll(limit, offset,sort, order, search);
				//prodLabourTypes = prodLabourTypeService.findByName("Abcd");
				
				System.out.println("Do nothing");
			} else {
				rowCount = prodLabourTypeService.count(sort, search, false);
				   prodLabourTypes = prodLabourTypeService.findByCategoryAndDepartment(categoryService.findOne(id), 
						   departmentService.findOne(deptId), limit, offset, sort, order, search);
			}
		} else {

			if (search != null && sort != null && sort.equalsIgnoreCase("name")) {
				rowCount = prodLabourTypeService.count(sort, search, false);
				prodLabourTypes = prodLabourTypeService.findByName(limit,offset, sort, order, search, false);
			} else if (search != null && sort != null) {
				rowCount = 0L;
				prodLabourTypes = new PageImpl<ProdLabourType>(
						new ArrayList<ProdLabourType>());
			} else {
				rowCount = prodLabourTypeService.count(sort, search, false);
				prodLabourTypes = prodLabourTypeService.findByName(limit,
						offset, sort, order, search, false);
			}
		}
*/
	
		prodLabourTypes = prodLabourTypeService.searchAll(limit, offset, sort, order, search, false);
		rowCount = prodLabourTypeService.countAll(sort, search, false);
		
		sb.append("{\"total\":").append(rowCount).append(",\"rows\": [");

		for (ProdLabourType prodLabourType : prodLabourTypes) {
			sb.append("{\"id\":\"")
					.append(prodLabourType.getId())
					.append("\",\"department\":\"")
					.append(prodLabourType.getDepartment().getName())
					.append("\",\"categName\":\"")
					.append((prodLabourType.getCategory() != null ? prodLabourType.getCategory().getName() : ""))
					.append("\",\"name\":\"")
					.append(prodLabourType.getName())
					.append("\",\"updatedBy\":\"")
					.append((prodLabourType.getModiBy() != null ? prodLabourType.getModiBy() : ""))
					.append("\",\"prodLabTypeDefault\":\"")
					.append((prodLabourType.getProdLabTypeDefault() == null ? "" : prodLabourType.getProdLabTypeDefault() ? "Yes" : "No"))
					.append("\",\"deactive\":\"")
					.append((prodLabourType.getDeactive() == null ? "": (prodLabourType.getDeactive() ? "Deactive": "Active")));
			
			if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
					userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
				
				sb.append("\",\"action1\":\"")
							.append("<a href='/jewels/manufacturing/masters/prodLabourType/edit/")
							.append(prodLabourType.getId()).append(".html'")
							.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
				sb.append("\",\"action2\":\"")
						.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/prodLabourType/delete/")
						.append(prodLabourType.getId()).append(".html'")
						.append(" class='btn btn-xs btn-danger triggerRemove")
						.append(prodLabourType.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
						.append("\"},");
			}else{
				
				sb.append("\",\"action1\":\"");
				if (roleRights.getCanEdit()) {
					sb.append(
							"<a href='/jewels/manufacturing/masters/prodLabourType/edit/")
							.append(prodLabourType.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {
					sb.append(
							"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/prodLabourType/delete/")
							.append(prodLabourType.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-danger triggerRemove")
						.append(prodLabourType.getId())
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
	
	@RequestMapping("/prodLabourType/custom/listing")
	@ResponseBody
	public String addCustomSearch(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "deptartmentName") String deptartmentName,
			@RequestParam(value = "categoryNm") String categoryNm,
			@RequestParam(value = "prodLobourName") String prodLobourName,
			Principal principal){
		
		StringBuilder sb = new StringBuilder();
		Page<ProdLabourType> prodLabourTypes = null;
		Long rowCount = null;
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("prodLabourType");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		rowCount = prodLabourTypeService.customSearchCount(deptartmentName, categoryNm, prodLobourName);
		prodLabourTypes = prodLabourTypeService.customSearch(limit, offset, sort, order, deptartmentName, categoryNm, prodLobourName);
		
		sb.append("{\"total\":").append(rowCount).append(",\"rows\": [");

		for (ProdLabourType prodLabourType : prodLabourTypes) {
			sb.append("{\"id\":\"")
					.append(prodLabourType.getId())
					.append("\",\"department\":\"")
					.append(prodLabourType.getDepartment().getName())
					.append("\",\"categName\":\"")
					.append((prodLabourType.getCategory() != null ? prodLabourType.getCategory().getName() : ""))
					.append("\",\"name\":\"")
					.append(prodLabourType.getName())
					.append("\",\"updatedBy\":\"")
					.append((prodLabourType.getModiBy() != null ? prodLabourType.getModiBy() : ""))
					.append("\",\"prodLabTypeDefault\":\"")
					.append((prodLabourType.getProdLabTypeDefault() == null ? "" : prodLabourType.getProdLabTypeDefault() ? "Yes" : "No"))
					.append("\",\"deactive\":\"")
					.append((prodLabourType.getDeactive() == null ? "": (prodLabourType.getDeactive() ? "Deactive": "Active")));

			if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
					userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
				
				sb.append("\",\"action1\":\"")
							.append("<a href='/jewels/manufacturing/masters/prodLabourType/edit/")
							.append(prodLabourType.getId()).append(".html'")
							.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
				sb.append("\",\"action2\":\"")
						.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/prodLabourType/delete/")
						.append(prodLabourType.getId()).append(".html'")
						.append(" class='btn btn-xs btn-danger triggerRemove")
						.append(prodLabourType.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
						.append("\"},");
			}else{
				
				sb.append("\",\"action1\":\"");
				if (roleRights.getCanEdit()) {
					sb.append(
							"<a href='/jewels/manufacturing/masters/prodLabourType/edit/")
							.append(prodLabourType.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {
					sb.append(
							"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/prodLabourType/delete/")
							.append(prodLabourType.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-danger triggerRemove")
						.append(prodLabourType.getId())
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

	
	@RequestMapping("/prodLabourType/add")
	public String add(Model model,Principal principal) {
		model = populateModel(model);
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("prodLabourType");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "prodLabourType/add";

		}else	
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}

		
		return "prodLabourType/add";
	}
	
	public Model populateModel(Model model) {
		model.addAttribute("departmentMap",departmentService.getDepartmentList());
		model.addAttribute("categoryMap", categoryService.getCategoryList());
		return model;

	}
	
	@RequestMapping(value = "/prodLabourType/add", method = RequestMethod.POST)
	public String addEditProductLabourType(@Valid @ModelAttribute("prodLabourType") ProdLabourType prodLabourType,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "redirect:/manufacturing/masters/prodLabourType/add.html";

		if (result.hasErrors()) {
			return "prodLabourType/add";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			prodLabourType.setCreatedBy(principal.getName());
			prodLabourType.setCreatedDt(new java.util.Date());
			retVal = "redirect:/manufacturing/masters/prodLabourType.html?categId="+ prodLabourType.getCategory().getId()+"_"+ prodLabourType
					.getDepartment().getId();
		} else {
			prodLabourType.setId(id);
			prodLabourType.setModiBy(principal.getName());
			prodLabourType.setModiDt(new java.util.Date());
			action = "updated";
			retVal = "redirect:/manufacturing/masters/prodLabourType.html?categId="+ prodLabourType.getCategory().getId()+"_"+ prodLabourType
					.getDepartment().getId();
		}

		prodLabourTypeService.save(prodLabourType);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;

	}

	@RequestMapping("/prodLabourType/edit/{id}")
	public String edit(@PathVariable int id, Model model,Principal principal) {
		ProdLabourType prodLabourType = prodLabourTypeService.findOne(id);
		model = populateModel(model);
		model.addAttribute("prodLabourType", prodLabourType);
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("prodLabourType");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "prodLabourType/add";

		}else	
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}
		
		return "prodLabourType/add";
	}

	@RequestMapping("/prodLabourType/delete/{id}")
	public String delete(@PathVariable int id) {
		prodLabourTypeService.delete(id);

		return "redirect:/manufacturing/masters/prodLabourType.html";
	}
	
	
}
