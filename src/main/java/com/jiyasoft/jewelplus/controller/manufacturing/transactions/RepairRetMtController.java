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
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairRetDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairRetMt;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IHSNService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IUserDeptTrfRightService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IRepairRetCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IRepairRetDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IRepairRetMetalDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IRepairRetMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IRepairRetStnDtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class RepairRetMtController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private RoleRightsService roleRightsService;
	
	@Autowired
	private IRepairRetMtService repairRetMtService;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private IUserDeptTrfRightService userDeptTrfRightService;
	
	@Autowired
	private IRepairRetDtService repairRetDtService;
	
	@Autowired
	private IRepairRetCompDtService repairRetCompDtService;
	
	@Autowired
	private IRepairRetMetalDtService repairRetMetalDtService;
	
	@Autowired
	private IRepairRetStnDtService repairRetStnDtService;
	

	@Autowired
	private IHSNService hsnService;
	
	@ModelAttribute("repairRetMt")
	public RepairRetMt constructMt() {
		return new RepairRetMt();
	}
	
	
	@ModelAttribute("repairRetDt")
	public RepairRetDt constructDt() {
		return new RepairRetDt();
	}
	
	
	@RequestMapping("/repairRetMt")
	public String users(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("repairRetMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			return "repairRetMt";
		} else {
			if (roleRights == null) {
				return "accesDenied";
			} else {
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());

			}
			return "repairRetMt";
		}
	}
	
	
	@RequestMapping("/repairRetPickup")
	public String addScreen(Model model){
	
		return "repairRetPickup";
	}
	
	
	
	
	
	
	@RequestMapping("/repairRetMt/listing")
	@ResponseBody
	public String repairRetMtListing(Model model, @RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search, Principal principal)
			throws ParseException {

		return repairRetMtService.repairRetListing(limit, offset, sort, order, search, principal);

	}
	
	
	@RequestMapping("/repairRetMt/add")
	public String add(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("repairRetMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		model.addAttribute("departmentMap", departmentService.getDepartmentList());
		model.addAttribute("hsnMap", hsnService.getHsnList());
	
		Department department = departmentService.findByName("Marketing");
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
			model.addAttribute("canEditTranddate", "false");
			
			model.addAttribute("allPartyMap", partyService.getPartyList());
			
			return "repairRetMt/add";
			
		}else{
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("canView", roleRights.getCanView());
			
		}
		
		model.addAttribute("allPartyMap", partyService.getPartyList());
		
	
		
		model.addAttribute("canEditTranddate", "false");
		return "repairRetMt/add";
		}
	}
	
	
	@RequestMapping(value = "/repairRetMt/add", method = RequestMethod.POST)
	public String addEditPackMt(
			@Valid @ModelAttribute("repairRetMt") RepairRetMt repairRetMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal,
			@RequestParam(value = "pPartyIds", required = false) Integer pPartyIds,
			@RequestParam(value = "pLocationIds", required = false) Integer pLocationIds,
			@RequestParam(value = "vTranDate", required = false) String vTranDate) throws ParseException {

		synchronized (this) {
		return repairRetMtService.saveRepairRetMt(repairRetMt, id, redirectAttributes, principal, result, pPartyIds, pLocationIds,vTranDate);
			
		}
		}
	
	
	@RequestMapping("/repairRetMt/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("repairRetMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		RepairRetMt repairRetMt = repairRetMtService.findOne(id);
		model.addAttribute("repairRetMt", repairRetMt);
		
		model.addAttribute("hsnMap", hsnService.getHsnList());
		
		model.addAttribute("allPartyMap", partyService.getPartyList());
		model.addAttribute("departmentMap", departmentService.getDepartmentList());
		model.addAttribute("model", "repairRetMt");
		
		
		Department department = departmentService.findByName("Marketing");
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
			model.addAttribute("canEditTranddate", "false");
			model.addAttribute("mtid", id);
			
			
			return "repairRetMt/add";
			
		}else{
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}

		model.addAttribute("mtid", id);
		model.addAttribute("canEditTranddate", "false");
		

		return "repairRetMt/add";
		}
	}
	
	
	@RequestMapping("/repairReturnTransfer/listing")
	@ResponseBody
	public String repairReturnTransferList(@RequestParam(value = "partyId", required = false) Integer partyId,
			@RequestParam(value = "locationId", required = false) Integer locationId){
		
		return repairRetMtService.repairReturnTransferList(partyId, locationId);
	
}	
	
	
	@RequestMapping("/repairRetTransferInFactory")
	@ResponseBody
	public String repairRetTransferInStock(@RequestParam(value = "vMtId", required = false) String vMtId,
			@RequestParam(value = "repairRetMtId", required = false) Integer repairRetMtId,
			Principal principal) throws ParseException {

		String retVal = "-1";
	
	
		
		synchronized (this) {
			
			try {
				retVal =repairRetDtService.repairRetTransferInFactory(principal,vMtId,repairRetMtId);	
				
			} catch (Exception e) {
				System.out.println(e);
			}
			
		}
		
	return retVal;

	}
	
	
	@RequestMapping("/repairRetDt/listing")
	@ResponseBody
	public String repairRetDtList(@RequestParam(value = "mtId", required = false) Integer mtId,
			@RequestParam(value = "flag", required = false) String flag){
		
		return repairRetDtService.repairRetDtListing(mtId, flag);
		
	
}	
	
	
	@RequestMapping("/repairRetMetalDt/listing")
	@ResponseBody
	public String repairRetMetalDtList(@RequestParam(value = "dtId", required = false) Integer dtId){
		
	return repairRetMetalDtService.repairRetMetalDtListing(dtId);
	
}
	
	
	
	
	@RequestMapping("/repairRetStnDt/listing")
	@ResponseBody
	public String repairRetStnDtList(@RequestParam(value = "dtId", required = false) Integer dtId){
		
		return repairRetStnDtService.repairRetStnDtListing(dtId);
		
	
}
	
	
	@RequestMapping("/repairRetCompDt/listing")
	@ResponseBody
	public String repairRetCompDtList(@RequestParam(value = "dtId", required = false) Integer dtId,
			@RequestParam(value = "disableFlg", required = false) String disableFlg){
		
		return repairRetCompDtService.repairRetCompDtListing(dtId, disableFlg);
		
	
}
	
	@ResponseBody
	@RequestMapping("/repairRetDt/delete/{id}")
	public String delete(@PathVariable int id){
		
		String retVal = repairRetDtService.deleteRepairRetDt(id);
		
		return retVal;
		
	}
	
	@ResponseBody
	@RequestMapping("/repairRetMt/delete/{id}")
	public String deleteMt(@PathVariable int id){
		
		String retVal = repairRetMtService.deleteRepairRetMt(id);
		
		return retVal;
		
	}
	
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
}
