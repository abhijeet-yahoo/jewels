package com.jiyasoft.jewelplus.controller.manufacturing.masters;

import java.security.Principal;
import java.util.Map;

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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;

@RequestMapping("/manufacturing/masters")
@Controller
public class ShapeController {

	@Autowired
	private IShapeService shapeService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private UserRoleService userRoleService;

	@ModelAttribute("shape")
	public Shape construct() {
		return new Shape();
	}

	@RequestMapping("/shape")
	public String users(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("shape");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
	
			return "shape";
		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}

		return "shape";
	}

	@RequestMapping("/shape/listing")
	@ResponseBody
	public String shapeListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "opt", required = true) String opt,
			Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<Shape> shapes = null;

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("shape");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;	
		}

/*		Long rowCount = null;

		// default search would be name wise
		if (search == null && sort == null) {
			rowCount = shapeService.count(sort, search, false);
			categories = shapeService.findByName(limit, offset, sort, order, search, false);
		} else if (search != null && sort != null && sort.equalsIgnoreCase("name")) {
			rowCount = shapeService.count(sort, search, false);
			categories = shapeService.findByName(limit, offset, sort, order, search, false);
		} else if (search != null && sort != null) {
			rowCount = 0L;
			categories = new PageImpl<Shape>(new ArrayList<Shape>());
		} else {
			rowCount = shapeService.count(sort, search, false);
			categories = shapeService.findByName(limit, offset, sort, order, search, false);
		}*/
		
		shapes = shapeService.searchAll(limit, offset, sort, order, search, false);

		sb.append("{\"total\":").append(shapes.getTotalElements()).append(",\"rows\": [");
		
		if(opt.equals("3")){
			
			for (Shape shape : shapes) {
				
					sb.append("{\"id\":\"")
						.append(shape.getId())
						.append("\",\"name\":\"")
						.append(shape.getName())
						.append("\",\"code\":\"")
						.append((shape.getCode() == null ? "" : shape.getCode()))
						.append("\"},");
		}
		}else{
		
		
		for (Shape shape : shapes) {
			if (opt.equals("1")) {
				sb.append("{\"id\":\"")
					.append(shape.getId())
					.append("\",\"name\":\"")
					.append(shape.getName())
					.append("\",\"code\":\"")
					.append((shape.getCode() == null ? "" : shape.getCode()))
					.append("\",\"updatedBy\":\"")
					.append((shape.getModiBy() == null ? "" : shape.getModiBy()))
					.append("\",\"updatedDt\":\"")
					.append((shape.getModiDt() == null ? "" : shape.getModiDt()))
					.append("\",\"deactive\":\"")
					.append((shape.getDeactive() == null ? "" : (shape.getDeactive() ? "Deactive" : "Active")))
					.append("\",\"deactiveDt\":\"")
					.append((shape.getDeactiveDt() == null ? "" : shape.getDeactiveDt()))
					.append("\",\"upTolerance\":\"")
					.append((shape.getUpperTolerance()>0.0 ? shape.getUpperTolerance() :""))
					.append("\",\"lowTolerance\":\"")
					.append((shape.getLowerTolerance()>0.0 ? shape.getLowerTolerance() :""));
				
				if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
						userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
				

					sb.append("\",\"action1\":\"")
							.append("<a href='/jewels/manufacturing/masters/shape/edit/").append(shape.getId()).append(".html'")
							.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

					sb.append("\",\"action2\":\"")
							.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/shape/delete/")
							.append(shape.getId()).append(".html'")
							.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(shape.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
							.append("\"},");
				
				}else{
					sb.append("\",\"action1\":\"");
					if (roleRights.getCanEdit()) {
						sb.append("<a href='/jewels/manufacturing/masters/shape/edit/").append(shape.getId()).append(
							".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

					sb.append("\",\"action2\":\"");
					if (roleRights.getCanDelete()) {
						sb.append(
								"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/shape/delete/")
								.append(shape.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(shape.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
							.append("\"},");
				
				}
					
			} else if (opt.equals("2")) {
				sb.append("{\"name\":\"")
						.append("<a href='javascript:getChild(")
						.append(shape.getId()).append(")'>")
						.append(shape.getName()).append("</a>\"},");
			}
		}
			}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";

		return str;
	}

	@RequestMapping("/shape/add")
	public String add(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("shape");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "shape/add";

		}else	
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}

		return "shape/add";
	}

	@RequestMapping(value = "/shape/add", method = RequestMethod.POST)
	public String addEditShape(
			@Valid @ModelAttribute("shape") Shape shape,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "redirect:/manufacturing/masters/shape/add.html";

		if (result.hasErrors()) {
			return "shape/add";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			shape.setCreatedBy(principal.getName());
			shape.setCreateDate(new java.util.Date());
			shape.setDeactive(false);
		} else {
			shape.setModiBy(principal.getName());
			shape.setModiDt(new java.util.Date());

			if (shape.getDeactive() != shapeService.findOne(id).getDeactive()) {
				shape.setDeactiveDt(new java.util.Date());
			} else {
				shape.setDeactiveDt(shapeService.findOne(id).getDeactiveDt());
			}
			shape.setId(id);

			action = "updated";
			retVal = "redirect:/manufacturing/masters/shape.html";
		}

		shapeService.save(shape);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;
	}
	
	
	@RequestMapping("/shape/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		Shape shape = shapeService.findOne(id);
		model.addAttribute("shape", shape);

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("shape");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "shape/add";

		}else	
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}

		return "shape/add";
	}

	@RequestMapping("/shape/delete/{id}")
	public String delete(@PathVariable int id) {
		shapeService.delete(id);

		return "redirect:/manufacturing/masters/shape.html";
	}

	@RequestMapping("/shapeAvailable")
	@ResponseBody
	public String userAvailable(@RequestParam String name,
			@RequestParam Integer id) {
		Boolean shapeAvailable = true;

		name = name.trim();

		if (id == null) {
			shapeAvailable = (shapeService.findByName(name) == null);
		} else {
			Shape shape = shapeService.findOne(id);
			if (!(name.equalsIgnoreCase(shape.getName()))) {
				shapeAvailable = (shapeService.findByName(name) == null);
			}
		}

		return shapeAvailable.toString();
	}
	
	
	
	
	@RequestMapping("/shape/dropDownlist")
	@ResponseBody
	public String shapeList() {

		StringBuilder sb = new StringBuilder();
		Map<Integer, String> shapeMap = shapeService.getShapeList();

		sb.append("<select id=\"shapeDropDownId\" name=\"shapeDropDownId\" class=\"form-control\" onChange=\"javascript:popQualityDropDown(this)\">");
		sb.append("<option value=\"\"> Select Shape </option>");
		for (Object key : shapeMap.keySet()) {
			sb.append("<option value=\"").append(key.toString()).append("\">")
					.append(shapeMap.get(key)).append("</option>");
		}
		sb.append("</select>");

		return sb.toString();
	}
}
