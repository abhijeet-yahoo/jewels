package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.FgRetDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.FgRetMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMt;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IUserDeptTrfRightService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IFgRetDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IFgRetMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class FgRetController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private RoleRightsService roleRightsService;
	
	@Autowired
	private IFgRetMtService fgRetMtService;
	
	@Autowired
	private IUserDeptTrfRightService userDeptTrfRightService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private IStockMtService stockMtService;
	
	@Autowired
	private IFgRetDtService fgRetDtService;
	
	@Autowired
	private EntityManager entityManager;
	
	@ModelAttribute("fgRetMt")
	public FgRetMt constructMt() {
		return new FgRetMt();
	}
	
	
	@ModelAttribute("fgRetDt")
	public FgRetDt constructDt() {
		return new FgRetDt();
	}
	
	@RequestMapping("/fgRetMt")
	public String users(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("fgRetMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			return "fgRetMt";
		} else {
			if (roleRights == null) {
				return "accesDenied";
			} else {
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());

			}
			return "fgRetMt";
		}
	}
	
	
	
	@RequestMapping("/fgRetMt/listing")
	@ResponseBody
	public String fgRetMtListing(Model model, @RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search, Principal principal)
			throws ParseException {

		return fgRetMtService.fgRetMtListing(limit, offset, sort, order, search, principal);

	}
	
	@RequestMapping("/fgRetMt/add")
	public String add(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("fgRetMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		Department department = departmentService.findByName("Marketing");
		
		model.addAttribute("locationMap", userDeptTrfRightService.getToDepartmentListFromUser(user.getId(), department.getId()));
		
		model.addAttribute("reparinglocationMap", departmentService.getReparingDepartmentList());
		
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
				
		
			
			return "fgRetMt/add";
			
		}else{
		
		if(roleRights == null){
			return "accesDenied";
		}else{

			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("canView", roleRights.getCanView());
			
		}
		
	
		return "fgRetMt/add";
		}
	}
	
	@RequestMapping("/fgRetMt/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("fgRetMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		FgRetMt fgRetMt = fgRetMtService.findOne(id);
		model.addAttribute("fgRetMt", fgRetMt);
		
		
		Department department = departmentService.findByName("Marketing");
		model.addAttribute("locationMap", userDeptTrfRightService.getToDepartmentListFromUser(user.getId(), department.getId()));
		model.addAttribute("reparinglocationMap", departmentService.getReparingDepartmentList());
		
		Department departmentTo = departmentService.findOne(fgRetMt.getLocation().getId());
		
		model.addAttribute("departmentToMap", userDeptTrfRightService.getToDepartmentListFromUser(user.getId(), departmentTo.getId()));
		
		model.addAttribute("mtid", id);
		
		
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			model.addAttribute("canEditTranddate", "false");
		
			
			
			return "fgRetMt/add";
			
		}else{
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}

		
		model.addAttribute("canEditTranddate", "false");
		

		return "fgRetMt/add";
		}
	}
	

	@RequestMapping(value = "/fgRetMt/add", method = RequestMethod.POST)
	public String addEditFgRetMt(
			@Valid @ModelAttribute("fgRetMt") FgRetMt fgRetMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal,
			@RequestParam(value = "pLocationToIds", required = false) Integer pLocationToIds,
			@RequestParam(value = "pLocationIds", required = false) Integer pLocationIds,
			@RequestParam(value = "vTranDate", required = false) String vTranDate) throws ParseException  {

		return fgRetMtService.saveFgRetMt(fgRetMt, id, redirectAttributes, principal, result, vTranDate,pLocationToIds,pLocationIds);
		
	}
	
	@RequestMapping("/fgRetBarcode/autoFill/list")
	@ResponseBody
	public String barcodeAutoFillList(@RequestParam(value = "term", required = false) String barcode,@RequestParam(value = "locationId", required = false) Integer locationId) {
				
		Integer limit = 15;
		
		if(barcode.length() >= 5){
			limit = 100;
		}
		
		
		
		Page<StockMt> stockList = stockMtService.fgRetBarcodeAutofill(limit, 0, "name", "ASC", barcode.toUpperCase(), true, locationId);
		StringBuilder sb = new StringBuilder();

		for (StockMt stockMt : stockList) {
			sb.append("\"")
				.append(stockMt.getBarcode())
				.append("\", ");
		}
	
		
		

		String str = "[" + sb.toString().trim();
		str = (str.lastIndexOf(",") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]";

		return str;
	}
	
	
	@RequestMapping(value="/fgRetDt/addBarcodeItem",method = RequestMethod.POST)
	@ResponseBody
	public String addBarcodeItem(@RequestParam(value = "barcode") String barcode,
			@RequestParam(value = "mtId") Integer mtId,
			@RequestParam(value = "locationId") Integer locationId,
			Principal principal) {
		
		synchronized (this) {
			
			return 	fgRetDtService.fgRetAddBarcodeItem(mtId, barcode, principal,locationId);
		}
		
	}
	
	
	@RequestMapping("/fgRetDt/listing")
	@ResponseBody
	public String fgIssDtList(@RequestParam(value = "mtId", required = false) Integer mtId){
		
		
		List<Object[]> objects =new ArrayList<Object[]>();
		
	
		
			@SuppressWarnings("unchecked")
			TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery("{call jsp_fgRetDtList(?) }");
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
				 .append(list[13] != null ? list[13] : "");
				 sb.append("\",\"action2\":\"");
					sb.append("<a href='javascript:deleteFgRetDt(event,");					
							sb.append(list[14]);
					sb.append(");' class='btn btn-xs btn-danger triggerRemove").append(list[14])
							.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
					.append("\"},");
				
				
			}
		   
			str = sb.toString();
			str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
					: str);
			str += "]}";
			
			
		return str;
		
	
}	
	
	
	
	@ResponseBody
	@RequestMapping("/fgRetDt/delete/{id}")
	public String fgRetDtDelete(@PathVariable int id,Principal principal){
		
		String retVal = fgRetDtService.fgRetDtDelete(id, principal);
		
		return retVal;
		
	}
	
	
	@ResponseBody
	@RequestMapping("/fgRetMt/delete/{id}")
	public String fgRetMtDelete(@PathVariable int id,Principal principal){
		
		String retVal = fgRetMtService.fgRetMtDelete(id, principal);
		
		return retVal;
		
	}
	
	
	

	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	

}
