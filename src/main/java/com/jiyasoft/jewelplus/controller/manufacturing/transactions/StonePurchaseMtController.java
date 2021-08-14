package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneInwardMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StonePurchaseDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StonePurchaseMt;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IInwardTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStonePurchaseMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class StonePurchaseMtController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;

	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private IPartyService partyService;

	@Autowired
	private IInwardTypeService inwardTypeService;
 
	@Autowired
	private IStonePurchaseMtService stonePurchaseMtService;
	
	@Autowired
	private IStoneTypeService stoneTypeService;

	@Autowired
	private IShapeService shapeService;


	@ModelAttribute("stonePurchaseMt")
	public StonePurchaseMt constructMt() {
		return new StonePurchaseMt();
	}

	@ModelAttribute("stonePurchaseDt")
	public StonePurchaseDt constructDt() {
		return new StonePurchaseDt();
	}
	
	
	@RequestMapping("/stonePurchaseMt")
	public String users(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stonePurchaseMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			return "stonePurchaseMt";
		} else {
			if (roleRights == null) {
				return "accesDenied";
			} else {
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());

			}
			return "stonePurchaseMt";
		}
	}

	@RequestMapping("/stonePurchaseMt/listing")
	@ResponseBody
	public String stonePurchaseMtListing(Model model, @RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search, Principal principal)
			throws ParseException {

		StringBuilder sb = new StringBuilder();
		Page<StonePurchaseMt> stonePurchaseMts = null;
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stonePurchaseMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

	
		stonePurchaseMts = stonePurchaseMtService.searchAll(limit, offset, sort, order, search, true);

		sb.append("{\"total\":").append(stonePurchaseMts.getTotalElements())
		.append(",\"rows\": [");

		for (StonePurchaseMt stonePurchaseMt : stonePurchaseMts) {
		
					sb.append("{\"id\":\"")
					.append(stonePurchaseMt.getId())
					.append("\",\"invNo\":\"")
					.append(stonePurchaseMt.getInvNo())
					.append("\",\"invDate\":\"")
					.append(stonePurchaseMt.getInvDateStr())
					.append("\",\"beNo\":\"")
					.append((stonePurchaseMt.getBeNo() != null ? stonePurchaseMt.getBeNo() : ""))
					.append("\",\"beDate\":\"")
					.append((stonePurchaseMt.getBeDateStr() != null ? stonePurchaseMt.getBeDateStr() : ""))
					.append("\",\"party\":\"")
					.append((stonePurchaseMt.getParty() != null ? stonePurchaseMt.getParty().getPartyCode() : ""))
					.append("\",\"supplier\":\"")
					.append((stonePurchaseMt.getSupplier() != null ? stonePurchaseMt.getSupplier().getPartyCode() : ""))
					.append("\",\"inwardType\":\"")
					.append((stonePurchaseMt.getInwardType() != null ? stonePurchaseMt.getInwardType().getName() : ""))
					.append("\",\"remark\":\"")
					.append((stonePurchaseMt.getRemark() != null ? stonePurchaseMt.getRemark() : ""));
			
					sb.append("\",\"action1\":\"");
					if (roleRights.getCanEdit()) {
						sb.append("<a href='/jewels/manufacturing/transactions/stonePurchaseMt/edit/")
							.append(stonePurchaseMt.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
		
					sb.append("\",\"action2\":\"");
					if (roleRights.getCanEdit()) {
						
					sb.append("<a href='javascript:deleteStonePurchaseMt(event,")
					  .append(stonePurchaseMt.getId()).append(", 0);' href='javascript:void(0);'")
					.append(" class='btn btn-xs btn-danger triggerRemove")
					.append(stonePurchaseMt.getId());
					}
					else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
			
					
					sb.append("\",\"action3\":\"");
					if (roleRights.getCanView()) {
						sb.append("<a href='/jewels/manufacturing/transactions/stonePurchaseMt/view/")
								.append(stonePurchaseMt.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(
							" class='btn btn-xs btn-success' ><span class='glyphicon glyphicon-eye-open'></span>&nbsp;View</a>")
							.append("\"},");			
					
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";

		return str;

	}

	@RequestMapping("/stonePurchaseMt/add")
	public String add(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stonePurchaseMt");
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
			return "stonePurchaseMt/add";
			
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
		return "stonePurchaseMt/add";
		}
	}

	private Model populateModel(Model model, Principal principal) {
		model.addAttribute("partyMap", partyService.getPartyList());
		model.addAttribute("inwardTypeMap",inwardTypeService.getInwardTypeList());
		model.addAttribute("stoneTypeMap", stoneTypeService.getStoneTypeList());
		model.addAttribute("shapeMap", shapeService.getShapeList());
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String curDate = dateFormat.format(date);
		model.addAttribute("currentDate", curDate);

		return model;
	}

	@RequestMapping(value = "/stonePurchaseMt/add", method = RequestMethod.POST)
	public String addEditStoneInwardMt(
			@Valid @ModelAttribute("stonePurchaseMt") StonePurchaseMt stonePurchaseMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "vTranDate", required = false) String vTranDate,			
			RedirectAttributes redirectAttributes, Principal principal) throws ParseException {

		String action = "added";
		String retVal = "redirect:/manufacturing/transactions/stonePurchaseMt/add.html";
		Date aDate = null;

		if (result.hasErrors()) {
			return "stonePurchaseMt/add";
		}
	

		if (id == null || id.equals("") || (id != null && id == 0)) {
			aDate = new java.util.Date();
			stonePurchaseMt.setCreatedBy(principal.getName());
			stonePurchaseMt.setCreatedDt(new java.util.Date());
			stonePurchaseMt.setUniqueId(aDate.getTime());

		} else {
			stonePurchaseMt.setModiBy(principal.getName());
			stonePurchaseMt.setModiDt(new java.util.Date());

			
			DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			Date date = originalFormat.parse(vTranDate);
			
			stonePurchaseMt.setInvDate(date);

			action = "updated";
			retVal = "redirect:/manufacturing/transactions/stonePurchaseMt.html";
		}
		
		if(stonePurchaseMt.getSupplier().getId() == null) {
			stonePurchaseMt.setSupplier(null);
		}
		
		stonePurchaseMt.setInvNo(stonePurchaseMt.getInvNo().replaceAll("[\\n\\t\\r ]", " ").trim());
		stonePurchaseMt.setRemark(stonePurchaseMt.getRemark().replaceAll("[\\n\\t\\r ]", " ").trim());

		stonePurchaseMtService.save(stonePurchaseMt);
		
		if (action.equals("added")) {
			StonePurchaseMt stnPurchase = stonePurchaseMtService.findByUniqueId(stonePurchaseMt.getUniqueId());
			
			 retVal = "redirect:/manufacturing/transactions/stonePurchaseMt/edit/"+stnPurchase.getId()+".html";
		}
		
		
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		return retVal;
	}

	@RequestMapping("/stonePurchaseMt/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("stonePurchaseMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		StonePurchaseMt stonePurchaseMt = stonePurchaseMtService.findOne(id);
		model.addAttribute("stonePurchaseMt", stonePurchaseMt);
		model = populateModel(model,principal);
		
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}

		model.addAttribute("mtid", id);
		model.addAttribute("canEditTranddate", "false");

		return "stonePurchaseMt/add";
		
	}
	
	@RequestMapping("/stonePurchaseMt/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable int id, Principal principal) {
		
		
		
		String retVal = stonePurchaseMtService.stonePurcDelete(id, principal);
		if(retVal == "-2"){
			return "-2";
		}
		
		return "redirect:/manufacturing/transactions/stonePurchaseMt.html";
	}
	
	@RequestMapping("/stonePurchaseMt/invoiceNoAvailable")
	@ResponseBody
	public String invoiceAvailable(@RequestParam String invNo,
			@RequestParam Integer id) {

		Boolean invoiceAvailable = true;

		if (id == null) {
			invoiceAvailable = (stonePurchaseMtService.findByInvNoAndDeactive(invNo, false) == null);
		} else {
			StonePurchaseMt stonePurchaseMt = stonePurchaseMtService.findOne(id);
			if (!(invNo.equalsIgnoreCase(stonePurchaseMt.getInvNo()))) {
				invoiceAvailable = (stonePurchaseMtService.findByInvNoAndDeactive(invNo, false) == null);
			}
		}

		return invoiceAvailable.toString();
	}
	
	
	
	
	
	
	
	
	
	@RequestMapping("/stonePurcMt/report/listing")
	@ResponseBody
	public String stonePurcInvoiceListing(Model model,
			@RequestParam(value="inwardTypeId",required=false)String inwardTypeIds,
			@RequestParam(value = "fromDate") String fromDate,
			@RequestParam(value = "toDate") String toDate) throws ParseException{
		
		
		StringBuilder sb = new StringBuilder();
		
		List<StonePurchaseMt>stonePurchaseMts = stonePurchaseMtService.getInvoiceList(inwardTypeIds, fromDate, toDate);
		
		
		
		sb.append("{\"total\":").append(stonePurchaseMts.size()).append(",\"rows\": [");

		for (StonePurchaseMt stonePurchaseMt : stonePurchaseMts) {
			sb.append("{\"id\":\"")
				.append(stonePurchaseMt.getId())
				.append("\",\"invNo\":\"")
				.append(stonePurchaseMt.getInvNo())
				.append("\"},");
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		
		
		return str;
	}
	
	
	
	
	

	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	
	
}
