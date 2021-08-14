package com.jiyasoft.jewelplus.controller.marketing.transactions;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.json.JSONObject;
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
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetRmCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetRmMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.SaleRetRmStnDt;
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
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRetCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRetDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRetLabDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRetMetalDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRetMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRetRmCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRetRmMetalDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRetRmStnDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.ISaleRetStnDtService;

@RequestMapping("/marketing/transactions")
@Controller
public class SaleRetController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private RoleRightsService roleRightsService;
	

	@Autowired
	private ISaleRetMtService saleRetMtService;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	
	@Autowired
	private IHSNService hsnService;
	
	@Autowired
	private ILookUpMastService lookUpMastService;
	
	@Autowired
	private IUserDeptTrfRightService userDeptTrfRightService;

	@Autowired
	private ISaleRetDtService saleRetDtService;
	
	@Autowired
	private ISaleRetCompDtService saleRetCompDtService;
	
	@Autowired
	private ISaleRetMetalDtService saleRetMetalDtService;
	
	@Autowired
	private ISaleRetStnDtService saleRetStnDtService;
	
	@Autowired
	private ISaleRetLabDtService saleRetLabDtService;
	

	@Autowired
	private ISaleRetRmMetalDtService saleRetRmMetalDtService;
	
	@Autowired
	private ISaleRetRmCompDtService saleRetRmCompDtService;
	
	@Autowired
	private ISaleRetRmStnDtService saleRetRmStnDtService;
	
	
	@Autowired
	private IComponentService componentService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private IMetalService metalService;
	
	@Autowired
	private ILocationRightsService locationRightsService;
	

	@Autowired
	private IStoneTypeService stoneTypeService;

	@Autowired
	private IShapeService shapeService;
	
	@ModelAttribute("saleRetMt")
	public SaleRetMt constructMt() {
		return new SaleRetMt();
	}
	
	
	@ModelAttribute("saleRetDt")
	public SaleRetDt constructDt() {
		return new SaleRetDt();
	}
	
	@ModelAttribute("saleRetRmMetalDt")
	public SaleRetRmMetalDt constructSaleRetRmMetalDt() {
		return new SaleRetRmMetalDt();
	}
	
	@ModelAttribute("saleRetRmCompDt")
	public SaleRetRmCompDt constructSaleRetRmCompDt() {
		return new SaleRetRmCompDt();
	}
	
	@ModelAttribute("saleRetRmStnDt")
	public SaleRetRmStnDt constructSaleRetRmStnDt() {
		return new SaleRetRmStnDt();
	}
	
	
	@RequestMapping("/saleRetMt")
	public String users(Model model, Principal principal) {
		
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("saleRetMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			return "saleRetMt";
		} else {
			if (roleRights == null) {
				return "accesDenied";
			} else {
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());

			}
			return "saleRetMt";
		}
	}
	
	@RequestMapping("/saleRetMt/listing")
	@ResponseBody
	public String saleRetMtListing(Model model, @RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search, Principal principal)
			throws ParseException {

		return saleRetMtService.saleRetMtListing(limit, offset, sort, order, search, principal);

	}
	
	
	@RequestMapping("/saleRetMt/add")
	public String add(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("saleRetMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		Department department = departmentService.findByName("Marketing");
		
		model.addAttribute("allPartyMap", partyService.getPartyList());
		model.addAttribute("locationMap", userDeptTrfRightService.getToDepartmentListFromUser(user.getId(), department.getId()));
		model.addAttribute("hsnMap", hsnService.getHsnList());
		model.addAttribute("transportMap", lookUpMastService.getActiveLookUpMastByType("Transport Mode"));
		
		model.addAttribute("purityDtMap", purityService.getPurityList());
		model.addAttribute("colorDtMap", colorService.getColorList());
		
		//Metal
		 model.addAttribute("metalMtlMap", metalService.getMetalList());
		model.addAttribute("colorMtlMap", colorService.getColorList());
		model.addAttribute("locationMtlMap", locationRightsService.getToLocationListFromUser(user.getId(),"metal"));
		model.addAttribute("purityMap", purityService.getPurityList());
		
		
		//Stone
		model.addAttribute("locationStnMap", locationRightsService.getToLocationListFromUser(user.getId(),"stone"));
		model.addAttribute("stoneTypeMap", stoneTypeService.getStoneTypeList());
		model.addAttribute("shapeMap", shapeService.getShapeList());
		
		//Component
		model.addAttribute("locationCompMap", locationRightsService.getToLocationListFromUser(user.getId(),"component"));
		model.addAttribute("metalCompMap", metalService.getMetalList());
		model.addAttribute("componentCompMap", componentService.getComponentList());
		model.addAttribute("purityCompMap", purityService.getPurityList());
		model.addAttribute("colorCompMap", colorService.getColorList());
		
		
		
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
			
			
			return "saleRetMt/add";
			
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
		return "saleRetMt/add";
		}
	}
	
	
	@RequestMapping(value = "/saleRetMt/add", method = RequestMethod.POST)
	public String addEditSaleRetMt(
			@Valid @ModelAttribute("saleRetMt") SaleRetMt saleRetMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal,
			@RequestParam(value = "pPartyIds", required = false) Integer pPartyIds,
			@RequestParam(value = "pLocationIds", required = false) Integer pLocationIds,
			@RequestParam(value = "vTranDate", required = false) String vTranDate,
			@RequestParam(value = "vOtherCharges", required = false) Double vOtherCharges,
			@RequestParam(value = "vFinalPrice", required = false) Double vFinalPrice,
			@RequestParam(value = "pIgst", required = false) Double pIgst,
			@RequestParam(value = "pSgst", required = false) Double pSgst,
			@RequestParam(value = "pCgst", required = false) Double pCgst) throws ParseException  {

		return saleRetMtService.saveSaleRetMt(saleRetMt, id, redirectAttributes, principal, result, pPartyIds, pLocationIds,vTranDate,vOtherCharges,vFinalPrice,pIgst,pSgst,pCgst);
		
	}
	
	
	
	@RequestMapping("/saleRetMt/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("saleRetMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		SaleRetMt saleRetMt = saleRetMtService.findOne(id);
		model.addAttribute("saleRetMt", saleRetMt);
		
		model.addAttribute("purityDtMap", purityService.getPurityList());
		model.addAttribute("colorDtMap", colorService.getColorList());
		
		Department department = departmentService.findByName("Marketing");
		
		model.addAttribute("allPartyMap", partyService.getPartyList());
		model.addAttribute("locationMap", userDeptTrfRightService.getToDepartmentListFromUser(user.getId(), department.getId()));
		model.addAttribute("hsnMap", hsnService.getHsnList());
		model.addAttribute("transportMap", lookUpMastService.getActiveLookUpMastByType("Transport Mode"));
		
		model.addAttribute("partyType", saleRetMt.getParty().getPartyType());
		model.addAttribute("totalOtherCharges", saleRetMt.getOtherCharges());
		model.addAttribute("finalPrice", saleRetMt.getFinalPrice());
		
		model.addAttribute("pSgst", saleRetMt.getSgst());
		model.addAttribute("pCgst", saleRetMt.getCgst());
		model.addAttribute("pIgst", saleRetMt.getIgst());
		
		//Metal
		 model.addAttribute("metalMtlMap", metalService.getMetalList());
		model.addAttribute("colorMtlMap", colorService.getColorList());
		model.addAttribute("locationMtlMap", locationRightsService.getToLocationListFromUser(user.getId(),"metal"));
		model.addAttribute("purityMap", purityService.getPurityList());
		
		
		//Stone
		model.addAttribute("locationStnMap", locationRightsService.getToLocationListFromUser(user.getId(),"stone"));
		model.addAttribute("stoneTypeMap", stoneTypeService.getStoneTypeList());
		model.addAttribute("shapeMap", shapeService.getShapeList());
		
		//Component
		model.addAttribute("locationCompMap", locationRightsService.getToLocationListFromUser(user.getId(),"component"));
		model.addAttribute("metalCompMap", metalService.getMetalList());
		model.addAttribute("componentCompMap", componentService.getComponentList());
		model.addAttribute("purityCompMap", purityService.getPurityList());
		model.addAttribute("colorCompMap", colorService.getColorList());
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String curDate = dateFormat.format(date);
		model.addAttribute("currentDate", curDate);
		
		model.addAttribute("lookType", saleRetMt.getParty().getType().getFieldValue());
		
		model.addAttribute("mtid", id);
		
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			model.addAttribute("canEditTranddate", "true");
			
			
			
			return "saleRetMt/add";
			
		}else{
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			model.addAttribute("canEdit", roleRights.getCanEdit());
			model.addAttribute("canDelete", roleRights.getCanDelete());
		}

	
		model.addAttribute("canEditTranddate", "false");
		

		return "saleRetMt/add";
		}
	}	
	
	@RequestMapping("/saleRetDt/listing")
	@ResponseBody
	public String saleRetDtList(@RequestParam(value = "mtId", required = false) Integer mtId,
			@RequestParam(value = "disableFlg", required = false) String disableFlg){
		
		return saleRetDtService.saleRetDtListing(mtId,disableFlg);
		
	}	
	
	@RequestMapping("/saleRetCompDt/listing")
	@ResponseBody
	public String saleRetCompDtList(@RequestParam(value = "dtId", required = false) Integer dtId,
			@RequestParam(value = "disableFlg", required = false) String disableFlg){
		
	return saleRetCompDtService.saleRetCompDtListing(dtId,disableFlg);	
	
	}
	
	
	@RequestMapping("/saleRetMetalDt/listing")
	@ResponseBody
	public String saleRetMetalDtList(@RequestParam(value = "dtId", required = false) Integer dtId){
		
		return saleRetMetalDtService.saleRetMetalDtListing(dtId);
	
	}
	
	@RequestMapping("/saleRetStnDt/listing")
	@ResponseBody
	public String saleRetStnDtList(@RequestParam(value = "dtId", required = false) Integer dtId,
			@RequestParam(value = "disableFlg", required = false) String disableFlg){
		
	return saleRetStnDtService.saleRetStnDtListing(dtId,disableFlg);
		
	
	}
	
	@RequestMapping("/saleRetLabDt/listing")
	@ResponseBody
	public String saleRetLabDtList(@RequestParam(value = "dtId", required = false) Integer dtId,
			@RequestParam(value = "disableFlg", required = false) String disableFlg){
		
		return saleRetLabDtService.saleRetLabDtListing(dtId,disableFlg);
		
	}
	
	@ResponseBody
	@RequestMapping("/saleRetDt/delete/{id}")
	public String delete(@PathVariable int id){
		
		String retVal = saleRetDtService.deleteSaleRetDt(id);
		
		return retVal;
		
	}
	
	
	@RequestMapping("/saleRetPickup/transfer")
	@ResponseBody
	public String saleTransferAdjustment(@RequestParam(value = "pMtId", required = false) Integer pMtId,			
		@RequestParam(value = "data", required = false) String data,
			Principal principal) throws ParseException {

			String retVal = "1";
			
	
			try {
				synchronized (this) {
					retVal = saleRetMtService.saleTransfer(pMtId, data, principal);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				retVal = "Warning : Error Occoured Contact Support";
			}
			

		return retVal;
	}
	
	
	@ResponseBody
	@RequestMapping("/saleRetMt/delete/{id}")
	public String deleteMt(@PathVariable int id){
		String retVal = "-1";
		try {
			retVal = saleRetMtService.deleteSaleRetMt(id);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return retVal;
		
	}
	
	/* SaleRetRmMetalDt */
	
	
	@RequestMapping("/saleRetRmMetalDt/listing")
	@ResponseBody
	public String saleRetRmMtlDtListing(@RequestParam(value = "mtId", required = false) Integer mtId,
			@RequestParam(value = "disableFlg", required = false) String disableFlg) {
		
		return saleRetRmMetalDtService.saleRetRmMetalDtListing(mtId,disableFlg);
	}
	
	@RequestMapping(value = "/saleRetRmMetalDt/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditSaleRetRmMetalDt(
			@Valid @ModelAttribute("saleRetRmMetalDt") SaleRetRmMetalDt saleRetRmMetalDt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "vSaleRetMMtId", required = true) Integer vSaleRetMtId,
			
			RedirectAttributes redirectAttributes, Principal principal) {

		
		String retVal = "-1";
		
			retVal= saleRetRmMetalDtService.saleRetRmMetalDtSave(saleRetRmMetalDt, id, vSaleRetMtId, principal);
	
		return retVal;			
		}
	
	@ResponseBody
	@RequestMapping(value = "/saleRetRmMetalDt/edit/{id}")
	public String editSaleRetRmMetalDt(@PathVariable int id, Model model) {

		SaleRetRmMetalDt saleRetRmMetalDt = saleRetRmMetalDtService.findOne(id);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("modalSaleRetRmMetalDtId", saleRetRmMetalDt.getId());
		jsonObject.put("department\\.id", saleRetRmMetalDt.getDepartment().getId());
		jsonObject.put("metal\\.id", saleRetRmMetalDt.getMetal().getId());
		jsonObject.put("purity\\.id", saleRetRmMetalDt.getPurity().getId());
		jsonObject.put("color\\.id", saleRetRmMetalDt.getColor().getId());
		jsonObject.put("metalWt", saleRetRmMetalDt.getMetalWt());
		jsonObject.put("rate", saleRetRmMetalDt.getRate());
		jsonObject.put("amount", saleRetRmMetalDt.getAmount());
		jsonObject.put("in9991", saleRetRmMetalDt.getIn999());
		jsonObject.put("remark", saleRetRmMetalDt.getRemark());
		jsonObject.put("vSaleRetMtId", saleRetRmMetalDt.getSaleRetMt().getId());
		
		
		return jsonObject.toString();
	}
	
	
	@RequestMapping("/saleRetRmMetalDt/delete/{id}")
	@ResponseBody
	public String deleteSaleRetRmMetalDt(@PathVariable int id, Principal principal) {

		String retVal = "-1";

		retVal = saleRetRmMetalDtService.saleRetRmMetalDtDelete(id, principal);

		return retVal;
	}
	
	
	
	
	//SaleRetRmCompDt
	
	@RequestMapping("/saleRetRmCompDt/listing")
	@ResponseBody
	public String saleRetRmCompDtListings(Model model,@RequestParam(value = "mtId", required = false) Integer mtId,Principal principal,
			@RequestParam(value = "disableFlg", required = false) String disableFlg) {
			return saleRetRmCompDtService.saleRetRmCompDtListing(mtId,disableFlg);
	}	
	
	
	@RequestMapping(value = "/saleRetRmCompDt/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditUserSaleRetRmCompDt(
			@Valid @ModelAttribute("saleRetRmCompDt") SaleRetRmCompDt saleRetRmCompDt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "vSaleRetRmCompMtId", required = true) Integer vSaleRetRmCompMtId,
			
			RedirectAttributes redirectAttributes, Principal principal) {
		
		String retVal = "-1";
		
			retVal= saleRetRmCompDtService.saleRetRmCompDtSave(saleRetRmCompDt, id, vSaleRetRmCompMtId, principal); 
	
		return retVal;			
		}


	@ResponseBody
	@RequestMapping(value = "/saleRetRmCompDt/edit/{id}")
	public String editSaleRetRmCompDt(@PathVariable int id, Model model) {

		SaleRetRmCompDt saleRetRmCompDt = saleRetRmCompDtService.findOne(id);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("modalSaleRetRmCompDtId", saleRetRmCompDt.getId());
		jsonObject.put("department\\.id", saleRetRmCompDt.getDepartment().getId());
		jsonObject.put("metal\\.id", saleRetRmCompDt.getMetal().getId());
		jsonObject.put("component\\.id", saleRetRmCompDt.getComponent().getId());
		jsonObject.put("purity\\.id", saleRetRmCompDt.getPurity().getId());
		jsonObject.put("color\\.id", saleRetRmCompDt.getColor().getId());
		jsonObject.put("qty", saleRetRmCompDt.getQty());
		jsonObject.put("metalWt", saleRetRmCompDt.getMetalWt());
		jsonObject.put("rate", saleRetRmCompDt.getRate());
		jsonObject.put("amount", saleRetRmCompDt.getAmount());
		jsonObject.put("vSaleRetRmCompMtId", saleRetRmCompDt.getSaleRetMt().getId());
		
		
		return jsonObject.toString();
	}
	
	@RequestMapping("/saleRetRmCompDt/delete/{id}")
	@ResponseBody
	public String deleteSaleRetRmCompDt(@PathVariable int id, Principal principal) {
		
	String retVal = "-1";
		
		retVal = saleRetRmCompDtService.saleRetRmCompDtDelete(id, principal);
		return retVal;
	}
	
	
	//Stone Return 
	
	
	@RequestMapping("/saleRetRmStnDt/listing")
	@ResponseBody
	public String saleRetRmStnDtListings(Model model,	@RequestParam(value = "mtId", required = false) Integer mtId,Principal principal,
			@RequestParam(value = "disableFlg", required = false) String disableFlg)
			 {
		
				return saleRetRmStnDtService.saleRetRmStnDtListing(mtId,disableFlg);
			 }
	
	
	@RequestMapping(value = "/saleRetRmStnDt/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditUserSaleRetRmStnDt(
			@Valid @ModelAttribute("saleRetRmStnDt") SaleRetRmStnDt saleRetRmStnDt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "vSaleRetRmStnMtId", required = true) Integer vSaleRetRmStnMtId,
			RedirectAttributes redirectAttributes, Principal principal) {
	
		String retVal = "-1";
		
			retVal= saleRetRmStnDtService.saleRetRmStnDtSave(saleRetRmStnDt, id, vSaleRetRmStnMtId, principal); 
					
	
		return retVal;			
		}
	
	
	@ResponseBody
	@RequestMapping(value = "/saleRetRmStnDt/edit/{id}")
	public String editSaleRetRmStnDt(@PathVariable int id, Model model) {

		SaleRetRmStnDt saleRetRmStnDt = saleRetRmStnDtService.findOne(id);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("modalSaleRetRmStnDtId", saleRetRmStnDt.getId());
		jsonObject.put("department\\.id", saleRetRmStnDt.getDepartment().getId());
		jsonObject.put("stoneType\\.id", saleRetRmStnDt.getStoneType().getId());
		jsonObject.put("shape\\.id", saleRetRmStnDt.getShape().getId());
		jsonObject.put("subShape\\.id", saleRetRmStnDt.getSubShape() != null ? saleRetRmStnDt.getSubShape().getId() : "");
		jsonObject.put("quality", saleRetRmStnDt.getQuality().getId());
		jsonObject.put("size", saleRetRmStnDt.getSize());
		jsonObject.put("vSieve", saleRetRmStnDt.getSieve() != null ? saleRetRmStnDt.getSieve() :"");
		jsonObject.put("vSizeGroupStr", saleRetRmStnDt.getSizeGroup() !=null ?  saleRetRmStnDt.getSizeGroup().getName() : "");
		jsonObject.put("stone", saleRetRmStnDt.getStone());
		jsonObject.put("carat", saleRetRmStnDt.getCarat());
		jsonObject.put("diffCarat", saleRetRmStnDt.getDiffCarat());
		jsonObject.put("rate", saleRetRmStnDt.getRate());
		jsonObject.put("amount", saleRetRmStnDt.getAmount());
		jsonObject.put("packetNo", saleRetRmStnDt.getPacketNo());
		jsonObject.put("remark", saleRetRmStnDt.getRemark());
		jsonObject.put("vSaleRetRmStnMtId", saleRetRmStnDt.getSaleRetMt().getId());
		
		return jsonObject.toString();
	}
	
	
	@RequestMapping("/saleRetRmStnDt/delete/{id}")
	@ResponseBody
	public String deleteSaleRetRmStnDt(@PathVariable int id, Principal principal) {
		
	String retVal = "-1";
		
		retVal = saleRetRmStnDtService.saleRetRmStnDtDelete(id, principal);
		return retVal;
	}
	
	
	@ResponseBody
	@RequestMapping("/saleRetMt/addSummaryDetails")
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
			retVal = saleRetMtService.addSaleRetSummaryDetails(mtId, fob, sgst, cgst, igst, otherCharges,finalPrice, principal);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return retVal;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/saleRetDt/add", method = RequestMethod.POST)
	public String addEditUser(
			@Valid @ModelAttribute("saleRetDt") SaleRetDt saleRetDt,
			BindingResult result,
			@RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes,
			Principal principal,
			@RequestParam(value = "vSaleRetMtId", required = true) Integer vSaleRetMtId,
			@RequestParam(value = "metalDtData", required = true) String metalDtData) {

		String retVal = "-1";

		if (result.hasErrors()) {
			return "sareRetMt/add";
		}

		
		retVal = saleRetDtService.transactionalSave(saleRetDt, id, vSaleRetMtId, metalDtData, principal);
		try {
			
		} catch (Exception e) {
			retVal = "Error:Contact Support";
			e.printStackTrace();
		}

		return retVal;

	}
	
	
	@RequestMapping("/saleRetRowMetalDtPickup/transfer")
	@ResponseBody
	public String saleRetRowMetalDtPickupTransfer(@RequestParam(value = "pMtId", required = false) Integer pMtId,			
		@RequestParam(value = "data", required = false) String data,
			Principal principal) throws ParseException {

			String retVal = "1";
			
	
			try {
				synchronized (this) {
					retVal = saleRetRmMetalDtService.saleMetalRowDataPickupSave(pMtId, data, principal);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				retVal = "Warning : Error Occoured Contact Support";
			}
			

		return retVal;
	}
	
	
	@RequestMapping("/saleCompRowDataPickup/transfer")
	@ResponseBody
	public String saleMetalRowDataPickup(@RequestParam(value = "pMtId", required = false) Integer pMtId,			
		@RequestParam(value = "data", required = false) String data,
			Principal principal) throws ParseException {

			String retVal = "1";
			
			
			try {
				synchronized (this) {
					retVal = saleRetRmCompDtService.saleCompRowDataPickupSave(pMtId, data, principal);
				}
			} catch (Exception e) {
				e.printStackTrace();
				retVal = "Warning : Error Occoured Contact Support";
			}
			
		return retVal;
	}
	
	
	@RequestMapping("/saleStoneRowDataPickup/transfer")
	@ResponseBody
	public String saleStoneRowDataPickup(@RequestParam(value = "pMtId", required = false) Integer pMtId,			
		@RequestParam(value = "data", required = false) String data,
			Principal principal) throws ParseException {

			String retVal = "1";
			
	
			try {
				synchronized (this) {
					retVal = saleRetRmStnDtService.saleStoneRowDataPickup(pMtId, data, principal);
				}
			} catch (Exception e) {
				e.printStackTrace();
				retVal = "Warning : Error Occoured Contact Support";
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
