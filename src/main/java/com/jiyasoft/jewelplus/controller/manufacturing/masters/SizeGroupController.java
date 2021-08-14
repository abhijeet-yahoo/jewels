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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneChart;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISizeGroupService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneChartService;

@RequestMapping("/manufacturing/masters")
@Controller
public class SizeGroupController {
	
	@Autowired
	private IShapeService shapeService;

	@Autowired
	private ISizeGroupService sizeGroupService;
	
	@Autowired
	private IStoneChartService stoneChartService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private UserRoleService userRoleService;

	@ModelAttribute("sizeGroup")
	public SizeGroup construct() {
		return new SizeGroup();
	}

	@RequestMapping("/sizeGroup")
	public String users(Model model, Principal principal) {

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("sizeGroup");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
	
			return "sizeGroup";
		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}
		
		return "sizeGroup";

	}

	@RequestMapping("/sizeGroup/listing")
	@ResponseBody
	public String sizeGroupListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "shapeId", required = false) Integer shapeId,
			@RequestParam(value = "opt", required = false) String opt,
			
			Principal principal) {

		
		StringBuilder sb = new StringBuilder();
		Page<SizeGroup> sizeGroups = null;

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("sizeGroup");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

		/*Long rowCount = null;*/

		
		/*rowCount = sizeGroupService.countAll(sort, search, false);*/
		
		/*if (search == null) {
			if (id == null) {
				rowCount = sizeGroupService.count(sort, search, false);
				sizeGroups = sizeGroupService.findAll(limit, offset, sort,order, search);
			} else {
				rowCount = sizeGroupService.count(sort, search, false);
				sizeGroups = sizeGroupService.findByShape(shapeService.findOne(id), limit, offset, sort,order, search);
			}
		} else {

			if (search != null && sort != null && sort.equalsIgnoreCase("name")) {
				rowCount = sizeGroupService.count(sort, search, false);
				sizeGroups = sizeGroupService.findByName(limit, offset,sort, order, search, false);
			} else if (search != null && sort != null) {
				rowCount = 0L;
				sizeGroups = new PageImpl<SizeGroup>(new ArrayList<SizeGroup>());
			} else {
				rowCount = sizeGroupService.count(sort, search, false);
				sizeGroups = sizeGroupService.findByName(limit, offset, sort, order, search, false);
			}
		}
*/		
		
		sizeGroups = sizeGroupService.searchAll(limit, offset, sort, order, search,shapeId, false);
		
		sb.append("{\"total\":").append(sizeGroups.getTotalElements()).append(",\"rows\": [");

		for (SizeGroup sizeGroup : sizeGroups) {
			if(opt.equals("1")){
			sb.append("{\"id\":\"")
					.append(sizeGroup.getId())
					.append("\",\"shapeName\":\"")
					.append((sizeGroup.getShape() != null ? sizeGroup.getShape().getName() : ""))
					.append("\",\"name\":\"")
					.append(sizeGroup.getName())
					.append("\",\"fromSize\":\"")
					.append(sizeGroup.getFromSize())
					.append("\",\"toSize\":\"")
					.append(sizeGroup.getToSize())
					.append("\",\"updatedBy\":\"")
					.append((sizeGroup.getModiBy() == null ? "" : sizeGroup
							.getModiBy()))
					.append("\",\"updatedDt\":\"")
					.append((sizeGroup.getModiDt() == null ? "" : sizeGroup
							.getModiDt()))
					.append("\",\"deactive\":\"")
					.append((sizeGroup.getDeactive() == null ? ""
							: (sizeGroup.getDeactive() ? "Deactive" : "Active")))
					.append("\",\"deactiveDt\":\"")
					.append((sizeGroup.getDeactiveDt() == null ? ""
							: sizeGroup.getDeactiveDt()));
			
			if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
					userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
					sb.append("\",\"action1\":\"")
								.append("<a href='/jewels/manufacturing/masters/sizeGroup/edit/")
								.append(sizeGroup.getId()).append(".html'")
								.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
		
					sb.append("\",\"action2\":\"")
							.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/sizeGroup/delete/")
							.append(sizeGroup.getId()).append(".html'")
							.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(sizeGroup.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
							.append("\"},");
					
			}else{
				sb.append("\",\"action1\":\"");
				if (roleRights.getCanEdit()) {
					sb.append("<a href='/jewels/manufacturing/masters/sizeGroup/edit/")
							.append(sizeGroup.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {
					sb.append(
							"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/sizeGroup/delete/")
							.append(sizeGroup.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-danger triggerRemove")
						.append(sizeGroup.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
						.append("\"},");
				
			}
					
			}else if(opt.equals("3")){
				sb.append("{\"id\":\"")
				.append(sizeGroup.getId())
				.append("\",\"shapeName\":\"")
				.append((sizeGroup.getShape() != null ? sizeGroup.getShape().getName() : ""))
				.append("\",\"name\":\"")
				.append(sizeGroup.getName())
				.append("\"},");
				
			}

			

		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}

	@RequestMapping("/sizeGroup/add")
	public String add(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("sizeGroup");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		model.addAttribute("shapeList", shapeService.getShapeList());
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "sizeGroup/add";

		}else {
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			
		}
	}
		

		return "sizeGroup/add";
	}

	@ResponseBody
	@RequestMapping(value = "/sizeGroup/add", method = RequestMethod.POST)
	public String addEditSizeGroup(
			@Valid @ModelAttribute("sizeGroup") SizeGroup sizeGroup,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "/jewels/manufacturing/masters/sizeGroup/add.html";

		if (result.hasErrors()) {
			return "sizeGroup/add";
		}
		
		

		if (id == null || id.equals("") || (id != null && id == 0)) {
			
			SizeGroup sizeGroupAdd = sizeGroupService.findByShapeAndNameAndDeactive(sizeGroup.getShape(), sizeGroup.getName(), false);
			if(sizeGroupAdd != null){
				return retVal = "-1";
			}
			
			sizeGroup.setCreatedBy(principal.getName());
			sizeGroup.setCreateDate(new java.util.Date());
			sizeGroup.setDeactive(false);
			sizeGroup.setName(sizeGroup.getName().trim());
		} else {
			
			sizeGroup.setName(sizeGroup.getName().trim());
			
			SizeGroup sizeGroupEdit = sizeGroupService.findByShapeAndNameAndDeactive(sizeGroup.getShape(), sizeGroup.getName(), false);
			
			if(sizeGroupEdit != null){
				if(!(sizeGroupEdit.getId().equals(sizeGroup.getId()))){
					return retVal = "-1";
				}
			}
			
			sizeGroup.setModiBy(principal.getName());
			sizeGroup.setModiDt(new java.util.Date());

			if (sizeGroup.getDeactive() != sizeGroupService.findOne(id).getDeactive()) {
				sizeGroup.setDeactiveDt(new java.util.Date());
			} else {
				sizeGroup.setDeactiveDt(sizeGroupService.findOne(id).getDeactiveDt());
			}
			sizeGroup.setId(id);
			
			action = "updated";
			retVal = "/jewels/manufacturing/masters/sizeGroup.html?id="+sizeGroup.getShape().getId();
		}
		
		sizeGroupService.save(sizeGroup);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;
	}

	@RequestMapping("/sizeGroup/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		SizeGroup sizeGroup = sizeGroupService.findOne(id);
		model.addAttribute("sizeGroup", sizeGroup);
		model.addAttribute("shapeList", shapeService.getShapeList());

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("sizeGroup");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "sizeGroup/add";

		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}

		return "sizeGroup/add";
	}

	@RequestMapping("/sizeGroup/delete/{id}")
	public String delete(@PathVariable int id,RedirectAttributes redirectAttributes) {
		
		SizeGroup sizeGroup=sizeGroupService.findOne(id);
		
		List<StoneChart>stoneCharts =stoneChartService.findBySizeGroupAndDeactive(sizeGroup, false);
		
		if(stoneCharts.size()>0){
			redirectAttributes.addFlashAttribute("success", false);	
			redirectAttributes.addFlashAttribute("action", "Can Not Delete, Used In StoneChart ");
		}else{
			sizeGroupService.delete(id);
		}
		

		return "redirect:/manufacturing/masters/sizeGroup.html";
	}

	@RequestMapping("/sizeGroupAvailable")
	@ResponseBody
	public String userAvailable(@RequestParam String name,
			@RequestParam Integer id) {
		Boolean sizeGroupAvailable = true;

		name = name.trim();

		if (id == null) {
			sizeGroupAvailable = (sizeGroupService.findByName(name) == null);
		} else {
			SizeGroup sizeGroup = sizeGroupService.findOne(id);
			if (!(name.equalsIgnoreCase(sizeGroup.getName()))) {
				sizeGroupAvailable = (sizeGroupService.findByName(name) == null);
			}
		}

		return sizeGroupAvailable.toString();
	}


	@ResponseBody
	@RequestMapping("/sizeGroup/combo")
	public String sizeGroupCombo(
			@RequestParam(value = "shapeId") int shapeId){
		String sizeGroup = sizeGroupService.getSizeGroupListDropDown(shapeId);
		return sizeGroup;
	}
	
}
