package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

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
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompOutwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompOutwardMt;
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
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICompOutwardMtService;


@RequestMapping("/manufacturing/transactions")
@Controller
public class CompOutwardMtController {

	@Autowired
	private ICompOutwardMtService compOutwardMtService;



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

	
	
	@ModelAttribute("compOutwardMt")
	public CompOutwardMt constructMt() {
		return new CompOutwardMt();
	}

	@ModelAttribute("compOutwardDt")
	public CompOutwardDt constructDt() {
		return new CompOutwardDt();
	}

	@RequestMapping("/compOutwardMt")
	public String users(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("compOutwardMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			return "compOutwardMt";
			
		}else
		{
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("canView", roleRights.getCanView());
		}
		
		return "compOutwardMt";
		}
	}

	@RequestMapping("/compOutwardMt/listing")
	@ResponseBody
	public String compOutwardMtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			Principal principal) throws ParseException {

		StringBuilder sb = new StringBuilder();
		Page<CompOutwardMt> compOutwardMts = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("compOutwardMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		// User loginUser = userService.findByName(principal.getName());

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

	
		
		compOutwardMts =  compOutwardMtService.searchAll(limit, offset, sort, order, search, true);

		sb.append("{\"total\":").append(compOutwardMts.getTotalElements()).append(",\"rows\": [");

		for (CompOutwardMt compOutwardMt : compOutwardMts) {
			
			Date currdate = sdf.parse(sdf.format(new Date()));
			Date invDate = sdf.parse(sdf.format(compOutwardMt.getInvDate()));
			
			sb.append("{\"id\":\"")
					.append(compOutwardMt.getId())
					.append("\",\"invNo\":\"")
					.append(compOutwardMt.getInvNo())
					.append("\",\"invDate\":\"")
					.append(compOutwardMt.getInvDateStr())
					.append("\",\"beNo\":\"")
					.append((compOutwardMt.getBeNo() != null ? compOutwardMt
							.getBeNo() : ""))
					.append("\",\"beDate\":\"")
					.append((compOutwardMt.getBeDateStr() != null ? compOutwardMt
							.getBeDateStr() : ""))
					.append("\",\"party\":\"")
					.append((compOutwardMt.getParty() != null ? compOutwardMt.getParty().getPartyCode() : ""))
					.append("\",\"inwardType\":\"")
					.append((compOutwardMt.getInwardType() != null ? compOutwardMt.getInwardType().getName() : ""))
					.append("\",\"deactive\":\"")
					.append(compOutwardMt.getDeactive() ? "Yes" : "No");
			
					sb.append("\",\"action1\":\"");
					if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
							userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
					
							sb.append("<a href='/jewels/manufacturing/transactions/compOutwardMt/edit/")
							  .append(compOutwardMt.getId()).append(".html'");	
								sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
				
					sb.append("\",\"action2\":\"");
							
							sb.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/transactions/compOutwardMt/delete/")
							.append(compOutwardMt.getId());
					sb.append(".html' class='btn btn-xs btn-danger triggerRemove")
							.append(compOutwardMt.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
					
					sb.append("\",\"action3\":\"");
							sb.append("<a href='/jewels/manufacturing/transactions/compOutwardMt/view/")
								.append(compOutwardMt.getId()).append(".html'");
						sb.append(" class='btn btn-xs btn-success' ><span class='glyphicon glyphicon-eye-open'></span>&nbsp;View</a>")
					
					.append("\"},");
		}else
		{
			if (roleRights.getCanEdit()) {
				
					if(currdate.equals(invDate)){
						sb.append("<a href='/jewels/manufacturing/transactions/compOutwardMt/edit/")
						  .append(compOutwardMt.getId()).append(".html'");
						
					}else{
						sb.append("<a onClick='javascript:displayBackDatedMsg(event, this)' href='javascript:void(0)'");
					}
				
				
			} else {
				sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
			}
			sb.append(" class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
		
			sb.append("\",\"action2\":\"");
			if(roleRights.getCanDelete()){
				
					if(currdate.equals(invDate)){
						sb.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/transactions/compOutwardMt/delete/")
						  .append(compOutwardMt.getId()).append(".html'");
						
					}else{
						sb.append("<a onClick='javascript:displayBackDatedMsg(event, this)' href='javascript:void(0)'");
					}
					
				
				
			}else {
				sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
			}
			sb.append(".html' class='btn btn-xs btn-danger triggerRemove")
					.append(compOutwardMt.getId())
					.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
			
			sb.append("\",\"action3\":\"");
				if (roleRights.getCanView()) {
					sb.append("<a href='/jewels/manufacturing/transactions/compOutwardMt/view/")
						.append(compOutwardMt.getId()).append(".html'");
				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(" class='btn btn-xs btn-success' ><span class='glyphicon glyphicon-eye-open'></span>&nbsp;View</a>")
			
			.append("\"},");
			}
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;

	}

	@RequestMapping("/compOutwardMt/add")
	public String add(Model model, Principal principal) {
		//User loginUser = userService.findByName(principal.getName());
		//model.addAttribute("canInsert", loginUser.getCanInsert());
		//model.addAttribute("canDelete", loginUser.getCanDelete());
		model = populateModel(model,principal);
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("compOutwardMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
			
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			
			return "compOutwardMt/add";
		}
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("canView", roleRights.getCanView());
		}

		

		return "compOutwardMt/add";
	}

	private Model populateModel(Model model,Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		model.addAttribute("partyMap", partyService.getPartyList());
		model.addAttribute("inwardTypeMap",inwardTypeService.getInwardTypeList());
		model.addAttribute("metalMap", metalService.getMetalList());
		model.addAttribute("colorMap", colorService.getColorList());
		model.addAttribute("componentMap", componentService.getComponentList());
		model.addAttribute("departmentMap",departmentService.getDepartmentListForCompNotCentral());
		
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
	


	@RequestMapping(value = "/compOutwardMt/add", method = RequestMethod.POST)
	public String addEditCompOutwardMt(
			@Valid @ModelAttribute("compOutwardMt") CompOutwardMt compOutwardMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "redirect:/manufacturing/transactions/compOutwardMt/add.html";
		Date aDate;
		
		if (result.hasErrors()) {
			return "compOutwardMt/add";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			aDate = new java.util.Date();
			
			
			Integer maxSrno = null;
			maxSrno = compOutwardMtService.getMaxCompOutInvSrno();
			maxSrno = (maxSrno == null ? 0 : maxSrno);
			
			compOutwardMt.setSrNo(++maxSrno);
			int si = maxSrno.toString().length();
			
			Calendar date = new GregorianCalendar();
			String vYear = "" + date.get(Calendar.YEAR);
			vYear = vYear.substring(2);
			
			Integer presentYear = Integer.parseInt(vYear);
			Integer nextYear = presentYear + 1;
			
			String bagSr = null;
			
			switch (si) {
			case 1:
				bagSr = "000"+maxSrno;
				break;
				
			case 2:
				bagSr = "00"+maxSrno;
				break;
				
			case 3:
				bagSr = "0"+maxSrno;
				break;
				
			default:
				bagSr = maxSrno.toString();
				break;
			}
			
			
			compOutwardMt.setInvNo("COMPOUT/" + (bagSr) + "/" + presentYear+"-"+nextYear);
			
			compOutwardMt.setCreatedBy(principal.getName());
			compOutwardMt.setCreatedDt(new java.util.Date());
			compOutwardMt.setUniqueId(aDate.getTime());
		} else {
			compOutwardMt.setModiBy(principal.getName());
			compOutwardMt.setModiDt(new java.util.Date());

			if (compOutwardMt.getDeactive() != compOutwardMtService.findOne(id)
					.getDeactive()) {
				compOutwardMt.setDeactiveDt(new java.util.Date());
			} else {
				compOutwardMt.setDeactiveDt(compOutwardMtService.findOne(id)
						.getDeactiveDt());
			}

			if (compOutwardMtService.findOne(id).getDeactive() == false) {
				compOutwardMt.setDeactive(false);
			} else {
				compOutwardMt.setDeactive(true);
			}
			
			

			compOutwardMt.setId(id);

			action = "updated";
			retVal = "redirect:/manufacturing/transactions/compOutwardMt.html";
		}
		
		if(compOutwardMt.getInvDate() == null){
			compOutwardMt.setInvDate(new Date());
		}

		compOutwardMtService.save(compOutwardMt);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);

		if(action.equals("added")){
			CompOutwardMt compOutwardMt2 = compOutwardMtService.findByUniqueId(compOutwardMt.getUniqueId());
			Integer compId= compOutwardMt2.getId();
			retVal  = "redirect:/manufacturing/transactions/compOutwardMt/edit/"+compId+".html";
		}
		
		return retVal;
	}

	@RequestMapping("/compOutwardMt/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		
		
		CompOutwardMt compOutwardMt = compOutwardMtService.findOne(id);
		model.addAttribute("compOutwardMt", compOutwardMt);
		model = populateModel(model,principal);
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("compOutwardMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
			
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			
			return "compOutwardMt/add";
			
		}else{
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("canView", roleRights.getCanView());
		}
		

		//User loginUser = userService.findByName(principal.getName());

		return "compOutwardMt/add";
		}
	}
	

	@RequestMapping("/compOutwardMt/view/{id}")
	public String view(@PathVariable int id, Model model, Principal principal) {
		
		CompOutwardMt compOutwardMt = compOutwardMtService.findOne(id);
		model.addAttribute("compOutwardMt", compOutwardMt);
		model = populateModel(model,principal);
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("compOutwardMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
			
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			
			return "compOutwardMt/add";
			
		}else{
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("canView", roleRights.getCanView());
		}
		

		return "compOutwardMt/add";
		}	
	}
	
	
	@RequestMapping("/compOutwardMt/delete/{id}")
	public String delete(@PathVariable int id) {
	
		String retVal = compOutwardMtService.compOutDelete(id);
		return "redirect:/manufacturing/transactions/compOutwardMt.html";
	}

	@RequestMapping("/cOutward/invoiceNoAvailable")
	@ResponseBody
	public String invoiceAvailable(@RequestParam String invNo,
			@RequestParam Integer id) {

		Boolean invoiceAvailable = true;

		if (id == null) {

			invoiceAvailable = (compOutwardMtService.findByInvNo(invNo) == null);
		} else {
			CompOutwardMt compOutward = compOutwardMtService.findOne(id);
			if (!(invNo.equalsIgnoreCase(compOutward.getInvNo()))) {
				invoiceAvailable = (compOutwardMtService.findByInvNo(invNo) == null);
			}
		}

		return invoiceAvailable.toString();
	}

	@RequestMapping("/purityCompOut/list")
	@ResponseBody
	public String productSizeList(
			@RequestParam(value = "metalId") Integer metalId,
			@ModelAttribute("compOutwardMt") CompOutwardMt compOutwardMt) {

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
	@RequestMapping("/compOutwardMt/partyWiseListing")
	public String popPartyWiseListing(
			@RequestParam(value = "partyIds") String partyIds,
			@RequestParam(value = "fromDate") String fromDate,
			@RequestParam(value = "toDate") String toDate) throws ParseException{
		
		
		List<Object[]> objects = compOutwardMtService.partyWiseAndDateWiseListing(partyIds, fromDate, toDate);
		
		StringBuilder sb = new StringBuilder();
		sb.append("{\"total\":").append(objects.size()).append(",\"rows\": [");
		
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
