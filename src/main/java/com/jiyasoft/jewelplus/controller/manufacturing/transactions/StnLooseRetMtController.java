package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
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
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnLooseRetDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnLooseRetMt;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IInwardTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStnLooseRetDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStnLooseRetMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class StnLooseRetMtController {

	
	@Autowired
	private IStnLooseRetMtService stnLooseRetMtService;
	
	@Autowired
	private UserService userService;
		
	@Autowired
	private IPartyService partyService;

	@Autowired
	private IInwardTypeService inwardTypeService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;

	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private IStnLooseRetDtService stnLooseRetDtService;
	
	
	@ModelAttribute("stnLooseRetMt")
	public StnLooseRetMt constructMt() {
		return new StnLooseRetMt();
	}

	@ModelAttribute("stnLooseRetDt")
	public StnLooseRetDt constructDt() {
		return new StnLooseRetDt();
	}
	
	
	@RequestMapping("/stnLooseRetMt")
	public String users(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stnLooseRetMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			return "stnLooseRetMt";
		} else {
			if (roleRights == null) {
				return "accesDenied";
			} else {
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());

			}
			return "stnLooseRetMt";
		}
	}
	
	
	@RequestMapping("/stnLooseRetMt/listing")
	@ResponseBody
	public String stnLooseRetMtListing(Model model, @RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search, Principal principal)
			throws ParseException {

		StringBuilder sb = new StringBuilder();
		Page<StnLooseRetMt> stnLooseRetMts = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stnLooseRetMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

	
		stnLooseRetMts = stnLooseRetMtService.searchAll(limit, offset, sort, order, search);

		sb.append("{\"total\":").append(stnLooseRetMts.getTotalElements()).append(",\"rows\": [");

		for (StnLooseRetMt stnLooseRetMt : stnLooseRetMts) {
			Date currdate = sdf.parse(sdf.format(new Date()));
			Date invDate = sdf.parse(sdf.format(stnLooseRetMt.getInvDate()));

					sb.append("{\"id\":\"")
					.append(stnLooseRetMt.getId())
					.append("\",\"invNo\":\"")
					.append(stnLooseRetMt.getInvNo())
					.append("\",\"invDate\":\"")
					.append(stnLooseRetMt.getInvDateStr())
					.append("\",\"beNo\":\"")
					.append((stnLooseRetMt.getBeNo() != null ? stnLooseRetMt.getBeNo() : ""))
					.append("\",\"beDate\":\"")
					.append((stnLooseRetMt.getBeDateStr() != null ? stnLooseRetMt.getBeDateStr() : ""))
					.append("\",\"party\":\"")
					.append((stnLooseRetMt.getParty() != null ? stnLooseRetMt.getParty().getPartyCode() : ""))
					.append("\",\"supplier\":\"")
					.append((stnLooseRetMt.getSupplier() != null ? stnLooseRetMt.getSupplier().getPartyCode() : ""))
					.append("\",\"inwardType\":\"")
					.append((stnLooseRetMt.getInwardType() != null ? stnLooseRetMt.getInwardType().getName() : ""))
					.append("\",\"remark\":\"")
					.append((stnLooseRetMt.getRemark() != null ? stnLooseRetMt.getRemark() : ""));
				
					sb.append("\",\"action1\":\"");
					if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
							|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
							|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
		
						sb.append("<a href='/jewels/manufacturing/transactions/stnLooseRetMt/edit/")
								.append(stnLooseRetMt.getId()).append(".html'");
		
						sb.append(
								".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
		
						sb.append("\",\"action2\":\"");
		
						sb.append("<a href='javascript:deleteStnLooseRetMt(event,");					
								sb.append(stnLooseRetMt.getId());
		
						sb.append(");' class='btn btn-xs btn-danger triggerRemove").append(stnLooseRetMt.getId())
								.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
						
		
				sb.append("\",\"action3\":\"");

				sb.append("<a href='/jewels/manufacturing/transactions/stnLooseRetMt/view/")
						.append(stnLooseRetMt.getId()).append(".html'");
				sb.append(
						" class='btn btn-xs btn-success' ><span class='glyphicon glyphicon-eye-open'></span>&nbsp;View</a>")

						.append("\"},");
			} else {

				if (roleRights.getCanEdit()) {

					sb.append("<a href='/jewels/manufacturing/transactions/stnLooseRetMt/edit/")
					.append(stnLooseRetMt.getId()).append(".html'");
		

				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(
						".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {

					if (currdate.equals(invDate)) {
						sb.append("<a href='javascript:deleteStnLooseRetMt(event,")	
						.append(stnLooseRetMt.getId()).append(".html'");

					} else {
						sb.append("<a onClick='javascript:displayBackDatedMsg(event, this)' href='javascript:void(0)'");
					}

				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(".html' class='btn btn-xs btn-danger triggerRemove").append(stnLooseRetMt.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");

				sb.append("\",\"action3\":\"");
				if (roleRights.getCanView()) {
					sb.append("<a href='/jewels/manufacturing/transactions/stnLooseRetMt/view/")
							.append(stnLooseRetMt.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(
						" class='btn btn-xs btn-success' ><span class='glyphicon glyphicon-eye-open'></span>&nbsp;View</a>")

						.append("\"},");
			}
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";

		
		return str;

	}
	
	
	@RequestMapping("/stnLooseRetMt/add")
	public String add(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stnLooseRetMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		model = populateModel(model, principal);
		
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			model.addAttribute("canEditTranddate", "false");
			return "stnLooseRetMt/add";
			
		}else{
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("canView", roleRights.getCanView());
		}
		
		model.addAttribute("canEditTranddate", "false");
		return "stnLooseRetMt/add";
		}
	}
	
	
	
	private Model populateModel(Model model, Principal principal) {
		//Client
		model.addAttribute("clientMap", partyService.getExportClientPartyList());
		model.addAttribute("inwardTypeMap",inwardTypeService.getInwardTypeList());
		
		//Supplier
		model.addAttribute("partyMap", partyService.getExportClientPartyListForSupplier());
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String curDate = dateFormat.format(date);
		model.addAttribute("currentDate", curDate);

		return model;
	}
	
	
	@RequestMapping(value = "/stnLooseRetMt/add", method = RequestMethod.POST)
	public String addEditstnLooseMt(
			@Valid @ModelAttribute("stnLooseRetMt") StnLooseRetMt stnLooseRetMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal,
			@RequestParam(value = "vTranDate", required = false) String vTranDate) throws ParseException {

		synchronized (this) {
		return stnLooseRetMtService.saveStnLooseRetMt(stnLooseRetMt, id, redirectAttributes, principal, result,vTranDate);
			
		}
		}
	
	
	@RequestMapping("/stnLooseRetMt/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stnLooseRetMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		StnLooseRetMt stnLooseRetMt = stnLooseRetMtService.findOne(id);
		model.addAttribute("stnLooseRetMt", stnLooseRetMt);
		model = populateModel(model,principal);
		
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			model.addAttribute("canEditTranddate", "false");
			model.addAttribute("mtid", id);
			
			
			return "stnLooseRetMt/add";
			
		}else{
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}

		model.addAttribute("mtid", id);
		model.addAttribute("canEditTranddate", "false");
		

		return "stnLooseRetMt/add";
		}
	}
	
	
	
	@RequestMapping(value = "/stnLooseRetDt/Transfer", method = RequestMethod.POST)
	@ResponseBody
	public String stnLooseRetDtTransfer(@RequestParam(value = "mtId", required = false) Integer mtId,
			@RequestParam(value = "data", required = false) String jsonData,
			Principal principal) throws ParseException {

		String retVal = "-1";
		
		synchronized (this) {
			retVal = stnLooseRetDtService.stnLooseRetDtSave(mtId, jsonData, principal);
			
		}
		
	return retVal;

	}
	
	
	@RequestMapping("/stnLooseRetDt/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable int id,Principal principal) {
		
		String retVal = stnLooseRetDtService.stnLooseRetDtDelete(id, principal);
			
		return retVal;
	}
	
	
	@RequestMapping("/stnLooseRetDt/listing")
	@ResponseBody
	public String stnLooseRetDtList(@RequestParam(value = "mtId", required = false) Integer mtId,
			@RequestParam(value = "canViewFlag", required = false) Boolean canViewFlag,Principal principal){
		
		return stnLooseRetDtService.stnLooseRetDtListing(mtId, canViewFlag, principal);
	
	}	
	
	
	@RequestMapping("/stnLooseRetMt/delete/{id}")
	@ResponseBody
	public String deleteRetMt(@PathVariable int id,Principal principal) {
		String retVal = "";
		
		retVal= stnLooseRetMtService.deleteMt(id, principal);
		
		if(retVal.equalsIgnoreCase("-1")){
			return retVal;
		}
		
		return "redirect:/manufacturing/transactions/stnLooseMt.html";						 

	}
	
	
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	
	
}
