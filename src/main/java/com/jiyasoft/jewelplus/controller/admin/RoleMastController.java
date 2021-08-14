package com.jiyasoft.jewelplus.controller.admin;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.admin.RoleMast;
import com.jiyasoft.jewelplus.service.admin.RoleMastService;

@Controller
@RequestMapping("/admin")
public class RoleMastController {
	
	
	@Autowired
	private RoleMastService roleMastService;
	
	@ModelAttribute("roleMast")
	public RoleMast construct(){
		return new RoleMast();
	}
	

	
	@RequestMapping("/roleMast")
	public String roleUser(Model model){
		return "roleMast";
	}
	
	
	
	
	@RequestMapping("/roleMast/listing")
	@ResponseBody
	public String colorListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search, Principal principal) {

		StringBuilder sb = new StringBuilder();
		
		List<RoleMast> roleMasts = roleMastService.findAll();
		

		sb.append("{\"total\":").append(roleMastService.count()).append(",\"rows\": [");

		for (RoleMast roleMast : roleMasts) {
			
			sb.append("{\"id\":\"")
					.append(roleMast.getId())
					.append("\",\"name\":\"")
					.append(roleMast.getRoleNm())
					.append("\"},");
		}
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;

	}
	
	
	@RequestMapping("/roleMast/add")
	public String addRoleMast(Model model){
		return "roleMast/add";
	}
	
	

	
	
	
	@RequestMapping(value = "/roleMast/add", method = RequestMethod.POST)
	public String addEditUser(@Valid @ModelAttribute("roleMast") RoleMast roleMast,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "redirect:/admin/roleMast/add.html";

		if (result.hasErrors()) {
			return "roleMast/add";
		}

		
		roleMastService.save(roleMast);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;

	}

	
	@RequestMapping("/roleMast/available")
	@ResponseBody
	public String roleMastAvailable(@RequestParam String roleNm,
			@RequestParam Integer id) {
		Boolean roleMastAvailable = true;

		roleMastAvailable = (roleMastService.findByRoleNm(roleNm) == null);
		
		return roleMastAvailable.toString();
	}
	

}
