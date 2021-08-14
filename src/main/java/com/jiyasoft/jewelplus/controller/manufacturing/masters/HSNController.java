package com.jiyasoft.jewelplus.controller.manufacturing.masters;

import java.security.Principal;

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

import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.HSNMast;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IHSNService;

@RequestMapping("/manufacturing/masters")
@Controller
public class HSNController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private IHSNService hSNService;

	@ModelAttribute("hSNMast")
	public HSNMast construct() {

		return new HSNMast();
	}

	@RequestMapping("/hSNMast")
	public String users(Model model,Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("hSNMast");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){

			model.addAttribute("canAdd", true);

			return "hSNMast";
		}else

			if(roleRights == null){
				return "accesDenied";
			}else{
				model.addAttribute("canAdd", roleRights.getCanAdd());
			}
		return "hSNMast";
	}

	@RequestMapping("/hSNMast/listing")
	@ResponseBody
	public String hSNListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			Principal principal) {

		return hSNService.hSNListing(limit, offset, sort, order, search, principal, false);

	}


	@RequestMapping("/hSNMast/add")
	public String add(Model model,Principal principal) {

		return hSNService.addHSNMaster(model, principal) ;

	}


	@RequestMapping(value = "/hSNMast/add", method = RequestMethod.POST)
	public String addEditUser(@Valid @ModelAttribute("hSNMast") HSNMast hSNMast,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {
	
		String action = "added";
		String retVal = "redirect:/manufacturing/masters/hSNMast/add.html";

		if (result.hasErrors()) {
			return "hSNMast/add";
		}

		if (id == null || id.equals(" ") || (id != null && id == 0)) {
			hSNMast.setCreatedBy(principal.getName());
			hSNMast.setCreatedDate(new java.util.Date());
		} else {
			hSNMast.setId(id);
			hSNMast.setModiBy(principal.getName());
			hSNMast.setModiDate(new java.util.Date());
			action = "updated";
			retVal = "redirect:/manufacturing/masters/hSNMast.html";
		}

		hSNService.save(hSNMast);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;
		
	}


	@RequestMapping("/hSNMast/edit/{id}")
	public String edit(@PathVariable int id, Model model,Principal principal) {
		HSNMast hSNMast = hSNService.findOne(id);
		model.addAttribute("hSNMast", hSNMast);

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("hSNMast");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){

			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);

			return "hSNMast/add";

		}else

			if(roleRights == null){
				return "accesDenied";
			}else{
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
			}


		return "hSNMast/add";
	}


	@RequestMapping("/hSNMast/delete/{id}")
	public String delete(@PathVariable int id) {
		hSNService.delete(id);
		return "redirect:/manufacturing/masters/hSNMast.html";
	}

	
	@RequestMapping("/hSNMastCodeAvailable")
	@ResponseBody
	public String hSNMastCodeAvailable(@RequestParam String code, @RequestParam Integer id) {
		Boolean hSNMastCodeAvailable = true;

		if (id == null) {
			hSNMastCodeAvailable = (hSNService.findByCodeAndDeactive(code, false) == null);

		} else {
			HSNMast hSNMast = hSNService.findOne(id);
			if (!(code.equalsIgnoreCase(hSNMast.getCode()))) {
				hSNMastCodeAvailable = (hSNService.findByCodeAndDeactive(code, false) == null);
			}
		}

		return hSNMastCodeAvailable.toString();
	}

}
