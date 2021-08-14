package com.jiyasoft.jewelplus.controller.manufacturing.masters;


import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.JSONObject;
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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LedgerGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.QLedgerGroup;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILedgerGroupService;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQuery;


@RequestMapping("/manufacturing/masters")
@Controller
public class LedgerGroupController {

	
	@Autowired
	private ILedgerGroupService ledgerGroupService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;

	@Autowired
	private UserRoleService userRoleService;
	
	
	@Autowired
	private EntityManager entityManager;
	
	@ModelAttribute("ledgerGroup")
	public LedgerGroup construct() {
		return new LedgerGroup();
	}

	@RequestMapping("/ledgerGroup")
	public String users(Model model,HttpSession httpSession,Principal principal) {
				
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("ledgerGroup");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
	
			return "ledgerGroup";
		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}
		return "ledgerGroup";
	}
	
	
	public Map<String, String> masterModel(){
		Map<String, String> mapList = new HashMap<String, String>();
		mapList.put("listingPage", "[ledgerGroup-add-edit-delete-refresh]");
		return mapList;
	}
	
	
	
	
	@RequestMapping("/ledgerGroup/listing")
	@ResponseBody
	public String ledgerGroupListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			Principal principal,HttpSession httpSession) {

		  
		    StringBuilder sb = new StringBuilder();
		    Page<LedgerGroup> ledgerGroups = null;
		    
		    User user = userService.findByName(principal.getName());
			UserRole userRole = userRoleService.findByUser(user);
			MenuMast menuMast = menuMastService.findByMenuNm("ledgerGroup");
			RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		  
		
		    if ((search != null) && (search.trim().length() == 0)) {
		    	search = null;
		    }
		
		    
		    ledgerGroups = ledgerGroupService.searchAll(limit, offset, sort, order, search, false);
		    
			sb.append("{\"total\":").append(ledgerGroups.getTotalElements()).append(",\"rows\": [");
	
			for (LedgerGroup ledgerGroup: ledgerGroups) {
				
				sb.append("{\"id\":\"")
						.append(ledgerGroup.getId())
						.append("\",\"name\":\"")
						.append(ledgerGroup.getName());
				
						LedgerGroup ledgerGroup2= ledgerGroupService.findOne(ledgerGroup.getGroupId());
						sb.append("\",\"mainGroup\":\"")
						.append(ledgerGroup2.getName() != null ? ledgerGroup2.getName() :"");
			
				
						sb.append("\",\"ledgerGroupCode\":\"")
						.append(ledgerGroup.getLedgerGroupCode())
						.append("\",\"nonEditable\":\"")
						.append(ledgerGroup.getNonEditable())
				        .append("\",\"deactive\":\"")
						.append((ledgerGroup.getDeactive() == null ? "Active" : (ledgerGroup.getDeactive() ? "Deactive" : "Active")));
						
						if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
								userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
							
							sb.append("\",\"action1\":\"");
								sb.append("<a href='/jewels/manufacturing/masters/ledgerGroup/edit/")
									.append(ledgerGroup.getId()).append(".html'")
									.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
				
							sb.append("\",\"action2\":\"");
								sb.append(
										"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/ledgerGroup/delete/")
										.append(ledgerGroup.getId()).append(".html'")
										.append(" class='btn btn-xs btn-danger triggerRemove")
										.append(ledgerGroup.getId())
										.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
										.append("\"},");
				
						}else{
							sb.append("\",\"action1\":\"");
							if (roleRights.getCanEdit()) {
								sb.append("<a href='/jewels/manufacturing/masters/ledgerGroup/edit/")
									.append(ledgerGroup.getId()).append(".html'");
							} else {
								sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
							}
							sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
				
							sb.append("\",\"action2\":\"");
							if (roleRights.getCanDelete()) {
								sb.append(
										"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/ledgerGroup/delete/")
										.append(ledgerGroup.getId()).append(".html'");
							} else {
								sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
							}
							sb.append(" class='btn btn-xs btn-danger triggerRemove")
									.append(ledgerGroup.getId())
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

	@RequestMapping("/ledgerGroup/add")
	public String add(Model model,HttpSession httpSession,Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("ledgerGroup");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		model.addAttribute("mainGroup", ledgerGroupService.getMainGroupList());
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			 return "ledgerGroup/add";

		}else	
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}
		
		 return "ledgerGroup/add";
		
		
	}
	
	
	
	@RequestMapping(value = "/ledgerGroup/add", method = RequestMethod.POST)
	public String addEditUser(@Valid @ModelAttribute("ledgerGroup") LedgerGroup ledgerGroup,
			BindingResult result, @RequestParam(value = "id") Integer id,@RequestParam Map<String,String> allRequestParams,
			RedirectAttributes redirectAttributes,Principal principal,HttpSession httpSession) {

		String action = "added";
		   String retVal = "redirect:/manufacturing/masters/ledgerGroup/add.html";
		   
		try {
			 	
				if (result.hasErrors()) {
					return "ledgerGroup/add";
				}
				
				LedgerGroup ledgerGroupDbData = ledgerGroupService.findByName(ledgerGroup.getName());
			
				if (id == null || id.equals("") || (id != null && id == 0)) {
				
				if(ledgerGroupDbData != null){
					return retVal = "-1";
				}
				
				ledgerGroup.setCreatedBy(principal.getName());
				ledgerGroup.setCreatedDate(new java.util.Date());
				
				if(ledgerGroup.getMainGroup() !=null && ledgerGroup.getMainGroup() !=""){
					LedgerGroup ledgerGroup2 = ledgerGroupService.findByName(ledgerGroup.getMainGroup());
					ledgerGroup.setGroupId(ledgerGroup2.getId());
				}
				
			}else {
				
				if(ledgerGroupDbData != null){
					if(!(ledgerGroupDbData.getId().equals(ledgerGroup.getId()))){
						return retVal = "-1";
					}
				}
				
				ledgerGroup.setId(id);
				ledgerGroup.setModiBy(principal.getName());
				ledgerGroup.setModiDate(new java.util.Date());
								
				if(ledgerGroup.getMainGroup() !=null && ledgerGroup.getMainGroup() !=""){
					LedgerGroup ledgerGroup2 = ledgerGroupService.findByName(ledgerGroup.getMainGroup());
					ledgerGroup.setGroupId(ledgerGroup2.getId(
					));
				}
				
				action = "updated";
				retVal = "redirect:/manufacturing/masters/ledgerGroup/add.html";
			}
			

			ledgerGroupService.save(ledgerGroup);
			
			redirectAttributes.addFlashAttribute("success", true);
			redirectAttributes.addFlashAttribute("action", action);

			
		} catch (Exception e) {
			e.printStackTrace();
			retVal = "Erorr : Record Not Saved("+e+") Contact Support";
		}
		   
		  
		return retVal;

	}

	@RequestMapping("/ledgerGroup/edit/{id}")
	public String edit(@PathVariable int id, Model model,
			HttpSession httpSession,Principal principal) {
		
		LedgerGroup ledgerGroup =ledgerGroupService.findOne(id);
		
		model.addAttribute("ledgerGroup", ledgerGroup);
		model.addAttribute("mainGroup", ledgerGroupService.getMainGroupList());
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("ledgerGroup");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "ledgerGroup/add";

		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}
		
		
		return "ledgerGroup/add";
		
		
	}


	@RequestMapping("/ledgerGroup/delete/{ids}")
	public String delete(@PathVariable String ids) {
		String retVal="-1";
		
		try {
			String[] id =ids.split(",");
			for(int i=0;i<id.length;i++){
				ledgerGroupService.delete(Integer.parseInt(id[i]));
			}
			
		} catch (Exception e) {
			retVal=e.toString();
		}
		
		return retVal;
	}
	
	
	@RequestMapping("/ledgerGroupAvailable")
	@ResponseBody
	public String ledgergroupAvailable(@RequestParam String name,
			@RequestParam Integer id,HttpSession httpSession) {
		
		Boolean ledgergroupAvailable = true;
		
		
		if (id == null) {
			ledgergroupAvailable = (ledgerGroupService.findByName(name) == null);
		} else {
			LedgerGroup ledgerGroup = ledgerGroupService.findOne(id);
			if (!(name.equalsIgnoreCase(ledgerGroup.getName()))) {
				ledgergroupAvailable = (ledgerGroupService.findByName(name) == null);
			}
		}

		return ledgergroupAvailable.toString();
	}
	
	
	
	
	
	  @RequestMapping("/mainLedgerGroup/list")
	  
	  @ResponseBody public String
	  mainLedgerGroupList(@ModelAttribute("ledgerGroup") LedgerGroup
	  ledgerGroup,HttpSession httpSession) {
	  
	  String retVal = "";
	  try {  
	  retVal =  ledgerGroupService.getMainLedgerGroupListDropDown(); 
	  } catch (Exception e) { 
		  e.printStackTrace(); retVal = "-1"; 
		  }
	  
	  return retVal; }
	 
	
	
	@ResponseBody
	@RequestMapping("/ledgerGroup/autoFillValidation")
	public String autoFillValidation(@RequestParam(value="name") String name,
			@RequestParam(value="voucherCode") String voucherCode,HttpSession httpSession){
		Boolean retVal = true;
		
		LedgerGroup ledgerGroup = ledgerGroupService.findByName(name);
		
		if(ledgerGroup == null){
			retVal = false;
		}
		
		return retVal.toString();
	}
	
	
	@ResponseBody
	@RequestMapping("/popUp/ledgerGroup/listing")
	public String popUpLedgerGroupListing(Model model,Principal principal,HttpSession httpSession) {
		
		StringBuilder sb = new StringBuilder();
		
		QLedgerGroup qLedgerGroup = QLedgerGroup.ledgerGroup;
		JPAQuery query = new JPAQuery(entityManager);
		
		List<Tuple> ledgerGroupDetails = null;
		
		ledgerGroupDetails = query.from(qLedgerGroup).
				where(qLedgerGroup.deactive.eq(false)).orderBy(qLedgerGroup.name.asc()).list(qLedgerGroup.id,qLedgerGroup.name);
		
		sb.append("{\"total\":").append(ledgerGroupDetails.size()).append(",\"rows\": [");
		
		for (Tuple row:ledgerGroupDetails) {
			sb.append("{\"id\":\"")
				.append(row.get(qLedgerGroup.id))
				.append("\",\"name\":\"")
				.append(row.get(qLedgerGroup.name))
				.append("\"},");
		}
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		
		return str;
	}
	
	
	
}
