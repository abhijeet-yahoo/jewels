package com.jiyasoft.jewelplus.controller.manufacturing.masters;

import java.security.Principal;
import java.util.List;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Employee;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IEmployeeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILookUpMastService;

@RequestMapping("/manufacturing/masters")
@Controller
public class EmployeeController {

	@Autowired
	private IDepartmentService departmentService;;

	@Autowired
	private IEmployeeService employeeService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private ILookUpMastService lookUpMastService;

	@ModelAttribute("employee")
	public Employee construct() {
		return new Employee();
	}

	@RequestMapping("/employee")
	public String users(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("employee");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
	
			return "employee";
		}else
		
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}

		return "employee";
	}

	@RequestMapping("/employee/listing")
	@ResponseBody
	public String employeeListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "id", required = false) Integer id,
			Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<Employee> employees = null;
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("employee");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

		employees = employeeService.searchAll(limit, offset, sort, order, search, false);

		sb.append("{\"total\":").append(employees.getTotalElements()).append(",\"rows\": [");

		for (Employee employee : employees) {
			
			sb.append("{\"id\":\"")
					.append(employee.getId())
					.append("\",\"deptName\":\"")
					.append((employee.getDepartment() != null ? employee.getDepartment().getName() : ""))		
					.append("\",\"designation\":\"")
					.append((employee.getLookUpMast() != null ?employee.getLookUpMast().getFieldValue() : ""))
					.append("\",\"name\":\"")
					.append(employee.getName())
					.append("\",\"code\":\"")
					.append(employee.getCode())
					.append("\",\"updatedBy\":\"")
					.append((employee.getModiBy() == null ? "" : employee.getModiBy()))
					.append("\",\"updatedDt\":\"")
					.append((employee.getModiDt() == null ? "" : employee.getModiDt()))
					.append("\",\"deactive\":\"")
					.append((employee.getDeactive() == null ? "" : (employee.getDeactive() ? "Deactive" : "Active")))
					.append("\",\"deactiveDt\":\"")
					.append((employee.getDeactiveDt() == null ? "" : employee.getDeactiveDt()));
			
			if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
					userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
				
				sb.append("\",\"action1\":\"");
					sb.append("<a href='/jewels/manufacturing/masters/employee/edit/")
							.append(employee.getId()).append(".html'")
							.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
				sb.append("\",\"action2\":\"");
					sb.append(
							"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/employee/delete/")
							.append(employee.getId()).append(".html'").append(" class='btn btn-xs btn-danger triggerRemove")
							.append(employee.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
							.append("\"},");

			}else{
				sb.append("\",\"action1\":\"");
				if (roleRights.getCanEdit()) {
					sb.append("<a href='/jewels/manufacturing/masters/employee/edit/")
							.append(employee.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {
					sb.append(
							"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/employee/delete/")
							.append(employee.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-danger triggerRemove")
						.append(employee.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
						.append("\"},");

			}
			

					
			
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}

	@RequestMapping("/employee/add")
	public String add(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("employee");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		model.addAttribute("designationMap", lookUpMastService.getActiveLookUpMastByType("Designation"));
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("departmentList",departmentService.getDepartmentList());
			
			return "employee/add";

		}else	

		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}
		
		model.addAttribute("departmentList",departmentService.getDepartmentList());

		return "employee/add";
	}

	@RequestMapping(value = "/employee/add", method = RequestMethod.POST)
	public String addEditEmployee(
			@Valid @ModelAttribute("employee") Employee employee,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "redirect:/manufacturing/masters/employee/add.html";

		if (result.hasErrors()) {
			return "employee/add";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			employee.setCreatedBy(principal.getName());
			employee.setCreatedDate(new java.util.Date());
			employee.setDeactive(false);
		} else {
			employee.setModiBy(principal.getName());
			employee.setModiDt(new java.util.Date());

			if (employee.getDeactive() != employeeService.findOne(id)
					.getDeactive()) {
				employee.setDeactiveDt(new java.util.Date());
			} else {
				employee.setDeactiveDt(employeeService.findOne(id)
						.getDeactiveDt());
			}
			employee.setId(id);

			action = "updated";
			retVal = "redirect:/manufacturing/masters/employee.html?id="+employee.getDepartment().getId();
		}
		
		if(employee.getLookUpMast().getId() == null) {
			employee.setLookUpMast(null);
		}
		
		
		employeeService.save(employee);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;
	}

	@RequestMapping("/employee/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		Employee employee = employeeService.findOne(id);
		model.addAttribute("employee", employee);
		model.addAttribute("departmentList",departmentService.getDepartmentList());
		
		model.addAttribute("designationMap", lookUpMastService.getActiveLookUpMastByType("Designation"));

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("employee");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("departmentList",departmentService.getDepartmentList());
			
			return "employee/add";

		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}

		return "employee/add";
	}

	@RequestMapping("/employee/delete/{id}")
	public String delete(@PathVariable int id) {
		employeeService.delete(id);

		return "redirect:/manufacturing/masters/employee.html";
	}
	

	@RequestMapping("/employeeAvailable")
	@ResponseBody
	public String userAvailable(@RequestParam String name,
			@RequestParam Integer id,
			@RequestParam Integer deptId) {
		Boolean employeeAvailable = true;

		name = name.trim();
				
		Department department = departmentService.findOne(deptId);

		if (id == null) {
			employeeAvailable = (employeeService.findByNameAndDepartmentAndDeactive(name, department, false) == null);
		} else {
			Employee employee = employeeService.findOne(id);
			if (!(name.equalsIgnoreCase(employee.getName()))) {
				employeeAvailable = (employeeService.findByNameAndDepartmentAndDeactive(name, department, false) == null);
			}
		}

		return employeeAvailable.toString();
	}
	
	
	//--------Employee--List for Reports -----------//
	
	@RequestMapping("/employee/report/listing")
	@ResponseBody
	public String employeeReportListing(Model model,
			@RequestParam(value = "deptIds", required = false) String deptIds){
		
		StringBuilder sb = new StringBuilder();
		sb.append("{\"total\":").append(employeeService.count()).append(",\"rows\": [");
		
		List<Employee> employees = employeeService.findEmployeeByDepartment(deptIds);
		
		for(Employee employee:employees){
			
			sb.append("{\"id\":\"")
			.append(employee.getId())
			.append("\",\"name\":\"")
			.append((employee.getName() != null ? employee.getName() : ""))
			.append("\"},");
			
		}
		
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		
		return str;
		
	}
	
	
	@ResponseBody
	@RequestMapping("/employeeList")
	public String employeeList(Model model,
			@RequestParam(value = "depts", required = false) Integer depts){
		
		
		List<Employee> employees = employeeService.findActiveEmployees(depts);

		JSONObject jsonObject = new JSONObject();
		
		for(Employee employee:employees){
			jsonObject.put(employee.getName(), employee.getName());
		}
		
	
		return jsonObject.toString();
	}
	
	
	
	
	

}
