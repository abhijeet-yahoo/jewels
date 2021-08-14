package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.UserDeptTrfRight;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ReadyBag;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.TranMt;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILocationRightsService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IUserDeptTrfRightService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IReadyBagService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ITranMtService;

@RequestMapping("/manufacturing/transactions")
@Controller
public class ReadyBagController {
	
	@Autowired
	private IReadyBagService readyBagService;
	
	@Autowired
	private ITranMtService tranMtService;
	
	@Autowired
	private IDepartmentService departmentService;

	@Autowired
	private UserService userService;
	
	
	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private IUserDeptTrfRightService userDeptTrfRightService;
	
	@Autowired
	private IBagMtService bagMtService;
	
	@Value("${companyName}")
	private String companyName;
	
	@Autowired
	private ILocationRightsService locationRightsService;


	@ModelAttribute("readyBag")
	public ReadyBag construct() {
		return new ReadyBag();
	}
	

	
	@ModelAttribute("userDeptTrfRight")
	public UserDeptTrfRight constructUserTrRights() {
		return new UserDeptTrfRight();
	}
	
	
	@RequestMapping("/readyBag")
	public String users(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
	//	model.addAttribute("locationMap", locationRightsService.getToLocationListFromUser(user.getId()));
		model.addAttribute("locationMap",userDeptTrfRightService.getDepartmentListFromUser(user.getId()));
		
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
		return "readyBag";
	}
	
	@RequestMapping("/readyBagReturn")
	public String readyBagReturn(Model model, Principal principal) {
		
		/*User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		
	
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
		return "readyBagReturn";
	}
	
	
	
	


	
	@RequestMapping("/readyBag/listing")
	@ResponseBody
	public String bagIssueReadyBagListing(Model model,@RequestParam(value = "pBagNm", required = false) String pBagNm,
			@RequestParam(value = "locationId", required = false) Integer locationId) {

		//TranMt tranMt = tranMtService.findOne(pBgIssId);
		BagMt bagMt = bagMtService.findByName(pBagNm.trim());
		
		
		StringBuilder sb = new StringBuilder();
		
		
		
		if (bagMt != null) {
			List<ReadyBag> readyBags = readyBagService.getBagsForIssuing(bagMt,locationId);
	
			sb.append("{\"total\":").append(0).append(",\"rows\": [");
			
			for (ReadyBag readyBag : readyBags) {
				sb.append("{\"id\":\"")
					.append(readyBag.getId())
					.append("\",\"stoneType\":\"")
					.append((readyBag.getStoneType() == null ? "" : readyBag.getStoneType().getName()))
					.append("\",\"partNm\":\"")
					.append((readyBag.getPartNm() == null ? "" : readyBag.getPartNm().getFieldValue()))
					.append("\",\"shape\":\"")
					.append((readyBag.getShape() == null ? "" : readyBag.getShape().getName()))
					.append("\",\"quality\":\"")
					.append((readyBag.getQuality() == null ? "" : readyBag.getQuality().getName()))
					.append("\",\"mm\":\"")
					.append((readyBag.getSize() == null ? "" : readyBag.getSize()))
					.append("\",\"sieve\":\"")
					.append((readyBag.getSieve() == null ? "" : readyBag.getSieve()))
					.append("\",\"stone\":\"")
					.append((readyBag.getStone() == null ? "" : readyBag.getStone()))
					.append("\",\"carat\":\"")
					.append((readyBag.getCarat() == null ? "" : readyBag.getCarat()))
					.append("\",\"setting\":\"")
					.append((readyBag.getSetting() == null ? "" : readyBag.getSetting().getName()))
					.append("\",\"setType\":\"")
					.append((readyBag.getSettingType() == null ? "" : readyBag.getSettingType().getName()))
					.append("\",\"centerStone\":\"")
					.append((readyBag.getCenterStone() != null ? (readyBag.getCenterStone() ? readyBag.getCenterStone() : false) : false))
					.append("\",\"action1\":\"")
					.append("<a href='/jewels/manufacturing/transactions/bagIssue/edit/")
					.append(readyBag.getId())
					.append(".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
					.append("\",\"action2\":\"")
					.append("<a onClick='javascript:readyBagDelete(").append(readyBag.getId()).append(");' href='javascript:void(0);'")
					.append(" class='btn btn-xs btn-danger triggerRemove")
					.append(readyBag.getId())
					.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
					.append("\"},");
			}
		}
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";


		return str;
	}
	
	@RequestMapping("/readyBagReturn/listing")
	@ResponseBody
	public String readyBagReturnListing(Model model,@RequestParam(value = "pBagNm", required = false) String pBagNm,
			@RequestParam(value = "locationId", required = false) Integer locationId) {

		return readyBagService.getReadyBagReturnList(pBagNm, locationId);
	}
	
	
	
	@RequestMapping("/toDepartment/list")
	@ResponseBody
	public String subConceptList(Principal principal) {
		return userDeptTrfRightService.getToDepartmentListDropDown(userService.findByName(principal.getName()).getId(), departmentService.findByName("Diamond").getId(),"deptTo.id");
	}
	
	@RequestMapping("/fromLocationDropdown/list")
	@ResponseBody
	public String fromLocationDropdown(Principal principal) {
		return userDeptTrfRightService.getToLocationRightsListDropDown(userService.findByName(principal.getName()).getId(),"locationId");
	}
	
	
	
	
	@RequestMapping(value = "/readyBag/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditBagIssueData(
			@RequestParam(value = "pBagNm", required = false) String pBagNm,
			@RequestParam(value = "pIds", required = false) String pIds,
			@RequestParam(value = "pDeptId", required = false) Integer pDeptId,
			@RequestParam(value = "pUpdGold", required = false) Boolean pUpdGold,
			@RequestParam(value = "pTrandate", required = false) String vTranDate,
			@RequestParam(value = "pLocationId", required = false) Integer pLocationId,
			
			Principal principal) throws ParseException {

		String retVal = "-1";
		
		
		Date date= new Date();
		
		if(vTranDate !=null && !vTranDate.isEmpty()){
			DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			date = originalFormat.parse(vTranDate);
				
		}
		
		synchronized (this) {
			retVal=readyBagService.readyBagTransfer(pBagNm, pIds, pDeptId, pUpdGold, principal,date,pLocationId);
		}
		
		
		
		return retVal;

	}
	
	@RequestMapping(value = "/multiReadyBag/transfer", method = RequestMethod.POST)
	@ResponseBody
	public String addEditMultiReadyData(
			@RequestParam(value = "pBagNm", required = false) String pBagNm,
			@RequestParam(value = "pDeptId", required = false) Integer pDeptId,
			@RequestParam(value = "pUpdGold", required = false) Boolean pUpdGold,
			@RequestParam(value = "pTrandate", required = false) String vTranDate,
			@RequestParam(value = "pLocationId", required = false) Integer locationId,
			Principal principal) throws ParseException {
		

	String retVal = "-1";
	String dataMsg="";

	Date date= new Date();
	
	if(vTranDate !=null && !vTranDate.isEmpty()){
		DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		date = originalFormat.parse(vTranDate);
			
	}
		
		synchronized (this) {
			
		
			
			String[] BagDtl = pBagNm.split(",");
			for (int i = 0; i < BagDtl.length; i++) {
				
				retVal =readyBagService.multiReadyBagTransfer(BagDtl[i], pDeptId, pUpdGold, principal,date,locationId);
				if(retVal !="1"){
					
					if(dataMsg.length()>0){
						
						dataMsg=dataMsg+"\n"+BagDtl[i]+"-"+retVal;
						
					}else{
						dataMsg=BagDtl[i]+"-"+retVal;
					}
					
				}
	
				
				
			}
			
		}
		
	if(dataMsg ==""){
		dataMsg="1";
	}
		return dataMsg;

	}
	
	
	
	@RequestMapping("/readyBag/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		ReadyBag readyBag = readyBagService.findOne(id);
		model.addAttribute("readyBag", readyBag);

		return "readyBag";
	}

	@RequestMapping("/readyBag/delete/{id}")
	@ResponseBody
	public String deleteBagging(@PathVariable int id,
			@RequestParam(value = "tranDate", required = false) String vTranDate,
			Principal principal) throws ParseException {
		
		
		Date date= new Date();
		
		if(vTranDate !=null && !vTranDate.isEmpty()){
			DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			date = originalFormat.parse(vTranDate);
				
		}
		
		return readyBagService.readyBagDelete(id,principal,date,companyName);
	}
	
	
	@RequestMapping("/checklist")
	@ResponseBody
	public String checkListInTranMt(
			@RequestParam(value="bagNm") String bagNm){
		
		String retVal = "-1";
		
		BagMt bagMt = bagMtService.findByName(bagNm);
		Department department = departmentService.findByName("Diamond");
		TranMt tranMt = tranMtService.findByBagMtAndDepartmentAndCurrStk(bagMt, department, true);
		
		if(tranMt != null){
			retVal = tranMt.getId()+"";
		}
		
		return retVal;
	}
	


}
