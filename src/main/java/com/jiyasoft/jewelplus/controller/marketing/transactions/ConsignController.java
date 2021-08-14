package com.jiyasoft.jewelplus.controller.marketing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.json.JSONObject;
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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRmCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRmMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRmStnDt;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IComponentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IEmployeeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IHSNService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILocationRightsService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILookUpMastService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IUserDeptTrfRightService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigLabDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigMetalDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRmCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRmMetalDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRmStnDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigStnDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IPackDtService;

@RequestMapping("/marketing/transactions")
@Controller
public class ConsignController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private RoleRightsService roleRightsService;
	
	@Autowired
	private IConsigMtService consigMtService;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private IConsigDtService consigDtService;
	
	@Autowired
	private IConsigMetalDtService consigMetalDtService;
	
	@Autowired
	private IPackDtService packDtService;
	
	
	@Autowired
	private IUserDeptTrfRightService userDeptTrfRightService;
	
	@Autowired
	private IStockMtService stockMtService;
	
	@Autowired
	private IConsigStnDtService consigStnDtService;
	
	@Autowired
	private IConsigLabDtService consigLabDtService;
	
	@Autowired
	private IConsigCompDtService consigCompDtService;
	
	@Autowired
	private IHSNService hsnService;
	
	@Autowired
	private ILookUpMastService lookUpMastService;
	
	@Autowired
	private IEmployeeService employeeService;
	
	@Autowired
	private IConsigRmMetalDtService consigRmMetalDtService;
	
	@Autowired
	private IConsigRmCompDtService consigRmCompDtService;
	
	@Autowired
	private IConsigRmStnDtService consigRmStnDtService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private IMetalService	metalService;
	
	@Autowired
	private ILocationRightsService locationRightsService;
	
	@Autowired
	private IComponentService componentService;
	
	@Autowired
	private IStoneTypeService stoneTypeService;

	@Autowired
	private IShapeService shapeService;
	
	
	
	@ModelAttribute("consigMt")
	public ConsigMt constructMt() {
		return new ConsigMt();
	}
	
	
	@ModelAttribute("consigDt")
	public ConsigDt constructDt() {
		return new ConsigDt();
	}
	
	@ModelAttribute("consigRmMetalDt")
	public ConsigRmMetalDt constructMtlDt() {
		return new ConsigRmMetalDt();
	}

	@ModelAttribute("consigRmCompDt")
	public ConsigRmCompDt constructCompDt() {
		return new ConsigRmCompDt();
	}

	@ModelAttribute("consigRmStnDt")
	public ConsigRmStnDt constructStnDt() {
		return new ConsigRmStnDt();
	}
	
	@Value("${diamond.stocktype}")
	private String diamondStockType;
	
	@Value("${allowNegativeDiamond}")
	private Boolean allowNegativeDiamond;
	
	@RequestMapping("/consigMt")
	public String users(Model model, Principal principal) {
		
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("consigMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			return "consigMt";
		} else {
			if (roleRights == null) {
				return "accesDenied";
			} else {
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());

			}
			return "consigMt";
		}
	}
	
	
	@RequestMapping("/consigMt/listing")
	@ResponseBody
	public String consigMtListing(Model model, @RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search, Principal principal)
			throws ParseException {

		return consigMtService.consigMtListing(limit, offset, sort, order, search, principal);

	}
	
	
	
	@RequestMapping("/consigMt/add")
	public String add(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("consigMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		Department department = departmentService.findByName("Marketing");
		
		model.addAttribute("allPartyMap", partyService.getPartyList());
		model.addAttribute("locationMap", userDeptTrfRightService.getToDepartmentListFromUser(user.getId(), department.getId()));
		model.addAttribute("hsnMap", hsnService.getHsnList());
		model.addAttribute("transportMap", lookUpMastService.getActiveLookUpMastByType("Transport Mode"));
		
		model.addAttribute("salesMap", employeeService.getEmployeeList(department.getId()));
		
		//Metal 
				model.addAttribute("metalMtlMap", metalService.getMetalList());
				model.addAttribute("colorMtlMap", colorService.getColorList());
				model.addAttribute("locationMtlMap", locationRightsService.getToLocationListFromUser(user.getId(),"metal"));
				model.addAttribute("purityMap", purityService.getPurityList());
		
		//Component
				model.addAttribute("metalCompMap", metalService.getMetalList());
				model.addAttribute("colorCompMap", colorService.getColorList());
				model.addAttribute("componentCompMap", componentService.getComponentList());
				model.addAttribute("purityCompMap", purityService.getPurityList());
				model.addAttribute("locationCompMap", locationRightsService.getToLocationListFromUser(user.getId(),"component"));		
				
		//Stone
		model.addAttribute("locationStnMap", locationRightsService.getToLocationListFromUser(user.getId(),"stone"));
		model.addAttribute("stoneTypeMap", stoneTypeService.getStoneTypeList());
		model.addAttribute("shapeMap", shapeService.getShapeList());
								
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			model.addAttribute("canEditTranddate", "true");
			
		
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String curDate = dateFormat.format(date);
			model.addAttribute("currentDate", curDate);
			
			
			return "consigMt/add";
			
		}else{
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("canView", roleRights.getCanView());
			
		}
		
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String curDate = dateFormat.format(date);
		model.addAttribute("currentDate", curDate);
		
		model.addAttribute("canEditTranddate", "false");
		return "consigMt/add";
		}
	}
	
	
	
	@RequestMapping(value = "/consigMt/add", method = RequestMethod.POST)
	public String addEditConsigMt(
			@Valid @ModelAttribute("consigMt") ConsigMt consigMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal,
			@RequestParam(value = "pPartyIds", required = false) Integer pPartyIds,
			@RequestParam(value = "pLocationIds", required = false) Integer pLocationIds,
			@RequestParam(value = "pEmployeeIds", required = false) Integer pEmployeeIds,
			@RequestParam(value = "vTranDate", required = false) String vTranDate) throws ParseException {

		return consigMtService.saveConsigMt(consigMt, id, redirectAttributes, principal, result,pPartyIds,pLocationIds,pEmployeeIds,vTranDate);
		
	}
	
	
	@RequestMapping("/consigMt/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("consigMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		ConsigMt consigMt = consigMtService.findOne(id);
		model.addAttribute("consigMt", consigMt);
		
		Department department = departmentService.findByName("Marketing");
		
		model.addAttribute("allPartyMap", partyService.getPartyList());
		model.addAttribute("locationMap", userDeptTrfRightService.getToDepartmentListFromUser(user.getId(), department.getId()));
		model.addAttribute("model", "Consig");
		model.addAttribute("hsnMap", hsnService.getHsnList());
		model.addAttribute("transportMap", lookUpMastService.getActiveLookUpMastByType("Transport Mode"));
		model.addAttribute("salesMap", employeeService.getEmployeeList(department.getId()));
		
		//Metal 
		model.addAttribute("metalMtlMap", metalService.getMetalList());
		model.addAttribute("colorMtlMap", colorService.getColorList());
		model.addAttribute("locationMtlMap", locationRightsService.getToLocationListFromUser(user.getId(),"metal"));
		model.addAttribute("purityMap", purityService.getPurityList());
		
		//Component
		model.addAttribute("metalCompMap", metalService.getMetalList());
		model.addAttribute("colorCompMap", colorService.getColorList());
		model.addAttribute("componentCompMap", componentService.getComponentList());
		model.addAttribute("purityCompMap", purityService.getPurityList());
		model.addAttribute("locationCompMap", locationRightsService.getToLocationListFromUser(user.getId(),"component"));
		
		//Stone
		model.addAttribute("locationStnMap", locationRightsService.getToLocationListFromUser(user.getId(),"stone"));
		model.addAttribute("stoneTypeMap", stoneTypeService.getStoneTypeList());
		model.addAttribute("shapeMap", shapeService.getShapeList());
		
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
			model.addAttribute("canEditTranddate", "true");
			model.addAttribute("mtid", id);
			
			
			return "consigMt/add";
			
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
		

		return "consigMt/add";
		}
	}
	
	

	
	@RequestMapping("/consigDt/listing")
	@ResponseBody
	public String consigDtList(@RequestParam(value = "mtId", required = false) Integer mtId,
			@RequestParam(value = "disableFlg", required = false) String disableFlg){
		
		return consigDtService.consigDtListing(mtId,disableFlg);
		
	
}	
	
	@RequestMapping("/consigCompDt/listing")
	@ResponseBody
	public String consigCompDtList(@RequestParam(value = "consigDtId", required = false) Integer consigDtId){
		
	return consigCompDtService.consigCompDtListing(consigDtId);	
	
}
	
	
	//-----Packing PickupList------//
	@RequestMapping("/consigMt/packingPickup/listing")
	@ResponseBody
	public String packMetalRateListing(
			@RequestParam(value = "partyId", required = false) Integer partyId) {
		
		return consigMtService.packingPickupList(partyId);
	
	}
	
	
	
	@RequestMapping("/consigMt/consigDtList/listing")
	@ResponseBody
	public String consigDtPickupListing(@RequestParam(value = "mtId", required = false) Integer mtId){
		
		return consigDtService.consigDtPickupListing(mtId);
	}
	
	
	//-----Consigment PickupList------//
		@RequestMapping("/consigMt/consignMentPickup/listing")
		@ResponseBody
		public String consignMentPickupListing(
				@RequestParam(value = "partyId", required = false) Integer partyId,
				@RequestParam(value = "tranType", required = false) String tranType) {
			
			return consigMtService.consignMentPickupListing(partyId,tranType);
		
		}
		
		@RequestMapping("/consigMt/packdtList/listing")
		@ResponseBody
		public String packDtList(@RequestParam(value = "mtId", required = false) Integer mtId){
			
			return consigDtService.packdtList(mtId);
			
		}
	
	
	
	@RequestMapping("/packingPickup/transfer")
	@ResponseBody
	public String transferAdjustment(@RequestParam(value = "pMtId", required = false) Integer pMtId,			
		@RequestParam(value = "data", required = false) String data,
		@RequestParam(value = "packMtId", required = false) Integer packMtId,
			Principal principal) throws ParseException {

			String retVal = "1";
			
	
			try {
				synchronized (this) {
					retVal = consigMtService.packingListTransfer(pMtId, data, principal);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				retVal = "Warning : Error Occoured Contact Support";
			}
			

		return retVal;
	}
	
	
	@RequestMapping("/locationWiseStock/transfer")
	@ResponseBody
	public String locationWiseStockTransfer(@RequestParam(value = "pMtId", required = false) Integer pMtId,			
		@RequestParam(value = "data", required = false) String data,@RequestParam(value = "mode", required = false) String mode,
			Principal principal) throws ParseException {

			String retVal = "";
			
	
			try {
				synchronized (this) {
					if(mode.equalsIgnoreCase("Consig")) {
						retVal = consigMtService.locationWiseStockTransfer(pMtId, data, principal);	
					}else {
						retVal = packDtService.locationWiseStockTransferInPacking(pMtId, data, principal);
					}
					
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				retVal = "Warning : Error Occoured Contact Support";
			}
			

		return retVal;
	}
	

	@RequestMapping("/consigMt/stockList")
	@ResponseBody
	public String stockList(@RequestParam(value = "locationId", required = false) Integer locationId){
		
		return consigMtService.stockList(locationId);
	}
	
	
	@RequestMapping("/consigMetalDt/listing")
	@ResponseBody
	public String consigMetalDtList(@RequestParam(value = "consigDtId", required = false) Integer consigDtId){
		
		return consigMetalDtService.consigMetalDtListing(consigDtId);
	
}
	
	@RequestMapping("/consigStnDt/listing")
	@ResponseBody
	public String consigStnDtList(@RequestParam(value = "consigDtId", required = false) Integer consigDtId){
		
	return consigStnDtService.consigStnDtListing(consigDtId);
		
	
}
	
	@RequestMapping("/consigLabDt/listing")
	@ResponseBody
	public String consigLabDtList(@RequestParam(value = "consigDtId", required = false) Integer consigDtId,
			@RequestParam(value = "disableFlg", required = false) String disableFlg){
		
		return consigLabDtService.consigLabDtListing(consigDtId,disableFlg);
		
}
	
	

	@RequestMapping("/barcode/autoFill/list")
	@ResponseBody
	public String barcodeAutoFillList(@RequestParam(value = "term", required = false) String barcode,@RequestParam(value = "locationId", required = false) Integer locationId) {
				
		Integer limit = 15;
		
		if(barcode.length() >= 5){
			limit = 100;
		}
		
		
		
		Page<StockMt> stockList = stockMtService.barcodeAutofill(limit, 0, "name", "ASC", barcode.toUpperCase(), true, locationId);
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

	
	

	@RequestMapping(value="/consigMt/addBarcodeItem",method = RequestMethod.POST)
	@ResponseBody
	public String addBarcodeItem(@RequestParam(value = "barcode") String barcode,
			@RequestParam(value = "mtId") Integer mtId,
			@RequestParam(value = "partyId", required = false) Integer partyId,
			@RequestParam(value = "locationId", required = false) Integer locationId,
			@RequestParam(value = "employeeId", required = false) Integer employeeId,
			Principal principal) {
		
		
		synchronized (this) {
			
			return 	consigDtService.addConsigBarcodeItem(mtId,barcode, principal,partyId,locationId,employeeId);
		}
		
	}
	
	@ResponseBody
	@RequestMapping("/consigDt/delete/{id}")
	public String delete(@PathVariable int id){
		
		String retVal = consigDtService.deleteConsigDt(id);
		
		return retVal;
		
	}
	
	
	@ResponseBody
	@RequestMapping("/consigMt/delete/{id}")
	public String deleteMt(@PathVariable int id,Principal principal){
		String retVal = "-1";
		try {
			retVal = consigMtService.deleteConsigMt(id,principal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return retVal;
		
	}
	
	
	@ResponseBody
	@RequestMapping("/consigMt/dtSummary")
	public String getDtItemSummary(
			@RequestParam(value="mtId")Integer mtId,Principal principal){
		
	
		return consigMtService.getDtItemSummary(mtId);
	
	}
	
	
	
	
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	
	
	
	
	/* RequestMapping for ConsigRmMetalDt */

	@RequestMapping("/consigRmMetalDt/listing")
	@ResponseBody
	public String consigRmMetalDtList(@RequestParam(value = "mtId", required = false) Integer mtId,
			@RequestParam(value = "disableFlg", required = false) String disableFlg) {

		return consigRmMetalDtService.consigRmMetalDtListing(mtId,disableFlg);

	}
	
	
	@RequestMapping(value = "/consigRmMetalDt/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditConsigRmMetalUser(@Valid @ModelAttribute("consigRmMetalDt") ConsigRmMetalDt consigRmMetalDt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "vConsigMtId", required = true) Integer vConsigMtId,

			RedirectAttributes redirectAttributes, Principal principal) {
		
		String retVal = "-1";
	
		retVal= consigRmMetalDtService.consigRmMetalDtSave(consigRmMetalDt, id, vConsigMtId, principal);

		return retVal;

	}
	
	
	 
	@ResponseBody
	@RequestMapping(value = "/consigRmMetalDt/edit/{id}")
	public String editConsigRmMetal(@PathVariable int id, Model model) {

		ConsigRmMetalDt consigRmMetalDt = consigRmMetalDtService.findOne(id);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("modalConsigRmMetalDtId", consigRmMetalDt.getId());
		jsonObject.put("department\\.id", consigRmMetalDt.getDepartment().getId());
		jsonObject.put("metal\\.id", consigRmMetalDt.getMetal().getId());
		jsonObject.put("purity\\.id", consigRmMetalDt.getPurity().getId());
		jsonObject.put("color\\.id", consigRmMetalDt.getColor().getId());
		jsonObject.put("metalWt", consigRmMetalDt.getMetalWt());
		jsonObject.put("rate", consigRmMetalDt.getRate());
		jsonObject.put("amount", consigRmMetalDt.getAmount());
		jsonObject.put("vConsigMtId", consigRmMetalDt.getConsigMt().getId());
		
		
		return jsonObject.toString();
	}
	  
	
	@RequestMapping("/consigRmMetalDt/delete/{id}")
	@ResponseBody
	public String deleteConsigRmMetal(@PathVariable int id, Principal principal) {
		
	String retVal = "-1";
		
		retVal = consigRmMetalDtService.consigRmMetalDtDelete(id, principal);
	
			
		return retVal;
	}
	
//	Request Mapping for Consignment issue for Component
	
	@RequestMapping("/consigRmCompDt/listing")
	@ResponseBody
	public String consigRmCompDtListing(
			@RequestParam(value = "mtId", required = false) Integer mtId) {

		return consigRmCompDtService.consigRmCompDtListing(mtId);
	}
	
	
	@RequestMapping(value = "/consigRmCompDt/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditConsigRmCompUser(
			@Valid @ModelAttribute("consigRmCompDt") ConsigRmCompDt consigRmCompDt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "vConsigRmCompMtId", required = true) Integer vConsigRmCompMtId,
			
			RedirectAttributes redirectAttributes, Principal principal) {
		
		String retVal = "-1";
		
			retVal= consigRmCompDtService.saveConsigRmCompDt(consigRmCompDt, id, vConsigRmCompMtId, principal); 
	
		return retVal;			
		}


	@ResponseBody
	@RequestMapping(value = "/consigRmCompDt/edit/{id}")
	public String editConsigRmComp(@PathVariable int id, Model model) {

		ConsigRmCompDt consigRmCompDt = consigRmCompDtService.findOne(id);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("modalConsigRmCompDtId", consigRmCompDt.getId());
		jsonObject.put("department\\.id", consigRmCompDt.getDepartment().getId());
		jsonObject.put("metal\\.id", consigRmCompDt.getMetal().getId());
		jsonObject.put("component\\.id", consigRmCompDt.getComponent().getId());
		jsonObject.put("purity\\.id", consigRmCompDt.getPurity().getId());
		jsonObject.put("color\\.id", consigRmCompDt.getColor().getId());
		jsonObject.put("qty", consigRmCompDt.getQty());
		jsonObject.put("metalWt", consigRmCompDt.getMetalWt());
		jsonObject.put("rate", consigRmCompDt.getRate());
		jsonObject.put("amount", consigRmCompDt.getAmount());
		jsonObject.put("vConsigRmCompMtId", consigRmCompDt.getConsigMt().getId());
		
		
		return jsonObject.toString();
	}
	
	@RequestMapping("/consigRmCompDt/delete/{id}")
	@ResponseBody
	public String deleteConsigRmComp(@PathVariable int id, Principal principal) {
		
	String retVal = "-1";
		
		retVal = consigRmCompDtService.consigRmCompDtDelete(id, principal);
		
		return retVal;
	}
	
	
	/* RequestMappingFor Stone ConsigRmStnDt*/
	
	
	@RequestMapping("/consigRmStnDt/listing")
	@ResponseBody
	public String consigRmStoneDtListing(
			@RequestParam(value = "mtId", required = false) Integer mtId) {
		return consigRmStnDtService.consigRmStnDtListing(mtId);
	}

	@RequestMapping(value = "/consigRmStnDt/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditConsigRmStnDtUser(

			@Valid @ModelAttribute("consigRmStnDt") ConsigRmStnDt consigRmStnDt, BindingResult result,
			@RequestParam(value = "id") Integer id,

			@RequestParam(value = "vConsigRmStnMtId", required = true) Integer vConsigRmStnMtId,

			RedirectAttributes redirectAttributes, Principal principal) {

		String retVal = "-1";

		retVal = consigRmStnDtService.saveConsigRmStnDt(consigRmStnDt, id,vConsigRmStnMtId, principal, "size", allowNegativeDiamond);
				

		return retVal;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/consigRmStnDt/edit/{id}")
	public String edit(@PathVariable int id, Model model) {

		ConsigRmStnDt consigRmStnDt = consigRmStnDtService.findOne(id);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("modalConsigRmStnDtId", consigRmStnDt.getId());
		jsonObject.put("department\\.id", consigRmStnDt.getDepartment().getId());
		jsonObject.put("stoneType\\.id", consigRmStnDt.getStoneType().getId());
		jsonObject.put("shape\\.id", consigRmStnDt.getShape().getId());
		jsonObject.put("subShape\\.id", consigRmStnDt.getSubShape() != null ? consigRmStnDt.getSubShape().getId() : "");
		jsonObject.put("quality", consigRmStnDt.getQuality().getId());
		jsonObject.put("size", consigRmStnDt.getSize());
		jsonObject.put("vSieve", consigRmStnDt.getSieve() != null ? consigRmStnDt.getSieve() :"");
		jsonObject.put("vSizeGroupStr", consigRmStnDt.getSizeGroup() !=null ?  consigRmStnDt.getSizeGroup().getName() : "");
		jsonObject.put("stone", consigRmStnDt.getStone());
		jsonObject.put("carat", consigRmStnDt.getCarat());
		jsonObject.put("diffCarat", consigRmStnDt.getDiffCarat());
		jsonObject.put("stnRate", consigRmStnDt.getStnRate());
		jsonObject.put("stnAmount", consigRmStnDt.getStnAmount());
		jsonObject.put("packetNo", consigRmStnDt.getPacketNo());
		jsonObject.put("remark", consigRmStnDt.getRemark());
		jsonObject.put("vConsigRmStnMtId", consigRmStnDt.getConsigMt().getId());
		
		
		return jsonObject.toString();
	}
	
	
	@RequestMapping("/consigRmStnDt/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable int id, Principal principal) {
		
	String retVal = "-1";
		
		retVal = consigRmStnDtService.consigRmStnDtDelete(id, principal);
		return retVal;
	}
	
	
	//Row Material Pickup
	
	@RequestMapping("/consigMt/rowMetalPickup/listing")
	@ResponseBody
	public String rowMetalPickupListing(
			@RequestParam(value = "partyId", required = false) Integer partyId) {
		
		return consigMtService.consigRowMetalPickupList(partyId);
	
	}
	
	@RequestMapping("/consigMt/rowMetalDetails/listing")
	@ResponseBody
	public String rowMetalDetails(
			@RequestParam(value = "mtId", required = false) Integer mtId) {
		
		return consigRmMetalDtService.rowMetalDetails(mtId);
	
	}
	
	
	@RequestMapping("/consigMt/rowStonePickup/listing")
	@ResponseBody
	public String rowStonePickupListing(
			@RequestParam(value = "partyId", required = false) Integer partyId) {
		
		return consigMtService.consigRowStonePickupList(partyId);
	
	}
	
	
	@RequestMapping("/consigMt/rowStoneDtDetails/listing")
	@ResponseBody
	public String rowStoneDtDetails(
			@RequestParam(value = "mtId", required = false) Integer mtId) {
		
		return consigRmStnDtService.rowStoneDtDetails(mtId);
	
	}
	
	@RequestMapping("/consigMt/rowCompPickup/listing")
	@ResponseBody
	public String rowCompPickup(
			@RequestParam(value = "partyId", required = false) Integer partyId) {
		
		return consigMtService.consigRowCompPickupList(partyId);
	
	}
	
	@RequestMapping("/consigMt/rowCompDtDetails/listing")
	@ResponseBody
	public String rowCompDtDetails(
			@RequestParam(value = "mtId", required = false) Integer mtId) {
		
		return consigRmCompDtService.rowCompDtDetails(mtId);
	
	}
	
	
	
}
