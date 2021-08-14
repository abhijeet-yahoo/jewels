package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.FgIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.FgIssMt;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IUserDeptTrfRightService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IFgIssDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IFgIssMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class FgIssController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private RoleRightsService roleRightsService;
	
	@Autowired
	private IFgIssMtService fgIssMtService;
	
	@Autowired
	private IFgIssDtService fgIssDtService;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IUserDeptTrfRightService userDeptTrfRightService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	
	@ModelAttribute("fgIssMt")
	public FgIssMt constructMt() {
		return new FgIssMt();
	}

	@ModelAttribute("fgIssDt")
	public FgIssDt constructDt() {
		return new FgIssDt();
	}
	

	@RequestMapping("/fgIssMt")
	public String users(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("fgIssMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			return "fgIssMt";
		} else {
			if (roleRights == null) {
				return "accesDenied";
			} else {
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());

			}
			return "fgIssMt";
		}
	}
	
	
	@RequestMapping("/fgIssMt/listing")
	@ResponseBody
	public String fgIssMtListing(Model model, @RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search, Principal principal)
			throws ParseException {

		StringBuilder sb = new StringBuilder();
		Page<FgIssMt> fgIssMts = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("fgIssMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}

	
		fgIssMts = fgIssMtService.searchAll(limit, offset, sort, order, search, true);

		sb.append("{\"total\":").append(fgIssMts.getTotalElements()).append(",\"rows\": [");

		for (FgIssMt fgIssMt : fgIssMts) {
			Date currdate = sdf.parse(sdf.format(new Date()));
			Date invDate = sdf.parse(sdf.format(fgIssMt.getInvDate()));

			sb.append("{\"id\":\"").append(fgIssMt.getId())
			.append("\",\"invNo\":\"")
			.append(fgIssMt.getInvNo())
			.append("\",\"invDate\":\"")
			.append(fgIssMt.getInvDateStr())
			.append("\",\"deptNm\":\"")
			.append(fgIssMt.getDepartment().getName())
			
			.append("\",\"action1\":\"");
					if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
							|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
							|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
		
						sb.append("<a href='/jewels/manufacturing/transactions/fgIssMt/edit/")
								.append(fgIssMt.getId()).append(".html'");
		
						sb.append(
								".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
		
						sb.append("\",\"action2\":\"");
		
						sb.append("<a href='javascript:deleteFgIssMt(event,");					
								sb.append(fgIssMt.getId());
		
						sb.append(");' class='btn btn-xs btn-danger triggerRemove").append(fgIssMt.getId())
								.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
	
						.append("\"},");
			} else {

				if (roleRights.getCanEdit()) {

					sb.append("<a href='/jewels/manufacturing/transactions/fgIssMt/edit/")
					.append(fgIssMt.getId()).append(".html'");
		

				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(
						".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");

				sb.append("\",\"action2\":\"");
				if (roleRights.getCanDelete()) {

					if (currdate.equals(invDate)) {
						sb.append("<a href='javascript:deleteFgIssMt(event,")	
						.append(fgIssMt.getId()).append(".html'");

					} else {
						sb.append("<a onClick='javascript:displayBackDatedMsg(event, this)' href='javascript:void(0)'");
					}

				} else {
					sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
				}
				sb.append(".html' class='btn btn-xs btn-danger triggerRemove").append(fgIssMt.getId())
						.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
						.append("\"},");
			}
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";

		
		return str;

	}
	
	
	
	@RequestMapping("/fgIssMt/add")
	public String add(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("fgIssMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
	
		
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			model.addAttribute("canEditTranddate", "false");
			
			Department department =departmentService.findByName("Marketing");
			
			model.addAttribute("deptMap",userDeptTrfRightService.getToDepartmentListFromUser(user.getId(),department.getId()));
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String curDate = dateFormat.format(date);
			model.addAttribute("currentDate", curDate);
			
			
			return "fgIssMt/add";
			
		}else{
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("canView", roleRights.getCanView());
			
		}
		
		
		Department department =departmentService.findByName("Marketing");
		
		model.addAttribute("deptMap",userDeptTrfRightService.getToDepartmentListFromUser(user.getId(),department.getId()));
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String curDate = dateFormat.format(date);
		model.addAttribute("currentDate", curDate);
		
		
		model.addAttribute("canEditTranddate", "false");
		return "fgIssMt/add";
		}
	}
	
	
	
	@RequestMapping("/fgIssMt/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("fgIssMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		FgIssMt fgIssMt = fgIssMtService.findOne(id);
		model.addAttribute("fgIssMt", fgIssMt);
		
		Department department =departmentService.findByName("Marketing");
		
		model.addAttribute("deptMap",userDeptTrfRightService.getToDepartmentListFromUser(user.getId(),department.getId()));
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String curDate = dateFormat.format(date);
		model.addAttribute("currentDate", curDate);
		
		
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			model.addAttribute("canEditTranddate", "false");
			model.addAttribute("mtid", id);
			
			
			return "fgIssMt/add";
			
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
		

		return "fgIssMt/add";
		}
	}
	
	
	
	@RequestMapping(value = "/fgIssMt/add", method = RequestMethod.POST)
	public String addEditFgIssMt(
			@Valid @ModelAttribute("fgIssMt") FgIssMt fgIssMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal,
			@RequestParam(value = "pLocationIds", required = false) Integer pLocationIds,
			@RequestParam(value = "vTranDate", required = false) String vTranDate) throws ParseException {

		String action = "Added";
		String retVal = "redirect:/manufacturing/transactions/fgIssMt/add.html";
	
	
		
	
		if (result.hasErrors()) {
			return "fgIssMt/add";
		}

		
		synchronized (this) {
			
			if(vTranDate !=null && !vTranDate.isEmpty()){
				DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				Date dates = originalFormat.parse(vTranDate);
				
				fgIssMt.setInvDate(dates);
				
				}
			
			
			if (id == null || id.equals("") || (id != null && id == 0)) {
				
				Integer maxSrno = null;
				maxSrno = fgIssMtService.getMaxInvSrno();
				maxSrno = (maxSrno == null ? 0 : maxSrno);
				
				fgIssMt.setInvSrNo(++maxSrno);
				int si = maxSrno.toString().length();
				
				Calendar date = new GregorianCalendar();
				String vYear = "" + date.get(Calendar.YEAR);
				vYear = vYear.substring(2);
				
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
				
				
				fgIssMt.setInvNo("FG-" + (bagSr) + "-" + vYear);
				fgIssMt.setCreatedBy(principal.getName());
				fgIssMt.setCreatedDt(new java.util.Date());
				

			} else {
				
				if(pLocationIds != null) {
			
					Department department = departmentService.findOne(pLocationIds);
					fgIssMt.setDepartment(department);
				}
				
				fgIssMt.setModiBy(principal.getName());
				fgIssMt.setModiDt(new java.util.Date());

		


				

				action = "Updated";
			//	retVal = "redirect:/manufacturing/transactions/fgIssMt.html";
				
				 retVal = "redirect:/manufacturing/transactions/fgIssMt/edit/"+id+".html";
			}
			


			
			fgIssMtService.save(fgIssMt);
			
			if (action.equals("Added")) {
			 retVal = "redirect:/manufacturing/transactions/fgIssMt/edit/"+fgIssMt.getId()+".html";
			}
			
			
			redirectAttributes.addFlashAttribute("success", true);
			redirectAttributes.addFlashAttribute("action", action);
		
			return retVal;
			
		}
		
		

		
	}
	
	
	
	
	@RequestMapping("/fgBagTransfer")
	public String addScreen(Model model){
		
		Department department = departmentService.findByName("Finish Goods");
		model.addAttribute("deptId", department.getId());
		
		return "fgBagTransfer";
	}
	
	
	
	@RequestMapping("/fgTransfer/listing")
	@ResponseBody
	public String fgTransferList(@RequestParam(value = "departmentId", required = false) Integer deptId){
		
		
		List<Object[]> objects =new ArrayList<Object[]>();
		
		String departmentCond ="";
		
			departmentCond = " deptId in (" + deptId + ")  ";

		
			@SuppressWarnings("unchecked")
			TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_departmentBaglistFgTransfer(?) }");
			query.setParameter(1, departmentCond);
			objects = query.getResultList();
		
		
		
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(objects.size()).append(",\"rows\": [");
		 
		 for(Object[] list:objects){
			 
				
				sb.append("{\"party\":\"")
			     .append(list[3] != null ? list[3] : "")
			     .append("\",\"orderNo\":\"")
				 .append(list[4] != null ? list[4] : "")
				 .append("\",\"refNo\":\"")
				 .append(list[5] != null ? list[5] : "")
				 .append("\",\"bagNo\":\"")
				 .append(list[6] != null ? list[6] : "")
				 .append("\",\"bagId\":\"")
				 .append(list[8] != null ? list[8] : "")
				 .append("\",\"id\":\"")
				 .append(list[8] != null ? list[8] : "")
				 .append("\",\"styleNo\":\"")
				 .append(list[7] != null ? list[7] : "")
				 .append("\",\"qty\":\"")
				 .append(list[2] != null ? list[2] : "")
				 .append("\",\"grossWt\":\"")
				 .append(list[1] != null ? list[1] : "")
				 .append("\",\"image\":\"")
				 .append(list[9] != null ? list[9] : "")
				 .append("\",\"barcode\":\"")
				 .append(list[10] != null ? list[10] : "")
				 .append("\",\"mtId\":\"")
				 .append(list[11] != null ? list[11] : "")
				 .append("\"},");
				
			}
		   
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
			
		return str;
		
	
}	
	
	
	
	
	@RequestMapping("/fgTransferInStock")
	@ResponseBody
	public String fgTransferInStock(@RequestParam(value = "vMtId", required = false) String vMtId,
			@RequestParam(value = "fgIssMtId", required = false) Integer fgIssMtId,
			Principal principal) throws ParseException {

		String retVal = "-1";
	
	
		
		synchronized (this) {
			
			try {
				retVal =fgIssDtService.fgTransferInStock(principal,vMtId,fgIssMtId);	
				
			} catch (Exception e) {
				System.out.println(e);
			}
				
					
				
			
			
		}
		
	return retVal;

	}
	
	
	
	@RequestMapping("/fgIssDt/listing")
	@ResponseBody
	public String fgIssDtList(@RequestParam(value = "mtId", required = false) Integer mtId){
		
		
		List<Object[]> objects =new ArrayList<Object[]>();
		
	
		
			@SuppressWarnings("unchecked")
			TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_fgIssDtList(?) }");
			query.setParameter(1, mtId);
			objects = query.getResultList();
		
		
		
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(objects.size()).append(",\"rows\": [");
		 
		 for(Object[] list:objects){
			 
				
				sb.append("{\"party\":\"")
			     .append(list[3] != null ? list[3] : "")
			     .append("\",\"orderNo\":\"")
				 .append(list[4] != null ? list[4] : "")
				 .append("\",\"refNo\":\"")
				 .append(list[5] != null ? list[5] : "")
				 .append("\",\"bagNo\":\"")
				 .append(list[6] != null ? list[6] : "")
				 .append("\",\"bagId\":\"")
				 .append(list[8] != null ? list[8] : "")
				 .append("\",\"styleNo\":\"")
				 .append(list[7] != null ? list[7] : "")
				 .append("\",\"qty\":\"")
				 .append(list[2] != null ? list[2] : "")
				 .append("\",\"grossWt\":\"")
				 .append(list[1] != null ? list[1] : "")
				 .append("\",\"image\":\"")
				 .append(list[9] != null ? list[9] : "")
				 .append("\",\"barcode\":\"")
				 .append(list[10] != null ? list[10] : "")
				 .append("\",\"mtId\":\"")
				 .append(list[11] != null ? list[11] : "")
				 .append("\",\"totCarat\":\"")
				 .append(list[12] != null ? list[12] : "")
				 .append("\",\"totStone\":\"")
				 .append(list[13] != null ? list[13] : "")
				 .append("\"},");
				
			}
		   
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
			
		return str;
		
	
}	
	
	
	
	@RequestMapping("/fgIssMtApp")
	public String fgIssMtApp(Model model){
		return "fgIssMtApp";
	}
	
	
	@RequestMapping("/fgIssMt/appListing")
	@ResponseBody
	public String fgIssDtAppList(){
		
		
		List<Object[]> objects =new ArrayList<Object[]>();
		
	
		
			@SuppressWarnings("unchecked")
			TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_fgIssDtPendingApproveList() }");
			objects = query.getResultList();
		
		
		
		
		String str="";
		 StringBuilder sb = new StringBuilder();
		 sb.append("{\"total\":").append(objects.size()).append(",\"rows\": [");
		 
		 for(Object[] list:objects){
			 
				
				sb.append("{\"party\":\"")
			     .append(list[3] != null ? list[3] : "")
			     .append("\",\"orderNo\":\"")
				 .append(list[4] != null ? list[4] : "")
				 .append("\",\"refNo\":\"")
				 .append(list[5] != null ? list[5] : "")
				 .append("\",\"bagNo\":\"")
				 .append(list[6] != null ? list[6] : "")
				 .append("\",\"bagId\":\"")
				 .append(list[8] != null ? list[8] : "")
				 .append("\",\"styleNo\":\"")
				 .append(list[7] != null ? list[7] : "")
				 .append("\",\"qty\":\"")
				 .append(list[2] != null ? list[2] : "")
				 .append("\",\"grossWt\":\"")
				 .append(list[1] != null ? list[1] : "")
				 .append("\",\"image\":\"")
				 .append(list[9] != null ? list[9] : "")
				 .append("\",\"barcode\":\"")
				 .append(list[10] != null ? list[10] : "")
				 .append("\",\"mtId\":\"")
				 .append(list[11] != null ? list[11] : "")
				 .append("\",\"totCarat\":\"")
				 .append(list[12] != null ? list[12] : "")
				 .append("\",\"totStone\":\"")
				 .append(list[13] != null ? list[13] : "")
				 .append("\",\"vouNo\":\"")
				 .append(list[14] != null ? list[14] : "")
				 .append("\",\"location\":\"")
				 .append(list[15] != null ? list[15] : "")
				 .append("\",\"fgIssDtId\":\"")
				 .append(list[16] != null ? list[16] : "")
				 .append("\"},");
				
			}
		   
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
			
		return str;
		
	
}	
	
	
	@RequestMapping("/fgIssApprove")
	@ResponseBody
	public String fgIssApprove(@RequestParam(value = "vFgIssDtIds", required = false) String vFgIssDtIds,
					Principal principal) throws ParseException {

		String retVal = "-1";
	
	
		
		synchronized (this) {
			
			try {
				retVal =fgIssDtService.fgApproval(principal,vFgIssDtIds);	
				
			} catch (Exception e) {
				System.out.println(e);
			}
				
					
				
			
			
		}
		
	return retVal;

	}
	
	
	
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	
}
