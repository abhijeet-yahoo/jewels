package com.jiyasoft.jewelplus.controller.marketing.transactions;

import java.io.File;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import com.jiyasoft.jewelplus.common.Utils;
import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.OrderType;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StockMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecCompDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecMetalDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecMt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.GrecStnDt;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IDepartmentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IOrderTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IUserDeptTrfRightService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IBagMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IStockMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IGrecCompDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IGrecDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IGrecMetalDtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IGrecMtService;
import com.jiyasoft.jewelplus.service.marketing.transactions.IGrecStnDtService;

@RequestMapping("/marketing/transactions")
@Controller
public class GrecMtController {
	

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private IGrecMtService grecMtService;
	
	@Autowired
	private IGrecDtService grecDtService;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private IGrecStnDtService grecStnDtService;
	
	@Autowired
	private IGrecMetalDtService grecMetalDtService;
	
	@Autowired
	private IGrecCompDtService grecCompDtService;
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IOrderTypeService orderTypeService;
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private IUserDeptTrfRightService userDeptTrfRightService;
	
	@Autowired
	private IStockMtService stockMtService;
	
	@Autowired
	private IBagMtService bagMtService;
	
	
	@Value("${netWtWithComp}")
	private Boolean netWtWithCompFlg;
	
	/*
	 * @Value("${grecAuotoNumber}") private Boolean grecAuotoNumber;
	 */
	
	@ModelAttribute("grecMt")
	public GrecMt constructGrecMt() {
		return new GrecMt();
	}

	@ModelAttribute("grecDt")
	public GrecDt constructGrecDt() {
		return new GrecDt();
	}
	
	@ModelAttribute("grecMetalDt")
	public GrecMetalDt constructGrecMetalDt() {
		return new GrecMetalDt();
	}
	
	@ModelAttribute("grecStnDt")
	public GrecStnDt constructGrecStnDt() {
		return new GrecStnDt();
	}

	
	@ModelAttribute("grecCompDt")
	public GrecCompDt constructGrecCompDt() {
		return new GrecCompDt();
	}
	
	@Value("${upload.directory.path}")
	private String uploadDirecotryPath;

	@Value("${upload.parent.directory.name}")
	private String uploadParentDirecotryName;

	@Value("${upload.directory.name}")
	private String uploadDirecotryName;
	
	@RequestMapping("/orderPickupForGrn")
	public String orderPickupForGrn(Model model){
		
		return "orderPickupForGrn";
	}
	
	@RequestMapping("/grecMt")
	public String users(Model model, Principal principal) {

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("grecMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if (userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
				|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")) {

			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			return "grecMt";
		} else {
			if (roleRights == null) {
				return "accesDenied";
			} else {
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());

			}
			return "grecMt";
		}
	}
	
	
	
	@RequestMapping("/grecMt/listing")
	@ResponseBody
	public String grecMtListing(Model model, @RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search, Principal principal) throws ParseException {

		
		return grecMtService.grecMtListing(limit, offset, sort, order, search, principal);
	}

	
	
	private Model populateModel(Model model, Principal principal) {
		model.addAttribute("partyMap", partyService.getPartyList());
		model.addAttribute("purityMap", purityService.getPurityList());
		model.addAttribute("colorMap", colorService.getColorList());
	

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		model.addAttribute("adminRightsMap",
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR")
						|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN")
						|| userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE"));

		return model;
	}
	
	@RequestMapping("/grecMt/add")
	public String add(Model model, Principal principal) {
		model = populateModel(model, principal);

		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("grecMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		model.addAttribute("orderTypeMap", orderTypeService.findRepairAndPurchaseOrderTypeMap());
		
		Department department = departmentService.findByName("Marketing");
		model.addAttribute("locationMap", userDeptTrfRightService.getToDepartmentListFromUser(user.getId(), department.getId()));
		
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

			return "grecMt/add";

		} else {

			if (roleRights == null) {
				return "accesDenied";
			} else {
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());

			}

		}

		return "grecMt/add";
	}
	

	@RequestMapping(value = "/grecMt/add", method = RequestMethod.POST)
	public String addEditGrecMt(
			@Valid @ModelAttribute("grecMt") GrecMt grecMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal,
			@RequestParam(value = "grecMtPartyId", required = false) Integer grecMtPartyId,
			@RequestParam(value = "pOrderTypeIds", required = false) Integer pOrderTypeIds,
			@RequestParam(value = "pLocationIds", required = false) Integer pLocationIds,
			@RequestParam(value = "vTranDate", required = false) String vTranDate) throws ParseException {

		return grecMtService.saveGrecMt(grecMt, id, redirectAttributes, principal, result, grecMtPartyId,pOrderTypeIds,vTranDate,pLocationIds);
		
	}


	
	
	
	@RequestMapping("/grecMt/edit/{id}")
	public String edit(@PathVariable Integer id, Model model, Principal principal) {
		GrecMt grecMt = grecMtService.findOne(id);
		model.addAttribute("grecMt", grecMt);
		model = populateModel(model, principal);		
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("grecMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		model.addAttribute("orderTypeMap", orderTypeService.findRepairAndPurchaseOrderTypeMap());
		model.addAttribute("mtid", id);
		
		Department department = departmentService.findByName("Marketing");
		model.addAttribute("locationMap", userDeptTrfRightService.getToDepartmentListFromUser(user.getId(), department.getId()));
		
		
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

			return "grecMt/add";

		} else {

			if (roleRights == null) {
				return "accesDenied";
			} else {
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());

			}

		}

		return "grecMt/add";

	}
	
	@RequestMapping("/grecMt/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable int id) {
		return grecMtService.deleteGrecMt(id);
	}
	
	/* ------------------------------- GrecDt ---------------------------------------------- */
	
	@RequestMapping("/grecDt/listing")
	@ResponseBody
	public String grecDtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "mtId", required = false) Integer mtId,
			@RequestParam(value = "disableFlg", required = false) Boolean disableFlg,Principal principal) {
				

		if (offset == null) {
			offset = 0;
		}

		return grecDtService.grecDtLisiting(limit, offset, sort, order, search, mtId, principal,disableFlg);
			
		
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/grecDt/add", method = RequestMethod.POST)
	public String addEditUser(@Valid @ModelAttribute("grecDt") GrecDt grecDt, BindingResult result,
			@RequestParam(value = "id") Integer id, RedirectAttributes redirectAttributes, Principal principal,
			@RequestParam(value = "vGrecMtId", required = true) Integer vGrecMtId,
			@RequestParam(value = "metalDtData", required = true) String metalDtData) {

		String retVal = "-1";

		if (result.hasErrors()) {
			return "grecMt/add";
		}

		try {
			retVal= grecDtService.transactionalSave(grecDt,id,vGrecMtId, metalDtData, principal, netWtWithCompFlg);

		} catch (Exception e) {
			retVal = "Error:Contact Support";
			e.printStackTrace();
		}

		return retVal;

	}
	
	
	  @ResponseBody

	@RequestMapping(value = "/grecDt/edit/{id}")
	public String edit(@PathVariable int id, Model model) {

		GrecDt grecDt = grecDtService.findOne(id);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("modalGrecDtId", grecDt.getId());
		jsonObject.put("design\\.mainStyleNo", grecDt.getDesign().getMainStyleNo());
		jsonObject.put("pcs", grecDt.getPcs() != null ? grecDt.getPcs() : "0");
		//jsonObject.put("productSize\\.id", grecDt.getProductSize() != null ? grecDt.getProductSize().getId() : null);
		jsonObject.put("reqCarat", grecDt.getReqCarat() != null ? grecDt.getReqCarat() : "");
		jsonObject.put("purity\\.id", grecDt.getPurity() != null ? grecDt.getPurity().getId() : null);
		jsonObject.put("color\\.id", grecDt.getColor() != null ? grecDt.getColor().getId() : null);
		jsonObject.put("dueDate", grecDt.getDueDateStr() != null ? grecDt.getDueDateStr() : "");
		jsonObject.put("refNo", grecDt.getRefNo() != null ? grecDt.getRefNo() : "");
		jsonObject.put("stampInst", grecDt.getStampInst() != null ? grecDt.getStampInst() : "");
		jsonObject.put("discPercent", grecDt.getDiscPercent() != null ? grecDt.getDiscPercent() : "0.0");
		jsonObject.put("remark", grecDt.getRemark() != null ? grecDt.getRemark() : "");
		jsonObject.put("designRemark", grecDt.getDesignRemark() != null ? grecDt.getDesignRemark() : "");
		jsonObject.put("barcode", grecDt.getBarcode() != null ? grecDt.getBarcode() : "");
		jsonObject.put("grossWt", grecDt.getGrossWt() != null ? grecDt.getGrossWt() : "0.0");
		jsonObject.put("item", grecDt.getItem() != null ? grecDt.getItem() : "");
		jsonObject.put("ordRef", grecDt.getOrdRef() != null ? grecDt.getOrdRef() : "");

		return jsonObject.toString();
	}

	@RequestMapping("/grecDt/delete/{id}")
	@ResponseBody
	public String deleteDt(@PathVariable int id) {
		return grecDtService.deleteGrecDt(id);
	}
	
	

	
	
	
	
	@RequestMapping("/grecMetalDt/listing")
	@ResponseBody
	public String grecMetalDtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "grecDtId", required = false) Integer grecDtId,Principal principal) {
				
		
		
		return grecMetalDtService.grecMetalDtListing(grecDtId);
		
		
		
	}
	
	
	@RequestMapping("/grecStoneDt/listing")
	@ResponseBody
	public String orderStoneDtListing(Model model,
		@RequestParam(value = "limit", required = false) Integer limit,
		@RequestParam(value = "offset", required = false) Integer offset,
		@RequestParam(value = "sort", required = false) String sort,
		@RequestParam(value = "order", required = false) String order,
		@RequestParam(value = "search", required = false) String search,
		@RequestParam(value = "grecDtId", required = false) Integer grecDtId,Principal principal,
		@RequestParam(value = "canViewFlag", required = false) Boolean canViewFlag) {
		
		
		return grecStnDtService.grecStnDtListing(grecDtId, canViewFlag, principal);
	}
	
	
	@RequestMapping("/grecCompDt/listing")
	@ResponseBody
	public String grecCostCompDtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "grecDtId", required = false) Integer grecDtId,Principal principal,
			@RequestParam(value = "canViewFlag", required = false) Boolean canViewFlag) {
	
		return grecCompDtService.grecCompDtListing(grecDtId, canViewFlag, principal);
	}
	
	
	
	@RequestMapping("/orderList/listing")
	@ResponseBody
	public String orderList(@RequestParam(value = "orderTypeId", required = false) Integer orderTypeId,
			@RequestParam(value = "partyId", required = false) Integer partyId){
		
	return grecMtService.orderPickListing(orderTypeId, partyId);
	
	}	
	
	
	@RequestMapping("/orderPickupTransfer")
	@ResponseBody
	public String orderPickupTransfer(@RequestParam(value = "bagIds", required = false) String bagIds,
			@RequestParam(value = "grcmtId", required = false) Integer grcmtId,
			Principal principal) throws ParseException {

		String retVal = "-1";
	
	
		
		synchronized (this) {
			
			try {
				retVal = grecMtService.orderPickupTransferToStock(principal, bagIds, grcmtId);
				
			} catch (Exception e) {
				System.out.println(e);
			}
				
					
				
			
			
		}
		
	return retVal;

	}
	
	
	@RequestMapping("/generateBagBarcode")
	@ResponseBody
	public String generateBagBarcode(@RequestParam(value = "mtId", required = false) Integer mtId, Principal principal) throws ParseException {
		
		String retVal= "-1";
		
		String barcodeuploadFilePath = uploadDirecotryPath + File.separator + uploadParentDirecotryName + File.separator
				+ uploadDirecotryName + File.separator + "StockBarcode" + File.separator;

		synchronized (this) {
		return grecMtService.generateBarcodeForGrec(mtId, barcodeuploadFilePath, principal);
		}
		
	}
	
	
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
}
