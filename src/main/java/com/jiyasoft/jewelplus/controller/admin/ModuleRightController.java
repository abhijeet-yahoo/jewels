package com.jiyasoft.jewelplus.controller.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jiyasoft.jewelplus.service.admin.ModuleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserService;

@RequestMapping("/admin")
@Controller
public class ModuleRightController {

	
	@Autowired
	private ModuleRightsService moduleRightsService;
	
	@Autowired
	private UserService userService;
	
	
	/*@RequestMapping("/validation/{moduleCode}")
	public String validateModule(@PathVariable String moduleCode,Model model,Principal principal){
		
		System.out.println("------------"+moduleCode);
		System.out.println("------------"+principal.getName());
		
		User user = userService.findByName(principal.getName());
		
		String retVal = "-1";
		
		ModuleRights moduleRights = moduleRightsService.findByUserAndModuleName(user, moduleCode);
		
		
		
		//System.out.println("----------"+moduleRights.getId());
		
		if(moduleRights != null){
			//retVal= "redirect:/manufacturing/masters/category.html";
			retVal = moduleRights.getModuleUrl();
		}else{
			retVal = "javascript:displayMsg(event, this);";
		}
		
		
		
		return retVal;
	}*/
	
	
	
	
}
