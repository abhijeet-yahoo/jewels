package com.jiyasoft.jewelplus.controller.manufacturing.masters;

import java.security.Principal;
import java.util.List;




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

import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILookUpMastService;

@RequestMapping("/manufacturing/masters")
@Controller
public class LookUpMastController {

	@Autowired
	private ILookUpMastService lookUpMastService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@ModelAttribute("lookUpMast")
	public LookUpMast  construct(){
		return new LookUpMast();	
	}
	
	
	@RequestMapping("/lookUpMast")
	public String lookUpMast(Model model,HttpSession httpSession,Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("lookUpMast");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			
			
			return "lookUpMast";
		}
		else{

			if(roleRights == null){
				return "accesDenied";
			}else{
				model.addAttribute("canAdd", roleRights.getCanAdd());
			}
			
			return "lookUpMast";
		}
	
		
	}
	
	
	

	@RequestMapping("/lookUpMast/listing")
	@ResponseBody
	public String lookupListingByType(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<LookUpMast> lookups = null;
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("lookUpMast");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
	
		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}
		
//		Long rowCount = null;
		lookups = lookUpMastService.searchAll(limit, offset, sort, order, search, false);
//		rowCount = lookUpMastService.countAll(sort, search, false);
		
		sb.append("{\"total\":").append(lookups.getTotalElements()).append(",\"rows\": [");

		for (LookUpMast lookUpMast: lookups) {
						
			sb.append("{\"id\":\"")
					.append(lookUpMast.getId())
					.append("\",\"fieldValue\":\"")
					.append(lookUpMast.getFieldValue() != null ? lookUpMast.getFieldValue() : "")
					.append("\",\"name\":\"")
					.append(lookUpMast.getName() != null ? lookUpMast.getName() : "")
					.append("\",\"code\":\"")
					.append(lookUpMast.getCode() != null ? lookUpMast.getCode() : "")
					.append("\",\"nonEditable\":\"")
					.append(lookUpMast.getNonEditable())
					.append("\",\"deactive\":\"")
					.append(lookUpMast.getDeactive() != null ? (lookUpMast.getDeactive() ? "Deactive":"Active") : "");
			
			if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
					userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){

				sb.append("\",\"action1\":\"");
					sb.append("<a href='/jewels/manufacturing/masters/lookUpMast/edit/")
						.append(lookUpMast.getId()).append(".html'")
					.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
				

				sb.append("\",\"action2\":\"");
					sb.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/lookUpMast/delete/")
							.append(lookUpMast.getId()).append(".html'")
							.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(lookUpMast.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
				}
			else{
			
					sb.append("\",\"action1\":\"");
						if (roleRights.getCanEdit()) {
							sb.append("<a href='/jewels/manufacturing/masters/lookUpMast/edit/")
								.append(lookUpMast.getId()).append(".html'");
						} else {
							sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
						}
						if(lookUpMast.getNonEditable()){
						sb.append(" class='btn btn-xs btn-warning' disabled='disabled' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
						}else{
							sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
						}
		
						sb.append("\",\"action2\":\"");
						if (roleRights.getCanDelete()) {
							sb.append(
									"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/lookUpMast/delete/")
									.append(lookUpMast.getId()).append(".html'");
						} else {
							sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
						}
						
						if(lookUpMast.getNonEditable()){
							sb.append(" class='btn btn-xs btn-danger triggerRemove' disabled='disabled'")
							.append(lookUpMast.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
						}else{
							sb.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(lookUpMast.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
						}
			}
						
								sb.append("\"},");

					
		}
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		
		System.out.println("str   "+str);
		
		return str;

	}

	
	@RequestMapping("/lookUpMast/add")
	public String add(Model model,HttpSession httpSession,Principal principal) {
				
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("lookUpMast");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("masterTypeMap", lookUpMastService.getTypeList());
			return "lookUpMast/add";
		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("masterTypeMap", lookUpMastService.getTypeList());
		}
		
		return "lookUpMast/add";
	}
	
	

	@ResponseBody
	@RequestMapping(value = "/lookUpMast/add", method = RequestMethod.POST)
	public String addEditLookUpMast(@Valid @ModelAttribute("lookUpMast") LookUpMast lookUpMast,
			BindingResult result, @RequestParam(value = "id") Integer id,
			HttpSession httpSession,Principal principal) {
		
		String action = "added";
		String retVal = "/jewels/manufacturing/masters/lookUpMast/add.html?success=true";
		
		if (result.hasErrors()) {
			return "lookUpMast/add";
		}
		
		
		
		
		try {
				
			LookUpMast lookupDbData = lookUpMastService.findByNameAndFieldValueAndDeactive(lookUpMast.getName(), lookUpMast.getFieldValue(),false);
			
			LookUpMast lookupCodeDbData = lookUpMastService.findByNameAndCodeAndDeactive(lookUpMast.getName(), lookUpMast.getCode(),false);

			if (id == null || id.equals("") || (id != null && id == 0)) {
				
				if(lookupDbData != null){
					return retVal = "-1";
				}
				
				if(lookupCodeDbData != null){
					return retVal = "-2";
				}
				
				lookUpMast.setCreatedBy(principal.getName());
				lookUpMast.setCreatedDate(new java.util.Date());
				
			}else{
				
				if(lookupDbData != null){
					if(!(lookupDbData.getId().equals(lookUpMast.getId()))){
						return retVal = "-1";
					}
				}
				
				if(lookupCodeDbData != null){
					if(!(lookupCodeDbData.getId().equals(lookUpMast.getId()))){
						return retVal = "-2";
					}
				}
				
				lookUpMast.setModiBy(principal.getName());
				lookUpMast.setModiDate(new java.util.Date());
				action = "updated";
				retVal = "/jewels/manufacturing/masters/lookUpMast.html?success=true";
			 }

			lookUpMastService.save(lookUpMast);
		} catch (Exception e) {
			e.printStackTrace();
			retVal = "Erorr : Record Not Saved("+e+") Contact Support";
		}
		
		
		
		return retVal;

	}
	
	
	
	@RequestMapping("/lookUpMast/edit/{id}")
	public String edit(@PathVariable int id, Model model,HttpSession httpSession,Principal principal) {
		
		LookUpMast  lookUpMast = lookUpMastService.findOne(id);
		
		model.addAttribute("lookUpMast", lookUpMast);
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("lookUpMast");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("masterTypeMap", lookUpMastService.getTypeList());
			return "lookUpMast/add";
		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			
			model.addAttribute("masterTypeMap", lookUpMastService.getTypeList());
		}
		
	
		
		return "lookUpMast/add";
		
	}

	@RequestMapping("/lookUpMast/delete/{id}")
	public String delete(@PathVariable int id) {
		lookUpMastService.delete(id);
		return "redirect:/manufacturing/masters/lookUpMast.html";
	}

	/*@ResponseBody
	@RequestMapping("/lookup/transtatus")
	public String lookupTranStatus(HttpSession httpSession){
		
		Company compSessionObj = (Company) httpSession.getAttribute("companyObj");
		List<LookUp> lookUps = lookUpService.findByNameAndCompId("TranStatus", compSessionObj.getCompId());
				
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for(LookUp lookUp:lookUps){
			JSONObject jsonObjectNew = new JSONObject();
			jsonObjectNew.put("id", lookUp.getId());
			jsonObjectNew.put("fieldValue", lookUp.getFieldValue());
			jsonArray.put(jsonObjectNew);
		}
		
		jsonObject.put("data", jsonArray);
		
		return jsonObject.toString();
		
	}
	*/
	
	
	@RequestMapping("/lookUpMast/DropdownByType")
	@ResponseBody
	public String getDropdownData(HttpSession httpSession,@RequestParam(value="name") String name){
		
		List<LookUpMast> lookUps = lookUpMastService.findByName(name);
		JSONObject jsonObject = new JSONObject();
		for(LookUpMast lookUp:lookUps){
			jsonObject.put(lookUp.getId().toString(), lookUp.getFieldValue());
		}
		return jsonObject.toString();
	}
	
	
	
	@RequestMapping("/lookup/typeWise/listing")
	@ResponseBody
	public String reportLookupListing(Model model,
			@RequestParam(value = "type", required = false) String type,
			Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<LookUpMast> lookups = lookUpMastService.findLookUpMastByType(type);
		
		sb.append("{\"total\":").append(lookups.getTotalElements()).append(",\"rows\": [");

		for (LookUpMast lookUpMast: lookups) {
						
					sb.append("{\"id\":\"")
					.append(lookUpMast.getId())
					.append("\",\"name\":\"")
					.append(lookUpMast.getFieldValue() != null ? lookUpMast.getFieldValue() : "");
					sb.append("\"},");

					
		}
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		
		System.out.println("str   "+str);
		
		return str;

	}
	
	
	
	
	

}
