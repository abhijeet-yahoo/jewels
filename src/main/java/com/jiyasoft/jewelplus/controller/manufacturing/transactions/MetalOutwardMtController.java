package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalOutwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalOutwardMt;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IInwardTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IMetalOutwardMtService;


@RequestMapping("/manufacturing/transactions")
@Controller
public class MetalOutwardMtController {

	@Autowired
	private IMetalOutwardMtService metalOutwardMtService;
	
		
	
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
	private IDepartmentService departmentService;
	
	@ModelAttribute("metalOutwardMt")
	public MetalOutwardMt constructMt() {
		return new MetalOutwardMt();
	}

	@ModelAttribute("metalOutwardDt")
	public MetalOutwardDt constructDt() {
		return new MetalOutwardDt();
	}

	@RequestMapping("/metalOutwardMt")
	public String users(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("metalOutwardMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			return "metalOutwardMt";
		}else{
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("canView", roleRights.getCanView());
			
		}
		
		return "metalOutwardMt";
		}
	}

	@RequestMapping("/metalOutwardMt/listing")
	@ResponseBody
	public String metalOutwardMtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,Principal principal) throws ParseException {

		StringBuilder sb = new StringBuilder();
		Page<MetalOutwardMt> metalOutwardMts = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("metalOutwardMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}


		
		metalOutwardMts=metalOutwardMtService.searchAll(limit, offset, sort, order, search, true);
		

		sb.append("{\"total\":").append(metalOutwardMts.getTotalElements()).append(",\"rows\": [");

	
		
		for (MetalOutwardMt metalOutwardMt : metalOutwardMts) {
			
			Date currdate = sdf.parse(sdf.format(new Date()));
			Date invDate = sdf.parse(sdf.format(metalOutwardMt.getInvDate()));

			sb.append("{\"id\":\"")
					.append(metalOutwardMt.getId())
					.append("\",\"invNo\":\"")
					.append(metalOutwardMt.getInvNo())
					.append("\",\"invDate\":\"")
					.append(metalOutwardMt.getInvDateStr())
					.append("\",\"beNo\":\"")
					.append((metalOutwardMt.getBeNo() != null ? metalOutwardMt.getBeNo() : "" ))
					.append("\",\"beDate\":\"")
					.append((metalOutwardMt.getBeDateStr() != null ? metalOutwardMt.getBeDateStr() : ""))
					.append("\",\"party\":\"")
					.append((metalOutwardMt.getParty() != null  ? metalOutwardMt.getParty().getName() : ""))
					.append("\",\"deactive\":\"")
					.append(metalOutwardMt.getDeactive() ? "Yes" : "No");
					sb.append("\",\"action1\":\"");
					if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
							userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
							
							sb.append("<a href='/jewels/manufacturing/transactions/metalOutwardMt/edit/")
						  .append(metalOutwardMt.getId()).append(".html'");
						
					sb.append(".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
				
					sb.append("\",\"action2\":\"");
						
							sb.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/transactions/metalOutwardMt/delete/")
							.append(metalOutwardMt.getId());
					
					sb.append(".html' class='btn btn-xs btn-danger triggerRemove")
							.append(metalOutwardMt.getId())
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
					
					sb.append("\",\"action3\":\"");
							sb.append("<a href='/jewels/manufacturing/transactions/metalOutwardMt/view/")
								.append(metalOutwardMt.getId()).append(".html'");
						sb.append(" class='btn btn-xs btn-success' ><span class='glyphicon glyphicon-eye-open'></span>&nbsp;View</a>")	
			
						.append("\"},");
		}else
		{
			if (roleRights.getCanEdit()) {
				
					
					if(currdate.equals(invDate)){
						sb.append("<a href='/jewels/manufacturing/transactions/metalOutwardMt/edit/")
						  .append(metalOutwardMt.getId()).append(".html'");
						
					}else{
						sb.append("<a onClick='javascript:displayBackDatedMsg(event, this)' href='javascript:void(0)'");
					}
				
			
			} else {
				sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
			}
			sb.append(".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
		
			sb.append("\",\"action2\":\"");
			if(roleRights.getCanDelete()){
					
					if(currdate.equals(invDate)){
						sb.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/transactions/metalOutwardMt/delete/")
						.append(metalOutwardMt.getId());
					}else{
						sb.append("<a onClick='javascript:displayBackDatedMsg(event, this)' href='javascript:void(0)'");
					}
				
				
				
			}else {
				sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
			}
			sb.append(".html' class='btn btn-xs btn-danger triggerRemove")
					.append(metalOutwardMt.getId())
					.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
			
			sb.append("\",\"action3\":\"");
				if (roleRights.getCanView()) {
					sb.append("<a href='/jewels/manufacturing/transactions/metalOutwardMt/view/")
						.append(metalOutwardMt.getId()).append(".html'");
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

	@RequestMapping("/metalOutwardMt/add")
	public String add(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("metalOutwardMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		model = populateModel(model,principal);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
			
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			return "metalOutwardMt/add";
		}else{
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("canView", roleRights.getCanView());
		}
		

		return "metalOutwardMt/add";
		}
	}

	private Model populateModel(Model model, Principal principal) {
		model.addAttribute("partyMap", partyService.getPartyList());
		model.addAttribute("inwardTypeMap", inwardTypeService.getInwardTypeList());
		model.addAttribute("metalMap", metalService.getMetalList());
		model.addAttribute("colorMap", colorService.getColorList());
		model.addAttribute("departmentMap",departmentService.getDepartmentListForMetal());
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		model.addAttribute("adminRightsMap",userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE"));
		
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

	@RequestMapping(value = "/metalOutwardMt/add", method = RequestMethod.POST)
	public String addEditMetalOutwardMt(
			@Valid @ModelAttribute("metalOutwardMt") MetalOutwardMt metalOutwardMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal) {

		String action = "added";
		String retVal = "redirect:/manufacturing/transactions/metalOutwardMt/add.html";
	

		if (result.hasErrors()) {
			return "metalOutwardMt/add";
		}

		if (id == null || id.equals("") || (id != null && id == 0)) {
			
			
			Integer maxSrno = null;
			maxSrno = metalOutwardMtService.getMaxInvSrno();
			maxSrno = (maxSrno == null ? 0 : maxSrno);
			
			metalOutwardMt.setSrNo(++maxSrno);
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
			
			
			metalOutwardMt.setInvNo("MTLOUT/" + (bagSr) + "/" + presentYear+"-"+nextYear);
			
			metalOutwardMt.setCreatedBy(principal.getName());
			metalOutwardMt.setCreatedDt(new java.util.Date());
			metalOutwardMt.setUniqueId(new java.util.Date().getTime());
		} else {
			metalOutwardMt.setModiBy(principal.getName());
			metalOutwardMt.setModiDt(new java.util.Date());

			if (metalOutwardMt.getDeactive() != metalOutwardMtService.findOne(id)
					.getDeactive()) {
				metalOutwardMt.setDeactiveDt(new java.util.Date());
			} else {
				metalOutwardMt.setDeactiveDt(metalOutwardMtService.findOne(id)
						.getDeactiveDt());
			}
			
			if(metalOutwardMtService.findOne(id).getDeactive() == false){
				metalOutwardMt.setDeactive(false);
			}else{
				metalOutwardMt.setDeactive(true);
			}
			
			metalOutwardMt.setId(id);

			action = "updated";
			retVal = "redirect:/manufacturing/transactions/metalOutwardMt.html";
		}
		
		if(metalOutwardMt.getInvDate() == null){
			metalOutwardMt.setInvDate(new Date());
		}

		metalOutwardMtService.save(metalOutwardMt);
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);
		
		if (action.equals("added")) {
			MetalOutwardMt metalMt = metalOutwardMtService.findByUniqueId(metalOutwardMt.getUniqueId());
			Integer metalId = metalMt.getId();
			retVal  = "redirect:/manufacturing/transactions/metalOutwardMt/edit/"+metalId+".html";
		}

		return retVal;
	}

	@RequestMapping("/metalOutwardMt/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		MetalOutwardMt metalOutwardMt = metalOutwardMtService.findOne(id);
		model.addAttribute("metalOutwardMt", metalOutwardMt);
		
		model = populateModel(model,principal);
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("metalOutwardMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
			
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			return "metalOutwardMt/add";
		}else{
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("canView", roleRights.getCanView());
		}
		

		return "metalOutwardMt/add";
		}


	}
	
	@RequestMapping("/metalOutwardMt/view/{id}")
	public String view(@PathVariable int id, Model model, Principal principal) {
		
		MetalOutwardMt metalOutwardMt = metalOutwardMtService.findOne(id);
		model.addAttribute("metalOutwardMt", metalOutwardMt);
		model = populateModel(model,principal);
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("metalOutwardMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
			
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			return "metalOutwardMt/add";
		}else{
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("canView", roleRights.getCanView());
		}
		

		return "metalOutwardMt/add";
		}
	}

	@RequestMapping("/metalOutwardMt/delete/{id}")
	public String delete(@PathVariable int id) {
		
		String retVal=metalOutwardMtService.metalOutDelete(id);
		
		return "redirect:/manufacturing/transactions/metalOutwardMt.html";
	}

	@RequestMapping("mOutward/invoiceNoAvailable")
	@ResponseBody
	public String invoiceAvailable(@RequestParam String invNo,
			@RequestParam Integer id) {
		
		Boolean invoiceAvailable = true;

		if (id == null) {

			invoiceAvailable = (metalOutwardMtService.findByInvNo(invNo) == null);
		} else {
			MetalOutwardMt metalOutward = metalOutwardMtService.findOne(id);
			if (!(invNo.equalsIgnoreCase(metalOutward.getInvNo()))) {
				invoiceAvailable = (metalOutwardMtService.findByInvNo(invNo) == null);
			}
		}

		return invoiceAvailable.toString();
	}


	@RequestMapping("/purityy/list")
	@ResponseBody
	public String productSizeList(@RequestParam(value = "metalId") Integer metalId,
			@ModelAttribute("metalOutwardMt") MetalOutwardMt metalOutwardMt) {

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

}
