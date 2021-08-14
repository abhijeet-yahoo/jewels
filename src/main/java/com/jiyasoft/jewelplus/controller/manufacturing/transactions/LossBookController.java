package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import javax.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.LossBook;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IEmployeeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IProdLabourTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ILossBookService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMtService;


@RequestMapping("/manufacturing/transactions")
@Controller
public class LossBookController {
	
	@Value("${trfApprovalFlg}")
	private Boolean trfApprovalFlg;
	
	@Autowired
	private ITranMetalService tranMetalService;
	
	@Autowired
	private ILossBookService lossBookService;

	@Autowired
	private IEmployeeService employeeService;
	
	@Autowired
	private IProdLabourTypeService prodLabourTypeService;
	
	@Autowired
	private ITranMtService tranMtService;
	
	@Autowired
	private IBagMtService bagMtService;
	
		
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRightsService roleRightsService;
	
	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Value("${upload.directory.name}")
	private String uploadDirecotryName;
	
	
	@ModelAttribute("lossBook")
	public LossBook construct() {
		return new LossBook();
	
	}
	
	
	
	
	@RequestMapping("/lossBook")
	public String users(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("lossBook");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("departmentMap",departmentService.getDepartmentList());
			model.addAttribute("userName",principal.getName());
			
		}
		/*
		 * model.addAttribute("adminRightsMap",
		 * userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
		 * userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") ||
		 * userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE"));
		 */
		
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE") || user.getSupervisor().equals(true)) {
			
			//model.addAttribute("canEditTranddate", "true");
			model.addAttribute("adminRightsMap", "true");
			
		}else {
		//	model.addAttribute("canEditTranddate", "false");
			model.addAttribute("adminRightsMap", "false");
		}
		
		model.addAttribute("canEditTranddate", "false");
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String curDate = dateFormat.format(date);
		model.addAttribute("currentDate", curDate);
		
		return "lossBook";
	}
	
	
	
/*
	@RequestMapping("/lossBook/add")
	public String add(Model model) {
		return "lossBook/add";
	}*/
	

	
	@RequestMapping("/lossBook/bagNoAvailable")
	@ResponseBody
	public String bagNoAvailable(
			@RequestParam(value = "bagMt.name", required = false) String BagNo,
			@RequestParam(value = "department.id", required = false) Integer deptId) {
		
		
		
		
		Boolean bagNoAvailable = true;
		
		if(deptId != null){
			BagMt bagMt = bagMtService.findByName(BagNo);
			Department department = departmentService.findOne(deptId);
			TranMt tranMtt = tranMtService.findByBagMtAndDepartmentAndCurrStk(bagMt, department, true);
			
			if(tranMtt != null){
				bagNoAvailable = true;
			}else{
				bagNoAvailable = false;
			}
		}else{
			bagNoAvailable = true;
		}
		
		
		return bagNoAvailable.toString();
	}
	
	@RequestMapping("/lossBook/list")
	@ResponseBody
	public String bagList(@RequestParam(value = "term", required = false) String name) {
		Page<BagMt> bagMtList = bagMtService.findByName(15, 0, "name", "ASC", name.toUpperCase(), true);
		StringBuilder sb = new StringBuilder();

		for (BagMt bagMt : bagMtList) {
			sb.append("\"")
				.append(bagMt.getName())
				.append("\", ");
		}

		String str = "[" + sb.toString().trim();
		str = (str.lastIndexOf(",") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]";

		return str;
	}
	
	
	@RequestMapping("/lossBook/partNm/list")
	@ResponseBody
	public String partNmList(
			@RequestParam(value = "departmentId") Integer departmentId,
			@RequestParam(value = "bagNo") String bagNo,
			@ModelAttribute("lossBook") LossBook lossBook)
	 {
		String uploadFilePath = "/jewels/uploads/"+ uploadDirecotryName.replaceAll("\\\\", "/") + "/design/";
		return lossBookService.getPartListForLossBook(departmentId, bagNo, uploadFilePath);
	}
	
	
	
	@RequestMapping("/lossBook/partNm/detail")
	@ResponseBody
	public String getPartNm(@ModelAttribute("lossBook") LossBook lossBook,
								@RequestParam(value = "bagNo", required = false) String bagNo,
							@RequestParam(value="partNmId")Integer partId){
		
		
	
		BagMt bagMt=bagMtService.findByName(bagNo);
		TranMetal tranMetal = tranMetalService.findByBagMtIdAndPartNmIdAndCurrStk(bagMt.getId(), partId, true);
		TranMt tranMt= tranMtService.findByBagMtCurrStk(bagMt, true);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("purity\\.name", tranMetal.getPurity().getName());
		jsonObject.put("partWt", tranMetal.getMetalWeight());
		jsonObject.put("grossWt", tranMt.getRecWt());
		jsonObject.put("bagPcs", tranMt.getPcs());
		
		return jsonObject.toString();
	
	}
	
	@RequestMapping("/lossBook/prodLabour/details")
	@ResponseBody
	public String getPartNmDetails(@RequestParam(value = "bagNo", required = false) String bagNo,
							@RequestParam(value = "departmentId") Integer departmentId,
							@ModelAttribute("lossBook") LossBook lossBook){
		
		return prodLabourTypeService.getLossLabourTypeDtDropDown(bagNo, departmentId);
	
	}
	
	@RequestMapping("/lossBook/employee/list")
	@ResponseBody
	public String employeeList(@RequestParam(value = "departmentId") Integer departmentId,
			@ModelAttribute("lossBook") LossBook lossBook) {
		
		StringBuilder sb = new StringBuilder();
		
		Map<Integer, String> employeeMap=employeeService.getEmployeeList(departmentId);

		sb.append("<select id=\"employee.id\" name=\"employee.id\" class=\"form-control\" onchange=\"javascript:popUpdateEmployeeInTable()\">");
		sb.append("<option value=\"\">- Select Worker -</option>");
		
		for (Object key : employeeMap.keySet()) {
			sb.append("<option value=\"").append(key.toString()).append("\">")
					.append(employeeMap.get(key)).append("</option>");
		}
		sb.append("</select>");
		

		return sb.toString();
	}
	
	
	@RequestMapping(value="/lossBook/add", method =RequestMethod.POST )
	@ResponseBody
	public String lossBookSave(@Valid @ModelAttribute("lossBook") LossBook lossBook,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value="empStoneTblData" ,required =false)String empStoneData,
			@RequestParam(value="vRemark" ,required =false)String vRemark,
			@RequestParam(value="toDeptNm" ,required =false)String toDeptNm,
			@RequestParam(value = "tranDate", required = false) String tranDate,
			RedirectAttributes redirectAttributes, Principal principal,
			@RequestParam(value = "saveTrfFlg", required = false) Boolean saveTrfFlg) throws ParseException{
		
		
		String retVal="Error";
		synchronized (this) {
			
			if(toDeptNm.equalsIgnoreCase("Rejection") || toDeptNm.equalsIgnoreCase("Cancel") || toDeptNm.equalsIgnoreCase("Hold Dept")){
				if(vRemark == null || vRemark == ""){
					return "Please Enter Remark ! ";
				}
			}
			
			Date date= new Date();
			
			if(tranDate !=null && !tranDate.isEmpty()){
				DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				date = originalFormat.parse(tranDate);
					
			}
			
			
			if(id == null){
				
				retVal=lossBookService.lossBookSave(lossBook,empStoneData,principal,date);
				
				if(retVal !="1") {
					return retVal;
				}
				if(saveTrfFlg.equals(true)) {
					Department toDept=departmentService.findByName(toDeptNm);
					Integer vDepartmentToId = toDept.getId();
					retVal=tranMtService.transfer(lossBook.getBagMt().getName(),lossBook.getDepartment().getId(), vDepartmentToId, principal,vRemark,date,trfApprovalFlg);		
				}
			
				
			}else{
				return "Contact Admin ,Record Not Save";
			}
			
			
			
			return retVal;
		}
		
	
		
		
	}
	
	
	@ResponseBody
	@RequestMapping("/lossValidation")
	public String lossValidation(Model model,
			@RequestParam(value = "depts", required = false) Integer depts){
		
		
	Department department =departmentService.findOne(depts);
	
	Double lossPercc = department.getLossPerc() != null ? department.getLossPerc() : 0;
	Double extraWtt  = department.getExtraWt() != null ? department.getExtraWt() : 0;
	
	return lossPercc+"^"+extraWtt;
	}
	

}
