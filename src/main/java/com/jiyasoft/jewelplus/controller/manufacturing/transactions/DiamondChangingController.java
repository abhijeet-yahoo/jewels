package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LocationRights;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.DiamondBagging;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranDt;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IEmployeeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILocationRightsService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IDiamondBaggingService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IDiamondChangingService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStoneTranService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranDtService;


@RequestMapping("/manufacturing/transactions")
@Controller
public class DiamondChangingController {

	@Autowired
	private IDiamondBaggingService diamondBaggingService;

	@Autowired
	private IBagMtService bagMtService;

	@Autowired
	private ISettingService settingService;

	@Autowired
	private ISettingTypeService settingTypeService;

	@Autowired
	private IStoneTranService stoneTranService;

	@Autowired
	private ITranDtService tranDtService;

	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private IEmployeeService employeeService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ILocationRightsService locationRightsService;
	
	@Autowired
	private IDiamondChangingService diamondChangingService;
	

	@Value("${upload.directory.name}")
	private String uploadDirecotryName;
	
	@Value("${diamond.stocktype}")
	private String diamondStockType;
	
	@Value("${changingIssStk}")
	private String changingIssStk;
	
	@Value("${allowNegativeDiamond}")
	private Boolean allowNegativeDiamond;
	
	@Value("${companyName}")
	private String companyName;

	@ModelAttribute("diamondBagging")
	public DiamondBagging construct() {
		return new DiamondBagging();
	}
	

	@RequestMapping("/diamondChanging")
	public String users(Model model, Principal principal) {
		model = populateModel(model,principal);
		return "diamondChanging";
	}
	
	
	@RequestMapping("/jobBag/diamondChanging/list")
	@ResponseBody
	public String bagList(
			@RequestParam(value = "term", required = false) String name) {
		Page<BagMt> bagMtList = bagMtService.findByName(15, 0, "name", "ASC",
				name.toUpperCase(), true);
		StringBuilder sb = new StringBuilder();

		for (BagMt bagMt : bagMtList) {
			sb.append("\"").append(bagMt.getName()).append("\", ");
		}

		String str = "[" + sb.toString().trim();
		str = (str.lastIndexOf(",") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]";

		return str;
	}
	
	@RequestMapping("/changingDiamond/checkBagInTranDt")
	@ResponseBody
	public String checkBag(
			@RequestParam(value = "bagName", required = false) String name) {
		
		Boolean result = false;
		
		BagMt bagMt = bagMtService.findByName(name.trim());
		List<TranDt> tranDt = tranDtService.findByBagMtAndCurrStk(bagMt, true);
		
		
		
		if(tranDt.size() > 0){
			result = true;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(result);
		
		return sb.toString();
	}
	
	
	@RequestMapping("/changingDiamond/employee")
	@ResponseBody
	public String popEmployee(
			@RequestParam(value = "deptNm", required = false) String deptNm) {
		
		Department department = departmentService.findByName(deptNm.trim());
		String employee = employeeService.getEmployeeListDropDownForChanging(department.getId());
		
		//System.out.println(employee);
		
		return employee;
	}
	
	
	@RequestMapping("/fetchBagName/fromStnAdjId")
	@ResponseBody
	public String fetchBagNm(
			@RequestParam(value = "idd", required = false) Integer idd) {
		
		StringBuilder sb = new StringBuilder();
		
		DiamondBagging diamondBagging = diamondBaggingService.findOne(idd);
		String  bagNm = diamondBagging.getBagMt().getName();
		sb.append(bagNm);
		
		return sb.toString();
	}
	

	@RequestMapping("/jobBag/diamondChanging/details")
	@ResponseBody
	public String bagDetails(
			@RequestParam(value = "bagName", required = false) String name) {
			
		return diamondChangingService.diamondChangingDetails(name, uploadDirecotryName);
	}

	
	
	@RequestMapping("/impstnAdj/diamondChanging/listing")
	@ResponseBody
	public String impStnAdjDetails(
			@RequestParam(value = "bagName", required = false) String name) {

		return diamondChangingService.diamondChangingListing(name);
		
	}
	
	
	
	
	
	@RequestMapping(value = "/return/diamondChanging/transfer", method = RequestMethod.POST)
	@ResponseBody
	public String returnDiamondTransfer(
			@Valid @ModelAttribute("diamondBagging") DiamondBagging diamondBagging,
			BindingResult result, 
			@RequestParam(value = "id") Integer id,
			@RequestParam(value = "pODIds") String pOIds,
			@RequestParam(value = "trfBagStone") String trfBagStone,
			@RequestParam(value = "trfBagCarat") String trfBagCarat,
			@RequestParam(value = "bagNm") String bagNm,
			//@RequestParam(value = "employeeeId") Integer employeeId,
			//@RequestParam(value = "typeNm") String typeNm,
			@RequestParam(value = "cDeptId") String cDeptNm,
			RedirectAttributes redirectAttributes, Principal principal) {

		String retVal = "1";

		if (result.hasErrors()) {
			return "diamondChanging";
		}
		
		synchronized (this) {
			retVal=diamondBaggingService.diamondChangingReturn(diamondBagging, pOIds, trfBagStone, trfBagCarat, bagNm, cDeptNm, principal);
		}
		
		
		
	
		return retVal;	
		
	}

	
	
	@RequestMapping("/diamondChangingBreakup/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		model = populateModel(model,principal);
		return "diamondChangingBreakup";	
	}
	
	
	private Model populateModel(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		/*
		 * UserRole userRole = userRoleService.findByUser(user); if
		 * (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
		 * userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") ||
		 * userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
		 * 
		 * model.addAttribute("canEditTranddate", "true");
		 * 
		 * }else { model.addAttribute("canEditTranddate", "false"); }
		 */
		
		model.addAttribute("canEditTranddate", "false");
		model.addAttribute("settingMap",settingService.getSettingList());
		model.addAttribute("settingTypeMap",settingTypeService.getSettingTypeList());
		model.addAttribute("departmentMap",departmentService.getDepartmentList());
		model.addAttribute("allowNegativeDiamond",allowNegativeDiamond);
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String curDate = dateFormat.format(date);
		model.addAttribute("currentDate", curDate);
		
		model.addAttribute("diamondStockType", diamondStockType);
		model.addAttribute("companyName", companyName);
		
		
		
		
		return model;
	}
	
	
	
	@RequestMapping("/changing/trandt/detail")
	@ResponseBody
	public String tranDtDetails(@RequestParam("tranDtId") Integer tranDtId,Principal principal){
			
		TranDt tranDt = tranDtService.findOne(tranDtId);
		
		Department location=  tranDt.getDepartment();
		
		User user =userService.findByName(principal.getName());
		LocationRights locationRights = locationRightsService.findByUserAndDepartmentAndDeactive(user, location, false);
		
		 if(location.getStoneStk().equals(true) && locationRights !=null ) {
		
		
		Double stockBalance = stoneTranService.getStockBalance(location.getId(),tranDt.getStoneType().getId(), tranDt.getShape().getId(), 
				tranDt.getQuality().getId(), tranDt.getSizeGroup().getId(), tranDt.getSieve(), changingIssStk);
		
		
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("modStoneType", tranDt.getStoneType().getName());
		jsonObject.put("modShape", tranDt.getShape().getName());
		jsonObject.put("modQuality", tranDt.getQuality().getName());
		jsonObject.put("modSize", tranDt.getSize());
		jsonObject.put("modBalCarat", stockBalance);
		jsonObject.put("partNmId", tranDt.getPartNm().getFieldValue());
	
		
		return jsonObject.toString();
		 }else {
			 return "Warning: Permission Denied";
		 }
	}
	
	
	@RequestMapping("/changing/trandtret/detail")
	@ResponseBody
	public String tranDtRetDetails(@RequestParam("tranDtId") Integer tranDtId){
		
		return diamondChangingService.trandtRetDetail(tranDtId);
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("/diamondchanging/addnew")
	public String changingAddNewBrk(
			@RequestParam(value = "tranDtId") Integer tranDtId,
			@RequestParam(value = "reqStone") Integer reqStone,
			@RequestParam(value = "reqCarat") Double reqCarat,
			@RequestParam(value = "tranDate") String vTranDate,
			Principal principal) throws ParseException{
		
		
		String retVal = ""; 
		

		Date date= new Date();
		
		if(vTranDate !=null && !vTranDate.isEmpty()){
			DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			date = originalFormat.parse(vTranDate);
				
		}
		
		synchronized (this) {
			try {
				retVal = diamondBaggingService.diamondChangingAddNewBreakUp(tranDtId, reqStone, reqCarat, principal,changingIssStk,allowNegativeDiamond,date);
			} catch (Exception e) {
				e.printStackTrace();
				retVal = "Error : Contact Support";
			}
			
		}
		
		return retVal;
		
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("/diamondchanging/returnsingle")
	public String changingReturnBrk(
			@RequestParam(value = "tranDtId") Integer tranDtId,
			@RequestParam(value = "retStone") Integer retStone,
			@RequestParam(value = "retCarat") Double retCarat,
			@RequestParam(value = "changingType") String changingType,
			@RequestParam(value = "employeeId") Integer employeeId,
			@RequestParam(value="tranDate") String vTranDate,
			Principal principal) throws ParseException{
	
		
		String retVal = ""; 
		
		Date date= new Date();
		
		if(vTranDate !=null && !vTranDate.isEmpty()){
			DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			date = originalFormat.parse(vTranDate);
				
		}
		
		synchronized (this) {
			try {
				retVal = diamondBaggingService.diamondChangingSingleReturnBreakUp(tranDtId, retStone, retCarat, changingType,employeeId,principal,date);
			} catch (Exception e) {
				retVal=e.getMessage();
				//retVal = "Error : Contact Support";
			}
			
		}
		
		return retVal;
		
	}
	
	
	
	
	@RequestMapping(value="/diamondchanging/returnselected",method=RequestMethod.POST)
	@ResponseBody
	public String changingReturnSelected(@RequestParam(value="vtblData") String tblData,
			@RequestParam(value="tranDate") String vTranDate,Principal principal) throws ParseException{
		

		Date date= new Date();
		
		if(vTranDate !=null && !vTranDate.isEmpty()){
			DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			date = originalFormat.parse(vTranDate);
				
		}
		
		String retVal =diamondBaggingService.diamondReturnSelected(tblData, principal,date);
		
		
		
		
		return retVal;
	}
	

	
}
