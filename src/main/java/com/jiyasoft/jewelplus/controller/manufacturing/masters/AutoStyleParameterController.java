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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.AutoStyleParameter;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IAutoStyleParameterService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ICategoryService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ICollectionService;

@RequestMapping("/manufacturing/masters")
@Controller
public class AutoStyleParameterController {
	@Autowired
	private IAutoStyleParameterService autoStyleParameterService;


	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;

	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private ICollectionService collectionService;
	
	@ModelAttribute("autoStyleParameter")
	public AutoStyleParameter construct() {
		return new AutoStyleParameter();
	}
	
	@RequestMapping("/autoStyleParameter")
	public String users(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("autoStyleParameter");
RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
	
			return "autoStyleParameter";
		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}
		
		return "autoStyleParameter";
	}
		
	@RequestMapping("/autoStyleParameter/listing")
	@ResponseBody
	public String autoStyleParameterListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			Principal principal) {

		StringBuilder sb = new StringBuilder();
		
		Page<AutoStyleParameter> autoStyleParameters = null;
	
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("autoStyleParameter");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

		
		  autoStyleParameters = autoStyleParameterService.searchAll(limit, offset , sort, order, search);
		 
		
		sb.append("{\"total\":").append(autoStyleParameters.getTotalElements()).append(",\"rows\": [");
		
		
		
		for (AutoStyleParameter autoStyleParameter : autoStyleParameters) {
			
			sb.append("{\"id\":\"")
			.append(autoStyleParameter.getId())
			.append("\",\"category\":\"") 
		    .append(autoStyleParameter.getCategory()!=null ? autoStyleParameter.getCategory().getName():"")
		    .append("\",\"collection\":\"") 
		    .append(autoStyleParameter.getCollectionMaster()!=null ? autoStyleParameter.getCollectionMaster().getName():"")
		    .append("\",\"lastNo\":\"")
		    .append(autoStyleParameter.getLastNo()!= null ? autoStyleParameter.getLastNo():"");
		
			if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
					userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
				sb.append("\",\"action1\":\"")
				.append("<a href='/jewels/manufacturing/masters/autoStyleParameter/edit/")
				.append(autoStyleParameter.getId()).append(".html'")
				.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

		sb.append("\",\"action2\":\"")
				.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/autoStyleParameter/delete/")
				.append(autoStyleParameter.getId()).append(".html'")
				.append(" class='btn btn-xs btn-danger triggerRemove")
				.append(autoStyleParameter.getId())
				.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
				.append("\"},");
			}else{
				sb.append("\",\"action1\":\"");
				if (roleRights.getCanEdit()) {
					sb.append("<a href='/jewels/manufacturing/masters/autoStyleParameter/edit/")
						.append(autoStyleParameter.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {
					sb.append(
							"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/autoStyleParameter/delete/")
							.append(autoStyleParameter.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-danger triggerRemove")
						.append(autoStyleParameter.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
						.append("\"},");
			}

		

	}

	String str = sb.toString();
	str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
			: str);
	str += "]}";

	return str;
}
	
	@RequestMapping("/autoStyleParameter/add")
	public String add(Model model,Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("autoStyleParameter");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("categoryMap",categoryService.getCategoryList());
			model.addAttribute("collectionMap",collectionService.getCollectionList());
			
			return "autoStyleParameter/add";

		}else	
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("categoryMap",categoryService.getCategoryList());
			model.addAttribute("collectionMap",collectionService.getCollectionList());
			
		}
		
		return "autoStyleParameter/add";
	}
		

	@ResponseBody
	@RequestMapping(value = "/autoStyleParameter/add", method = RequestMethod.POST)
	public String addEditAutoStyleParameter(
			@Valid @ModelAttribute("autoStyleParameter") AutoStyleParameter autoStyleParameter,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "/jewels/manufacturing/masters/autoStyleParameter/add.html?success=true";
		

		if (result.hasErrors()) {
			return "autoStyleParameter/add";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			
			AutoStyleParameter autoStyleParameter2 =
					
			autoStyleParameterService.findByCategoryAndCollectionMasterAndDeactive(autoStyleParameter.getCategory(), autoStyleParameter.getCollectionMaster(), false);
			if(autoStyleParameter2 != null){
				return retVal = "-1";
			}
			
			
			autoStyleParameter.setCreatedBy(principal.getName());
			autoStyleParameter.setCreatedDate(new java.util.Date());
			
		} else {
			
			AutoStyleParameter autoStyleParameter2 = autoStyleParameterService.findByCategoryAndCollectionMasterAndDeactive(autoStyleParameter.getCategory(), autoStyleParameter.getCollectionMaster(), false);
			
			if(autoStyleParameter2 != null){
				
				if(autoStyleParameter2.getId().equals(autoStyleParameter.getId())){
				}else{
					return retVal="-1"; 
				}
				
			}
			
			autoStyleParameter.setModiBy(principal.getName());
			autoStyleParameter.setModiDate(new java.util.Date());

			if (autoStyleParameter.isDeactive() != autoStyleParameterService.findOne(id).isDeactive()) {
				autoStyleParameter.setDeactiveDt(new java.util.Date());
			} else {
				autoStyleParameter.setDeactiveDt(autoStyleParameterService.findOne(id).getDeactiveDt());
			}
			autoStyleParameter.setId(id);
			
			action = "updated";
			retVal = "/jewels/manufacturing/masters/autoStyleParameter.html?success=true";
			
		}
		
				
		autoStyleParameterService.save(autoStyleParameter);
		
		
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;
	}	
	

	@RequestMapping("/autoStyleParameter/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
	
		AutoStyleParameter autoStyleParameter = autoStyleParameterService.findOne(id);
		model.addAttribute("autoStyleParameter", autoStyleParameter);
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("autoStyleParameter");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		
		
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			
			model.addAttribute("categoryMap",categoryService.getCategoryList());
			model.addAttribute("collectionMap",collectionService.getCollectionList());
			
			
			return "autoStyleParameter/add";

		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			
			
			model.addAttribute("categoryMap",categoryService.getCategoryList());
			model.addAttribute("collectionMap",collectionService.getCollectionList());
			
	
		}	
		
		return "autoStyleParameter/add";
	}
		
	@RequestMapping("/autoStyleParameter/delete/{id}")
	public String delete(@PathVariable int id) {
		autoStyleParameterService.delete(id);
		return "redirect:/manufacturing/masters/autoStyleParameter.html";
	}
	
		
	
		
		
		
		
		
		
		
		
}
