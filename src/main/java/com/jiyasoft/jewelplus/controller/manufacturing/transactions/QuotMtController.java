package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiyasoft.jewelplus.domain.admin.MenuMast;
import com.jiyasoft.jewelplus.domain.admin.RoleRights;
import com.jiyasoft.jewelplus.domain.admin.User;
import com.jiyasoft.jewelplus.domain.admin.UserRole;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ClientReference;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotExportExcel;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMetal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotQuality;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.QuotStnDt;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IClientRefService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IComponentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILabourTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IShapeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IStoneTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotLabDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotMtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.IQuotStnDtService;

@RequestMapping("/manufacturing/transactions")
@Controller

public class QuotMtController {
	
	@Autowired
	private IQuotMtService quotMtService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private IClientRefService clientRefService;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private IComponentService componentService;
	
	@Autowired
	private IPurityService purityService;
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private ILabourTypeService labourTypeService;
	
	@Autowired
	private ISettingService settingService;
	
	@Autowired
	private ISettingTypeService settingTypeService;

	@Autowired
	private IQuotDtService quotDtService;
	
	@Autowired
	private IQuotStnDtService quotStnDtService;
	
	@Autowired
	private IQuotCompDtService quotCompDtService;
	
	@Autowired
	private IQuotLabDtService quotLabDtService;
	
	
	@Autowired
	private IShapeService shapeService;
	
	@Autowired
	private IStoneTypeService stoneTypeService;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IQuotMetalService quotMetalService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;
	
	@Autowired
	private UserRoleService userRoleService;
	

	
	@Value("${netWtWithComp}")
	private Boolean netWtWithCompFlg;
	
		
	@ModelAttribute("quotMt")
	public QuotMt construct() {
		return new QuotMt();
	}

	
	@ModelAttribute("quotDt")
	public QuotDt constructDt() {
		return new QuotDt();
	}
	
	@ModelAttribute("quotStnDt")
	public QuotStnDt constructStnDt() {
		return new QuotStnDt();
	}
	
	
	@ModelAttribute("quotCompDt")
	public QuotCompDt constructCompDt() {
		return new QuotCompDt();
	}
	
	
	@ModelAttribute("quotLabDt")
	public QuotLabDt constructLabDt() {
		return new QuotLabDt();
	}
	
	@ModelAttribute("quotQuality")
	public QuotQuality constructQuotQuality() {
		return new QuotQuality();
	}
	
	@RequestMapping("/quotMt/uploadExcel")
	public String excelFilePage(Model model) {
		model.addAttribute("tableDisp", "no");
		return "uploadExcelQuotDtStyle";
	}
	
	@RequestMapping("/quotMt")
	public String quot(Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("quotMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);

		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			return "quotMt";
		} else {
			if (roleRights == null) {
				return "accesDenied";
			} else 
			{
				
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());
				
				return "quotMt";
			}

		}

	}
	

	
	
	@RequestMapping("/quotMt/listing")
	@ResponseBody
	public String quotMtListing(Model model, @RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search, Principal principal) {

		StringBuilder sb = new StringBuilder();
		Page<QuotMt> quotMts = null;

		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("quotMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
	

//		Long rowCount = null;
//		rowCount = quotMtService.countAll(search);
		quotMts = quotMtService.searchAll(limit, offset, sort, order, search);

		sb.append("{\"total\":").append(quotMts.getTotalElements()).append(",\"rows\": [");

		for (QuotMt quotMt : quotMts) {

			sb.append("{\"id\":\"").append(quotMt.getId()).append("\",\"party\":\"")
					.append((quotMt.getParty() != null ? quotMt.getParty().getPartyCode() : "")).append("\",\"invNo\":\"")
					.append(quotMt.getInvNo())
					.append("\",\"invDate\":\"")
					.append(quotMt.getInvDateStr())
					.append("\",\"masterQuot\":\"")
					.append(quotMt.getMasterFlg()? "Yes":"No")
					.append("\",\"metalRate\":\"")
					.append((quotMt.getMetalRate() != null ? quotMt.getMetalRate() : ""))
					.append("\",\"refNo\":\"")
					.append((quotMt.getRefNo() != null ? quotMt.getRefNo() : ""))
					.append("\",\"silverRate\":\"")
					.append((quotMt.getSilverRate() != null ? quotMt.getMetalRate() : ""))
					.append("\",\"discPerc\":\"")
					.append((quotMt.getDiscPercent() != null ? quotMt.getDiscPercent() : ""))
					.append("\",\"deactive\":\"")
					.append((quotMt.getDeactive() == null ? "" : (quotMt.getDeactive() ? "Deactive" : "Active")));
					if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
							userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
				
					sb.append("\",\"action1\":\"")
					.append("<a href='/jewels/manufacturing/transactions/quotMt/edit/")
					.append(quotMt.getId())
					.append(".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
					.append("\",\"action2\":\"")
					.append("<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/transactions/quotMt/delete/")
					.append(quotMt.getId())
					.append(".html' class='btn btn-xs btn-danger triggerRemove")
					.append(quotMt.getId())
					.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
					.append("\",\"action3\":\"")
					.append("<a href='/jewels/manufacturing/transactions/quotMt/view/")
					.append(quotMt.getId()).append(".html'")
					.append(" class='btn btn-xs btn-success' ><span class='glyphicon glyphicon-eye-open'></span>&nbsp;View</a>")
					.append("\"},");
					}
					else
					{
						sb.append("\",\"action1\":\"");
						if (roleRights.getCanEdit()) {
						sb.append("<a href='/jewels/manufacturing/transactions/quotMt/edit/")
						.append(quotMt.getId());
						}else{
							sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
						}
						sb.append(".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>");
						
						sb.append("\",\"action2\":\"");
						if (roleRights.getCanDelete()) {
							sb.append(
									"<a onClick='javascript:doDelete(event, this)' href='/jewels/manufacturing/transactions/quotMt/delete/")
									.append(quotMt.getId()).append(".html'");
						} else {
							sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
						}
						sb.append(" class='btn btn-xs btn-danger triggerRemove")
								.append(quotMt.getId())
								.append("'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>");
						
						sb.append("\",\"action3\":\"");
						if (roleRights.getCanView()) {
						sb.append("<a href='/jewels/manufacturing/transactions/quotMt/view/")
						.append(quotMt.getId()).append(".html'");
						}
						 else {
								sb.append("<a onClick='javascript:displayMsg(event, this)' href='javascript:void(0)'");
							}
						sb.append(
						" class='btn btn-xs btn-success' ><span class='glyphicon glyphicon-eye-open'></span>&nbsp;View</a>")
						.append("\"},");
					}
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";

		return str;

	}
	
	@RequestMapping("/quotMt/add")
	public String addQuotMt(Model model,Principal principal) {
		
		model = populateModel(model,principal); 
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("quotMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			
			return "quotMt/add";

		}else {
			
			if(roleRights == null){
				return "accesDenied";
			}else{
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());
				
			}
			
		}

		return "quotMt/add";
		
		
	}
	
	
	private Model populateModel(Model model, Principal principal) {
		model.addAttribute("partyMap", partyService.getPartyList());
		model.addAttribute("componentMap", componentService.getComponentList());
		model.addAttribute("purityMap", purityService.getPurityList());
		model.addAttribute("colorMap", colorService.getColorList());
		model.addAttribute("labourTypeMap",labourTypeService.getLabourTypeList());
		model.addAttribute("settingMap",settingService.getSettingList());
		model.addAttribute("settingTypeMap",settingTypeService.getSettingTypeList());
		model.addAttribute("shapeMap", shapeService.getShapeList());
		model.addAttribute("stoneTypeMap", stoneTypeService.getStoneTypeList());
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		model.addAttribute("adminRightsMap",userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
		userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE"));
		return model;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/quotMt/add", method = RequestMethod.POST)
	public String addEditQuotMt(@Valid @ModelAttribute("quotMt") QuotMt quotMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, Principal principal,
			@RequestParam(value = "quotMtPartyId") int partyId) {

		String action = "added";
		String retVal = "/jewels/manufacturing/transactions/quotMt/add.html";
		Date aDate = null;

		if (result.hasErrors()) {
			return "quotMt/add";
		}
		
		Party party =partyService.findOne(partyId);
		
		if(quotMt.getRefNo() !=null && !quotMt.getRefNo().isEmpty()){
			Boolean refNoAvailable=true;
			
			if (id == null) {

				refNoAvailable = (quotMtService.findByRefNoAndDeactive(quotMt.getRefNo(), false) == null);
			} else {
				QuotMt quotMt2 = quotMtService.findOne(id);
				if (! (quotMt.getRefNo().equalsIgnoreCase(quotMt2.getRefNo())) ) {
					refNoAvailable = (quotMtService.findByRefNoAndDeactive(quotMt.getRefNo(), false) == null);
				}
			}

			
			if(refNoAvailable.equals(false)){
				return "-1";
			}
			
		}
		
		if(quotMt.getMasterFlg().equals(true)) {
			Boolean masterAvailable=true;
			
			QuotMt quotMt2 =quotMtService.findByPartyAndMasterFlgAndDeactive(party, true, false);
			if(quotMt2 !=null) {
			if (id == null) {

				masterAvailable = (quotMt2 == null);
			} else {
				
				if (! (quotMt.getId().equals(quotMt2.getId())) ) {
					masterAvailable = (quotMt2 == null);
				}
			}
			}
			
			if(masterAvailable.equals(false)){
				return "-2";
			}
			
		}
		
		
		
		
		

		if (id == null || id.equals("") || (id != null && id == 0)) {
			
			aDate = new java.util.Date();
			quotMt.setCreatedBy(principal.getName());
			quotMt.setCreatedDate(new java.util.Date());
			quotMt.setUniqueId(aDate.getTime());
			quotMt.setPartyNm(party.getName());
			quotMt.setParty(party);
			
			if(quotMt.getPurity().getId() == null){
				quotMt.setPurity(null);
			}
			
			if(quotMt.getColor().getId() == null){
				quotMt.setColor(null);
			}
			
			Long maxSrno = quotMtService.maxBySrNo();
			maxSrno = (maxSrno == null ? 1 : maxSrno + 1);
			quotMt.setSrNo(maxSrno);
			
			quotMtService.save(quotMt);
			
		} else {
			
			QuotMt quotMtOld = quotMtService.findOne(id);
			
			quotMtOld.setInvNo(quotMt.getInvNo());
			quotMtOld.setInvDate(quotMt.getInvDate());
			quotMtOld.setParty(quotMt.getParty());
			quotMtOld.setPartyNm(party.getName());
			quotMtOld.setParty(party);
			quotMtOld.setRefNo(quotMt.getRefNo());
			/*quotMtOld.setMetalRate(quotMt.getMetalRate());
			quotMtOld.setSilverRate(quotMt.getSilverRate());
			quotMtOld.setAddPercent(quotMt.getAddPercent());
			quotMtOld.setTagPercent(quotMt.getTagPercent());
			quotMtOld.setDispPercent(quotMt.getDispPercent());*/
			quotMtOld.setDiscPercent(quotMt.getDiscPercent());
			/*quotMtOld.setHandlingPerc(quotMt.getHandlingPerc());*/
			quotMtOld.setPurity((quotMt.getPurity() != null ? quotMt.getPurity() : null));
			quotMtOld.setColor(quotMt.getColor() != null ? quotMt.getColor() : null);
			quotMtOld.setModiBy(principal.getName());
			quotMtOld.setModiDate(new java.util.Date());
			quotMtOld.setMasterFlg(quotMt.getMasterFlg());
			if(quotMt.getPurity().getId() == null){
				quotMtOld.setPurity(null);
			}
			
			if(quotMt.getColor().getId() == null){
				quotMtOld.setColor(null);
			}
			
			quotMtOld.setExchangeRate(quotMt.getExchangeRate());
			quotMtService.save(quotMtOld);
			
			action = "updated";
			retVal =  "/jewels/manufacturing/transactions/quotMt/edit/"+id+".html";
		}
		
		
		if (action.equals("added")) {
			
			QuotMt quotMtNew = quotMtService.findByUniqueId(quotMt.getUniqueId());
			Integer quotMtId = quotMtNew.getId();
			
			Calendar date = new GregorianCalendar();
			String vYear = "" + date.get(Calendar.YEAR);
			vYear = vYear.substring(2);
			
			quotMtNew.setInvNo("QUOT"+"-"+quotMtNew.getSrNo()+"-"+vYear);
			quotMtService.save(quotMtNew);
			
			retVal  = "/jewels/manufacturing/transactions/quotMt/edit/"+quotMtId+".html";
		}
		
		
		//redirectAttributes.addFlashAttribute("success", true);
		//redirectAttributes.addFlashAttribute("action", action);
		
		return retVal;
	}
	
	
	@RequestMapping("/quotMt/edit/{id}")
	public String edit(@PathVariable int id, Model model,Principal principal) {
		QuotMt quotMt = quotMtService.findOne(id);
		model.addAttribute("quotMt", quotMt);
		model.addAttribute("rLockStatus", quotMt.getrLock());
		model = populateModel(model,principal);
		model.addAttribute("colorMap", colorService.getColorList());
		model.addAttribute("purityMap", purityService.getPurityList());
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("quotMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			
			return "quotMt/add";

		}else {
			
			if(roleRights == null){
				return "accesDenied";
			}else{
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());
				return "quotMt/add";
			}
			
			
		}

		
		
	}
	
	@RequestMapping("/quotMt/view/{id}")
	public String view(@PathVariable int id, Model model, Principal principal) {
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("quotMt");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		QuotMt quotMt = quotMtService.findOne(id);
		model.addAttribute("quotMt", quotMt);
		model.addAttribute("rLockStatus", quotMt.getrLock());
		model = populateModel(model,principal);
		model.addAttribute("colorMap", colorService.getColorList());
		model.addAttribute("purityMap", purityService.getPurityList());
		
		if(userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMINISTRATOR") ||
				userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN") || userRole.getRoleMast().getRoleNm().equalsIgnoreCase("ADMIN ROLE")){
		
			model.addAttribute("canAdd", true);
			model.addAttribute("canEdit", true);
			model.addAttribute("canDelete", true);
			model.addAttribute("canView", true);
			
			return "quotMt/add";

		}else {
			
			if(roleRights == null){
				return "accesDenied";
			}else{
				model.addAttribute("canAdd", roleRights.getCanAdd());
				model.addAttribute("canEdit", roleRights.getCanEdit());
				model.addAttribute("canDelete", roleRights.getCanDelete());
				model.addAttribute("canView", roleRights.getCanView());
				return "quotMt/add";
			}
			
		}

		
		

	}

	
	
	
	@RequestMapping("/quotMt/delete/{id}")
	public String delete(@PathVariable int id) {
		quotMtService.delete(id);
		
		QuotMt quotMt = quotMtService.findOne(id);
		
		
		
		
		List<QuotDt> quotDts = quotDtService.findByQuotMtAndDeactive(quotMt, false);
		for(QuotDt quotDt:quotDts){
			quotDtService.delete(quotDt.getId());
		}
		
		
		List<QuotMetal> quotMetals = quotMetalService.findByQuotMtAndDeactive(quotMt, false);
		for(QuotMetal quotMetal:quotMetals){
			quotMetalService.delete(quotMetal.getId());
		}
		
		List<QuotStnDt> quotStnDts = quotStnDtService.findByQuotMtAndDeactive(quotMt, false);
		for(QuotStnDt quotStnDt:quotStnDts){
			quotStnDtService.delete(quotStnDt.getId());
		}
		
		List<QuotCompDt> quotCompDts = quotCompDtService.findByQuotMtAndDeactive(quotMt, false);
		for(QuotCompDt quotCompDt:quotCompDts){
			quotCompDtService.delete(quotCompDt.getId());
		}
		
		List<QuotLabDt> quotLabDts = quotLabDtService.findByQuotMtAndDeactive(quotMt, false);
		for(QuotLabDt quotLabDt:quotLabDts){
			quotLabDtService.delete(quotLabDt.getId());;
		}

		return "redirect:/manufacturing/transactions/quotMt.html";
	}
	
	
	@RequestMapping("/quotMt/invoiceNoAvailable")
	@ResponseBody
	public String invoiceAvailable(@RequestParam String invNo,
			@RequestParam Integer id) {

		Boolean invoiceAvailable = true;

		if (id == null) {

			invoiceAvailable = (quotMtService.findByInvNoAndDeactive(invNo, false) == null);
		} else {
			QuotMt quotMt = quotMtService.findOne(id);
			if (! (invNo.equalsIgnoreCase(quotMt.getInvNo())) ) {
				invoiceAvailable = (quotMtService.findByInvNoAndDeactive(invNo, false) == null);
			}
		}

		return invoiceAvailable.toString();
	}

	
	
	@RequestMapping(value = "/applyRate/quotMt", method = RequestMethod.POST)
	@ResponseBody
	public String applyRate(@Valid @ModelAttribute("quotMt") QuotMt quotMt,
			@RequestParam(value="tempPartyId")Integer partyId ,
			@RequestParam(value="tempPurityId")Integer purityId,
			@RequestParam(value="tempColorId")Integer colorId,BindingResult result,
			Principal principal){
		
		if (result.hasErrors()) {
			return "-1";
			
		}

		
		String retval="-1";
		
		try {
			
			/*
			 * Party party=partyService.findOne(partyId); quotMt.setParty(party);
			 * if(purityId == null){ quotMt.setPurity(null); } if(colorId == null){
			 * quotMt.setColor(null); }
			 * 
			 * quotMtService.save(quotMt);
			 */
			
			
			List<QuotDt> quotDts = quotDtService.findByQuotMtAndDeactive(quotMt, false);
			
			for(QuotDt quotDt : quotDts){
				quotDtService.applyRate(quotDt,principal,netWtWithCompFlg);
				
				
			} 
			retval="1";
			
		} catch (Exception e) {
			e.printStackTrace();
			retval="-1";
		}
			
		
		
	
		
		return retval;
	}
	
	
	/*@RequestMapping(value = "/applyRate/quotMt", method = RequestMethod.POST)
	@ResponseBody
	public String applyRate(
			@RequestParam(value="quotMtIdPkAR") Integer mtId,
			@RequestParam(value="quotGldRate") Double quotGldRate,
			@RequestParam(value="quotSlvRate") Double quotSlvRate,
			@RequestParam(value="quotAddPerc") Double quotAddPerc,
			@RequestParam(value="quotTagPerc") Double quotTagPerc,
			@RequestParam(value="quotDisPerc") Double quotDisPerc,Principal principal){
		
		String retval="1";
		
		QuotMt quotMt = quotMtService.findOne(mtId);
		quotMt.setMetalRate(quotGldRate);
		quotMt.setSilverRate(quotSlvRate);
		quotMt.setAddPercent(quotAddPerc);
		quotMt.setTagPercent(quotTagPerc);
		quotMt.setDispPercent(quotDisPerc);
		quotMtService.save(quotMt);
		
				
		List<QuotDt> quotDts = quotDtService.findByQuotMtAndDeactive(quotMt, false);
		
		for(QuotDt quotDt : quotDts){
			quotDtService.applyRate(quotDt,principal);
			
			
		} // ending the main for loop
		
		
		
		return retval;
	}*/
	
	
	
	@RequestMapping("/quotationMt/report/listing")
	@ResponseBody
	public String quotaionMtReportListing(Model model, @RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search, Principal principal){
		
		StringBuilder sb = new StringBuilder();
		Page<QuotMt> quotMts = null;
		
		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}
		
		
		quotMts = quotMtService.searchAll(limit, offset, sort, order, search);

		sb.append("{\"total\":").append(quotMts.getTotalElements()).append(",\"rows\": [");
		
		for (QuotMt quotMt : quotMts) {
			
			sb.append("{\"id\":\"")
			.append(quotMt.getId())
			.append("\",\"invNo\":\"")
			.append(quotMt.getInvNo())
			.append("\"},");
		}
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]}";
						
		return str;
		
		
	}
	
	@RequestMapping("/quotParty/discPerc")
	@ResponseBody
	public String discPercFromParty(
			@RequestParam(value = "partyId")Integer partyId){
		
		System.out.println("in controller "+partyId);
		Double discPerc = 0.0;
		
		Party party = partyService.findOne(partyId);
		System.out.println("party obj "+party.getId());
		if(party.getDiscountPercent()!=null){
			discPerc = party.getDiscountPercent();
		}
		
		return discPerc+"";
	}
	
	
	
	
	
	
	@ResponseBody
	@RequestMapping("/quotMt/barcode")
	public String barcode(
			@RequestParam(value="mtId")Integer mtId,Principal principal){
		
		String retVal = "1";
		QuotMt quotMt = quotMtService.findOne(mtId);
		List<QuotDt> quotDts = quotDtService.findByQuotMtAndDeactive(quotMt, false);
		
		try {
			for(QuotDt quotDt:quotDts){
				
				if(quotDt.getrLock().equals(false)){
					
					List<QuotMetal>quotMetals =quotMetalService.findByQuotDtAndDeactive(quotDt, false);
					if(quotMetals.size()>0){
						QuotMetal quotMetal =quotMetals.get(0);
						
						ClientReference clientReference =clientRefService.findByPartyAndDesignAndPurityAndDeactive(quotMt.getParty(), quotDt.getDesign(),
								quotMetal.getPurity(),false);
						
						if(clientReference !=null ){
						
						
							if(quotMt.getParty().getName().equalsIgnoreCase("005")){
								Double vWt =Math.round((clientReference.getFinishWt()+((clientReference.getFinishWt()*5)/100))*100.0)/100.0;
								quotDt.setGrossWt(vWt);
							}else{
								
								quotDt.setGrossWt(clientReference.getFinishWt());
								
							}
							
						
						
						List<QuotStnDt>quotStnDts =quotStnDtService.findByQuotDtAndDeactive(quotDt, false);
						Integer totStn=0;
						Double totCarat =0.0;
						for(QuotStnDt quotStnDt :quotStnDts){
							
							totStn +=quotStnDt.getStone();
							totCarat +=Math.round(quotStnDt.getCarat()*1000.0)/1000.0;
							
						}
						
						Double vDiaWt =  Math.round((totCarat/5)*1000.0)/1000.0;
						
						List<QuotCompDt>quotCompDts =quotCompDtService.findByQuotDtAndDeactive(quotDt, false);
						Double totCompWt =0.0;
						for(QuotCompDt quotCompDt :quotCompDts){
							
							totCompWt +=Math.round(quotCompDt.getMetalWt()*1000.0)/1000.0;
							
							
						}
						Double vNetWt =0.0;
						if(netWtWithCompFlg.equals(true)){
							vNetWt =Math.round((quotDt.getGrossWt()-vDiaWt)*1000.0)/1000.0;	
						}else{
							vNetWt =Math.round((quotDt.getGrossWt()-vDiaWt-totCompWt)*1000.0)/1000.0;
						}
						
						
						
						quotDt.setNetWt(vNetWt);
						
					
				
						if(quotMetal.getColor().getName().equalsIgnoreCase("Y")){
							quotDt.setBarcode(clientReference.getY());
						}else if(quotMetal.getColor().getName().equalsIgnoreCase("W")){
							quotDt.setBarcode(clientReference.getW());
						}else if (quotMetal.getColor().getName().equalsIgnoreCase("P")){
							quotDt.setBarcode(clientReference.getP());
						}else if (quotMetal.getColor().getName().equalsIgnoreCase("PW")){
							quotDt.setBarcode(clientReference.getPw());
						}else if (quotMetal.getColor().getName().equalsIgnoreCase("PY")){
							quotDt.setBarcode(clientReference.getPy());
						}else if (quotMetal.getColor().getName().equalsIgnoreCase("TT")){
							quotDt.setBarcode(clientReference.getTt());
						}else if (quotMetal.getColor().getName().equalsIgnoreCase("WP")){
							quotDt.setBarcode(clientReference.getWp());
						}else if (quotMetal.getColor().getName().equalsIgnoreCase("WY")){
							quotDt.setBarcode(clientReference.getWy());
						}else if (quotMetal.getColor().getName().equalsIgnoreCase("YP")){
							quotDt.setBarcode(clientReference.getYp());
						}else if (quotMetal.getColor().getName().equalsIgnoreCase("YW")){
							quotDt.setBarcode(clientReference.getYw());
						}
						
						quotDtService.save(quotDt);
						
									
						quotMetal.setMetalWeight(vNetWt);
						
						quotMetalService.save(quotMetal);
						
						}
						
					}
					
					
					quotDtService.applyMetal(quotDt );
					
					quotDtService.applyLabRate(quotDt,principal);				
					
					quotDtService.updateFob(quotDt,netWtWithCompFlg);
					
					
					
					
					
				}
				
				
				

				
				
				
				
			}
			
			
		} catch (Exception e) {
		
			e.printStackTrace();
			System.out.println(e);
		retVal="-1";
		}
		
	
		
		
		return retVal;
	}
	
	
	
	@ResponseBody
	@RequestMapping("/quotMt/summary")
	public String getSummary(
			@RequestParam(value="mtId")Integer mtId,Principal principal){
		
		
		QuotMt quotMt =quotMtService.findOne(mtId);
		
		List<QuotDt>quotDts =quotDtService.findByQuotMtAndDeactive(quotMt, false);
		
		JSONObject jsonObject = new JSONObject();
		Double totPcs=0.0;
		Integer totStone=0;
		Double totGrossWt=0.0;
		Double totNetWt=0.0;
		Double totCarat=0.0;
		Double totCompWt=0.0;
		Double totMetalVal=0.0;
		Double totStnVal=0.0;
		Double totCompVal=0.0;
		Double totLabVal=0.0;
		Double totSetVal=0.0;
		Double totHdlgVal=0.0;
		Double totFobVal=0.0;
		Double totDiscAmt=0.0;
		Double totFinalPrice=0.0;
		
		for(QuotDt quotDt :quotDts){
			
			totPcs +=quotDt.getPcs();
			totGrossWt +=quotDt.getGrossWt();
			totNetWt += quotDt.getNetWt();
			totMetalVal += quotDt.getMetalValue();
			totStnVal += quotDt.getStoneValue();
			totCompVal +=quotDt.getCompValue();
			totLabVal += quotDt.getLabValue();
			totSetVal += quotDt.getSetValue();
			totHdlgVal += quotDt.getHdlgValue();
			totFobVal += quotDt.getFob();
			totDiscAmt+= quotDt.getDiscAmount();
			totFinalPrice += quotDt.getFinalPrice();
			
			
			
			
			
		}
		
		
		List<QuotStnDt>quotStnDts =quotStnDtService.findByQuotMtAndDeactive(quotMt, false);
		for(QuotStnDt quotStnDt : quotStnDts){
			
			totStone +=quotStnDt.getStone();
			totCarat += quotStnDt.getCarat();
			
			
		}
		
		List<QuotCompDt>quotCompDts = quotCompDtService.findByQuotMtAndDeactive(quotMt, false);
		for(QuotCompDt quotCompDt : quotCompDts){
			totCompWt += quotCompDt.getMetalWt();
			
		}
		
	
		
		jsonObject.put("totPcs", Math.round(totPcs*100.0)/100.0);
		jsonObject.put("totStone", Math.round(totStone));
		jsonObject.put("totGrossWt",Math.round(totGrossWt*1000.0)/1000.0);
		jsonObject.put("totNetWt",Math.round(totNetWt*1000.0)/1000.0);
		jsonObject.put("totCarat",Math.round(totCarat*1000.0)/1000.0);		
		jsonObject.put("totCompWt",Math.round(totCompWt*1000.0)/1000.0);
		jsonObject.put("totMetalVal",Math.round(totMetalVal*100.0)/100.0);
		jsonObject.put("totStnVal",Math.round(totStnVal*100.0)/100.0);
		jsonObject.put("totCompVal",Math.round(totCompVal*100.0)/100.0);
		jsonObject.put("totLabVal",Math.round(totLabVal*100.0)/100.0);
		jsonObject.put("totSetVal",Math.round(totSetVal*100.0)/100.0);
		jsonObject.put("totHdlgVal",Math.round(totHdlgVal*100.0)/100.0);
		jsonObject.put("totFobVal",Math.round(totFobVal*100.0)/100.0);
		jsonObject.put("totDiscAmt",Math.round(totDiscAmt*100.0)/100.0);
		jsonObject.put("totFinalPrice",Math.round(totFinalPrice*100.0)/100.0);
				
	

		return jsonObject.toString();
		
		
	}

	
	
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	
	
	
	
	
	
	@ResponseBody
	@RequestMapping("/quotMt/applyDiamondRate")
	public String applyDiamondRate(
			@RequestParam(value="mtId")Integer mtId,Principal principal){
		
		String retVal = "1";
		
		synchronized (this) {
			QuotMt quotMt = quotMtService.findOne(mtId);
			List<QuotDt> quotDts = quotDtService.findByQuotMtAndDeactive(quotMt, false);
			
			try {
				for(QuotDt quotDt:quotDts){
					
					if(quotDt.getrLock().equals(false)){
						
						
						retVal=	quotDtService.applyDiamondRate(quotDt);
					}
					
					quotDtService.updateFob(quotDt,netWtWithCompFlg);
					
					
				}
				
				
			} catch (Exception e) {
			
				e.printStackTrace();
				System.out.println(e);
			retVal="-1";
			}
			
			
		}
		
		
	
		
		
		return retVal;
	}
	
	
	@RequestMapping("/quotation/invNoList")
	@ResponseBody
	public String invNoList(@RequestParam(value = "term", required = false) String invNo,
			@RequestParam(value="partyNm") String partyNm){
		
		Page<QuotMt>quotMtList = quotMtService.findByInvNoListByParty(15, 0, "invNo", "ASC", invNo.toUpperCase(), true,partyNm);
		
		StringBuilder sb = new StringBuilder();
		
		for (QuotMt quotMt : quotMtList) {
			sb.append("\"")
				.append(quotMt.getInvNo())
				.append("\", ");
		}

		String str = "[" + sb.toString().trim();
		str = (str.lastIndexOf(",") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]";

		return str;
	}
	
	
	
	@RequestMapping("/quot/reportExcel")
	public ModelAndView getExcel(@RequestParam(value="mtId") Integer mtId){
	    Boolean flg=false;   
		
		
	              //List<Category> categoryList = categoryService.findAll(); 
		
		@SuppressWarnings("unchecked")
		TypedQuery<QuotExportExcel> query =  (TypedQuery<QuotExportExcel>)entityManager.createNativeQuery("{ CALL jsp_rep_quotationexcel(?) }",QuotExportExcel.class);
		query.setParameter(1,mtId);
		
		
		List<QuotExportExcel> quotList = query.getResultList();

		return new ModelAndView(new QuotExcelReportView(flg), "quotList", quotList);
	              
	              
	       }
	
	
	
	@RequestMapping(value = "/quotMt/updatepuritycost", method = RequestMethod.POST)
	@ResponseBody
	public String updatepuritycost(@Valid @ModelAttribute("quotMt") QuotMt quotMt,
			@RequestParam(value="tempPartyId")Integer partyId,
			@RequestParam(value="tempPurityId")Integer purityId,
			@RequestParam(value="tempColorId")Integer colorId,BindingResult result,
			Principal principal){
		
		if (result.hasErrors()) {
			return "-1";
			
		}

		
		String retval="-1";
		
		try {
			
			/*
			 * Party party=partyService.findOne(partyId); quotMt.setParty(party);
			 * if(purityId == null){ quotMt.setPurity(null); } if(colorId == null){
			 * quotMt.setColor(null); }
			 * 
			 * quotMtService.save(quotMt);
			 */
			
			
			List<QuotDt> quotDts = quotDtService.findByQuotMtAndDeactive(quotMt, false);
			
			for(QuotDt quotDt : quotDts){
				quotDtService.updateFob(quotDt, netWtWithCompFlg);
				
			} 
			retval="1";
			
		} catch (Exception e) {
			e.printStackTrace();
			retval="-1";
		}
			
		
		
	
		
		return retval;
	}
	

	
	
}
