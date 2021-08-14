package com.jiyasoft.jewelplus.controller.admin;

import java.security.Principal;
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
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;

@RequestMapping("/admin")
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
		
	@Autowired
	private MenuMastService menuMastService;

	@Autowired
	private RoleRightsService roleRightsService;
	

	@Autowired
	private UserRoleService userRoleService;

	@ModelAttribute("user")
	public User construct() {
		return new User();
	}

	@RequestMapping("/users")
	public String users(Model model, Principal principal) {
		/*User loginUser = userService.findByName(principal.getName());
		//model.addAttribute("canInsert", loginUser.getCanInsert());

		model.addAttribute("users", userService.findAll());*/
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("users");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", false);
		}
		

		return "users";
	}

	@RequestMapping("/users/listing")
	@ResponseBody
	public String userListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<User> users = null;

		
		if ((search != null) && (search.trim().length() == 0)) {
			search = null;	
		}

		/*Long rowCount = null;

		// default search would be name wise
		if (search == null && sort == null) {
			rowCount = userService.count(sort, search, false);
			users = userService.findByName(limit, offset, sort, order, search, false);
		} else if (search != null && sort != null && sort.equalsIgnoreCase("name")) {
			rowCount = userService.count(sort, search, false);
			users = userService.findByName(limit, offset, sort, order, search, false);
		} else if (search != null && sort != null) {
			rowCount = 0L;
			users = new PageImpl<User>(new ArrayList<User>());
			
		} else {
			rowCount = userService.count(sort, search, false);
			users = userService.findByName(limit, offset, sort, order, search, false);
		}*/
		
		users = userService.searchAll(limit, offset, sort, order, search, false);
		

		sb.append("{\"total\":").append(users.getTotalElements())
				.append(",\"rows\": [");
		for (User user : users) {
			
			
			sb.append("{\"id\":\"")
				.append(user.getId())
				.append("\",\"name\":\"")
				.append(user.getName())
				.append("\",\"userRole\":\"")
				.append(user.getRoleMast()==null?"":user.getRoleMast().getRoleNm())
				.append("\",\"password\":\"")
				.append(user.getPassword())
				.append("\",\"updatedBy\":\"")
				.append((user.getModiBy() == null ? "" : user.getModiBy()))
				.append("\",\"updatedDt\":\"")
				.append((user.getModiDt() == null ? "" : user.getModiDt()))
				.append("\",\"deactive\":\"")
				.append((user.getDeactive() == null ? "" : (user.getDeactive() ? "Deactive" : "Active")))
				.append("\",\"deactiveDt\":\"")
				.append((user.getDeactiveDt() == null ? "" : user.getDeactiveDt()))
				.append("\",\"action1\":\"")
				.append("<a href='/jewels/admin/user/edit/").append(user.getId())
				.append(".html'")
				.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
				.append("\"},");

			/*sb.append("\",\"action1\":\"");
			if (loginUser.getCanUpdate()) {
				sb.append("<a href='/jewels/admin/user/edit/").append(user.getId())
						.append(".html'");
			} else {
				sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
			}
			sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

			sb.append("\",\"action2\":\"");
			if (loginUser.getCanDelete()) {
				sb.append(
						"<a onClick='javascript:doDelete(event, this)' href='/jewels/admin/user/delete/")
						.append(user.getId()).append(".html'");
			} else {
				sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
			}
			sb.append(" class='btn btn-xs btn-danger triggerRemove")
					.append(user.getId())
					.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
					.append("\"},");*/
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";

		return str;
	}

	@RequestMapping("/user/register")
	public String userRegister(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("users");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", false);
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}

		return "user/register";
	}

	

	
	
	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	public String addEditUser(@Valid @ModelAttribute("user") User user,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "redirect:/admin/user/register.html";

		if (result.hasErrors()) {
			return "user/register";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			user.setCreatedBy(principal.getName());
			user.setCreateDate(new java.util.Date());
			user.setDeactive(false);
			
			userService.save(user); // add save
		} else {
			user.setModiBy(principal.getName());
			user.setModiDt(new java.util.Date());

			if (user.getDeactive() != userService.findOne(id).getDeactive()) {
				user.setDeactiveDt(new java.util.Date());
			} else {
				user.setDeactiveDt(userService.findOne(id).getDeactiveDt());
			}
			user.setId(id);
			

			System.out.println(id);
			
			if(id == 1){
				if(user.getName().equalsIgnoreCase("admin")){
					userService.save(user); // edit save
					action = "updated";
					retVal = "redirect:/admin/users.html";
				}else{
					action = "NOT UPDATED";
				}
			}else{
				userService.save(user); // edit save
				retVal = "redirect:/admin/users.html";
				action = "updated";
			}
			
			
		}
		
		//userService.save(user);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;
	}

	@RequestMapping("/user/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		User user = userService.findOne(id);
		model.addAttribute("user", user);
		//model.addAttribute("canUpdate", loginUser.getCanUpdate());
		//model.addAttribute("canDelete", loginUser.getCanDelete());

		return "user/register";
	}

	@RequestMapping("/user/delete/{id}")
	public String delete(@PathVariable int id) {
		userService.delete(id);

		return "redirect:/admin/users.html";
	}

	@RequestMapping("/userAvailable")
	@ResponseBody
	public String userAvailable(@RequestParam String name,
			@RequestParam Integer id) {
		Boolean userAvailable = true;

		name = name.trim();

		if (id == null) {
			userAvailable = (userService.findByName(name) == null);
		} else {
			User user = userService.findOne(id);
			if (!(name.equalsIgnoreCase(user.getName()))) {
				userAvailable = (userService.findByName(name) == null);
			}
		}

		return userAvailable.toString();
	}
	
	
	
	@RequestMapping("/user/passwordReset")
	public String userResetPassword(Model model,Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("passwordReset");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		model.addAttribute("userId", user.getId());
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			
			return "user/passwordReset";
		}else
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}
		return "user/passwordReset";
	}
	

	
	@RequestMapping(value = "/user/passwordReset", method = RequestMethod.POST)
	public String editUserPasswrd(@Valid @ModelAttribute("user") User user,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "Change";
		String retVal = "redirect:/admin/user/passwordReset.html";

		User user2 = userService.findOne(id);
	
			
		user2.setPassword(user.getPassword());
		user2.setModiBy(principal.getName());
		user2.setModiDt(new java.util.Date());
		
		
		userService.save(user2);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;
	}
}
