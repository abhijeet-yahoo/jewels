package com.jiyasoft.jewelplus.controller.admin;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.repository.admin.IUserRepository;
import com.jiyasoft.jewelplus.service.admin.RoleMastService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;

@RequestMapping("/admin")
@Controller
public class UserRoleController {
	
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private RoleMastService roleMastService;
	
	
	
	@ModelAttribute("userRole")
	public UserRole construct(){
		return new UserRole();
	}
	
	
	
	
		@RequestMapping("/userRole")
		public String userRole(Model model){
			model = populateModel(model);
			return "userRole";
		}
		
		
		public Model populateModel(Model model){
			model.addAttribute("userMap",userService.getUserList());
			model.addAttribute("roleMastMap",roleMastService.getRoleMastList());
			return model;
		}
		
		
		
		@RequestMapping(value = "userRole/add")
		@ResponseBody
		public String addUserRole(@ModelAttribute("userRole") UserRole userRole,Principal principal){
			
			String retVal = "-1";
			
			
			User user = userService.findOne(userRole.getUser().getId());
			//RoleMast roleMast = roleMastService.findOne(userRole.getRoleMast().getId());
			
			UserRole userRoleOld = userRoleService.findByUser(user);
			
			if(userRoleOld == null){
				userRoleService.save(userRole);
				user.setRoleMast(userRole.getRoleMast());
				userRepository.save(user);
			}else{
				userRoleOld.setUser(userRole.getUser());
				userRoleOld.setRoleMast(userRole.getRoleMast());
				userRoleService.save(userRoleOld);
				
				user.setRoleMast(userRole.getRoleMast());
				userRepository.save(user);
			}
			
			
			return retVal;
		}
	
	

}
