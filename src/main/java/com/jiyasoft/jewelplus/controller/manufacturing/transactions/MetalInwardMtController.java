package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.reports.ReportFilter;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalInwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalInwardMt;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IInwardTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.reports.IReportFilterService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalInwardMtService;


@RequestMapping("/manufacturing/transactions")
@Controller
public class MetalInwardMtController {
	
	

	@Autowired
	private IMetalInwardMtService metalInwardMtService;

	@Autowired
	private UserService userService;

	@Autowired
	private IPartyService partyService;

	@Autowired
	private IInwardTypeService inwardTypeService;

	@Autowired
	private IMetalService metalService;

	@Autowired
	private IColorService colorService;

	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;

	@Autowired
	private UserRoleService userRoleService;
	
	
	@Autowired
	private IReportFilterService reportFilterService;
	

	@Value("${companyName}")
	private String companyName;
	

	@ModelAttribute("metalInwardMt")
	public MetalInwardMt constructMt() {
		return new MetalInwardMt();
	}

	
	
	@ModelAttribute("metalInwardDt")
	public MetalInwardDt constructDt() {
		return new MetalInwardDt();
	}

	@RequestMapping("/metalInwardMt")
	public String users(Model model, Principal principal) {

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("metalInwardMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {

			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			return "metalInwardMt";

		} else {
			if (roleRights == null) {
				return "accesDenied";
			} else {
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());
			}
			return "metalInwardMt";
		}
	}
		

	@RequestMapping("/metalInwardMt/listing")
	@ResponseBody
	public String metalInwardMtListing(Model model, @RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search, Principal principal)
			throws ParseException {

		StringBuilder sb = new StringBuilder();
		Page<MetalInwardMt> metalInwardMts = null;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("metalInwardMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

		metalInwardMts = metalInwardMtService.searchAll(limit, offset, sort, order, search, true);

	

		sb.append("{\"total\":").append(metalInwardMts.getTotalElements()).append(",\"rows\": [");

		for (MetalInwardMt metalInwardMt : metalInwardMts) {

			Date currdate = sdf.parse(sdf.format(new Date()));
			Date invDate = sdf.parse(sdf.format(metalInwardMt.getInvDate()));

			sb.append("{\"id\":\"").append(metalInwardMt.getId()).append("\",\"invNo\":\"")
					.append(metalInwardMt.getInvNo()).append("\",\"invDate\":\"")
					.append(metalInwardMt.getInvDateStr())
					.append("\",\"beNo\":\"")
					.append((metalInwardMt.getBeNo() != null ? metalInwardMt.getBeNo() : ""))
					.append("\",\"beDate\":\"")
					.append((metalInwardMt.getBeDateStr() != null ? metalInwardMt.getBeDateStr() : ""))
					.append("\",\"party\":\"")
					.append((metalInwardMt.getParty() != null ? metalInwardMt.getParty().getPartyCode() : ""))
					.append("\",\"supplier\":\"")
					.append((metalInwardMt.getSupplier() != null ? metalInwardMt.getSupplier().getPartyCode() : ""))
					.append("\",\"inwardType\":\"")
					.append((metalInwardMt.getInwardType() != null ? metalInwardMt.getInwardType().getName() : ""))
					.append("\",\"remark\":\"")
					.append((metalInwardMt.getRemark() != null ? metalInwardMt.getRemark() : ""));

			sb.append("\",\"action1\":\"");
			if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
					|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
					|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {

				sb.append("<a href='/jewels/manufacturing/transactions/metalInwardMt/edit/")
						.append(metalInwardMt.getId()).append(".html'");
				sb.append(
						".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
				sb.append("\",\"action2\":\"");
				sb.append(
						"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/transactions/metalInwardMt/delete/")
						.append(metalInwardMt.getId());
				sb.append(".html' class='btn btn-xs btn-danger triggerRemove").append(metalInwardMt.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
				sb.append("\",\"action3\":\"");
				sb.append("<a href='/jewels/manufacturing/transactions/metalInwardMt/view/")
						.append(metalInwardMt.getId()).append(".html'");
				sb.append(
						" class='btn btn-xs btn-success' ><span class='glyphicon glyphicon-eye-open'></span>&nbsp;View</a>")
						.append("\"},");
			} else {
				if (roleRights.getCanEdit()) {

					if (currdate.equals(invDate)) {
						sb.append("<a href='/jewels/manufacturing/transactions/metalInwardMt/edit/")
								.append(metalInwardMt.getId()).append(".html'");

					} else {
						sb.append("<a onClick='javascript:displayBackDatedMsg(event, this)' href='javascript:void(0)'");
					}

				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(
						".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {

					if (currdate.equals(invDate)) {
						sb.append(
								"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/transactions/metalInwardMt/delete/")
								.append(metalInwardMt.getId());

					} else {
						sb.append("<a onClick='javascript:displayBackDatedMsg(event, this)' href='javascript:void(0)'");
					}

				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(".html' class='btn btn-xs btn-danger triggerRemove").append(metalInwardMt.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
				sb.append("\",\"action3\":\"");
				if (roleRights.getCanView()) {
					sb.append("<a href='/jewels/manufacturing/transactions/metalInwardMt/view/")
							.append(metalInwardMt.getId()).append(".html'");
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
	
	@RequestMapping("/metalInwardMt/add")
	public String add(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("metalInwardMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		model = populateModel(model,principal);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
			
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			
			return "metalInwardMt/add";
		}else{
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("canView", roleRights.getCanView());
		}

		

		return "metalInwardMt/add";
		}
		
	}

	private Model populateModel(Model model, Principal principal) {
		//Client
		model.addAttribute("clientMap", partyService.getExportClientPartyList());
		model.addAttribute("inwardTypeMap",inwardTypeService.getInwardTypeList());
		model.addAttribute("metalMap", metalService.getMetalList());
		model.addAttribute("colorMap", colorService.getColorList());
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		model.addAttribute("company",companyName);
		
		//Supplier
				model.addAttribute("partyMap", partyService.getExportClientPartyListForSupplier());
		
		model.addAttribute("adminRightsMap",userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
		userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE"));
		
		ReportFilter reportFilter = reportFilterService.findByName("metalInwardInvoice");
		model.addAttribute("xml",reportFilter.getXml());
		
		/*
		 * if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
		 * userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") ||
		 * userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
		 * 
		 * model.addAttribute("canEditTranddate", "true");
		 * 
		 * }else { model.addAttribute("canEditTranddate", "false"); }
		 */
		
		model.addAttribute("canEditTranddate", "false");
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String curDate = dateFormat.format(date);
		model.addAttribute("currentDate", curDate);
		
		
		
			return model;
	}

	@RequestMapping(value = "/metalInwardMt/add", method = RequestMethod.POST)
	public String addEditMetalInwardMt(
			@Valid @ModelAttribute("metalInwardMt") MetalInwardMt metalInwardMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "vTranDate", required = false) String vTranDate,		
			RedirectAttributes redirectAttributes, Principal principal) throws ParseException {

		synchronized (this) {
			
			String action = "added";
			String retVal = "redirect:/manufacturing/transactions/metalInwardMt/add.html";
			Date aDate = null;

			if (result.hasErrors()) {
				return "metalInwardMt/add";
			}
			
			if(vTranDate !=null && !vTranDate.isEmpty()){
				DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				Date dates = originalFormat.parse(vTranDate);
				
				metalInwardMt.setInvDate(dates);
				
				}

			if (id == null || id.equals("") || (id != null && id == 0)) {
				aDate = new java.util.Date();
				metalInwardMt.setCreatedBy(principal.getName());
				metalInwardMt.setCreatedDt(new java.util.Date());
				metalInwardMt.setUniqueId(aDate.getTime());
				
				Integer maxSrNo=metalInwardMtService.getMaxInvSrno();
				maxSrNo = (maxSrNo == null ? 1 : maxSrNo + 1);
				metalInwardMt.setSrNo(maxSrNo);
				
				
					int si = maxSrNo.toString().length();
				
				Calendar date = new GregorianCalendar();
				String vYear = "" + date.get(Calendar.YEAR);
				vYear = vYear.substring(2);
				
				String bagSr = null;
				
				switch (si) {
				case 1:
					bagSr = "000"+maxSrNo;
					break;
					
				case 2:
					bagSr = "00"+maxSrNo;
					break;
					
				case 3:
					bagSr = "0"+maxSrNo;
					break;
					
				default:
					bagSr = maxSrNo.toString();
					break;
				}
				
				if(!companyName.equalsIgnoreCase("nuance")) {
					metalInwardMt.setInvNo("IN-" + (bagSr) + "-" + vYear);
				}
				
			
				
				
			} else {
				MetalInwardMt metalInwardMt2 = metalInwardMtService.findOne(id);
				metalInwardMt.setSrNo(metalInwardMt2.getSrNo());
				metalInwardMt.setModiBy(principal.getName());
				metalInwardMt.setModiDt(new java.util.Date());
				
				if(!companyName.equalsIgnoreCase("nuance")) {
					metalInwardMt.setInvNo(metalInwardMt2.getInvNo());
				}

				action = "updated";
				retVal = "redirect:/manufacturing/transactions/metalInwardMt.html";
			}
			
			metalInwardMt.setRemark(metalInwardMt.getRemark().replaceAll("[\\n\\t\\r ]", " ").trim());
			metalInwardMtService.save(metalInwardMt);
			redirectAttributes.addFlashAttribute("success", true);
			redirectAttributes.addFlashAttribute("action", action);
			
			if (action.equals("added")) {
				MetalInwardMt metalMt = metalInwardMtService.findByUniqueId(metalInwardMt.getUniqueId());
				Integer metalId = metalMt.getId();
				retVal  = "redirect:/manufacturing/transactions/metalInwardMt/edit/"+metalId+".html";
			}

			return retVal;
			
		}
		
		
	}

	@RequestMapping("/metalInwardMt/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		MetalInwardMt metalInwardMt = metalInwardMtService.findOne(id);
		model.addAttribute("metalInwardMt", metalInwardMt);
		
		model = populateModel(model,principal);

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("metalInwardMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
			
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			
			return "metalInwardMt/add";
		}else{
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("canView", roleRights.getCanView());
		}

		return "metalInwardMt/add";
		}
	}
	
	@RequestMapping("/metalInwardMt/view/{id}")
	public String view(@PathVariable int id, Model model, Principal principal) {
		
		MetalInwardMt metalInwardMt = metalInwardMtService.findOne(id);
		model.addAttribute("metalInwardMt", metalInwardMt);
		model = populateModel(model,principal);
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("metalInwardMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
			
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			
			return "metalInwardMt/add";
		}else{
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("canView", roleRights.getCanView());
		}

		return "metalInwardMt/add";
		}
	}

	@RequestMapping("/metalInwardMt/delete/{id}")
	public String delete(@PathVariable int id) {
		
	/*	metalInwardMtService.delete(id);

		MetalInwardMt metalInwardMt = metalInwardMtService.findOne(id);

		Integer metalInwardDtId = null;
		Integer metalTranId = null;
		List<MetalInwardDt> metalInwardDt = metalInwardDtService
				.findByMetalInwardMt(metalInwardMt);
		List<MetalTran> metalTran;

		for (MetalInwardDt metal : metalInwardDt) {
			metalInwardDtId = metal.getId();
			metalInwardDtService.delete(metalInwardDtId);

			metalTran = metalTranService.findByRefTranIdAndTranTypeAndDeactive(metalInwardDtId, "Inward",false);

			for (MetalTran metTran : metalTran) {
				metalTranId = metTran.getId();
				metalTranService.delete(metalTranId);
			}

		}*/
		
		
		String retVal=metalInwardMtService.metalInwDelete(id);

		return	 "redirect:/manufacturing/transactions/metalInwardMt.html";
		 
	}

	@RequestMapping("/invoiceNoAvailable")
	@ResponseBody
	public String invoiceAvailable(@RequestParam String invNo,
			@RequestParam Integer id) {

		Boolean invoiceAvailable = true;

		if (id == null) {
			invoiceAvailable = (metalInwardMtService.findByInvNoAndDeactive(invNo, false) == null);
		} else {
			MetalInwardMt metalInward = metalInwardMtService.findOne(id);
			if (! (invNo.equalsIgnoreCase(metalInward.getInvNo())) ) {
				invoiceAvailable = (metalInwardMtService.findByInvNoAndDeactive(invNo, false) == null);
			}
		}

		return invoiceAvailable.toString();
	}

	@RequestMapping("/purity/list")
	@ResponseBody
	public String productSizeList(
			@RequestParam(value = "metalId") Integer metalId,
			@ModelAttribute("metalInwardMt") MetalInwardMt metalInwardMt) {

		return purityService.getPurityListDropDown(metalId);
	}

	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	
	
	@ResponseBody
	@RequestMapping("/metalInwardMt/partyWiseListing")
	public String popPartyWiseListing(
			@RequestParam(value = "partyIds") String partyIds,
			@RequestParam(value = "fromDate") String fromDate,
			@RequestParam(value = "toDate") String toDate) throws ParseException{
		
		
		List<Object[]> objects = metalInwardMtService.partyWiseAndDateWiseListing(partyIds, fromDate, toDate);
		
		StringBuilder sb = new StringBuilder();
		sb.append("{\"total\":").append(100000).append(",\"rows\": [");
		
		for (Object[] obj : objects) {
			int i=0;
			 for (Object element : obj) {
			    	if(i == 0){
			    		sb.append("{\"id\":\"");
			    		sb.append(element != null ? element.toString() : "");
			    	}else{
			    		sb.append("\",\"invNo\":\"")
						.append(element != null ?  element.toString() : "")
			    		.append("\"},");
			    	}
			    	i++;
			    }
			
			}
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		
		return str;
	}
	

}
