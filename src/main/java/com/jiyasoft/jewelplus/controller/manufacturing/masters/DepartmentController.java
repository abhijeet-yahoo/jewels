package com.jiyasoft.jewelplus.controller.manufacturing.masters;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IBranchService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILocationRightsService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IUserDeptTrfRightService;

@RequestMapping("/manufacturing/masters")
@Controller
public class DepartmentController {
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private IUserDeptTrfRightService userDeptTrfRightService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private ILocationRightsService locationRightsService;
	
	@Autowired
	private IBranchService branchService;

	@ModelAttribute("department")
	public Department construct() {
		return new Department();
	}

	@RequestMapping("/department")
	public String users(Model model,Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("department");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
	
			return "department";
		}else
	
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}

		return "department";
	}

	@RequestMapping("/department/listing")
	@ResponseBody
	public String departmentListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "opt", required = true) String opt,
			Principal principal) {


		StringBuilder sb = new StringBuilder();
		Page<Department> departments = null;

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("department");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;	
		}


		


		

	
		
		
		if(opt.equals("3")){
			
			List<Department> locationDept = null;
			
			/*
			 * if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
			 * userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") ||
			 * userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			 * 
			 * locationDept =
			 * departmentService.findByLooseMetalStkOrComponentStkOrStoneStkOrderByNameAsc(
			 * true, true, true);
			 * 
			 * for(Department department :locationDept) {
			 * System.out.println("name "+department.getName()); } //locationDept =
			 * departmentService.findActiveDepartments(); }else { locationDept =
			 * locationRightsService.findActiveLocationRightsSortByName(user.getId()); }
			 */
			locationDept = locationRightsService.findActiveLocationRightsSortByName(user.getId());	
			
		//	locationDept = departmentService.findByLooseMetalStkOrComponentStkOrStoneStkOrderByNameAsc(true, true, true);
			
			sb.append("{\"total\":").append(locationDept.size()).append(",\"rows\": [");
			
			for(Department locDept : locationDept){
			
				/*
				 * if(locDept.getLossBookDept().equals(true)){ continue; }
				 */
				
				sb.append("{\"id\":\"")
				.append(locDept.getId())
				.append("\",\"name\":\"")
				.append(locDept.getName())
				.append("\"},");
			}
			
		}else if (opt.equals("4")) {
			List<Department> departmentList=null;
			
			if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
					|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
				
				departmentList = departmentService.findActiveDepartments();
			}else {
				departmentList = userDeptTrfRightService.findActiveUserDeptSortByName(user.getId());	
			}
			
			
			
			sb.append("{\"total\":").append(departmentList.size()).append(",\"rows\": [");
			for (Department department : departmentList) {
				sb.append("{\"id\":\"")
				.append(department.getId())
				.append("\",\"name\":\"")
				.append(department.getName())
				.append("\"},");
			}
			
			
			
			
		}else if (opt.equals("5")) {
			
			Department department = departmentService.findByName("Marketing");
			List<Department> departmentList = userDeptTrfRightService.findActiveUserToDeptSortByName(user.getId(),department.getId());
			
			sb.append("{\"total\":").append(departmentList.size()).append(",\"rows\": [");
			for (Department dept : departmentList) {
				sb.append("{\"id\":\"")
				.append(dept.getId())
				.append("\",\"name\":\"")
				.append(dept.getName())
				.append("\"},");
			}
			
			
			
			
		}
		
		else{
			
			departments = departmentService.searchAll(limit, offset, sort, order, search, false);
			sb.append("{\"total\":").append(departments.getTotalElements()).append(",\"rows\": [");
				for (Department department : departments) {
					if (opt.equals("1")) {
						sb.append("{\"id\":\"")
								.append(department.getId())
								.append("\",\"name\":\"")
								.append(department.getName())
								.append("\",\"lossPerc\":\"")
								.append(department.getLossPerc() == null ?"":department.getLossPerc())
								.append("\",\"extraWt\":\"")
								.append(department.getExtraWt() == null ? "" : department.getExtraWt())	
								.append("\",\"expectedRecovery\":\"")
								.append(department.getExpectedRecovery()!=null ? department.getExpectedRecovery():"")
								.append("\",\"code\":\"")
								.append(department.getCode())
								.append("\",\"processSeq\":\"")
								.append(department.getProcessSeq() == null ? "": department.getProcessSeq())
								.append("\",\"process\":\"")
								.append(department.getProcess() == null ? "": department.getProcess())
								.append("\",\"wip\":\"")
								.append(department.getWip())
								.append("\",\"lossBookDept\":\"")
								.append(department.getLossBookDept())
								.append("\",\"barcodeFlg\":\"")
								.append(department.getBarcodeFlg())
								.append("\",\"stoneProd\":\"")
								.append(department.getStoneProd())
								.append("\",\"looseMetalStk\":\"")
								.append(department.getLooseMetalStk())
								.append("\",\"pcsProd\":\"")
								.append(department.getPcsProd())
								.append("\",\"compStk\":\"")
								.append(department.getComponentStk())
								.append("\",\"stoneStk\":\"")
								.append(department.getStoneStk())
								.append("\",\"castBal\":\"")
								.append(department.getCastBal())
								.append("\",\"deactive\":\"")
								.append(department.getDeactive() != null ? (department.getDeactive() ? "Deactive":"Active") : "")
								.append("\",\"branchMaster\":\"")
							    .append(department.getBranchMaster()!=null ? department.getBranchMaster().getName():"");
						
						if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
								userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
							
							sb.append("\",\"action1\":\"");
								sb.append("<a href='/jewels/manufacturing/masters/department/edit/")
								  .append(department.getId()).append(".html'")
						          .append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
							sb.append("\",\"action2\":\"");
								sb.append(
										"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/department/delete/")
										.append(department.getId()).append(".html'")
								        .append(" class='btn btn-xs btn-danger triggerRemove")
										.append(department.getId())
										.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
										.append("\"},");
		
						}else{
							sb.append("\",\"action1\":\"");
							if (roleRights.getCanEdit()) {
								sb.append("<a href='/jewels/manufacturing/masters/department/edit/")
									.append(department.getId()).append(".html'");
							} else {
								sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
							}
							sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
							sb.append("\",\"action2\":\"");
							if (roleRights.getCanDelete()) {
								sb.append(
										"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/department/delete/")
										.append(department.getId()).append(".html'");
							} else {
								sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
							}
							sb.append(" class='btn btn-xs btn-danger triggerRemove")
									.append(department.getId())
									.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
									.append("\"},");
	
						}
						
						
												
							
					} else if (opt.equals("2")) {
						sb.append("{\"name\":\"")
								.append("<a href='javascript:getChild(")
								.append(department.getId()).append(")'>")
								.append(department.getName()).append("</a>\"},");
					}
				}
		} //ending the first else-if

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		System.err.println("str    "+str);

		return str;

	}

	@RequestMapping("/department/add")
	public String add(Model model,Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("department");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		model.addAttribute("branchMasterMap",branchService.getBranchMasterList());
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "department/add";

		}else	

		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}
		
		return "department/add";
	}

	@RequestMapping(value = "/department/add", method = RequestMethod.POST)
	public String addEditUser(
			@Valid @ModelAttribute("department") Department department,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {
		
		
		String action = "added";
		String retVal = "redirect:/manufacturing/masters/department/add.html";

		if (result.hasErrors()) {
			System.out.println(result.getObjectName());
			return "department/add";

		}
		

		if(department.getBranchMaster().getId() == null) {
			department.setBranchMaster(null);
		}


		if (id == null || id.equals("") || (id != null && id == 0)) {
			department.setCreatedBy(principal.getName());
			department.setCreatedDate(new java.util.Date());
		} else {
			department.setId(id);
			department.setModiBy(principal.getName());
			department.setModiDt(new java.util.Date());
			action = "updated";
			retVal = "redirect:/manufacturing/masters/department.html";
		}

		departmentService.save(department);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;

	}

	@RequestMapping("/department/edit/{id}")
	public String edit(@PathVariable int id, Model model,Principal principal) {
		Department department = departmentService.findOne(id);
		model.addAttribute("department", department);
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("department");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		model.addAttribute("branchMasterMap",branchService.getBranchMasterList());
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "department/add";

		}else	
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}
		
		return "department/add";
	}

	@RequestMapping("/department/delete/{id}")
	public String delete(@PathVariable int id) {
		departmentService.delete(id);

		return "redirect:/manufacturing/masters/department.html";
	}

	
	@RequestMapping("/departmentCodeAvailable")
	@ResponseBody
	public String departmentCodeAvailable(@RequestParam String code,
			@RequestParam Integer id) {
		Boolean departmentAvailable = true;

		if (id == null) {
			departmentAvailable = (departmentService.findByCodeAndDeactive(code, false) == null);
		} else {
			Department department = departmentService.findOne(id);
			if (!(code.equalsIgnoreCase(department.getCode()))) {
				departmentAvailable = (departmentService.findByCodeAndDeactive(code, false) == null);
			}
		}

		return departmentAvailable.toString();
	}
	
	@RequestMapping("/departmentAvailable")
	@ResponseBody
	public String departmentAvailable(@RequestParam String name,
			@RequestParam Integer id) {
		Boolean departmentAvailable = true;

		if (id == null) {
			departmentAvailable = (departmentService.findByName(name) == null);
		} else {
			Department department = departmentService.findOne(id);
			if (!(name.equalsIgnoreCase(department.getName()))) {
				departmentAvailable = (departmentService.findByName(name) == null);
			}
		}

		return departmentAvailable.toString();
	}
	
	
	
	@RequestMapping("/department/dropDownlist")
	@ResponseBody
	public String deptList() {

		StringBuilder sb = new StringBuilder();
		Map<Integer, String> deptMap = departmentService.getDepartmentList();

		sb.append("<select id=\"deptDropDownId\" name=\"deptDropDownId\" class=\"form-control\" >");
		sb.append("<option value=\"\"> Select Department </option>");
		for (Object key : deptMap.keySet()) {
			sb.append("<option value=\"").append(key.toString()).append("\">")
					.append(deptMap.get(key)).append("</option>");
		}
		sb.append("</select>");

		return sb.toString();
	}
	
	@RequestMapping("/location/dropDownlist")
	@ResponseBody
	public String locationList(@RequestParam String mOpt,
			Principal principal) {
		
		User user =userService.findByName(principal.getName());

		StringBuilder sb = new StringBuilder();
		Map<Integer, String> locationMap = locationRightsService.getToLocationListFromUser(user.getId(), mOpt);
		
		System.out.println("size "+locationMap.size());

		sb.append("<select id=\"locationDropDownId\" name=\"locationDropDownId\" class=\"form-control\"  >");
		sb.append("<option value=\"\"> Select Location </option>");
		if(locationMap.size()==1) {
			for (Object key : locationMap.keySet()) {
				sb.append("<option selected=\"selected\" value=\"").append(key.toString()).append("\">")
						.append(locationMap.get(key)).append("</option>");
			}	
		}else {
			for (Object key : locationMap.keySet()) {
				sb.append("<option value=\"").append(key.toString()).append("\">")
						.append(locationMap.get(key)).append("</option>");
			}	
		}
		
		sb.append("</select>");

		return sb.toString();
	}
	
	
	@RequestMapping("/getToDepartmentListDropDownForAll")
	@ResponseBody
	public String getToDepartmentListDropDownForAll(Principal principal) {
		User user = userService.findByName(principal.getName());
		
		return departmentService.getToDepartmentListDropDownForAll(user.getId());
	}
	
	

}
