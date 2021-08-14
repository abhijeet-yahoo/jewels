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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Concept;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IConceptService;


@RequestMapping("/manufacturing/masters")
@Controller
public class ConceptController {

	@Autowired
	private IConceptService conceptService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private UserRoleService userRoleService;

	@ModelAttribute("concept")
	public Concept construct() {
		return new Concept();
	}

	@RequestMapping("/concept")
	public String users(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("concept");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			
			
			return "concept";
		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}

		return "concept";
	}

	@RequestMapping("/concept/listing")
	@ResponseBody
	public String conceptListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "opt", required = true) String opt,
			Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<Concept> concepts = null;

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("concept");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;	
		}

		Long rowCount = null;

		/*// default search would be name wise
		if (search == null && sort == null) {
			rowCount = conceptService.count(sort, search, false);
			concepts = conceptService.findByName(limit, offset, sort, order, search, false);
		} else if (search != null && sort != null && sort.equalsIgnoreCase("name")) {
			rowCount = conceptService.count(sort, search, false);
			concepts = conceptService.findByName(limit, offset, sort, order, search, false);
		} else if (search != null && sort != null) {
			rowCount = 0L;
			concepts = new PageImpl<Concept>(new ArrayList<Concept>());
		} else {
			rowCount = conceptService.count(sort, search, false);
			concepts = conceptService.findByName(limit, offset, sort, order, search, false);
		}
		*/

		concepts = conceptService.searchAll(limit, offset, sort, order, search, false);
		rowCount = conceptService.countAll(sort, search, false);

		
		sb.append("{\"total\":").append(rowCount).append(",\"rows\": [");
		
		for (Concept concept : concepts) {
			if (opt.equals("1")) {
				
				sb.append("{\"id\":\"")
					.append(concept.getId())
					.append("\",\"name\":\"")
					.append(concept.getName().trim())
					.append("\",\"updatedBy\":\"")
					.append((concept.getModiBy() == null ? "" : concept.getModiBy()))
					.append("\",\"updatedDt\":\"")
					.append((concept.getModiDt() == null ? "" : concept.getModiDt()))
					.append("\",\"deactive\":\"")
					.append((concept.getDeactive() == null ? "" : (concept.getDeactive() ? "Deactive" : "Active")))
					.append("\",\"deactiveDt\":\"")
					.append((concept.getDeactiveDt() == null ? "" : concept.getDeactiveDt()));
				
				if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
						userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
					
						sb.append("\",\"action1\":\"");
						sb.append("<a href='/jewels/manufacturing/masters/concept/edit/").append(concept.getId()).append(
							".html'")
							.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

						sb.append("\",\"action2\":\"");
						sb.append(
								"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/concept/delete/")
								.append(concept.getId()).append(".html'")
								.append(" class='btn btn-xs btn-danger triggerRemove")
								.append(concept.getId())
								.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
								.append("\"},");

				}else{
					sb.append("\",\"action1\":\"");
					if (roleRights.getCanEdit()) {
						sb.append("<a href='/jewels/manufacturing/masters/concept/edit/").append(concept.getId()).append(
							".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

					sb.append("\",\"action2\":\"");
					if (roleRights.getCanDelete()) {
						sb.append(
								"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/concept/delete/")
								.append(concept.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(concept.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
							.append("\"},");

				}
				

								} else if (opt.equals("2")) {
				sb.append("{\"name\":\"")
						.append("<a href='javascript:getChild(")
						.append(concept.getId()).append(")'>")
						.append(concept.getName()).append("</a>\"},");
			}else if(opt.equals("3")){
				sb.append("{\"id\":\"")
				.append(concept.getId())
				.append("\",\"name\":\"")
				.append(concept.getName())
				.append("\"},");
				
			}
		}

		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";
		
		return str;
	}

	@RequestMapping("/concept/add")
	public String add(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("concept");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "concept/add";

		}else	
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}

		return "concept/add";
	}

	@RequestMapping(value = "/concept/add", method = RequestMethod.POST)
	public String addEditConcept(
			@Valid @ModelAttribute("concept") Concept concept,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "redirect:/manufacturing/masters/concept/add.html";

		if (result.hasErrors()) {
			return "concept/add";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			concept.setCreatedBy(principal.getName());
			concept.setCreateDate(new java.util.Date());
			concept.setDeactive(false);
		} else {
			concept.setModiBy(principal.getName());
			concept.setModiDt(new java.util.Date());

			if (concept.getDeactive() != conceptService.findOne(id).getDeactive()) {
				concept.setDeactiveDt(new java.util.Date());
			} else {
				concept.setDeactiveDt(conceptService.findOne(id).getDeactiveDt());
			}
			concept.setId(id);

			action = "updated";
			retVal = "redirect:/manufacturing/masters/concept.html";
		}

		conceptService.save(concept);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;
	}
	
	
	@RequestMapping("/concept/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		Concept concept = conceptService.findOne(id);
		model.addAttribute("concept", concept);

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("concept");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "concept/add";

		}else	
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}

		return "concept/add";
	}

	@RequestMapping("/concept/delete/{id}")
	public String delete(@PathVariable int id) {
		conceptService.delete(id);

		return "redirect:/manufacturing/masters/concept.html";
	}

	@RequestMapping("/conceptAvailable")
	@ResponseBody
	public String userAvailable(@RequestParam String name,
			@RequestParam Integer id) {
		Boolean conceptAvailable = true;

		name = name.trim();

		if (id == null) {
			conceptAvailable = (conceptService.findByName(name) == null);
		} else {
			Concept concept = conceptService.findOne(id);
			if (!(name.equalsIgnoreCase(concept.getName()))) {
				conceptAvailable = (conceptService.findByName(name) == null);
			}
		}

		return conceptAvailable.toString();
	}

}
