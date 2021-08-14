package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ReadyBagIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ReadyBagIssMt;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILocationRightsService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IUserDeptTrfRightService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IReadyBagIssDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IReadyBagIssMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class ReadyBagIssController {
	

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	
	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private RoleRightsService roleRightsService;
	
	@Autowired
	private IReadyBagIssMtService readyBagIssMtService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	
	@Autowired
	private IReadyBagIssDtService readyBagIssDtService;
	
	@Autowired
	private IUserDeptTrfRightService userDeptTrfRightService;
	
	@Autowired
	private ILocationRightsService locationRightsService;
;
	
	@ModelAttribute("readyBagIssMt")
	public ReadyBagIssMt construct() {
		return new ReadyBagIssMt();
		
	}
	

	@ModelAttribute("readyBagIssDt")
	public ReadyBagIssDt constructDt() {
		return new ReadyBagIssDt();
		
	}
	
	@RequestMapping("/readyBagApproval")
	public String pendingApproval(Model model, Principal principal) {
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String curDate = dateFormat.format(date);
		model.addAttribute("currentDate", curDate);
		
		User user = userService.findByName(principal.getName());
		model.addAttribute("locationMap", locationRightsService.getToLocationListFromUser(user.getId(),"All"));
		return "readyBagApproval";
	}
	
	@RequestMapping("/readyBagTranfer")
	public String readyBagTranfer(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("readyBagIssMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			return "readyBagTranfer";
		} else {
			if (roleRights == null) {
				return "accesDenied";
			} else {
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());

			}
			return "readyBagTranfer";
		}
	}
	
	
	@RequestMapping("/readyBagIssMt")
	public String users(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("readyBagIssMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			return "readyBagIssMt";
		} else {
			if (roleRights == null) {
				return "accesDenied";
			} else {
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());

			}
			return "readyBagIssMt";
		}
	}
	
	
	@RequestMapping("/readyBagIssMt/listing")
	@ResponseBody
	public String readyBagIssMtListing(Model model, @RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "disableFlg", required = false) String disableFlg, Principal principal)
			throws ParseException {

		return readyBagIssMtService.readyBagIssMtListing(limit, offset, sort, order, search, principal);

	}
	
	
	
	@RequestMapping("/readyBagIssMt/add")
	public String add(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("readyBagIssMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		Department department = departmentService.findByName("Diamond");
		model.addAttribute("locationMap", userDeptTrfRightService.getToDepartmentListFromUser(user.getId(), department.getId()));
		
	
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String curDate = dateFormat.format(date);
			model.addAttribute("currentDate", curDate);
			
			
			return "readyBagIssMt/add";
			
		}else{
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("canView", roleRights.getCanView());
			
		}
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String curDate = dateFormat.format(date);
		model.addAttribute("currentDate", curDate);
		
		
		return "readyBagIssMt/add";
		}
	}
	
	
	@RequestMapping(value = "/readyBagIssMt/add", method = RequestMethod.POST)
	public String addEditReadyBagIssMt(
			@Valid @ModelAttribute("readyBagIssMt") ReadyBagIssMt readyBagIssMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			 @RequestParam(value = "pLocationIds", required = false) Integer pLocationIds,
			 @RequestParam(value = "vTranDate", required = false) String vTranDate,
			RedirectAttributes redirectAttributes, Principal principal) throws ParseException {

		return readyBagIssMtService.readyBagMtSave(readyBagIssMt, id, redirectAttributes, principal, result,pLocationIds,vTranDate);
		
	}
	
	@RequestMapping("/readyBagIssMt/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("readyBagIssMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		ReadyBagIssMt readyBagIssMt = readyBagIssMtService.findOne(id);
		model.addAttribute("readyBagIssMt", readyBagIssMt);
		
		Department department = departmentService.findByName("Diamond");
		model.addAttribute("locationMap", userDeptTrfRightService.getToDepartmentListFromUser(user.getId(), department.getId()));
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String curDate = dateFormat.format(date);
		model.addAttribute("currentDate", curDate);
		
		
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			model.addAttribute("mtid", id);
			
			
			return "readyBagIssMt/add";
			
		}else{
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}

		model.addAttribute("mtid", id);
	

		return "readyBagIssMt/add";
		}
	}
	
	
	@ResponseBody
	@RequestMapping("/readyBagIssTrf/listing")
	public String readyBagIssTrfList(Principal principal){
		
		String retVal ="-1";
		try {
			
			retVal = readyBagIssDtService.readyBagTrfListing(principal);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return retVal;
	}
	

	@RequestMapping(value = "/readyBagIssDt/add", method = RequestMethod.POST)
	@ResponseBody
	public String readyBagIssDtTransfer(
			@Valid @ModelAttribute("readyBagIssDt") ReadyBagIssDt readyBagIssDt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "readyBagIssMtId") Integer readyBagIssMtId,
			@RequestParam(value="pBagIds")String pBagIds,
			RedirectAttributes redirectAttributes, Principal principal) throws ParseException {
		
		String retVal = "-1";
	
		
		synchronized (this) {
		
			retVal = readyBagIssDtService.transferReadyBagIss(pBagIds, readyBagIssMtId, principal);
		}
		
	return retVal;

	}
	
	@RequestMapping("/readyBagIssDt/listing")
	@ResponseBody
	public String ReadyBagIssDtListing(@RequestParam(value = "mtId", required = false) Integer mtId,
			@RequestParam(value = "disableFlg", required = false) String disableFlg){
		
		return readyBagIssDtService.ReadyBagIssDtListing(mtId,disableFlg);
		
	
	}
	
	@ResponseBody
	@RequestMapping("/readyBagIssDt/delete/{id}")
	public String deleteDt(@PathVariable int id){
		
		String retVal = readyBagIssDtService.deleteDt(id);
		
		return retVal;
		
	}
	
	@ResponseBody
	@RequestMapping("/readyBagIssMt/delete/{id}")
	public String deleteMt(@PathVariable int id){
		
		String retVal = readyBagIssMtService.deleteMt(id);
		
		return retVal;
		
	}
	
	@ResponseBody
	@RequestMapping("/readyBagPendingApproval/listing")
	public String readyBagPendingApprovalList(@RequestParam(value = "locationId", required = false) Integer locationId,Principal principal){
		
		String retVal = readyBagIssMtService.readyBagPendingApprovalList(locationId,principal);
		
		return retVal;
		
	}
	
	
	@RequestMapping("readyBagPendingApproval/Transfer")
	@ResponseBody
	public String pendingApprovalTransfer(
			@RequestParam(value = "locationId", required = false) Integer locationId,
			@RequestParam(value = "pBagId", required = false) String pBagId,
			Principal principal) throws ParseException {

		String retVal = "-1";
	
		
		synchronized (this) {
			retVal = readyBagIssDtService.getReadyBagPendingApproval(locationId, pBagId);
			
		}
		
	return retVal;

	}
	
	

	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

}
