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
import java.util.Map;

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
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompInwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompInwardMt;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IComponentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IInwardTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.reports.IReportFilterService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompInwardMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class CompInwardMtController {

	@Autowired
	private ICompInwardMtService compInwardMtService;
	
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
	private IComponentService componentService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;

	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	
	@Autowired
	private IReportFilterService reportFilterService;
	
	
	@Value("${companyName}")
	private String companyName;

	@ModelAttribute("compInwardMt")
	public CompInwardMt constructMt() {
		return new CompInwardMt();
	}

	@ModelAttribute("compInwardDt")
	public CompInwardDt constructDt() {
		return new CompInwardDt();
	}

	@RequestMapping("/compInwardMt")
	public String users(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("compInwardMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		

			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			return "compInwardMt";
		
		}else{
	 if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("canView", roleRights.getCanView());
		}
		return "compInwardMt";
		}
	}

	@RequestMapping("/compInwardMt/listing")
	@ResponseBody
	public String compInwardMtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			Principal principal) throws ParseException {

			StringBuilder sb = new StringBuilder();
			Page<CompInwardMt> compInwardMts = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			User user = userService.findByName(principal.getName());
			UserRole userRole = userRoleService.findByUser(user);
			MenuMast menuMast = menuMastService.findByMenuNm("compInwardMt");
			RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
			
		
	
			if ((search != null) && (search.trim().length() == 0)) {
				search = null;
			}
	

			compInwardMts=compInwardMtService.searchAll(limit, offset, sort, order, search, true);
	
			sb.append("{\"total\":").append(compInwardMts.getTotalElements()).append(",\"rows\": [");
	
			for (CompInwardMt compInwardMt : compInwardMts) {
				
				Date currdate = sdf.parse(sdf.format(new Date()));
				Date invDate = sdf.parse(sdf.format(compInwardMt.getInvDate()));
				
				sb.append("{\"id\":\"")
				.append(compInwardMt.getId())
				.append("\",\"invNo\":\"")
				.append(compInwardMt.getInvNo())
				.append("\",\"invDate\":\"")
				.append(compInwardMt.getInvDateStr())
				.append("\",\"beNo\":\"")
				.append((compInwardMt.getBeNo() != null ? compInwardMt.getBeNo() : ""))
				.append("\",\"beDate\":\"")
				.append((compInwardMt.getBeDateStr() != null ? compInwardMt.getBeDateStr() : ""))
				.append("\",\"party\":\"")
				.append((compInwardMt.getParty() != null ? compInwardMt.getParty().getPartyCode(): ""))
				.append("\",\"supplier\":\"")
				.append((compInwardMt.getSupplier() != null ? compInwardMt.getSupplier().getPartyCode() : ""))
				.append("\",\"inwardType\":\"")
				.append((compInwardMt.getInwardType() != null ? compInwardMt.getInwardType().getName() : ""))
				.append("\",\"deactive\":\"")
				.append(compInwardMt.getDeactive() ? "Yes" : "No");
		
				sb.append("\",\"action1\":\"");
				
				
				
				if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
						userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
								
							sb.append("<a href='/jewels/manufacturing/transactions/compInwardMt/edit/")
						  .append(compInwardMt.getId()).append(".html'");
							sb.append(".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
				
							sb.append("\",\"action2\":\"");
							sb.append("<a href='javascript:deleteCompInwardMt(event,");					
							sb.append(compInwardMt.getId());
							sb.append(");' class='btn btn-xs btn-danger triggerRemove").append(compInwardMt.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
									
							sb.append("\",\"action3\":\"");
							sb.append("<a href='/jewels/manufacturing/transactions/compInwardMt/view/")
								.append(compInwardMt.getId()).append(".html'");
						sb.append(" class='btn btn-xs btn-success' ><span class='glyphicon glyphicon-eye-open'></span>&nbsp;View</a>")	;
				}else
				{
				if (roleRights.getCanEdit()) {
					
						
						if(currdate.equals(invDate)){
							sb.append("<a href='/jewels/manufacturing/transactions/compInwardMt/edit/")
							  .append(compInwardMt.getId()).append(".html'");
							
						}else{
							sb.append("<a onClick='javascript:displayBackDatedMsg(event, this)' href='javascript:void(0)'");
						}
					
					
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
			
				
				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {

					if (currdate.equals(invDate)) {
						sb.append("<a href='javascript:deleteCompInwardMt(event,")	
						.append(compInwardMt.getId()).append(".html'");

					} else {
						sb.append("<a onClick='javascript:displayBackDatedMsg(event, this)' href='javascript:void(0)'");
					}

				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(".html' class='btn btn-xs btn-danger triggerRemove").append(compInwardMt.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
				
				
				
				sb.append("\",\"action3\":\"");
					if (roleRights.getCanView()) {
						sb.append("<a href='/jewels/manufacturing/transactions/compInwardMt/view/")
							.append(compInwardMt.getId()).append(".html'");
					} else {
						sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
					}
					sb.append(" class='btn btn-xs btn-success' ><span class='glyphicon glyphicon-eye-open'></span>&nbsp;View</a>")	;
				}
				sb.append("\"},");
	}
	
	String str = sb.toString();
	str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
			: str);
	str += "]}";
	
	return str;
	}

	@RequestMapping("/compInwardMt/add")
	public String add(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("compInwardMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
			
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			model = populateModel(model,principal);
			return "compInwardMt/add";
		}
		
		else if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("canView", roleRights.getCanView());
			model = populateModel(model,principal);

			return "compInwardMt/add";
		}
		
		
	}

	private Model populateModel(Model model, Principal principal) {
		//Client
		model.addAttribute("clientMap", partyService.getExportClientPartyList());
		model.addAttribute("inwardTypeMap",	inwardTypeService.getInwardTypeList());
		model.addAttribute("metalMap", metalService.getMetalList());
		model.addAttribute("colorMap", colorService.getColorList());
		model.addAttribute("componentMap", componentService.getComponentList());
		model.addAttribute("company",companyName);
		
		//Supplier
		model.addAttribute("partyMap", partyService.getExportClientPartyListForSupplier());
				
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		model.addAttribute("adminRightsMap", userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE"));
		
		/*
		 * if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
		 * userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") ||
		 * userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
		 * 
		 * model.addAttribute("canEditTranddate", "true");
		 * 
		 * }else { model.addAttribute("canEditTranddate", "false"); }
		 * 
		 */
		model.addAttribute("canEditTranddate", "false");
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String curDate = dateFormat.format(date);
		model.addAttribute("currentDate", curDate);
		
		model.addAttribute("departmentMap",departmentService.getDepartmentListForCompNotCentral());
	
		ReportFilter reportFilter = reportFilterService.findByName("compInwardInvoice");
		model.addAttribute("xml",reportFilter.getXml());

		return model;
	}

	@RequestMapping(value = "/compInwardMt/add", method = RequestMethod.POST)
	public String addEditCompInwardMt(
			@Valid @ModelAttribute("compInwardMt") CompInwardMt compInwardMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "vTranDate", required = false) String vTranDate,		
			RedirectAttributes redirectAttributes, Principal principal) throws ParseException {

		synchronized (this) {
			
			String action = "added";
			String retVal = "redirect:/manufacturing/transactions/compInwardMt/add.html";
			Date aDate;
			
			
			if(vTranDate !=null && !vTranDate.isEmpty()){
				DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				Date dates = originalFormat.parse(vTranDate);
				
				compInwardMt.setInvDate(dates);
				
				}

			if (result.hasErrors()) {
				return "compInwardMt/add";
			}

			if (id == null || id.equals("") || (id != null && id == 0)) {
				aDate = new java.util.Date();
				compInwardMt.setCreatedBy(principal.getName());
				compInwardMt.setCreatedDt(new java.util.Date());
				compInwardMt.setUniqueId(aDate.getTime());
				
				Integer maxSrNo=compInwardMtService.getMaxInvSrno();
				maxSrNo = (maxSrNo == null ? 1 : maxSrNo + 1);
				compInwardMt.setSrNo(maxSrNo);
				
				
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
					compInwardMt.setInvNo("IN-" + (bagSr) + "-" + vYear);	
				}
				
				
			} else {
				CompInwardMt compInwardMt2 =compInwardMtService.findOne(id);
				compInwardMt.setSrNo(compInwardMt2.getSrNo());
				compInwardMt.setModiBy(principal.getName());
				compInwardMt.setModiDt(new java.util.Date());
				
				if(!companyName.equalsIgnoreCase("nuance")) {
					compInwardMt.setInvNo(compInwardMt2.getInvNo());
				}

				action = "updated";
				retVal = "redirect:/manufacturing/transactions/compInwardMt.html";
			}
			

			compInwardMtService.save(compInwardMt);
			redirectAttributes.addFlashAttribute("success", true);
			redirectAttributes.addFlashAttribute("action", action);
			
			if (action.equals("added")) {
				CompInwardMt compInward = compInwardMtService.findByUniqueId(compInwardMt.getUniqueId());
				Integer compId = compInward.getId();
				retVal  = "redirect:/manufacturing/transactions/compInwardMt/edit/"+compId+".html";
			}

			return retVal;
			
		}
		
	}

	@RequestMapping("/compInwardMt/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		CompInwardMt compInwardMt = compInwardMtService.findOne(id);
		model.addAttribute("compInwardMt", compInwardMt);

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("compInwardMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
			
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			model = populateModel(model,principal);
			return "compInwardMt/add";
		}
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("canView", roleRights.getCanView());
			model = populateModel(model,principal);

			return "compInwardMt/add";
		}
		
	}
	
	@RequestMapping("/compInwardMt/view/{id}")
	public String view(@PathVariable int id, Model model, Principal principal) {
		
		CompInwardMt compInwardMt = compInwardMtService.findOne(id);
		model.addAttribute("compInwardMt", compInwardMt);
		model = populateModel(model,principal);
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("compInwardMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
			
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			model = populateModel(model,principal);
			return "compInwardMt/add";
		}
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("canView", roleRights.getCanView());
			model = populateModel(model,principal);

			return "compInwardMt/add";
		}
	}

	@RequestMapping("/compInwardMt/delete/{id}")
	public String delete(@PathVariable int id, Principal principal) {
	
		
		String retVal = compInwardMtService.compInwDelete(id,principal);
		
		if(retVal.equalsIgnoreCase("-1")){
			return retVal;
		}

		return "redirect:/manufacturing/transactions/compInwardMt.html";
	}

	@RequestMapping("/cInward/invoiceNoAvailable")
	@ResponseBody
	public String invoiceAvailable(@RequestParam String invNo,
			@RequestParam Integer id) {

		Boolean invoiceAvailable = true;

		if (id == null) {

			invoiceAvailable = (compInwardMtService.findByInvNoAndDeactive(invNo, false) == null);
		} else {
			CompInwardMt compInward = compInwardMtService.findOne(id);
			if (!(invNo.equalsIgnoreCase(compInward.getInvNo()))) {
				invoiceAvailable = (compInwardMtService.findByInvNoAndDeactive(invNo, false) == null);
			}
		}

		return invoiceAvailable.toString();
	}

	@RequestMapping("/purityComp/list")
	@ResponseBody
	public String productSizeList(
			@RequestParam(value = "metalId") Integer metalId,
			@ModelAttribute("compInwardMt") CompInwardMt compInwardMt) {

		StringBuilder sb = new StringBuilder();
		Map<Integer, String> purityMap = purityService.getPurityList(metalId);

		sb.append("<select id=\"purity.id\" name=\"purity.id\" class=\"form-control\">");
		sb.append("<option value=\"\">- Select Purity -</option>");
		for (Object key : purityMap.keySet()) {
			sb.append("<option value=\"").append(key.toString()).append("\">")
					.append(purityMap.get(key)).append("</option>");
		}
		sb.append("</select>");

		return sb.toString();
	}

	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	
	
	
	@ResponseBody
	@RequestMapping("/compInwardMt/partyWiseListing")
	public String popPartyWiseListing(
			@RequestParam(value = "partyIds") String partyIds,
			@RequestParam(value = "fromDate") String fromDate,
			@RequestParam(value = "toDate") String toDate) throws ParseException{
		
		
		List<Object[]> objects = compInwardMtService.partyWiseAndDateWiseListing(partyIds, fromDate, toDate);
		
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
