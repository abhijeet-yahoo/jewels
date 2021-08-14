package com.jiyasoft.jewelplus.controller.manufacturing.masters;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ExchangeRateMaster;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ICountryService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IExchangeRateService;

@RequestMapping("/manufacturing/masters")
@Controller
public class ExchangeRateMastController {


	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private IExchangeRateService exchangeRateService;
	
	@Autowired
	private ICountryService countryService;
	
	@ModelAttribute("exchangeRate")
	public ExchangeRateMaster construct() {
		return new ExchangeRateMaster();
	}

	
	@RequestMapping("/exchangeRate")
	public String users(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("exchangeRate");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {

			model.addAttribute("canAdd", true);
			

			return "exchangeRate";
		} else

		if (roleRights == null) {
			return "accesDenied";
		} else {
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}

		return "exchangeRate";
	}

	@RequestMapping("/exchangeRate/listing")
	@ResponseBody
	public String exchngRtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			Principal principal) {

		return exchangeRateService.exchngRtListing(limit, offset, sort, order, search, principal);

	}

	@RequestMapping("/exchangeRate/add")
	public String add(Model model,Principal principal) {

		return exchangeRateService.addExchngRateMaster(model, principal) ;

	}
	
	
	@RequestMapping(value = "/exchangeRate/add", method = RequestMethod.POST)
	public String addEditUser(@Valid @ModelAttribute("exchangeRate") ExchangeRateMaster exchangeRate,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {
	
		String retVal ="-1";
		
		try {
			retVal = exchangeRateService.exchangeRateMasterSave(exchangeRate, id, redirectAttributes, principal, result);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	
		return retVal;
	}
	
	
	

	@RequestMapping("/exchangeRate/edit/{id}")
	public String edit(@PathVariable int id, Model model,Principal principal) {
		ExchangeRateMaster exchangeRate = exchangeRateService.findOne(id);
		model.addAttribute("exchangeRate", exchangeRate);

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("exchangeRate");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		model.addAttribute("countryMap",countryService.getCountryWiseCurrencyList());

		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){

			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			return "exchangeRate/add";

		}else

			if(roleRights == null){
				return "accesDenied";
			}else{
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
		//		model.addAttribute("countryMap",countryService.getCountryWiseCurrencyList());
			}


		return "exchangeRate/add";
	}

	
	@RequestMapping("/exchangeRate/delete/{id}")
	public String delete(@PathVariable int id) {
		exchangeRateService.delete(id);
		return "redirect:/manufacturing/masters/exchangeRate.html";
	}

	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
}
