package com.jiyasoft.jewelplus.controller.manufacturing.transactions;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.reports.ReportFilter;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostCompDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostExportExcel;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostLabDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostLabDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostMetalDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostStnDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostStnDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingDtItem;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.service.admin.MenuMastService;
import com.jiyasoft.jewelplus.service.admin.RoleRightsService;
import com.jiyasoft.jewelplus.service.admin.UserRoleService;
import com.jiyasoft.jewelplus.service.admin.UserService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IColorService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IComponentService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ILabourTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IMetalService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPartyService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.IPurityService;
//import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingChargeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.ISettingTypeService;
import com.jiyasoft.jewelplus.service.manufacturing.masters.reports.IReportFilterService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostCompDtItemService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostCompDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostLabDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostMetalDtItemService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostMetalDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostStnDtItemService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostStnDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingDtItemService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingDtService;
import com.jiyasoft.jewelplus.service.manufacturing.transactions.ICostingMtService;


@RequestMapping("/manufacturing/transactions")
@Controller
public class CostingMtController {

	@Value("${netWtWithComp}")
	private Boolean netWtWithCompFlg;
	
	@Autowired
	private ICostingMtService costingMtService;
	
	
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
	private ICostingDtService costingDtService;
	
	@Autowired
	private ICostStnDtService costStnDtService;
	
	@Autowired
	private ICostCompDtService costCompDtService;
	
	@Autowired
	private ICostLabDtService costLabDtService;
	
	@Autowired
	private ICostMetalDtService costMetalDtService;
	
	@Autowired
	private RoleRightsService roleRightsService;

	@Autowired
	private MenuMastService menuMastService;

	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private IMetalService metalService;
	
	@Autowired
	private ICostMetalDtItemService costMetalDtItemService;
	
	@Autowired
	private ICostingDtItemService costingDtItemService;
	
	@Autowired
	private ICostStnDtItemService costStnDtItemService;
	
	@Autowired
	private ICostCompDtItemService costCompDtItemService;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private IReportFilterService reportFilterService;

	
	@ModelAttribute("costingMt")
	public CostingMt construct() {
		return new CostingMt();
	}
	
	@ModelAttribute("costingDt")
	public CostingDt constructDt() {
		return new CostingDt();
	}
	
	@ModelAttribute("costStnDt")
	public CostStnDt constructStnDt() {
		return new CostStnDt();
	}
	
	@ModelAttribute("costCompDt")
	public CostCompDt constructCompDt() {
		return new CostCompDt();
	}
	
	@ModelAttribute("costLabDt")
	public CostLabDt constructLabDt() {
		return new CostLabDt();
	}
	
	@ModelAttribute("costingDtItem")
	public CostingDtItem constructDtItem() {
		return new CostingDtItem();
	}
	
	@ModelAttribute("costStnDtItem")
	public CostStnDtItem constructStnDtItem() {
		return new CostStnDtItem();
	}
	
	@ModelAttribute("costCompDtItem")
	public CostCompDtItem constructCompDtItem() {
		return new CostCompDtItem();
	}
	
	@ModelAttribute("costLabDtItem")
	public CostLabDtItem constructLabDtItem() {
		return new CostLabDtItem();
	}
	
	@RequestMapping("/costingMt")
	public String costing(Model model) {
		return "costingMt";
	}
	
	@RequestMapping("/costingTrfExcel")
	public String costingExcel(Model model) {
		return "costingTrfExcel";
	}
	
	
	
	
	@RequestMapping("/costingTransfer")
	public String costingTransfer(Model model) {
		return "costingTransfer";
	}
	
	@RequestMapping("/costCompFinding")
	public String compFinding(Model model) {
		return "costCompFinding";
	}
	
	
	@RequestMapping("/costingMtNew")
	public String costingMtNew(Model model, Principal principal) {
		
		User user = userService.findByName(principal.getName());
		UserRole userRole = userRoleService.findByUser(user);
		MenuMast menuMast = menuMastService.findByMenuNm("costingMtNew");
		RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
		
		if(roleRights == null){
			return "accesDenied";
		}else{
			model.addAttribute("canAdd", roleRights.getCanAdd());
			
			
			
		}
		
		return "costingMtNew";
	}

	
	@RequestMapping("/costingMt/listing")
	@ResponseBody
	public String costingMtListing(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search, 
			@RequestParam(value = "opt", required = true) String opt,Principal principal) {
		
		
		

		StringBuilder sb = new StringBuilder();
		Page<CostingMt> costingMts = null;
		
		if ((search != null) && (search.trim().length() == 0)) {
			search = null;
		}
		
		costingMts = costingMtService.findByInvNo(limit, offset, sort, order,search);
	

		sb.append("{\"total\":").append(costingMts.getTotalElements()).append(",\"rows\": [");

		for (CostingMt costingMt : costingMts) {
			
			sb.append("{\"id\":\"")
					.append(costingMt.getId())
					.append("\",\"party\":\"")
					.append((costingMt.getParty() != null ? costingMt.getParty().getPartyCode() : ""))
					.append("\",\"invNo\":\"")
					.append(costingMt.getInvNo())
					.append("\",\"invDate\":\"")
					.append(costingMt.getInvDateStr())
					.append("\",\"expClose\":\"")
					.append((costingMt.getExpClose() != null ? (costingMt.getExpClose() ? "Closed" : "Open") : ""))
					.append("\",\"actionClose\":\"")
					.append("<a href='javascript:doCostMtOpen(event,")
					.append(costingMt.getId())
					.append(");' class='btn btn-xs btn-success'><span class='glyphicon glyphicon-lock'></span>&nbsp;Open Invoice</a>");
			
				if (opt.equals("0")) {
					
					sb.append("\",\"action1\":\"")
					.append("<a href='/jewels/manufacturing/transactions/costingMt/edit/")
					.append(costingMt.getId())
					.append(".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
					.append("\",\"action2\":\"")
					.append("<a href='javascript:deleteCosting(event,")
					.append(costingMt.getId())
					.append(");' class='btn btn-xs btn-danger'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
					.append("\"},");
				
				}else if (opt.equals("1")) {
					sb.append("\",\"action1\":\"")
					.append("<a href='/jewels/manufacturing/transactions/costingMtNew/edit/")
					.append(costingMt.getId())
					.append(".html' class='btn btn-xs btn-warning' ><span class='glyphicon glyphicon-edit'></span>&nbsp;Edit</a>")
					.append("\",\"action2\":\"")
					.append("<a href='javascript:deleteCosting(event,")
					.append(costingMt.getId())
					.append(");' class='btn btn-xs btn-danger'><span class='glyphicon glyphicon-trash'></span>&nbsp;Delete</a>")
					.append("\"},");
					
						
			}
					
			
		}
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";

		return str;

	}
	
	
	@RequestMapping("/costingMt/add")
	public String addCostingMt(Model model) {
		model = populateModel(model); 
		
		return "costingMt/add";
	}
	
	
	@RequestMapping("/costingMtNew/add")
	public String addCostingMtNew(Model model,Principal principal) {
	
	User user = userService.findByName(principal.getName());
	UserRole userRole = userRoleService.findByUser(user);
	MenuMast menuMast = menuMastService.findByMenuNm("costingMtNew");
	RoleRights roleRights = roleRightsService.findByRoleMastAndMenuMast(userRole.getRoleMast(), menuMast);
	
	if(roleRights == null){
		return "accesDenied";
	}else{
		model.addAttribute("canAdd", roleRights.getCanAdd());
		model.addAttribute("canEdit", roleRights.getCanEdit());
		model.addAttribute("canDelete", roleRights.getCanDelete());
		model.addAttribute("costingType", "merge");
		
	}
	model = populateModel(model);
	return "costingMtNew/add";
	
	}
		
	
	private Model populateModel(Model model) {
		model.addAttribute("partyMap", partyService.getExportClientPartyList());
		model.addAttribute("allPartyMap", partyService.getPartyList());
		model.addAttribute("componentMap", componentService.getComponentList());
		model.addAttribute("purityMap", purityService.getPurityList());
		model.addAttribute("colorMap", colorService.getColorList());
		model.addAttribute("labourTypeMap",labourTypeService.getLabourTypeList());
		model.addAttribute("settingMap",settingService.getSettingList());
		model.addAttribute("settingTypeMap",settingTypeService.getSettingTypeList());
		model.addAttribute("tagColorMap",getTagColor());
		model.addAttribute("metalMap", metalService.getMetalList());
		
		ReportFilter reportFilter = reportFilterService.findByName("styleNotMatch");
		model.addAttribute("xml",reportFilter.getXml());
		return model;
	}
	
	public List<String> getTagColor(){
		
		List<String> list = new ArrayList<String>();
		list.add("White");
		list.add("Green");
		list.add("Pink");
		
		return list;
		
	}
	
	

	@RequestMapping(value = "/costingMt/add", method = RequestMethod.POST)
	public String addEditCostingMt(@Valid @ModelAttribute("costingMt") CostingMt costingMt,
			BindingResult result, @RequestParam(value = "id") Integer id,
			RedirectAttributes redirectAttributes, 
			@RequestParam(value = "opt", required = true) String opt,Principal principal) {

		String action = "added";
		String retVal = "redirect:/manufacturing/transactions/costingMt/add.html";
		Date aDate = null;

		if (result.hasErrors()) {
			if(opt.equals("1")) {
				return "costingMtNew/add";
			}else {
				return "costingMt/add";	
			}
		}
		
		Boolean invoiceAvailable = true;
		
		if (id == null) {

			invoiceAvailable = (costingMtService.findByInvNoAndDeactive(costingMt.getInvNo(), false) == null);
		} else {
			CostingMt costingMt2 = costingMtService.findOne(id);
			if (! (costingMt.getInvNo().equalsIgnoreCase(costingMt2.getInvNo())) ) {
				invoiceAvailable = (costingMtService.findByInvNoAndDeactive(costingMt.getInvNo(), false) == null);
			}
		}
		
		if (!invoiceAvailable) {
			if(opt.equals("1")) {
				redirectAttributes.addFlashAttribute("success", false);
				redirectAttributes.addFlashAttribute("action", "Duplicate Invoice Number Error");
				
				if(id != null && id>0 ) {
					return "redirect:/manufacturing/transactions/costingMtNew/edit/"+id+".html";
				}else {
					return "redirect:/manufacturing/transactions/costingMtNew/add.html";
				}
				
				
			}else {
				return "costingMt/add";	
			}
			
			
		}
		
		

		if (id == null || id.equals("") || (id != null && id == 0)) {
			
			
			aDate = new java.util.Date();
			costingMt.setCreatedBy(principal.getName());
			costingMt.setCreatedDate(new java.util.Date());
			costingMt.setUniqueId(aDate.getTime());
			costingMt.setParty(costingMt.getParty());
			costingMt.setInvNo(costingMt.getInvNo().trim());
			costingMtService.save(costingMt);
		} else {
			
			CostingMt costingMtOld = costingMtService.findOne(id);
			costingMtOld.setInvNo(costingMt.getInvNo().trim());
			costingMtOld.setInvDate(costingMt.getInvDate());
			costingMtOld.setParty(costingMt.getParty());
			costingMtOld.setParty(costingMt.getParty());
			costingMtOld.setMetalRate(costingMt.getMetalRate());
			costingMtOld.setSilverRate(costingMt.getSilverRate());
			costingMtOld.setLossPercMt(costingMt.getLossPercMt());
			costingMtOld.setAlloyRate(costingMt.getAlloyRate());
			costingMtOld.setAddPercent(costingMt.getAddPercent());
			costingMtOld.setTagPercent(costingMt.getTagPercent());
			costingMtOld.setDispPercent(costingMt.getDispPercent());
			costingMtOld.setTagColor(costingMt.getTagColor());
			costingMtOld.setDutyPerc(costingMt.getDutyPerc());
			costingMtOld.setDiscountPerc(costingMt.getDiscountPerc());
			costingMtOld.setModiBy(principal.getName());
			costingMtOld.setModiDate(new java.util.Date());
			
			if(costingMtOld.getExpClose() == false){
				costingMtService.save(costingMtOld);
			}else{
				System.out.println("------------------>>>>>>>>>>>>>>>>>>>>  record not updated");
			}
			
			action = "updated";
			if (opt.equals("0")) {
			retVal = "redirect:/manufacturing/transactions/costingMt/edit/"+id+".html";
		}else if (opt.equals("1")) {
			retVal = "redirect:/manufacturing/transactions/costingMtNew/edit/"+id+".html";
		}
			}

		if (opt.equals("0")) {
			if (action.equals("added")) {
				CostingMt costingMtNew = costingMtService.findByUniqueId(costingMt.getUniqueId());
				Integer costingMtId = costingMtNew.getId();
				retVal  = "redirect:/manufacturing/transactions/costingMt/edit/"+costingMtId+".html";
			}	
		}else if(opt.equals("1")){
			if (action.equals("added")) {
				CostingMt costingMtNew = costingMtService.findByUniqueId(costingMt.getUniqueId());
				Integer costingMtId = costingMtNew.getId();
				retVal  = "redirect:/manufacturing/transactions/costingMtNew/edit/"+costingMtId+".html";
			}	
		}
		
		
		
		
		redirectAttributes.addFlashAttribute("success", true);
		redirectAttributes.addFlashAttribute("action", action);
		
		return retVal;

	}

	@RequestMapping("/costingMt/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		CostingMt costingMt = costingMtService.findOne(id);
		model.addAttribute("costingMt", costingMt);
		model.addAttribute("closeStatus",costingMt.getExpClose());
		model = populateModel(model);


		return "costingMt/add";
	}
	
	@RequestMapping("/costingMtNew/edit/{id}")
	public String editCostingtMtNew(@PathVariable int id, Model model) {
		CostingMt costingMt = costingMtService.findOne(id);
		model.addAttribute("costingMt", costingMt);
		model.addAttribute("closeStatus",costingMt.getExpClose());
		model = populateModel(model);
		model.addAttribute("costingType", "merge");
		return "costingMtNew/add";
	}

	@RequestMapping("/costingMt/delete/{id}")
	public String delete(@PathVariable int id) {
		
		costingMtService.delete(id);
		
		CostingMt costingMt = costingMtService.findOne(id);
		
		List<CostingDt> costingDts = costingDtService.findByCostingMtAndDeactive(costingMt, false);
		for(CostingDt costingDt:costingDts){
			costingDtService.delete(costingDt.getId());
		}
		
		List<CostStnDt> costStnDts = costStnDtService.findByCostingMtAndDeactive(costingMt, false);
		for(CostStnDt costStnDt:costStnDts){
			costStnDtService.delete(costStnDt.getId());
		}
		
		List<CostCompDt> costCompDts = costCompDtService.findByCostingMtAndDeactive(costingMt, false);
		for(CostCompDt costCompDt:costCompDts){
			costCompDtService.delete(costCompDt.getId());
		}
		
		List<CostLabDt> costLabDts = costLabDtService.findByCostingMtAndDeactive(costingMt, false);
		for(CostLabDt costLabDt:costLabDts){
			costLabDtService.delete(costLabDt.getId());;
		}
		
		List<CostMetalDt> costMetalDts= costMetalDtService.findByCostingMtAndDeactive(costingMt, false);
		for (CostMetalDt costMetalDt : costMetalDts) {
			costMetalDtService.delete(costMetalDt.getId());
		}

		return "redirect:/manufacturing/transactions/costingMt.html";
	}
	
	
	
	@RequestMapping("/costingMtNew/delete/{id}")
	@ResponseBody
	public String costMtDelete(@PathVariable int id, Principal principal) {
		
		String retVal= costingMtService.deleteCostingMtInvoice(id, principal);
		if(!retVal.isEmpty()){
			return retVal;
		}
			
			return "redirect:/manufacturing/transactions/costingMtNew.html";
			
		}
		
		
		
	
	
	
	
	@ResponseBody
	@RequestMapping("/costingMt/exportCloseOpen/{id}")
	public String openClose(@PathVariable int id){
		
		CostingMt costingMt = costingMtService.findOne(id);
		
		if(costingMt.getExpClose().equals(true)){
			 costingMt.setExpClose(false);
			 costingMtService.save(costingMt);
		}
		
		
		return "1";
	}
	
	
	@RequestMapping("/costMt/invoiceNoAvailable")
	@ResponseBody
	public String invoiceAvailable(@RequestParam String invNo,
			@RequestParam Integer id) {

		Boolean invoiceAvailable = true;

		if (id == null) {

			invoiceAvailable = (costingMtService.findByInvNoAndDeactive(invNo, false) == null);
		} else {
			CostingMt costingMt = costingMtService.findOne(id);
			if (! (invNo.equalsIgnoreCase(costingMt.getInvNo())) ) {
				invoiceAvailable = (costingMtService.findByInvNoAndDeactive(invNo, false) == null);
			}
		}

		return invoiceAvailable.toString();
	}

	
	
	
	
	@RequestMapping(value = "/applyRate/transfer", method = RequestMethod.POST)
	@ResponseBody
	public String applyRate(@Valid @ModelAttribute("costingMt") CostingMt costingMt,
			@RequestParam(value="tempPartyId")Integer partyId ,BindingResult result,
			Principal principal){
		
		if (result.hasErrors()) {
			return "-1";
			
		}

		
		String retval="-1";
		
		try {
			
			/*
			 * Party party=partyService.findOne(partyId); costingMt.setParty(party);
			 * costingMtService.save(costingMt);
			 */
			
			
			List<CostingDt> costingDts = costingDtService.findByCostingMtAndDeactive(costingMt, false);
			
			for(CostingDt costingDt : costingDts){
				costingDtService.applyRate(costingDt,principal);
				
				
			} 
			retval="1";
			
		} catch (Exception e) {
			e.printStackTrace();
			retval="-1";
		}
			
		
		
	
		
		return retval;
	}
	
	
	
	
	
	
	@RequestMapping(value = "/applyRate/merge", method = RequestMethod.POST)
	@ResponseBody
	public String applyRateMerge(@Valid @ModelAttribute("costingMt") CostingMt costingMt,
			@RequestParam(value="tempPartyId")Integer partyId ,BindingResult result,
			Principal principal){
		
		if (result.hasErrors()) {
			return "-1";
			
		}

		
		String retval="-1";
		
		try {
			
			Party party=partyService.findOne(partyId);
			/*
			 * costingMt.setParty(party); costingMtService.save(costingMt);
			 */
			
			
			
			
			
			if(party.getDiaWtType().equalsIgnoreCase("As Per Order")) {
				
				costingMtService.updateDiaWtAsPerOrder(costingMt.getId());
			
				
			}
			
			
			
			List<CostingDtItem> costingDtItems = costingDtItemService.findByCostingMtAndDeactive(costingMt, false);
			
			for(CostingDtItem costingDtItem : costingDtItems){
				
				costingDtItemService.applyItemRate(costingDtItem,principal,netWtWithCompFlg);
				
			} 
			retval="1";
			
		} catch (Exception e) {
			e.printStackTrace();
			retval="-1";
		}
			
		
		
	
		
		return retval;
	}
	
	
	
	
	@RequestMapping(value = "/costMt/updatepuritycost", method = RequestMethod.POST)
	@ResponseBody
	public String updatepuritycost(@Valid @ModelAttribute("costingMt") CostingMt costingMt,
			@RequestParam(value="tempPartyId")Integer partyId ,BindingResult result,
			Principal principal){
		
		if (result.hasErrors()) {
			return "-1";
			
		}

		
		String retval="-1";
		
		try {
			
			Party party=partyService.findOne(partyId);
			costingMt.setParty(party);
			costingMtService.save(costingMt);
			
			
			List<CostingDtItem> costingDtItems = costingDtItemService.findByCostingMtAndDeactive(costingMt, false);
			
			for(CostingDtItem costingDtItem : costingDtItems){
				costingDtItemService.updateItemFob(costingDtItem,netWtWithCompFlg);
				
			} 
			retval="1";
			
		} catch (Exception e) {
			e.printStackTrace();
			retval="-1";
		}
			
		
		
	
		
		return retval;
	}
	
	
	
	@RequestMapping("/costing/invNoList")
	@ResponseBody
	public String invNoList(@RequestParam(value = "term", required = false) String invNo,
			@RequestParam(value="fieldNm") String fieldNm){
		Page<CostingMt>costingMtList = costingMtService.findByInvNo(15, 0, "invNo", "ASC", invNo.toUpperCase());
		
		StringBuilder sb = new StringBuilder();
		System.out.println("fieldNm  "+fieldNm);
		
		System.out.println("term       "+invNo);

		for (CostingMt costingMt : costingMtList) {
			if(fieldNm.equalsIgnoreCase(costingMt.getInvNo())){
				continue;
			}
			sb.append("\"")
				.append(costingMt.getInvNo())
				.append("\", ");
		}

		String str = "[" + sb.toString().trim();
		str = (str.lastIndexOf(",") != -1 ? str.substring(0, str.length() - 1) : str);
		str += "]";

		return str;
	}
	
	@RequestMapping(value="/costing/transfer",method=RequestMethod.POST)
	@ResponseBody
	public String costingTransfer(@RequestParam(value="dtids")String dtids,
			@RequestParam(value="fromInvoice")String fromInvoice,
			@RequestParam(value="toInvoice")String toInvoice,Principal principal){
			return costingMtService.costingTransfer(fromInvoice, toInvoice, dtids,principal);
	}
	
	
	@ResponseBody
	@RequestMapping("/costingMt/partyWiseListing")
	public String popPartyWiseListing(
			@RequestParam(value = "partyIds") String partyIds,
			@RequestParam(value = "fromDate") String fromDate,
			@RequestParam(value = "toDate") String toDate,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "search", required = false) String search ) throws ParseException{
		
		
		
		List<Object[]> objects = costingMtService.partyWiseAndDateWiseListing(limit,offset,sort,order,search,partyIds, fromDate, toDate);
		
		StringBuilder sb = new StringBuilder();
		sb.append("{\"total\":").append(100000).append(",\"rows\": [");
		
		for (Object[] obj : objects) {
			int i=0;
			 for (Object element : obj) {
			    	if(i == 0){
			    		sb.append("{\"id\":\"");
			    		sb.append(element != null ? element.toString() : "");
			    	}else{
			    		sb.append("\",\"invNo\":\"")
						.append(element != null ?  element.toString() : "")
			    		.append("\"},");
			    	}
			    	i++;
			    }
			
			}
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		
		return str;
	}
	
	
	@ResponseBody
	@RequestMapping("/costingMt/ExportInvoiceAll/partyWiseListing")
	public String exportInvoiceAllPartyWiseListing(
			@RequestParam(value = "partyIds") String partyIds,
			@RequestParam(value = "fromDate") String fromDate,
			@RequestParam(value = "toDate") String toDate,
			@RequestParam(value = "search", required = false) String search ) throws ParseException{
		
		
		
		List<Object[]> objects = costingMtService.exportInvoiceAllPartyWiseAndDateWiseListing(search, partyIds, fromDate, toDate);
		
		StringBuilder sb = new StringBuilder();
		sb.append("{\"total\":").append(objects.size()).append(",\"rows\": [");
		
		for (Object[] obj : objects) {
			int i=0;
			 for (Object element : obj) {
			    	if(i == 0){
			    		sb.append("{\"id\":\"");
			    		sb.append(element != null ? element.toString() : "");
			    	}else{
			    		sb.append("\",\"invNo\":\"")
						.append(element != null ?  element.toString() : "")
			    		.append("\"},");
			    	}
			    	i++;
			    }
			
			}
		
		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		
		return str;
	}
	
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/costingMt/dtListingExcel")
	public String dtListingExcel(@RequestParam(value="invCond")String pInvCond){

		String invNoCond="";
		
		if (pInvCond.length() > 0) {
			invNoCond =  "  dtId in (" + pInvCond + ")  ";
		}else{
			invNoCond = " ' ' ";
		}
		
		
		TypedQuery<Object[]> query =  (TypedQuery<Object[]>)entityManager.createNativeQuery("{ CALL jsp_rep_invdtexcel(?) }");
		query.setParameter(1,invNoCond);
		
		StringBuilder sb = new StringBuilder();

		sb.append("{\"total\":").append(15000).append(",\"rows\": [");
			
		List<Object[]> dtList = query.getResultList();
		
		
		
		for (Object[] result : dtList) {
			
			sb.append("{\"productId\":\"")
			.append(result[0]!=null? result[0]:"")
			.append("\",\"productCode\":\"")
			.append(result[1]!=null? result[1]:"")
			.append("\",\"productName\":\"")
			.append(result[2]!=null? result[2]:"")
			.append("\",\"categoryName\":\"")
			.append(result[3]!=null? result[3]:"")
			.append("\",\"subCategoryName\":\"")
			.append(result[4]!=null? result[4]:"")
			.append("\",\"programName\":\"")
			.append(result[5]!=null? result[5]:"")
			.append("\",\"noofPcsinpack\":\"")
			.append(result[6]!=null? result[6]:"")
			.append("\",\"theam\":\"")
			.append(result[7]!=null? result[7]:"")
			.append("\",\"collectionName\":\"")
			.append(result[8]!=null? result[8]:"")
			.append("\",\"styleno\":\"")
			.append(result[9]!=null? result[9]:"")
			.append("\",\"styleName\":\"")
			.append(result[10]!=null? result[10]:"")
			.append("\",\"mRP\":\"")
			.append(result[11]!=null? result[11]:"")
			.append("\",\"shopFor\":\"")
			.append(result[12]!=null? result[12]:"")
			.append("\",\"metalName\":\"")
			.append(result[13]!=null? result[13]:"")
			.append("\",\"metalPurityName\":\"")
			.append(result[14]!=null? result[14]:"")
			.append("\",\"metalColorName\":\"")
			.append(result[15]!=null? result[15]:"")
			.append("\",\"weight\":\"")
			.append(result[16]!=null? result[16]:"")
			.append("\",\"engraving\":\"")
			.append(result[17]!=null? result[17]:"")
			.append("\",\"productType\":\"")
			.append(result[18]!=null? result[18]:"")
			.append("\",\"instock\":\"")
			.append(result[19]!=null? result[19]:"")
			.append("\",\"description\":\"")
			.append(result[20]!=null? result[20]:"")
			.append("\"},");
			
			
		    
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
		
		return str;
		
	}
	
	
	
	
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/costingMt/dtStnListingExcel")
	public String dtStnListingExcel(@RequestParam(value="invCond")String pInvCond){
		
		String invNoCond="";
		
		if (pInvCond.length() > 0) {
			invNoCond =  "  dtId in (" + pInvCond + ")  ";
		}else{
			invNoCond = " ' ' ";
		}
		
		
				
		TypedQuery<Object[]> query =  (TypedQuery<Object[]>)entityManager.createNativeQuery("{ CALL jsp_rep_invstndtexcel(?) }");
		query.setParameter(1,invNoCond);
		
		StringBuilder sb = new StringBuilder();

		sb.append("{\"total\":").append(15000).append(",\"rows\": [");
			
		List<Object[]> dtList = query.getResultList();
		for (Object[] result : dtList) {
			
			sb.append("{\"productCode\":\"")
			.append(result[0]!=null? result[0]:"")
			.append("\",\"stoneTypeName\":\"")
			.append(result[1]!=null? result[1]:"")
			.append("\",\"stoneShapeName\":\"")
			.append(result[2]!=null? result[2]:"")
			.append("\",\"subStoneShapeName\":\"")
			.append(result[3]!=null? result[3]:"")
			.append("\",\"stoneQualityName\":\"")
			.append(result[4]!=null? result[4]:"")
			.append("\",\"stonePcs\":\"")
			.append(result[5]!=null? result[5]:"")
			.append("\",\"stoneCaret\":\"")
			.append(result[6]!=null? result[6]:"")
			.append("\",\"stoneSize\":\"")
			.append(result[7]!=null? result[7]:"")
			.append("\",\"stoneSizeGroup\":\"")
			.append(result[8]!=null? result[8]:"")
			.append("\"},");
			
			
		    
		}

		String str = sb.toString();
		str = (str.indexOf("},") != -1 ? str.substring(0, str.length() - 1)
				: str);
		str += "]}";
		
	
		return str;
		
	}
	
	@ResponseBody
	@RequestMapping("/costingMt/summary")
	public String getSummary(
			@RequestParam(value="mtId")Integer mtId,Principal principal){
		
		
		CostingMt costingMt =costingMtService.findOne(mtId);
		
		List<CostingDt>costingDts =costingDtService.findByCostingMtAndDeactive(costingMt, false);
		
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
		
		for(CostingDt costingDt :costingDts){
			
			totPcs +=costingDt.getPcs();
			totGrossWt +=costingDt.getGrossWt();
			totNetWt += costingDt.getNetWt();
			totMetalVal += costingDt.getMetalValue();
			totStnVal += costingDt.getStoneValue();
			totCompVal +=costingDt.getCompValue();
			totLabVal += costingDt.getLabValue();
			totSetVal += costingDt.getSetValue();
			totHdlgVal += costingDt.getHdlgValue();
			totFobVal += costingDt.getFob();
			totDiscAmt+= costingDt.getDiscAmount();
			totFinalPrice += costingDt.getFinalPrice();
			
			
			
			
			
		}
		
		
		List<CostStnDt>costStnDts =costStnDtService.findByCostingMtAndDeactive(costingMt, false);
		for(CostStnDt costStnDt : costStnDts){
			
			totStone +=costStnDt.getStone();
			totCarat += costStnDt.getCarat();
			
			
		}
		
		List<CostCompDt>costCompDts = costCompDtService.findByCostingMtAndDeactive(costingMt, false);
		for(CostCompDt costCompDt : costCompDts){
			totCompWt += costCompDt.getMetalWt();
			
		}
		
	
		
		jsonObject.put("totPcs", Math.round(totPcs*100.0)/100.0);
		jsonObject.put("totStone", Math.round(totStone));
		jsonObject.put("totGrossWt", Math.round(totGrossWt*1000.0)/1000.0);
		jsonObject.put("totNetWt", Math.round(totNetWt*1000.0)/1000.0);
		jsonObject.put("totCarat",Math.round(totCarat*1000.0)/1000.0);		
		jsonObject.put("totCompWt", Math.round(totCompWt*1000.0)/1000.0);
		jsonObject.put("totMetalVal",Math.round(totMetalVal*100.0)/100.0);
		jsonObject.put("totStnVal", Math.round(totStnVal*100.0)/100.0);
		jsonObject.put("totCompVal",Math.round(totCompVal*100.0)/100.0);
		jsonObject.put("totLabVal", Math.round(totLabVal*100.0)/100.0);
		jsonObject.put("totSetVal",Math.round(totSetVal*100.0)/100.0);
		jsonObject.put("totHdlgVal",Math.round(totHdlgVal*100.0)/100.0);
		jsonObject.put("totFobVal",Math.round(totFobVal*100.0)/100.0);
		jsonObject.put("totDiscAmt",Math.round(totDiscAmt*100.0)/100.0);
		jsonObject.put("totFinalPrice",Math.round(totFinalPrice*100.0)/100.0);
				
	

		return jsonObject.toString();
		
		
	}

		

	@ResponseBody
		@RequestMapping("/costingMt/dtItemSummary")
		public String getDtItemSummary(
				@RequestParam(value="mtId")Integer mtId,Principal principal){
			
			
			CostingMt costingMt =costingMtService.findOne(mtId);
			
			List<CostingDtItem>costingDtItems =costingDtItemService.findByCostingMtAndDeactive(costingMt, false);
			
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
			
			for(CostingDtItem costingDtItem :costingDtItems){
				
				totPcs +=costingDtItem.getPcs();
				totGrossWt +=costingDtItem.getGrossWt();
				totNetWt += costingDtItem.getNetWt();
				totMetalVal += costingDtItem.getMetalValue();
				totStnVal += costingDtItem.getStoneValue();
				totCompVal +=costingDtItem.getCompValue();
				totLabVal += costingDtItem.getLabValue();
				totSetVal += costingDtItem.getSetValue();
				totHdlgVal += costingDtItem.getHdlgValue();
				totFobVal += costingDtItem.getFob();
				totDiscAmt+= costingDtItem.getDiscAmount();
				totFinalPrice += costingDtItem.getFinalPrice();
				
				
				
				
				
			}
			
			
			List<CostStnDtItem>costStnDtItems =costStnDtItemService.findByCostingMtAndDeactive(costingMt, false);
			for(CostStnDtItem costStnDtItem : costStnDtItems){
				
				totStone +=costStnDtItem.getStone();
				totCarat += costStnDtItem.getCarat();
				
				
			}
			
			List<CostCompDtItem>costCompDtItems = costCompDtItemService.findByCostingMtAndDeactive(costingMt, false);
			for(CostCompDtItem costCompDtItem : costCompDtItems){
				totCompWt += costCompDtItem.getMetalWt();
				
			}
			
		
			
			jsonObject.put("totPcs", Math.round(totPcs*100.0)/100.0);
			jsonObject.put("totStone", Math.round(totStone));
			jsonObject.put("totGrossWt",Math.round(totGrossWt*1000.0)/1000.0);
			jsonObject.put("totNetWt", Math.round(totNetWt*1000.0)/1000.0);
			jsonObject.put("totCarat",Math.round(totCarat*1000.0)/1000.0);		
			jsonObject.put("totCompWt", Math.round(totCompWt*1000.0)/1000.0);
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
	



	@ResponseBody
	@RequestMapping("/costingMt/updateLossPerc")
	public String updateLossPerc(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "lossPerc", required = false) Double lossPerc,
			@RequestParam(value ="costingType", required = false) String costingType) {
		
		
		if(costingType != null && costingType.equalsIgnoreCase("merge")){
			
			CostMetalDtItem costMetalDtItem =  costMetalDtItemService.findOne(id);
			
			costMetalDtItem.setLossPerc(lossPerc);
			costMetalDtItemService.save(costMetalDtItem);
			
			if(costMetalDtItem.getMainMetal().equals(true)){
				CostingDtItem costingDtItem = costMetalDtItem.getCostingDtItem();
				costingDtItem.setLossPercDt(lossPerc);
				costingDtItemService.save(costingDtItem);
				
			}
			
			
			costingDtItemService.updateItemFob(costMetalDtItem.getCostingDtItem(),netWtWithCompFlg);
			
			
		}else{
			
			CostMetalDt costMetalDt =  costMetalDtService.findOne(id);
			
			costMetalDt.setLossPerc(lossPerc);
			costMetalDtService.save(costMetalDt);
			
			if(costMetalDt.getMainMetal().equals(true)){
				CostingDt costingDt = costMetalDt.getCostingDt();
				costingDt.setLossPercDt(lossPerc);
				costingDtService.save(costingDt);
				
			}
			
			costingDtService.updateFob(costMetalDt.getCostingDt());
			
			
				
		}
		return "1";
		
		
	}
	
	
	@RequestMapping("/costing/reportExcel")
	public ModelAndView getExcel(@RequestParam(value="mtId") Integer mtId){
	       
	              //List<Category> categoryList = categoryService.findAll(); 
		
		@SuppressWarnings("unchecked")
		TypedQuery<Object[]> query =  (TypedQuery<Object[]>)entityManager.createNativeQuery("{ CALL jsp_rep_costsheetExcel(?) }");
		query.setParameter(1,mtId);
		
		
		List<Object[]> costingList = query.getResultList();
		
		
	              
	              return new ModelAndView(new CostingExcelReportView(), "costingList", costingList);
	              
	              
	       }
	
	
	
	
	@RequestMapping("/costing/costingExcel")
	public ModelAndView costingExcel(@RequestParam(value="mtId") Integer mtId,
			@RequestParam(value="perPcFlg" ,required = false) Boolean perPcFlg,
			@RequestParam(value="poWiseFlg" ,required = false) Boolean poWiseFlg){
	       
	              //List<Category> categoryList = categoryService.findAll(); 
		
		@SuppressWarnings("unchecked")
		TypedQuery<CostExportExcel> query =  (TypedQuery<CostExportExcel>)entityManager.createNativeQuery("{ CALL jsp_rep_costingexcel(?,?,?) }",CostExportExcel.class);
		query.setParameter(1,mtId);
		query.setParameter(2,poWiseFlg);
		query.setParameter(3,perPcFlg);
		
		List<CostExportExcel> costList = query.getResultList();
		          
	              return new ModelAndView(new CostExcelReportView(perPcFlg), "costList", costList);
	              
	   }
	
	
	@RequestMapping("/costing/costingExcelMerge")
	public ModelAndView costingExcelMerge(@RequestParam(value="mtId") Integer mtId,
			@RequestParam(value="perPcFlg" ,required = false) Boolean perPcFlg,
			@RequestParam(value="poWiseFlg" ,required = false) Boolean poWiseFlg){
	       
	              //List<Category> categoryList = categoryService.findAll(); 
		
		@SuppressWarnings("unchecked")
		TypedQuery<CostExportExcel> query = (TypedQuery<CostExportExcel>)entityManager.createNativeQuery("{ CALL jsp_rep_costingexcelMerge(?,?,?) }",CostExportExcel.class);
		query.setParameter(1,mtId);
		query.setParameter(2,poWiseFlg);
		query.setParameter(3,perPcFlg);
		
		List<CostExportExcel> costList = query.getResultList();
		          
	    return new ModelAndView(new CostExcelReportView(perPcFlg),"costList",costList);
	              
	   }
	
	
	

	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	
	
	
}
