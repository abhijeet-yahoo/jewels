package com.jiyasoft.jewelplus.controller.marketing.transactions;

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
import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StkTrfMt;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IHSNService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILookUpMastService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IUserDeptTrfRightService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStkTrfCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStkTrfDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStkTrfLabDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStkTrfMetalDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStkTrfMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStkTrfStnDtService;

@RequestMapping("/marketing/transactions")
@Controller
public class StkTrfController {


	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private RoleRightsService roleRightsService;
	
	@Autowired
	private IStkTrfMtService stkTrfMtService;
	
	@Autowired
	private IUserDeptTrfRightService userDeptTrfRightService;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private IStkTrfDtService stkTrfDtService;
	
	@Autowired
	private IStkTrfMetalDtService stkTrfMetalDtService;
	
	@Autowired
	private IStkTrfStnDtService stkTrfStnDtService;
	
	@Autowired
	private IStkTrfCompDtService stkTrfCompDtService;
	
	@Autowired
	private IStkTrfLabDtService stkTrfLabDtService;
	
	@Autowired
	private IHSNService hsnService;
	
	@Autowired
	private ILookUpMastService lookUpMastService;
	
	@ModelAttribute("stkTrfMt")
	public StkTrfMt stkTrfMt() {
		return new StkTrfMt();
	}
	
	
	
	@ModelAttribute("stkTrfDt")
	public StkTrfDt constructDt() {
		return new StkTrfDt();
	}
	
	@RequestMapping("/stkTrfMt")
	public String users(Model model, Principal principal) {
		
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stkTrfMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			return "stkTrfMt";
		} else {
			if (roleRights == null) {
				return "accesDenied";
			} else {
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());

			}
			return "stkTrfMt";
		}
	}
	
	
	@RequestMapping("/branchStkTrfMt")
	public String branchStkTrfMt(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("branchStkTrfMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			return "branchStkTrfMt";
		} else {
			if (roleRights == null) {
				return "accesDenied";
			} else {
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());

			}
			return "branchStkTrfMt";
		}
	}
	
	
	@RequestMapping("/brTrfPendApproval")
	public String brTrfPendApproval(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("brTrfPendApproval");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		Department department = departmentService.findByName("Marketing");
		model.addAttribute("locationMap", userDeptTrfRightService.getToDepartmentListFromUser(user.getId(), department.getId()));
		
		
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			return "brTrfPendApproval";
		} else {
			if (roleRights == null) {
				return "accesDenied";
			} else {
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());

			}
			return "brTrfPendApproval";
		}
	}
	
	@RequestMapping("/stkTrfPendApproval")
	public String stkTrfPendApproval(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stkTrfPendApproval");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		Department department = departmentService.findByName("Marketing");
		model.addAttribute("locationMap", userDeptTrfRightService.getToDepartmentListFromUser(user.getId(), department.getId()));
		
		
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			return "stkTrfPendApproval";
		} else {
			if (roleRights == null) {
				return "accesDenied";
			} else {
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());

			}
			return "stkTrfPendApproval";
		}
	}
	
	
	
	
	
	@RequestMapping("/stkTrfMt/listing")
	@ResponseBody
	public String stkTrfMtListing(Model model, @RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search, 
			@RequestParam(value = "tranType", required = false) String tranType,
			Principal principal)
			throws ParseException {

		return stkTrfMtService.stkTrfMtListing(limit, offset, sort, order, search, principal,tranType);

	}
	
	@RequestMapping("/stkTrfMt/add")
	public String add(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stkTrfMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		Department department = departmentService.findByName("Marketing");
		
		model.addAttribute("allPartyMap", partyService.getPartyList());
		model.addAttribute("locationMap", userDeptTrfRightService.getToDepartmentListFromUser(user.getId(), department.getId()));
		
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			model.addAttribute("canEditTranddate", "true");
			
		
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String curDate = dateFormat.format(date);
			model.addAttribute("currentDate", curDate);
			
			
			return "stkTrfMt/add";
			
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
		
		model.addAttribute("canEditTranddate", "false");
		return "stkTrfMt/add";
		}
	}
	
	
	@RequestMapping("/branchStkTrfMt/add")
	public String branchStkTrfMtAdd(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("branchStkTrfMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		Department department = departmentService.findByName("Marketing");
		
		model.addAttribute("allPartyMap", partyService.getPartyList());
		model.addAttribute("locationMap", userDeptTrfRightService.getToDepartmentListFromUser(user.getId(), department.getId()));
		model.addAttribute("hsnMap", hsnService.getHsnList());
		model.addAttribute("transportMap", lookUpMastService.getActiveLookUpMastByType("Transport Mode"));
		
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			model.addAttribute("canEditTranddate", "true");
			
		
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String curDate = dateFormat.format(date);
			model.addAttribute("currentDate", curDate);
			
			
			return "branchStkTrfMt/add";
			
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
		
		model.addAttribute("canEditTranddate", "false");
		return "branchStkTrfMt/add";
		}
	}
	
	
	
	@RequestMapping(value = "/stkTrfMt/add", method = RequestMethod.POST)
	public String addEditStkTrfMt(
			@Valid @ModelAttribute("stkTrfMt") StkTrfMt stkTrfMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal,
			@RequestParam(value = "pLocationIds", required = false) Integer pLocationIds,
			@RequestParam(value = "pToLocationIds", required = false) Integer pToLocationIds,
			@RequestParam(value = "vTranDate", required = false) String vTranDate) throws ParseException  {

		return stkTrfMtService.saveStkTrfMt(stkTrfMt, id, redirectAttributes, principal, result,pLocationIds,pToLocationIds,vTranDate);
		
	}
	
	
	
	@RequestMapping(value = "/branchStkTrfMt/add", method = RequestMethod.POST)
	public String addEditBranchStkTrfMt(
			@Valid @ModelAttribute("stkTrfMt") StkTrfMt stkTrfMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal,
			@RequestParam(value = "pLocationIds", required = false) Integer pLocationIds,
			@RequestParam(value = "pToLocationIds", required = false) Integer pToLocationIds,
			@RequestParam(value = "vTranDate", required = false) String vTranDate,
			@RequestParam(value = "vOtherCharges", required = false) Double vOtherCharges,
			@RequestParam(value = "vFinalPrice", required = false) Double vFinalPrice,
			@RequestParam(value = "pIgst", required = false) Double pIgst,
			@RequestParam(value = "pSgst", required = false) Double pSgst,
			@RequestParam(value = "pCgst", required = false) Double pCgst) throws ParseException  {

		return stkTrfMtService.saveBranchStkTrfMt(stkTrfMt, id, redirectAttributes, principal, result,pLocationIds,pToLocationIds,vTranDate,vOtherCharges,vFinalPrice,pIgst,pSgst,pCgst);
		
	}
	
	
	@RequestMapping("/branchStkTrfMt/edit/{id}")
	public String editBranchStkTrfMt(@PathVariable int id, Model model, Principal principal) {
		

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("branchStkTrfMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		StkTrfMt stkTrfMt = stkTrfMtService.findOne(id);
		model.addAttribute("stkTrfMt", stkTrfMt);
		
		Department department = departmentService.findByName("Marketing");
		
		model.addAttribute("allPartyMap", partyService.getPartyList());
		model.addAttribute("locationMap", userDeptTrfRightService.getToDepartmentListFromUser(user.getId(), department.getId()));
		
		Department departmentTo = departmentService.findOne(stkTrfMt.getLocation().getId());
		
		model.addAttribute("departmentToMap", userDeptTrfRightService.getToDepartmentListFromUser(user.getId(), departmentTo.getId()));
		model.addAttribute("transportMap", lookUpMastService.getActiveLookUpMastByType("Transport Mode"));
		
		model.addAttribute("hsnMap", hsnService.getHsnList());
		model.addAttribute("totalOtherCharges", stkTrfMt.getOtherCharges());
		model.addAttribute("finalPrice", stkTrfMt.getFinalPrice());
		
		model.addAttribute("pSgst", stkTrfMt.getSgst());
		model.addAttribute("pCgst", stkTrfMt.getCgst());
		model.addAttribute("pIgst", stkTrfMt.getIgst());
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String curDate = dateFormat.format(date);
		model.addAttribute("currentDate", curDate);
		
		userDeptTrfRightService.getToDepartmentListDropDown(
				user.getId(), department.getId(),"location.id");
	
		
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			model.addAttribute("canEditTranddate", "true");
			model.addAttribute("mtid", id);
			
			
			return "branchStkTrfMt/add";
			
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
		

		return "branchStkTrfMt/add";
		}
	}
	
	@RequestMapping("/stkTrfMt/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stkTrfMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		StkTrfMt stkTrfMt = stkTrfMtService.findOne(id);
		model.addAttribute("stkTrfMt", stkTrfMt);
		
		Department department = departmentService.findByName("Marketing");
		
		model.addAttribute("allPartyMap", partyService.getPartyList());
		model.addAttribute("locationMap", userDeptTrfRightService.getToDepartmentListFromUser(user.getId(), department.getId()));
	
		Department departmentTo = departmentService.findOne(stkTrfMt.getLocation().getId());
		
		model.addAttribute("departmentToMap", userDeptTrfRightService.getToDepartmentListFromUser(user.getId(), departmentTo.getId()));
		
		userDeptTrfRightService.getToDepartmentListDropDown(
				user.getId(), department.getId(),"location.id");
		
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
			model.addAttribute("canEditTranddate", "true");
			model.addAttribute("mtid", id);
			
			
			return "stkTrfMt/add";
			
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
		

		return "stkTrfMt/add";
		}
	}
	
	@RequestMapping(value="/stkTrfMt/addBarcodeItem",method = RequestMethod.POST)
	@ResponseBody
	public String addBarcodeItem(@RequestParam(value = "barcode") String barcode,
			@RequestParam(value = "mtId") Integer mtId, 
			@RequestParam(value = "tranType", required = false) String tranType, 
			Principal principal) {
		
		
		synchronized (this) {
			
			return 	stkTrfDtService.addStkTrfBarcodeItem(mtId, barcode, principal,tranType);
		}
		
	}
	
	@RequestMapping("/stkTrfDt/listing")
	@ResponseBody
	public String stkTrfDtList(@RequestParam(value = "mtId", required = false) Integer mtId,
			@RequestParam(value = "disableFlg", required = false) String disableFlg){
		
		return stkTrfDtService.stkTrfDtListing(mtId,disableFlg);
		
	
}	
	
	@RequestMapping("/stkTrfCompDt/listing")
	@ResponseBody
	public String consigCompDtList(@RequestParam(value = "stkTrfDtId", required = false) Integer stkTrfDtId,
			@RequestParam(value = "disableFlg", required = false) String disableFlg){
		
	return stkTrfCompDtService.stkTrfCompDtListing(stkTrfDtId,disableFlg);	
	
}
	
	@RequestMapping("/stkTrfMetalDt/listing")
	@ResponseBody
	public String stkTrfMetalDtList(@RequestParam(value = "stkTrfDtId", required = false) Integer stkTrfDtId){
		
		return stkTrfMetalDtService.stkTrfMetalDtListing(stkTrfDtId);
	
}
	
	@RequestMapping("/stkTrfStnDt/listing")
	@ResponseBody
	public String consigStnDtList(@RequestParam(value = "stkTrfDtId", required = false) Integer stkTrfDtId,
			@RequestParam(value = "disableFlg", required = false) String disableFlg){
		
	return stkTrfStnDtService.stkTrfStnDtListing(stkTrfDtId,disableFlg);
		
	
}
	
	@RequestMapping("/stkTrfLabDt/listing")
	@ResponseBody
	public String consigLabDtList(@RequestParam(value = "stkTrfDtId", required = false) Integer stkTrfDtId,
			@RequestParam(value = "disableFlg", required = false) String disableFlg){
		
		return stkTrfLabDtService.stkTrfLabDtListing(stkTrfDtId,disableFlg);
		
}
	
	@RequestMapping("/stkTrfMt/tolocationDropdown")
	@ResponseBody
	public String popDeptTo(@RequestParam(value = "locationId", required = false) Integer locationId,Principal principal) {
		User user = userService.findByName(principal.getName());

		Department department = departmentService.findOne(locationId);
		
		return userDeptTrfRightService.getToDepartmentListDropDown(
				user.getId(), department.getId(),"location.id");
	}
	
	
	
	@ResponseBody
	@RequestMapping("/stkTrfDt/delete/{id}")
	public String delete(@PathVariable int id,@RequestParam(value = "tranType", required = false) String tranType){
		
		System.out.println("tranType   "+tranType);
		
		String retVal = stkTrfDtService.deleteStkTrfDt(id,tranType);
		
		return retVal;
		
	}
	
	
	@ResponseBody
	@RequestMapping("/stkTrfMt/delete/{id}")
	public String deleteMt(@PathVariable int id,@RequestParam(value = "tranType", required = false) String tranType){
		String retVal = "-1";
		try {
			retVal = stkTrfMtService.deleteStkTrfMt(id,tranType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return retVal;
		
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("/stkTrfMt/dtSummary")
	public String getDtItemSummary(
			@RequestParam(value="mtId")Integer mtId,Principal principal){
		
	
		return stkTrfMtService.getDtItemSummary(mtId);
	
	}
	
	@ResponseBody
	@RequestMapping("/branchStkTrfMt/branchWiseGst")
	public String branchWiseGst(
			@RequestParam(value="locationId")Integer locationId,Principal principal,@RequestParam(value="toLocationId")Integer toLocationId){
		
	
		return stkTrfMtService.getBranchWiseGst(locationId,toLocationId);
	
	}
	
	
	@ResponseBody
	@RequestMapping("/branchStkTrfMt/addSummaryDetails")
	public String addSummaryDetails(
			@RequestParam(value="mtId")Integer mtId,
			@RequestParam(value="fob", required = false) Double fob,
			@RequestParam(value="sgst", required = false) Double sgst,
			@RequestParam(value="cgst", required = false) Double cgst,
			@RequestParam(value="igst", required = false) Double igst,
			@RequestParam(value="otherCharges", required = false) Double otherCharges,
			@RequestParam(value="finalPrice", required = false) Double finalPrice,
			
			Principal principal){
		
		String retVal ="-1";
		try {
			retVal = stkTrfMtService.addSummaryDetails(mtId, fob, sgst, cgst, igst, otherCharges,finalPrice, principal);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return retVal;
	}
	
	
	@ResponseBody
	@RequestMapping("/stkTrf/pendingApprovalList")
	public String pendingApprovalList(
			@RequestParam(value="locationId")Integer locationId,
			@RequestParam(value="trantype")String tranType,
			Principal principal){
		
		String retVal ="-1";
		try {
			
			retVal = stkTrfMtService.getStkTrfPendingList(locationId,tranType);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return retVal;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/stkTrfPendingApproval" , method=RequestMethod.POST)
	public String stkTrfPendingApproval(@Valid @ModelAttribute("stkTrfMt") StkTrfMt stkTrfMt,
			BindingResult result,
			Principal principal, RedirectAttributes redirectAttributes,
			@RequestParam(value = "pDtIds", required = false) String pDtIds){
		
		String retVal= "-1";
		synchronized (this) {
			
			retVal = stkTrfMtService.savePendingApproval(pDtIds,principal);
			
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
