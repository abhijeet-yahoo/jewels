package com.jiyasoft.jewelplus.controller.manufacturing.masters;

import java.security.Principal;
import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.CollectionMaster;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ICollectionService;



@RequestMapping("/manufacturing/masters")
@Controller
public class CollectionController {

	@Autowired
    private ICollectionService collectionService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;
	
	@Autowired
	private UserRoleService userRoleService;

	
	@Autowired
	private MenuMastService menuMastService;
	
	@ModelAttribute("collection")
	public CollectionMaster construct() {
		return new CollectionMaster();
	}

	@RequestMapping("/collection")
	public String users(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("collection");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			
			
			return "collection";
		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}
		
		return "collection";
	}

	@RequestMapping("/collection/listing")
	@ResponseBody
	public String collectionListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "opt", required = true) String opt,
			Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<CollectionMaster> collections = null;

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("collection");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		
		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

		Long rowCount = null;

		if (search == null && sort == null) {
			rowCount = collectionService.count(sort, search, false);
			collections = collectionService.findByName(limit, offset, sort, order,search, false);
		} else if (search != null && sort != null && sort.equalsIgnoreCase("name")) {
			rowCount = collectionService.count(sort, search, false);
			collections = collectionService.findByName(limit, offset, sort, order,search, false);
		} else if (search != null && sort != null) {
			rowCount = 0L;
			collections = new PageImpl<CollectionMaster>(new ArrayList<CollectionMaster>());
		} else {
			rowCount = collectionService.count(sort, search, false);
			collections = collectionService.findByName(limit, offset, sort, order,search, false);
		}

		sb.append("{\"total\":").append(rowCount).append(",\"rows\": [");

		for (CollectionMaster collectionMaster : collections) {
			if(opt.equals("1")){
				sb.append("{\"id\":\"")
						.append(collectionMaster.getId())
						.append("\",\"name\":\"")
						.append(collectionMaster.getName())
						.append("\",\"collectionCode\":\"")
						.append((collectionMaster.getCollectionCode() == null ? "": collectionMaster.getCollectionCode()))
						.append("\",\"updatedBy\":\"")
						.append((collectionMaster.getModiBy() == null ? "" : collectionMaster.getModiBy()))
						.append("\",\"updatedDt\":\"")
						.append((collectionMaster.getModiDt() == null ? "" : collectionMaster.getModiDt()))
						.append("\",\"deactive\":\"")
						.append((collectionMaster.getDeactive() == null ? "": (collectionMaster.getDeactive() ? "Deactive": "Active")))
						.append("\",\"deactiveDt\":\"")
						.append((collectionMaster.getDeactiveDt() == null ? "": collectionMaster.getDeactiveDt()));

				if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
						userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
					
					sb.append("\",\"action1\":\"");
						sb.append("<a href='/jewels/manufacturing/masters/collection/edit/")
							.append(collectionMaster.getId()).append(".html'")
				
					.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

					sb.append("\",\"action2\":\"");
						sb.append(
								"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/collection/delete/")
								.append(collectionMaster.getId()).append(".html'")
								.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(collectionMaster.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
							.append("\"},");
				}else{
					
					sb.append("\",\"action1\":\"");
					if (roleRights.getCanEdit()) {
						sb.append("<a href='/jewels/manufacturing/masters/collection/edit/")
							.append(collectionMaster.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

					sb.append("\",\"action2\":\"");
					if (roleRights.getCanDelete()) {
						sb.append(
								"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/collection/delete/")
								.append(collectionMaster.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(collectionMaster.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
							.append("\"},");
				}
				
				
				
				
			
			}else if(opt.equals("3")){
				sb.append("{\"id\":\"")
				.append(collectionMaster.getId())
				.append("\",\"name\":\"")
				.append(collectionMaster.getName())
				.append("\",\"collectionCode\":\"")
				.append((collectionMaster.getCollectionCode() == null ? "": collectionMaster.getCollectionCode()))
				.append("\"},");
		
			}

		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}

	@RequestMapping("/collection/add")
	public String add(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("collection");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "collection/add";

		}else	
			
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}
		
		return "collection/add";
	}

	@RequestMapping(value = "/collection/add", method = RequestMethod.POST)
	public String addEditCollection(
			@Valid @ModelAttribute("collection") CollectionMaster collectionMaster,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "redirect:/manufacturing/masters/collection/add.html";

		if (result.hasErrors()) {
			return "collection/add";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			collectionMaster.setCreatedBy(principal.getName());
			collectionMaster.setCreatedDt(new java.util.Date());
			collectionMaster.setDeactive(false);
		} else {
			collectionMaster.setModiBy(principal.getName());
			collectionMaster.setModiDt(new java.util.Date());

			if (collectionMaster.getDeactive() != collectionService.findOne(id).getDeactive()) {
				collectionMaster.setDeactiveDt(new java.util.Date());
			} else {
				collectionMaster.setDeactiveDt(collectionService.findOne(id).getDeactiveDt());
			}
			collectionMaster.setId(id);
       
			action = "updated";
			
			retVal = "redirect:/manufacturing/masters/collection.html";
		}

		collectionService.save(collectionMaster);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;
	}

	@RequestMapping("/collection/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		CollectionMaster collectionMaster = collectionService.findOne(id);
		model.addAttribute("collection", collectionMaster);
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("collection");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "collection/add";

		}else	
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}
		
		return "collection/add";
	}

	@RequestMapping("/collection/delete/{id}")
	public String delete(@PathVariable int id) {
		collectionService.delete(id);
		return "redirect:/manufacturing/masters/collection.html";
	}

	@RequestMapping("/collectionAvailable")
	@ResponseBody
	public String collectionAvailable(@RequestParam String name,
			@RequestParam Integer id) {
		Boolean collectionAvailable = true;

		name = name.trim();

		if (id == null) {
			collectionAvailable = (collectionService.findByName(name) == null);
		} else {
			CollectionMaster collectionMaster = collectionService.findOne(id);
			if (!(name.equalsIgnoreCase(collectionMaster.getName()))) {
				collectionAvailable = (collectionService.findByName(name) == null);
			}
		}

		return collectionAvailable.toString();
	}
	
	@RequestMapping("/collectionCodeAvailable")
	@ResponseBody
	public String collectionCodeAvailable(@RequestParam String collectionCode,
			@RequestParam Integer id) {
		Boolean collectionCodeAvailable = true;

		collectionCode = collectionCode.trim();

		if (id == null) {
			collectionCodeAvailable = (collectionService.findByCollectionCode(collectionCode) == null);
		} else {
			CollectionMaster collectionMaster = collectionService.findOne(id);
			if (!(collectionCode.equalsIgnoreCase(collectionMaster.getCollectionCode()))) {
				collectionCodeAvailable = (collectionService.findByCollectionCode(collectionCode) == null);
			}
		}

		return collectionCodeAvailable.toString();
	}
}
