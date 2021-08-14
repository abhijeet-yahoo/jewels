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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IQualityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;


@RequestMapping("/manufacturing/masters")
@Controller
public class QualityController {
	@Autowired
	private IShapeService shapeService;

	@Autowired
	private IQualityService qualityService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private UserRoleService userRoleService;

	@ModelAttribute("quality")
	public Quality construct() {
		return new Quality();
	}

	@RequestMapping("/quality")
	public String users(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("quality");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
	
			return "quality";
		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}

		return "quality";
	}

	@RequestMapping("/quality/listing")
	@ResponseBody
	public String qualityListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "shapeId", required = false) Integer shapeId,
			Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<Quality> qualities = null;

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("quality");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

		
		qualities = qualityService.searchAll(limit, offset, sort, order, search, shapeId, false);
	
		sb.append("{\"total\":").append(qualities.getTotalElements()).append(",\"rows\": [");

		
		for (Quality quality : qualities) {
			sb.append("{\"id\":\"")
					.append(quality.getId())
					.append("\",\"shapeName\":\"")
					.append((quality.getShape() != null ? quality.getShape().getName() : ""))
					.append("\",\"name\":\"")
					.append(quality.getName())
					.append("\",\"group\":\"")
					.append(quality.getQualityGroup() !=null?quality.getQualityGroup():"")
					.append("\",\"party\":\"")
					.append((quality.getParty() != null ? quality.getParty().getPartyCode() : ""))
					.append("\",\"code\":\"")
					.append((quality.getCode() == null ? "" : quality.getCode()))
					.append("\",\"intQuality\":\"")
					.append((quality.getIntQuality() == null ? "" : quality.getIntQuality()))
					.append("\",\"description\":\"")
					.append((quality.getDescription() == null ? "" : quality.getDescription()))
					.append("\",\"updatedBy\":\"")
					.append((quality.getModiBy() == null ? "" : quality.getModiBy()))
					.append("\",\"updatedDt\":\"")
					.append((quality.getModiDt() == null ? "" : quality.getModiDt()))
					.append("\",\"deactive\":\"")
					.append((quality.getDeactive() == null ? "" : (quality.getDeactive() ? "Deactive" : "Active")))
					.append("\",\"deactiveDt\":\"")
					.append((quality.getDeactiveDt() == null ? "" : quality.getDeactiveDt()));
			
			if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
					userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
			
		
					sb.append("\",\"action1\":\"")
								.append("<a href='/jewels/manufacturing/masters/quality/edit/")
								.append(quality.getId()).append(".html'")
								.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
		
					sb.append("\",\"action2\":\"")
					.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/quality/delete/")
							.append(quality.getId()).append(".html'")
							.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(quality.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
							.append("\"},");
			}else{

				sb.append("\",\"action1\":\"");
				if (roleRights.getCanEdit()) {
					sb.append(
							"<a href='/jewels/manufacturing/masters/quality/edit/")
							.append(quality.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {
					sb.append(
							"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/quality/delete/")
							.append(quality.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-danger triggerRemove")
						.append(quality.getId())
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
	

	@RequestMapping("/quality/add")
	public String add(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("quality");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			model.addAttribute("shapeList", shapeService.getShapeList());
			model.addAttribute("partyMap", partyService.getPartyList());
			
			return "quality/add";

		}else	

		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}
		model.addAttribute("shapeList", shapeService.getShapeList());
		model.addAttribute("partyMap", partyService.getPartyList());

		return "quality/add";
	}

	@RequestMapping(value = "/quality/add", method = RequestMethod.POST)
	public String addEditQuality(
			@Valid @ModelAttribute("quality") Quality quality,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "redirect:/manufacturing/masters/quality/add.html";

		if (result.hasErrors()) {
			return "quality/add";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			quality.setCreatedBy(principal.getName());
			quality.setCreateDate(new java.util.Date());
			
			
		} else {
			
			
			
			quality.setModiBy(principal.getName());
			quality.setModiDt(new java.util.Date());

			action = "updated";
			retVal = "redirect:/manufacturing/masters/quality.html?id="
					+ quality.getShape().getId();
		}
		
		if(quality.getParty().getId()== null){
			quality.setParty(null);
		}
		
		qualityService.save(quality);
		
		/*
		 * if(quality.getIntQuality() != null && quality.getIntQuality() != "" ){
		 * 
		 * List<Quality> qualityList =
		 * qualityService.findByIntQuality(quality.getIntQuality());
		 * 
		 * for(Quality quality2 : qualityList){
		 * 
		 * if(quality.getDescription() != null && quality.getDescription() != ""){
		 * quality2.setDescription(quality.getDescription());
		 * qualityService.save(quality2); }
		 * 
		 * }
		 * 
		 * }
		 */
		

		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;
	}

	@RequestMapping("/quality/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		Quality quality = qualityService.findOne(id);
		model.addAttribute("quality", quality);
		model.addAttribute("shapeList", shapeService.getShapeList());
		model.addAttribute("partyMap", partyService.getPartyList());

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("quality");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "quality/add";

		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}

		return "quality/add";
	}

	@RequestMapping("/quality/delete/{id}")
	public String delete(@PathVariable int id) {
		qualityService.delete(id);

		return "redirect:/manufacturing/masters/quality.html";
	}

	@RequestMapping("/qualityAvailable")
	@ResponseBody
	public String userAvailable(@RequestParam String name,
			@RequestParam Integer id,
			@RequestParam Integer shape) {
		Boolean qualityAvailable = true;

		name = name.trim();
		
		Shape shapeObj = shapeService.findOne(shape);

		if (id == null) {
			qualityAvailable = (qualityService.findByShapeAndName(shapeObj,name) == null);
		} else {
			Quality quality = qualityService.findOne(id);
			if (!(name.equalsIgnoreCase(quality.getName()))) {
				qualityAvailable = (qualityService.findByShapeAndName(shapeObj,name) == null);
			}
		}

		return qualityAvailable.toString();
	}
	
	
	@RequestMapping("/quality/report/listing")
	@ResponseBody
	public String qualityReportListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "shapeId", required = false) String shapeId,
			Principal principal) {
		
		StringBuilder sb = new StringBuilder();
		
		List<Quality> qualities = qualityService.findByShapeList(shapeId);
		
		sb.append("{\"total\":").append(qualityService.count()).append(",\"rows\": [");
		
		for (Quality quality : qualities) {
			sb.append("{\"id\":\"")
					.append(quality.getId())
					.append("\",\"name\":\"")
					.append(quality.getName())
					.append("\"},");
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
		
	}

}
