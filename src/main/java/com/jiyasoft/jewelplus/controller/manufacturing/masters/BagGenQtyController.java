package com.jiyasoft.jewelplus.controller.manufacturing.masters;

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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.BagGenQty;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IBagGenQtyService;


@RequestMapping("/manufacturing/masters")
@Controller
public class BagGenQtyController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;

	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private IBagGenQtyService bagGenQtyService;
	
	@ModelAttribute("bagGenQty")
	public BagGenQty construct(){
		return new BagGenQty();
	}
	
	@RequestMapping("/bagGenQty")
	public String users(Model model,Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("bagGenQty");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
		}
		return "bagGenQty";
	}
	
	
	@RequestMapping("/bagGenQty/listing")
	@ResponseBody
	public String bagGenQtyListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search, Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<BagGenQty> bagGens = null;

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("bagGenQty");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}
		
		bagGens = bagGenQtyService.searchAll(limit, offset, sort, order, search, true);
		
		sb.append("{\"total\":").append(bagGens.getTotalElements()).append(",\"rows\": [");

		for (BagGenQty bagGenQty : bagGens) {
			
			sb.append("{\"id\":\"")
					.append(bagGenQty.getId())
					.append("\",\"fromCaratWt\":\"")
					.append(bagGenQty.getFromCtsWt())
					.append("\",\"toCaratWt\":\"")
					.append(bagGenQty.getToCtsWt()!=null? bagGenQty.getToCtsWt() :"")
					.append("\",\"qty\":\"")
					.append(bagGenQty.getQty()!=null?bagGenQty.getQty():"")
					.append("\",\"deactive\":\"")
					.append(bagGenQty.isDeactive() ? "Yes":"No");
			
					sb.append("\",\"action1\":\"");
					if (roleRights.getCanEdit()) {
						sb.append("<a href='/jewels/manufacturing/masters/bagGenQty/edit/")
							.append(bagGenQty.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
		
					sb.append("\",\"action2\":\"");
					if (roleRights.getCanDelete()) {
						sb.append(
								"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/masters/bagGenQty/delete/")
								.append(bagGenQty.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-danger triggerRemove")
							.append(bagGenQty.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Deactivate</a>")
							.append("\"},");
		}
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		System.out.println(str);
		
		return str;

	}
	
	
	@RequestMapping("/bagGenQty/add")
	public String add(Model model,Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("bagGenQty");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}
		
		return "bagGenQty/add";
	}

	@RequestMapping(value = "/bagGenQty/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditUser(@Valid @ModelAttribute("bagGenQty") BagGenQty bagGenQty,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "/jewels/manufacturing/masters/bagGenQty/add.html?success="+true+"&action="+action;

		if (result.hasErrors()) {
			return "bagGenQty/add";
		}
		
		String checkDuplicate=bagGenQtyService.checkDuplicate(bagGenQty.getFromCtsWt(), bagGenQty.getToCtsWt(), id);
		if(checkDuplicate == "true"){
			return "-1";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			bagGenQty.setCreatedBy(principal.getName());
			bagGenQty.setCreatedDate(new java.util.Date());
			
		} else {
			bagGenQty.setId(id);
			bagGenQty.setModiBy(principal.getName());
			bagGenQty.setModiDate(new java.util.Date());
			action = "updated";
			retVal = "/jewels/manufacturing/masters/bagGenQty.html?id="+id+"&success="+true+"&action="+action;
		}

		bagGenQtyService.save(bagGenQty);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;

	}

	@RequestMapping("/bagGenQty/edit/{id}")
	public String edit(@PathVariable int id, Model model,Principal principal) {
		BagGenQty bagGenQty = bagGenQtyService.findOne(id);
		model.addAttribute("bagGenQty", bagGenQty);

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("bagGenQty");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}
		
		
		return "bagGenQty/add";
	}

	@RequestMapping("/bagGenQty/delete/{id}")
	public String delete(@PathVariable int id) {
		bagGenQtyService.delete(id);

		return "redirect:/manufacturing/masters/bagGenQty.html";
	}



}
