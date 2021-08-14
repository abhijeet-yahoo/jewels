package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;
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
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ReadyBagRetDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ReadyBagRetMt;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILocationRightsService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IUserDeptTrfRightService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IReadyBagRetDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IReadyBagRetMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class ReadyBagRetController {
	

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	
	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private RoleRightsService roleRightsService;
	
	@Autowired
	private IReadyBagRetMtService readyBagRetMtService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	
	@Autowired
	private IReadyBagRetDtService readyBagRetDtService;
	
	@Autowired
	private IUserDeptTrfRightService userDeptTrfRightService;
	
	@Autowired
	private ILocationRightsService locationRightsService;
	
	@ModelAttribute("readyBagRetMt")
	public ReadyBagRetMt construct() {
		return new ReadyBagRetMt();
		
	}
	

	@ModelAttribute("readyBagRetDt")
	public ReadyBagRetDt constructDt() {
		return new ReadyBagRetDt();
		
	}
	

	@RequestMapping("/readyBagRetApproval")
	public String fgIssMtApp(Model model){
		return "readyBagRetApproval";
	}
	
	
	@RequestMapping("/readyBagRetMt")
	public String users(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("readyBagRetMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			return "readyBagRetMt";
		} else {
			if (roleRights == null) {
				return "accesDenied";
			} else {
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());

			}
			return "readyBagRetMt";
		}
	}
	
	
	@RequestMapping("/readyBagRetMt/listing")
	@ResponseBody
	public String readyBagRetMtListing(Model model, @RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search, Principal principal)
			throws ParseException {

		return readyBagRetMtService.readyBagRetMtListing(limit, offset, sort, order, search, principal);

	}
	
	
	
	@RequestMapping("/readyBagRetMt/add")
	public String add(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("readyBagRetMt");
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
			
			
			return "readyBagRetMt/add";
			
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
		
		
		return "readyBagRetMt/add";
		}
	}
	
	
	@RequestMapping(value = "/readyBagRetMt/add", method = RequestMethod.POST)
	public String addEditReadyBagRetMt(
			@Valid @ModelAttribute("readyBagRetMt") ReadyBagRetMt readyBagRetMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			 @RequestParam(value = "pLocationIds", required = false) Integer pLocationIds,
			 @RequestParam(value = "vTranDate", required = false) String vTranDate,
			RedirectAttributes redirectAttributes, Principal principal) throws ParseException {

		return readyBagRetMtService.readyBagRetMtSave(readyBagRetMt, id, redirectAttributes, principal, result,pLocationIds,vTranDate);
		
	}
	
	@RequestMapping("/readyBagRetMt/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("readyBagRetMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		ReadyBagRetMt readyBagRetMt = readyBagRetMtService.findOne(id);
		model.addAttribute("readyBagRetMt", readyBagRetMt);
		
		Department department = departmentService.findByName("Diamond");
		model.addAttribute("locationMap", userDeptTrfRightService.getToDepartmentListFromUser(user.getId(), department.getId()));
		
		model.addAttribute("mtid", id);
		
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
		
			
			return "readyBagRetMt/add";
			
		}else{
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}

		
	

		return "readyBagRetMt/add";
		}
	}
	
	


	
	@RequestMapping("/readyBagRetDt/listing")
	@ResponseBody
	public String ReadyBagRetDtListing(@RequestParam(value = "mtId", required = false) Integer mtId,
			@RequestParam(value = "disableFlg", required = false) String disableFlg){
		
		return readyBagRetDtService.ReadyBagRetDtListing(mtId,disableFlg);
		
	
	}
	
	@ResponseBody
	@RequestMapping("/readyBagRetDt/delete/{id}")
	public String deleteDt(@PathVariable int id){
		
		String retVal = readyBagRetDtService.deleteDt(id);
		
		return retVal;
		
	}
	
	@ResponseBody
	@RequestMapping("/readyBagRetMt/delete/{id}")
	public String deleteMt(@PathVariable int id){
		
		String retVal = readyBagRetMtService.deleteMt(id);
		
		return retVal;
		
	}
	
	
	
	
	@RequestMapping("/readyBagReturnList")
	public String readyBagReturnList(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("readyBagRetMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			return "readyBagReturnList";
		} else {
			if (roleRights == null) {
				return "accesDenied";
			} else {
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());

			}
			return "readyBagReturnList";
		}
	}

	
	@ResponseBody
	@RequestMapping("/returnReadyBagTrf/listing")
	public String readyBagIssTrfList(Principal principal,@RequestParam(value = "mtId", required = false) Integer mtId){
		
		String retVal ="-1";
		try {
			
			retVal = readyBagRetDtService.returnReadyBagTrfListing(mtId, principal);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return retVal;
	}
	
	@RequestMapping(value = "/readyBagRetDt/add", method = RequestMethod.POST)
	@ResponseBody
	public String readyBagIssDtTransfer(
			@Valid @ModelAttribute("readyBagRetDt") ReadyBagRetDt readyBagRetDt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "readyBagRetMtId") Integer readyBagRetMtId,
			@RequestParam(value="pBagIds")String pBagIds,
			RedirectAttributes redirectAttributes, Principal principal) throws ParseException {
		
		String retVal = "-1";
		synchronized (this) {
		
			retVal = readyBagRetDtService.returnReadyBagIss(pBagIds, readyBagRetMtId, principal);
		}
		
	return retVal;

	}
	
	
	@RequestMapping(value = "/readyBagRetApprove", method = RequestMethod.POST)
	@ResponseBody
	public String readyBagRetApprove(
			@RequestParam(value="vBagIds")String pBagIds,
			RedirectAttributes redirectAttributes, Principal principal) throws ParseException {
		
		String retVal = "-1";
		synchronized (this) {
		
			retVal = readyBagRetDtService.readyBagRetApprove(pBagIds,principal);
		}
		
	return retVal;

	}
	
	
	
	
	
	@RequestMapping("/readyBagRet/appListing")
	@ResponseBody
	public String readyBagRetAppList(){
		
		return readyBagRetDtService.ReadyBagRetDtPendingApprovalListing() ;
	
}	

	

	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

}
