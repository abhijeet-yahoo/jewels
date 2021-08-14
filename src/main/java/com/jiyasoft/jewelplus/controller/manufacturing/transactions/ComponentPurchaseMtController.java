package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ComponentPurchaseDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ComponentPurchaseMt;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IComponentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IInwardTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IComponentPurchaseMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class ComponentPurchaseMtController {
	
	@Autowired
	private IComponentPurchaseMtService componentPurchaseMtService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private IPartyService partyService;

	@Autowired
	private IInwardTypeService inwardTypeService;

	@Autowired
	private IMetalService metalService;

	@Autowired
	private IColorService colorService;

	@Autowired
	private IComponentService componentService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;

	@Autowired
	private UserRoleService userRoleService;
	

	@Autowired
	private IPurityService purityService;

	@ModelAttribute("componentPurchaseMt")
	public ComponentPurchaseMt constructMt() {
		return new ComponentPurchaseMt();
	}

	@ModelAttribute("componentPurchaseDt")
	public ComponentPurchaseDt constructDt() {
		return new ComponentPurchaseDt();
	}

	@RequestMapping("/componentPurchaseMt")
	public String users(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("componentPurchaseMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		

	 if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("canView", roleRights.getCanView());
		}
		return "componentPurchaseMt";
	}

	@RequestMapping("/componentPurchaseMt/listing")
	@ResponseBody
	public String componentPurchaseMtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			Principal principal) throws ParseException {

			StringBuilder sb = new StringBuilder();
			Page<ComponentPurchaseMt> componentPurchaseMts = null;
			
			User user = userService.findByName(principal.getName());
			UserRole userRole = userRoleService.findByUser(user);
			MenuMast menuMast = menuMastService.findByMenuNm("componentPurchaseMt");
			RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
			
		
	
			if ((search != null) && (search.trim().length() == 0)) {
				search = null;
			}
	

			componentPurchaseMts=componentPurchaseMtService.searchAll(limit, offset, sort, order, search, true);
	
			sb.append("{\"total\":").append(componentPurchaseMts.getTotalElements()).append(",\"rows\": [");
	
			for (ComponentPurchaseMt componentPurchaseMt : componentPurchaseMts) {
				
				sb.append("{\"id\":\"")
				.append(componentPurchaseMt.getId())
				.append("\",\"invNo\":\"")
				.append(componentPurchaseMt.getInvNo())
				.append("\",\"invDate\":\"")
				.append(componentPurchaseMt.getInvDateStr())
				.append("\",\"beNo\":\"")
				.append((componentPurchaseMt.getBeNo() != null ? componentPurchaseMt.getBeNo() : ""))
				.append("\",\"beDate\":\"")
				.append((componentPurchaseMt.getBeDateStr() != null ? componentPurchaseMt.getBeDateStr() : ""))
				.append("\",\"party\":\"")
				.append((componentPurchaseMt.getParty() != null ? componentPurchaseMt.getParty().getName() : ""))
				.append("\",\"inwardType\":\"")
				.append((componentPurchaseMt.getInwardType() != null ? componentPurchaseMt.getInwardType().getName() : ""))
				.append("\",\"deactive\":\"")
				.append(componentPurchaseMt.getDeactive() ? "Yes" : "No");
		
				sb.append("\",\"action1\":\"");
				if (roleRights.getCanEdit()) {
						
					sb.append("<a href='/jewels/manufacturing/transactions/componentPurchaseMt/edit/")
					  .append(componentPurchaseMt.getId()).append(".html'");
					
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
			
				
				sb.append("\",\"action2\":\"");
				if (roleRights.getCanEdit()) {
					
				sb.append("<a href='javascript:deleteComponentMtPurchaseMt(event,")
				  .append(componentPurchaseMt.getId()).append(", 0);' href='javascript:void(0);'")
				.append(" class='btn btn-xs btn-danger triggerRemove")
				.append(componentPurchaseMt.getId());
				}
				else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
				
				sb.append("\",\"action3\":\"");
					if (roleRights.getCanView()) {
						sb.append("<a href='/jewels/manufacturing/transactions/componentPurchaseMt/view/")
							.append(componentPurchaseMt.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-success' ><span class='glyphicon glyphicon-eye-open'></span>&nbsp;View</a>")	;
				
				sb.append("\"},");
	}
	
	String str = sb.toString();
	str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
			: str);
	str += "]}";
	
	return str;
	}

	@RequestMapping("/componentPurchaseMt/add")
	public String add(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("componentPurchaseMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("canView", roleRights.getCanView());
			model = populateModel(model,principal);

			return "componentPurchaseMt/add";
		}
		
		
	}

	private Model populateModel(Model model, Principal principal) {
		model.addAttribute("partyMap", partyService.getPartyList());
		model.addAttribute("inwardTypeMap",	inwardTypeService.getInwardTypeList());
		model.addAttribute("metalMap", metalService.getMetalList());
		model.addAttribute("colorMap", colorService.getColorList());
		model.addAttribute("componentMap", componentService.getComponentList());
		model.addAttribute("purityMap", purityService.getPurityList());
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		model.addAttribute("adminRightsMap", userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE"));
		
		/*
		 * if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
		 * userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") ||
		 * userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
		 * 
		 * model.addAttribute("canEditTranddate", "true");
		 * 
		 * }else { model.addAttribute("canEditTranddate", "false"); }
		 */
		
		model.addAttribute("canEditTranddate", "false");
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String curDate = dateFormat.format(date);
		model.addAttribute("currentDate", curDate);
		
	

		return model;
	}

	@RequestMapping(value = "/componentPurchaseMt/add", method = RequestMethod.POST)
	public String addEditCompInwardMt(
			@Valid @ModelAttribute("componentPurchaseMt") ComponentPurchaseMt componentPurchaseMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "redirect:/manufacturing/transactions/componentPurchaseMt/add.html";
		Date aDate;

		if (result.hasErrors()) {
			return "componentPurchaseMt/add";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			aDate = new java.util.Date();
			componentPurchaseMt.setCreatedBy(principal.getName());
			componentPurchaseMt.setCreatedDt(new java.util.Date());
			componentPurchaseMt.setUniqueId(aDate.getTime());
		} else {
			componentPurchaseMt.setModiBy(principal.getName());
			componentPurchaseMt.setModiDt(new java.util.Date());

			componentPurchaseMt.setId(id);

			action = "updated";
			retVal = "redirect:/manufacturing/transactions/componentPurchaseMt.html";
		}
		
		

		componentPurchaseMtService.save(componentPurchaseMt);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);
		
		if (action.equals("added")) {
			ComponentPurchaseMt componentPurchaseMt2 = componentPurchaseMtService.findByUniqueId(componentPurchaseMt.getUniqueId());
		
			retVal  = "redirect:/manufacturing/transactions/componentPurchaseMt/edit/"+componentPurchaseMt2.getId()+".html";
		}

		return retVal;
	}

	@RequestMapping("/componentPurchaseMt/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		ComponentPurchaseMt componentPurchaseMt = componentPurchaseMtService.findOne(id);
		model.addAttribute("componentPurchaseMt", componentPurchaseMt);

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("componentPurchaseMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("canView", roleRights.getCanView());
			model = populateModel(model,principal);

			return "componentPurchaseMt/add";
		}
		
	}
	
	@RequestMapping("/componentPurchaseMt/view/{id}")
	public String view(@PathVariable int id, Model model, Principal principal) {
		
		ComponentPurchaseMt componentPurchaseMt = componentPurchaseMtService.findOne(id);
		model.addAttribute("componentPurchaseMt", componentPurchaseMt);
		model = populateModel(model,principal);
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("compInwardMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("canView", roleRights.getCanView());
			model = populateModel(model,principal);

			return "componentPurchaseMt/add";
		}
	}

	@RequestMapping("/componentPurchaseMt/delete/{id}")
	public String delete(@PathVariable int id) {
		
		String retVal = componentPurchaseMtService.componentPurchaseMtDelete(id);
		if(retVal == "-2"){
			return "-2";
		}
		
		return "redirect:/manufacturing/transactions/componentPurchaseMt.html";
	}

	@RequestMapping("/componentPurchaseMt/invoiceNoAvailable")
	@ResponseBody
	public String invoiceAvailable(@RequestParam String invNo,
			@RequestParam Integer id) {

		Boolean invoiceAvailable = true;

		if (id == null) {

			invoiceAvailable = (componentPurchaseMtService.findByInvNoAndDeactive(invNo, false) == null);
		} else {
			ComponentPurchaseMt componentPurchaseMt = componentPurchaseMtService.findOne(id);
			if (!(invNo.equalsIgnoreCase(componentPurchaseMt.getInvNo()))) {
				invoiceAvailable = (componentPurchaseMtService.findByInvNoAndDeactive(invNo, false) == null);
			}
		}

		return invoiceAvailable.toString();
	}
	
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	


}
