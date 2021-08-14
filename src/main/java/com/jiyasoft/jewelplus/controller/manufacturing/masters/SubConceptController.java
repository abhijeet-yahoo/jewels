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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Concept;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SubConcept;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IConceptService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISubConceptService;

@RequestMapping("/manufacturing/masters")
@Controller
public class SubConceptController {

	@Autowired
	private IConceptService conceptService;;

	@Autowired
	private ISubConceptService subConceptService;;

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private UserRoleService userRoleService;

	@ModelAttribute("subConcept")
	public SubConcept construct() {
		return new SubConcept();
	}

	@RequestMapping("/subConcept")
	public String users(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("subConcept");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			
			
			return "subConcept";
		}else
		
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}

		return "subConcept";
	}

	@RequestMapping("/subConcept/listing")
	@ResponseBody
	public String subConceptListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "opt", required = true) String opt,
			Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<SubConcept> subConcepts = null;

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("subConcept");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

		Long rowCount = null;
		
		subConcepts = subConceptService.searchAll(limit, offset, sort, order, search, false);
		rowCount = subConceptService.countAll(sort, search, false);


		/*if (search == null) {
			if (id == null) {
				rowCount = subConceptService.count(sort, search, false);
				subConcepts = subConceptService.findAll(limit, offset, sort,
						order, search);
			} else {
				rowCount = subConceptService.count(sort, search, false);
				subConcepts = subConceptService.findByConcept(
						conceptService.findOne(id), limit, offset, sort, order,
						search);

			}
		} else {

			if (search != null && sort != null && sort.equalsIgnoreCase("name")) {
				rowCount = subConceptService.count(sort, search, false);
				subConcepts = subConceptService.findByName(limit, offset, sort,
						order, search, false);
			} else if (search != null && sort != null) {
				rowCount = 0L;
				subConcepts = new PageImpl<SubConcept>(
						new ArrayList<SubConcept>());
			} else {
				rowCount = subConceptService.count(sort, search, false);
				subConcepts = subConceptService.findByName(limit, offset, sort,
						order, search, false);
			}
		}
		*/
		
		sb.append("{\"total\":").append(rowCount).append(",\"rows\": [");

		for (SubConcept subConcept : subConcepts) {
			if(opt.equals("1")){
			sb.append("{\"id\":\"")
					.append(subConcept.getId())
					.append("\",\"conceptName\":\"")
					.append((subConcept.getConcept() != null ? subConcept.getConcept().getName() : ""))
					.append("\",\"name\":\"")
					.append(subConcept.getName() != null ? subConcept.getName() : "")
					.append("\",\"updatedBy\":\"")
					.append((subConcept.getModiBy() == null ? "" : subConcept
							.getModiBy()))
					.append("\",\"updatedDt\":\"")
					.append((subConcept.getModiDt() == null ? "" : subConcept
							.getModiDt()))
					.append("\",\"deactive\":\"")
					.append((subConcept.getDeactive() == null ? ""
							: (subConcept.getDeactive() ? "Deactive" : "Active")))
					.append("\",\"deactiveDt\":\"")
					.append((subConcept.getDeactiveDt() == null ? ""
							: subConcept.getDeactiveDt()));
		
						if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
								userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
							
							sb.append("\",\"action1\":\"");
								sb.append(
										"<a href='/jewels/manufacturing/masters/subConcept/edit/")
										.append(subConcept.getId()).append(".html'");
							sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
				
							sb.append("\",\"action2\":\"");
								sb.append(
										"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/subConcept/delete/")
										.append(subConcept.getId()).append(".html'");
						
						sb.append(" class='btn btn-xs btn-danger triggerRemove")
								.append(subConcept.getId())
								.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
								.append("\"},");
				
						}else{
							sb.append("\",\"action1\":\"");
							if (roleRights.getCanEdit()) {
								sb.append(
										"<a href='/jewels/manufacturing/masters/subConcept/edit/")
										.append(subConcept.getId()).append(".html'");
						} else {
								sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
							}
							sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
				
							sb.append("\",\"action2\":\"");
							if (roleRights.getCanDelete()) {
								sb.append(
										"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/subConcept/delete/")
										.append(subConcept.getId()).append(".html'");
							} else {
								sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
							}
						sb.append(" class='btn btn-xs btn-danger triggerRemove")
								.append(subConcept.getId())
								.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
								.append("\"},");
				
						}
						

						
			}else if(opt.equals("3")){
				sb.append("{\"id\":\"")
				.append(subConcept.getId())
				.append("\",\"conceptName\":\"")
				.append((subConcept.getConcept() != null ? subConcept.getConcept().getName() : ""))
				.append("\",\"name\":\"")
				.append(subConcept.getName())
				.append("\"},");
			}

			
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}

	
	
	
	
	@RequestMapping("/subConcept/report/listing")
	@ResponseBody
	public String subConceptReportListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "conId", required = false) String conId,
			Principal principal) {
	
		
		String str = null;
				
		if(conId.equalsIgnoreCase("0")){
			
			StringBuilder sb = new StringBuilder();
			
			List<SubConcept> subConceptList = subConceptService.findByDeactive(false);
			
			
			sb.append("{\"total\":").append(subConceptService.count()).append(",\"rows\": [");
			
			for(SubConcept subConcept : subConceptList){
				
				sb.append("{\"id\":\"")
				.append(subConcept.getId())
				.append("\",\"name\":\"")
				.append(subConcept.getName() != null ? subConcept.getName() : "")
				.append("\"},");
				
			}
			
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
			str += "]}";

		}else{
			str = subConceptService.getSubConceptReportListDropDown(conId);
		}
	
		
		return str;	
	}
	
	
	
	@RequestMapping("/subConcept/add")
	public String add(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("subConcept");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("conceptList", conceptService.getConceptList());
			return "subConcept/add";

		}else	

		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}
		
		model.addAttribute("conceptList", conceptService.getConceptList());


		return "subConcept/add";
	}

	@RequestMapping(value = "/subConcept/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditConcept(
			@Valid @ModelAttribute("subConcept") SubConcept subConcept,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "/jewels/manufacturing/masters/subConcept/add.html";

		if (result.hasErrors()) {
			return "subConcept/add";
		}
		
		
			
				

		if (id == null || id.equals("") || (id != null && id == 0)) {
			SubConcept subConcept2=subConceptService.findByConceptAndName(subConcept.getConcept(), subConcept.getName());
			if(subConcept2 != null){
				return retVal = "-1";
			}
				
			
			subConcept.setCreatedBy(principal.getName());
			subConcept.setCreateDate(new java.util.Date());
			subConcept.setDeactive(false);
		} else {
			
			SubConcept subConcept2=subConceptService.findByConceptAndName(subConcept.getConcept(), subConcept.getName());
			
			if(subConcept2 != null){
				
				
				if(subConcept2.getId().equals(subConcept.getId()))
				{
				}else{
					return retVal="-1"; 
				}
				
			}

			
			subConcept.setModiBy(principal.getName());
			subConcept.setModiDt(new java.util.Date());

			if (subConcept.getDeactive() != subConceptService.findOne(id)
					.getDeactive()) {
				subConcept.setDeactiveDt(new java.util.Date());
			} else {
				subConcept.setDeactiveDt(subConceptService.findOne(id)
						.getDeactiveDt());
			}
			subConcept.setId(id);

			action = "updated";
			retVal = "/jewels/manufacturing/masters/subConcept.html?id="
					+ subConcept.getConcept().getId();
		}
		subConceptService.save(subConcept);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;
	}

	@RequestMapping("/subConcept/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		SubConcept subConcept = subConceptService.findOne(id);
		model.addAttribute("subConcept", subConcept);
		model.addAttribute("conceptList", conceptService.getConceptList());

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("subConcept");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("conceptList", conceptService.getConceptList());
			return "subConcept/add";

		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}

		return "subConcept/add";
	}

	@RequestMapping("/subConcept/delete/{id}")
	public String delete(@PathVariable int id) {
		subConceptService.delete(id);

		return "redirect:/manufacturing/masters/subConcept.html";
	}

	@RequestMapping("/subConceptAvailable")
	@ResponseBody
	public String userAvailable(@RequestParam String name,
			@RequestParam Integer id,
			@RequestParam Integer conceptId) {
		Boolean subConceptAvailable = true;

		name = name.trim();

		Concept concept =conceptService.findOne(conceptId);
		if (id == null) {
			subConceptAvailable = (subConceptService.findByConceptAndName(concept, name) == null);
		} else {
			SubConcept subConcept = subConceptService.findOne(id);
			if (!(name.equalsIgnoreCase(subConcept.getName()))) {
				subConceptAvailable = (subConceptService.findByConceptAndName(concept, name) == null);
			}
		}

		return subConceptAvailable.toString();
	}

}
