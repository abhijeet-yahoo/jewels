package com.jiyasoft.jewelplus.controller.manufacturing.masters;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.UserDeptTrfRight;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IUserDeptTrfRightService;

@RequestMapping("/manufacturing/masters")
@Controller
public class UserDeptTrfRightController {

	@Autowired
	private IUserDeptTrfRightService userDeptTrfRightService;

	@Autowired
	private UserService userService;

	@Autowired
	private IDepartmentService departmentService;

	@ModelAttribute("userDeptTrfRight")
	public UserDeptTrfRight construct() {
		return new UserDeptTrfRight();
	}

	@RequestMapping("/userDeptTrfRight")
	public String userDeptTrf(Model model) {
		// model.addAttribute("userDeptTrfRight",userDeptTrfRightService.findAll());
		model = populateModel(model);
		return "userDeptTrfRight";
	}
	
	
	@RequestMapping("/userDeptTrfRight/listing")
	@ResponseBody
	public String userDeptTrfRightListing(
			Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "userId", required = false) Integer userId,
			@RequestParam(value = "deptId", required = false) Integer deptId,
			Principal principal) {
		
		User user = userService.findOne(userId);
		Department department = departmentService.findOne(deptId);
		
		StringBuilder sb = new StringBuilder();

		sb.append("{\"total\":").append(userDeptTrfRightService.count())
				.append(",\"rows\": [");
		
		List<UserDeptTrfRight> userDeptTrfRights = userDeptTrfRightService.findByUserAndDepartmentAndDeactive(user, department, false);

		for (UserDeptTrfRight userDeptTrfRight : userDeptTrfRights) {

			sb.append("{\"id\":\"")
					.append(userDeptTrfRight.getId())
					.append("\",\"userName\":\"")
					.append((userDeptTrfRight.getUser() == null ? "" : userDeptTrfRight.getUser().getName()))
					.append("\",\"fromDept\":\"")
					.append((userDeptTrfRight.getDepartment() == null ? "" : userDeptTrfRight.getDepartment().getName()))
					.append("\",\"toDept\":\"")
					.append((userDeptTrfRight.getToDeptId() == null ? "" : userDeptTrfRight.getToDeptId().getName()))
					.append("\",\"deactive\":\"")
					.append(userDeptTrfRight.getDeactive() ? "deactive":"active")
					.append("\",\"action2\":\"")
					.append("<a href='javascript:deleteUserDeptTransfer(")
					.append(userDeptTrfRight.getId())
					.append(");' class='btn btn-xs btn-danger triggerRemove' ><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
					.append("\"},");
					
					/*
					 .append("\",\"action2\":\"")
					.append("<a href='javascript:deleteUserDeptTransfer(")
					.append(userDeptTrfRight.getId())
					.append(".html' class='btn btn-xs btn-danger triggerRemove")
					.append(userDeptTrfRight.getId())
					.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
					.append("\"},");*/
					
					
					
			/*		.append("\",\"action2\":\"")
					.append("<a onClick='javascript:doDelete(event, this)' href='/manufacturing/masters/userDeptTrfRight/delete/")
					.append(userDeptTrfRight.getId())
					.append(".html' class='btn btn-xs btn-danger triggerRemove")
					.append(userDeptTrfRight.getId())
					.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
					.append("\"},");
					*/
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
		

	}	

	@RequestMapping("/userDeptTrfRight/add")
	public String addUserDeptTrfRight(Model model) {
		return "userDeptTrfRight/add";
	}

	public Model populateModel(Model model) {

		model.addAttribute("departmentMap",departmentService.getDepartmentList());
		model.addAttribute("userMap",userService.getUserList());
		return model;

	}
	
	
	@RequestMapping(value = "/userDeptTrfRight/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditUser(
			@Valid @ModelAttribute("userDeptTrfRight") UserDeptTrfRight userDeptTrfRight,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "vUserId") Integer vUserId,
			@RequestParam(value = "vfromDeptId") Integer vfromDeptId,
			@RequestParam(value = "vToDeptId") Integer vToDeptId,
			RedirectAttributes redirectAttributes, Principal principal) {
		
		String action = "added";
		String retVal = "1";

		if (result.hasErrors()) {
			return "userDeptTrfRight/add";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			userDeptTrfRight.setCreatedBy(principal.getName());
			userDeptTrfRight.setCreatedDt(new java.util.Date());
		
			System.out.println("printing the TOOOODEPTTTTTT = "+ vToDeptId);
			System.out.println("printing the userId = "+ vUserId);
			System.out.println("printing the departmentId = "+ vfromDeptId);
			
			User user = userService.findOne(vUserId);
			Department department = departmentService.findOne(vfromDeptId);
			//Department toDepartment  = departmentService.findOne(vToDeptId);
			
			userDeptTrfRight.setUser(user);
			userDeptTrfRight.setDepartment(department);
			
			List<UserDeptTrfRight> userdept = userDeptTrfRightService.findByUserAndDepartmentAndDeactive(user, department, false);
			for(UserDeptTrfRight userDeptTrf : userdept)
			{
				System.out.println("in for loop????????????");
				
				if(userDeptTrf.getToDeptId().getId() == vToDeptId)
				{
					return retVal = "-1";
				}
				
				if(userDeptTrf.getDepartment().getId() == vToDeptId)
				{
					return retVal = "-1";
				}
				
				
			}
			
			
		} else {
			userDeptTrfRight.setId(id);
			userDeptTrfRight.setModiBy(principal.getName());
			userDeptTrfRight.setModiDt(new java.util.Date());
			action = "updated";
			retVal = "1";
		}
		
		userDeptTrfRightService.save(userDeptTrfRight);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);	
		
		return retVal;
		
	}
	
	@RequestMapping("/userDeptTrfRight/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable int id) {
		userDeptTrfRightService.delete(id);

		return "1";
	}

}
