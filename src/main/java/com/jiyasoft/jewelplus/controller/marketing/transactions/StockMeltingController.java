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
import com.jiyasoft.jewelplus.domain.marketing.transactions.StockMeltingDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.StockMeltingMt;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IUserDeptTrfRightService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStockMeltingDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IStockMeltingMtService;

@RequestMapping("/marketing/transactions")
@Controller
public class StockMeltingController {
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private RoleRightsService roleRightsService;
	
	@Autowired
	private IUserDeptTrfRightService userDeptTrfRightService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private IStockMeltingMtService stockMeltingMtService;
	
	@Autowired
	private IStockMeltingDtService stockMeltingDtService;
	

	@ModelAttribute("stockMeltingMt")
	public StockMeltingMt constructMt() {
		return new StockMeltingMt();
	}
	
	
	@ModelAttribute("stockMeltingDt")
	public StockMeltingDt constructDt() {
		return new StockMeltingDt();
	}
	
	@RequestMapping("/stockMeltingTransfer")
	public String stockMeltingTransfer(Model model){
		
		return "stockMeltingTransfer";
	}
	
	@RequestMapping("/stockMeltingApproval")
	public String stockMeltingApproval(Model model){
		
		return "stockMeltingApproval";
	}
	
	
	@RequestMapping("/stockMeltingMt")
	public String users(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stockMeltingMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			return "stockMeltingMt";
		} else {
			if (roleRights == null) {
				return "accesDenied";
			} else {
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());

			}
			return "stockMeltingMt";
		}
	}
	
	
	@RequestMapping("/stockMeltingMt/listing")
	@ResponseBody
	public String stockMeltingMtListing(Model model, @RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search, Principal principal)
			throws ParseException {

		return stockMeltingMtService.stockMeltingMtListing(limit, offset, sort, order, search, principal);

	}
	
	
	
	
	
	@RequestMapping("/stockMeltingMt/add")
	public String add(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stockMeltingMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
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
			model.addAttribute("canEditTranddate", "true");
				
		
			
			return "stockMeltingMt/add";
			
		}else{
		
		if(roleRights == null){
			return "accesDenied";
		}else{

			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("canView", roleRights.getCanView());
			
		}
		
	
		return "stockMeltingMt/add";
		}
	}
	
	
	
	@RequestMapping(value = "/stockMeltingMt/add", method = RequestMethod.POST)
	public String addEditStockMeltingMt(
			@Valid @ModelAttribute("stockMeltingMt") StockMeltingMt stockMeltingMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal,
			@RequestParam(value = "pLocationIds", required = false) Integer pLocationIds,
			@RequestParam(value = "vTranDate", required = false) String vTranDate) throws ParseException  {

		return stockMeltingMtService.saveStockMeltingMt(stockMeltingMt, id, redirectAttributes, principal, result, vTranDate,pLocationIds);
		
	}
	
	
	@RequestMapping("/stockMeltingMt/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stockMeltingMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		StockMeltingMt stockMeltingMt = stockMeltingMtService.findOne(id);
		model.addAttribute("stockMeltingMt", stockMeltingMt);
		
		
		Department department = departmentService.findByName("Marketing");
		model.addAttribute("locationMap", userDeptTrfRightService.getToDepartmentListFromUser(user.getId(), department.getId()));
		
			
		model.addAttribute("mtid", id);
		
		
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			model.addAttribute("canEditTranddate", "true");
		
			
			
			return "stockMeltingMt/add";
			
		}else{
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}

		
		model.addAttribute("canEditTranddate", "false");
		

		return "stockMeltingMt/add";
		}
	}
	
	
	@RequestMapping("/stockMeltingTransfer/listing")
	@ResponseBody
	public String repairIssDtAppList(@RequestParam(value = "deptId", required = false) Integer deptId){
		
		return stockMeltingMtService.stockMeltingTransferListing(deptId);
	}
	
	@RequestMapping("/stockTransferToMelting")
	@ResponseBody
	public String stockTransferToMelting(@RequestParam(value = "stockMeltMtId", required = false) Integer stockMeltMtId,
			@RequestParam(value = "barcode", required = false) String barcode,
			Principal principal) throws ParseException {

		String retVal = "-1";
	
	
		
		synchronized (this) {
			
			try {
				retVal =stockMeltingDtService.stockMeltingTransfer(principal, barcode, stockMeltMtId);	
				
			} catch (Exception e) {
				System.out.println(e);
			}
			
		}
		
	return retVal;

	}
	
	
	@RequestMapping("/stockTransferToMeltingFromPickup")
	@ResponseBody
	public String stockTransferToMeltingFromPickup(@RequestParam(value = "stockMeltMtId", required = false) Integer stockMeltMtId,
			@RequestParam(value = "vBarcodeIds", required = false) String vBarcodeIds,
			Principal principal) throws ParseException {

		String retVal = "-1";
	
		synchronized (this) {
			
			try {
				
				String[] barcode = vBarcodeIds.split(",");
				for (int i = 0; i < barcode.length; i++) {
					retVal =stockMeltingDtService.stockMeltingTransfer(principal, barcode[i], stockMeltMtId);	
				}
				
			} catch (Exception e) {
				System.out.println(e);
			}
			
		}
		
	return retVal;

	}
	
	
	@RequestMapping("/stockMeltingApproval/save")
	@ResponseBody
	public String stockMeltingApproval(@RequestParam(value = "vDtId", required = false) String vDtId,
			Principal principal) throws ParseException {

		String retVal = "-1";
	
	
		
		synchronized (this) {
			
			try {
				retVal =stockMeltingDtService.stockMeltingApproval(principal, vDtId);	
				
			} catch (Exception e) {
				System.out.println(e);
			}
			
		}
		
	return retVal;

	}
	
	
	
	
	@RequestMapping("/stockMeltingDt/listing")
	@ResponseBody
	public String stockMeltingDtList(@RequestParam(value = "mtId", required = false) Integer mtId,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search	){
					
		return stockMeltingDtService.stockMeltingDtListing(limit,offset,sort,order,search,mtId);
		
	
}	
	
	@RequestMapping("/stockMeltingApproval/listing")
	@ResponseBody
	public String stockMeltingApproval(@RequestParam(value = "approvalFlg", required = false) Boolean approvalFlg){
					
		return stockMeltingDtService.stockMeltingPickupListing(approvalFlg);
		
	
}
	
	@ResponseBody
	@RequestMapping("/stockMeltingDt/delete/{id}")
	public String delete(@PathVariable int id,Principal principal){
		
		String retVal = stockMeltingDtService.stockMeltingDtDelete(id,principal);
		
		return retVal;
		
	}
	
	
	@ResponseBody
	@RequestMapping("/stockMeltingMt/delete/{id}")
	public String deleteMt(@PathVariable int id,Principal principal){
		String retVal = "-1";
		try {
			retVal = stockMeltingMtService.stockMeltingMtDelete(id,principal);
		} catch (Exception e) {
			e.printStackTrace();
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
