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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;

@RequestMapping("/manufacturing/masters")
@Controller
public class PurityController {

	@Autowired
	private IMetalService metalService;;

	@Autowired
	private IPurityService purityService;;

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private UserRoleService userRoleService;

	@ModelAttribute("purity")
	public Purity construct() {
		return new Purity();
	}

	@RequestMapping("/purity")
	public String users(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("purity");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
	
			return "purity";
			
		}else
			
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}

		return "purity";
	}

	@RequestMapping("/purity/listing")
	@ResponseBody
	public String purityListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "opt", required = false) String opt,
			Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<Purity> puritys = null;

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("purity");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

		Long rowCount = null;
		
		puritys = purityService.searchAll(limit, offset, sort, order, search, false);
		rowCount = purityService.countAll(sort, search, false);
		

		/*if (search == null) {
			if (id == null) {
				rowCount = purityService.count(sort, search, false);
				puritys = purityService.findAll(limit, offset, sort, order,
						search);
			} else {
				rowCount = purityService.count(sort, search, false);
				puritys = purityService.findByMetal(metalService.findOne(id),
						limit, offset, sort, order, search);
			}
		} else {

			if (search != null && sort != null && sort.equalsIgnoreCase("name")) {
				rowCount = purityService.count(sort, search, false);
				puritys = purityService.findByName(limit, offset, sort, order,
						search, false);
			} else if (search != null && sort != null) {
				rowCount = 0L;
				puritys = new PageImpl<Purity>(new ArrayList<Purity>());
			} else {
				rowCount = purityService.count(sort, search, false);
				puritys = purityService.findByName(limit, offset, sort, order,
						search, false);
			}
		}*/

		sb.append("{\"total\":").append(rowCount).append(",\"rows\": [");
		for (Purity purity : puritys) {
			if(opt.equals("1")){
			sb.append("{\"id\":\"")
					.append(purity.getId())
					.append("\",\"metalName\":\"")
					.append((purity.getMetal() != null ? purity.getMetal().getName() : ""))
					.append("\",\"name\":\"")
					.append(purity.getName())
					.append("\",\"defPurity\":\"")
					.append((purity.getDefPurity() == null ? "" : purity.getDefPurity() ? "Yes" : "No"))
					.append("\",\"pure\":\"")
					.append((purity.getPure() == null ? "": purity.getPure() ? "Yes" : "No"))
					.append("\",\"waxWtConv\":\"")
					.append((purity.getWaxWtConv() == null ? "" : purity.getWaxWtConv()))
					.append("\",\"purityConv\":\"")
					.append((purity.getPurityConv() == null ? "" : purity.getPurityConv()))
					.append("\",\"purity\":\"")
					.append((purity.getvPurity() == null ? "" : purity.getvPurity()))
					.append("\",\"updatedBy\":\"")
					.append((purity.getModiBy() == null ? "" : purity.getModiBy()))
					.append("\",\"updatedDt\":\"")
					.append((purity.getModiDate() == null ? "" : purity.getModiDate()))
					.append("\",\"deactive\":\"")
					.append((purity.getDeactive() == null ? "" : purity.getDeactive() ? "Deactive" : "Active"))
					// true==deactive and false==active
					.append("\",\"deactiveDt\":\"")
					.append((purity.getDeactiveDt() == null ? "" : purity.getDeactiveDt()));

			if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
					userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
				
				sb.append("\",\"action1\":\"")
							.append("<a href='/jewels/manufacturing/masters/purity/edit/")
							.append(purity.getId()).append(".html'")
							.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
				sb.append("\",\"action2\":\"")
							.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/purity/delete/")
							.append(purity.getId()).append(".html'")
							.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(purity.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
							.append("\"},");
			}else{
				
				sb.append("\",\"action1\":\"");
				if (roleRights.getCanEdit()) {
					sb.append("<a href='/jewels/manufacturing/masters/purity/edit/")
							.append(purity.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
	
				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {
					sb.append(
							"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/purity/delete/")
							.append(purity.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-danger triggerRemove")
						.append(purity.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
						.append("\"},");
			}
			
						
			}else if(opt.equals("3")){
				sb.append("{\"id\":\"")
				.append(purity.getId())
				.append("\",\"name\":\"")
				.append(purity.getName())
				.append("\"},");
			}
			

		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;
	}

	@RequestMapping("/purity/add")
	public String add(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("purity");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			model.addAttribute("metalList", metalService.getMetalList());
			return "purity/add";

		}else	
		
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}
		model.addAttribute("metalList", metalService.getMetalList());

		return "purity/add";
	}

	@RequestMapping(value = "/purity/add", method = RequestMethod.POST)
	public String addEditUser(@Valid @ModelAttribute("purity") Purity purity,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "redirect:/manufacturing/masters/purity/add.html";

		if (result.hasErrors()) {
			return "purity/add";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			purity.setCreatedBy(principal.getName());
			purity.setCreatedDate(new java.util.Date());
		} else {
			purity.setModiBy(principal.getName());
			purity.setModiDate(new java.util.Date());

			if (purity.getDeactive() != purityService.findOne(id).getDeactive()) {
				purity.setDeactiveDt(new java.util.Date());
			} else {
				purity.setDeactiveDt(purityService.findOne(id).getDeactiveDt());
			}

			purity.setId(id);

			action = "updated";
			retVal = "redirect:/manufacturing/masters/purity.html?id="+ purity.getMetal().getId();
		}

		purityService.save(purity);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;
	}

	@RequestMapping("/purity/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		Purity purity = purityService.findOne(id);
		model.addAttribute("purity", purity);
		model.addAttribute("metalList", metalService.getMetalList());

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("purity");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			
			model.addAttribute("metalList", metalService.getMetalList());
			return "purity/add";

		}else	
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}

		return "purity/add";
	}

	@RequestMapping("/purity/delete/{id}")
	public String delete(@PathVariable int id) {
		purityService.delete(id);

		return "redirect:/manufacturing/masters/purity.html";
	}

	@RequestMapping("/pure/chk")
	@ResponseBody
	public String pureChecking(@RequestParam Boolean pure,
			@RequestParam Integer metal, @RequestParam Integer id) {

		Boolean pureAvailable = true;
		Metal metalObj = metalService.findOne(metal);

		pureAvailable = (purityService.findByMetalAndPure(metalObj, pure) == null);
		Purity purity = purityService.findByMetalAndPure(metalObj, pure);

		if (purity != null && purity.getId() == id) {
			pureAvailable = true;
		}

		return pureAvailable.toString();

	}

	@RequestMapping("/defaultPurity/chk")
	@ResponseBody
	public String defaultPureChecking(@RequestParam Boolean defPurity,
			@RequestParam Integer metal, @RequestParam Integer id) {

		Boolean defPurityAvailable = true;
		//Metal metalObj = metalService.findOne(metal);

		defPurityAvailable = (purityService.findByDefPurityAndDeactive(defPurity,false) == null);

		Purity purity = purityService.findByDefPurityAndDeactive(defPurity,false);

		if (purity != null && purity.getId() == id) {
			defPurityAvailable = true;
		}

		return defPurityAvailable.toString();
	}

	
	
	@ResponseBody
	@RequestMapping("/puritylist")
	public String purityCombo(){
		
		List<Purity> puritys = purityService.findAll();
		JSONObject jsonObject = new JSONObject();
		
		for(Purity purity:puritys){
			jsonObject.put(purity.getName(), purity.getName());
		}
	
		return jsonObject.toString();
	}
	
	
	@ResponseBody
	@RequestMapping("/purity/conversion")
	public String purityConversion(
			@RequestParam(value = "purityId") Integer purityId){
		
		String retVal = "0.0";
		Purity purity = purityService.findOne(purityId);
		if(purity != null){
			if(purity.getWaxWtConv() != null){
				retVal = purity.getWaxWtConv().toString();
			}
		}
	
		return retVal;
	}
	
	
}
