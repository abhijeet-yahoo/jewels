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
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetRmCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetRmMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.ConsigRetRmStnDt;
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
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRetCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRetDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRetLabDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRetMetalDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRetMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRetRmCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRetRmMetalDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRetRmStnDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IConsigRetStnDtService;

@RequestMapping("/marketing/transactions")
@Controller
public class ConsigRetController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private RoleRightsService roleRightsService;
	
	@Autowired
	private IConsigRetMtService consigRetMtService;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	
	@Autowired
	private IHSNService hsnService;
	
	@Autowired
	private ILookUpMastService lookUpMastService;
	
	@Autowired
	private IEmployeeService employeeService;
	
	@Autowired
	private IUserDeptTrfRightService userDeptTrfRightService;
	
	@Autowired
	private IConsigRetDtService consigRetDtService;

	
	@Autowired
	private IConsigRetStnDtService consigRetStnDtService;
	
	@Autowired
	private IConsigRetCompDtService consigRetCompDtService;
	
	@Autowired
	private IConsigRetMetalDtService consigRetMetalDtService;
	
	@Autowired
	private IConsigRetLabDtService consigRetLabDtService;

	@Autowired
	private IConsigRetRmMetalDtService consigRetRmMetalDtService;
	
	@Autowired
	private IConsigRetRmCompDtService consigRetRmCompDtService;
	
	@Autowired
	private IConsigRetRmStnDtService consigRetRmStnDtService;
	
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
	
	
	@ModelAttribute("consigRetMt")
	public ConsigRetMt constructMt() {
		return new ConsigRetMt();
	}
	
	
	@ModelAttribute("consigRetDt")
	public ConsigRetDt constructDt() {
		return new ConsigRetDt();
	}
	
	
	@ModelAttribute("consigRetRmMetalDt")
	public ConsigRetRmMetalDt constructConsigRetRmMetalDt() {
		return new ConsigRetRmMetalDt();
	}
	
	
	@ModelAttribute("consigRetRmStnDt")
	public ConsigRetRmStnDt constructConsigRetRmStnDt() {
		return new ConsigRetRmStnDt();
	}
	
	
	@ModelAttribute("consigRetRmCompDt")
	public ConsigRetRmCompDt constructConsigRetRmCompDt() {
		return new ConsigRetRmCompDt();
	}
	
	
	@RequestMapping("/consigRetMt")
	public String users(Model model, Principal principal) {
		
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("consigRetMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			return "consigRetMt";
		} else {
			if (roleRights == null) {
				return "accesDenied";
			} else {
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());

			}
			return "consigRetMt";
		}
	}
	
	@RequestMapping("/consigRetMt/listing")
	@ResponseBody
	public String consigRetMtListing(Model model, @RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search, Principal principal)
			throws ParseException {

		return consigRetMtService.consigRetMtListing(limit, offset, sort, order, search, principal);

	}
	
	@RequestMapping("/consigRetMt/add")
	public String add(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("consigRetMt");
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
			
			
			return "consigRetMt/add";
			
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
		return "consigRetMt/add";
		}
	}
	
	
	@RequestMapping(value = "/consigRetMt/add", method = RequestMethod.POST)
	public String addEditConsigMt(
			@Valid @ModelAttribute("consigRetMt") ConsigRetMt consigRetMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal,
			@RequestParam(value = "pPartyIds", required = false) Integer pPartyIds,
			@RequestParam(value = "pLocationIds", required = false) Integer pLocationIds,
			@RequestParam(value = "pEmployeeIds", required = false) Integer pEmployeeIds,
			@RequestParam(value = "vTranDate", required = false) String vTranDate) throws ParseException {

		return consigRetMtService.saveConsigRetMt(consigRetMt, id, redirectAttributes, principal, result,pPartyIds,pLocationIds,pEmployeeIds,vTranDate);
		
	}
	
	
	@RequestMapping("/consigRetMt/edit/{id}")
	public String edit(@PathVariable int id, Model model, Principal principal) {
		

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("consigRetMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		ConsigRetMt consigRetMt = consigRetMtService.findOne(id);
		model.addAttribute("consigRetMt", consigRetMt);
		
		Department department = departmentService.findByName("Marketing");
		
		model.addAttribute("allPartyMap", partyService.getPartyList());
		model.addAttribute("locationMap",userDeptTrfRightService.getToDepartmentListFromUser(user.getId(), department.getId()));
		model.addAttribute("hsnMap", hsnService.getHsnList());
		model.addAttribute("transportMap", lookUpMastService.getActiveLookUpMastByType("Transport Mode"));
		model.addAttribute("salesMap", employeeService.getEmployeeList(department.getId()));
		// Metal
		model.addAttribute("metalMtlMap", metalService.getMetalList());
		model.addAttribute("colorMtlMap", colorService.getColorList());
		model.addAttribute("locationMtlMap", locationRightsService.getToLocationListFromUser(user.getId(),"metal"));
		model.addAttribute("purityMap", purityService.getPurityList());
		// Stone
		model.addAttribute("locationStnMap", locationRightsService.getToLocationListFromUser(user.getId(),"stone"));
		model.addAttribute("stoneTypeMap", stoneTypeService.getStoneTypeList());
		model.addAttribute("shapeMap", shapeService.getShapeList());

		// Component
		model.addAttribute("locationCompMap", locationRightsService.getToLocationListFromUser(user.getId(),"component"));
		model.addAttribute("metalCompMap", metalService.getMetalList());
		model.addAttribute("componentCompMap", componentService.getComponentList());
		model.addAttribute("purityCompMap", purityService.getPurityList());
		model.addAttribute("colorCompMap", colorService.getColorList());
		
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
			
			
			return "consigRetMt/add";
			
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
		

		return "consigRetMt/add";
		}
	}
	
	@RequestMapping("/consigRetDt/listing")
	@ResponseBody
	public String consigRetDtList(@RequestParam(value = "mtId", required = false) Integer mtId,
			@RequestParam(value = "disableFlg", required = false) String disableFlg){
		
		return consigRetDtService.consigRetDtListing(mtId,disableFlg);
		
	}	
	
	@RequestMapping("/consigRetCompDt/listing")
	@ResponseBody
	public String consigRetCompDtList(@RequestParam(value = "dtId", required = false) Integer dtId,
			@RequestParam(value = "disableFlg", required = false) String disableFlg){
		
	return consigRetCompDtService.consigRetCompDtListing(dtId,disableFlg);	
	
	}
	
	
	@RequestMapping("/consigRetMetalDt/listing")
	@ResponseBody
	public String consigRetMetalDtList(@RequestParam(value = "dtId", required = false) Integer dtId){
		
		return consigRetMetalDtService.consigRetMetalDtListing(dtId);
	
	}
	
	@RequestMapping("/consigRetStnDt/listing")
	@ResponseBody
	public String consigRetStnDtList(@RequestParam(value = "dtId", required = false) Integer dtId,
			@RequestParam(value = "disableFlg", required = false) String disableFlg){
		
	return consigRetStnDtService.consigRetStnDtListing(dtId,disableFlg);
		
	
	}
	
	@RequestMapping("/consigRetLabDt/listing")
	@ResponseBody
	public String consigRetLabDtList(@RequestParam(value = "dtId", required = false) Integer dtId,
			@RequestParam(value = "disableFlg", required = false) String disableFlg){
		
		return consigRetLabDtService.consigRetLabDtListing(dtId,disableFlg);
		
	}
	
	@ResponseBody
	@RequestMapping("/consigRetDt/delete/{id}")
	public String delete(@PathVariable int id){
		
		String retVal = consigRetDtService.deleteConsigRetDt(id);
		
		return retVal;
		
	}
	
	
	@RequestMapping("/consigRetPickup/transfer")
	@ResponseBody
	public String consigTransferAdjustment(@RequestParam(value = "pMtId", required = false) Integer pMtId,			
		@RequestParam(value = "data", required = false) String data,
			Principal principal) throws ParseException {

			String retVal = "1";
			
	
			try {
				synchronized (this) {
					retVal = consigRetMtService.consignmetTransfer(pMtId, data, principal);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				retVal = "Warning : Error Occoured Contact Support";
			}
			

		return retVal;
	}
	
	
	@ResponseBody
	@RequestMapping("/consigRetMt/delete/{id}")
	public String deleteMt(@PathVariable int id){
		String retVal = "-1";
		try {
			retVal = consigRetMtService.deleteConsigRetMt(id);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return retVal;
		
	}
	
	
	/* ConsigRetRmMetalDt */
	
	
	@RequestMapping("/consigRetRmMetalDt/listing")
	@ResponseBody
	public String consigRetRmMtlDtListing(@RequestParam(value = "mtId", required = false) Integer mtId,
			@RequestParam(value = "disableFlg", required = false) String disableFlg) {
		
		return consigRetRmMetalDtService.consigRetRmMetalDtListing(mtId,disableFlg);
	}
	
	@RequestMapping(value = "/consigRetRmMetalDt/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditConsigRetRmMetalDt(
			@Valid @ModelAttribute("consigRetRmMetalDt") ConsigRetRmMetalDt consigRetRmMetalDt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "vConsigRetMtId", required = true) Integer vConsigRetMtId,
			
			RedirectAttributes redirectAttributes, Principal principal) {

		
		String retVal = "-1";
		
			retVal= consigRetRmMetalDtService.consigRetRmMetalDtSave(consigRetRmMetalDt, id, vConsigRetMtId, principal);
	
		return retVal;			
		}
	
	@ResponseBody
	@RequestMapping(value = "/consigRetRmMetalDt/edit/{id}")
	public String editConsigRetRmMetalDt(@PathVariable int id, Model model) {

		ConsigRetRmMetalDt consigRetRmMetalDt = consigRetRmMetalDtService.findOne(id);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("modalConsigRetRmMetalDtId", consigRetRmMetalDt.getId());
		jsonObject.put("department\\.id", consigRetRmMetalDt.getDepartment().getId());
		jsonObject.put("metal\\.id", consigRetRmMetalDt.getMetal().getId());
		jsonObject.put("purity\\.id", consigRetRmMetalDt.getPurity().getId());
		jsonObject.put("color\\.id", consigRetRmMetalDt.getColor().getId());
		jsonObject.put("metalWt", consigRetRmMetalDt.getMetalWt());
		jsonObject.put("rate", consigRetRmMetalDt.getRate());
		jsonObject.put("amount", consigRetRmMetalDt.getAmount());
		jsonObject.put("in9991", consigRetRmMetalDt.getIn999());
		jsonObject.put("remark", consigRetRmMetalDt.getRemark());
		jsonObject.put("vConsigRetMtId", consigRetRmMetalDt.getConsigRetMt().getId());
		
		return jsonObject.toString();
	}
	
	
	@RequestMapping("/consigRetRmMetalDt/delete/{id}")
	@ResponseBody
	public String deleteConsigRetRmMetalDt(@PathVariable int id, Principal principal) {

		String retVal = "-1";

		retVal = consigRetRmMetalDtService.consigRetRmMetalDtDelete(id, principal);

		return retVal;
	}
	
	
	/* Consignment Stone return */
	
	@RequestMapping("/consigRetRmStnDt/listing")
	@ResponseBody
	public String consigRetRmStnDtListings(Model model,	@RequestParam(value = "mtId", required = false) Integer mtId,Principal principal,
			@RequestParam(value = "disableFlg", required = false) String disableFlg)
			 {
		
				return consigRetRmStnDtService.consigRetRmStnDtListing(mtId,disableFlg,principal);
			 }
	
	
	@RequestMapping(value = "/consigRetRmStnDt/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditUserConsigRetRmStnDt(
			@Valid @ModelAttribute("consigRetRmStnDt") ConsigRetRmStnDt consigRetRmStnDt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "vConsigRetRmStnMtId", required = true) Integer vConsigRetRmStnMtId,
			RedirectAttributes redirectAttributes, Principal principal) {
	
			String retVal = "-1";
		
			retVal= consigRetRmStnDtService.consigRetRmStnDtSave(consigRetRmStnDt, id, vConsigRetRmStnMtId, principal); 

			return retVal;			
		
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/consigRetRmStnDt/edit/{id}")
	public String editConsigRetRmStnDt(@PathVariable int id, Model model) {

		ConsigRetRmStnDt consigRetRmStnDt = consigRetRmStnDtService.findOne(id);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("modalConsigRetRmStnDtId", consigRetRmStnDt.getId());
		jsonObject.put("department\\.id", consigRetRmStnDt.getDepartment().getId());
		jsonObject.put("stoneType\\.id", consigRetRmStnDt.getStoneType().getId());
		jsonObject.put("shape\\.id", consigRetRmStnDt.getShape().getId());
		jsonObject.put("subShape\\.id", consigRetRmStnDt.getSubShape() != null ? consigRetRmStnDt.getSubShape().getId() : "");
		jsonObject.put("quality", consigRetRmStnDt.getQuality().getId());
		jsonObject.put("size", consigRetRmStnDt.getSize());
		jsonObject.put("vSieve", consigRetRmStnDt.getSieve() != null ? consigRetRmStnDt.getSieve() :"");
		jsonObject.put("vSizeGroupStr", consigRetRmStnDt.getSizeGroup() !=null ?  consigRetRmStnDt.getSizeGroup().getName() : "");
		jsonObject.put("stone", consigRetRmStnDt.getStone());
		jsonObject.put("carat", consigRetRmStnDt.getCarat());
		jsonObject.put("diffCarat", consigRetRmStnDt.getDiffCarat());
		jsonObject.put("rate", consigRetRmStnDt.getRate());
		jsonObject.put("amount", consigRetRmStnDt.getAmount());
		jsonObject.put("packetNo", consigRetRmStnDt.getPacketNo());
		jsonObject.put("remark", consigRetRmStnDt.getRemark());
		jsonObject.put("vConsigRetRmStnMtId", consigRetRmStnDt.getConsigRetMt().getId());
		
		return jsonObject.toString();
	}
	
	
	@RequestMapping("/consigRetRmStnDt/delete/{id}")
	@ResponseBody
	public String deleteConsigRetRmStnDt(@PathVariable int id, Principal principal) {
		
	String retVal = "-1";
		
		retVal = consigRetRmStnDtService.consigRetRmStnDtDelete(id, principal);
		return retVal;
	}
	
	
	/*Consignment Return	Component*/
	
	@RequestMapping("/consigRetRmCompDt/listing")
	@ResponseBody
	public String consigRetRmCompDtListings(Model model,@RequestParam(value = "mtId", required = false) Integer mtId,Principal principal,
			@RequestParam(value = "disableFlg", required = false) String disableFlg) {
			return consigRetRmCompDtService.consigRetRmCompDtListing(mtId,disableFlg);
	}	
	
	
	@RequestMapping(value = "/consigRetRmCompDt/add", method = RequestMethod.POST)
	@ResponseBody
	public String addEditUserConsigRetRmCompDt(
			@Valid @ModelAttribute("consigRetRmCompDt") ConsigRetRmCompDt consigRetRmCompDt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			@RequestParam(value = "vConsigRetRmCompMtId", required = true) Integer vConsigRetRmCompMtId,
			
			RedirectAttributes redirectAttributes, Principal principal) {
		
			String retVal = "-1";
		
			retVal= consigRetRmCompDtService.consigRetRmCompDtSave(consigRetRmCompDt, id, vConsigRetRmCompMtId, principal); 
	
			return retVal;			
		
	}


	@ResponseBody
	@RequestMapping(value = "/consigRetRmCompDt/edit/{id}")
	public String editConsigRetRmCompDt(@PathVariable int id, Model model) {

		ConsigRetRmCompDt consigRetRmCompDt = consigRetRmCompDtService.findOne(id);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("modalConsigRetRmCompDtId", consigRetRmCompDt.getId());
		jsonObject.put("department\\.id", consigRetRmCompDt.getDepartment().getId());
		jsonObject.put("metal\\.id", consigRetRmCompDt.getMetal().getId());
		jsonObject.put("component\\.id", consigRetRmCompDt.getComponent().getId());
		jsonObject.put("purity\\.id", consigRetRmCompDt.getPurity().getId());
		jsonObject.put("color\\.id", consigRetRmCompDt.getColor().getId());
		jsonObject.put("qty", consigRetRmCompDt.getQty());
		jsonObject.put("metalWt", consigRetRmCompDt.getMetalWt());
		jsonObject.put("rate", consigRetRmCompDt.getRate());
		jsonObject.put("amount", consigRetRmCompDt.getAmount());
		jsonObject.put("vConsigRetRmCompMtId", consigRetRmCompDt.getConsigRetMt().getId());
		
		
		return jsonObject.toString();
		
	}
	
	@RequestMapping("/consigRetRmCompDt/delete/{id}")
	@ResponseBody
	public String deleteConsigRetRmCompDt(@PathVariable int id, Principal principal) {
		
	String retVal = "-1";
		
		retVal = consigRetRmCompDtService.consigRetRmCompDtDelete(id, principal);
		return retVal;
	}
	
	
	
	@RequestMapping("/consigMetalRowDataPickup/transfer")
	@ResponseBody
	public String consigMetalRowDataPickup(@RequestParam(value = "pMtId", required = false) Integer pMtId,			
		@RequestParam(value = "data", required = false) String data,
			Principal principal) throws ParseException {

			String retVal = "1";
			
	
			try {
				synchronized (this) {
					retVal = consigRetRmMetalDtService.consigMetalRowDataPickup(pMtId, data, principal);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				retVal = "Warning : Error Occoured Contact Support";
			}
			

		return retVal;
	}
	
	
	
	
	
	@RequestMapping("/consigCompRowDataPickup/transfer")
	@ResponseBody
	public String consigCompRowDataPickup(@RequestParam(value = "pMtId", required = false) Integer pMtId,			
		@RequestParam(value = "data", required = false) String data,
			Principal principal) throws ParseException {

			String retVal = "1";
			
	
			try {
				synchronized (this) {
					retVal = consigRetRmCompDtService.consigCompRowDataPickup(pMtId, data, principal);
				}
			} catch (Exception e) {
				e.printStackTrace();
				retVal = "Warning : Error Occoured Contact Support";
			}
			
		return retVal;
	}
	
	
	
	@RequestMapping("/consigStoneRowDataPickup/transfer")
	@ResponseBody
	public String consigStoneRowDataPickup(@RequestParam(value = "pMtId", required = false) Integer pMtId,			
		@RequestParam(value = "data", required = false) String data,
			Principal principal) throws ParseException {

			String retVal = "1";
			
	
			try {
				synchronized (this) {
					retVal = consigRetRmStnDtService.consigStoneRowDataPickup(pMtId, data, principal);
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
