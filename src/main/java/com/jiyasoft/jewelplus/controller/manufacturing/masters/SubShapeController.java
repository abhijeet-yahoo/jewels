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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SubShape;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISubShapeService;

@RequestMapping("/manufacturing/masters")
@Controller
public class SubShapeController {

	@Autowired
	private IShapeService shapeService;

	@Autowired
	private ISubShapeService subShapeService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private UserRoleService userRoleService;

	@ModelAttribute("subShape")
	public SubShape construct() {
		return new SubShape();
	}

	@RequestMapping("/subShape")
	public String users(Model model, Principal principal) {

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("subShape");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
	
			return "subShape";
		}else
	
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}
		
		return "subShape";
	}

	@RequestMapping("/subShape/listing")
	@ResponseBody
	public String subShapeListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "id", required = false) Integer id,
			Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<SubShape> subShapes = null;

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("subShape");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

		Long rowCount = null;
		
		subShapes = subShapeService.searchAll(limit, offset, sort, order, search, false);
		rowCount = subShapeService.countAll(sort, search, false);


		/*if (search == null) {
			if (id == null) {
				rowCount = subShapeService.count(sort, search, false);
				subShapes = subShapeService.findAll(limit, offset, sort, order,search);
			} else {
				rowCount = subShapeService.count(sort, search, false);
				subShapes = subShapeService.findByShape(
						shapeService.findOne(id), limit, offset, sort, order,
						search);
			}
		} else {

			if (search != null && sort != null && sort.equalsIgnoreCase("name")) {
				rowCount = subShapeService.count(sort, search, false);
				subShapes = subShapeService.findByName(limit, offset, sort,
						order, search, false);
			} else if (search != null && sort != null) {
				rowCount = 0L;
				subShapes = new PageImpl<SubShape>(new ArrayList<SubShape>());
			} else {
				rowCount = subShapeService.count(sort, search, false);
				subShapes = subShapeService.findByName(limit, offset, sort,
						order, search, false);
			}
		}
*/
		sb.append("{\"total\":").append(rowCount).append(",\"rows\": [");

		for (SubShape subShape : subShapes) {
			sb.append("{\"id\":\"")
					.append(subShape.getId())
					.append("\",\"shapeName\":\"")
					.append((subShape.getShape() != null ? subShape.getShape()
							.getName() : ""))
					.append("\",\"shape\":\"")
					.append(subShape.getShape())
					.append("\",\"name\":\"")
					.append(subShape.getName())
					.append("\",\"code\":\"")
					.append(subShape.getCode()!=null ? subShape.getCode():"")
					.append("\",\"updatedBy\":\"")
					.append((subShape.getModiBy() == null ? "" : subShape
							.getModiBy()))
					.append("\",\"updatedDt\":\"")
					.append((subShape.getModiDt() == null ? "" : subShape
							.getModiDt()))
					.append("\",\"deactive\":\"")
					.append((subShape.getDeactive() == null ? "" : (subShape
							.getDeactive() ? "Deactive" : "Active")))
					.append("\",\"deactiveDt\":\"")
					.append((subShape.getDeactiveDt() == null ? "" : subShape
							.getDeactiveDt()));
			
			if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
					userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
			

						sb.append("\",\"action1\":\"")
									.append("<a href='/jewels/manufacturing/masters/subShape/edit/")
									.append(subShape.getId()).append(".html'")
									.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
			
						sb.append("\",\"action2\":\"")
								.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/subShape/delete/")
								.append(subShape.getId()).append(".html'")
								.append(" class='btn btn-xs btn-danger triggerRemove")
								.append(subShape.getId())
								.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
								.append("\"},");
			}else{
				

				sb.append("\",\"action1\":\"");
				if (roleRights.getCanEdit()) {
					sb.append(
							"<a href='/jewels/manufacturing/masters/subShape/edit/")
							.append(subShape.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {
					sb.append(
							"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/subShape/delete/")
							.append(subShape.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-danger triggerRemove")
						.append(subShape.getId())
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

	@RequestMapping("/subShape/add")
	public String add(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("subShape");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("shapeList", shapeService.getShapeList());
			
			return "subShape/add";

		}else	
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}
		model.addAttribute("shapeList", shapeService.getShapeList());

		return "subShape/add";
	}

	@RequestMapping(value = "/subShape/add", method = RequestMethod.POST)
	public String addEditConcept(
			@Valid @ModelAttribute("subShape") SubShape subShape,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "redirect:/manufacturing/masters/subShape/add.html";

		if (result.hasErrors()) {
			return "subShape/add";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			subShape.setCreatedBy(principal.getName());
			subShape.setCreateDate(new java.util.Date());
			subShape.setDeactive(false);
		} else {
			subShape.setModiBy(principal.getName());
			subShape.setModiDt(new java.util.Date());

			if (subShape.getDeactive() != subShapeService.findOne(id)
					.getDeactive()) {
				subShape.setDeactiveDt(new java.util.Date());
			} else {
				subShape.setDeactiveDt(subShapeService.findOne(id)
						.getDeactiveDt());
			}
			subShape.setId(id);

			action = "updated";
			retVal = "redirect:/manufacturing/masters/subShape.html?id="+ subShape.getShape().getId();
		}
		subShapeService.save(subShape);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;
	}

	@RequestMapping("/subShape/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		SubShape subShape = subShapeService.findOne(id);
		model.addAttribute("subShape", subShape);
		model.addAttribute("shapeList", shapeService.getShapeList());

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("subShape");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("shapeList", shapeService.getShapeList());
			
			return "subShape/add";

		}else	
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}

		return "subShape/add";
	}

	@RequestMapping("/subShape/delete/{id}")
	public String delete(@PathVariable int id) {
		subShapeService.delete(id);

		return "redirect:/manufacturing/masters/subShape.html";
	}

	@RequestMapping("/subShapeAvailable")
	@ResponseBody
	public String userAvailable(@RequestParam String name,
			@RequestParam Integer id) {
		Boolean subShapeAvailable = true;

		name = name.trim();

		if (id == null) {
			subShapeAvailable = (subShapeService.findByName(name) == null);
		} else {
			SubShape subShape = subShapeService.findOne(id);
			if (!(name.equalsIgnoreCase(subShape.getName()))) {
				subShapeAvailable = (subShapeService.findByName(name) == null);
			}
		}

		return subShapeAvailable.toString();
	}

}
