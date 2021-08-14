package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BarcodeDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BarcodeMt;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBarcodeDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBarcodeMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class BarcodeMtController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private MenuMastService menuMastService;

	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private IBarcodeMtService barcodeMtService;

	@Autowired
	private IDepartmentService departmentService;

	@ModelAttribute("barcodeMt")
	public BarcodeMt constructMt() {
		return new BarcodeMt();
	}

	@ModelAttribute("barcodeDt")
	public BarcodeDt constructDt() {
		return new BarcodeDt();
	}

	@RequestMapping("/barcodeMt")
	public String users(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("barcodeMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			return "barcodeMt";
		} else {
			if (roleRights == null) {
				return "accesDenied";
			} else {
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());

			}
			return "barcodeMt";
		}
	}

	@RequestMapping("/barcodeMt/listing")
	@ResponseBody
	public String barcodeMtListing(Model model, @RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search, Principal principal)
			throws ParseException {

		return barcodeMtService.barcodeMtListing(limit, offset, sort, order, search, principal);
	}

	@RequestMapping("/barcodeMt/add")
	public String add(Model model, Principal principal) {

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("barcodeMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {

			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			model.addAttribute("canEditTranddate", "false");
			model.addAttribute("deptMap", departmentService.getDepartmentList());

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String curDate = dateFormat.format(date);
			model.addAttribute("currentDate", curDate);

			return "barcodeMt/add";

		} else {

			if (roleRights == null) {
				return "accesDenied";
			} else {
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
			return "barcodeMt/add";
		}
		
	}

	@RequestMapping("/barcodeMt/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("barcodeMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		BarcodeMt barcodeMt = barcodeMtService.findOne(id);
		model.addAttribute("barcodeMt", barcodeMt);

		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {

			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			model.addAttribute("canEditTranddate", "true");
			model.addAttribute("mtid", id);
			model.addAttribute("deptMap", departmentService.getDepartmentList());

			return "barcodeMt/add";

		} else {

			if (roleRights == null) {
				return "accesDenied";
			} else {
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
			}

			model.addAttribute("mtid", id);
			model.addAttribute("canEditTranddate", "false");

			return "barcodeMt/add";
		}
	}

	@RequestMapping(value = "/barcodeMt/add", method = RequestMethod.POST)
	public String addEditBarcodeMt(@Valid @ModelAttribute("barcodeMt") BarcodeMt barcodeMt, BindingResult result,
			@RequestParam(value = "id") Integer id, RedirectAttributes redirectAttributes, Principal principal) {

		return barcodeMtService.saveBarcodeMt(barcodeMt, id, redirectAttributes, principal, result);

	}

	@RequestMapping("/barcodeDt/listing")
	@ResponseBody
	public String barcodeDtListing(@RequestParam(value = "mtId", required = false) Integer mtId) throws ParseException {
		

		return barcodeMtService.barcodeDtListing(mtId);
	}


	@RequestMapping("/barcode/department/dropDownlist")
	@ResponseBody
	public String deptList() {
		
		StringBuilder sb = new StringBuilder();
		
		
		Map<Integer, String> deptMap = departmentService.getDepartmentListforBarcode();
		
		sb.append("<select id=\"barcodeFlgId\" name=\"barcodeFlgId\" class=\"form-control\" onChange=\"javascript:popBagDt()\">");
		sb.append("<option value=\"\"> Select Department</option>");
		for (Object key : deptMap.keySet()) {
			sb.append("<option value=\"").append(key.toString()).append("\">")
					.append(deptMap.get(key)).append("</option>");
		}
		sb.append("</select>");

		return sb.toString();
	}
	
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
