package com.jiyasoft.jewelplus.controller.manufacturing.masters;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;
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

import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ClientStamp;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.WipStyle;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDesignService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IWipStyleService;

@RequestMapping("/manufacturing/masters")
@Controller
public class WipStyleController {

	@Autowired
	private IPartyService partyService;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private IDesignService designService;

	@Autowired
	private IWipStyleService wipStyleService;

	@ModelAttribute("wipStyle")
	public WipStyle construct() {
		return new WipStyle();
	}

	@RequestMapping("/wipStyle")
	public String wipStyle(Model model, Principal principal) {

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("wipStyle");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {

			model.addAttribute("canAdd", true);

			return "wipStyle";
		} else

		if (roleRights == null) {
			return "accesDenied";
		} else {
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}

		return "wipStyle";
	}

	@RequestMapping("/wipStyle/listing")
	@ResponseBody
	public String wipStyleListing(Model model, @RequestParam(value = "partyCode", required = false) String partyCode,
			Principal principal) {

		return wipStyleService.wipStyleListing(partyCode, principal);

	}

	@ResponseBody
	@RequestMapping(value = "/wipStyle/add", method = RequestMethod.POST)
	public String addEditWipStyle(@Valid @ModelAttribute("wipStyle") WipStyle wipStyle, BindingResult result,
			@RequestParam(value = "id") Integer id, HttpSession httpSession, Principal principal) {

		String retVal = "-1";
		synchronized (this) {
			try {

				if (result.hasErrors()) {
					return "Error Contact Admin";
				}
				
				
				
				Party party = partyService.findByPartyCodeAndDeactive(wipStyle.getParty().getPartyCode(),false);
				if (party == null) {
					return "Party Not Valid";
				}

				Design design = designService.findByMainStyleNoAndDeactive(wipStyle.getDesign().getMainStyleNo(),
						false);

				WipStyle wipStyle2 = wipStyleService.findByPartyAndDesignAndDeactive(party, design, false);

				if (wipStyle2 != null) {
					return retVal = "Duplicate Entry";
				}

				if (id == null || id.equals("") || (id != null && id == 0)) {

					wipStyle.setParty(party);
					wipStyle.setDesign(design);
					wipStyle.setCreatedBy(principal.getName());
					wipStyle.setCreatedDt(new java.util.Date());
					retVal = "1";
				} else {

					wipStyle.setParty(party);
					wipStyle.setDesign(design);
					wipStyle.setId(id);
					wipStyle.setModiBy(principal.getName());
					wipStyle.setModiDt(new java.util.Date());
					retVal = "redirect:/manufacturing/masters/wipStyle.html";
				}

				wipStyleService.save(wipStyle);
			} catch (Exception e) {
				e.printStackTrace();
				retVal = "Erorr : Record Not Saved(" + e + ") Contact Support";
			}

		}

		return retVal;

	}

	@ResponseBody
	@RequestMapping("/wipStyle/delete/{id}")
	public String delete(@PathVariable int id) {

		String retVal="-1";
		try {
			wipStyleService.delete(id);
			retVal="1";
		} catch (Exception e) {
			retVal="-1";
		}
		

		return retVal;
	}

}
