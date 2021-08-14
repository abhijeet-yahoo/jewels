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
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairIssMt;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IUserDeptTrfRightService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IRepairIssDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IRepairIssMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class RepairIssController {
	
	@Autowired
	private IRepairIssMtService repairIssMtService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private RoleRightsService roleRightsService;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IUserDeptTrfRightService userDeptTrfRightService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private IRepairIssDtService repairIssDtService;
	
	
	@ModelAttribute("repairIssMt")
	public RepairIssMt constructMt() {
		return new RepairIssMt();
	}

	@ModelAttribute("repairIssDt")
	public RepairIssDt constructDt() {
		return new RepairIssDt();
	}
	
	

	@RequestMapping("/repairIssMt")
	public String users(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("repairIssMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			return "repairIssMt";
		} else {
			if (roleRights == null) {
				return "accesDenied";
			} else {
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());

			}
			return "repairIssMt";
		}
	}
	
	
	@RequestMapping("/repairIssMt/add")
	public String add(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("repairIssMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);		
		
		Department department =departmentService.findByName("Marketing");
		
		model.addAttribute("deptMap",userDeptTrfRightService.getToDepartmentListFromUserForRepairIss(user.getId(),department.getId()));
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
			
			return "repairIssMt/add";
			
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
		return "repairIssMt/add";
		}
	}
	

	@RequestMapping("/repairIssMt/listing")
	@ResponseBody
	public String repairIssMtListings(Model model, @RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search, Principal principal)
			throws ParseException {

		return repairIssMtService.repairIssMtListing(model, limit, offset, sort, order, search, principal);
	}


	
	
	@RequestMapping("/repairIssMt/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("repairIssMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		RepairIssMt repairIssMt = repairIssMtService.findOne(id);
		model.addAttribute("repairIssMt", repairIssMt);
		
		Department department =departmentService.findByName("Marketing");
		
		model.addAttribute("deptMap",userDeptTrfRightService.getToDepartmentListFromUserForRepairIss(user.getId(),department.getId()));
		
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
			
			
			return "repairIssMt/add";
			
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
		

		return "repairIssMt/add";
		}
	}
	
	
	
	@RequestMapping(value = "/repairIssMt/add", method = RequestMethod.POST)
	public String addEditRepairIssMt(
			@Valid @ModelAttribute("repairIssMt") RepairIssMt repairIssMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal,
			@RequestParam(value = "pLocationIds", required = false) Integer pLocationIds,
			@RequestParam(value = "vTranDate", required = false) String vTranDate) throws ParseException {

		String action = "Added";
		String retVal = "redirect:/manufacturing/transactions/repairIssMt/add.html";
	
	
		
	
		if (result.hasErrors()) {
			return "repairIssMt/add";
		}

		
		synchronized (this) {
			
			if(vTranDate !=null && !vTranDate.isEmpty()){
				DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				Date dates = originalFormat.parse(vTranDate);
				
				repairIssMt.setInvDate(dates);
				
				}
			
			
			if (id == null || id.equals("") || (id != null && id == 0)) {
				
				Integer maxSrno = null;
				maxSrno = repairIssMtService.getMaxInvSrno();
				maxSrno = (maxSrno == null ? 0 : maxSrno);
				
				repairIssMt.setInvSrNo(++maxSrno);
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
				
				
				repairIssMt.setInvNo("REPAIR-" + (bagSr) + "-" + vYear);
				repairIssMt.setCreatedBy(principal.getName());
				repairIssMt.setCreatedDt(new java.util.Date());
				

			} else {
				repairIssMt.setModiBy(principal.getName());
				repairIssMt.setModiDt(new java.util.Date());

				if(pLocationIds != null) {
					
					Department department = departmentService.findOne(pLocationIds);
					repairIssMt.setDepartment(department);
				}
				
				action = "Updated";
			//	retVal = "redirect:/manufacturing/transactions/repairIssMt.html";
				
				 retVal = "redirect:/manufacturing/transactions/repairIssMt/edit/"+repairIssMt.getId()+".html";
			}
			


			
			repairIssMtService.save(repairIssMt);
			
			if (action.equals("Added")) {
			 retVal = "redirect:/manufacturing/transactions/repairIssMt/edit/"+repairIssMt.getId()+".html";
			}
			
			
			redirectAttributes.addFlashAttribute("success", true);
			redirectAttributes.addFlashAttribute("action", action);
		
			return retVal;
			
		}
		
		

		
	}
	
	/*
	 * 
	 * @RequestMapping("/repairIssMt/delete/{id}") public String
	 * delete(@PathVariable int id) { repairIssMtService.delete(id); return
	 * "redirect:/manufacturing/transactions/repairIssMt.html"; }
	 */
	
	@RequestMapping("/repairBagTransfer")
	public String addScreen(Model model){
		
		Department department = departmentService.findByName("Repair"); //("Finish Goods");
		Department department2 = departmentService.findByName("REPARING-HU"); //("Finish Goods");
		
		
		model.addAttribute("deptId",department.getId() );
		
		return "repairBagTransfer";
	}
	
	
	
	@RequestMapping("/repairTransfer/listing")
	@ResponseBody
	public String repairTransferList(@RequestParam(value = "departmentId", required = false) Integer deptId){
		
		
		List<Object[]> objects =new ArrayList<Object[]>();
		
		Department department = departmentService.findByName("Repair"); //("Finish Goods");
		Department department2 = departmentService.findByName("REPARING-HU"); //("Finish Goods");
		
		String departmentId = department.getId()+","+department2.getId();
		
		String departmentCond ="";
		
			departmentCond = " deptId in (" + departmentId + ")  ";

		
			@SuppressWarnings("unchecked")
			TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_departmentBaglistForRepair(?) }");
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
	
	
	
	
	@RequestMapping("/repairTransferInStock")
	@ResponseBody
	public String repairTransferInStock(@RequestParam(value = "vMtId", required = false) String vMtId,
			@RequestParam(value = "repairIssMtId", required = false) Integer repairIssMtId,
			Principal principal) throws ParseException {

		String retVal = "-1";
	
	
		
		synchronized (this) {
			
			try {
				retVal =repairIssDtService.repairTransferInStock(principal,vMtId,repairIssMtId);	
				
			} catch (Exception e) {
				System.out.println(e);
			}
			
		}
		
	return retVal;

	}



	
	@RequestMapping("/repairIssDt/listing")
	@ResponseBody
	public String repairIssDtList(@RequestParam(value = "mtId", required = false) Integer mtId){
		
		
		List<Object[]> objects =new ArrayList<Object[]>();
		
	
		
			@SuppressWarnings("unchecked")
			TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_repairIssDtList(?) }");
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
	
	
	
	@RequestMapping("/repairIssMtApp")
	public String repairIssMtApp(Model model){
		return "repairIssMtApp";
	}
	
	
	@RequestMapping("/repairIssMt/appListing")
	@ResponseBody
	public String repairIssDtAppList(){
		
		
		List<Object[]> objects =new ArrayList<Object[]>();
		
	
		
			@SuppressWarnings("unchecked")
			TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_repairIssDtPendingApproveList() }");
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
				 .append("\",\"repairIssDtId\":\"")
				 .append(list[16] != null ? list[16] : "")
				 .append("\"},");
				
			}
		   
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
			
		return str;
		
	
}	
	

	
	
	@RequestMapping("/repairIssApprove")
	@ResponseBody
	public String repairIssApprove(@RequestParam(value = "vRepairIssDtIds", required = false) String vRepairIssDtIds,
					Principal principal) throws ParseException {

		String retVal = "-1";
	
	
		
		synchronized (this) {
			
			try {
				retVal =repairIssDtService.repairApproval(principal,vRepairIssDtIds);	
				
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
