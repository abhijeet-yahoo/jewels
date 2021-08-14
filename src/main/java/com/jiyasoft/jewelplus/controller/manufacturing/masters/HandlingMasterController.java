package com.jiyasoft.jewelplus.controller.manufacturing.masters;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.HandlingMaster;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.HandlingMasterFl;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IHandlingMasterService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;

@Controller
@RequestMapping("/manufacturing/masters")
public class HandlingMasterController {
	
	@Autowired
	private IHandlingMasterService handlingMasterService;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private IShapeService shapeService;
	
	@Autowired
	private IStoneTypeService stoneTypeService;
	
	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private RoleRightsService roleRightService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private IMetalService metalService;
	
	@Value("${companyName}")
	private String companyName;
	
	
	@ModelAttribute("handlingMaster")
	public HandlingMaster construct() {
		return new HandlingMaster();
	}
	

	@ModelAttribute("handlingMasterFl")
	public HandlingMasterFl construct2(){
		return new HandlingMasterFl();
	}

	@RequestMapping("/handlingMaster")
	public String users(Model model, Principal principal) {
		
		if(companyName.equalsIgnoreCase("flawless")){
		
			User user = userService.findByName(principal.getName());
			UserRole userRole = userRoleService.findByUser(user);
			MenuMast menuMast = menuMastService.findByMenuNm("handlingMaster");
			RoleRights roleRights = roleRightService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
			
			if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
					userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
			
				model.addAttribute("canAdd", true);
		
				return "handlingMasterFl";
			}else
			
			
			if(roleRights == null){
				return "accesDenied";
			}else{
				model.addAttribute("canAdd", roleRights.getCanAdd());
			}
			
			
			return "handlingMasterFl";
			
		}else{
			
			User loginUser = userService.findByName(principal.getName());
			UserRole userRole = userRoleService.findByUser(loginUser);
			MenuMast menuMast = menuMastService.findByMenuNm("handlingMaster");
			RoleRights roleRights = roleRightService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
			
			
			if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
					userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
			
				model.addAttribute("canAdd", true);
		
				return "handlingMaster";
				
			}else{		
			if(roleRights == null){
				return "accesDenied";
			}else{
				model.addAttribute("canAdd", roleRights.getCanAdd());
				return "handlingMaster";
				}
			}
		}
		
		

	
	}
	
	
	@RequestMapping("/handlingMaster/listing")
	@ResponseBody
	public String colorListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search, Principal principal) {

	    StringBuilder sb = new StringBuilder();
		Page<HandlingMaster> handlingMasters = null;
		
		
		User loginUser = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(loginUser);
		MenuMast menuMast = menuMastService.findByMenuNm("handlingMaster");
		RoleRights roleRights = roleRightService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}
		
		Long rowCount = null;
		rowCount = handlingMasterService.count(sort, search, true);
		handlingMasters = handlingMasterService.findByPartyAndShap(limit, offset, sort, order,search, true);

		sb.append("{\"total\":").append(rowCount).append(",\"rows\": [");

		for (HandlingMaster handlingMaster : handlingMasters) {
			
			sb.append("{\"id\":\"")
					.append(handlingMaster.getId())
					.append("\",\"party\":\"")
					.append(handlingMaster.getParty() != null ? handlingMaster.getParty().getPartyCode() : "")
					.append("\",\"metal\":\"")
					.append(handlingMaster.getMetal() !=null ? handlingMaster.getMetal().getName() :"")
					.append("\",\"stoneType\":\"")
					.append(handlingMaster.getStoneType().getName())
					.append("\",\"shape\":\"")
					.append(handlingMaster.getShape().getName())
					.append("\",\"perCarate\":\"")
					.append(handlingMaster.getPerCarate())
					.append("\",\"percentage\":\"")
					.append(handlingMaster.getPercentage());
			
			if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
					userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
				
					  sb.append("\",\"action1\":\"")
						.append("<a href='/jewels/manufacturing/masters/handlingMaster/edit/")
						.append(handlingMaster.getId()).append(".html'")
        				.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
					  sb.append("\",\"action2\":\"")
					    .append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/handlingMaster/delete/")
						.append(handlingMaster.getId()).append(".html'")
						.append(" class='btn btn-xs btn-danger triggerRemove")
						.append(handlingMaster.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
						.append("\"},");

			}else{
				
					sb.append("\",\"action1\":\"");
					if (roleRights.getCanEdit()) {
						sb.append("<a href='/jewels/manufacturing/masters/handlingMaster/edit/")
							.append(handlingMaster.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
		
					sb.append("\",\"action2\":\"");
					if (roleRights.getCanDelete()) {
						sb.append(
								"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/handlingMaster/delete/")
								.append(handlingMaster.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(handlingMaster.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
							.append("\"},");
	
				}
				
					
		}
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		System.out.println(str);
		
		return str;

	}

	
	public Model populateModel(Model model) {
		model.addAttribute("partyMap",partyService.getPartyList());
		model.addAttribute("metalMap", metalService.getMetalList());
		model.addAttribute("shapeMap",shapeService.getShapeList() );
		model.addAttribute("stoneTypeMap",stoneTypeService.getStoneTypeList() );
		return model;
	}
	
	
	@RequestMapping("/handlingMaster/add")
	public String add(Model model, Principal principal) {
		
		User loginUser = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(loginUser);
		MenuMast menuMast = menuMastService.findByMenuNm("handlingMaster");
		RoleRights roleRights = roleRightService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model = populateModel(model);
			
			return "handlingMaster/add";

		}else	
		
			if(roleRights == null){
				return "accesDenied";
			}else{
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model = populateModel(model);
			}
				return "handlingMaster/add";
		
		
		
	//	model = popRoleRightModel(model, principal);
	
	}
	
	public Model popRoleRightModel(Model model,Principal principal){
		User loginUser = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(loginUser);
		MenuMast menuMast = menuMastService.findByMenuNm("handlingMaster");
		RoleRights roleRights = roleRightService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		model.addAttribute("canAdd", roleRights.getCanAdd());
		model.addAttribute("canDelete", roleRights.getCanDelete());
		model.addAttribute("canEdit", roleRights.getCanEdit());
		return model;
	}

	@ResponseBody
	@RequestMapping(value = "/handlingMaster/add", method = RequestMethod.POST)
	public String addEditUser(@Valid @ModelAttribute("handlingMaster") HandlingMaster handlingMaster,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {
		
		
		String action = "added";
		String retVal = "/jewels/manufacturing/masters/handlingMaster/add.html?success=true";

		if (result.hasErrors()) {
			return "handlingMaster/add";
		}
		
		if(handlingMaster.getPerCarate() == null){
			handlingMaster.setPerCarate(0.0);
		}
		
		if(handlingMaster.getPercentage() == null){
			handlingMaster.setPercentage(0.0);;
		}
		
		if(handlingMaster.getPerCarate() > 0 && handlingMaster.getPercentage() > 0){
			return retVal = "-2";
		}else if(handlingMaster.getPerCarate() == 0 && handlingMaster.getPercentage() == 0){
			return retVal = "-3";
		}

		HandlingMaster handlingMasterValidate = null;
		
		if (id == null || id.equals("") || (id != null && id == 0)) {
			handlingMasterValidate = handlingMasterService.findByPartyAndMetalAndStoneTypeAndShapeAndDeactive(handlingMaster.getParty(),handlingMaster.getMetal(),handlingMaster.getStoneType(), handlingMaster.getShape(), false);
			if(handlingMasterValidate != null){
				return retVal = "-1";
			}
			handlingMaster.setCreatedBy(principal.getName());
			handlingMaster.setCreatedDt(new java.util.Date());
		} else {
			handlingMasterValidate = handlingMasterService.findByPartyAndMetalAndStoneTypeAndShapeAndDeactive(handlingMaster.getParty(),handlingMaster.getMetal(),handlingMaster.getStoneType(), handlingMaster.getShape(), false);
           if(handlingMasterValidate != null){
				if(handlingMasterValidate.getId().equals(handlingMaster.getId())){
				}else{
					return retVal = "-1"; 
				}
			}
			
			handlingMaster.setId(id);
			handlingMaster.setModiBy(principal.getName());
			handlingMaster.setModiDt(new java.util.Date());
			action = "updated";
			retVal = "/jewels/manufacturing/masters/handlingMaster.html?success=true";
		}

		handlingMasterService.save(handlingMaster);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;

	}

	@RequestMapping("/handlingMaster/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		
		HandlingMaster handlingMaster = handlingMasterService.findOne(id);
		model.addAttribute("handlingMaster", handlingMaster);
		
		User loginUser = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(loginUser);
		MenuMast menuMast = menuMastService.findByMenuNm("handlingMaster");
		RoleRights roleRights = roleRightService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model = populateModel(model);
			
			return "handlingMaster/add";

		}else	
		
			if(roleRights == null){
				return "accesDenied";
			}else{
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model = populateModel(model);
			}
				return "handlingMaster/add";
		
	//	model = popRoleRightModel(model, principal);
	//	model = populateModel(model);
	//	return "handlingMaster/add";
	}
	
	
	
	@RequestMapping("/handlingMaster/view/{id}")
	public String view(@PathVariable int id, Model model, Principal principal) {
		
		
		HandlingMaster handlingMaster = handlingMasterService.findOne(id);
		model.addAttribute("handlingMaster", handlingMaster);
		model = populateModel(model);
		User loginUser = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(loginUser);
		MenuMast menuMast = menuMastService.findByMenuNm("handlingMaster");
		RoleRights roleRights = roleRightService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		model.addAttribute("canView",roleRights.getCanView());
		model.addAttribute("editRight",roleRights.getCanEdit());
		
		return "handlingMaster/add";
		
	}

	@RequestMapping("/handlingMaster/delete/{id}")
	public String delete(@PathVariable int id) {
		handlingMasterService.delete(id);
		return "redirect:/manufacturing/masters/handlingMaster.html";
	}



}
