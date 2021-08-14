package com.jiyasoft.jewelplus.controller.admin;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;

@RequestMapping("/admin")
@Controller
public class RedirectController {
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleMastService RoleMastService;
	
	@Autowired
	private RoleRightsService roleRightService;
	
	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@RequestMapping("/validation/{menuName}")
	public String validation(@PathVariable String menuName,Model model,Principal principal){
		
		String retVal = "-1";
		
		MenuMast menuMast = menuMastService.findByMenuNm(menuName.trim());
		User user = userService.findByName(principal.getName().trim());
		UserRole userRole = userRoleService.findByUser(user);

			
		
			if(userRole != null){
				
				if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
						userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
					
					retVal = "redirect:"+menuMast.getUrl();
					
				}else{
					
					
					RoleRights roleRights = roleRightService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
					if(roleRights != null){
						
						retVal = "redirect:"+roleRights.getMenuMast().getUrl();
			
					}else{
						
						retVal = "accesDenied";
					}
					
					
				}
				
				
				
				
			}else{
				retVal = "accesDenied";
			}
		
			
			
		
		return retVal;
		
	}
	
	
	
}
