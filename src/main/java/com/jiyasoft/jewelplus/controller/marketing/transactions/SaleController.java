package com.jiyasoft.jewelplus.controller.marketing.transactions;

import java.io.File;
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
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRmCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRmMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRmStnDt;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IComponentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IHSNService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILocationRightsService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILookUpMastService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IUserDeptTrfRightService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleLabDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleMetalDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRmCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRmMetalDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRmStnDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleStnDtService;

@RequestMapping("/marketing/transactions")
@Controller
public class SaleController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private RoleRightsService roleRightsService;
	
	@Autowired
	private ISaleMtService saleMtService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private IUserDeptTrfRightService userDeptTrfRightService;
	
	@Autowired
	private ISaleDtService saleDtService;
	
	@Autowired
	private ISaleMetalDtService saleMetalDtService;
	
	@Autowired
	private ISaleCompDtService saleCompDtService;
	
	@Autowired
	private ISaleStnDtService saleStnDtService;
	
	@Autowired
	private ISaleLabDtService saleLabDtService;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private IHSNService hsnService;
	
	@Autowired
	private ILookUpMastService lookUpMastService;
	
	@Autowired
	private ISaleRmMetalDtService saleRmMetalDtService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private IMetalService	metalService;
	
	@Autowired
	private ILocationRightsService locationRightsService;
	
	@Autowired
	private ISaleRmCompDtService saleRmCompDtService;
	
	@Autowired
	private IComponentService componentService;
	
	@Autowired
	private IStoneTypeService stoneTypeService;

	@Autowired
	private IShapeService shapeService;
	
	@Autowired
	private ISaleRmStnDtService saleRmStnDtService;
	
	@Value("${upload.directory.path}")
	private String uploadDirecotryPath;

	@Value("${upload.parent.directory.name}")
	private String uploadParentDirecotryName;

	@Value("${upload.directory.name}")
	private String uploadDirecotryName;
	
	
	@ModelAttribute("saleMt")
	public SaleMt constructMt() {
		return new SaleMt();
	}
	
	
	@ModelAttribute("saleDt")
	public SaleDt constructDt() {
		return new SaleDt();
	} 
	
	
	@ModelAttribute("saleRmMetalDt")
	public SaleRmMetalDt constructRmMetalDt() {
		return new SaleRmMetalDt();
	} 
	
	@ModelAttribute("saleRmCompDt")
	public SaleRmCompDt constructRmCompDt() {
		return new SaleRmCompDt();
	} 
	
	@ModelAttribute("saleRmStnDt")
	public SaleRmStnDt constructRmStnDt() {
		return new SaleRmStnDt();
	}
	
	
	@Value("${jobworkDiaStk}")
	private String diamondStockType;
	
	@Value("${allowNegativeDiamond}")
	private Boolean allowNegativeDiamond;
	
	
	
	@RequestMapping("/saleMt")
	public String users(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("saleMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			return "saleMt";
		} else {
			if (roleRights == null) {
				return "accesDenied";
			} else {
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());

			}
			return "saleMt";
		}
	}
	
	
	@RequestMapping("/saleMt/listing")
	@ResponseBody
	public String saleMtListing(Model model, @RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search, Principal principal)
			throws ParseException {

		return saleMtService.saleMtListing(limit, offset, sort, order, search, principal);

	}
	
	
	
	@RequestMapping("/saleMt/add")
	public String add(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("saleMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		model.addAttribute("departmentMap", departmentService.getDepartmentList());
	
		Department department = departmentService.findByName("Marketing");
		model.addAttribute("locationMap", userDeptTrfRightService.getToDepartmentListFromUser(user.getId(), department.getId()));
		model.addAttribute("hsnMap", hsnService.getHsnList());
		model.addAttribute("partyType", "Local");
		model.addAttribute("transportMap", lookUpMastService.getActiveLookUpMastByType("Transport Mode"));
		model.addAttribute("paymentTermMap", lookUpMastService.getActiveLookUpMastByType("Payment Term"));
		
		
		// Metal
		model.addAttribute("metalMtlMap", metalService.getMetalList());
		model.addAttribute("colorMtlMap", colorService.getColorList());
		model.addAttribute("locationMtlMap", locationRightsService.getToLocationListFromUser(user.getId(),"metal"));
		model.addAttribute("purityMap", purityService.getPurityList());

		// Component
		model.addAttribute("metalCompMap", metalService.getMetalList());
		model.addAttribute("colorCompMap", colorService.getColorList());
		model.addAttribute("componentCompMap", componentService.getComponentList());
		model.addAttribute("purityCompMap", purityService.getPurityList());
		model.addAttribute("locationCompMap", locationRightsService.getToLocationListFromUser(user.getId(),"component"));

		// Stone
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
			
			model.addAttribute("allPartyMap", partyService.getPartyList());
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String curDate = dateFormat.format(date);
			model.addAttribute("currentDate", curDate);
			
			
			return "saleMt/add";
			
		}else{
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
			model.addAttribute("canView", roleRights.getCanView());
			
		}
		
		model.addAttribute("allPartyMap", partyService.getPartyList());
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String curDate = dateFormat.format(date);
		model.addAttribute("currentDate", curDate);
		
		model.addAttribute("canEditTranddate", "false");
		return "saleMt/add";
		}
	}
	
	
	
	@RequestMapping(value = "/saleMt/add", method = RequestMethod.POST)
	public String addEditSaleMt(
			@Valid @ModelAttribute("saleMt") SaleMt saleMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal,
			@RequestParam(value = "pPartyIds", required = false) Integer pPartyIds,
			@RequestParam(value = "pLocationIds", required = false) Integer pLocationIds,
			@RequestParam(value = "vTranDate", required = false) String vTranDate,
			@RequestParam(value = "vOtherCharges", required = false) Double vOtherCharges,
			@RequestParam(value = "vFinalPrice", required = false) Double vFinalPrice,
			@RequestParam(value = "pIgst", required = false) Double pIgst,
			@RequestParam(value = "pSgst", required = false) Double pSgst,
			@RequestParam(value = "pCgst", required = false) Double pCgst) throws ParseException {
		
			String barcodeuploadFilePath = uploadDirecotryPath + File.separator + uploadParentDirecotryName + File.separator
				+ uploadDirecotryName + File.separator + "salesBarcode" + File.separator;


			return saleMtService.saveSaleMt(saleMt, id, redirectAttributes, principal, result,pPartyIds,pLocationIds,vTranDate,
					vOtherCharges,vFinalPrice,pIgst,pSgst,pCgst,barcodeuploadFilePath);
			
		}
		
	
	
	
	
	@RequestMapping("/saleMt/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("saleMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		SaleMt saleMt = saleMtService.findOne(id);
		model.addAttribute("saleMt", saleMt);
		
		model.addAttribute("allPartyMap", partyService.getPartyList());
		model.addAttribute("departmentMap", departmentService.getDepartmentList());
		model.addAttribute("model", "PackList");
		model.addAttribute("hsnMap", hsnService.getHsnList());
		model.addAttribute("partyType", saleMt.getParty().getPartyType());
		model.addAttribute("totalOtherCharges", saleMt.getOtherCharges());
		model.addAttribute("finalPrice", saleMt.getFinalPrice());
		model.addAttribute("transportMap", lookUpMastService.getActiveLookUpMastByType("Transport Mode"));
		model.addAttribute("paymentTermMap", lookUpMastService.getActiveLookUpMastByType("Payment Term"));
		
		model.addAttribute("pSgst", saleMt.getSgst());
		model.addAttribute("pCgst", saleMt.getCgst());
		model.addAttribute("pIgst", saleMt.getIgst());
		
	//	LookUpMast lookUpMast = lookUpMastService.findOne(saleMt.getParty().getType().getId())
		
		model.addAttribute("lookType", saleMt.getParty().getType().getFieldValue());
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String curDate = dateFormat.format(date);
		model.addAttribute("currentDate", curDate);
		
		Department department = departmentService.findByName("Marketing");
		model.addAttribute("locationMap", userDeptTrfRightService.getToDepartmentListFromUser(user.getId(), department.getId()));
		
		
		//Metal 
				model.addAttribute("metalMtlMap", metalService.getMetalList());
				model.addAttribute("colorMtlMap", colorService.getColorList());
				model.addAttribute("locationMtlMap", locationRightsService.getToLocationListFromUser(user.getId(),"metal"));
				model.addAttribute("purityMap", purityService.getPurityList());
				
				// Component
				model.addAttribute("metalCompMap", metalService.getMetalList());
				model.addAttribute("colorCompMap", colorService.getColorList());
				model.addAttribute("componentCompMap", componentService.getComponentList());
				model.addAttribute("purityCompMap", purityService.getPurityList());
				model.addAttribute("locationCompMap", locationRightsService.getToLocationListFromUser(user.getId(),"component"));

				// Stone
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
			model.addAttribute("mtid", id);
			
			
			return "saleMt/add";
			
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
		

		return "saleMt/add";
		}
	}
	

	@RequestMapping("/saleDt/listing")
	@ResponseBody
	public String saleDtList(@RequestParam(value = "mtId", required = false) Integer mtId,
			@RequestParam(value = "disableFlg", required = false) String disableFlg){
		
		return saleDtService.saleDtListing(mtId,disableFlg);
		
}	
	
	
	@RequestMapping("/saleStnDt/listing")
	@ResponseBody
	public String saleStnDtList(@RequestParam(value = "saleDtId", required = false) Integer dtId,
			@RequestParam(value = "disableFlg", required = false) String disableFlg){
		
		return saleStnDtService.saleStnDtListing(dtId,disableFlg);
	
	}
	
	@RequestMapping("/saleCompDt/listing")
	@ResponseBody
	public String saleCompDtList(@RequestParam(value = "saleDtId", required = false) Integer dtId,
			@RequestParam(value = "disableFlg", required = false) String disableFlg){
		
		return saleCompDtService.saleCompDtListing(dtId,disableFlg);
	
	}
	
	@RequestMapping("/saleLabDt/listing")
	@ResponseBody
	public String saleLabDtList(@RequestParam(value = "saleDtId", required = false) Integer dtId,
			@RequestParam(value = "disableFlg", required = false) String disableFlg){
		
		return saleLabDtService.saleLabDtListing(dtId,disableFlg);
	
	}
	
	
	
	
	@RequestMapping("/saleMetalDt/listing")
	@ResponseBody
	public String saleMetalDtList(@RequestParam(value = "saleDtId", required = false) Integer dtId){
		
		return saleMetalDtService.saleMetalDtListing(dtId);
	
}
	
	
	
	
	@RequestMapping("/saleMt/packingPickup/transfer")
	@ResponseBody
	public String transferAdjustment(@RequestParam(value = "pMtId", required = false) Integer pMtId,			
		@RequestParam(value = "data", required = false) String data,
			Principal principal) throws ParseException {

			String retVal = "1";
			
	
			try {
				synchronized (this) {
					retVal = saleMtService.salePackingListTransfer(pMtId, data, principal);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				retVal = "Warning : Error Occoured Contact Support";
			}
			

		return retVal;
	}
	
	@RequestMapping("/saleMt/consigPickup/transfer")
	@ResponseBody
	public String consigTransferAdjustment(@RequestParam(value = "pMtId", required = false) Integer pMtId,			
		@RequestParam(value = "data", required = false) String data,
			Principal principal) throws ParseException {

			String retVal = "1";
			
	
			try {
				synchronized (this) {
					retVal = saleMtService.saleConsignmetTransfer(pMtId, data, principal);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				retVal = "Warning : Error Occoured Contact Support";
			}
			

		return retVal;
	}
	
	
	@ResponseBody
	@RequestMapping("/saleDt/delete/{id}")
	public String deleteDt(@PathVariable int id){
		
		String retVal = saleDtService.deleteSaleDt(id);
		
		return retVal;
		
	}
	
	@ResponseBody
	@RequestMapping("/saleMt/delete/{id}")
	public String deleteMt(@PathVariable int id,Principal principal){
		String retVal = "-1";
		try {
			retVal = saleMtService.deleteSaleMt(id,principal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return retVal;
		
	}
	
	@ResponseBody
	@RequestMapping("/saleMt/applyGst")
	public String getGstDetails(
			@RequestParam(value = "hsnId") Integer hsnId) {

		
return hsnService.getapplyGst(hsnId);

	}
	
	@ResponseBody
	@RequestMapping("/saleMt/dtSummary")
	public String getDtItemSummary(
			@RequestParam(value="mtId")Integer mtId,Principal principal){
		
	
		return saleMtService.saleDtSummary(mtId, principal);
	}
	
	@ResponseBody
	@RequestMapping("/saleMt/addSummaryDetails")
	public String addSummaryDetails(
			@RequestParam(value="mtId")Integer mtId,
			@RequestParam(value="fob", required = false) Double fob,
			@RequestParam(value="sgst", required = false) Double sgst,
			@RequestParam(value="cgst", required = false) Double cgst,
			@RequestParam(value="igst", required = false) Double igst,
			@RequestParam(value="otherCharges", required = false) Double otherCharges,
			@RequestParam(value="finalPrice", required = false) Double finalPrice,
			
			Principal principal){
		
		String retVal ="-1";
		try {
			retVal = saleMtService.addSummaryDetails(mtId, fob, sgst, cgst, igst, otherCharges,finalPrice, principal);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return retVal;
	}
	
	
	
	
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	
	/* RequestMapping for  SaleRmMetalDt */

	@RequestMapping("/saleRmMetalDt/listing")
	@ResponseBody
	public String saleRmMetalDtList(@RequestParam(value = "mtId", required = false) Integer mtId,
			@RequestParam(value = "disableFlg", required = false) String disableFlg) {

		return saleRmMetalDtService.saleRmMetalListing(mtId,disableFlg);

	}
	
	
	
	@RequestMapping(value = "/saleRmMetalDt/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditSaleRmMetalUser(@Valid @ModelAttribute("saleRmMetalDt") SaleRmMetalDt saleRmMetalDt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "vSaleMtId", required = true) Integer vSaleMtId,

			RedirectAttributes redirectAttributes, Principal principal) {
		
		String retVal = "-1";
	
		retVal= saleRmMetalDtService.saleRmMetalDtSave(saleRmMetalDt, id, vSaleMtId, principal);

		return retVal;

	}
	
	
	 
	@ResponseBody
	@RequestMapping(value = "/saleRmMetalDt/edit/{id}")
	public String editSaleRmMetal(@PathVariable int id, Model model) {

		SaleRmMetalDt saleRmMetalDt = saleRmMetalDtService.findOne(id);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("modalSaleRmMetalDtId", saleRmMetalDt.getId());
		jsonObject.put("department\\.id", saleRmMetalDt.getDepartment().getId());
		jsonObject.put("metal\\.id", saleRmMetalDt.getMetal().getId());
		jsonObject.put("purity\\.id", saleRmMetalDt.getPurity().getId());
		jsonObject.put("color\\.id", saleRmMetalDt.getColor().getId());
		jsonObject.put("metalWt", saleRmMetalDt.getMetalWt());
		jsonObject.put("rate", saleRmMetalDt.getRate());
		jsonObject.put("amount", saleRmMetalDt.getAmount());
		jsonObject.put("vSaleMtId", saleRmMetalDt.getSaleMt().getId());
		
		
		return jsonObject.toString();
	}
	  
	
	@RequestMapping("/saleRmMetalDt/delete/{id}")
	@ResponseBody
	public String deleteSaleRmMetal(@PathVariable int id, Principal principal) {
		
	String retVal = "-1";
		
		retVal = saleRmMetalDtService.saleRmMetalDtDelete(id, principal);
	
			
		return retVal;
	}
	
//	Request Mapping for Sale issue for Component
	
	@RequestMapping("/saleRmCompDt/listing")
	@ResponseBody
	public String saleRmCompDtListing(
			@RequestParam(value = "mtId", required = false) Integer mtId,
			@RequestParam(value = "disableFlg", required = false) String disableFlg) {

		return saleRmCompDtService.saleRmCompDtListing(mtId,disableFlg);
	}
	
	
	@RequestMapping(value = "/saleRmCompDt/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditConsigRmCompUser(
			@Valid @ModelAttribute("saleRmCompDt") SaleRmCompDt saleRmCompDt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "vSaleRmCompMtId", required = true) Integer vSaleRmCompMtId,
			
			RedirectAttributes redirectAttributes, Principal principal) {
		
		String retVal = "-1";
		
			retVal= saleRmCompDtService.saveSaleRmCompDt(saleRmCompDt, id, vSaleRmCompMtId, principal); 
	
		return retVal;			
		}


	@ResponseBody
	@RequestMapping(value = "/saleRmCompDt/edit/{id}")
	public String editSaleRmComp(@PathVariable int id, Model model) {

		SaleRmCompDt saleRmCompDt = saleRmCompDtService.findOne(id);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("modalSaleRmCompDtId", saleRmCompDt.getId());
		jsonObject.put("department\\.id", saleRmCompDt.getDepartment().getId());
		jsonObject.put("metal\\.id", saleRmCompDt.getMetal().getId());
		jsonObject.put("component\\.id", saleRmCompDt.getComponent().getId());
		jsonObject.put("purity\\.id", saleRmCompDt.getPurity().getId());
		jsonObject.put("color\\.id", saleRmCompDt.getColor().getId());
		jsonObject.put("qty", saleRmCompDt.getQty());
		jsonObject.put("metalWt", saleRmCompDt.getMetalWt());
		jsonObject.put("rate", saleRmCompDt.getRate());
		jsonObject.put("amount", saleRmCompDt.getAmount());
		jsonObject.put("vSaleRmCompMtId", saleRmCompDt.getSaleMt().getId());
		
		
		return jsonObject.toString();
	}
	
	@RequestMapping("/saleRmCompDt/delete/{id}")
	@ResponseBody
	public String deleteSaleRmComp(@PathVariable int id, Principal principal) {
		
	String retVal = "-1";
		
		retVal = saleRmCompDtService.saleRmCompDtDelete(id, principal);
		
		return retVal;
	}
	
	
/* RequestMappingFor Stone SaleRmStnDt*/
	
	
	@RequestMapping("/saleRmStnDt/listing")
	@ResponseBody
	public String saleRmStoneDtListing(
			@RequestParam(value = "mtId", required = false) Integer mtId,
			@RequestParam(value = "disableFlg", required = false) String disableFlg) {
		return saleRmStnDtService.saleRmStnDtListing(mtId,disableFlg);
	}

	@RequestMapping(value = "/saleRmStnDt/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditSaleRmStnDtUser(

			@Valid @ModelAttribute("saleRmStnDt") SaleRmStnDt saleRmStnDt, BindingResult result,
			@RequestParam(value = "id") Integer id,

			@RequestParam(value = "vSaleRmStnMtId", required = true) Integer vSaleRmStnMtId,

			RedirectAttributes redirectAttributes, Principal principal) {

		String retVal = "-1";
									
		retVal = saleRmStnDtService.saveSaleRmStnDt(saleRmStnDt, id,vSaleRmStnMtId, principal, diamondStockType, allowNegativeDiamond);
				

		return retVal;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/saleRmStnDt/edit/{id}")
	public String edit(@PathVariable int id, Model model) {

		SaleRmStnDt saleRmStnDt = saleRmStnDtService.findOne(id);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("modalSaleRmStnDtId", saleRmStnDt.getId());
		jsonObject.put("department\\.id", saleRmStnDt.getDepartment().getId());
		jsonObject.put("stoneType\\.id", saleRmStnDt.getStoneType().getId());
		jsonObject.put("shape\\.id", saleRmStnDt.getShape().getId());
		jsonObject.put("subShape\\.id", saleRmStnDt.getSubShape() != null ? saleRmStnDt.getSubShape().getId() : "");
		jsonObject.put("quality", saleRmStnDt.getQuality().getId());
		jsonObject.put("size", saleRmStnDt.getSize());
		jsonObject.put("vSieve", saleRmStnDt.getSieve() != null ? saleRmStnDt.getSieve() :"");
		jsonObject.put("vSizeGroupStr", saleRmStnDt.getSizeGroup() !=null ?  saleRmStnDt.getSizeGroup().getName() : "");
		jsonObject.put("stone", saleRmStnDt.getStone());
		jsonObject.put("carat", saleRmStnDt.getCarat());
		jsonObject.put("diffCarat", saleRmStnDt.getDiffCarat());
		jsonObject.put("stnRate", saleRmStnDt.getStnRate());
		jsonObject.put("stnAmount", saleRmStnDt.getStnAmount());
		jsonObject.put("packetNo", saleRmStnDt.getPacketNo());
		jsonObject.put("remark", saleRmStnDt.getRemark());
		jsonObject.put("vSaleRmStnMtId", saleRmStnDt.getSaleMt().getId());
		
		
		return jsonObject.toString();
	}
	
	
	@RequestMapping("/saleRmStnDt/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable int id, Principal principal) {
		
	String retVal = "-1";
		
		retVal = saleRmStnDtService.saleRmStnDtDelete(id, principal);
		return retVal;
	}
	
	
	@RequestMapping("/saleMt/salePickup/listing")
	@ResponseBody
	public String salePickupListing(
			@RequestParam(value = "partyId", required = false) Integer partyId) {
		
		return saleMtService.saleMtPickupList(partyId);
	
	}
	
	@RequestMapping("/saleMt/saleDtList/listing")
	@ResponseBody
	public String saleDtListPickupListing(
			@RequestParam(value = "mtId", required = false) Integer mtId) {
		
		return saleDtService.saleDtPickupListing(mtId);
	
	}
	
	@ResponseBody
	@RequestMapping("/saleInvoiceAvailable")
	public String saleInvoiceAvailable(@RequestParam Integer id, @RequestParam String invNo){
	
		return saleMtService.checkInvoiceAvailable(id, invNo);
		
	}
	
	@RequestMapping("/saleMtMetal/salePickup/listing")
	@ResponseBody
	public String saleMetalPickupListing(
			@RequestParam(value = "partyId", required = false) Integer partyId) {
		
		return saleMtService.saleMtMetalPickupList(partyId);
	
	}
	
	@RequestMapping("/saleMt/saleRowMetalDtList/listing")
	@ResponseBody
	public String saleRowMetalDtListPickupListing(
			@RequestParam(value = "mtId", required = false) Integer mtId) {
		
		return saleRmMetalDtService.saleRowMetalDetails(mtId);
	
	}

	
	@RequestMapping("/saleMt/rowCompPickup/listing")
	@ResponseBody
	public String saleMtRowCompPickup(
			@RequestParam(value = "partyId", required = false) Integer partyId) {
		
		return saleMtService.saleRowCompPickupList(partyId);
	
	}
	
	@RequestMapping("/saleMt/rowCompDtDetails/listing")
	@ResponseBody
	public String rowCompDtDetails(
			@RequestParam(value = "mtId", required = false) Integer mtId) {
		
		return saleRmCompDtService.saleRowCompDtDetails(mtId);
	
	}
	
	
	@RequestMapping("/saleMt/rowStonePickup/listing")
	@ResponseBody
	public String rowStonePickupListing(
			@RequestParam(value = "partyId", required = false) Integer partyId) {
		
		return saleMtService.saleRowStonePickupList(partyId);
	
	}
	

	@RequestMapping("/saleMt/rowStoneDtDetails/listing")
	@ResponseBody
	public String rowStoneDtDetails(
			@RequestParam(value = "mtId", required = false) Integer mtId) {
		
		return saleRmStnDtService.saleRowStoneDtDetails(mtId);
	
	}
	
	
}
