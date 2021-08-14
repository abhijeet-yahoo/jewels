package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VoucherTrfMt;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IUserDeptTrfRightService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IVoucherTrfMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class VoucherTrfMtController {

	@Autowired
	private IVoucherTrfMtService voucherTrfMtService;
	
	@Autowired
	private RoleRightsService roleRightsService;
	
	@Autowired
	private MenuMastService menuMastService;
	
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private UserService userService; 
	
	@Autowired
	private IUserDeptTrfRightService userDeptTrfRightService;
	
	@ModelAttribute("voucherTrfMt")
	public VoucherTrfMt construct() {
		
		return new VoucherTrfMt();
	}
	
	@RequestMapping("/voucherTrfMt")
	public String users(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("voucherTrfMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}
		
		
		return "voucherTrfMt";
	}
	
	
	
	
	@RequestMapping("/voucherTrfMt/listing")
	@ResponseBody
	public String processMastListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			Principal principal) {
		
		StringBuilder sb = new StringBuilder();
		Page<VoucherTrfMt> voucherTrfMt = null;

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("voucherTrfMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

		Long rowCount = null;
		
		
		  voucherTrfMt = voucherTrfMtService.searchAll(limit, offset, sort, order,search,false);
		 
		
		sb.append("{\"total\":").append(rowCount).append(",\"rows\": [");

		for (VoucherTrfMt vouchertrfMt: voucherTrfMt) {
		
			
				sb.append("{\"id\":\"")
				.append(vouchertrfMt.getId())
				.append("\",\"voucherNo\":\"")
				.append((vouchertrfMt.getVoucherno() != null ? vouchertrfMt.getVoucherno() : ""))
				.append("\",\"voucherDate\":\"")
				.append(vouchertrfMt.getVoucherno())
				.append("\",\"bagNo\":\"")
				.append(vouchertrfMt.getBagMt() !=null ? vouchertrfMt.getBagMt().getName() :"")
				.append("\",\"deptFrom\":\"")
				.append((vouchertrfMt.getDeptFrom() != null ? vouchertrfMt.getDeptFrom().getName() : ""))
				.append("\",\"deptFrom\":\"")
				.append((vouchertrfMt.getDeptTo() != null ? vouchertrfMt.getDeptTo().getName() : ""))
				.append("\",\"action1\":\"")
				.append("<a href='/jewels/manufacturing/transactions/voucherTrfMt/edit/")
				.append(vouchertrfMt.getId())
				.append(".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\",\"action2\":\"")
				.append("<a href='javascript:deleteVoucherTrf(event,")
				.append(vouchertrfMt.getId())
				.append(");' class='btn btn-xs btn-danger'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
				.append("\"},");
			
			
	
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
		
	}
	
	@RequestMapping("/voucherTrfMt/add")
	public String add(Model model,Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("voucherTrfMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("departmenttMap",userDeptTrfRightService.getDepartmentListFromUser(user.getId()));
		}
		
		return "voucherTrfMt/add";
	}
	
	
	@RequestMapping("/voucherTrfMt/delete/{id}")
	public String delete(@PathVariable int id) {
		voucherTrfMtService.delete(id);
		return "redirect:/manufacturing/transactions/vouchertrf.html";
	}
	
	
	
}
